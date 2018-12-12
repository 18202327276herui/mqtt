namespace InstantMessaging.Core.Models.Public
{
    public class GroupSimpleInfo:InfoBase
    {
        /// <summary>
        /// 创建者 id
        /// </summary>
        public string Creator { get; set; }
        /// <summary>
        /// 当前成员数
        /// </summary>
        public int MemberCount { get; set; }
        /// <summary>
        /// 最大成员数
        /// </summary>
        public int MaxCount { get; set; }
    }
}
