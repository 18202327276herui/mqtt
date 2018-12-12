namespace InstantMessaging.Core.Models.Public.Http
{
    public   class LoginResultData:HttpResposeDataBase
    {
        /// <summary>
        /// 登录结果
        /// </summary>
        public LoginResult Result { get; set; }
        /// <summary>
        /// Token
        /// </summary>
        public string Token { get; set; }
        /// <summary>
        /// mqtt 登录参数
        /// </summary>
        public MqttLoginCredential MqttCredencial { get; set; }
        /// <summary>
        /// oss 登录参数
        /// </summary>
        public OssLoginCredencial OssCredencial { get; set; }
        /// <summary>
        /// db 地址 ip+端口
        /// </summary>
        public string DBHost { get; set; }
        /// <summary>
        /// topic 地址 ip+端口
        /// </summary>
        public string TopicHost { get; set; }
        /// <summary>
        /// group storage 地址 ip+端口
        /// </summary>
        public string GroupStorageHost { get; set; }

        
    }
}
