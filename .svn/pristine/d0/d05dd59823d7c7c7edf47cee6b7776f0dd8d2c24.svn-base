package usung.com.mqttclient.bean.db;

import java.util.List;
import usung.com.mqttclient.bean.user.UserSimpleInfo;

/**
 * 登陆后获得的初始化信息
 *
 * @author herui
 * @date 2018/12/20
 */

public class InitiaData {
    /// <summary>
    /// 陌生人列表
    /// </summary>
    public List<UserSimpleInfo> StrangerList;
    /// <summary>
    /// 黑名单列表
    /// </summary>
    public List<UserSimpleInfo> BlackList;
    /// <summary>
    /// 好友列表
    /// </summary>
    public List<UserSimpleInfo> FriendList;
    /// <summary>
    /// 群组列表
    /// </summary>
    public List<GroupInfo> Groups;
    /// <summary>
    /// 离线消息
    /// </summary>
    public List<Message> OffLineMessages;
    /// <summary>
    /// 自己的消息简述
    /// </summary>
    public UserSimpleInfo SelfInfo;
    /// <summary>
    /// 自己的Mqtt主题
    /// </summary>
    public String SelfTopic;
    /// <summary>
    /// 所有好友和群组的主题，键值对 {userid，topic}
    /// 在线 value不等于空（""），离线value为空（""）
    /// </summary>
//    public KeyValuePair<String, String>[] AllTopic;

    /// <summary>
    /// 分配给客户端的系统管理员的主题，所有的系统请求，需要通过这个主题发送，添加好友，添加群，邀请入群
    /// 发送失败以后，调用接口重获取一个新的主题。
    /// </summary>
    public AdministratorInfo Admin;

    public List<UserSimpleInfo> getStrangerList() {
        return StrangerList;
    }

    public void setStrangerList(List<UserSimpleInfo> strangerList) {
        StrangerList = strangerList;
    }

    public List<UserSimpleInfo> getBlackList() {
        return BlackList;
    }

    public void setBlackList(List<UserSimpleInfo> blackList) {
        BlackList = blackList;
    }

    public List<UserSimpleInfo> getFriendList() {
        return FriendList;
    }

    public void setFriendList(List<UserSimpleInfo> friendList) {
        FriendList = friendList;
    }

    public List<GroupInfo> getGroups() {
        return Groups;
    }

    public void setGroups(List<GroupInfo> groups) {
        Groups = groups;
    }

    public List<Message> getOffLineMessages() {
        return OffLineMessages;
    }

    public void setOffLineMessages(List<Message> offLineMessages) {
        OffLineMessages = offLineMessages;
    }

    public UserSimpleInfo getSelfInfo() {
        return SelfInfo;
    }

    public void setSelfInfo(UserSimpleInfo selfInfo) {
        SelfInfo = selfInfo;
    }

    public String getSelfTopic() {
        return SelfTopic;
    }

    public void setSelfTopic(String selfTopic) {
        SelfTopic = selfTopic;
    }

    public AdministratorInfo getAdmin() {
        return Admin;
    }

    public void setAdmin(AdministratorInfo admin) {
        Admin = admin;
    }
}
