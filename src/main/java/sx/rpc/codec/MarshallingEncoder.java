package sx.rpc.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import org.jboss.marshalling.Marshaller;

import java.io.IOException;

/**
 * @Auther: allanyang
 * @Date: 2019/7/9 21:47
 * @Description:
 */
@ChannelHandler.Sharable
public class MarshallingEncoder {

    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];
    Marshaller marshaller;

    public MarshallingEncoder() throws IOException {
        this.marshaller = MarshallingCodecFactory.buildMarshalling();
    }

    /**
     * 把msg写入sendBuf中
     * @param msg
     * @param sendBuf
     */
    public void encode(Object msg, ByteBuf sendBuf) throws IOException {
        try {
            int lengthPos = sendBuf.writerIndex();
            sendBuf.writeBytes(LENGTH_PLACEHOLDER);
            ChannelBufferByteOutput output = new ChannelBufferByteOutput(sendBuf);
            marshaller.start(output);
            marshaller.writeObject(msg);
            marshaller.finish();
            sendBuf.setInt(lengthPos, sendBuf.writerIndex() - lengthPos - 4);
        } finally {
            marshaller.close();
        }
    }
}