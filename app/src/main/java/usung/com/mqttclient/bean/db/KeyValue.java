package usung.com.mqttclient.bean.db;

import java.io.Serializable;

/**
 * Created by herui on 2019/1/10.
 */

public class KeyValue implements Serializable {
    /**
     *  Key
     */
    private String Key;
    /**
     * Value
     */
    private String Value;

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
