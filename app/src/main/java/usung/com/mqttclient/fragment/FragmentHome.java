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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import usung.com.mqttclient.ActivityChat;
import usung.com.mqttclient.R;
import usung.com.mqttclient.adapter.AdapterMainRecyclerView;
import usung.com.mqttclient.base.BaseFragment;

/**
 * 主页 -- 首页
 *
 * @author herui
 * @date 2018/12/10
 */

public class FragmentHome extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private AdapterMainRecyclerView adapterMainRceyclerView;

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
        List<String> dataLists = new ArrayList<>();
        dataLists.add("123");
        dataLists.add("456");
        dataLists.add("789");
        adapterMainRceyclerView = new AdapterMainRecyclerView(getActivity(), dataLists);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(manager);
        rvList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rvList.setAdapter(adapterMainRceyclerView);

        adapterMainRceyclerView.setListener(new AdapterMainRecyclerView.onItemClickListener() {
            @Override
            public void onItemClick(View view) {
                startActivity(new Intent(getActivity(), ActivityChat.class));
            }
        });
    }
}
