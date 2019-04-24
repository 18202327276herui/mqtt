package usung.com.mqttclient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import usung.com.mqttclient.ActivityChat;
import usung.com.mqttclient.R;
import usung.com.mqttclient.activity.ActivitySearch;
import usung.com.mqttclient.adapter.ContactAdapter;
import usung.com.mqttclient.base.BaseFragment;
import usung.com.mqttclient.bean.db.Contact;
import usung.com.mqttclient.bean.db.InitiaDataResult;
import usung.com.mqttclient.bean.user.UserSimpleInfo;
import usung.com.mqttclient.utils.InitiadataUtil;
import usung.com.mqttclient.utils.StickyHeaderDecoration;
import usung.com.mqttclient.utils.cn.CNPinyin;
import usung.com.mqttclient.utils.cn.CNPinyinFactory;
import usung.com.mqttclient.widget.CharIndexView;

/**
 * 主页 -- 通讯录
 *
 * @author herui
 * @date 2018/12/10
 */

public class FragmentMailList extends BaseFragment {
    @BindView(R.id.header_title)
    TextView headerTitle;
    @BindView(R.id.backButton)
    RelativeLayout backButtonView;
    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.iv_main)
    CharIndexView ivMain;
    @BindView(R.id.tv_index)
    TextView tvIndex;
    /**
     *  联系人适配器
     */
    private ContactAdapter adapter;
    /**
     *  联系人列表 （转拼音）
     */
    private ArrayList<CNPinyin<Contact>> contactList = new ArrayList<>();
    /**
     *  联系人列表
     */
    private List<Contact> contacts = new ArrayList<>();
    /**
     *  初始化返回数据
     */
    private InitiaDataResult initiaDataResult;

    public static FragmentMailList newInstance(Bundle bundle) {
        FragmentMailList f = new FragmentMailList();
        f.setArguments(bundle);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View messageView = inflater.inflate(R.layout.fragment_mail_list, container, false);
        ButterKnife.bind(this, messageView);
        // 获取初始化数据
        initiaDataResult = InitiadataUtil.getInitiadata(getActivity());
        initViews();
        return messageView;
    }

    @Override
    protected void initViews() {
        super.initViews();

        headerTitle.setText(R.string.mail_list);
        backButtonView.setVisibility(View.GONE);

        // RecyclerView 适配器
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rvMain.setLayoutManager(manager);
        // 右边首字母检索
        ivMain.setOnCharIndexChangedListener(new CharIndexView.OnCharIndexChangedListener() {
            @Override
            public void onCharIndexChanged(char currentIndex) {
                for (int i = 0; i < contactList.size(); i++) {
                    if (contactList.get(i).getFirstChar() == currentIndex) {
                        manager.scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }

            @Override
            public void onCharIndexSelected(String currentIndex) {
                if (currentIndex == null) {
                    tvIndex.setVisibility(View.INVISIBLE);
                } else {
                    tvIndex.setVisibility(View.VISIBLE);
                    tvIndex.setText(currentIndex);
                }
            }
        });
        getPinyinList();
    }

    /**
     *  获取拼音列表
     */
    private void getPinyinList() {
        // 获取好友列表
        List<UserSimpleInfo> userSimpleInfoList = initiaDataResult.getData().getFriendList();
        for (UserSimpleInfo userSimpleInfo : userSimpleInfoList) {
            Contact contact = new Contact(userSimpleInfo.getNickName(), R.mipmap.header0, userSimpleInfo);
            contacts.add(contact);
        }
        contactList = CNPinyinFactory.createCNPinyinList(contacts);
        // 排序
        Collections.sort(contactList);
        adapter = new ContactAdapter(contactList);
        rvMain.setAdapter(adapter);
        rvMain.addItemDecoration(new StickyHeaderDecoration(adapter));
        // adapter 点击事件
        adapter.setListener(new ContactAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, UserSimpleInfo userSimpleInfo) {
                startActivity(new Intent(getActivity(), ActivityChat.class).putExtra("recipientId", userSimpleInfo.getId() + ""));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.left_rightButton, R.id.bt_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 查找
            case R.id.bt_search:
                startActivity(new Intent(getActivity(), ActivitySearch.class).putExtra("contactList", contactList));
                break;
            default:
                break;
        }
    }
}
