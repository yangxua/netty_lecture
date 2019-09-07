package xcache.conf;


import com.fenqile.work.common.utils.ApplicationUtil;
import com.fenqile.work.common.utils.CollectionUtil;
import com.fenqile.work.common.utils.StringUtil;
import com.fenqile.work.common.xcache.XcacheProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;


/**
 * @author Joeson Chan<xueguichen@lexin.com>
 */
@Slf4j
@Configuration
@PropertySource("classpath:server.properties")
public class XcacheConfig {
    /**
     * ConfigurableEnvironment
     */
    @Autowired
    private ConfigurableEnvironment env;

    @Bean
    public XcacheProperty toCacheConfg() {
        XcacheProperty property = new XcacheProperty();
        if (null == env.getProperty(XcacheProperty.XCACHE_SWITCH_PROPERTY) || !env.getProperty(XcacheProperty.XCACHE_SWITCH_PROPERTY, Boolean.class)) {
            return property;
        }

        try {
            property.setXcacheSwitch(CollectionUtil.notContain(property.getExcludeEnvList(), ApplicationUtil.getApplicationMode().name()));
            if (StringUtil.isNotEmpty(env.getProperty(XcacheProperty.XCACHE_REDIS_KEY_PROPERTY))) {
                property.setXcacheRedisKey(env.getProperty(XcacheProperty.XCACHE_REDIS_KEY_PROPERTY));
            } else if (StringUtil.isNotEmpty(env.getProperty(XcacheProperty.DEFAULT_REDIS_KEY))) {
                property.setXcacheRedisKey(env.getProperty(XcacheProperty.DEFAULT_REDIS_KEY));
            } else {
                throw new IllegalArgumentException(XcacheProperty.XCACHE_REDIS_KEY_PROPERTY + " of server.properties can not be empty");
            }
            if (null != env.getProperty(XcacheProperty.XCACHE_TIME_PROPERTY)) {
                property.setCacheSecodes(Integer.valueOf(env.getProperty(XcacheProperty.XCACHE_TIME_PROPERTY, Integer.class)));
                if (property.getCacheSecodes() <= 0) {
                    throw new IllegalArgumentException(XcacheProperty.XCACHE_TIME_PROPERTY + " of server.properties must bigger than 0");
                }
            }
            if (null != env.getProperty(XcacheProperty.XCACHE_ALARM_TIME_PROPERTY)) {
                property.setCacheAlarmSecodes(env.getProperty(XcacheProperty.XCACHE_ALARM_TIME_PROPERTY, Integer.class));
                if (property.getCacheAlarmSecodes() <= 0) {
                    throw new IllegalArgumentException(XcacheProperty.XCACHE_ALARM_TIME_PROPERTY + " of server.properties must bigger than 0");
                }
            }
            if (null != env.getProperty(XcacheProperty.XCACHE_LOCK_DEFAULT_TIMEOUT_PROPERTY)) {
                property.setLockTimeoutMillisSeconds(env.getProperty(XcacheProperty.XCACHE_LOCK_DEFAULT_TIMEOUT_PROPERTY, Integer.class));
                if (property.getLockTimeoutMillisSeconds() <= 0) {
                    throw new IllegalArgumentException(XcacheProperty.XCACHE_LOCK_DEFAULT_TIMEOUT_PROPERTY + " of server.properties must bigger than 0");
                }
            }
            if (null != env.getProperty(XcacheProperty.XCACHE_LOCK_EXCLUDE_ENV)) {
                property.setExcludeEnvList(StringUtil.split2List(env.getProperty(XcacheProperty.XCACHE_LOCK_EXCLUDE_ENV), StringUtil.COMMA));
            }
        } catch (Exception e) {
            log.warn("xcache config error", e);
        }

        return property;
    }
}
