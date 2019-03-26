package im.protocol.request;

import im.protocol.Packet;
import im.protocol.command.Command;
import lombok.Data;

/**
 * @Auther: allanyang
 * @Date: 2019/3/26 09:49
 * @Description:
 */
@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    protected Byte getCommand() {
        return Command.QUIT_GROUP_REQUEST;
    }
}