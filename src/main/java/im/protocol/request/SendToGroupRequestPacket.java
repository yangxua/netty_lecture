package im.protocol.request;

import im.protocol.Packet;
import im.protocol.command.Command;
import lombok.Data;

/**
 * @Auther: allanyang
 * @Date: 2019/3/26 17:56
 * @Description:
 */
@Data
public class SendToGroupRequestPacket extends Packet {

    private String groupId;
    private String message;

    @Override
    protected Byte getCommand() {
        return Command.SEND_TO_GROUP_REQUEST;
    }
}