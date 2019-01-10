package usung.com.mqttclient.bean.user;

import java.io.Serializable;

/**
 * 用户状态信息
 *
 * @author herui
 * @date 2019/1/10
 */

public class UserStateInfo implements Serializable {
    /// <summary>
    /// 状态
    /// </summary>
    public int State;
    /// <summary>
    /// 主题
    /// </summary>
    public String Topic;

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }
}
