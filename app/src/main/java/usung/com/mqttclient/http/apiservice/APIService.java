package usung.com.mqttclient.http.apiservice;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import usung.com.mqttclient.bean.user.LoginParameter;
import usung.com.mqttclient.bean.user.LoginResultData;
import usung.com.mqttclient.bean.user.RegistResultData;
import usung.com.mqttclient.bean.user.RegisteParameter;
import usung.com.mqttclient.http.base.BaseResult;

/**
 * 服务器API地址
 * Created by lifei on 2018/5/3.
 */

public interface APIService {

//    String baseUrl = BaseApplication.getInstance().getSERVER_URL();

    /**
     * 登录
     */
    @POST("login")
    Observable<LoginResultData> login(@Body LoginParameter body);
    /**
     * 注册
     */
    @POST("registe")
    Observable<RegistResultData> registe(@Body RegisteParameter body);
    /**
     * 更换token
     */
//    @POST("changetoken")
//    Observable<> changetoken(@Body );
//    /**
//     * 更换管理员
//     */
//    @POST("getadministrator")
//    Observable<> getadministrator(@Body );
}
