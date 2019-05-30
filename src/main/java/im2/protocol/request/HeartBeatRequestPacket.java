package im2.protocol.request;

import im2.protocol.Packet;
import im2.protocol.command.Command;

/**
 * @Auther: allanyang
 * @Date: 2019/5/30 15:34
 * @Description:
 */
public class HeartBeatRequestPacket extends Packet {

    @Override
    public byte getCommand() {
        return Command.HEART_BEAT_REQUEST;
    }
}