package cn.cmy.chatbot.api.domain.zsxq.model.req;

import lombok.Data;

import java.util.List;

/**
 * @author cmy
 * @Description 知识星球评论请求数据
 * @date 2024/3/17 17:03
 * @Version 1.0
 */
@Data
public class CommentReqData {
    /**
     * 评论内容
     */
    private String text;
    /**
     * 图片id
     */
    private List<String> imageIds;
    /**
     * &#064;用户id
     */
    private List<String> mentionedUserIds;
}
