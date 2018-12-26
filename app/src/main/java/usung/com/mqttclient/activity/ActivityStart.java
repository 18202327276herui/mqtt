package usung.com.mqttclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;

import java.util.List;

import usung.com.mqttclient.R;
import usung.com.mqttclient.base.BaseActivity;
import usung.com.mqttclient.utils.PermissionHelper;

/**
 * @author herui
 * @dtae 2018/12/17
 */
public class ActivityStart extends BaseActivity {
//    private String[] permissions = {PermissionHelper.PERMISSION_CAMERA, PermissionHelper.PERMISSION_ACCESS_FINE_LOCATION, PermissionHelper.PERMISSION_READ_PHONE_STATE,
//            PermissionHelper.PERMISSION_WRITE_EXTERNAL_STORAGE};
    private String[] permissions = {PermissionHelper.PERMISSION_READ_PHONE_STATE,
            PermissionHelper.PERMISSION_WRITE_EXTERNAL_STORAGE};

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

        if (PermissionHelper.needCheckPermission()) {
            if (PermissionHelper.requestPerssions(this, PermissionHelper.CODE_MULTI_PERMISSION, permissions)) {
                loadLoginUI();
            }
        } else {
            loadLoginUI();
        }

    }

    public void loadLoginUI(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ActivityStart.this, ActivityLogin.class));
                finish();
            }
        }, 2000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionHelper.onRequestPermissionsResult(PermissionHelper.CODE_MULTI_PERMISSION, permissions, grantResults, new PermissionHelper.OnRequestPermissionsResultCallbacks() {
            @Override
            public void onPermissionsGranted(int requestCode, List<String> perms, boolean isAllGranted) {
                if (isAllGranted) {
                    loadLoginUI();
                }
            }

            @Override
            public void onPermissionsDenied(int requestCode, List<String> perms, boolean isAllDenied) {
                PermissionHelper.openSettingActivity(ActivityStart.this, ActivityLogin.class.getSimpleName(),
                        getString(R.string.permission_prompt), true);
            }
        });
    }
}
