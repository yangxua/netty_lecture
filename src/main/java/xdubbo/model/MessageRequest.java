package xdubbo.model;

import lombok.Data;
import lombok.ToString;

/**
 * @Auther: allanyang
 * @Date: 2019/9/5 17:25
 * @Description:
 */
@Data
@ToString
public class MessageRequest {

    /**
     * traceId
     */
    private String traceId;

    /**
     * 接口名
     */
    private String interfaceName;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数类型
     */
    private Class<?>[] paramTypes;

    /**
     * 参数名
     */
    private String[] paramNames;
}