package usung.com.mqttclient.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 *
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
}
