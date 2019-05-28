package im2.protocol.response;

import im2.protocol.Packet;
import im2.protocol.command.Command;
import im2.serializer.SerializerAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: allanyang
 * @Date: 2019/5/28 09:55
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogoutResponsePacket extends Packet {

    /**
     * 是否成功
     */
    private boolean isSuccess;

    @Override
    protected byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }

    @Override
    protected byte getSerializer() {
        return SerializerAlgorithm.JSON;
    }
}