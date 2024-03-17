package cn.cmy.chatbot.api.domain.ai.model.vo;

import lombok.Data;

/**
 * @author cmy
 * @Description 选择，也就是chatGPT的回答，一般只有一个，索引是0
 * @date 2024/3/17 18:14
 * @Version 1.0
 */
@Data
public class Choices {
    /**
     * 选择索引
     */
    private int index;

    /**
     * 选择内容
     */
    private Message message;

    /**
     * 终止原因
     */
    private String finishReason;
}
