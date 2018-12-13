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
import usung.com.mqttclient.http.base.BaseResult;
import usung.com.mqttclient.http.observers.CommonObserver;
import usung.com.mqttclient.http.observers.NormalObserver;

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
                .subscribe(new CommonObserver<LoginResultData>(getActivity()) {
                    @Override
                    public void onSuccess(LoginResultData loginResultData, String msg, int error, int total) {
                        Log.e("test", "000");
                    }

                    @Override
                    public void onFailure(String msg, int error, int total) {

                    }
                });
    }

    @Override
    public void initViews() {
        super.initViews();

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
