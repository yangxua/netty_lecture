package im.protocol.request;

import im.protocol.Packet;
import im.protocol.command.Command;
import lombok.Data;

import java.util.List;

/**
 * @Auther: allanyang
 * @Date: 2019/3/25 21:40
 * @Description:
 */
@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIds;

    @Override
    protected Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}