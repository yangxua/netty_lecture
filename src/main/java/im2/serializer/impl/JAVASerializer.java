package im2.serializer.impl;

import im2.serializer.Serializer;

/**
 * @Auther: allanyang
 * @Date: 2019/5/17 18:25
 * @Description:
 */
public class JAVASerializer implements Serializer {

    @Override
    public <T> byte[] serialize(T obj) {

        //@todo
        return new byte[0];
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        //@todo
        return null;
    }
}