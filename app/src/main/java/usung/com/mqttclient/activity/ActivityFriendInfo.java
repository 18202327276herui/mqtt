package usung.com.mqttclient.activity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import usung.com.mqttclient.R;
import usung.com.mqttclient.base.BaseActivity;

/**
 *  好友详情信息页面
 * @author herui
 */
public class ActivityFriendInfo extends BaseActivity {
    @BindView(R.id.header_title)
    TextView headerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_info);
        ButterKnife.bind(this);

        initViews();
    }

    @Override
    protected void initViews() {
        super.initViews();

        headerTitle.setText("详细资料");
    }
}
