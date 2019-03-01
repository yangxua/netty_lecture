package thirdexample;

import io.netty.util.internal.SystemPropertyUtil;

/**
 * @Auther: allanyang
 * @Date: 2019/2/13 13:55
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        int res = Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", Runtime.getRuntime().availableProcessors() * 2));
        System.out.println(res);
    }
}