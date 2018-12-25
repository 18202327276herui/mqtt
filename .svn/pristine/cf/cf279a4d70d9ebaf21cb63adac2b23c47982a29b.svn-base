namespace InstantMessaging.Core.Models.Public.Mqtt
{
    public  enum SystemAcknowlegeType
    {
        /// <summary>
        /// 已接收请求
        /// </summary>
        Successed,
        /// <summary>
        /// 相同的请求已经存在 
        /// </summary>
        Failed,
        /// <summary>
        /// 请求已失效 ，客户端回复请求时会使用，如 收到好友请求但长时间不处理，服务器默认保存请求的时间为30天，过期删除
        /// </summary>
        RequestExpired,
    }
}
