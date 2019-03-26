package im.protocol.response;

import im.protocol.Packet;
import im.protocol.command.Command;
import lombok.Data;

/**
 * @Auther: allanyang
 * @Date: 2019/3/26 18:14
 * @Description:
 */
@Data
public class SendToGroupResponsePacket extends Packet {

    private String fromGroupId;
    private String fromUserName;
    private String msg;

    @Override
    protected Byte getCommand() {
        return Command.SEND_TO_GROUP_RESPONSE;
    }
}