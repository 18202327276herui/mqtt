package usung.com.mqttclient;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import usung.com.mqttclient.adapter.AdapterChatRecyclerView;
import usung.com.mqttclient.base.APPConstants;
import usung.com.mqttclient.base.BaseActivity;
import usung.com.mqttclient.base.MqttHelper;
import usung.com.mqttclient.bean.HrMqttMessage;
import usung.com.mqttclient.bean.user.LoginParameter;
import usung.com.mqttclient.bean.user.LoginResultData;
import usung.com.mqttclient.http.base.Api;
import usung.com.mqttclient.http.observers.NoBaseResultObserver;

/**
 * @author herui
 */
public class ActivityChat extends BaseActivity {
    @BindView(R.id.header_title)
    TextView headerTitle;
    @BindView(R.id.et_message)
    EditText etMessage;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.rv_listview)
    RecyclerView mRecyclerView;
    private AdapterChatRecyclerView adapterChatRceyclerView;
    private List<HrMqttMessage> messageLists = new ArrayList<>();
    private MqttHelper mqttHelper;
    private MqttAndroidClient mqttAndroidClient;
    final String subscriptionTopic = "369";
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APPConstants.UPDATE_RECYCLEVIEW:
                    Bundle bundle = msg.getData();
                    HrMqttMessage hrMqttMessage = new HrMqttMessage();
                    hrMqttMessage.setText("Message Arreved: " + subscriptionTopic + " : " +  bundle.get("data"));
                    hrMqttMessage.setType(APPConstants.TYPE_LEFT);
                    messageLists.add(hrMqttMessage);
                    adapterChatRceyclerView.notifyDataSetChanged();
                    mRecyclerView.smoothScrollToPosition(messageLists.size());
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        mqttHelper = MqttHelper.getInstance(getActivity());
        mqttAndroidClient = mqttHelper.getMqttAndroidClient();
        initViews();

        LoginParameter loginParameter = new LoginParameter();
        loginParameter.setUserId("001");
        loginParameter.setPassWord("001");
        Api.getApiService().login(loginParameter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NoBaseResultObserver<LoginResultData>(getActivity()) {
                    @Override
                    public void onResponse(LoginResultData loginResultData) {
                        Log.e("test", "000");
                    }
                });
