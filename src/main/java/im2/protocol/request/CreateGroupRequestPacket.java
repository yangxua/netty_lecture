package im2.protocol.request;

import im2.protocol.Packet;
import im2.protocol.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @Auther: allanyang
 * @Date: 2019/5/27 21:37
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateGroupRequestPacket extends Packet {

    /**
     * 加入组的用户列表
     */
    private List<String> userIdList;

    @Override
    public byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }

}