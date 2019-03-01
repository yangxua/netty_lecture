package newland.rpc.core.client.main;

import java.lang.reflect.Proxy;

/**
 * @Auther: allanyang
 * @Date: 2019/2/22 11:29
 * @Description:
 */
public class MessageSendExecutor {

    private RpcServerLoader loader = RpcServerLoader.getInstance();

    public MessageSendExecutor(String serverAddress) {
        loader.load(serverAddress);
    }

    public void stop() {
        loader.unload();
    }

    public static <T> T execute(Class<T> rpcInterface) {
        return (T)Proxy.newProxyInstance(rpcInterface.getClassLoader(), new Class<?>[]{rpcInterface},new MessageSendProxy<T>(rpcInterface));
    }
}