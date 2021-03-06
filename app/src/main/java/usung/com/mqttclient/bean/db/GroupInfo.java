package usung.com.mqttclient.bean.db;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

/**
 * 群信息
 * @author herui
 * @date 2018/12/21
 */

public class GroupInfo implements Serializable {
    /// <summary>
    /// 管理员
    /// </summary>
    public List<Long> Administrators;
    /// <summary>
    /// 成员列表
    /// </summary>
    public Map<Long, GroupMember> Members;
    /// <summary>
    /// 是否被禁言
    /// </summary>
    public Boolean IsMuted;

    public GroupSimpleInfo SimpleInfo;

    public List<Long> getAdministrators() {
        return Administrators;
    }

    public void setAdministrators(List<Long> administrators) {
        Administrators = administrators;
    }

    public Map<Long, GroupMember> getMembers() {
        return Members;
    }

    public void setMembers(Map<Long, GroupMember> members) {
        Members = members;
    }

    public Boolean getMuted() {
        return IsMuted;
    }

    public void setMuted(Boolean muted) {
        IsMuted = muted;
    }

    public GroupSimpleInfo getSimpleInfo() {
        return SimpleInfo;
    }

    public void setSimpleInfo(GroupSimpleInfo simpleInfo) {
        SimpleInfo = simpleInfo;
    }
}
