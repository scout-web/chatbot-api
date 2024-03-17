package cn.cmy.chatbot.api.domain.zsxq.model.vo;

import lombok.Data;

/**
 * @author cmy
 * @Description TODO
 * @date 2024/3/17 16:35
 * @Version 1.0
 */
@Data
public class Talk {
    /**
     * 所属用户
     */
    private Owner owner;
    /**
     * 话题内容
     */
    private String text;
}
