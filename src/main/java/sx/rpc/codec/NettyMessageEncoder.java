package sx.rpc.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import sx.rpc.struct.NettyMessage;

import java.util.Map;

/**
 * @Auther: allanyang
 * @Date: 2019/7/9 21:44
 * @Description:
 */
public class NettyMessageEncoder extends MessageToByteEncoder<NettyMessage> {

    private MarshallingEncoder marshallingEncoder;

    /**
     * 目的是把msg转成字节码放入bytebuf中
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, ByteBuf sendBuf) throws Exception {
        if (msg == null || msg.getHeader() == null) {
            throw new Exception("The encode message is null");
        }

        sendBuf.writeInt(msg.getHeader().getCrcCode());
        sendBuf.writeInt(msg.getHeader().getLength());
        sendBuf.writeLong(msg.getHeader().getSessionID());
        sendBuf.writeByte(msg.getHeader().getType());
        sendBuf.writeByte(msg.getHeader().getPriority());
        sendBuf.writeByte(msg.getHeader().getAttachment().size());

        String key;
        byte[] keyArray;
        Object value;
        for (Map.Entry<String, Object> param : msg.getHeader().getAttachment().entrySet()) {
            key = param.getKey();
            keyArray = key.getBytes("UTF-8");
            sendBuf.writeInt(keyArray.length);
            sendBuf.writeBytes(keyArray);
            value = param.getValue();
            marshallingEncoder.encode(value, sendBuf);
        }

        key = null;
        keyArray = null;
        value = null;
        if (msg.getBody() != null) {
            marshallingEncoder.encode(msg.getBody(), sendBuf);
        } else {
            sendBuf.writeInt(0);
        }

        sendBuf.setInt(4, sendBuf.readableBytes() - 8);
    }
}