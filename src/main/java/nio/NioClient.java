package nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: allanyang
 * @Date: 2019/2/11 18:21
 * @Description:
 */
public class NioClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeys) {
                if (selectionKey.isConnectable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();

                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        buffer.put((channel + ":连接成功").getBytes());
                        buffer.flip();
                        channel.write(buffer);

                        ExecutorService thread = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                        thread.submit(() ->{
                            while (true) {
                                buffer.clear();
                                InputStreamReader inputStreamReader = new InputStreamReader(System.in);
                                BufferedReader bufr = new BufferedReader(inputStreamReader);

                                String str = bufr.readLine();

                                buffer.put(str.getBytes());
                                buffer.flip();
                                channel.write(buffer);
                            }
                        });
                    }
                }
            }
        }
    }
}