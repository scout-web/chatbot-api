package cn.cmy.chatbot.api.test;

import cn.cmy.chatbot.api.domain.ai.IOpenAI;
import cn.cmy.chatbot.api.domain.ai.service.OpenAI;
import cn.cmy.chatbot.api.domain.zsxq.IZsxqApi;
import cn.cmy.chatbot.api.domain.zsxq.model.aggregates.TalkAggregates;
import cn.cmy.chatbot.api.domain.zsxq.model.vo.Topics;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * @author cmy
 * @Description TODO
 * @date 2024/3/17 17:16
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {
    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;
    @Value("${chatbot-api.openAiKey}")
    private String openAiKey;

    @Resource
    private IZsxqApi zsxqApi;
    @Resource
    private IOpenAI openAI;

    @Test
    public void test_zsxqApi() throws IOException {
        TalkAggregates talkAggregates = zsxqApi.queryQuestions(groupId, cookie);
//        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = talkAggregates;
        logger.info("测试结果：{}", JSON.toJSONString(talkAggregates));

        List<Topics> topics = talkAggregates.getRespData().getTopics();
        for (Topics topic : topics) {
            long topicId = topic.getTopicId();
            String text = topic.getTalk().getText();
            logger.info("topicId：{} text：{}", topicId, text);
//
//            // 回答问题
//            zsxqApi.answerQuestion(String.valueOf(topicId), text, cookie);
        }
    }

    @Test
    public void test_gpt() throws IOException {
        String response = openAI.doChatGPT(openAiKey, "帮我写一个java冒泡排序");
        logger.info("测试结果：{}", response);

    }
}
