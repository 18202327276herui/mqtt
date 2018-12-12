using System;
namespace InstantMessaging.Core.Models.Public.Mqtt
{
    /// <summary>
    /// mqtt 通信使用的消息类
    /// </summary>
    public class Message
    {
        /// <summary>
        /// 消息类型
        /// </summary>
        public MessageType MessageType { get; set; }
        /// <summary>
        /// 标识是否群消息
        /// 当为true Recipientid 为 群id ，否则为用户id
        /// </summary>
        public bool IsGroup { get; set; }
        /// <summary>
        /// 消息Id 唯一 使用 Guid
        /// </summary>
        public string Id { get; set; } = Guid.NewGuid().ToString();
        /// <summary>
        /// 标识消息所属的会话 ，有 3种
        /// 1.对于群消息，session即为群id
        /// 2.对于私人消息，发送方的session ，为接受者id，接受者的session，为发送方id
        /// 3，系统消息 统一为 “sys”
        /// </summary>
        public string Session { get; set; }
      
        /// <summary>
        /// Content 可为2次序列化的json字符串，
        /// </summary>
        public string Content { get; set; }

        /// <summary>
        /// 发送者Id
        /// </summary>
        public string SenderId { get; set; }
        /// <summary>
        /// 当为 群组消息是 此id 为群id， 为私人消息时，此id为接收者id
        /// </summary>
        public string RecipentId { get; set; }
        /// <summary>
        /// 消息创建时间，创建时自动设置
        /// </summary>
        public DateTime Time { get; set; } = DateTime.Now;
    


    }
}
