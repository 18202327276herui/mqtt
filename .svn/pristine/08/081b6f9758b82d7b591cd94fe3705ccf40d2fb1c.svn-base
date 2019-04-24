package usung.com.mqttclient.bean.user;

import java.io.Serializable;

import usung.com.mqttclient.bean.HttpResposeDataBase;

/**
 * 登录返回结果
 *
 * @author herui
 * @date 2018/12/13
 */

public class LoginResultData extends HttpResposeDataBase implements Serializable {
    /// <summary>
    /// 登录结果
    /// </summary>
    public int Result;
    /// <summary>
    /// <summary>
    /// 登录结果字符串说明
    /// </summary>
    public String ResultMessage;
    /// Token
    /// </summary>
    public String Token;
    /// <summary>
    /// mqtt 登录参数
    /// </summary>
    public MqttLoginCredential MqttCredential;
    /// <summary>
    /// oss 登录参数
    /// </summary>
    public OssLoginCredential OssCredential;
    /// <summary>
    /// db 地址 ip+端口
    /// </summary>
    public String DBHost;
    /// <summary>
    /// topic 地址 ip+端口
    /// </summary>
    public String TopicHost;
    /// <summary>
    /// group storage 地址 ip+端口
    /// </summary>
    public String GroupStorageHost;

    public int getResult() {
        return Result;
    }

    public void setResult(int result) {
        Result = result;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public MqttLoginCredential getMqttCredencial() {
        return MqttCredential;
    }

    public void setMqttCredencial(MqttLoginCredential mqttCredencial) {
        MqttCredential = mqttCredencial;
    }

    public OssLoginCredential getOssCredencial() {
        return OssCredential;
    }

    public void setOssCredencial(OssLoginCredential ossCredencial) {
        OssCredential = ossCredencial;
    }

    public String getDBHost() {
        return DBHost;
    }

    public void setDBHost(String DBHost) {
        this.DBHost = DBHost;
    }

    public String getTopicHost() {
        return TopicHost;
    }

    public void setTopicHost(String topicHost) {
        TopicHost = topicHost;
    }

    public String getGroupStorageHost() {
        return GroupStorageHost;
    }

    public void setGroupStorageHost(String groupStorageHost) {
        GroupStorageHost = groupStorageHost;
    }

    public String getResultMessage() {
        return ResultMessage;
    }

    public void setResultMessage(String resultMessage) {
        ResultMessage = resultMessage;
    }
}
