package usung.com.mqttclient.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * 自定义mqtt消息
 * @author herui
 * @date 2018/12/12
 */

public class HrMqttMessage implements Serializable {
    /**
     *  消息文本
     */
    private String text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

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
}
