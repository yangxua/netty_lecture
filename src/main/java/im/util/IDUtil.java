package im.util;

import java.util.UUID;

/**
 * @Auther: allanyang
 * @Date: 2019/3/22 16:39
 * @Description:
 */
public class IDUtil {

    public static String random() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}