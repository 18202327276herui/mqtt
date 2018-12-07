package usung.com.mqttclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import usung.com.mqttclient.adapter.AdapterMainRecyclerView;
import usung.com.mqttclient.base.BaseActivity;

/**
 * 主界面
 *
 * @author herui
 * @date 2018/11/28
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private AdapterMainRecyclerView adapterMainRceyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    public void initViews() {
        List<String> dataLists = new ArrayList<>();
        dataLists.add("123");
        dataLists.add("456");
        dataLists.add("789");
        adapterMainRceyclerView = new AdapterMainRecyclerView(getActivity(), dataLists);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(manager);
        rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvList.setAdapter(adapterMainRceyclerView);

        adapterMainRceyclerView.setListener(new AdapterMainRecyclerView.onItemClickListener() {
            @Override
            public void onItemClick(View view) {
                startActivity(new Intent(getActivity(), ActivityChat.class));
            }
        });
    }

    @OnClick({R.id.rl_home, R.id.rl_message, R.id.rl_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_home:
                break;
            case R.id.rl_message:
                break;
            case R.id.rl_my:
                break;
            default:
                break;
        }
    }
}
