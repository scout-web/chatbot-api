package cn.cmy.chatbot.api.test;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.apache.http.client.methods.CloseableHttpResponse;
import cn.hutool.http.HttpRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import java.io.IOException;

/**
 * @author cmy
 * @Description 单元测试
 * @date 2024/3/16 22:12
 * @Version 1.0
 */
@SpringBootTest
public class ApiTest {

    @Test
    public void query_unanswered_questions() throws IOException {
        //get请求
//        String url = "https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20";
        String url = "https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20";
//        String url = "https://api.zsxq.com/v2/topics/1522548815521512/comments?sort=asc&count=30&with_sticky=true";

        String cookie = "zsxq_access_token=DA15E971-5DAF-0070-EB1F-260AC97F8264_A9E87DB1E0A7FDD5; zsxqsessionid=204e27788eea148a5c013cbb161dfb1a; abtest_env=product; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22812841554881412%22%2C%22first_id%22%3A%2218e4a63cea91afb-0359203cae7592-4c657b58-1638720-18e4a63ceaa7a8%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThlNGE2M2NlYTkxYWZiLTAzNTkyMDNjYWU3NTkyLTRjNjU3YjU4LTE2Mzg3MjAtMThlNGE2M2NlYWE3YTgiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI4MTI4NDE1NTQ4ODE0MTIifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22812841554881412%22%7D%2C%22%24device_id%22%3A%2218e4a63cea91afb-0359203cae7592-4c657b58-1638720-18e4a63ceaa7a8%22%7D";

        //创建httpClient
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //创建get请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Cookie", cookie);
        httpGet.setHeader("Accept",  "application/json, text/plain, */*");
        //执行请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(res);
        } else {
            System.out.println("请求失败");
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer_question() throws IOException {
        //post请求
        String url = "https://api.zsxq.com/v2/topics/1522548815521512/comments";

        String paramJson = "{\"req_data\":{\"text\":\"test!!!\",\"image_ids\":[],\"mentioned_user_ids\":[]}}";

        StringEntity entity = new StringEntity(paramJson, ContentType.create("text/json","UTF-8"));

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Cookie","zsxq_access_token=DA15E971-5DAF-0070-EB1F-260AC97F8264_A9E87DB1E0A7FDD5; zsxqsessionid=204e27788eea148a5c013cbb161dfb1a; abtest_env=product; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22812841554881412%22%2C%22first_id%22%3A%2218e4a63cea91afb-0359203cae7592-4c657b58-1638720-18e4a63ceaa7a8%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThlNGE2M2NlYTkxYWZiLTAzNTkyMDNjYWU3NTkyLTRjNjU3YjU4LTE2Mzg3MjAtMThlNGE2M2NlYWE3YTgiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI4MTI4NDE1NTQ4ODE0MTIifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22812841554881412%22%7D%2C%22%24device_id%22%3A%2218e4a63cea91afb-0359203cae7592-4c657b58-1638720-18e4a63ceaa7a8%22%7D");
        httpPost.setHeader("Content-Type",  "application/json");
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36 Edg/122.0.0.0");
        httpPost.setEntity(entity);

        //执行请求
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = httpClient.execute(httpPost);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(res);
        } else {
            System.out.println("请求失败");
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}
