package im.protocol.response;

import im.protocol.Packet;
import im.protocol.command.Command;
import im.session.Session;
import lombok.Data;

import java.util.List;

/**
 * @Auther: allanyang
 * @Date: 2019/3/26 16:45
 * @Description:
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;
    private List<Session> sessions;

    @Override
    protected Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}