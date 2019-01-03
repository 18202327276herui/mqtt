package usung.com.mqttclient.bean.db;

import java.io.Serializable;

/**
 * /// <summary>
 * /// 一些数据库操作所需要的凭据
 * /// 1.存储离线消息
 * /// 对方必须为你的好友，或者处于相同的群之中
 * /// 2.查询用户主题
 * /// 同上
 * /// 3.查询用户状态
 * /// 同上
 * /// <see cref="Op
 *
 * @author herui
 * @date 2018/12/28
 */

public class OpereatCredencial implements Serializable {
    /// <summary>
    /// 关系来自
    /// </summary>
    public int Relience;
    /// <summary>
    /// 处于相同的群的 id ，如果是好友关系，此字段无用
    /// </summary>
    public String Groupid;

    public int getRelience() {
        return Relience;
    }

    public void setRelience(int relience) {
        Relience = relience;
    }

    public String getGroupid() {
        return Groupid;
    }

    public void setGroupid(String groupid) {
        Groupid = groupid;
    }
}
