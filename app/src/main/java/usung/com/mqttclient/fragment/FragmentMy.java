package usung.com.mqttclient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import usung.com.mqttclient.R;
import usung.com.mqttclient.activity.ActivityBlackAndStrangerList;
import usung.com.mqttclient.base.BaseFragment;
import usung.com.mqttclient.bean.HttpResposeDataBase;
import usung.com.mqttclient.bean.db.GetUserSimpleInfoParameter;
import usung.com.mqttclient.bean.user.UserSimpleInfoResult;
import usung.com.mqttclient.http.base.Api;
import usung.com.mqttclient.http.observers.NoBaseResultObserver;
import usung.com.mqttclient.utils.SharePreferenceUtil;
import usung.com.mqttclient.utils.ToastUtil;

import static usung.com.mqttclient.base.APPConstants.SHARE_LOGIN_NAME;
import static usung.com.mqttclient.base.APPConstants.SHARE_LOGIN_NAME_AND_PAW;

/**
 * 主页--我的
 *
 * @author herui
 * @date 2018/12/10
 */

public class FragmentMy extends BaseFragment {
    @BindView(R.id.header_title)
    TextView headerTitle;
    @BindView(R.id.backButton)
    RelativeLayout backButtonView;
    @BindView(R.id.tv_nick)
    TextView tvNick;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    /**
     *  SharedPreference储存
     */
    private SharePreferenceUtil spUtil;

    public static FragmentMy newInstance(Bundle bundle) {
        FragmentMy f = new FragmentMy();
        f.setArguments(bundle);
        return f;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        spUtil = new SharePreferenceUtil(getActivity());
        getusersimpleinfo();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View messageView = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, messageView);

        initViews();
        return messageView;
    }

    @Override
    protected void initViews() {
        super.initViews();

        headerTitle.setText("我的");
        backButtonView.setVisibility(View.GONE);
    }

    /**
     * 获取用户简要消息
     */
    public void getusersimpleinfo() {
//        showLoading("");
        GetUserSimpleInfoParameter getUserSimpleInfoParameter = new GetUserSimpleInfoParameter();
        getUserSimpleInfoParameter.setFriendId(spUtil.getPreference(SHARE_LOGIN_NAME_AND_PAW, SHARE_LOGIN_NAME));
        Api.getApiService().getusersimpleinfo(getUserSimpleInfoParameter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NoBaseResultObserver<UserSimpleInfoResult>(getActivity()) {
                    @Override
                    public void onResponse(UserSimpleInfoResult userSimpleInfoResult) {
                        // 获取个人信息成功
                        if (userSimpleInfoResult != null && userSimpleInfoResult.getCode() == HttpResposeDataBase.SUCESSED) {
                            tvNick.setText(userSimpleInfoResult.getInfo().getNickName());
                            tvNumber.setText(userSimpleInfoResult.getInfo().getId());
                        } else {
                            ToastUtil.showToast(R.string.get_user_info_fail);
                        }
//                        dismissLoading();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.ll_black_list, R.id.ll_stranger_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //黑名单
            case R.id.ll_black_list:
                startActivity(new Intent(getActivity(), ActivityBlackAndStrangerList.class));
                break;
            //陌生人
            case R.id.ll_stranger_list:
                startActivity(new Intent(getActivity(), ActivityBlackAndStrangerList.class));
                break;
            default:
                break;
        }
    }

}
