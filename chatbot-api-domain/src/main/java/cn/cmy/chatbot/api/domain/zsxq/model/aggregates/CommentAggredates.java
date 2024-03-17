package cn.cmy.chatbot.api.domain.zsxq.model.aggregates;

import cn.cmy.chatbot.api.domain.zsxq.model.res.CommentRespData;
import lombok.Data;

/**
 * @author cmy
 * @Description 知识星球评论聚合数据
 * @date 2024/3/17 16:47
 * @Version 1.0
 */
@Data
public class CommentAggredates {
    /**
     * 是否成功
     */
    private boolean succeeded;
    /**
     * 返回数据
     */
    private CommentRespData respData;
}
