package quick.netty.pkg.session;

import io.netty.channel.Channel;
import quick.netty.pkg.Attributes;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtils {

    private static final Map<String, Channel> sessionMap = new ConcurrentHashMap();

    public static void bindSession(Session session, Channel channel) {
        sessionMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            sessionMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static Boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {
        return sessionMap.get(userId);
    }

}
