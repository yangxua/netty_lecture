package im2.protocol.response;

import im2.protocol.Packet;
import im2.protocol.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Auther: allanyang
 * @Date: 2019/5/28 21:21
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JoinGroupResponsePacket extends Packet {

    /**
     * 组id
     */
    private String groupId;

    /**
     * 是否成功
     */
    private boolean isSuccess;

    @Override
    protected byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }
}