//        RegisteParameter registeParameter = new RegisteParameter();
//        registeParameter.setUserId("001");
//        registeParameter.setNickName("test");
//        registeParameter.setFirstPassWords("001");
//        Api.getApiService().registe(registeParameter)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new CommonObserver<RegistResultData>(getActivity()) {
//                    @Override
//                    public void onSuccess(RegistResultData registResultData, String msg, int error, int total) {
//                        Log.e("test", "000");
//
//                    }
//
//                    @Override
//                    public void onFailure(String msg, int error, int total) {
//
//                    }
//                });
    }

    @Override
    public void initViews() {
        super.initViews();

        String json = "{\"Result\":0,\"Token\":\"f0a29f2a-00d8-4c49-bf7f-4eb5d9b9b35f\",\"MqttCredencial\":{\"ClientId\":\"6dff775f-6ec2-459e-92ed-dc29d3301a46\",\"UserId\":null,\"Password\":null,\"Host\":\"192.168.0.61\",\"SslPort\":8883,\"Certification\":\"LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUN4akNDQWE2Z0F3SUJBZ0lKQUprMURiWkJ1OEZETUEwR0NTcUdTSWIzRFFFQkN3VUFNQk14RVRBUEJnTlYKQkFNTUNFMTVWR1Z6ZEVOQk1CNFhEVEUzTVRFd01qRXpOREkwTjFvWERURTVNVEV3TWpFek5ESTBOMW93RXpFUgpNQThHQTFVRUF3d0lUWGxVWlhOMFEwRXdnZ0VpTUEwR0NTcUdTSWIzRFFFQkFRVUFBNElCRHdBd2dnRUtBb0lCCkFRRHNoRGhvNmVmMUpDbERKMjRwZVNzWGRGbkZPM3hJQjcrQlNwMVlQY092bVJFQ0tVRzBtTE9SdzNoTm0xNW0KOGVHT24xaUxHRS94S2xhWjc0L3hqeXE4ZjdxSUdaQ212Wmo1OW0rZWlKQ0FteThTaVVKWnRTVm9PbE96ZXBKZApQb0RnY0J2REtBNG9nWjNpSkhNVU5JM0VkbEQ2bnJLRUpGMnFlMkpVckwwZ3Y2NXVvMi9ON1hWTnZFODdEazNKCjgzS3lDQW1ldSt4K21vUzFJTG5qczJEdVBFR1N4WnF6ZjdJUU1iWHVOV0pZQU9aZzl0NEZnMFlqVGlBYVd3M0cKSktBb01ZNHRJM0pDcWx2d0dSNGxIN2tmazNXc0Q0b2ZHbEZoeFU0bkVHMHhnbkpsOEJjb0pXRDFBMlJqR2UxZgpxQ2lqcVBTZTkzbDJ3dDhPcGJ5SHp3YzdBZ01CQUFHakhUQWJNQXdHQTFVZEV3UUZNQU1CQWY4d0N3WURWUjBQCkJBUURBZ0VHTUEwR0NTcUdTSWIzRFFFQkN3VUFBNElCQVFBaSt0NWpCck14RnpvRjc2a3lSZDNyaU5EbFdwMHcKTkNld2tvaEJrd0JIc1FmSHpTbmM2YzUwNGpkeXprRWlENDJVY0k4YXNQc0pjc1lyUStVbzZPQm4wNDl1NDlXbgp6Y1NFUlZTVmVjMS9UQVBTL2VnRlRVOVFNV3RQU0FtOEFFYVE2WVlBdWl3T0xDY0MrQ20vYTNlM2RXU1JXdDhvCkxxS1g2Q1dUbG1LV2UxODJNaEZQcFpZeFpRTEdhcHRpNFI0bWI1UXVzVWJjNnRYYmtjWDgyR2pEUFRPdUF3N2IKbVdwelZkNXhubHA3VnorNTB1K1lhQVlVbUNvYmcwaFIvQXVUckE0R0RNbGd6VG51WlFoRjZvOGlWa3lwWE90UwpVZno2WDN0VlZFclZWYzdVVWZ6U251cEhqMU0yaDRyemxRM29xSG9BRW5YY0ptVjRmL1BmLzZGVwotLS0tLUVORCBDRVJUSUZJQ0FURS0tLS0tCg==\"},\"OssCredencial\":{\"Id\":\"LTAIDUfq0iEzLZbH\",\"Key\":\"8VHQiVoLE9ymHluppyKfSy5GcB2eY0\",\"Bucket\":\"usung-chat-test\",\"Host\":\"\"},\"DBHost\":\"127.0.0.1\",\"TopicHost\":\"127.0.0.1\",\"GroupStorageHost\":\"127.0.0.1\",\"Code\":200,\"Message\":\"请求成功\"}";

        Gson gson = new Gson();
        LoginResultData loginResultData = gson.fromJson(json, LoginResultData.class);

        headerTitle.setText("Android开发交流 ");
        adapterChatRceyclerView = new AdapterChatRecyclerView(getActivity(), messageLists);
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(adapterChatRceyclerView);

        try {
            mqttAndroidClient.subscribe(subscriptionTopic, 0, new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    // message Arrived!
                    Log.e("Chat messageArrived", "Message: " + topic + " : " + new String(message.getPayload()));
                    Message message1 = new Message();
                    message1.what = APPConstants.UPDATE_RECYCLEVIEW;
                    Bundle bundle = new Bundle();
                    bundle.putString("data", new String(message.getPayload()));
                    message1.setData(bundle);
                    handler.sendMessage(message1);
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                HrMqttMessage hrMqttMessage = new HrMqttMessage();
                hrMqttMessage.setText(etMessage.getText().toString());
                hrMqttMessage.setType(APPConstants.TYPE_RIGHT);
                messageLists.add(hrMqttMessage);
                adapterChatRceyclerView.notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition(messageLists.size());
//                publishMessage = etMessage.getText().toString();
//                publishMessage();
                mqttHelper.publishMessage(etMessage.getText().toString());
                break;
            default:
                break;
        }
    }
}
