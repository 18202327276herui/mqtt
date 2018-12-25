package usung.com.mqttclient.bean;

import java.io.Serializable;

/** Http 返回数据
 * Created by herui on 2018/12/13.
 */

public class HttpResposeDataBase implements Serializable {
    public static final int SUCESSED = 200;
    public static final int FAILED = 400;
    public static final int IDENTIFYFAILED = 401;
    public static final int SERVERERROR = 500;
    public static  final int PARAMETERINCORRECT = 402;
    public static  final int NORIGHT = 403;
    public static  final int UNKNOEW = 404;
    public static  final String SUCCESSED_MESSAGE = "请求成功";
    public static  final String FAILED_MESSAGE = "请求失败";
    public static  final String IDENTIFYFAILED_MESSAGE = "用户未登录，或者Token已失效";
    public static  final String PARAMETERINCORRECT_MESSAGE = "参数不正确";
    public static  final String UNKNOEW_MESSAGE = "未知错误";
    public static  final String SERVERERROR_MESSAGE = "服务器错误，请与管理员联系";
    public static  final String NORIGHT_MESSAGE = "没有进行这个操作的权限";

    /// <summary>
    ///     返回: 200=成功 400=失败,401=未登录，404=未找到 402=参数不对,500=系统内部错误，其它=错误码
    /// </summary>

    public int Code;
    /// <summary>
    /// 信息提示
    /// </summary>
    public String Message;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
