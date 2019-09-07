package xdubbo.model;

import lombok.Data;
import lombok.ToString;

/**
 * @Auther: allanyang
 * @Date: 2019/9/5 17:32
 * @Description:
 */
@Data
@ToString
public class MessageResponse {

    /**
     * 请求id
     */
    private String traceId;

    /**
     * 错误信息
     */
    private String error;

    /**
     * 结果
     */
    private Object resultDesc;
}