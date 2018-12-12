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

/**
 * 主页 -- 消息
 *
 * @author herui
 * @date 2018/12/10
 */

public class FragmentMessage extends BaseFragment {
    @BindView(R.id.header_title)
    TextView headerTitle;
    @BindView(R.id.backButton)
    RelativeLayout backButtonView;

    public static FragmentMessage newInstance(Bundle bundle) {
        FragmentMessage f = new FragmentMessage();
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

        headerTitle.setText("消息");
        backButtonView.setVisibility(View.GONE);
    }
}
