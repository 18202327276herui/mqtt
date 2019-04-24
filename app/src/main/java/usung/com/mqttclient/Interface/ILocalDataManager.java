package usung.com.mqttclient.Interface;

import java.util.List;
import java.util.stream.Stream;

import usung.com.mqttclient.bean.db.Message;

/**
 * /// <summary>
 * /// 提供 文件消息的管理功能
 * ///
 * /// </summary>
 * Created by herui on 2019/4/3.
 */

public interface ILocalDataManager {
    /// <summary>
    /// 用于目录标识
    /// </summary>
    long UserId = 0;
    /// <summary>
    /// 资源目录
    /// </summary>
    String ResourceFolder = "";

    /// <summary>
    /// 保存消息
    /// </summary>
    /// <param name="msg"></param>
    void SaveMessage(Message msg);

    /// <summary>
    /// 保存未读消息
    /// </summary>
    /// <param name="msg"></param>
    /// <returns></returns>
    int SaveUnreadMessage(Message msg);

    /// <summary>
    /// 删除某个会话的数据
    /// </summary>
    /// <param name="session">userid或者groupid</param>
    void RemoveSession(String session);

    /// <summary>
    /// 创建存储结构
    /// </summary>
    /// <param name="session"></param>
    void AddSession(String session);

    /// <summary>
    /// 是否包含会话
    /// </summary>
    /// <param name="session"></param>
    boolean ContainsSession(String session);

    /// <summary>
    /// 文件是否存在
    /// </summary>
    /// <param name="filename">文件名，相对于<see cref="ResourceFolder"/></param>
    /// <returns></returns>
    boolean FileExist(String filename);

    /// <summary>
    /// 文件是否存在且长度不为0
    /// </summary>
    /// <param name="filename">文件名，相对于<see cref="ResourceFolder"/></param>
    /// <returns></returns>
    boolean NonZeroFileExist(String filename);

    /// <summary>
    /// 通过文件名获取真实的本地地址全路径
    /// </summary>
    /// <param name="filename">文件名</param>
    /// <returns>存在返回全地址，失败返回null</returns>
    String GetFullPath(String filename);

    /// <summary>
    /// 保存文件
    /// </summary>
    /// <param name="filename"></param>
    /// <param name="stream"></param>
    void SaveFile(String filename, Stream stream);

    /// <summary>
    /// 标识消息已读
    /// </summary>
    /// <param name="msgid"></param>
    void SetReaded(String msgid);

    /// <summary>
    /// 标识消息已处理
    /// </summary>
    /// <param name="msgid"></param>
    void SetHanded(String msgid);

    /// <summary>
    /// 获取会话的全部历史消息
    /// </summary>
    /// <param name="session"></param>
    /// <returns></returns>
//    List<LocalMessage> GetAllMessage(String session);
    /// <summary>
    /// 获取全部未读消息
    /// </summary>
    /// <param name="session"></param>
    /// <returns></returns>
    List<Message> GetUnReadMessage(String session);

    /// <summary>
    /// 初始化 完成相关文件的创建
    /// </summary>
    void Init();

    /// <summary>
    /// 用于记录好友、群组请求结果
    /// </summary>
    /// <param name="msgid"></param>
    /// <param name="result"></param>
//    void SetRequestResult(String msgid, RequestResult result);
    /// <summary>
    /// 用于记录 上传下载结果
    /// </summary>
    /// <param name="msgid"></param>
    /// <param name="result"></param>
//    void SetTransferResult(String msgid, TransferResult result);
    /// <summary>
    /// 用于获取会话未读消息
    /// </summary>
    /// <param name="session"></param>
    /// <returns></returns>
    int GetUnreadMessageCount(String session);

    /// <summary>
    /// 获取 最近的 一条 群消息,用于给<see cref="IGroupMessageStorageManager.GetGroupMessageAfter(String, string)"/>
    /// 提供查询参数
    /// </summary>
    /// <param name="groupid"></param>
    /// <returns></returns>
    String GetLatestGroupMessageId(long groupid);

    /// <summary>
    /// 存储离线消息
    /// </summary>
    /// <param name="message"></param>
    void SaveOffLineMessage(Message message);

    /// <summary>
    /// 删除文件
    /// </summary>
    /// <param name="filename"></param>
    /// <returns></returns>
    void DeleteFile(String filename);
}
