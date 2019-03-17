package quick.netty.pkg;

import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: allanyang
 * @Date: 2019/3/13 17:42
 * @Description:
 */
public class PacketCodeC {

    public static final int MAGIC = 0x12345678;
    private static final PacketCodeC INSTANCE = new PacketCodeC();
    private PacketCodeC(){}

    public static PacketCodeC getInstance() {
        return INSTANCE;
    }

    private static final Map<Byte, Class<? extends Package>> packageTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packageTypeMap = new HashMap<>();
        packageTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPackage.class);
        packageTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePackage.class);
        packageTypeMap.put(Command.MESSAGE_REQUEST, MessageRequestPackage.class);
        packageTypeMap.put(Command.MESSAGE_RESPONSE, MessageResponsePackage.class);


        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public ByteBuf encode(ByteBuf buf, Package pkg) {

        //序列化pkg
        byte[] serialize = Serializer.DEFAULT.serialize(pkg);

        buf.writeInt(MAGIC);
        buf.writeByte(pkg.getVersion());
        buf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        buf.writeByte(pkg.getCommand());
        buf.writeInt(serialize.length);
        buf.writeBytes(serialize);

        return buf;
    }

    public Package decode(ByteBuf buf) {
        //跳过魔术
        buf.skipBytes(4);

        //跳过版本号
        buf.skipBytes(1);

        byte serializeAlgorithm  = buf.readByte();

        byte command = buf.readByte();

        int len = buf.readInt();

        byte[] bytes = new byte[len];
        buf.readBytes(bytes);

        Class<? extends Package> requestType = getRequestType(command);
        
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType,bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Package> getRequestType(byte command) {
        return packageTypeMap.get(command);
    }
}