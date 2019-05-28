package im2.protocol.response;

import im2.protocol.Packet;
import im2.protocol.command.Command;
import im2.session.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @Auther: allanyang
 * @Date: 2019/5/28 21:47
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ListGroupMembersResponsePacket extends Packet {

    /**
     * 组内session列表
     */
    private List<Session> list;

    /**
     * 组id
     */
    private String groupId;

    @Override
    protected byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}