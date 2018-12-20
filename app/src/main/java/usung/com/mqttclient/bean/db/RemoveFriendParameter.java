package usung.com.mqttclient.bean.db;

import java.io.Serializable;

import usung.com.mqttclient.bean.HttpRequestParameterBase;

/**
 *  删除好友参数
 * @author herui
 * @date 2018/12/19
 */

public class RemoveFriendParameter extends HttpRequestParameterBase implements Serializable{
    public String FriendId;

    public String getFriendId() {
        return FriendId;
    }

    public void setFriendId(String friendId) {
        FriendId = friendId;
    }
}
