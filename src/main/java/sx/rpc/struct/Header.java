package sx.rpc.struct;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: allanyang
 * @Date: 2019/7/9 21:35
 * @Description:
 */
@Data
public final class Header {

    private int crcCode = 0xabef0101;

    private int length;

    private long sessionID;

    private byte type;

    private byte priority;

    private Map<String, Object> attachment = new HashMap<>();
}