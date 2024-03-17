package cn.cmy.chatbot.api.domain.zsxq;

import cn.cmy.chatbot.api.domain.zsxq.model.aggregates.CommentAggredates;
import cn.cmy.chatbot.api.domain.zsxq.model.aggregates.TalkAggregates;

import java.io.IOException;

/**
 * @author cmy
 * @Description api
 * @date 2024/3/17 16:29
 * @Version 1.0
 */
public interface IZsxqApi {

    /**
     * 查询talk问题
     * @param groupId 星球组id
     * @param cookie 用户cookie
     * @return
     */
    TalkAggregates queryQuestions(String groupId, String cookie) throws IOException;


    /**
     * 回答问题
     * @param topicId
     * @param text
     * @return
     */
    CommentAggredates answerQuestion(String topicId, String text, String cookie) throws IOException;
}
