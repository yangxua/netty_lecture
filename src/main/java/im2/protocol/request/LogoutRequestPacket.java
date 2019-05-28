package im2.protocol.request;

import im2.protocol.Packet;
import im2.protocol.command.Command;
import im2.serializer.SerializerAlgorithm;

/**
 * @Auther: allanyang
 * @Date: 2019/5/28 09:52
 * @Description:
 */
public class LogoutRequestPacket extends Packet {

    @Override
    protected byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }

    @Override
    protected byte getSerializer() {
        return SerializerAlgorithm.JSON;
    }
}