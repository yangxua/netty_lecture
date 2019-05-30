package im2.protocol.response;

import im2.protocol.Packet;
import im2.protocol.command.Command;

/**
 * @Auther: allanyang
 * @Date: 2019/5/30 15:39
 * @Description:
 */
public class HeartBeatResponsePacket extends Packet {

    @Override
    public byte getCommand() {
        return Command.HEART_BEAT_RESPONSE;
    }
}