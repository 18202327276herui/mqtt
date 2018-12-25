using InstantMessaging.Core.Models.Public.Mqtt;
using InstantMessaging.Core.Models.Public;
using System.Collections.Generic;

namespace InstantMessaging.Core.Models.Public.Http
{
    /// <summary>
    /// 登陆后获得的初始化信息
    /// </summary>
    public  class InitiaData
    {
        /// <summary>
        /// 陌生人列表
        /// </summary>
        public UserSimpleInfo[] StrangerList { get; set; } 
        /// <summary>
        /// 黑名单列表
        /// </summary>
        public UserSimpleInfo[] BlackList { get; set; } 
        /// <summary>
        /// 好友列表
        /// </summary>
        public UserSimpleInfo[] FriendList { get; set; } 
        /// <summary>
        /// 群组列表
        /// </summary>
        public GroupInfo[] Groups { get; set; }
        /// <summary>
        /// 离线消息
        /// </summary>
        public Message[] OffLineMessages { get; set; }
        /// <summary>
        /// 自己的消息简述
        /// </summary>
        public UserSimpleInfo SelfInfo { get; set; }
        /// <summary>
        /// 自己的Mqtt主题
        /// </summary>
        public string SelfTopic { get; set; }
        /// <summary>
        /// 所有好友和群组的主题，键值对 {userid，topic}
        /// 在线 value不等于空（""），离线value为空（""）
        /// </summary>
        public KeyValuePair<string, string>[] AllTopic { get; set; }

        /// <summary>
        /// 分配给客户端的系统管理员的主题，所有的系统请求，需要通过这个主题发送，添加好友，添加群，邀请入群
        /// 发送失败以后，调用接口重获取一个新的主题。
        /// </summary>
        public AdministratorInfo Admin { get; set; }

       
       
    }
}
