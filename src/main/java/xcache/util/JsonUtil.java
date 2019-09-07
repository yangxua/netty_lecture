package xcache.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import org.apache.commons.lang.ArrayUtils;

import java.lang.reflect.Type;
import java.util.List;

/**
 * FastJson 工具类
 *
 * @author Joeson Chan<xueguichen@lexin.com>
 */
public final class JsonUtil {

    public static String getString(JSONObject json, String key) {
        return getString(json, key, null);
    }

    public static String getString(JSONObject json, String key, String defaultValue) {
        if (null == json || !json.containsKey(key)) {
            return defaultValue;
        }

        return json.getString(key);
    }

    public static Integer getInteger(JSONObject json, String key) {
        return getInteger(json, key, null);
    }

    public static Integer getInteger(JSONObject json, String key, Integer defaultValue) {
        if (null == json || !json.containsKey(key)) {
            return defaultValue;
        }

        return json.getInteger(key);
    }

    public static Long getLong(JSONObject json, String key) {
        return getLong(json, key, null);
    }

    public static Long getLong(JSONObject json, String key, Long defaultValue) {
        if (null == json || !json.containsKey(key)) {
            return defaultValue;
        }

        return json.getLong(key);
    }

    public static JSONObject getJSONObject(JSONObject json, String key) {
        return getJSONObject(json, key, null);
    }

    public static JSONObject getJSONObject(JSONObject json, String key, JSONObject defaultValue) {
        if (null == json || !json.containsKey(key)) {
            return defaultValue;
        }

        return json.getJSONObject(key);
    }

    public static JSONArray getJSONArray(JSONObject json, String key) {
        return getJSONArray(json, key, null);
    }

    public static JSONArray getJSONArray(JSONObject json, String key, JSONArray defaultValue) {
        if (null == json || !json.containsKey(key)) {
            return defaultValue;
        }

        return json.getJSONArray(key);
    }

    public static <T> Object toJson(T t) {
        return JSON.toJSON(t);
    }

    public static <T> T parse(byte[] bytes, Class<T> clazz) {
        if (ArrayUtils.isEmpty(bytes) || null == clazz) {
            return null;
        }

        return JSONObject.parseObject(bytes, clazz);
    }

    public static <T> T parse(String text, Class<T> clazz) {
        if (StringUtil.isEmpty(text) || null == clazz) {
            return null;
        }

        return JSONObject.parseObject(text, clazz);
    }

    public static <T> T parse(String text, Type type, Feature... features) {
        if (StringUtil.isEmpty(text) || null == type) {
            return null;
        }

        return JSON.parseObject(text, type, features);
    }

    public static <T> T parse(String text, TypeReference<T> type, Feature... features) {
        if (StringUtil.isEmpty(text) || null == type) {
            return null;
        }

        return JSON.parseObject(text, type, features);
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        if (StringUtil.isEmpty(text) || null == clazz) {
            return CollectionUtil.emptyList();
        }

        return JSONArray.parseArray(text, clazz);
    }

    public static <T> T parse(byte[] bytes, Type type, Feature... features) {
        if (ArrayUtils.isEmpty(bytes) || null == type) {
            return null;
        }

        return JSON.parseObject(bytes, type, features);
    }

    public static <T> T parse(JSONObject json, Class<T> clazz) {
        if (null == json || null == clazz) {
            return null;
        }

        return JSONObject.toJavaObject(json, clazz);
    }

    public static <T> List<T> parseArray(JSONArray array, Class<T> clazz) {
        if (null == array || null == clazz) {
            return null;
        }

        return parseArray(JsonUtil.toString(array), clazz);
    }

    public static <T> String toString(JSON json) {
        return null != json ? json.toJSONString() : StringUtil.EMPTY;
    }

    public static String toString(Object obj) {
        return null != obj ? JSON.toJSONString(obj) : StringUtil.EMPTY;
    }

    /**
     * 驼峰key 转 下划线
     */
    public static JSON humpKey2Underline(JSON json) {
        toHumpOrUnderLine(json, false);
        return json;
    }

    /**
     * 下滑线key 转 驼峰
     */
    public static JSON underlineKey2Hump(JSON json) {
        toHumpOrUnderLine(json, true);
        return json;
    }

    private static void toHumpOrUnderLine(Object obj, boolean toHump) {
        if (null == obj) {
            return;
        }

        if (obj instanceof JSONArray) {
            JSONArray array = ((JSONArray) obj);
            for (int i = 0; i < array.size(); i++) {
                toHumpOrUnderLine(array.get(i), toHump);
            }
        } else if (obj instanceof JSONObject) {
            JSONObject json = (JSONObject) obj;

            String[] keys = json.keySet().toArray(new String[0]);
            for (String key : keys) {
                String newKey = toHump ? StringUtil.underLineToHump(key) : StringUtil.humpToUnderLine(key);
                Object value = json.get(key);
                json.put(newKey, value);
                if (!key.equals(newKey)) {
                    json.remove(key);
                }
                toHumpOrUnderLine(value, toHump);
            }
        }
    }
}
