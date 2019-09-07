package xdubbo.core.client.main;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Auther: allanyang
 * @Date: 2019/9/6 17:09
 * @Description:
 */
public class RpcServiceProxy<T> implements InvocationHandler {

    private Class<T> clazz;

    public RpcServiceProxy(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {



        return null;
    }
}