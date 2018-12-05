package usung.com.mqttclient.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import butterknife.Unbinder;
import usung.com.mqttclient.R;

/**
 * Descriptions:
 * Created by fenghui on 2018/4/28.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * savedInstanceState的标志：代表忽略，不重新登录
     */
    public final int TAG_SAVE_DATA_IGNORE = 1;

    /**
     * 返回按钮
     */
    protected View backButton;
    /**
     * 标题
     */
    protected TextView title;
    /**
     * 右边按钮
     */
    public View rightButton;
    /**
     * 右边按钮的左边
     */
    protected View left_rightButton;

    protected View emptyView;
    protected Unbinder unbinder;
    protected static final String SERVER_URL_KEY = "server_url_key";
    private boolean isShowGestureView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.getInstance().addActivity(this);
//        emptyView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.empty_view, null, false);
        if (savedInstanceState != null && savedInstanceState.getInt("tag", 0) != TAG_SAVE_DATA_IGNORE) {
            savedInstanceState.getString(SERVER_URL_KEY);
        } else {
            onReady(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * 所有后续操作需在onReady里执行
     */
    protected void onReady(Bundle savedInstanceState) {
    }

    /**
     * 可根据savedInstanceState是否为空判断获取页面传值或系统传值
     * 界面复写此方法时，中需要在onReady 或 onCreate 中调用
     *
     * @param savedInstanceState 当被系统杀死后这个值不为空，可获取保存的内容
     */
    protected void getIntentValue(Bundle savedInstanceState) {
    }

    // 初始化页面view
    protected void initViews() {
        // listView为空的视图
        backButton = findViewById(R.id.backButton);
//        rightButton = findViewById(R.id.rightButton);
//        left_rightButton = findViewById(R.id.left_rightButton);
        if (null != backButton) {
            backButton.setOnClickListener(this);
        }
        if (null != rightButton) {
            rightButton.setOnClickListener(this);
        }
        if (null != left_rightButton) {
            left_rightButton.setOnClickListener(this);
        }
//        title = (TextView) findViewById(R.id.header_title);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backButton:
                onBackPressed();
                break;
//            case R.id.emptyView:
////                loadList();
//                break;
//            case R.id.tv_empty:
////                loadList();
//                break;
            default:
                break;
        }
    }

    /**
     * listView为空时点击刷新
     */
    protected void loadList() {

    }

    protected void setRightButtonText(int resId) {
        setRightButtonText(getString(resId));
    }

    protected void setRightButtonText(String text) {
        if (rightButton != null && rightButton instanceof Button) {
            ((Button) rightButton).setText(text);
        }
    }

    protected void setLeftRightButtonText(int resId) {
        setLeftRightButtonText(getString(resId));
    }

    protected void setLeftRightButtonText(String text) {
        if (left_rightButton != null && left_rightButton instanceof Button) {
            ((Button) left_rightButton).setText(text);
        }
    }

    /**
     * 设置 rightButton 的图片资源
     */
    protected void setRightButtonImage(int resId) {
        if (rightButton != null && rightButton instanceof ImageButton) {
            ((ImageButton) rightButton).setImageResource(resId);
        } else if (rightButton != null && rightButton instanceof Button) {
            rightButton.setBackgroundResource(resId);
        }
    }

    protected void setLeftRightButtonImage(int resId) {
        if (left_rightButton != null && left_rightButton instanceof ImageButton) {
            ((ImageButton) left_rightButton).setImageResource(resId);
        } else if (left_rightButton != null && left_rightButton instanceof Button) {
            left_rightButton.setBackgroundResource(resId);
        }
    }

//    @Override
//    public void onBackPressed() {
////        if (getSwipeHelper() != null) {
//////            getSwipeHelper().onError();
////        }
////        super.onBackPressed();
//    }

    /**
     * 当listview为空时，进行页面展示处理
     *
     * @param object  listView、expandableListView、GridView
     * @param no_data (0暂无数据，点击和下拉是可刷新)(1加载失败，请检查网络或稍后重试)(2数据解析失败)
     * @param imgRes  资源图片的id
     * @param parent  要显示emptyView的容器，一般使用swipeToLoadLayout.getParent()
     *                （如果有swipeToLoadLayout），但其parent必须是Frame或Relative，否则会显示到全屏
     */
//    public void setEmptyView(Object object, String no_data, int imgRes, ViewGroup parent) {
//        emptyView.setOnClickListener(this);
//        TextView tv_empty = (TextView) emptyView.findViewById(R.id.tv_empty);
//        ImageView img_empty = (ImageView) emptyView.findViewById(R.id.img_empty);
//        if (no_data.equals(APPConstants.EMPTY_TYPE_NO_DATA)) {
//            tv_empty.setText(R.string.http_no_data);
//        } else if (no_data.equals(APPConstants.EMPTY_TYPE_HTTP_FAILURE)) {
//            tv_empty.setText(R.string.http_load_failure);
//        } else if (no_data.equals(APPConstants.EMPTY_TYPE_FORMAT_FAILURE)) {
//            tv_empty.setText(R.string.http_parsing_failure);
//        } else if (no_data.equals(APPConstants.EMPTY_TYPE_NO_MESSGE)) {
//            tv_empty.setText(R.string.not_messge);
//        } else {
//            tv_empty.setText(no_data);
//        }
//        if (imgRes != 0) {
//            img_empty.setImageResource(imgRes);
//        } else {
//            img_empty.setVisibility(View.GONE);
//        }
//
//        // 先移除
//        ViewParent newEmptyViewParent = emptyView.getParent();
//        if (null != newEmptyViewParent && newEmptyViewParent instanceof ViewGroup) {
//            ((ViewGroup) newEmptyViewParent).removeView(emptyView);
//        }
//
//        // 添加
//        if (parent != null && (parent instanceof FrameLayout || parent instanceof RelativeLayout)) {
//            parent.addView(emptyView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        } else {
//            addContentView(emptyView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        }
//
//        if (object instanceof ListView) {
//            ((ListView) object).setEmptyView(emptyView);
//        } else if (object instanceof GridView) {
//            ((GridView) object).setEmptyView(emptyView);
//        }
//    }

    /**
     * 当listview为空时，进行页面展示处理
     *
     * @param no_data (0暂无数据，点击和下拉是可刷新)(1加载失败，请检查网络或稍后重试)(2数据解析失败)
     * @param parent  要显示emptyView的容器，一般使用swipeToLoadLayout.getParent()
     *                （如果有swipeToLoadLayout），但其parent必须是Frame或Relative，否则会显示到全屏
     */
    public void setEmptyView(Object object, String no_data, ViewGroup parent) {
//        setEmptyView(object, no_data, 0, parent);
    }

    @Override
    protected void onStop() {
        super.onStop();

//        isShowGestureView = !SystemUtil.isAppOnForeground(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (getSwipeHelper() != null) {
//            getSwipeHelper().onError();
//            getSwipeHelper().destroy(getSwipeRefreshLayout(), getAutoLoadListView());
//        }
//        if (unbinder != null) {
//            unbinder.unbind();
//        }
//        LoadingDialog.destroy(this);
//        AlertDialog.destroy(this);
    }

//    public void showLoading(String msg) {
//        LoadingDialog.getInstance(this).show(msg);
//    }
//
//    public void showLoading(int resId) {
//        LoadingDialog.getInstance(this).show(resId);
//    }
//
//    public void dismissLoading() {
//        LoadingDialog.getInstance(this).dismiss();
//    }

    private boolean isShowing = false;

    public void showExitDialog(@StringRes int resId) {
        showExitDialog(getString(resId));
    }

    public void showExitDialog(String string) {
//        if (!isShowing) {
//            new AlertDialog(this).builder()
//                    .setMsg(string)
//                    .setCancelable(false)
//                    .setCancledOnTouchOutside(false)
//                    .setPositiveButton(getString(R.string.ok), v ->
//                            finish())
//                    .show();
//            isShowing = true;
//        }
    }

    public boolean isEmpty(List list) {
        return list == null || list.size() <= 0;
    }

    /**
     * 子类重写下面三个方法后将无需在destroy和back里判断swipeHelper的关闭和销毁情况
     */
//    protected SwipeHelper getSwipeHelper() {
//        return null;
//    }

    protected SwipeRefreshLayout getSwipeRefreshLayout() {
        return null;
    }

//    protected AutoLoadListView getAutoLoadListView() {
//        return null;
//    }

    protected BaseActivity getActivity() {
        return this;
    }

//    protected <T> ObservableTransformer<T, T> netWorkTransformer() {
//        return tObservable -> tObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }

//    protected <T extends BaseResult<R>,R> ObservableTransformer<T , R> preHanderServerResult() {
//        return new ObservableTransformer<T, R>() {
//            @Override
//            public ObservableSource<R> apply(io.reactivex.Observable<T> upstream) {
//                upstream.subscribe((BiConsumer<T, Throwable>) (t, throwable) -> {
//
//                });
//                return null;
//            }
//        }
//    }
}