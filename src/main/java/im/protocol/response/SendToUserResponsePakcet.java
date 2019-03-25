package im.protocol.response;

import im.protocol.Packet;
import im.protocol.command.Command;
import lombok.Data;

/**
 * @Auther: allanyang
 * @Date: 2019/3/25 12:00
 * @Description:
 */
@Data
public class SendToUserResponsePakcet extends Packet {

    private String fromUserName;
    private String fromUserId;
    private String msg;


    @Override
    protected Byte getCommand() {
        return Command.SEND_TO_USER_RESPONSE;
    }
}