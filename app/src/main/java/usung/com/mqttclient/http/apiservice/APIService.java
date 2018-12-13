package usung.com.mqttclient.http.apiservice;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import usung.com.mqttclient.bean.user.LoginParameter;
import usung.com.mqttclient.bean.user.LoginResultData;
import usung.com.mqttclient.http.base.BaseResult;

/**
 * 服务器API地址
 * Created by lifei on 2018/5/3.
 */

public interface APIService {

//    String baseUrl = BaseApplication.getInstance().getSERVER_URL();

//    @POST("api/Biz/Login/Signin")
//    Observable<BaseResult<User>> login(@Body LoginBody body);

//    @POST("api/Biz/Login/Register")
//    Observable<BaseResult<String>> register(@Body RegisterBody body);

    /**
     * 登录
     */
    @POST("login")
    Observable<BaseResult<LoginResultData>> login(@Body LoginParameter body);
//
//    /**
//     * 检查手机号是否可注册
//     */
//    @GET("api/App/User/CheckPhoneCanUse")
//    Observable<BaseResult<Object>> CheckPhoneCanUse(@Query("phoneNum") String phone);
//
//    /**
//     * 注册
//     */
//    @POST("api/App/User/Register")
//    Observable<BaseResult<Object>> Register(@Body RegisterBody body);
//
//    /**
//     * 发送验证码
//     */
//    @GET("api/App/User/SendPhoneCode")
//    Observable<BaseResult<Object>> SendPhoneCode(@Query("phoneNum") String phone);
//
//    /**
//     * 找回密码
//     */
//    @FormUrlEncoded
//    @POST("api/App/User/ResetPwd")
//    Observable<BaseResult<Object>> ResetPwd(@Field("PhoneNum") String phone,
//                                            @Field("VerifyCode") String code,
//                                            @Field("Password") String pwd);
//
//    /**
//     * 获取常用地址
//     *
//     * @param id        地址id，可不传
//     * @param objId     关联id，此处传userId
//     * @param isDefault 是否只获取默认地址
//     */
//    @POST("api/App/Address/GetAddress")
//    @FormUrlEncoded
//    Observable<BaseResult<ArrayList<EpAddress>>> GetAddress(@Field("Id") String id, @Field("ObjectId") String objId, @Field("IsDefalut") boolean isDefault);
//
//    /**
//     * 新增（编辑）地址
//     */
//    @POST("api/App/Address/CreateAddress")
//    Observable<BaseResult<Object>> CreateAddress(@Body CreateAddressBody body);
//
//    /**
//     * 删除地址
//     */
//    @POST("api/App/Address/DeleteAddress")
//    Observable<BaseResult<Object>> DeleteAddress(@Body DeleteAddressBody body);
//
//    /**
//     * 设置为默认地址
//     */
//    @FormUrlEncoded
//    @POST("api/App/Address/SetDefaultAddress")
//    Observable<BaseResult<Object>> SetDefaultAddress(@Field("AddressId") String addressId);
//
//    /**
//     * 获取省份列表
//     */
//    @GET("api/App/Address/GetProvinceList")
//    Observable<BaseResult<ArrayList<Province>>> GetProvinceList();
//
//    /**
//     * 获取城市列表
//     */
//    @GET("api/App/Address/GetCityList")
//    Observable<BaseResult<ArrayList<City>>> GetCityList(@Query("provinceId") String provinceId);
//
//    /**
//     * 获取区域列表
//     */
//    @GET("api/App/Address/GetDistrictList")
//    Observable<BaseResult<ArrayList<District>>> GetDistrictList(@Query("cityId") String cityId);
//
//    /**
//     * 创建订单
//     */
//    @POST("api/App/PointShop/CreateOrder")
//    Observable<BaseResult<ArrayList<PointShopOrderInfoList>>> CreateOrder(@Body CreateOrderBody body);
//
//    /**
//     * 支付（获取支付宝（微信）支付详情，以便调取支付宝（微信）sdk）
//     */
//    @POST("api/App/PointShop/BatchPayPointShopOrders")
//    Observable<BaseResult<PartnerOrder>> BatchPayPointShopOrders(@Body BatchPayPointShopOrdersBody body);
//
//    /**
//     * 获取微信支付参数
//     */
//    @GET("api/App/WeiXin/GetAppWeiXinPayRequest")
//    Observable<BaseResult<WxAppPayRequest>> GetAppWeiXinPayRequest(@Query("prepay_id") String id);
//
//    @GET("api/App/ScQrCode/CheckECT")
////    @GET("CheckECT")
//    Observable<BaseResult<Object>> checkECT(@Query("id") String id);
//
//    @POST("api/App/PointShop/SearchPointShopOrderList")
//    @FormUrlEncoded
//    Observable<BaseResult<ArrayList<PointShopOrderInfoList>>> SearchPointShopOrderList(@Field("OrgId") String OrgId,
//                                                                                       @Field("PayAccountType") int PayAccountType,
//                                                                                       @Field("Cat") int Cat,
//                                                                                       @Field("OrderStauts") int OrderStauts,
//                                                                                       @Field("PayAccount") int PayAccount,
//                                                                                       @Field("pageIndex") int pageIndex,
//                                                                                       @Field("pageSize") int pageSize);
}
