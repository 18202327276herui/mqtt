package usung.com.mqttclient.bean.db;

import java.io.Serializable;

/**
 * @author herui
 * @date 2019/1/29
 */

public class FileDescription implements Serializable {
    /// <summary>
    ///  oss 上的 key
    /// </summary>
    public String Key;
    /// <summary>
    /// 文件长度
    /// </summary>
    public long Length;
    /// <summary>
    /// 文件名
    /// </summary>
    public String FileName;

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public long getLength() {
        return Length;
    }

    public void setLength(long length) {
        Length = length;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }
}
