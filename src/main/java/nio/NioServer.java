package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @Auther: allanyang
 * @Date: 2019/2/11 15:20
 * @Description:
 */
public class NioServer {
    private static Map<String,SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket socket = serverSocketChannel.socket();
        socket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            selectionKeys.forEach(selectionKey -> {
                final SocketChannel client;
                try {
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                        client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);
                        String key = "["+ UUID.randomUUID().toString()+"]";
                        clientMap.put(key,client);
                    } else if (selectionKey.isReadable()) {
                        client = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(512);

                        int count = client.read(buffer);
                        if (count > 0) {
                            buffer.flip();
                            Charset charset = Charset.forName("utf-8");
                            String receiveMessage = String.valueOf(charset.decode(buffer).array());
                            System.out.println(client + ":" + receiveMessage);
                            String sendKey = null;

                            for (Map.Entry<String, SocketChannel> entry: clientMap.entrySet()) {
                                if (entry.getValue() == client){
                                    sendKey = entry.getKey();
                                    break;
                                }
                            }

                            for (Map.Entry<String, SocketChannel> entry: clientMap.entrySet()) {
                                SocketChannel value = entry.getValue();

                                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                byteBuffer.put((sendKey + ":" + receiveMessage).getBytes());
                                byteBuffer.flip();

                                value.write(buffer);
                            }
                        }
                    }
                    selectionKeys.clear();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}