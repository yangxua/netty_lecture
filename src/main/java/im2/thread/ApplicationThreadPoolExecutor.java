package im2.thread;

import java.util.concurrent.*;

/**
 * @Auther: allanyang
 * @Date: 2019/5/19 11:32
 * @Description:
 */
public class ApplicationThreadPoolExecutor extends ThreadPoolExecutor {

    public ApplicationThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadPoolExecutor, AbortPolicyWithReport abortPolicyWithReport) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadPoolExecutor, abortPolicyWithReport);
    }

    public static Executor getExecutor(String name, int thread, int queues) {
        return new ApplicationThreadPoolExecutor(thread, thread, 0, TimeUnit.MILLISECONDS, queues == 0 ? new SynchronousQueue<>() : (queues < 0 ? new LinkedBlockingQueue<>() : new LinkedBlockingQueue<>(queues)), new ApplicationThreadFactory(name), new AbortPolicyWithReport(name));
    }

    public static Executor getExecutor(int thread, int queues) {
        return getExecutor("ApplicationThreadPool", thread, queues);
    }

    public static Executor getExecutor(int thread) {
        return getExecutor(thread, -1);
    }

    public static Executor getExecutor() {
        return getExecutor(Runtime.getRuntime().availableProcessors() * 2);
    }
}