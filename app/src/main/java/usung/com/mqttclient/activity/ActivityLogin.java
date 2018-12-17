package usung.com.mqttclient.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import usung.com.mqttclient.MainActivity;
import usung.com.mqttclient.R;
import usung.com.mqttclient.adapter.AdapterRecordLoginInfo;
import usung.com.mqttclient.base.APPConstants;
import usung.com.mqttclient.base.BaseActivity;
import usung.com.mqttclient.bean.HttpResposeDataBase;
import usung.com.mqttclient.bean.user.LoginParameter;
import usung.com.mqttclient.bean.user.LoginResultData;
import usung.com.mqttclient.http.base.Api;
import usung.com.mqttclient.http.observers.NoBaseResultObserver;
import usung.com.mqttclient.utils.SharePreferenceUtil;
import usung.com.mqttclient.utils.StringHelper;
import usung.com.mqttclient.utils.ToastUtil;
import usung.com.mqttclient.widget.RecordLoginPopWindow;

import static usung.com.mqttclient.base.APPConstants.SHARE_LOGIN_NAME;
import static usung.com.mqttclient.base.APPConstants.SHARE_LOGIN_NAME_AND_PAW;
import static usung.com.mqttclient.base.APPConstants.SHARE_LOGIN_PWD;

/**
 * @author herui
 */
