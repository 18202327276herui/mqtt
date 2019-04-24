package com.usung.mqttclient;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by herui on 2018/12/10.
 */

public class MqttHelper {
    private Context context;
    private static MqttHelper instance;
    /**
     * mqtt 相关
     */
    MqttAndroidClient mqttAndroidClient;
    //    final String serverUri = "tcp://iot.eclipse.org:1883";
    /**
     * 服务器地址
     */
    final String serverUri = "ssl://192.168.0.61:8883";
    /**
     * 客户端 id
     */
    private String clientId = "";
    /**
     * 订阅的主题
     */
    private String subscriptionTopic = "";

    public MqttHelper(Context context) {
        this.context = context;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSubscriptionTopic() {
        return subscriptionTopic;
    }

    public void setSubscriptionTopic(String subscriptionTopic) {
        this.subscriptionTopic = subscriptionTopic;
    }

    /**
     * 单例模式
     *
     * @param context
     * @return
     */
    public static MqttHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (MqttHelper.class) {
                if (instance == null) {
                    instance = new MqttHelper(context);
                }
            }
        }
        return instance;
    }

    /**
     * 获取mqtt客户端
     *
     * @return mqtt客户端
     */
    public MqttAndroidClient getMqttAndroidClient() {
        return mqttAndroidClient;
    }

    /**
     *  初始化mqtt
     * @param inputStream 证书输入流
     */
    public void initMqtt(InputStream inputStream) {
//        clientId = clientId + System.currentTimeMillis();

        mqttAndroidClient = new MqttAndroidClient(context.getApplicationContext(), serverUri, clientId);
        mqttAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                if (reconnect) {
                    addToHistory("Reconnected to : " + serverURI);
                    // Because Clean Session is true, we need to re-subscribe
                    subscribeToTopic();
                } else {
                    addToHistory("Connected to: " + serverURI);
                }
            }

            @Override
            public void connectionLost(Throwable cause) {
                addToHistory("The Connection was lost.");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                addToHistory("Incoming message: " + new String(message.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });

        // mqtt连接设置
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        // 自动重连
        mqttConnectOptions.setAutomaticReconnect(true);
        // 清除session
        mqttConnectOptions.setCleanSession(true);
        // 设置证书
        mqttConnectOptions.setSocketFactory(getSSLSocketFactory(inputStream));
//        mqttConnectOptions.setUserName("001");
//        mqttConnectOptions.setPassword("001".toCharArray());

        try {
            addToHistory("Connecting to " + serverUri);
            mqttAndroidClient.connect(mqttConnectOptions, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // 连接成功
                    DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();
                    disconnectedBufferOptions.setBufferEnabled(true);
                    disconnectedBufferOptions.setBufferSize(100);
                    disconnectedBufferOptions.setPersistBuffer(false);
                    disconnectedBufferOptions.setDeleteOldestMessages(false);
                    mqttAndroidClient.setBufferOpts(disconnectedBufferOptions);
                    subscribeToTopic();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // 连接失败
                    addToHistory("Failed to connect to: " + serverUri);
                }
            });

        } catch (MqttException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *  打印消息
     * @param mainText 消息
     */
    public void addToHistory(String mainText) {
        Log.e("test", mainText);
        Snackbar.make(((Activity) context).findViewById(android.R.id.content), mainText, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    /**
     * 订阅主题
     */
    public void subscribeToTopic() {
        try {
            mqttAndroidClient.subscribe("/Im/" + subscriptionTopic, 0, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    addToHistory("Subscribed!");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    addToHistory("Failed to subscribe");
                }
            });

            mqttAndroidClient.subscribe("/Im/" + subscriptionTopic, 0, new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    // message Arrived! 收到消息
                    Log.e("mqtt messageArrived", "Message: " + topic + " : " + new String(message.getPayload()));
                }
            });

        } catch (MqttException ex) {
            System.err.println("Exception whilst subscribing");
            ex.printStackTrace();
        }
    }

    /**
     *  发送消息
     * @param publishMessage 消息
     * @param topic 主题
     */
    public void publishMessage(String publishMessage, String topic) {
        try {
            // 构造message消息
            MqttMessage message = new MqttMessage();
            // 设置发送消息的字节数组
            message.setPayload(publishMessage.getBytes());
            // 发送消息
            mqttAndroidClient.publish(topic, message);
//            addToHistory("Message Published");
            Log.e("Message Published", "send");
            if (!mqttAndroidClient.isConnected()) {
                addToHistory(mqttAndroidClient.getBufferedMessageCount() + " messages in buffer.");
            }
        } catch (MqttException e) {
            System.err.println("Error Publishing: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 获取SSLSocketFactory
     *
     * @return SSLSocketFactory
     */
    public SSLSocketFactory getSSLSocketFactory(InputStream inputStream) {
        SSLContext sslContext = null;
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            String certificateAlias = Integer.toString(0);
            //拷贝好的证书
            keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(inputStream));
            sslContext = SSLContext.getInstance("TLSv1");
            final TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init
                    (
                            null,
                            trustManagerFactory.getTrustManagers(),
                            new SecureRandom()
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }

        final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        return sslSocketFactory;
    }

    /**
     *  销毁context引用
     * @param context
     */
    public static void destroy(Context context) {
        if (instance != null && context == instance.context) {
            instance.context = null;
        }
    }
}
