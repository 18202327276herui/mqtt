package usung.com.mqttclient.utils;

import android.content.Context;

import usung.com.mqttclient.bean.db.InitiaDataResult;
import usung.com.mqttclient.bean.user.LoginResultData;

public class InitiadataUtil {
    public static final String SHARE_INITIADATA = "initiadata";

    /**
     * 存入初始化数据
     */
    public static void putInitiadata(Context context, InitiaDataResult initiaDataResult) {
        SharePreferenceUtil spUtil = new SharePreferenceUtil(context);
        spUtil.savePreference(SHARE_INITIADATA, SHARE_INITIADATA, GsonHelper.getGson().toJson(initiaDataResult));
    }

    /**
     * 清除初始化数据
     */
    public static void cleanInitiadata(Context context) {
        SharePreferenceUtil spUtil = new SharePreferenceUtil(context);
        spUtil.removePreference(SHARE_INITIADATA, SHARE_INITIADATA);
    }

    /**
     * 获取初始化数据
     */
    public static InitiaDataResult getInitiadata(Context context) {
        SharePreferenceUtil spUtil = new SharePreferenceUtil(context);
        String initiadata = spUtil.getPreference(SHARE_INITIADATA, SHARE_INITIADATA);
        return GsonHelper.getGson().fromJson(initiadata, InitiaDataResult.class);
    }
}
