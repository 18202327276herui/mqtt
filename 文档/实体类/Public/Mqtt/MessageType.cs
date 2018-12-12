namespace InstantMessaging.Core.Models.Public.Mqtt
{
    public enum MessageType : byte
    {
        /// <summary>
        /// 文本
        /// </summary>
        Text,
        /// <summary>
        /// 语音
        /// </summary>
        Voice,
        /// <summary>
        /// 音乐
        /// </summary>
        Music,
        /// <summary>
        /// 图片
        /// </summary>
        Image,
        /// <summary>
        /// gif
        /// </summary>
        Gif,
       /// <summary>
       /// 视屏
       /// </summary>
        Vedio,
        /// <summary>
        /// 文件
        /// </summary>
        File,
        /// <summary>
        ///窗体抖动
        /// </summary>
        Shake,
        /// <summary>
        /// 请求
        /// </summary>
        Request,
        /// <summary>
        /// 系统确认请求  由系统发送  
        /// </summary>
        SystemAcknowlege,
        /// <summary>
        /// 回复
        /// </summary>
        Response,
        /// <summary>
        /// 系统请求通过 由系统发送
        /// </summary>
        RequestPassed,
        /// <summary>
        /// 一般 消息类型
        /// </summary>
        Content,
        /// <summary>
        /// 移除好友  由系统发送
        /// </summary>
        FriendRemoved,
        /// <summary>
        /// 被移除群  由系统发送
        /// </summary>
        GroupMemberRemoved,
        /// <summary>
        /// 群解散了   由系统发送
        /// </summary>
        GroupReleased,
        /// <summary>
        /// 请求入群  由系统发送
        /// </summary>
        /// <summary>
        /// 加入群  由系统发送
        /// </summary>
        NewGroupMemberJoined,
        /// <summary>
        /// 离开群  由系统发送
        /// </summary>
        GroupMemberLeft,
        /// <summary>
        /// 全体禁言  由系统发送
        /// </summary>
        GroupMuted,
        /// <summary>
        /// 解除全体禁言  由系统发送
        /// </summary>
        GroupUnmuted,
        /// <summary>
        /// 禁言某个人 由系统发送
        /// </summary>
        GroupMemberMuted,
        /// <summary>
        /// 取消禁言某个人 由系统发送
        /// </summary>
        GroupMemberUnmuted,
        /// <summary>
        /// 群成员权限发生更改  由系统发送
        /// </summary>
        GroupMemberRoleChanged,
        /// <summary>
        /// 消息回复
        /// </summary>
        MsgAck,
        /// <summary>
        /// 用户状态发生更改
        /// </summary>
        UserStateChanged,
        /// <summary>
        /// 强制下线，重复登陆时 由系统发送
        /// </summary>
        ForceLogOff,
        /// <summary>
        /// 用户属性发生更改
        /// </summary>
        UserDescriptionChanged,
        /// <summary>
        /// 群组属性发生更改  由系统发送
        /// </summary>
        GroupDescriptionChanged,
        /// <summary>
        /// 消息撤回
        /// </summary>
        MessageWithdraw,
    }
}
