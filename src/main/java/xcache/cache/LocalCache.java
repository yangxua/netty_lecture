package xcache.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: allanyang
 * @Date: 2019/8/9 16:33
 * @Description:
 */
public class LocalCache<String, XcacheInstance> {

    private static final int DEFAULT_MAX_SIZE = 100;
    private LoadingCache<String, XcacheInstance> loadingCache;

    public LocalCache(int maxSize, int alarmTime, int expireTime, CacheLoader<String, XcacheInstance> cacheLoader) {
        loadingCache = CacheBuilder.newBuilder().maximumSize(maxSize == 0 ? DEFAULT_MAX_SIZE : maxSize).expireAfterAccess(expireTime, TimeUnit.SECONDS).refreshAfterWrite(alarmTime, TimeUnit.SECONDS).softValues().build(cacheLoader);
    }

    public void put(String key, XcacheInstance cache) {
        loadingCache.put(key, cache);
    }

    public XcacheInstance get(String key) {
        return loadingCache.getIfPresent(key);
    }

    public void clear(String key) {
        loadingCache.invalidate(key);
    }
}