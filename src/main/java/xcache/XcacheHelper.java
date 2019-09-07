package xcache;

import xcache.conf.XcacheConf;
import xcache.util.JsonUtil;
import xcache.util.StringUtil;

/**
 * @Auther: allanyang
 * @Date: 2019/8/9 17:07
 * @Description:
 */
public class XcacheHelper {


    public static Object[] parseParam(XcacheConf conf, String cacheInstanceKey) {
        StringBuilder sb = new StringBuilder();
        sb.append(conf.getSerializeType()).append(StringUtil.COLON);
        sb.append(conf.getPrefix()).append(StringUtil.COLON);
        cacheInstanceKey.replace(sb.toString(), StringUtil.EMPTY);
        return JsonUtil.parse(cacheInstanceKey.substring(0, cacheInstanceKey.indexOf(StringUtil.COLON)), new Object[1].getClass());
    }
}