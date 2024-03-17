package cn.cmy.chatbot.api.domain.zsxq.model.vo;

import lombok.Data;
import java.util.Date;
/**
 * @author cmy
 * @Description TODO
 * @date 2024/3/17 16:35
 * @Version 1.0
 */
@Data
public class Topics {
    /**
     * 话题id
     */
    private long topicId;
    /**
     * 所属群组
     */
    private Group group;
    /**
     * 类型：talk
     */
    private String type;
    /**
     * 话题内容
     */
    private Talk talk;
    /**
     * 喜欢数
     */
    private int likesCount;
    /**
     * 打赏数
     */
    private int rewardsCount;
    /**
     * 评论数
     */
    private int commentsCount;
    /**
     * 阅读数
     */
    private int readingCount;
    /**
     * 阅读者数
     */
    private int readersCount;
    /**
     * 是否精华
     */
    private boolean digested;
    /**
     * 是否置顶
     */
    private boolean sticky;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 用户表态，是否订阅、是否喜欢
     */
    private UserSpecific userSpecific;


}
