package usung.com.mqttclient.utils;

import android.content.Context;

import usung.com.mqttclient.bean.user.LoginResultData;

public class UserUtil {
    public static final String SHARE_USER = "user";

    /**
     * 存入用户数据
     */
    public static void putUser(Context context, LoginResultData loginResultData) {
        SharePreferenceUtil spUtil = new SharePreferenceUtil(context);
        spUtil.savePreference(SHARE_USER, SHARE_USER, GsonHelper.getGson().toJson(loginResultData));
    }

    /**
     * 清除用户数据
     */
    public static void cleanUser(Context context) {
        SharePreferenceUtil spUtil = new SharePreferenceUtil(context);
        spUtil.removePreference(SHARE_USER, SHARE_USER);
    }

    /**
     * 获取用户数据
     */
    public static LoginResultData getUser(Context context) {
        SharePreferenceUtil spUtil = new SharePreferenceUtil(context);
        String user = spUtil.getPreference(SHARE_USER, SHARE_USER);
        return GsonHelper.getGson().fromJson(user, LoginResultData.class);
    }
}
