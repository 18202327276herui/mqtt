package usung.com.mqttclient;

import android.app.LauncherActivity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import usung.com.mqttclient.adapter.AdapterMainRecyclerView;
import usung.com.mqttclient.adapter.PagerAdapter;
import usung.com.mqttclient.base.BaseActivity;
import usung.com.mqttclient.base.MqttHelper;
import usung.com.mqttclient.bean.HttpRequestParameterBase;
import usung.com.mqttclient.bean.HttpResposeDataBase;
import usung.com.mqttclient.bean.db.InitiaDataResult;
import usung.com.mqttclient.bean.user.LoginResultData;
import usung.com.mqttclient.fragment.FragmentHome;
import usung.com.mqttclient.fragment.FragmentMailList;
import usung.com.mqttclient.fragment.FragmentMy;
import usung.com.mqttclient.http.base.Api;
import usung.com.mqttclient.http.observers.NoBaseResultObserver;
import usung.com.mqttclient.utils.InitiadataUtil;
import usung.com.mqttclient.utils.SharePreferenceUtil;
import usung.com.mqttclient.utils.ToastUtil;
import usung.com.mqttclient.utils.UserUtil;

import static usung.com.mqttclient.base.APPConstants.SHARE_LOGIN_NAME;
import static usung.com.mqttclient.base.APPConstants.SHARE_LOGIN_NAME_AND_PAW;

/**
 * 主界面
 *
 * @author herui
 * @date 2018/11/28
 */
public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.img_home)
    ImageView imgHome;
    @BindView(R.id.txt_home)
    TextView txtHome;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.txt_message)
    TextView txtMessage;
    @BindView(R.id.img_my)
    ImageView imgMy;
    @BindView(R.id.txt_my)
    TextView txtMy;
    @BindView(R.id.vp_view_pager)
    ViewPager vpViewPager;
    /**
     * 登录结果数据
     */
    private LoginResultData loginResultData;
    /**
     * 本地数据库
     */
    private SharePreferenceUtil spUtil;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        initData();

    }

    @Override
    public void initViews() {
        loginResultData = UserUtil.getUser(getActivity());
        spUtil = new SharePreferenceUtil(MainActivity.this);
    }

    /**
     * 初始化数据
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initData() {
        showLoading("");
        // 初始化参数
        HttpRequestParameterBase httpRequestParameterBase = new HttpRequestParameterBase();
        httpRequestParameterBase.setSelfId(spUtil.getPreference(SHARE_LOGIN_NAME_AND_PAW, SHARE_LOGIN_NAME));
        httpRequestParameterBase.setToken(loginResultData.getToken());
        Api.getApiService().getinitiadata(httpRequestParameterBase)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NoBaseResultObserver<InitiaDataResult>(getActivity()) {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onResponse(InitiaDataResult initiaDataResult) {
                        // 请求成功
                        if (initiaDataResult != null && initiaDataResult.getCode() == HttpResposeDataBase.SUCESSED) {
                            InitiadataUtil.putInitiadata(getActivity(), initiaDataResult);
                            MqttHelper mqttHelper = MqttHelper.getInstance(getActivity());
                            mqttHelper.setClientId(loginResultData.getMqttCredencial().getClientId());
                            mqttHelper.setSubscriptionTopic(initiaDataResult.getData().getSelfTopic());
                            mqttHelper.initMqtt();
                        } else {
                            ToastUtil.showToast(R.string.get_initiadata_fail);
                        }
                        initFragment();
                        dismissLoading();
                    }
                });
    }

    /**
     * 初始化Fragment
     */
    public void initFragment() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentHome.newInstance(null));
        fragments.add(FragmentMailList.newInstance(null));
        fragments.add(FragmentMy.newInstance(null));
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), fragments, null);
        // 设置adapter
        vpViewPager.setAdapter(adapter);
        // 添加页面改变事件
        vpViewPager.addOnPageChangeListener(this);
        // 默认显示第一页
        vpViewPager.setCurrentItem(0);
    }

    @OnClick({R.id.rl_home, R.id.rl_message, R.id.rl_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 主页
            case R.id.rl_home:
                setTabSelection(0);
                vpViewPager.setCurrentItem(0, false);
                break;
            // 通讯录
            case R.id.rl_message:
                setTabSelection(1);
                vpViewPager.setCurrentItem(1, false);
                break;
            // 我的
            case R.id.rl_my:
                setTabSelection(2);
                vpViewPager.setCurrentItem(2, false);
                break;
            default:
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0表示首页，1表示电话，2表示我的。
     */
    public void setTabSelection(int index) {
        // 每次选中之前先清除之前的选中状态
        clearSelection();
        switch (index) {
            case 0:
                txtHome.setTextColor(getResources().getColor(R.color.color_theme));
                imgHome.setImageResource(R.mipmap.img_home_selected);
                break;
            case 1:
                txtMessage.setTextColor(getResources().getColor(R.color.color_theme));
                imgMessage.setImageResource(R.mipmap.img_message_selected);
                break;
            case 2:
                txtMy.setTextColor(getResources().getColor(R.color.color_theme));
                imgMy.setImageResource(R.mipmap.img_my_selected);
                break;
            default:
                break;
        }
    }

    /**
     * 清除之前的选中状态
     */
    public void clearSelection() {
        imgHome.setImageResource(R.mipmap.img_home_unselected);
        txtHome.setTextColor(getResources().getColor(R.color.color_grey_text));
        imgMessage.setImageResource(R.mipmap.img_message_unselected);
        txtMessage.setTextColor(getResources().getColor(R.color.color_grey_text));
        txtMy.setTextColor(getResources().getColor(R.color.color_grey_text));
        imgMy.setImageResource(R.mipmap.img_my_unselected);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        // 页面改变
        setTabSelection(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
