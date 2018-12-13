package usung.com.mqttclient.bean.user;

/**
 * @author herui
 * @date 2018/12/13
 * Id = "LTAIDUfq0iEzLZbH"
 * key = "8VHQiVoLE9ymHluppyKfSy5GcB2eY0"
 * Bucket = "usung-chat-test"
 */

public class OssLoginCredencial {
    /// <summary>
    /// 用户名
    /// </summary>
    public String Id;
    /// <summary>
    /// 密匙
    /// </summary>
    public String Key;
    /// <summary>
    /// Bucket
    /// </summary>
    public String Bucket;
    /// <summary>
    /// 主机
    /// </summary>
    public String Host;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getBucket() {
        return Bucket;
    }

    public void setBucket(String bucket) {
        Bucket = bucket;
    }

    public String getHost() {
        return Host;
    }

    public void setHost(String host) {
        Host = host;
    }
}
