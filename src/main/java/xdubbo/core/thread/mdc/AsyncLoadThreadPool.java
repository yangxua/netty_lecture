package xdubbo.core.thread.mdc;

import org.springframework.scheduling.annotation.Async;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.concurrent.*;

/**
 * @Auther: allanyang
 * @Date: 2019/9/6 10:49
 * @Description:
 *
 *
 * 扩展了J.U.C的ThreadPoolExecutor，主要扩展点说明：
 *  <p>
 *  <pre>
 *  1. 覆写newTaskFor函数，返回自定义的{@linkplain AsyncLoadFuture}
 *  2. 增强了Pool池中的Worker线程，会自动复制caller Thread的threadLocal信息，几点考虑：
 *    a. Worker线程为pool的内部管理对象，在操作ThreadLocal信息时安全性上不存在问题，持有的引用在task完成后也可以正常释放。ThreadLocal引用在Worker线程中的生命周期<=Caller Thread线程
 *    b. 做为并行异步加载，一个主要的设计思想就是对业务尽可能的透明，尽可能的减少使用陷井，所以这里通过非正常手段实现了ThreadLocal的支持，实属无奈
 *  </pre>
 *  </p>
 */
public class AsyncLoadThreadPool extends ThreadPoolExecutor {

    private static final Field threadLoaclField = ReflectionUtils.findField(Thread.class, "threadLocals");
    private static final Field inheritableThreadLoaclField = ReflectionUtils.findField(Thread.class, "inheritableThreadLocals");

    static {
        // 强制的声明accessible
        ReflectionUtils.makeAccessible(threadLoaclField);
        ReflectionUtils.makeAccessible(inheritableThreadLoaclField);
    }

    // 继承自ThreadPoolExecutor的构造函数
    public AsyncLoadThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public AsyncLoadThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public AsyncLoadThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    public AsyncLoadThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    @Override
    public <V> AsyncLoadFuture<V> submit (Callable<V> task) {
        if (task == null) {
            throw new IllegalArgumentException("task can't be null");
        }

        AsyncLoadFuture ftask = new AsyncLoadFuture<>(task, true);
        execute(ftask);
        return ftask;
    }

    @Override
    public  AsyncLoadFuture submit (Runnable task) {
        if (task == null) {
            throw new IllegalArgumentException("task can't be null");
        }

        AsyncLoadFuture ftask = new AsyncLoadFuture(task, true);
        execute(ftask);
        return ftask;
    }

    public <V> AsyncLoadFuture<V> submit (Callable<V> task, boolean needThreadLocalSuppport) {
        if (task == null) {
            throw new IllegalArgumentException("task can't be null");
        }

        AsyncLoadFuture ftask = new AsyncLoadFuture<>(task, needThreadLocalSuppport);
        execute(ftask);
        return ftask;
    }

    public <V> AsyncLoadFuture<V> submit (Runnable task, boolean needThreadLocalSuppport) {
        if (task == null) {
            throw new IllegalArgumentException("task can't be null");
        }

        AsyncLoadFuture ftask = new AsyncLoadFuture<V>(task, needThreadLocalSuppport);
        execute(ftask);
        return ftask;
    }

    // ====================== 扩展点 ==========================

    @Override
    public void execute(Runnable command) {
        if (command instanceof AsyncLoadFuture) {
            AsyncLoadFuture afuture = (AsyncLoadFuture) command;
            boolean flag = afuture.isNeedThreadLocalSupport();

            if (flag) {
                Thread runnerThread = Thread.currentThread();//此处是子线程
                if (ReflectionUtils.getField(threadLoaclField, runnerThread) == null) {
                    // 创建一个空的ThreadLocal,立马写回去
                    // 这时会在runner线程产生一空记录的ThreadLocalMap记录
                    new ThreadLocal<>();
                }

                if (ReflectionUtils.getField(inheritableThreadLoaclField, runnerThread) == null) {
                    //同理，创建一个可继承的threadlocal
                    new InheritableThreadLocal<>();
                }
            }
        }

        super.execute(command);
    }

