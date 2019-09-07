package xcache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import xcache.conf.XcacheConf;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Joeson Chan<xueguichen@lexin.com>
 */
@Data
@ToString
@AllArgsConstructor
public class XcacheInstance<T> implements Serializable {

    /**
     * 缓存实例
     */
    private T t;

    /**
     * 缓存版本
     */
    private String version;

    /**
     * 多机器并行控制，启动开始的版本号为1
     */
    private long mmcc;

    /**
     * 缓存对象sign，如果sign不一直，会认为对象已经改动，丢弃缓存，并重新reload
     */
    private String modelSign;

    /**
     * 缓存时间戳
     */
    private long timeMillis;

    /**
     * 缓存预警时间戳
     */
    private long alarmTimeMillis;

    /**
     * 前缀
     */
    private String prefix;

    /**
     * 缓存key
     */
    private String key;

    public XcacheInstance() {
    }


    public XcacheInstance(T t, XcacheModelConf cacheModelConf, XcacheConf cacheConf, String xcacheInstanceKey) {
        Date now = new Date();
        this.t = t;
        this.version = cacheConf.getVersion();
        this.modelSign = cacheModelConf.getSign();
        this.timeMillis = now.getTime() + cacheConf.getSecodes() * 1000;
        this.alarmTimeMillis = now.getTime() + cacheConf.getAlarmSecods() * 1000;
        this.prefix = cacheConf.getPrefix();
        this.key = xcacheInstanceKey;
    }

}

