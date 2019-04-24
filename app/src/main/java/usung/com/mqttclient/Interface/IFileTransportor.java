package usung.com.mqttclient.Interface;

/**
 /// <summary>
 /// 提供文件上传下载功能
 ///
 /// ftp 异步上传和下载的回调，
 /// c# 有委托这种东西，即函数指针，
 /// 以下的这个东西可以用一个接口或者抽象类来代替
 ///每传输一段数据回调一次，用于监测当前传输任务的进度，速度
 ///如果该回调返回 true 继续进行任务，否则 结束任务
 /// </summary>
 * Created by herui on 2019/4/3.
 */

//public delegate bool TransferedPartCallback(TransferPartInfo info);
public interface IFileTransportor
{
    /// <summary>
    /// 异步上传文件
    /// </summary>
    /// <param name="loacal"></param>
    /// <param name="remote"></param>
    /// <param name="handler">
    /// 回调函数
    /// <see cref="TransferPartInfo"/><see cref="TransferedPartCallback"/>
    /// </param>
//    void UploadAsync(String loacal, String remote, TransferedPartCallback handler, String bucket);
    /// <summary>
    ///
    /// </summary>
    /// <param name="local"></param>
    /// <param name="remote"></param>
    /// <param name="finishedCallback"></param>
//    void UploadAsync(String local, String remote, Action<boolean> finishedCallback, String bucket);
    /// <summary>
    /// 同步上传
    /// </summary>
    /// <param name="loacal"></param>
    /// <param name="remote"></param>
    /// <returns></returns>
//    FtpResult Upload(String local, String remote, String bucket);
    /// <summary>
    /// 异步下载文件
    /// </summary>
    /// <param name="local"></param>
    /// <param name="remote"></param>
    /// <param name="handler"></param>
//    void DownloadAsyn(String local, String remote, TransferedPartCallback handler, String bucket);

    /// <summary>
    /// /
    /// </summary>
    /// <param name="local"></param>
    /// <param name="remote"></param>
    /// <param name="finishedCallback"></param>
//    void DownloadAsync(String local, String remote, Action<bool> finishedCallback, String bucket);
    /// <summary>
    /// 同步下载文件
    /// </summary>
    /// <param name="local"></param>
    /// <param name="remote"></param>
    /// <returns></returns>
//    FtpResult Download(String local, String remote, String bucket);
    /// <summary>
    /// 文件是否存在
    /// </summary>
    /// <param name="filepath"></param>
    /// <returns></returns>
//    FtpResult FileExist(String filepath, String bucket);
    /// <summary>
    /// 获取服务器某个文件夹的所有文件
    /// </summary>
    /// <param name="folder"></param>
    /// <returns></returns>
//    FtpResult ListFile(String folder, String bucket);
    /// <summary>
    /// 删除文件
    /// </summary>
    /// <param name="filename"></param>
    /// <returns></returns>
//    FtpResult DeleteFile(String filename, String bucket);
}
