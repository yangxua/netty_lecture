package im.protocol.response;

import im.protocol.Packet;
import im.protocol.command.Command;
import lombok.Data;

import java.util.List;

/**
 * @Auther: allanyang
 * @Date: 2019/3/25 21:41
 * @Description:
 */
@Data
public class CreateGroupResponsePacket extends Packet {

    private String groupId;
    private List<String> userNames;

    @Override
    protected Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}