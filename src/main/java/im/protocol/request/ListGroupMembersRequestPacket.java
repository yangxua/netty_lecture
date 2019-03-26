package im.protocol.request;

import im.protocol.Packet;
import im.protocol.command.Command;
import lombok.Data;

/**
 * @Auther: allanyang
 * @Date: 2019/3/26 16:34
 * @Description:
 */
@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    protected Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }
}