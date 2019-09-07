package xcache;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Joeson Chan<xueguichen@lexin.com>
 */
public class XcacheModelConf {

    private Class clazz;

    private List<Field> fields;

    private String sign;

    public XcacheModelConf() {
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return this.clazz;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


}
