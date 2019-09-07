package xcache.cache;

import com.google.common.cache.CacheLoader;
import com.google.common.collect.Maps;
import xcache.XcacheHelper;
import xcache.XcacheInstance;
import xcache.XcacheModelConf;
import xcache.conf.XcacheConf;
import xcache.util.SpringHelpUtil;

import java.util.Map;

/**
 * @Auther: allanyang
 * @Date: 2019/8/9 16:52
 * @Description:
 */
public class LocalCacheContext {

    private static final Map<XcacheConf, LocalCache<String, XcacheInstance>> conf2Cache = Maps.newConcurrentMap();

    public static LocalCache<String, XcacheInstance> getLocalCache(XcacheConf conf, XcacheModelConf modelConf) {
        if (!conf2Cache.containsKey(conf)) {
            synchronized (LocalCacheContext.class) {
                if (!conf2Cache.containsKey(conf)) {
                    conf2Cache.put(conf, new LocalCache<>(conf.getMaxSize(), conf.getAlarmSecods(), conf.getSecodes(), new CacheLoader<String, XcacheInstance>() {
                        @Override
                        public XcacheInstance load(String key) throws Exception {
                            Object[] args = XcacheHelper.parseParam(conf, key);
                            Object obj = conf.getMethod().invoke(SpringHelpUtil.getBean(conf.getMethod().getDeclaringClass()), args);
                            return new XcacheInstance(obj, modelConf, conf, key);
                        }
                    }));
                }
            }
        }

        return conf2Cache.get(conf);
    }
}