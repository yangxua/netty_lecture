package im2.protocol.request;

import im2.protocol.Packet;
import im2.protocol.command.Command;
import im2.serializer.SerializerAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Auther: allanyang
 * @Date: 2019/5/28 21:00
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JoinGroupRequestPacket extends Packet {

    /**
     * 要加入的组id
     */
    private String groupId;

    @Override
    protected byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }

    @Override
    protected byte getSerializer() {
        return SerializerAlgorithm.JSON;
    }
}