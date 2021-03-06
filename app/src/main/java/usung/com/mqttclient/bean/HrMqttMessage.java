package usung.com.mqttclient.bean;

import android.graphics.Bitmap;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import usung.com.mqttclient.bean.db.Message;

/**
 * 自定义mqtt消息
 * @author herui
 * @date 2018/12/12
 */

public class HrMqttMessage extends Message implements Serializable {
    /**
     *  消息类型（发送==1，接收==2）
     */
    private int type;
    /**
     * 头像
     */
    private Bitmap icon;
    /**
     * 发送的图片
     */
    private Bitmap bitmap;
    /**
     *  语音文件
     */
    private File voiceFile;
    /**
     *  语音文件长度
     */
    private String voiceLength;
//    /**
//     *  发送时间
//     */
//    private Date sendDate;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public File getVoiceFile() {
        return voiceFile;
    }

    public void setVoiceFile(File voiceFile) {
        this.voiceFile = voiceFile;
    }

    public String getVoiceLength() {
        return voiceLength;
    }

    public void setVoiceLength(String voiceLength) {
        this.voiceLength = voiceLength;
    }

    //    public Date getSendDate() {
//        return sendDate;
//    }
//
//    public void setSendDate(Date sendDate) {
//        this.sendDate = sendDate;
//    }
}
