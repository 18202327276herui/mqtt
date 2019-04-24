package usung.com.mqttclient.Interface;

import java.util.ArrayList;
import java.util.List;

import usung.com.mqttclient.bean.db.GroupInfo;
import usung.com.mqttclient.bean.db.GroupMember;
import usung.com.mqttclient.bean.db.GroupSimpleInfo;
import usung.com.mqttclient.bean.user.UserSimpleInfo;

/**
 /// <summary>
 /// 客户端 模块 完成好友信息、topic、 用户状态 的管理
 /// </summary>
 * Created by herui on 2019/4/3.
 */

public interface IUserInfoManager {
    /// <summary>
    /// 用户当前Id
    /// </summary>
    long UserId = 0;
    /// <summary>
    /// 查询某个好友的简要信息
    /// </summary>
    /// <param name="userid"></param>
    /// <returns></returns>
    UserSimpleInfo GetUserSimpleInfo(long userid);
    /// <summary>
    /// 获取陌生人列表
    /// </summary>
    List<Long> StrangerList = new ArrayList<>();
    /// <summary>
    /// 获取黑名单
    /// </summary>
    List<Long> BlackList = new ArrayList<>();

    /// <summary>
    /// 获取好友列表
    /// </summary>
    List<Long> FriendList = new ArrayList<>();

    /// <summary>
    /// 是否处于别人的黑名单
    /// </summary>
    /// <param name="userid"></param>
    /// <returns></returns>
    boolean IsInBlackList(long userid);

