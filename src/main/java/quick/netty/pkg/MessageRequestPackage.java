package quick.netty.pkg;

import lombok.Data;

@Data
public class MessageRequestPackage extends Package{

    private String toUserId;

    private String message;

    public MessageRequestPackage() {
    }

    public MessageRequestPackage(String toUserId) {

        this.toUserId = toUserId;
    }

    public MessageRequestPackage(String toUserId, String message) {

        this.toUserId = toUserId;
        this.message = message;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
