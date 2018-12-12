using Newtonsoft.Json;

namespace InstantMessaging.Core.Models.Public.Http
{

    #region http请求参数类型集合.............................

    /// <summary>
    /// 请求参数 用于身份确认必要的参数
    /// 
    /// userid =“001” 和 token="001" 是测试数据，磨人都能通过验证
    /// </summary>
    public class HttpRequestParamerBase
    {
        public string Token { get; set; } = "001";
        public string SelfId { get; set; } = "001";

    }
 


    

    #endregion

    #region Http返回结果类型集合
  

  
    
  






    #endregion
}

