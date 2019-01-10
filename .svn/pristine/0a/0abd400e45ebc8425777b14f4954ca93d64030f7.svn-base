package usung.com.mqttclient.bean.db;

import usung.com.mqttclient.bean.user.UserSimpleInfo;
import usung.com.mqttclient.utils.cn.CN;

/**
 * Created by you on 2017/9/11.
 */

public class Contact implements CN {

    public final String name;

    public final int imgUrl;

    public UserSimpleInfo userSimpleInfo;

    public Contact(String name, int imgUrl, UserSimpleInfo userSimpleInfo) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.userSimpleInfo = userSimpleInfo;
    }

    @Override
    public String chinese() {
        return name;
    }
}
