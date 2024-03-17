package cn.cmy.chatbot.api.domain.ai.service;

import cn.cmy.chatbot.api.domain.ai.IOpenAI;
import cn.cmy.chatbot.api.domain.ai.model.aggregates.AIAnswer;
import cn.cmy.chatbot.api.domain.ai.model.vo.Choices;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author cmy
 * @Description api服务
 * @date 2024/3/17 18:10
 * @Version 1.0
 */
@Service
public class OpenAI implements IOpenAI {
    @Override
    public String doChatGPT(String openAiKey, String question) throws IOException {
        String url = "https://api.openai.com/v1/completions";

        //构造请求参数
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("model", "gpt-3.5-turbo");
        paramMap.put("temperature", 0.2);
        List<Map<String, String>> dataList = new ArrayList<>();
        dataList.add(new HashMap<String, String>(){{
            put("role", "user");
            //问题
            put("content", question);
        }});
        paramMap.put("messages", dataList);

        //构造httpClient
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //构造post请求
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer " + openAiKey);

        //构造请求参数
        StringEntity stringEntity = new StringEntity(JSONUtil.toJsonStr(paramMap), ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        //执行请求，获取响应
        CloseableHttpResponse response = httpClient.execute(post);
        //判断响应状态码
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            //获取响应体，解析成java对象
            String jsonStr = EntityUtils.toString(response.getEntity());
            AIAnswer aiAnswer = JSON.parseObject(jsonStr, AIAnswer.class);
            StringBuilder answers = new StringBuilder();
            //获取回答
            List<Choices> choices = aiAnswer.getChoices();
            for (Choices choice : choices) {
                answers.append(choice.getMessage().getContent());
            }
            return answers.toString();
        } else {
            throw new RuntimeException("api.openai.com Err Code is " + response.getStatusLine().getStatusCode());
        }
    }
}
