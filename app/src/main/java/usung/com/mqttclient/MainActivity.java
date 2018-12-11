package usung.com.mqttclient;

import android.os.Bundle;
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
import usung.com.mqttclient.adapter.AdapterMainRecyclerView;
import usung.com.mqttclient.adapter.PagerAdapter;
import usung.com.mqttclient.base.BaseActivity;
import usung.com.mqttclient.base.MqttHelper;
import usung.com.mqttclient.fragment.FragmentHome;
import usung.com.mqttclient.fragment.FragmentMessage;
import usung.com.mqttclient.fragment.FragmentMy;

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
    private AdapterMainRecyclerView adapterMainRceyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        initFragment();
        MqttHelper.getInstance(getActivity());
    }

    @Override
    public void initViews() {
    }

    /**
     * 初始化Fragment
     */
    public void initFragment() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentHome.newInstance(null));
        fragments.add(FragmentMessage.newInstance(null));
        fragments.add(FragmentMy.newInstance(null));
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), fragments, null);
        vpViewPager.setAdapter(adapter);
        vpViewPager.addOnPageChangeListener(this);
        vpViewPager.setCurrentItem(0);
    }

    @OnClick({R.id.rl_home, R.id.rl_message, R.id.rl_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_home:
                setTabSelection(0);
                vpViewPager.setCurrentItem(0, false);
                break;
            case R.id.rl_message:
                setTabSelection(1);
                vpViewPager.setCurrentItem(1, false);
                break;
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
        setTabSelection(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
