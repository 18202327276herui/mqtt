package usung.com.mqttclient.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.reactivestreams.Subscription;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import usung.com.mqttclient.ActivityChat;
import usung.com.mqttclient.R;
import usung.com.mqttclient.adapter.SearchAdapter;
import usung.com.mqttclient.base.BaseActivity;
import usung.com.mqttclient.bean.db.Contact;
import usung.com.mqttclient.bean.user.UserSimpleInfo;
import usung.com.mqttclient.utils.ConfigUtils;
import usung.com.mqttclient.utils.cn.CNPinyin;
import usung.com.mqttclient.utils.cn.CNPinyinIndex;
import usung.com.mqttclient.utils.cn.CNPinyinIndexFactory;

/**
 * 搜索
 *
 * @author herui
 */
public class ActivitySearch extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    /**
     * 窗体控件上一次的高度,用于监听键盘弹起
     */
    private int mLastHeight;
    private SearchAdapter adapter;
    private EditText et_search;
    private Subscription subscription;
    private ArrayList<CNPinyin<Contact>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        initViews();
    }

    @Override
    protected void initViews() {
        super.initViews();

        contactList = (ArrayList<CNPinyin<Contact>>) getIntent().getSerializableExtra("contactList");

        rvMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SearchAdapter(new ArrayList<CNPinyinIndex<Contact>>());
        rvMain.setAdapter(adapter);
        initToolbar();

        final View decorView = this.getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect r = new Rect();
                        decorView.getWindowVisibleDisplayFrame(r);
                        if (mLastHeight != r.bottom) {
                            mLastHeight = r.bottom;
                            ViewGroup.LayoutParams params = llRoot.getLayoutParams();
                            params.height = r.bottom - llRoot.getTop()/*  - statusHeight*/;
                            llRoot.setLayoutParams(params);
                        }
                    }
                });

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                ArrayList<CNPinyinIndex<Contact>> newDatas = CNPinyinIndexFactory.indexList(contactList, editable.toString());
                adapter.setNewDatas(newDatas);
            }
        });

        adapter.setListener(new SearchAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, UserSimpleInfo userSimpleInfo) {
                startActivity(new Intent(getActivity(), ActivityChat.class).putExtra("recipientId", userSimpleInfo.getId()));
            }
        });
    }

    /**
     * 初始化toolbar
     */
    public void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        View actionView = getLayoutInflater().inflate(R.layout.actionbar_search, null);
        actionView.findViewById(R.id.iv_return).setOnClickListener(this);
        et_search = (EditText) actionView.findViewById(R.id.et_search);
        ActionBar.LayoutParams actionBarParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        bar.setCustomView(actionView, actionBarParams);
        ConfigUtils.setStatusBarColor(this, getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected void onDestroy() {
        if (subscription != null) {
        }
        super.onDestroy();
    }
}
