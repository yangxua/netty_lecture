package xdubbo.model;

import lombok.Data;

import java.util.Map;

/**
 * @Auther: allanyang
 * @Date: 2019/9/5 17:35
 * @Description:
 */
@Data
public class MessageContainer {

    private Map<String,Object> beanName2Service;
}