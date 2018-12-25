package usung.com.mqttclient.bean.user;

import java.io.Serializable;

import usung.com.mqttclient.bean.HttpResposeDataBase;

/**
 * 获取用户简要信息结果类
 * @author herui
 * @date 2018/12/20
 */

public class UserSimpleInfoResult extends HttpResposeDataBase implements Serializable {
    public UserSimpleInfo Info;

    public UserSimpleInfo getInfo() {
        return Info;
    }

    public void setInfo(UserSimpleInfo info) {
        Info = info;
    }
}
