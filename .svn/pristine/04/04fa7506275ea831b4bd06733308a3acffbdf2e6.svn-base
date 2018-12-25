namespace InstantMessaging.Core.Models.Public.Mqtt
{
    /// <summary>
    /// <see cref="MessageType.SystemAcknowlege"/>消息，content 内容，此消息由系统发送
    /// 客户端每向系统发送一条<see cref="MessageType.Request"/>或者<see cref="MessageType.Response"/>系统会
    /// 回复一条确认消息，注意记录系统回复有没有超时，超时调用接口重新获取系统管理员，重新发送request或者response
    /// </summary>
    public class SystemAcknowlege
    {
        /// <summary>
        /// 消息Id
        /// </summary>
        public string MessageId { get; set; }
        /// <summary>
        /// 请求id
        /// </summary>
        public string RequestId { get; set; }
        /// <summary>
        /// 系统处理结果
        /// </summary>
        public SystemAcknowlegeType SystemRequestResult { get; set; }

    }
}
