package usung.com.mqttclient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import usung.com.mqttclient.ActivityChat;
import usung.com.mqttclient.R;
import usung.com.mqttclient.activity.ActivityFriendInfo;
import usung.com.mqttclient.adapter.AdapterMainRecyclerView;
import usung.com.mqttclient.base.APPConstants;
import usung.com.mqttclient.base.BaseFragment;
import usung.com.mqttclient.base.MqttHelper;
import usung.com.mqttclient.bean.HrMqttMessage;
import usung.com.mqttclient.bean.db.HistoryMessage;
import usung.com.mqttclient.bean.db.InitiaDataResult;
import usung.com.mqttclient.bean.user.UserStateInfo;
import usung.com.mqttclient.utils.GsonHelper;
import usung.com.mqttclient.utils.InitiadataUtil;
import usung.com.mqttclient.utils.TimeHelper;
import usung.com.mqttclient.utils.ToastUtil;

/**
 * 主页 -- 首页
 *
 * @author herui
 * @date 2018/12/10
 */

public class FragmentHome extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.header_title)
    TextView headerTitle;
    @BindView(R.id.backButton)
    RelativeLayout backButtonView;
    @BindView(R.id.rightButton)
    Button rightButton;
    /**
     * 适配器
     */
    private AdapterMainRecyclerView adapterMainRceyclerView;
    /**
     * 历史消息
     */
    private List<HistoryMessage> historyMessageList = new ArrayList<>();
    /**
     * 初始化数据
     */
    private InitiaDataResult initiaDataResult;
    /**
     * mqtt客户端
     */
    private MqttAndroidClient mqttAndroidClient;
    /**
     * mqtt帮助类
     */
    private MqttHelper mqttHelper;
    /**
     * 订阅的主题
     */
    private String subscriptionTopic = "";

    public static FragmentHome newInstance(Bundle bundle) {
        FragmentHome f = new FragmentHome();
        f.setArguments(bundle);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, homeView);
        initViews();
        return homeView;
    }

    @Override
    protected void initViews() {
        headerTitle.setText("首页");
        backButtonView.setVisibility(View.GONE);
        // 初始化数据
        initiaDataResult = InitiadataUtil.getInitiadata(getActivity());
        // mqtt帮助类
        mqttHelper = MqttHelper.getInstance(getActivity());
        // mqtt客户端
        mqttAndroidClient = mqttHelper.getMqttAndroidClient();
        // 订阅的主题
        subscriptionTopic = initiaDataResult.getData().getSelfTopic();

        try {
            historyMessageList = LitePal.where("senderid = ?", initiaDataResult.getData().getSelfInfo().getId() + "").find(HistoryMessage.class);
        } catch (Exception e) {
            Log.i("test", "00");
        }
        adapterMainRceyclerView = new AdapterMainRecyclerView(getActivity(), historyMessageList);
        // 配置RceyclerView
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(manager);
        rvList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rvList.setAdapter(adapterMainRceyclerView);
        // RceyclerView点击事件
        adapterMainRceyclerView.setListener(new AdapterMainRecyclerView.onItemClickListener() {
            @Override
            public void onItemClick(View view, String recipientId) {
                startActivity(new Intent(getActivity(), ActivityChat.class).putExtra("recipientId", recipientId));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 延迟2秒
                    Thread.sleep(2000);
                    try {
                        // 订阅主题
                        mqttAndroidClient.subscribe("/Im/" + subscriptionTopic, 0, new IMqttMessageListener() {
                            @Override
                            public void messageArrived(String topic, MqttMessage message) throws Exception {
                                // message Arrived! 收到消息
                                Log.e("FragmentHome Arrived", "Message: " + topic + " : " + new String(message.getPayload()));
                            }
                        });
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.rightButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rightButton:
                XPopup.get(getActivity()).asAttachList(new String[]{"添加朋友"}, new int[]{R.mipmap.ic_add_friend},
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                ToastUtil.showToast("click" + text);
                            }
                        })
                        .atView(rightButton).show();
                break;
            default:
                break;
        }
    }
}
