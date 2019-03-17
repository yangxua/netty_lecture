package quick.netty.pkg;

import io.netty.util.AttributeKey;
import quick.netty.pkg.session.Session;

public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}
