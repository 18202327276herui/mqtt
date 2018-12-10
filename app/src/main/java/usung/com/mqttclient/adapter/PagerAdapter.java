package usung.com.mqttclient.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import java.util.List;

/**
 *  viewPager适配器
 * Created by herui on 2018/12/10.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    /**
     *  添加的Fragment的集合
     */
    private List<Fragment> mFragments;
    /**
     *  每个Fragment对应的title的集合
     */
    private List<String> mFragmnetsTitles;
    private FragmentManager mFragmentManager;

    public PagerAdapter(FragmentManager fm, List<Fragment> mFragments, List<String> mFragmnetsTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mFragmnetsTitles = mFragmnetsTitles;
        this.mFragmentManager = fm;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = mFragments.get(position);
        fragment.setMenuVisibility(true);
        // 如果fragment还没有added
        if (!fragment.isAdded()) {
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.add(fragment, fragment.getClass().getSimpleName());
            ft.commitAllowingStateLoss();
            /**
             * 在用FragmentTransaction.commit()方法提交FragmentTransaction对象后
             * 会在进程的主线程中，用异步的方式来执行。 如果想要立即执行这个等待中的操作，就要调用这个方法（只能在主线程中调用）。
             * 要注意的是，所有的回调和相关的行为都会在这个调用中被执行完成，因此要仔细确认这个方法的调用位置。
             */
            mFragmentManager.executePendingTransactions();
        }

        if (fragment.getView() != null && fragment.getView().getParent() == null) {
            container.addView(fragment.getView());
        }
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mFragments.get(position).getView());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (mFragmnetsTitles == null || mFragmnetsTitles.isEmpty()) {
            return "";
        }
        return mFragmnetsTitles.get(position);
    }
}
