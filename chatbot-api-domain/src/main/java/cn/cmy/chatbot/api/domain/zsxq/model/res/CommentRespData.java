package cn.cmy.chatbot.api.domain.zsxq.model.res;

import cn.cmy.chatbot.api.domain.zsxq.model.vo.Comment;
import lombok.Data;

/**
 * @author cmy
 * @Description 评论返回数据
 * @date 2024/3/17 16:45
 * @Version 1.0
 */
@Data
public class CommentRespData {
    /**
     * 评论信息
     */
    private Comment comment;
}
