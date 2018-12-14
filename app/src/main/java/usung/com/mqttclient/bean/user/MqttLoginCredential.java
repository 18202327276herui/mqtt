package usung.com.mqttclient.bean.user;

import java.io.Serializable;

/**
 * @author herui
 * @date 2018/12/13
 */

public class MqttLoginCredential implements Serializable {
    /// <summary>
    /// mqtt 连接的客户端id
    /// </summary>
    public String ClientId;
    /// <summary>
    /// mqtt 连接的账户名
    /// </summary>
    public String UserId;
    /// <summary>
    /// mqtt 登陆密码
    /// </summary>
    public String Password;
    /// <summary>
    /// mqtt 服务器地址 ip+端口
    /// </summary>
    public String Host;
    /// <summary>
    /// mqtt ssl 端口
    /// </summary>
    public int SslPort;
    /// <summary>
    /// ssl 证书
    /// </summary>
    public byte[] Certification;

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getHost() {
        return Host;
    }

    public void setHost(String host) {
        Host = host;
    }

    public int getSslPort() {
        return SslPort;
    }

    public void setSslPort(int sslPort) {
        SslPort = sslPort;
    }

    public byte[] getCertification() {
        return Certification;
    }

    public void setCertification(byte[] certification) {
        Certification = certification;
    }
}
