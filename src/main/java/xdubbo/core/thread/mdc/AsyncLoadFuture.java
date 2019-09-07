package xdubbo.core.thread.mdc;

import lombok.Data;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Auther: allanyang
 * @Date: 2019/9/6 10:17
 * @Description:
 *
 * 继承J.U.C下的FutureTask, 主要的变化点：
 *  <p>
 *  <pre>
 *  1. 持有提交task的thread引用，用于threadLocal处理.新的pool处理线程可以继承/共享callerThread线程的threadLocal信息
 *  </pre>
 */
@Data
public class AsyncLoadFuture<V> extends FutureTask<V> {

    /**
     * 父线程
     */
    private Thread callerThread;

    /**
     * 子线程
     */
    private Thread runnerThread;

    /**
     * 记录下future开始执行的时间
     */
    private long startTime = 0;

    /**
     * 记录下future结束执行的时间
     */
    private long endTime = 0;

    /**
     * 是否需要threadlocal支持
     */
    private boolean needThreadLocalSupport;

    public AsyncLoadFuture(Callable<V> callable, boolean needThreadLocalSupport) {
        super(callable);
        this.callerThread = Thread.currentThread();
        this.needThreadLocalSupport = needThreadLocalSupport;
    }

    public AsyncLoadFuture(Runnable runnable, boolean needThreadLocalSupport) {
        super(runnable, null);
        this.callerThread = Thread.currentThread();
        this.needThreadLocalSupport = needThreadLocalSupport;
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();

        //当前运行线程，也就是子线程，可能是线程池中的那个父线程自己
        this.runnerThread = Thread.currentThread();

        super.run();
    }

    @Override
    public void done() {
        // 记录一下时间点，Future在cancel调用，正常完成，或者运行出异常都会回调该方法
        endTime = System.currentTimeMillis();
    }

}