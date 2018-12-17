package usung.com.mqttclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import usung.com.mqttclient.R;
import usung.com.mqttclient.base.BaseActivity;

/**
 * @author herui
 * @dtae 2018/12/17
 */
public class ActivityStart extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //判断该Activity是不是任务空间的源Activity，“非”也就是说是被系统重新实例化出来
        if (!this.isTaskRoot()) {
            finish();
            //finish()之后该活动会继续执行后面的代码，你可以logCat验证，加return避免可能的exception
            return;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ActivityStart.this, ActivityLogin.class));
                finish();
            }
        }, 2000);
    }
}
