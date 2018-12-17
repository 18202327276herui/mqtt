package usung.com.mqttclient.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

import usung.com.mqttclient.base.BaseApplication;


/**
 * toast工具类
 */
public class ToastUtil {
    private static Toast toast;

    /**
     * 显示资源文件string.xml里面的字段
     *
     * @param resId 消息资源
     */
    public static void showToast(int resId) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getInstance().getApplicationContext(), resId, Toast.LENGTH_SHORT);
        } else {
            toast.setText(BaseApplication.getInstance().getResources().getString(resId));
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 显示字符串消息
     *
     * @param message 消息内容
     */
    public static void showToast(String message) {
        if (!Thread.currentThread().getName().equals("main")) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> Toast.makeText(BaseApplication.getInstance().getApplicationContext(), message, Toast.LENGTH_SHORT).show());
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getInstance().getApplicationContext(), message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 长时间显示字符串消息
     *
     * @param message 消息内容
     */
    public static void showLongToast(String message) {
        if (!Thread.currentThread().getName().equals("main")) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> Toast.makeText(BaseApplication.getInstance().getApplicationContext(), message, Toast.LENGTH_LONG).show());
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getInstance().getApplicationContext(), message, Toast.LENGTH_LONG);
        } else {
            toast.setText(message);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    /**
     * 显示字符串消息
     *
     * @param resId 消息资源
     */
    public static void showToast(int resId, int timeType) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getInstance().getApplicationContext(), resId, Toast.LENGTH_SHORT);
        } else {
            toast.setText(BaseApplication.getInstance().getResources().getString(resId));
            toast.setDuration(timeType);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 显示字符串消息
     *
     * @param message 消息内容
     */
    public static void showToast(String message, int timeType) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getInstance().getApplicationContext(), message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
            toast.setDuration(timeType);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 显示资源文件string.xml里面的字段
     *
     * @param context  上下文
     * @param resId    消息资源
     * @param timeType 显示的时间长短：Toast.LENGTH_LONG 和 LENGTH_SHORT
     */
    public static void showToastResId(Context context, int resId, int timeType) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), resId, timeType);
        } else {
            toast.setText(context.getApplicationContext().getResources().getString(resId));
            toast.setDuration(timeType);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 显示字符串消息
     *
     * @param context  上下文
     * @param message  消息内容
     * @param timeType 显示的时间长短：Toast.LENGTH_LONG 和 LENGTH_SHORT
     */
    public static void showToastMessageString(Context context, String message, int timeType) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), message, timeType);
        } else {
            toast.setText(message);
            toast.setDuration(timeType);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
