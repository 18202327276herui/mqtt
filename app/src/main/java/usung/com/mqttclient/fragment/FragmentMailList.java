package usung.com.mqttclient.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import usung.com.mqttclient.R;
import usung.com.mqttclient.adapter.ContactAdapter;
import usung.com.mqttclient.base.BaseFragment;
import usung.com.mqttclient.bean.db.Contact;
import usung.com.mqttclient.utils.StickyHeaderDecoration;
import usung.com.mqttclient.utils.TestUtils;
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

    private ContactAdapter adapter;
    private ArrayList<CNPinyin<Contact>> contactList = new ArrayList<>();

    public static FragmentMailList newInstance(Bundle bundle) {
        FragmentMailList f = new FragmentMailList();
        f.setArguments(bundle);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View messageView = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, messageView);
        initViews();
        return messageView;
    }

    @Override
    protected void initViews() {
        super.initViews();

        headerTitle.setText(R.string.mail_list);
        backButtonView.setVisibility(View.GONE);

        adapter = new ContactAdapter(contactList);
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rvMain.setLayoutManager(manager);
        rvMain.setAdapter(adapter);
        rvMain.addItemDecoration(new StickyHeaderDecoration(adapter));

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

    private void getPinyinList() {
        Observable.create(new ObservableOnSubscribe<List<CNPinyin<Contact>>>() {
            @Override
            public void subscribe(ObservableEmitter<List<CNPinyin<Contact>>> emitter) throws Exception {
                List<CNPinyin<Contact>> contactList = CNPinyinFactory.createCNPinyinList(TestUtils.contactList(getActivity()));
                Collections.sort(contactList);
                emitter.onNext(contactList);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CNPinyin<Contact>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<CNPinyin<Contact>> cnPinyins) {
                        contactList.addAll(cnPinyins);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
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
            case R.id.bt_search:

                break;
            default:
                break;
        }
    }
}
