package xdubbo.utils;

import xdubbo.core.thread.ApplicationPolicy;
import xdubbo.core.thread.ApplicationThreadFactory;
import xdubbo.core.thread.ApplicationThreadPool;

import java.util.concurrent.*;

/**
 * @Auther: allanyang
 * @Date: 2019/9/5 18:22
 * @Description:
 */
public final class ThreadPoolUtil {

    private ThreadPoolUtil(){throw new Error("can't init it!");}

    public static Executor getExecutor(String name, int thread, int queues) {
        return new ApplicationThreadPool(thread, thread, 0, TimeUnit.MILLISECONDS, queues == 0 ? new SynchronousQueue<>() : (queues < 0 ? new LinkedBlockingQueue<>() : new LinkedBlockingQueue<>(queues)), new ApplicationThreadFactory(name), new ApplicationPolicy(name));
    }

    public static Executor getExecutor(String name, int queues) {
        return getExecutor(name, Runtime.getRuntime().availableProcessors() * 2,queues);
    }

    public static Executor getExecutor(String name) {
        return getExecutor(name, 0);
    }

}