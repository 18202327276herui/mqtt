package usung.com.mqttclient.Interface;

import usung.com.mqttclient.bean.user.DeviceType;
import usung.com.mqttclient.bean.user.LoginResultData;
import usung.com.mqttclient.bean.user.RegistResultData;

/**
 *  访问 login服务
 * Created by herui on 2019/4/2.
 */

public interface ILoginVisitor {
    /**
     *  改变Token
     * @param userid 用户id
     * @param oldToken 旧的Token
     * @return 新的Token
     */
    String changeToken(String userid, String oldToken);
    /**
     *  登录
     * @param userid 用户id
     * @param password 用户密码
     * @param type 设备类型
     * @return 登录结果
     */
    LoginResultData login(long userid, String password, DeviceType type);
    /**
     *  注册
     * @param userid 用户id
     * @param password 用户密码
     * @param nickName 昵称
     * @return 注册结果
     */
    RegistResultData register(long userid, String password, String nickName);
//    BotInfo GetAdmin(String oldAdmin, long userid, String token);
}
