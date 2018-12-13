package usung.com.mqttclient.http.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import usung.com.mqttclient.base.BaseApplication;
import usung.com.mqttclient.utils.NetWorkUtil;

/**
 * 网络对象层
 */
public class BaseApi {

    String TAG = "fenghui";
    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 30000;
    //    public static final int READ_TIME_OUT = 7676;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 30000;
//    public static final int CONNECT_TIME_OUT = 7676;

    /**
     * 无超时及缓存策略的Retrofit
     *
     * @param baseUrl
     * @return retrofit
     */
    public Retrofit getSimpleRetrofit(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())//请求结果转换为基本类型，一般为String
                .addConverterFactory(GsonConverterFactory.create())//请求的结果转为实体类
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//适配RxJava2.0,
                // RxJava1.x则为RxJavaCallAdapterFactory.create()
                .build();
        return retrofit;
    }

    /**
     * 使用OkHttp配置了超时及缓存策略的Retrofit
     *
     * @param baseUrl
     * @return retrofit
     */
    public Retrofit getRetrofit(String baseUrl) {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存
        File cacheFile = new File(BaseApplication.getInstance().getApplicationContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        //增加头部信息
        Interceptor headerInterceptor = chain -> {
            Request build = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")//设置允许请求json数据
                    .build();
            return chain.proceed(build);
        };

        //创建一个OkHttpClient并设置超时时间
        OkHttpClient client = getUnSafeBuilder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new ReceivedCookiesInterceptor(BaseApplication.getInstance()))
                .addInterceptor(new AddCookiesInterceptor(BaseApplication.getInstance()))
                .addInterceptor(REWRITE_RESPONSE_INTERCEPTOR_OFFLINE)//没网的情况下
                .addNetworkInterceptor(REWRITE_RESPONSE_INTERCEPTOR)//有网的情况下
                .addInterceptor(headerInterceptor)
                .addInterceptor(logInterceptor)
                .cache(cache)

                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())//请求结果转换为基本类型，一般为String
                .addConverterFactory(GsonConverterFactory.create())//请求的结果转为实体类
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//适配RxJava2.0,
                // RxJava1.x则为RxJavaCallAdapterFactory.create()
                .build();
        return retrofit;
    }


    public class AddCookiesInterceptor implements Interceptor {
        private Context context;

        public AddCookiesInterceptor(Context context) {
            super();
            this.context = context;

        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            if (chain == null)
                Log.d("http", "Addchain == null");
            final Request.Builder builder = chain.request().newBuilder();
            SharedPreferences sharedPreferences = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
            Observable.just(sharedPreferences.getString("cookie", ""))
                    .subscribe(cookie -> {
                        //添加cookie
//                        Log.d("http", "AddCookiesInterceptor"+cookie);
                        builder.addHeader("cookie", cookie);
                    });
            return chain.proceed(builder.build());
        }
    }

    private Response createNewResponse(Response response, String content, int code) {
        return response.newBuilder().body(ResponseBody.create(response.body().contentType(), content)).code(code).build();
    }

    public class ReceivedCookiesInterceptor implements Interceptor {
        SharedPreferences sharedPreferences;
        Context context;

        public ReceivedCookiesInterceptor(Context context) {
            super();
            this.context = context;
            sharedPreferences = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            if (chain == null)
                Log.d("http", "Receivedchain == null");
            Response originalResponse = chain.proceed(chain.request());
            String content = originalResponse.body().string();
            int code = originalResponse.code();
            String url = originalResponse.request().url().toString();
            /*if (code == 401) {
//                CrashReport.postCatchedException(new Throwable("message=====>" + content
//                        + ",url=====>" + url + ",code ====>" + code));
                ContextCompat.startActivity(context, new Intent(".activity.user.ActivityLogin").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("exit", true), null);
                //若出现一次401 直接将cookie删除 用户删除  推送解绑
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("cookie");
                editor.commit();
                UserUtil.cleanUser(context);
                PushManager.stopWork(context);
                return createNewResponse(originalResponse, content, code);
            }*/
            if (code != 200) {
//                CrashReport.postCatchedException(new Throwable("return message is=====>" + content
//                        + "request url is =====>" + url + "request code is ====>" + code));
                return createNewResponse(originalResponse, content, code);
            }
            if (!originalResponse.headers("set-cookie").isEmpty()) {
                final StringBuffer cookieBuffer = new StringBuffer();
                String privateCookie = sharedPreferences.getString("cookie", "");

                //获取原来的cookie的key(当这个值出现新值的时候好覆盖)
                String[] privateCookiedata = privateCookie.equals("") ? new String[0] : privateCookie.split(";");
                Map<String, String> cookies = new HashMap<>();
                for (String s : privateCookiedata) {
                    cookies.put(s.split("=", 0)[0], s.split("=", 0)[1]);
                }
                Observable.fromIterable(originalResponse.headers("set-cookie"))
                        .map(s -> {
                            String[] cookieArray = s.split(";");
                            return cookieArray[0];
                        })
                        .subscribe(cookie -> cookies.put(cookie.split("=", 0)[0], cookie.split("=", 0)[1]), new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
//                                ToastUtil.Companion.showToast("Cookie设置失败");
                            }
                        }, () -> {
                            for (String cookie : cookies.keySet()) {
                                cookieBuffer.append(cookie + "=" + cookies.get(cookie) + ";");
                            }
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("cookie", cookieBuffer.toString());
                            Log.d("http", "ReceivedCookiesInterceptor" + cookieBuffer.toString());
                            editor.commit();
                        });
            }

            return createNewResponse(originalResponse, content, code);
        }
    }

    private OkHttpClient.Builder getUnSafeBuilder() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final javax.net.ssl.SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private OkHttpClient.Builder getSBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            String certificateAlias = Integer.toString(0);
            keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(BaseApplication.getInstance().getResources().getAssets().open("usung.cer")));//拷贝好的证书
            SSLContext sslContext = SSLContext.getInstance("TLS");
            final TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init
                    (
                            null,
                            trustManagerFactory.getTrustManagers(),
                            new SecureRandom()
                    );
            builder.sslSocketFactory(sslContext.getSocketFactory());
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder;
    }

    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor REWRITE_RESPONSE_INTERCEPTOR = chain -> {
        Response originalResponse = chain.proceed(chain.request());
        String cacheControl = originalResponse.header("Cache-Control");
        if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=" + CACHE_STALE_SEC)
                    .build();
        } else {
            return originalResponse;
        }
    };

    private final Interceptor REWRITE_RESPONSE_INTERCEPTOR_OFFLINE = chain -> {
        Request request = chain.request();
        if (!NetWorkUtil.isNetConnected(BaseApplication.getInstance())) {
            request = request.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached")
                    .build();
        }
        Response response = chain.proceed(request);
//        if (response.code() == 504) {
//            ToastUtil.showToast("请检查网络");
//            return response;
//        }
        return response;
    };
}