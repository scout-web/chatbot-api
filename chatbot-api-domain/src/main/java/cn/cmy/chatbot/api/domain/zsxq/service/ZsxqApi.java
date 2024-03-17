package cn.cmy.chatbot.api.domain.zsxq.service;

import cn.cmy.chatbot.api.domain.zsxq.IZsxqApi;
import cn.cmy.chatbot.api.domain.zsxq.model.aggregates.CommentAggredates;
import cn.cmy.chatbot.api.domain.zsxq.model.aggregates.TalkAggregates;


import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import java.io.IOException;

/**
 * @author cmy
 * @Description api
 * @date 2024/3/17 16:52
 * @Version 1.0
 */
@Service
public class ZsxqApi implements IZsxqApi {
    /**
     * 查询talk问题
     * @param groupId 星球组id
     * @param cookie 用户cookie
     * @return
     */
    @Override
    public TalkAggregates queryQuestions(String groupId, String cookie) throws IOException {
        //get请求
        String url = "https://api.zsxq.com/v2/groups/"+groupId+"/topics?scope=all&count=20";

        //创建httpClient
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //创建get请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Cookie", cookie);
        httpGet.setHeader("Accept",  "application/json, text/plain, */*");
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36 Edg/115.0.1901.183");

        //执行请求
        TalkAggregates talkAggregates = null;
        CloseableHttpResponse response = httpClient.execute(httpGet);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonRes = EntityUtils.toString(response.getEntity(), "UTF-8");
            //解析json为对象
            talkAggregates = JSON.parseObject(jsonRes, TalkAggregates.class);
        } else {
            System.out.println("请求失败");
            System.out.println(response.getStatusLine().getStatusCode());
        }
        return talkAggregates;
    }


    /**
     * 回答问题
     * @param topicId
     * @param text
     * @param cookie
     * @return
     * @throws IOException
     */
    @Override
    public CommentAggredates answerQuestion(String topicId, String text, String cookie) throws IOException {
        //post请求
        String url = "https://api.zsxq.com/v2/topics/"+topicId+"/comments";

        String paramJson = "{\"req_data\":{\"text\":\""+text+"\",\"image_ids\":[],\"mentioned_user_ids\":[]}}";

        StringEntity entity = new StringEntity(paramJson, ContentType.create("text/json","UTF-8"));

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Cookie",cookie);
        httpPost.setHeader("Content-Type",  "application/json");
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36 Edg/122.0.0.0");
        httpPost.setEntity(entity);

        //执行请求
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = httpClient.execute(httpPost);

        CommentAggredates commentAggredates = null;
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonRes = EntityUtils.toString(response.getEntity(), "UTF-8");
            //解析json为对象
            commentAggredates = JSON.parseObject(jsonRes, CommentAggredates.class);
            System.out.println("回答星球问题的结果："+commentAggredates);
        } else {
            System.out.println("请求失败");
            System.out.println(response.getStatusLine().getStatusCode());
        }
        return commentAggredates;
    }
}
