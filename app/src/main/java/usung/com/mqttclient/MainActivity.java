package usung.com.mqttclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import usung.com.mqttclient.adapter.AdapterMainRecyclerView;
import usung.com.mqttclient.base.BaseActivity;

/** 主界面
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

        // 绑定
        ButterKnife.bind(this);

        initViews();
    }

    @Override
    public void initViews() {
        adapterMainRceyclerView = new AdapterMainRecyclerView(getActivity());
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
}
