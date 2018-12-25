namespace InstantMessaging.Core.Models.Public.Http
{
    /// <summary>
    /// 一些数据库操作所需要的凭据
    /// 1.存储离线消息
    /// 对方必须为你的好友，或者处于相同的群之中
    /// 2.查询用户主题
    /// 同上
    /// 3.查询用户状态
    /// 同上
    /// <see cref="OpereatRelience"></see>
    /// </summary>
    public class OpereatCredencial
    {
        /// <summary>
        /// 关系来自
        /// </summary>
        public OpereatRelience Relience { get; set; }
        /// <summary>
        /// 处于相同的群的 id ，如果是好友关系，此字段无用
        /// </summary>
        public string Groupid { get; set; }
    }
}
