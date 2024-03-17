package cn.cmy.chatbot.api.domain.ai.model.vo;

/**
 * @author cmy
 * @Description TODO
 * @date 2024/3/17 18:13
 * @Version 1.0
 */
public class Usage {
    /**
     * 提式-询问消耗的令牌数
     */
    private int promptTokens;

    /**
     * 回答消耗的令牌数
     */
    private int completionTokens;

    /**
     * 总消耗的令牌数
     */
    private int totalTokens;
}
