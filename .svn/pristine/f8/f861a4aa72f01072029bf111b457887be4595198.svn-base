package usung.com.mqttclient.Interface;

import usung.com.mqttclient.bean.db.Message;

/**
 * /// <summary>
 ///  客户端通信层提功能抽象
 /// 注：
 ///1、 qoslevel 均为零
 ///2、主题前缀：/Im/
 ///
 /// </summary>
 * Created by herui on 2019/4/2.
 */

public interface IMessager {
    /// <summary>
    /// mqtt publish 函数封装
    ///
    /// 发送后，可能需要进行的操作
    /// 1、存储到本地消息
    /// 2、添加到ack确认队列
    /// 3、更新UI
    /// ............
    /// </summary>
    boolean sendMessage(Message msg);
    /// <summary>
    /// 连接
    /// </summary>
    /// <returns></returns>
    boolean connect();
    /// <summary>
    /// 关闭
    /// </summary>
    /// <returns></returns>
    boolean close();
    /// <summary>
    /// 订阅主题  核心 mqtt subscribe
    /// </summary>
    /// <param name="topic"></param>
    /// <returns></returns>
    boolean subscribeSelf(long selfId);
    /// <summary>
    /// 订阅群主题  核心 mqtt subscribe
    /// </summary>
    /// <param name="groupId"></param>
    /// <returns></returns>
    boolean unsubscribeGroup(long groupId);
    /// <summary>
    /// 取消订阅  群主题
    /// </summary>
    /// <param name="groupId"></param>
    /// <returns></returns>
    boolean subscribeGroup(long groupId);
}
