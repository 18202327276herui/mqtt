package usung.com.mqttclient.bean.user;

import java.io.Serializable;

/**
 * 登录参数
 * @author herui
 * @date 2018/12/13
 */

public class LoginParameter implements Serializable {
    /// <summary>
    /// 用户名
    /// </summary>
    public String UserId;
    /// <summary>
    /// 密码
    /// </summary>
    public String PassWord;
    /// <summary>
    /// 验证码  尚未启用
    /// </summary>
    public String VarifyCode;
    /// <summary>
    /// 关联的session id  尚未启用
    /// </summary>
    public String RelatedSessionId;

    public DeviceType DeviceType;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getVarifyCode() {
        return VarifyCode;
    }

    public void setVarifyCode(String varifyCode) {
        VarifyCode = varifyCode;
    }

    public String getRelatedSessionId() {
        return RelatedSessionId;
    }

    public void setRelatedSessionId(String relatedSessionId) {
        RelatedSessionId = relatedSessionId;
    }

    public usung.com.mqttclient.bean.user.DeviceType getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(usung.com.mqttclient.bean.user.DeviceType deviceType) {
        DeviceType = deviceType;
    }
}
