package xcache.conf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import xcache.SerializeType;
import xcache.XcacheType;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author Joeson Chan<xueguichen@lexin.com>
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class XcacheConf {

    private String prefix;

    private String version;

    private int secodes;

    private int alarmSecods;

    private long lockTimeOutMills;

    private XcacheType cacheType;

    private SerializeType serializeType;

    private Type returnType;

    private Method method;

    private boolean cacheNull;

    private String preCacheKey;

    private int maxSize;



}
