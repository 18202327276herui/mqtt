package usung.com.mqttclient.bean;

import java.io.Serializable;

/**
 *
 * @author herui
 * @date 2018/12/14
 */

public class HttpRequestParameterBase implements Serializable {
    public String Token;
    public String SelfId ;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getSelfId() {
        return SelfId;
    }

    public void setSelfId(String selfId) {
        SelfId = selfId;
    }
}
