package cn.cmy.chatbot.api.domain.zsxq.model.vo;

import lombok.Data;

/**
 * @author cmy
 * @Description TODO
 * @date 2024/3/17 16:36
 * @Version 1.0
 */
@Data
public class UserSpecific {
    /**
     * 用户是否点击喜欢
     */
    private boolean liked;
    /**
     * 用户是否订阅
     */
    private boolean subscribed;
}
