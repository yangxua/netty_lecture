package xdubbo.core.thread;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Auther: allanyang
 * @Date: 2019/9/5 18:10
 * @Description:
 */
public class ApplicationPolicy extends ThreadPoolExecutor.AbortPolicy {

    private String threadName;

    public ApplicationPolicy(String threadName) {
        this.threadName = threadName;
    }

    /**
     * Always throws RejectedExecutionException.
     *
     * @param r the runnable task requested to be executed
     * @param e the executor attempting to execute this task
     * @throws RejectedExecutionException always
     */
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        String msg = String.format("RpcServer["
                        + " Thread Name: %s, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d),"
                        + " Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s)]",
                threadName, e.getPoolSize(), e.getActiveCount(), e.getCorePoolSize(), e.getMaximumPoolSize(), e.getLargestPoolSize(),
                e.getTaskCount(), e.getCompletedTaskCount(), e.isShutdown(), e.isTerminated(), e.isTerminating());
        throw new RejectedExecutionException(msg);
    }
}