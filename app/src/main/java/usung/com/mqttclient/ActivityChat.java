package usung.com.mqttclient;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import usung.com.mqttclient.base.BaseActivity;

/**
 * @author herui
 */
public class ActivityChat extends BaseActivity {
    @BindView(R.id.header_title)
    TextView headerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        initViews();
    }

    @Override
    public void initViews(){
        super.initViews();
        headerTitle.setText("Android开发交流 ");
    }
}
