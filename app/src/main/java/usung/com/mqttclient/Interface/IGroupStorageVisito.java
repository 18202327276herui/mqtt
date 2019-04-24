package usung.com.mqttclient.Interface;

import java.util.Date;

import usung.com.mqttclient.bean.db.Message;

/**
 * /// <summary>
 * /// 访问群消息服务
 * /// </summary>
 * Created by herui on 2019/4/3.
 */

public interface IGroupStorageVisito {
    Message[] GetGroupMessageAfter(long groupid, String msgid);

    Message[] GetGroupMessageSince(long groupid, Date time);
}
