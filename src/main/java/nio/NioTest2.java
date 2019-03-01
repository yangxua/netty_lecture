package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: allanyang
 * @Date: 2019/2/10 15:14
 * @Description:
 */
public class NioTest2 {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("out.txt");

        FileChannel channel1 = fileInputStream.getChannel();
        FileChannel channel2 = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while (true) {
            buffer.clear();

            int read = channel1.read(buffer);

            if (read == -1) {
                break;
            }

            buffer.flip();

            channel2.write(buffer);
        }

        fileInputStream.close();
        fileOutputStream.close();
    }
}