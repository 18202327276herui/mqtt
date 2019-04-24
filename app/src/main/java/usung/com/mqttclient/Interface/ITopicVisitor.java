package usung.com.mqttclient.Interface;

/**
 /// <summary>
 /// 提供 topic 服务的访问
 /// 尽量忽略掉通信协议的细节，提供更高层的的抽象
 /// <see cref="TopicPublicServiceDescription"/>
 /// </summary>
 * Created by herui on 2019/4/3.
 */

public interface ITopicVisitor {
    String queryTopic(long userid);
    String queryUserState(long userid, String oldTopic);
}
