namespace InstantMessaging.Core.Models.Public.Http
{
    /// <summary>
    /// 注册不成功 <see cref="GroupId"/>和<see cref="Topic"/>为 null 值
    /// </summary>
    public class RegistGroupResultData
    {
        /// <summary>
        /// 群Id
        /// </summary>
        public string GroupId { get; set; }
        /// <summary>
        /// 群主题
        /// </summary>
        public string Topic { get; set; }
        /// <summary>
        /// 是否成功
        /// </summary>
        public bool Successed { get; set; }
    }
}
