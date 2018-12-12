namespace InstantMessaging.Core.Models.Public.Http
{
    /// <summary>
    /// 注册所需要的参数
    /// </summary>
    public class RegisteParameter
    {
        /// <summary>
        /// 用户 Id
        /// </summary>
        public string UserId { get; set; }
        /// <summary>
        /// 第一次密码
        /// </summary>
        public string FirstPassWords { get; set; }
        /// <summary>
        /// 第二次密码  尚未启用
        /// </summary>
        public string SecondPassWords { get; set; }
        /// <summary>
        /// 验证码 尚未启用
        /// </summary>
        public string VarifyCode { get; set; }
        /// <summary>
        ///  关联验证码的SessionId      尚未启用
        /// </summary>
        public string RelatedSessionId { get; set; }
        
    }
}
