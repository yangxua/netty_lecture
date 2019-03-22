package im.attributes;

import im.session.Session;
import io.netty.util.AttributeKey;

/**
 * @Auther: allanyang
 * @Date: 2019/3/22 15:37
 * @Description:
 */
public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}