    /// <summary>
    /// 获取群组列表
    /// </summary>
    List<Long> GroupList = new ArrayList<>();
    /// <summary>
    /// 用户是否位于黑名单之中
    /// </summary>
    /// <param name="userid"></param>
    /// <returns></returns>
    boolean IsInStranger(long userid);
    /// <summary>
    /// 用户是否是好友
    /// </summary>
    /// <param name="userid"></param>
    /// <returns></returns>
    boolean IsInFriend(long userid);
    /// <summary>
    /// 存储离线消息
    /// </summary>
    /// <param name="msg"></param>
    void AddFriend(UserSimpleInfo info);
    /// <summary>
    /// 移除好友的缓存
    /// </summary>
    /// <param name="friendid"></param>
    /// <returns></returns>
    boolean RemoveFriend(long friendid);
    /// <summary>
    /// 添加陌生人列表
    /// </summary>
    /// <param name="info"></param>
    void AddIntoStrangerList(UserSimpleInfo info);
    /// <summary>
    /// 从陌生人列表一处
    /// </summary>
    /// <param name="friendid"></param>
    /// <returns></returns>
    boolean RemoveFromStrangerList(long friendid);
    /// <summary>
    /// 添加进黑名单
    /// </summary>
    /// <param name="info"></param>
    void AddIntoBlackList(UserSimpleInfo info);
    /// <summary>
    /// 从黑名单
    /// </summary>
    /// <param name="friendid"></param>
    /// <returns></returns>
    boolean RemoveFromeBlackList(long friendid);
    /// <summary>
    /// 移除群成员
    /// </summary>
    /// <param name="groupid"></param>
    /// <param name="memberid"></param>
    void RemoveMemberFromGroup(long groupid, long memberid);
    /// <summary>
    /// 标记群已被禁言
    /// </summary>
    /// <param name="groupid"></param>
    void MuteGroup(long groupid);
    /// <summary>
    /// 标记群被解除禁言
    /// </summary>
    /// <param name="groupid"></param>
    void UnMuteGroup(long groupid);
    /// <summary>
    /// 删除群组的缓存
    /// </summary>
    /// <param name="groupid"></param>
    /// <returns></returns>
    boolean RemoveGroup(long groupid);
    /// <summary>
    /// 添加群成员
    /// </summary>
    /// <param name="groupid"></param>
    /// <param name="member"></param>
    void AddGroupMember(long groupid, long member);
    /// <summary>
    /// 更改成员角色
    /// </summary>
    /// <param name="groupid"></param>
    /// <param name="memberid"></param>
    /// <param name="role"></param>
//    void ChangeGroupMemberRole(long groupid, long memberid, GroupMemberRole role);
    /// <summary>
    /// 标记成员被禁言
    /// </summary>
    /// <param name="groupid"></param>
    /// <param name="memberid"></param>
    void MuteGroupMember(long groupid, long memberid);
    /// <summary>
    /// 接触群成员被解除禁言
    /// </summary>
    /// <param name="groupid"></param>
    /// <param name="memberid"></param>
    void UnmuteGroupMember(long groupid, long memberid);
    /// <summary>
    /// 用户是否在群中
    /// </summary>
    /// <param name="groupid"></param>
    /// <param name="memberid"></param>
    /// <returns></returns>
    boolean IsUserInGroup(long groupid, long memberid);
    /// <summary>
    /// 获取群信息
    /// </summary>
    /// <param name="groupid"></param>
    /// <returns></returns>
    GroupInfo GetGroupInfo(long groupid);
    /// <summary>
    /// 更新用户描述
    /// </summary>
    /// <param name="usrtid"></param>
    /// <param name="description"></param>
    void UpdateUserDescription(long usrtid, String description);
    /// <summary>
    /// 更新群组描述
    /// </summary>
    /// <param name="groupid"></param>
    /// <param name="description"></param>
    void UpdateGroupDescription(long groupid, String description);
    /// <summary>
    /// 获取群管理员Id
    /// </summary>
    /// <param name="groupid"></param>
    /// <returns></returns>
    List<Long> GetGroupAdministrators(long groupid);
    /// <summary>
    /// 添加群简要信息
    /// </summary>
    /// <param name="info"></param>
    void AddGroup(GroupSimpleInfo info);
    /// <summary>
    /// 添加群信息
    /// </summary>
    /// <param name="info"></param>
    void AddGroup(GroupInfo info);
    /// <summary>
    /// 是否包含群组
    /// </summary>
    /// <param name="groupid"></param>
    /// <returns></returns>
    boolean ContainsGroup(long groupid);
    /// <summary>
    /// 群是否包含该成员
    /// </summary>
    /// <param name="groupid"></param>
    /// <param name="memberid"></param>
    /// <returns></returns>
    boolean ContainsGroupMember(long groupid, long memberid);
    /// <summary>
    /// 群是否被禁言
    /// </summary>
    /// <param name="groupid"></param>
    /// <returns></returns>
    boolean GroupIsMuted(long groupid);
    /// <summary>
    /// 获取群成员
    /// </summary>
    /// <param name="groupid"></param>
    /// <param name="memberid"></param>
    /// <returns></returns>
    GroupMember GetGroupMember(long groupid, long memberid);
    /// <summary>
    /// 查询用户主题
    /// </summary>
    /// <param name="id"></param>
    /// <returns></returns>
    String GetTopic(long id);
    /// <summary>
    /// 存储用户主题
    /// </summary>
    /// <param name="topics"></param>
    /// <param name="changestate"></param>
//    void SaveTopic(List<KeyValuePair<Long, String>> topics, boolean changestate = true);
    /// <summary>
    /// 存储用户主题
    /// </summary>
    /// <param name="user"></param>
    /// <param name="topic"></param>
    /// <param name="changestate"></param>
    void SaveTopic(long user, String topic, boolean changestate);
    /// <summary>
    /// 移除用户主题
    /// </summary>
    /// <param name="userid"></param>
    void RemoveTopic(long userid);
    /// <summary>
    ///设置用户状态
    /// </summary>
    /// <param name="userid"></param>
    /// <param name="state"></param>
//    void SetUserState(long userid, UserState state);
    /// <summary>
    /// 获取用户状态
    /// </summary>
    /// <param name="userid"></param>
    /// <returns></returns>
//    UserState GetUserState(long userid);
    /// <summary>
    /// 是否是群组id
    /// </summary>
    /// <param name="id"></param>
    /// <returns></returns>
    boolean IsGroup(long id);
    /// <summary>
    /// 用户是否在线
    /// </summary>
    /// <returns></returns>
    List<Long> GetOnlineFriend();
}
