package cn.cmy.chatbot.api.domain.ai;

import java.io.IOException;

/**
 * @author cmy
 * @Description ChatGPT
 * @date 2024/3/17 18:10
 * @Version 1.0
 */
public interface IOpenAI {
    String doChatGPT(String openAiKey, String question) throws IOException;
}
