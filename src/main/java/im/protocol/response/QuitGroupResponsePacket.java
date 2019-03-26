package im.protocol.response;

import im.protocol.Packet;
import im.protocol.command.Command;
import lombok.Data;

/**
 * @Auther: allanyang
 * @Date: 2019/3/26 16:21
 * @Description:
 */
@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    @Override
    protected Byte getCommand() {
        return Command.QUIT_GROUP_RESPONSE;
    }
}