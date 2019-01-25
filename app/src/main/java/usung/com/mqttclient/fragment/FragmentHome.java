package usung.com.mqttclient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import usung.com.mqttclient.ActivityChat;
import usung.com.mqttclient.R;
import usung.com.mqttclient.adapter.AdapterMainRecyclerView;
import usung.com.mqttclient.base.BaseFragment;
import usung.com.mqttclient.bean.db.HistoryMessage;
import usung.com.mqttclient.bean.db.InitiaDataResult;
import usung.com.mqttclient.utils.InitiadataUtil;

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

        historyMessageList = LitePal.where("senderid = ?", initiaDataResult.getData().getSelfInfo().getId()).find(HistoryMessage.class);
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
}
