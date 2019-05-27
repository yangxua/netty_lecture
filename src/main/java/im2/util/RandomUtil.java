package im2.util;

import java.util.UUID;

/**
 * @Auther: allanyang
 * @Date: 2019/5/27 20:10
 * @Description:
 */
public final class RandomUtil {

    private RandomUtil(){}

    public static String random() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}