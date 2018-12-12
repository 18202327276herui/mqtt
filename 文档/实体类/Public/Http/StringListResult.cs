namespace InstantMessaging.Core.Models.Public.Http
{
    /// <summary>
    /// string 链表的返回值
    /// </summary>
    public class StringListResult : HttpResposeDataBase
    {
        public string[] Array { get; set; }
    }

}
