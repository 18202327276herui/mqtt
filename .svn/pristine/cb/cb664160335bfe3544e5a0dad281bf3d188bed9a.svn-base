package usung.com.mqttclient.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import usung.com.mqttclient.R;
import usung.com.mqttclient.base.BaseActivity;
import usung.com.mqttclient.utils.ToastUtil;

/**
 *  好友详情信息页面
 * @author herui
 */
public class ActivityFriendInfo extends BaseActivity {
    @BindView(R.id.header_title)
    TextView headerTitle;
    @BindView(R.id.rightButton)
    Button rightButton;

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

    @OnClick({R.id.rightButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rightButton:
                XPopup.get(getActivity()).asAttachList(new String[]{"加入黑名单"}, new int[]{R.mipmap.ic_add_friend},
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                ToastUtil.showToast("click" + text);
                            }
                        })
                        .atView(rightButton).show();
                break;
            default:
                break;
        }
    }
}
