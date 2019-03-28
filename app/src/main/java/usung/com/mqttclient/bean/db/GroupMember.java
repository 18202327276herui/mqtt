package usung.com.mqttclient.bean.db;

import java.io.Serializable;

/**
 * 群成员
 * @author herui
 * @date 2018/12/21
 */

public class GroupMember implements Serializable {
    /// <summary>
    /// yonghuid
    /// </summary>
    public long UserId ;
    /// <summary>
    /// 群组角色
    /// </summary>
    public int Role ;
    /// <summary>
    /// 最后一次活跃
    /// </summary>
    public String LastActive ;
    /// <summary>
    /// 是否被禁言
    /// </summary>
    public Boolean IsMuted ;

    public String JoinTime ;

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        UserId = userId;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int role) {
        Role = role;
    }

    public String getLastActive() {
        return LastActive;
    }

    public void setLastActive(String lastActive) {
        LastActive = lastActive;
    }

    public Boolean getMuted() {
        return IsMuted;
    }

    public void setMuted(Boolean muted) {
        IsMuted = muted;
    }

    public String getJoinTime() {
        return JoinTime;
    }

    public void setJoinTime(String joinTime) {
        JoinTime = joinTime;
    }
}
