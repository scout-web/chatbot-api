package cn.cmy.chatbot.api.domain.zsxq.model.vo;

import lombok.Data;
import java.util.Date;
/**
 * @author cmy
 * @Description TODO
 * @date 2024/3/17 16:34
 * @Version 1.0
 */
@Data
public class Comment {
    /**
     * 评论id
     */
    private long commentId;
    /**
     * 评论创建时间
     */
    private Date createTime;
    /**
     * 评论创建者
     */
    private Owner owner;
    /**
     * 评论内容
     */
    private String text;
    /**
     * 喜欢数
     */
    private int likesCount;
    /**
     * 打赏数
     */
    private int rewardsCount;
    /**
     * 是否置顶
     */
    private boolean sticky;

}
