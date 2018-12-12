using System.Collections.Generic;

namespace InstantMessaging.Core.Models.Public
{
    public class GroupInfo
    {
        /// <summary>
        /// 管理员
        /// </summary>
        public List<string> Administrators = new List<string>();
        /// <summary>
        /// 成员列表
        /// </summary>
        public Dictionary<string, GroupMember> Members = new Dictionary<string, GroupMember>();
        /// <summary>
        /// 是否被禁言
        /// </summary>
        public bool IsMuted { get; set; }

        public GroupSimpleInfo SimpleInfo { get; set; }
      
    }

}
