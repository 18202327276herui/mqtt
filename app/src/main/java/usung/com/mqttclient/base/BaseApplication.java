package usung.com.mqttclient.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.Stack;

public class BaseApplication extends Application {

    public static final String TAG = "BaseApplication";
    private static BaseApplication instance;
    private String SERVER_URL = APPConstants.SERVER_URL;

    /*********************
     * 设置全局的app的密码和用户名start
     ***************/
    private String Pwd;

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String pwd) {
        Pwd = pwd;
    }

    /********************* 设置全局的app的密码和用户名 end ***************/


    /**
     * 实例化一次
     */
    public static BaseApplication getInstance() {
        return instance;
    }

    public String getSERVER_URL() {
        return SERVER_URL;
    }

    public void setSERVER_URL(String SERVER_URL) {
        this.SERVER_URL = SERVER_URL;
    }

    //杀进程
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

//    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    Stack<Activity> activityStack = null;

    /**
     * 把Activity添加到栈中
     * @param activity
     */
    public void addActivity(Activity activity){
        if (activityStack == null){
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前activity
     * @return
     */
    public Activity getCurrentActivity(){
        return activityStack.lastElement();
    }

    /**
     * 结束当前activity
     */
    public void endCurrentActivity(){
        finishActivity(activityStack.lastElement());
    }

    /**
     * 结束指定的activity
     * @param activity
     */
    public void finishActivity(Activity activity){
        if (activity != null){
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public void finishActivity(Class<?> cls){
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)){
                finishActivity(activity);
            }
        }
    }

    /**
     * 清空activity栈
     */
    public void finishAllActivity(){
        if (activityStack != null && !activityStack.isEmpty()) {
            for (Activity activity : activityStack) {
                activity.finish();
            }
        }

        activityStack.clear();
    }

    /**
     * 退出应用
     */
    public void exitApp(){
        try {
            finishAllActivity();
        }catch (Exception e){
            Log.e("退出错误：", e.getMessage());
        }
    }
}
