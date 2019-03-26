package im.protocol;

import im.protocol.command.Command;
import im.protocol.request.*;
import im.protocol.response.*;
import im.serializer.JSONSerializer;
import im.serializer.Serializer;
import im.serializer.SerializerAlgorithm;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: allanyang
 * @Date: 2019/3/22 14:44
 * @Description:
 */
public class PacketCodec {

    public static final int MAGIC = 0x12345678;
    public static final PacketCodec INSTANCE = new PacketCodec();

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte,Serializer> serializeTypeMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(Command.SEND_TO_USER_REQUEST, SendToUserRequestPacket.class);
        packetTypeMap.put(Command.SEND_TO_USER_RESPONSE, SendToUserResponsePakcet.class);
        packetTypeMap.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        packetTypeMap.put(Command.CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        packetTypeMap.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        packetTypeMap.put(Command.JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        packetTypeMap.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        packetTypeMap.put(Command.QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
        packetTypeMap.put(Command.LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestPacket.class);
        packetTypeMap.put(Command.LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponsePacket.class);
        packetTypeMap.put(Command.SEND_TO_GROUP_REQUEST, SendToGroupRequestPacket.class);
        packetTypeMap.put(Command.SEND_TO_GROUP_RESPONSE, SendToGroupResponsePacket.class);


        serializeTypeMap = new HashMap<>();
        serializeTypeMap.put(SerializerAlgorithm.JSON, new JSONSerializer());
    }

    /**
     * 序列化
     * @param buf
     * @param packet
     */
    public void encode(ByteBuf buf, Packet packet) {
        //序列化
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        buf.writeInt(MAGIC);
        buf.writeByte(packet.getVersion());
        buf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        buf.writeByte(packet.getCommand());
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
    }

    /**
     * 反序列化
     * @param buf
     */
    public Packet decode(ByteBuf buf) {

        //跳过magic
        buf.skipBytes(4);
        //跳过版本号
        buf.skipBytes(1);

        //获取算法
        byte algorithm = buf.readByte();
        //获取command
        byte command = buf.readByte();
        //获取长度
        int len = buf.readInt();
        byte[] bytes = new byte[len];

        buf.readBytes(bytes);

        Class<? extends Packet> packet = getPacket(command);
        Serializer serialize = getAlgorithm(algorithm);

        if (packet != null && serialize != null) {
            return  serialize.deserialize(packet, bytes);
        }

        return null;
    }

    private Serializer getAlgorithm(byte algorithm) {
        return serializeTypeMap.get(algorithm);
    }

    private Class<? extends Packet> getPacket(byte command) {
        return packetTypeMap.get(command);
    }




}