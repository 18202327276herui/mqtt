namespace InstantMessaging.Core.Models.Public.Http
{
    /// <summary>
    /// 返回值为 bool 远程调用结果
    /// </summary>
    public  class BoolResult:HttpResposeDataBase
    {
        public bool Result { get; set; }
    }
}
