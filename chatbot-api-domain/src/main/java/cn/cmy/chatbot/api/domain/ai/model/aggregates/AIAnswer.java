package cn.cmy.chatbot.api.domain.ai.model.aggregates;

import cn.cmy.chatbot.api.domain.ai.model.vo.Choices;
import cn.cmy.chatbot.api.domain.ai.model.vo.Usage;
import lombok.Data;

import java.util.List;

/**
 * @author cmy
 * @Description chatGPT api
 * @date 2024/3/17 18:12
 * @Version 1.0
 */
@Data
public class AIAnswer {
    private String id;

    private String object;

    private int created;

    /**
     * 使用的模型
     */
    private String model;

    /**
     * 回复
     */
    private List<Choices> choices;
    /**
     * 令牌消耗详情
     */
    private Usage usage;

}
