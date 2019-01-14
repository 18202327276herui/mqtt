package usung.com.mqttclient.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Unbinder;
import usung.com.mqttclient.R;
import usung.com.mqttclient.widget.LoadingDialog;

/**
 * 基础Fragment Fragment都应该继承此类
 *
 * @author fenghui 2016.05.20
 */
public class BaseFragment extends Fragment implements OnClickListener {
    /**
     * 无数据
     */
    public final String EMPTY_TYPE_NO_DATA = "0";
    /**
     * 请求失败
     */
    public final String EMPTY_TYPE_HTTP_FAILURE = "1";
    /**
     * 解析失败
     */
    public final String EMPTY_TYPE_FORMAT_FAILURE = "2";
    /*标题*/
    protected TextView title;
    /*返回按钮*/
    protected View backButton;
    /* 右边按钮 */
    public View rightButton;
    /* 右边按钮的左边 */
    protected View left_rightButton;
    /*进度条对话框*/
    protected Dialog mydialog;
    private TextView txt_no_data;
    /*Fragment视图*/
    protected View fragmentView;
    /*ListView为空时的View*/
    protected View emptyView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i("生命周期", "f onAttach");
//        mydialog = new Dialog(activity, R.style.loadingDialog);
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        Log.i("生命周期", "f onCreate");
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);
        Log.i("生命周期", "f onActivityCreated");
    }

    /**
     * listview为空时点击刷新
     */
    protected void loadList() {

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("生命周期", "f onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("生命周期", "f onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("生命周期", "f onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("生命周期", "f onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("生命周期", "f onDestroyView");
//        if (getSwipeHelper() != null) {
//            getSwipeHelper().onError();
//            getSwipeHelper().destroy(getSwipeRefreshLayout(), getAutoLoadListView());
//        }
        if (getUnbinder() != null) {
            getUnbinder().unbind();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        LoadingDialog.destroy(getContext());
        Log.i("生命周期", "f onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("生命周期", "f onDetach");
    }

    protected Unbinder getUnbinder() {
        return null;
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

    //初始化页面view
    protected void initViews() {
//        //listview为空的视图
//        try {
//            emptyView = LayoutInflater.from(getActivity()).inflate(R.layout.view_empty, null);
//        } catch (NullPointerException title) {
//
//        }
    }

    public void showLoading(String msg) {
        LoadingDialog.getInstance(getContext()).show(msg);
    }

    public void dismissLoading() {
        LoadingDialog.getInstance(getContext()).dismiss();
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

    protected void setRightButtonImage(int resId) {
        if (rightButton != null && rightButton instanceof ImageButton) {
            ((ImageButton) rightButton).setImageResource(resId);
        }
    }

    protected void setLeftRightButtonImage(int resId) {
        if (left_rightButton != null && left_rightButton instanceof ImageButton) {
            ((ImageButton) left_rightButton).setImageResource(resId);
        }
    }

    public String returnString(int rid) {
        return getResources().getString(rid);
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_ll_emptyview:
//                loadList(); // 当页面没有数据时，可重写此方法
//                break;
//            case R.id.txt_no_data:
//                loadList();
//                break;
//            default:
//                break;
//        }
    }

//    /**
//     * 当listview为空时，进行页面展示处理
//     *
//     * @param mListView
//     * @param no_data   (0暂无数据，点击和下拉是可刷新)(1加载失败，请检查网络或稍后重试)(2数据解析失败)
//     * @param parent    要显示emptyView的容器，一般使用swipeToLoadLayout.getParent()，但其parent必须是Frame或Relative，否则会显示到全屏
//     */
//    protected void setEmptyView(ListView mListView, String no_data, ViewGroup parent) {
//        emptyView.setOnClickListener(this);
//        txt_no_data = (TextView) emptyView.findViewById(R.id.txt_no_data);
////        txt_no_data.setOnClickListener(this);
//        if (no_data.equals("0")) {
//            txt_no_data.setText(R.string.http_no_data);
//        } else if (no_data.equals("1")) {
//            txt_no_data.setText(R.string.http_load_failure);
//        } else if (no_data.equals("2")) {
//            txt_no_data.setText(R.string.http_parsing_failure);
//        } else {
//            txt_no_data.setText(no_data);
//        }
//
//        //先移除
//        ViewParent newEmptyViewParent = emptyView.getParent();
//        if (null != newEmptyViewParent && newEmptyViewParent instanceof ViewGroup) {
//            ((ViewGroup) newEmptyViewParent).removeView(emptyView);
//        }
//
//        //添加
//        if (parent != null && (parent instanceof FrameLayout || parent instanceof RelativeLayout)) {
//            parent.addView(emptyView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        } else {
//            getActivity().addContentView(emptyView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        }
//        mListView.setEmptyView(emptyView);
//    }

//    /**
//     * 当listview为空时，进行页面展示处理
//     *
//     * @param mListView
//     * @param no_data   (0暂无数据，点击和下拉是可刷新)(1加载失败，请检查网络或稍后重试)(2数据解析失败)
//     * @param parent    要显示emptyView的容器，一般使用swipeToLoadLayout.getParent()，但其parent必须是Frame或Relative，否则会显示到全屏
//     */
//    protected void setEmptyView(RecyclerView mListView, String no_data, ViewGroup parent) {
//        emptyView.setOnClickListener(this);
//        txt_no_data = (TextView) emptyView.findViewById(R.id.txt_no_data);
////        txt_no_data.setOnClickListener(this);
//        if (no_data.equals("0")) {
//            txt_no_data.setText(R.string.http_no_data);
//        } else if (no_data.equals("1")) {
//            txt_no_data.setText(R.string.http_load_failure);
//        } else if (no_data.equals("2")) {
//            txt_no_data.setText(R.string.http_parsing_failure);
//        } else {
//            txt_no_data.setText(no_data);
//        }
//
//        //先移除
//        ViewParent newEmptyViewParent = emptyView.getParent();
//        if (null != newEmptyViewParent && newEmptyViewParent instanceof ViewGroup) {
//            ((ViewGroup) newEmptyViewParent).removeView(emptyView);
//        }
//
//        //添加
//        if (parent != null && (parent instanceof FrameLayout || parent instanceof RelativeLayout)) {
//            parent.addView(emptyView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        } else {
//            getActivity().addContentView(emptyView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        }
//        mListView.addView(emptyView);
//    }

    public boolean isEmpty(List list) {
        return list == null || list.size() <= 0;
    }


    public void removeEmptyView(ListView mListView) {
        ViewParent newEmptyViewParent = emptyView.getParent();
        if (null != newEmptyViewParent && newEmptyViewParent instanceof ViewGroup) {
            ((ViewGroup) newEmptyViewParent).removeView(emptyView);
        }
        if (mListView != null) {
            mListView.setEmptyView(null);
        }
    }

    private boolean hasShownExitDialog = false;

    /**
     * 无法继续操作，点击确定退出当前activity
     *
     * @param text 提示文字
     */
    public void showExitDialog(String text) {
        if (getActivity() != null && !getActivity().isFinishing() && !hasShownExitDialog) {
            new AlertDialog.Builder(getActivity()).setCancelable(false)
                    .setMessage(text).setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    getActivity().fileList();
                }
            }).show();
            hasShownExitDialog = true;
        }
    }

    public void showExitDialog(int resId) {
        showExitDialog(getString(resId));
    }

    /**
     * 提示dialog
     */
    public void showTipsDialog(String text) {
        if (getActivity() != null && !getActivity().isFinishing()) {
            new AlertDialog.Builder(getActivity()).setCancelable(false)
                    .setMessage(text).setPositiveButton(getString(R.string.ok), null).show();
        }
    }

    public void showTipsDialog(int resId) {
        showTipsDialog(getString(resId));
    }
}