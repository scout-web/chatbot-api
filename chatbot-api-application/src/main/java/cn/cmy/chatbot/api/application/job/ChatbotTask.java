package cn.cmy.chatbot.api.application.job;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.cmy.chatbot.api.domain.ai.IOpenAI;
import cn.cmy.chatbot.api.domain.zsxq.IZsxqApi;
import cn.cmy.chatbot.api.domain.zsxq.model.aggregates.CommentAggredates;
import cn.cmy.chatbot.api.domain.zsxq.model.aggregates.TalkAggregates;
import cn.cmy.chatbot.api.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
/**
 * @author cmy
 * @Description 任务体
 * @date 2024/3/17 19:10
 * @Version 1.0
 */
public class ChatbotTask implements Runnable{
    private Logger logger = LoggerFactory.getLogger(ChatbotTask.class);

    private String groupName;
    private String groupId;
    private String cookie;
    private String openAiKey;
    private boolean silenced;

    private IZsxqApi zsxqApi;
    private IOpenAI openAI;

    public ChatbotTask(String groupName, String groupId, String cookie, String openAiKey, IZsxqApi zsxqApi, IOpenAI openAI, boolean silenced) {
        this.groupName = groupName;
        this.groupId = groupId;
        this.cookie = cookie;
        this.openAiKey = openAiKey;
        this.zsxqApi = zsxqApi;
        this.openAI = openAI;
        this.silenced = silenced;
    }

    @Override
    public void run() {
        try {
            if (new Random().nextBoolean()) {
                logger.info("{} 随机打烊中...", groupName);
                return;
            }

            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour > 22 || hour < 7) {
                logger.info("{} 打烊时间不工作，AI 下班了！", groupName);
                return;
            }

            // 1. 检索问题
            TalkAggregates talkAggregates = zsxqApi.queryQuestions(groupId, cookie);
//            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = talkAggregates;
            logger.info("{} 检索结果：{}", groupName, JSON.toJSONString(talkAggregates));
            List<Topics> topics = talkAggregates.getRespData().getTopics();
            if (null == topics || topics.isEmpty()) {
                logger.info("{} 本次检索未查询到待会答问题", groupName);
                return;
            }

            // 2. AI 回答
            Topics topic = topics.get(topics.size() - 1);
            String answer = openAI.doChatGPT(openAiKey, topic.getTalk().getText().trim());
            // 3. 问题回复
            CommentAggredates commentAggredates = zsxqApi.answerQuestion(groupId, answer, cookie);
//            logger.info("{} 编号：{} 问题：{} 回答：{} 状态：{}", groupName, topic.getTopic_id(), topic.getQuestion().getText(), answer, status);
        } catch (Exception e) {
            logger.error("{} 自动回答问题异常", groupName, e);
        }
    }

}
