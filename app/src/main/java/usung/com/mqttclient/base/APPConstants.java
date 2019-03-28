package usung.com.mqttclient.base;

/**
 * Created by herui on 2018/12/12.
 */

public class APPConstants {
    public static final String SERVER_URL = "http://192.168.0.61:10336/";
//    public static final String SERVER_URL = "http://192.168.0.173:10336/";
    //    public static final String SERVER_URL = "http://192.168.0.43:10336/";
    public static final int UPDATE_RECYCLEVIEW = 1;
    public static final int UPDATE_RECYCLEVIEW_IMG = 2;

    public static final int TYPE_LEFT_TEXT = 1;
    public static final int TYPE_RIGHT_TEXT = 2;
    public static final int TYPE_LEFT_IMAGE = 3;
    public static final int TYPE_RIGHT_IMAGE = 4;
    public static final int TYPE_LEFT_VOICE = 5;
    public static final int TYPE_RIGHT_VOICE = 6;

    /**
     * 登录账号的保存
     */
    public static final String SHARE_LOGIN_NAME_LIST_RECORD = "loginNames";
    /**
     * 账号和未加密的密码
     */
    public static final String SHARE_LOGIN_NAME_AND_PAW = "loginInfo";
    /**
     * 账号
     */
    public static final String SHARE_LOGIN_NAME = "name";
    /**
     * 密码
     */
    public static final String SHARE_LOGIN_PWD = "pwd";

    /**
     * 图片选择返回RESULT
     */
    public static final int RESULT_LOAD_IMAGE = 1;

    /**
     * 消息类型
     */
    public static final int MESSAGE_TYPE_TEXT = 0;
    public static final int MESSAGE_TYPE_VOICE = 1;
    public static final int MESSAGE_TYPE_IMAGE = 3;
    public static final int MESSAGE_TYPE_MSG_ACK = 22;
    public static final int MESSAGE_TYPE_USER_STATE_CHANGED = 23;
}
