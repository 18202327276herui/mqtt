package usung.com.mqttclient.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import usung.com.mqttclient.Interface.ILoginVisitor;
import usung.com.mqttclient.R;
import usung.com.mqttclient.base.BaseActivity;
import usung.com.mqttclient.bean.HttpResposeDataBase;
import usung.com.mqttclient.bean.user.DeviceType;
import usung.com.mqttclient.bean.user.LoginResultData;
import usung.com.mqttclient.bean.user.RegistResultData;
import usung.com.mqttclient.bean.user.RegisteParameter;
import usung.com.mqttclient.http.base.Api;
import usung.com.mqttclient.http.observers.NoBaseResultObserver;
import usung.com.mqttclient.utils.StringHelper;
import usung.com.mqttclient.utils.ToastUtil;

/**
 *  注册界面
 * @author herui
 */
public class ActivityRegister extends BaseActivity implements ILoginVisitor {
    @BindView(R.id.header_title)
    TextView headerTitle;
    @BindView(R.id.edt_user_name)
    EditText edtUserName;
    @BindView(R.id.edt_nick_name)
    EditText edtNickName;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.edt_confirm_password)
    EditText edtConfirmPassword;
    private String userName;
    private String nickName;
    private String passWord;
    private String confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        initViews();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void initViews() {
        super.initViews();

        headerTitle.setText(getString(R.string.register));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 注册按钮
            case R.id.btn_register:
                userName = edtUserName.getText().toString();
                nickName = edtNickName.getText().toString();
                passWord = edtPassword.getText().toString();
                confirmPassword = edtConfirmPassword.getText().toString();

                if (StringHelper.isEmpty(userName)) {
                    ToastUtil.showToast(getString(R.string.input_user_name));
                    return;
                }
                if (StringHelper.isEmpty(nickName)) {
                    ToastUtil.showToast(getString(R.string.input_nick_name));
                    return;
                }
                if (StringHelper.isEmpty(passWord)) {
                    ToastUtil.showToast(getString(R.string.input_passsword));
                    return;
                }
                if (StringHelper.isEmpty(confirmPassword)) {
                    ToastUtil.showToast(getString(R.string.input_confirm_password));
                    return;
                }

                if (!passWord.equalsIgnoreCase(confirmPassword)){
                    ToastUtil.showToast(getString(R.string.two_password_inconsistencies));
                    return;
                }

                register(Long.parseLong(userName), passWord, nickName);
                break;
            default:
                break;
        }
    }

    @Override
    public String changeToken(String userid, String oldToken) {
        return null;
    }

    @Override
    public LoginResultData login(long userid, String password, DeviceType type) {
        return null;
    }

    /**
     *  注册
     * @param userid 用户id
     * @param password 用户密码
     * @param nickName 昵称
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public RegistResultData register(long userid, String password, String nickName) {
        showLoading("");
        RegisteParameter registeParameter = new RegisteParameter();
        registeParameter.setUserId(userid);
        registeParameter.setNickName(nickName);
        registeParameter.setFirstPassWords(password);
        Api.getApiService().registe(registeParameter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NoBaseResultObserver<RegistResultData>(getActivity()) {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onResponse(RegistResultData registResultData) {
                        if (registResultData != null) {
                            if (registResultData.getResult() == 0 && registResultData.getCode() == HttpResposeDataBase.SUCESSED) {
                                ToastUtil.showToast(R.string.register_success);
                                finish();
                            }else {
                                ToastUtil.showToast(registResultData.getResult() + "");
                            }
                        } else {
                            ToastUtil.showToast(R.string.register_fail);
                        }
                        dismissLoading();
                    }
                });
        return null;
    }
}
