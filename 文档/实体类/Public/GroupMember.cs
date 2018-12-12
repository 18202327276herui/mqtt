using System;

namespace InstantMessaging.Core.Models.Public
{
    public class GroupMember
    {
        /// <summary>
        /// yonghuid
        /// </summary>
        public string UserId { get; set; }
        /// <summary>
        /// 群组角色
        /// </summary>
        public GroupMemberRole Role { get; set; }
        /// <summary>
        /// 最后一次活跃
        /// </summary>
        public DateTime LastActive { get; set; }
        /// <summary>
        /// 是否被禁言
        /// </summary>
        public bool IsMuted { get; set; }

    }
}
