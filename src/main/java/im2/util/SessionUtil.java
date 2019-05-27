package im2.util;

import im2.attributes.Attributes;
import im2.session.Session;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: allanyang
 * @Date: 2019/5/27 19:48
 * @Description:
 */
public final class SessionUtil {

    private static final Map<String, Channel> userId2Channel = new ConcurrentHashMap<>();

    private SessionUtil(){}

    public static void bindSession(Session session, Channel channel) {
        userId2Channel.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            userId2Channel.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {
        return userId2Channel.get(userId);
    }
}