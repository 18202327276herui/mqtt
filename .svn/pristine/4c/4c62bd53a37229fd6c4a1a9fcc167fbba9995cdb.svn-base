package usung.com.mqttclient.bean.db;

import java.io.Serializable;

/**
 * Created by herui on 2019/2/1.
 */

/// <summary>
/// <see cref="MessageType.Request"/>消息Content 的类容
/// </summary>
public class Request implements Serializable {
    /// <summary>
    /// 请求 id guid
    /// </summary>
    public String RequestId ;
    /// <summary>
    /// 请求类型<see cref="RequestType"/>
    /// </summary>
    public int RequestType;
    /// <summary>
    /// 申请者 ，邀请入群的申请者为群id，只支持群管理员权限以上的用户，邀请其他用户入群
    /// </summary>
    public String Proposer ;
    /// <summary>
    /// 验证消息
    /// </summary>
    public String VarifyMessage ;
    /// <summary>
    /// 处理者 id ，入侵申请 系统会自动分配给一个群管理员来处理
    /// </summary>
    public String Recipient ;

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public int getRequestType() {
        return RequestType;
    }

    public void setRequestType(int requestType) {
        RequestType = requestType;
    }

    public String getProposer() {
        return Proposer;
    }

    public void setProposer(String proposer) {
        Proposer = proposer;
    }

    public String getVarifyMessage() {
        return VarifyMessage;
    }

    public void setVarifyMessage(String varifyMessage) {
        VarifyMessage = varifyMessage;
    }

    public String getRecipient() {
        return Recipient;
    }

    public void setRecipient(String recipient) {
        Recipient = recipient;
    }
}
