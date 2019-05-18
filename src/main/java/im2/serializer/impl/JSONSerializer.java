package im2.serializer.impl;

import com.alibaba.fastjson.JSON;
import im2.serializer.Serializer;

/**
 * @Auther: allanyang
 * @Date: 2019/5/17 18:22
 * @Description:
 */
public class JSONSerializer implements Serializer {

    @Override
    public <T> byte[] serialize(T obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}