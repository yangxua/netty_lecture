package im2.protocol.request;

import im2.protocol.Packet;
import im2.protocol.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Auther: allanyang
 * @Date: 2019/5/28 21:28
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuitGroupRequestPacket extends Packet {

    /**
     * 组id
     */
    private String groupId;

    @Override
    public byte getCommand() {
        return Command.QUIT_GROUP_REQUEST;
    }
}