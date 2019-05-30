package im2.protocol.request;

import im2.protocol.Packet;
import im2.protocol.command.Command;

/**
 * @Auther: allanyang
 * @Date: 2019/5/28 09:52
 * @Description:
 */
public class LogoutRequestPacket extends Packet {

    @Override
    public byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }

}