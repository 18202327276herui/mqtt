package usung.com.mqttclient.fragment;

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
import usung.com.mqttclient.R;
import usung.com.mqttclient.base.BaseFragment;
import usung.com.mqttclient.bean.db.InitiaDataResult;
import usung.com.mqttclient.bean.user.UserSimpleInfo;
import usung.com.mqttclient.utils.InitiadataUtil;

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
    private InitiaDataResult initiaDataResult;


    public static FragmentMy newInstance(Bundle bundle) {
        FragmentMy f = new FragmentMy();
        f.setArguments(bundle);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View messageView = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, messageView);

        initiaDataResult = InitiadataUtil.getInitiadata(getActivity());
        initViews();
        return messageView;
    }

    @Override
    protected void initViews() {
        super.initViews();

        UserSimpleInfo userSimpleInfo = initiaDataResult.getData().getSelfInfo();
        headerTitle.setText("我的");
        backButtonView.setVisibility(View.GONE);

        tvNick.setText(userSimpleInfo.getNickName());
        tvNumber.setText(userSimpleInfo.getId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
