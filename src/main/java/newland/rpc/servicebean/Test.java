package newland.rpc.servicebean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther: allanyang
 * @Date: 2019/2/22 17:01
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("rpc-invoke-config.xml");
    }
}