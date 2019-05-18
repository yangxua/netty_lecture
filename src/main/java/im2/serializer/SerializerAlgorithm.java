package im2.serializer;

/**
 * @Auther: allanyang
 * @Date: 2019/5/17 18:21
 * @Description:
 */
public interface SerializerAlgorithm {

    /**
     * JSON序列化算法
     */
    byte JSON = 1;

    /**
     * JAVA序列化算法
     */
    byte JAVA = 2;
}