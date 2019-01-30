package usung.com.mqttclient.bean.db;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * mqtt 通信使用的消息类
 *
 * @author herui
 * @date 2018/12/24
 */

public class Message implements Serializable {
    /// <summary>
    /// 消息类型
    /// </summary>
    public int MessageType;
    /// <summary>
    /// 标识是否群消息
    /// 当为true Recipientid 为 群id ，否则为用户id
    /// </summary>
    public Boolean IsGroup;
    /// <summary>
    /// 消息Id 唯一 使用 Guid { get; set; } = Guid.NewGuid().ToString();
    /// </summary>
    public String MessageId ;
    /// <summary>
    /// 标识消息所属的会话 ，有 3种
    /// 1.对于群消息，session即为群id
    /// 2.对于私人消息，发送方的session ，为接受者id，接受者的session，为发送方id
    /// 3，系统消息 统一为 “sys”
    /// </summary>
    public String Session;

    /// <summary>
    /// Content 可为2次序列化的json字符串，
    /// </summary>
    public String Content;

    /// <summary>
    /// 发送者Id
    /// </summary>
    public String SenderId;
    /// <summary>
    /// 当为 群组消息是 此id 为群id， 为私人消息时，此id为接收者id
    /// </summary>
    public String RecipientId;
    /// <summary>
    /// 消息创建时间，创建时自动设置
    /// </summary>
    public String Time;

    public int getMessageType() {
        return MessageType;
    }

    public void setMessageType(int messageType) {
        MessageType = messageType;
    }

    public Boolean getGroup() {
        return IsGroup;
    }

    public void setGroup(Boolean group) {
        IsGroup = group;
    }

    public String getSession() {
        return Session;
    }

    public void setSession(String session) {
        Session = session;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getSenderId() {
        return SenderId;
    }

    public void setSenderId(String senderId) {
        SenderId = senderId;
    }

    public String getRecipientId() {
        return RecipientId;
    }

    public void setRecipientId(String recipientId) {
        RecipientId = recipientId;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getMessageId() {
        return MessageId;
    }

    public void setMessageId(String messageId) {
        MessageId = messageId;
    }
}
