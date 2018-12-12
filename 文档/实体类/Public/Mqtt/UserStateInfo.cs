namespace InstantMessaging.Core.Models.Public.Mqtt
{
    /// <summary>
    /// <see cref="MessageType.UserStateChanged"/>消息，contet 的内容 ，
    /// </summary>
    public class UserStateInfo
    {
        /// <summary>
        /// 状态
        /// </summary>
        public UserState State { get; set; }
        /// <summary>
        /// 主题
        /// </summary>
        public string Topic { get; set; }
    }
}
