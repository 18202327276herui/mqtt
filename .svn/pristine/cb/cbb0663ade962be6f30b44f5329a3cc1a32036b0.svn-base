package usung.com.mqttclient.Interface;

import usung.com.mqttclient.bean.db.GroupInfo;
import usung.com.mqttclient.bean.db.GroupSimpleInfo;
import usung.com.mqttclient.bean.db.InitiaData;
import usung.com.mqttclient.bean.db.Message;
import usung.com.mqttclient.bean.user.UserSimpleInfo;

/**
 /// <summary>
 /// 提供访问后台Db服务功能
 /// 分成几个服务组，是因为，到时候可能这几个服务会部署到不同的服务器上
 /// <see cref="DBPublicServiceDefination"/>
 /// </summary>
 * Created by herui on 2019/4/3.
 */

public interface IDbVisitor {
    boolean addIntoBlackList(long userid, long friendid);
    boolean addIntoStrangerList(long userid, long friendid);
//    boolean ChangeGroupMemberRole(long groupid, long memberid, GroupMemberRole role);
//    RegistGroupResultData CreateGroup(long userid, string description, string nickName, string portrait);
    GroupInfo getGroupInfo(long groupid);
    GroupSimpleInfo getGroupSimpleInfo(long groupid);
    InitiaData getInitiaData(long userid);
    UserSimpleInfo getUserSimpleInfo(long userid);
    boolean muteGroup(long groupid);
    boolean muteGroupMember(long groupid, long memberid);
    boolean realeseGroup(long groupid);
    boolean removeFriend(long friendid);
    boolean removeFromBlackList(long userid, long friendid);
    boolean removeFromStrangerList(long userid, long friendid);
    boolean removeUserFromGroup(long groupid, long memberid);
    boolean saveOffLineMessage(Message msg);
    boolean unMuteGroup(long groupid);
    boolean unmuteGroupMember(long groupid, long memberid);
    boolean updateUserSimpleInfo(String userid, String nickName, String description, String portrait);
    boolean updateGroupSimpleInfo(long groupid, String nickName, String description, String portrait);
    boolean leaveGroup(long groupid);
}
