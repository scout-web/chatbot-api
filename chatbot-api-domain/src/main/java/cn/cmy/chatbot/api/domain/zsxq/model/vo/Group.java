package cn.cmy.chatbot.api.domain.zsxq.model.vo;

import lombok.Data;

/**
 * @author cmy
 * @Description TODO
 * @date 2024/3/17 16:33
 * @Version 1.0
 */
@Data
public class Group {
    /**
     * 群组id
     */
    private long groupId;
    /**
     * 群组名称
     */
    private String name;
    /**
     * 群组类型
     */
    private String type;
    /**
     *
     */
    private String backgroundUrl;

}
