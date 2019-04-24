package usung.com.mqttclient.Interface;

import usung.com.mqttclient.bean.db.Message;

/**
 /// <summary>
 /// 处理<see cref="IMessager"/>的各种事件
 /// <see cref="IMessager"/>需要提供事件的触发
 /// 主要业务逻辑需要在这里实现,主要处理收到消息的各种逻辑
 /// UI handler 处理各种ui事件，主要是发送
 ///
 /// </summary>
 * Created by herui on 2019/4/3.
 */

public interface IMessagerEventHandler {
    void OnMessageReceived(Message msg);
    void OnDisconnect(Exception ex);
    void OnConnect();
}
