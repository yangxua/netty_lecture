package quick.netty.pkg.utils;

import java.util.UUID;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 19:51
 * @Description:
 */
public class IdUtils {

    public static String random() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}