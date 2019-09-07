package xdubbo.core.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: allanyang
 * @Date: 2019/9/5 18:04
 * @Description:
 */
public class ApplicationThreadFactory implements ThreadFactory {

    /**
     * 工厂标志
     */
    private String flag;

    /**
     * 该工厂产生线程个数
     */
    private final AtomicInteger count = new AtomicInteger(0);

    public ApplicationThreadFactory(String flag) {
        this.flag = flag;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "pool-" + flag + "-" + count.getAndIncrement());
    }
}