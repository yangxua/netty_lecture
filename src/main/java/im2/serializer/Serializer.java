package im2.serializer;

/**
 * @Auther: allanyang
 * @Date: 2019/5/17 18:15
 * @Description:
 */
public interface Serializer {

    /**
     * 序列化
     */
    <T> byte[] serialize(T obj);

    /**
     * 反序列化
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz);
}
