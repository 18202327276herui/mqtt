namespace InstantMessaging.Core.Models.Public.Http
{
    /// <summary>
    ///操作来源
    /// </summary>
    public enum OpereatRelience
    {
        /// <summary>
        /// 好友关系
        /// </summary>
        FromFriendList,
        /// <summary>
        /// 处于相同的一个群
        /// </summary>
        FromGroup,
        /// <summary>
        /// 来自黑名单
        /// </summary>
        FromBlackList,
        /// <summary>
        /// 来自陌生人列表
        /// </summary>
        FromStrangerList
    }
}
