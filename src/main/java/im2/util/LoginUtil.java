package im2.util;

import im2.attributes.Attributes;
import io.netty.channel.Channel;

/**
 * @Auther: allanyang
 * @Date: 2019/5/19 10:32
 * @Description:
 */
public class LoginUtil {

    public static void mark(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean isLogin(Channel channel) {
        return channel.attr(Attributes.LOGIN).get() != null;
    }


}