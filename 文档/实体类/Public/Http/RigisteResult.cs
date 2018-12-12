namespace InstantMessaging.Core.Models.Public.Http
{
    public enum RigistResult
    {
        /// <summary>
        /// 成功
        /// </summary>
        Successed,
        /// <summary>
        /// 用户名已存在
        /// </summary>
        IdExist,
        /// <summary>
        /// Id过长
        /// </summary>
        IdTooLong,
        /// <summary>
        /// Id过短
        /// </summary>
        IdTooShort,
        /// <summary>
        /// 用户名 包含无效字符
        /// </summary>
        IdContainsInvalidChar,
        /// <summary>
        /// 两次密码不正确
        /// </summary>
        PasswordsNotMatch,
        /// <summary>
        /// 密码太短
        /// </summary>
        PasswordsTooShort,
        /// <summary>
        /// 密码太长
        /// </summary>
        PasswordsTooLong,
        /// <summary>
        /// 密码过于简单
        /// </summary>
        PasswordsTooSimple,
        /// <summary>
        /// 密码包含无效字符
        /// </summary>
        PassWordsContainsInvalidChar,
        /// <summary>
        /// 验证码不正确
        /// </summary>
        VarufyCodeDoesNotMatch,
        
    }
}
