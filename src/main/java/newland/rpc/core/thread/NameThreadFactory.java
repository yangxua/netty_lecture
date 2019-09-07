package newland.rpc.core.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: allanyang
 * @Date: 2019/2/21 17:10
 * @Description:
 */
public class NameThreadFactory implements ThreadFactory {

    private static final AtomicInteger threadFactoryNum = new AtomicInteger(1);
    private final AtomicInteger threadNum = new AtomicInteger(1);
    private final String prefix;
    private final boolean daemoThread;
    private final ThreadGroup threadGroup;

    public NameThreadFactory(String prefix) {
        this(prefix,false);
    }

    public NameThreadFactory() {
        this("rpcserver-threadpool-" + threadFactoryNum.getAndIncrement());
    }

    public NameThreadFactory(String prefix,boolean deamo) {
        this.prefix = prefix + "-a-";
        this.daemoThread = deamo;
        SecurityManager s = System.getSecurityManager();
        threadGroup = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
    }

    public ThreadGroup getThreadGroup() {
        return this.threadGroup;
    }

    @Override
    public Thread newThread(Runnable r) {
        String name = prefix + threadNum.getAndIncrement();
        Thread t = new Thread(threadGroup, r, name ,0);
        t.setDaemon(daemoThread);
        return t;
    }
}