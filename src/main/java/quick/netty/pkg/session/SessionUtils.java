package quick.netty.pkg.session;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import quick.netty.pkg.Attributes;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtils {

    private static final Map<String, Channel> sessionMap = new ConcurrentHashMap();

    private static final Map<String, ChannelGroup> channelGroupMap = new ConcurrentHashMap();

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

    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup) {
        channelGroupMap.put(groupId, channelGroup);
    }

    public static ChannelGroup getChannelGroup(String groupId) {
        return channelGroupMap.get(groupId);
    }
}
