package cn.cmy.chatbot.api.domain.ai.model.vo;

import lombok.Data;

/**
 * @author cmy
 * @Description 消息
 * @date 2024/3/17 18:13
 * @Version 1.0
 */
@Data
public class Message {
    /**
     * 消息角色
     */
    private String role;

    /**
     * 消息内容
     */
    private String content;
}
