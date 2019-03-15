package quick.netty.pkg;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

public class MessageResponsePackage extends Package{

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
