package usung.com.mqttclient.bean.db;

import usung.com.mqttclient.bean.HttpRequestParameterBase;

import java.io.Serializable;

/**
 *  查询主题接口，所需要的主题
 * @author herui
 * @date 2018/12/28
 */

public class QueryTopicParameter extends HttpRequestParameterBase implements Serializable {
    /// <summary>
    /// 要查询的对象 ，用户Id或者qunid
    /// </summary>
    public String TargetId ;
    /// <summary>
    /// 凭据
    /// </summary>
    public OpereatCredencial Credencial;

    public String getTargetId() {
        return TargetId;
    }

    public void setTargetId(String targetId) {
        TargetId = targetId;
    }

    public OpereatCredencial getCredencial() {
        return Credencial;
    }

    public void setCredencial(OpereatCredencial credencial) {
        Credencial = credencial;
    }
}
