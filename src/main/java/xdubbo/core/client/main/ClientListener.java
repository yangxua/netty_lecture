package xdubbo.core.client.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Auther: allanyang
 * @Date: 2019/9/6 14:58
 * @Description:
 */
@Component
@Slf4j
public class ClientListener implements ApplicationListener<ContextRefreshedEvent> {

    private transient ApplicationContext ctx;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.ctx = event.getApplicationContext();

        if (ctx.getParent() == null) {
            try {
                ClientManager.getInstance().init();
            } catch (Exception e) {
                log.error("客户端初始化失败，错误信息：{}", e.getMessage(), e);
                throw new Error(e);
            }
        }
    }
}