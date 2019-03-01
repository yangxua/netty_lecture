package newland.rpc.model;

import java.util.Map;

/**
 * @Auther: allanyang
 * @Date: 2019/2/21 17:07
 * @Description:
 */
public class MessageKeyValue {

    private Map<String, Object> messageKeyVal;

    public void setMessageKeyVal(Map<String,Object> messageKeyVal) {
        this.messageKeyVal = messageKeyVal;
    }

    public Map<String, Object> getMessageKeyVal() {
        return messageKeyVal;
    }
}