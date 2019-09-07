package xdubbo.core.client.main;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: allanyang
 * @Date: 2019/9/6 17:04
 * @Description:
 */
public class RpcServiceManager {

    private static final Map<Class<?>, Object> clazz2Service = new ConcurrentHashMap<>();
    private static final Map<String, Object> beanName2Service = new ConcurrentHashMap<>();

    public void putService(Class<?> clazz, Object service) {
        clazz2Service.put(clazz, service);
    }

    public <T> T getService(Class<T> clazz) {
        T service =  (T) clazz2Service.getOrDefault(clazz, null);
        if (service != null) {
            return service;
        }

        service = (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, new RpcServiceProxy(clazz));
        clazz2Service.putIfAbsent(clazz, service);
        return service;
    }

    public void putService(String beanName, Object service) {
        beanName2Service.put(beanName, service);
    }

    public Object getService(String beanName) {
        return beanName2Service.get(beanName);
    }
}