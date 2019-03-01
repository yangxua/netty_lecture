package newland.rpc.model;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: allanyang
 * @Date: 2019/2/22 16:27
 * @Description:
 */
public class MessageCallBack {

    private MessageRequest request = null;
    private MessageResponse response = null;
    private Lock lock = new ReentrantLock();
    private Condition finish = lock.newCondition();

    public MessageCallBack(MessageRequest request) {
        this.request = request;
    }

    public Object start() throws InterruptedException {
        try {
            lock.lock();
            finish.await(10 * 1000, TimeUnit.MILLISECONDS);
            if (response != null) {
                return response.getResult();
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    public void over(MessageResponse response) {
        try {
            lock.lock();
            this.response = response;
            finish.signalAll();
        } finally {
            lock.unlock();
        }
    }
}