    /**
     * Method invoked prior to executing the given Runnable in the
     * given thread.  This method is invoked by thread {@code t} that
     * will execute task {@code r}, and may be used to re-initialize
     * ThreadLocals, or to perform logging.
     *
     * <p>This implementation does nothing, but may be customized in
     * subclasses. Note: To properly nest multiple overridings, subclasses
     * should generally invoke {@code super.beforeExecute} at the end of
     * this method.
     *
     * @param t the thread that will run task {@code r}
     * @param command the task that will be executed
     */
    @Override
    public void beforeExecute(Thread runner, Runnable command) {
        if (command instanceof AsyncLoadFuture) {
            AsyncLoadFuture afuture = (AsyncLoadFuture) command;
            boolean flag = afuture.isNeedThreadLocalSupport();

            if (flag) {
                initThreadLocal(threadLoaclField, afuture.getCallerThread(), runner);
                initThreadLocal(inheritableThreadLoaclField, afuture.getCallerThread(), runner);
            }
        }

        super.beforeExecute(runner, command);
    }

    /**
     * Method invoked upon completion of execution of the given Runnable.
     * This method is invoked by the thread that executed the task. If
     * non-null, the Throwable is the uncaught {@code RuntimeException}
     * or {@code Error} that caused execution to terminate abruptly.
     *
     * <p>This implementation does nothing, but may be customized in
     * subclasses. Note: To properly nest multiple overridings, subclasses
     * should generally invoke {@code super.afterExecute} at the
     * beginning of this method.
     *
     * <p><b>Note:</b> When actions are enclosed in tasks (such as
     * {@link FutureTask}) either explicitly or via methods such as
     * {@code submit}, these task objects catch and maintain
     * computational exceptions, and so they do not cause abrupt
     * termination, and the internal exceptions are <em>not</em>
     * passed to this method. If you would like to trap both kinds of
     * failures in this method, you can further probe for such cases,
     * as in this sample subclass that prints either the direct cause
     * or the underlying exception if a task has been aborted:
     *
     *  <pre> {@code
     * class ExtendedExecutor extends ThreadPoolExecutor {
     *   // ...
     *   protected void afterExecute(Runnable r, Throwable t) {
     *     super.afterExecute(r, t);
     *     if (t == null && r instanceof Future<?>) {
     *       try {
     *         Object result = ((Future<?>) r).get();
     *       } catch (CancellationException ce) {
     *           t = ce;
     *       } catch (ExecutionException ee) {
     *           t = ee.getCause();
     *       } catch (InterruptedException ie) {
     *           Thread.currentThread().interrupt(); // ignore/reset
     *       }
     *     }
     *     if (t != null)
     *       System.out.println(t);
     *   }
     * }}</pre>
     *
     * @param command the runnable that has completed
     * @param t the exception that caused termination, or null if
     * execution completed normally
     */
    @Override
    public void afterExecute(Runnable command, Throwable t) {
        if (command instanceof AsyncLoadFuture) {
            AsyncLoadFuture afuture = (AsyncLoadFuture) command;
            boolean flag = afuture.isNeedThreadLocalSupport();
            if (flag) {
                recoverThreadLocal(threadLoaclField, afuture.getCallerThread(), afuture.getRunnerThread());
                recoverThreadLocal(inheritableThreadLoaclField, afuture.getCallerThread(), afuture.getRunnerThread());
            }
        }

        super.afterExecute(command, t);
    }

    /**
     * 初始化子线程的threadlocal
     */
    private void initThreadLocal(Field field, Thread caller, Thread runner) {
        if (caller == null || runner == null) {
            return ;
        }

        // 主要考虑这样的情况：
        // 1.
        // 如果caller线程没有使用ThreadLocal对象，而异步加载的runner线程执行中使用了ThreadLocal对象，则需要复制对象到caller线程上
        // 2.
        // 后续caller,多个runner线程有使用ThreadLocal对象，使用的是同一个引用,直接set都是针对同一个ThreadLocal,所以以后就不需要进行合并

        // 因为在提交Runnable时已经同步创建了一个ThreadLocalMap对象，所以runner线程只需要复制caller对应的引用即可，不需要进行合并，简化处理
        // threadlocal属性复制,注意是引用复制
        Object callerThreadLocalMap = ReflectionUtils.getField(field, caller);
        if (null != callerThreadLocalMap) {
            //把caller的threadlocalmap写入到runner中
            ReflectionUtils.setField(field, runner, callerThreadLocalMap);
        }
    }

    /**
     * 清除子线程的threadlocal
     */
    private void recoverThreadLocal(Field field, Thread caller, Thread runner) {
        if (null == runner) {
            return ;
        }

        ReflectionUtils.setField(field, runner, null);
    }

}