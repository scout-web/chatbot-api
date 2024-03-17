package cn.cmy.chatbot.api.domain.zsxq.model.res;

import cn.cmy.chatbot.api.domain.zsxq.model.vo.Topics;
import lombok.Data;

import java.util.List;

/**
 * @author cmy
 * @Description 知识星球topics返回数据
 * @date 2024/3/17 16:46
 * @Version 1.0
 */
@Data
public class TalkRespData {
    /**
     * 知识星球话题列表
     */
    private List<Topics> topics;
}
