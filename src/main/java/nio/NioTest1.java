package nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @Auther: allanyang
 * @Date: 2019/2/1 15:35
 * @Description:
 */
public class NioTest1 {

    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);

        for (int i = 0;i < buffer.capacity(); i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }

        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}