package usung.com.mqttclient.http.base;


import usung.com.mqttclient.base.BaseApplication;
import usung.com.mqttclient.http.apiservice.APIService;

/**
 * @description retrofit适配RxJava，使用Rxjava并通过Retrofit调用api
 */
public class Api {
    private volatile static APIService apiService;

    private Api() {
        BaseApi baseApi = new BaseApi();
        apiService = baseApi.getRetrofit(BaseApplication.getInstance().getSERVER_URL()).create(APIService.class);
    }

    public static APIService getApiService() {
        if (apiService == null) {
            synchronized (Api.class) {
                if (apiService == null) {
                    new Api();
                }
            }
        }
        return apiService;
    }
}