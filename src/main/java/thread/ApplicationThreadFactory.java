package thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: allanyang
 * @Date: 2019/2/26 19:28
 * @Description:
 */
public class ApplicationThreadFactory implements ThreadFactory {

    private AtomicInteger count = new AtomicInteger(1);

    private String flag;

    public ApplicationThreadFactory(String flag) {
        this.flag = flag;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "pool-" + flag + "-" + count.getAndIncrement());
    }
}