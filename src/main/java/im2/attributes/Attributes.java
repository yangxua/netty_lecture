package im2.attributes;

import im2.session.Session;
import io.netty.util.AttributeKey;

/**
 * @Auther: allanyang
 * @Date: 2019/5/19 10:29
 * @Description:
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}