public class ActivityLogin extends BaseActivity {
    @BindView(R.id.edt_account)
    EditText edtAccount;
    @BindView(R.id.edt_pwd)
    EditText edtPwd;
    @BindView(R.id.cb_pwd_visibility)
    CheckBox cbPwdVisibility;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.cb_remember_pwd)
    CheckBox cbRememberPwd;
    @BindView(R.id.checkB_remind_account)
    CheckBox checkBRemindAccount;
    private String userName;
    private String userPwd;
    /**
     * 存储登录账号的集合
     */
    private ArrayList<String> loginDataList = null;
    private RecordLoginPopWindow popupWindow;
    /**
     * 存储登录账号的适配器
     */
    private AdapterRecordLoginInfo mAdapter = null;
    /**
     * 是否选中记住密码
     */
    private boolean ischecked;
    private SharePreferenceUtil spUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        spUtil = new SharePreferenceUtil(ActivityLogin.this);
        initViews();
        // 获取历史登录账号
        getloginDataList();
    }

    @Override
    protected void initViews() {
        super.initViews();

        edtAccount.setText(spUtil.getPreference(SHARE_LOGIN_NAME_AND_PAW, SHARE_LOGIN_NAME));
        edtPwd.setText(spUtil.getPreference(SHARE_LOGIN_NAME_AND_PAW, SHARE_LOGIN_PWD));
        ischecked = (Boolean) SharePreferenceUtil.getPreferenceValue(ActivityLogin.this, SHARE_LOGIN_NAME_AND_PAW, "ischecked", false);
        cbRememberPwd.setChecked(ischecked);

        cbPwdVisibility.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    edtPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType
                            .TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    edtPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                edtPwd.requestFocus();
                // 设置输入框的光标在最后
                edtPwd.setSelection(edtPwd.getText().toString().length());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.btn_login, R.id.tv_register, R.id.checkB_remind_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                userName = edtAccount.getText().toString();
                userPwd = edtPwd.getText().toString();

                if (StringHelper.isEmpty(userName)) {
                    ToastUtil.showToast(getString(R.string.input_user_name));
                    return;
                } else if (StringHelper.isEmpty(userPwd)) {
                    ToastUtil.showToast(getString(R.string.input_pwd));
                    return;
                }
                login();
                break;
            case R.id.tv_register:
//                startActivity(new Intent(ActivityLogin.this, ActivityRegister.class));
                break;
            case R.id.checkB_remind_account:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                if (loginDataList == null || loginDataList.size() <= 0) {
                    checkBRemindAccount.setChecked(false);
                    ToastUtil.showToastResId(ActivityLogin.this, R.string.no_loging_record, Toast
                            .LENGTH_LONG);
                    return;
                }
                if (popupWindow == null || !popupWindow.isShowing()) {
                    showPopUp(edtAccount);
                } else {
                    popupWindow.dismiss();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 登录
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void login() {
        showLoading("");

        LoginParameter loginParameter = new LoginParameter();
        loginParameter.setUserId(edtAccount.getText().toString());
        loginParameter.setPassWord(edtPwd.getText().toString());
        Api.getApiService().login(loginParameter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NoBaseResultObserver<LoginResultData>(getActivity()) {
                    @Override
                    public void onResponse(LoginResultData loginResultData) {
                        if (loginResultData != null) {
                            if (loginResultData.getResult() == 0 && loginResultData.getCode() == HttpResposeDataBase.SUCESSED) {
                                if (cbRememberPwd.isChecked()) {
                                    spUtil.savePreference(SHARE_LOGIN_NAME_AND_PAW, SHARE_LOGIN_PWD, userPwd);
                                    spUtil.savePreference(SHARE_LOGIN_NAME_AND_PAW, "ischecked", cbRememberPwd.isChecked());
                                } else {
                                    spUtil.removePreference(SHARE_LOGIN_NAME_AND_PAW, SHARE_LOGIN_PWD);
                                    spUtil.removePreference(SHARE_LOGIN_NAME_AND_PAW, "ischecked");
                                }
                                spUtil.savePreference(SHARE_LOGIN_NAME_AND_PAW, SHARE_LOGIN_NAME, userName);
//                        spUtil.savePreference(SHARE_LOGIN_NAME_AND_PAW, "userFullName", user.getFullName());
//                        spUtil.savePreference(SHARE_LOGIN_NAME_AND_PAW, "userId", user.getUserId());
//                        UserUtil.putUser(getActivity(), user);
                                // 判断登录名是否存在于loginDataList 集合中
                                if (!loginDataList.contains(userName)) {
                                    loginDataList.add(userName);
                                }

                                if (loginDataList.size() > 5) {
                                    loginDataList.remove(0);
                                }
                                saveLoginDataList();
                                ToastUtil.showToast(R.string.login_success);
                                startActivity(new Intent(ActivityLogin.this, MainActivity.class));
                                finish();
                            }else {
                                ToastUtil.showToast(loginResultData.getResult() + "");
                            }
                        } else {
                            ToastUtil.showToast(R.string.login_fail);
                        }
                        dismissLoading();
                    }
                });
    }

    /**
     * 从sharePreference中获取集合
     */
    public ArrayList<String> getloginDataList() {
        SharedPreferences sharedPreferences = getSharedPreferences(APPConstants
                .SHARE_LOGIN_NAME_LIST_RECORD, MODE_PRIVATE);
        String strJson = sharedPreferences.getString("key", null);
        if (null == strJson) {
            loginDataList = new ArrayList<String>();
        } else {
            Gson gson = new Gson();
            loginDataList = gson.fromJson(strJson, new TypeToken<ArrayList<String>>() {
            }.getType());
        }
        return loginDataList;
    }

    /**
     * 记住登录账号的popupWindow
     *
     * @param v
     */
    private void showPopUp(View v) {
        popupWindow = new RecordLoginPopWindow(ActivityLogin.this);
        View contentView = LayoutInflater.from(ActivityLogin.this).inflate(R.layout
                .popup_login_name, null);
        ListView mListView = (ListView) contentView.findViewById(R.id.mListView);
        mAdapter = new AdapterRecordLoginInfo(ActivityLogin.this, loginDataList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // 切换账号时，判断所选账号与之前的账号是否一致，如果不一致则清空密码
                if (StringHelper.isNotEmpty(edtAccount.getText().toString()) && !edtAccount
                        .getText().toString().equals(loginDataList.get(position))) {
                    edtPwd.setText("");
                }
                edtAccount.setText(loginDataList.get(position));
                popupWindow.dismiss();
            }
        });

        popupWindow.showPopupWindow(contentView, v, checkBRemindAccount);
    }

    /**
     * @param name
     */
    public void deleteName(String name) {
        this.loginDataList.remove(name);
        if (loginDataList.size() <= 0 && popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            mAdapter.setListAndRefresh(loginDataList);
        }
        saveLoginDataList();
    }

    /**
     * 保存 （把集合保存到sharePreference中）
     */
    public void saveLoginDataList() {
        SharedPreferences sharedPreferences = getSharedPreferences(APPConstants
                .SHARE_LOGIN_NAME_LIST_RECORD, MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String strJson = gson.toJson(loginDataList);
        spEditor.putString("key", strJson).commit();
    }
}
