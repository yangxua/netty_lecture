package newland.rpc.core.thread;

import java.util.concurrent.*;

/**
 * @Auther: allanyang
 * @Date: 2019/2/21 17:32
 * @Description:
 */
public class RpcThreadPool extends ThreadPoolExecutor {

    public RpcThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, rejectedExecutionHandler);
    }


    public static Executor getExecutor(int thread, int queues) {
        String name = "RpcThreadPool";
        return new RpcThreadPool(thread, thread, 0, TimeUnit.MILLISECONDS, queues == 0 ? new SynchronousQueue<Runnable>() : (queues < 0 ? new LinkedBlockingQueue<Runnable>() : new LinkedBlockingQueue<Runnable>(queues)), new NameThreadFactory(name, true), new AbortPolicyWithReport(name));
    }
}