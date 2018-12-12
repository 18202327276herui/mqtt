namespace InstantMessaging.Core.Models.Public.Mqtt
{
    public enum ContentType : byte
    {
        /// <summary>
        /// 文字
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
        /// 视屏
        /// </summary>
        Vedio,
        /// <summary>
        /// 动图
        /// </summary>
        Gif,
        /// <summary>
        /// 文件
        /// </summary>
        File,
        /// <summary>
        /// 窗体抖动
        /// </summary>
        Shake,
        /// <summary>
        /// 空
        /// </summary>
        None,
    
    }
}
