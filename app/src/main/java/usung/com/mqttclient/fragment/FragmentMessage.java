package usung.com.mqttclient.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return messageView;
    }
}
