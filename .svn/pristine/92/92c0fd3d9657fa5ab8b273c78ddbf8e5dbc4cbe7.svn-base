package usung.com.mqttclient.activity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import usung.com.mqttclient.R;
import usung.com.mqttclient.base.BaseActivity;

/**
 *  黑名单 陌生人列表
 * @author herui
 */
public class ActivityBlackAndStrangerList extends BaseActivity {
    @BindView(R.id.header_title)
    TextView headerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_and_stranger_list);
        ButterKnife.bind(this);

        initViews();
    }

    @Override
    protected void initViews() {
        super.initViews();

        headerTitle.setText("黑名单");
    }
}
