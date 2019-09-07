package xdubbo.model;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: allanyang
 * @Date: 2019/9/5 17:42
 * @Description:
 */
@Data
@ToString
@Slf4j
public class MessageCallBack {

    public static final int DEFAULT_TIME_OUT_LIMIT = 3 * 1000;

    private MessageRequest request;
    private MessageResponse response;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public MessageCallBack(MessageRequest request) {
        this.request = request;
    }

    public Object start() {
        try {
            lock.lock();
            condition.await(DEFAULT_TIME_OUT_LIMIT, TimeUnit.MILLISECONDS);
            return response == null ? null : response.getResultDesc();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            return response == null ? null : response.getResultDesc();
        } finally {
            lock.unlock();
        }
    }

    public void over(MessageResponse response) {
        try {
            lock.lock();
            this.response = response;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}