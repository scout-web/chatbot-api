package cn.cmy.chatbot.api.domain.zsxq.model.vo;

import lombok.Data;

/**
 * @author cmy
 * @Description TODO
 * @date 2024/3/17 16:34
 * @Version 1.0
 */
@Data
public class Owner {
    /**
     * 用户id
     */
    private long userId;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户头像
     */
    private String avatarUrl;
    /**
     * 用户所在地（省份）
     */
    private String location;
}
