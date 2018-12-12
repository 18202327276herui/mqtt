namespace InstantMessaging.Core.Models.Public.Mqtt
{
    /// <summary>
    /// <see cref="MessageType.Request"/>消息Content 的类容
    /// </summary>
    public class Request
    {
        /// <summary>
        /// 请求 id guid
        /// </summary>
        public string RequestId { get; set; }
        /// <summary>
        /// 请求类型<see cref="RequestType"/>
        /// </summary>
        public RequestType RequestType { get; set; }
        /// <summary>
        /// 申请者 ，邀请入群的申请者为群id，只支持群管理员权限以上的用户，邀请其他用户入群
        /// </summary>
        public string Proposer { get; set; }
        /// <summary>
        /// 验证消息
        /// </summary>
        public string VarifyMessage { get; set; }
        /// <summary>
        /// 处理者 id ，入侵申请 系统会自动分配给一个群管理员来处理
        /// </summary>
        public string Recipient { get; set; }
        
        /// <summary>
        /// 重写
        /// 如果  请求类型 申请者 处理者 都相同 ，则认为是一个相同的请求
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;

            if (obj.GetType() != typeof(Request))
                return false;

            var request = (Request)obj;

            return request.Proposer == Proposer && 
                   RequestType == request.RequestType &&
                   Recipient == request.Recipient;
        }
        public override int GetHashCode()
        {
            return base.GetHashCode();
        }

    }
}
