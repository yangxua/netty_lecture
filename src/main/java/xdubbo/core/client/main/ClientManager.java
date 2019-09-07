package xdubbo.core.client.main;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import xcache.util.StringUtil;
import xdubbo.core.client.initialize.ClientRpcInitializer;
import xdubbo.model.MessageRequest;
import xdubbo.utils.ThreadPoolUtil;

import java.util.concurrent.ExecutorService;

/**
 * @Auther: allanyang
 * @Date: 2019/9/6 15:01
 * @Description:
 */
@Slf4j
public class ClientManager {

    private static volatile ClientManager instance;
    private EventLoopGroup bossGroup;
    private Bootstrap bootstrap;
    private static ExecutorService clientRpcThreadPool = (ExecutorService) ThreadPoolUtil.getExecutor("client-rpc");
    private static final String DEFAULT_IP = "127.0.0.1";
    private static final int DEFAULT_PORT = 9876;
    private static final int MAX_RETRY = 3;
    private static final int MAX_PORT = 65536;
    private static final int MIN_PORT = 0;

    private ClientManager(){}
    public static ClientManager getInstance() {
        if (instance == null) {
            synchronized (ClientManager.class) {
                if (instance == null) {
                    instance = new ClientManager();
                }
            }
        }

        return instance;
    }

    private Bootstrap bootstrap() {
        return bootstrap;
    }

    public void init() {
        initClientRpcLoader();
    }

    private void initClientRpcLoader() {
        bossGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();

        bootstrap().group(bossGroup).channel(NioSocketChannel.class).handler(new ClientRpcInitializer());

        //connect(b, DEFAULT_IP, DEFAULT_PORT, MAX_RETRY);
    }


    private void invoke(String ip, int port, MessageRequest request) {
        if (StringUtil.isBlank(ip)) {
            throw new IllegalArgumentException("ip can't be null");
        }
        if (port > MAX_PORT || port <= MIN_PORT) {
            throw new IllegalArgumentException("port is invalid");
        }

        clientRpcThreadPool.submit(new ClientMessageSendTask(request, bootstrap, ip, port));
    }
}