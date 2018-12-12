namespace InstantMessaging.Core.Models.Public.Http
{
    /// <summary>
    /// 查询主题接口，所需要的主题
    /// </summary>
    public  class QueryTopicParameter:HttpRequestParamerBase
    {
        /// <summary>
        /// 要查询的对象 ，用户Id或者qunid
        /// </summary>
        public string TargetId { get; set; }
        /// <summary>
        /// 凭据
        /// </summary>
        public OpereatCredencial Credencial { get; set; }

    }
}
