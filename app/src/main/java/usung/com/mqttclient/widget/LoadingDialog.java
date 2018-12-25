package usung.com.mqttclient.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import usung.com.mqttclient.MainActivity;
import usung.com.mqttclient.R;

/**
 * 加载对话框
 * Created by xiezhenyu on 2017/3/30.
 */
public class LoadingDialog extends Dialog {
    private static LoadingDialog instance;
    private Context context;
    //    private ImageView img;
//    private ProgressBar progressBar;
    private TextView tv;
//    private MyAnimationDrawable anim;

    public static LoadingDialog getInstance(Context context) {
        if (instance == null) {
            synchronized (LoadingDialog.class) {
                if (instance == null) {
                    instance = new LoadingDialog(context);
                }
            }
        }
        if (instance.context == null || instance.context != context) {
            try {
                instance.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
            instance = new LoadingDialog(context);
        }
        return instance;
    }

    private LoadingDialog(Context context) {
        super(context, R.style.loadingDialog);
        this.context = context;
        init();
    }

    /**
     * 销毁context引用
     */
    public static void destroy(Context context) {
        if (instance != null && context == instance.context) {
            instance.context = null;
        }
    }

    private void init() {
        if (getWindow() != null) {
            View layout = LayoutInflater.from(context).inflate(R.layout.dialog_loading1, (ViewGroup) getWindow().getDecorView(), false);
            tv = (TextView) layout.findViewById(R.id.txt_text);
//            img = (ImageView) layout.findViewById(R.id.progressBar1);
//            progressBar = layout.findViewById(R.id.progressBar1);
//            int sWidth = ScreenUtils.getScreenWidth(getContext());
//            int sHeight = ScreenUtils.getScreenHeight(getContext());
            setContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT/*(int) ((Math.min(sWidth, sHeight)) * 0.3)*/, ViewGroup.LayoutParams.WRAP_CONTENT));
            setCanceledOnTouchOutside(false);

            // 屏蔽主页加载对话框显示时的返回键
            if (context instanceof MainActivity) {
                setCancelable(false);
            }
            setOnKeyListener((dialog, keyCode, event) -> {
                // 屏蔽主页加载对话框显示时的返回键
                if (context instanceof MainActivity) {
                    return true;
                }
                // 点击非主页（ActivityMain）界面的返回键，都结束当前Activity
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dismiss();
                    if (context instanceof Activity) {
                        ((Activity) context).finish();
                    }
                }
                return true;
            });
            setOnDismissListener(dialog -> {
//                stopAnim();
//                    instance.context = null;
            });
        }
    }

    @Override
    public void show() {
//        super.show();
        show("");
    }

    public void show(int resId) {
        show(getContext().getString(resId));
    }

    public void show(String str) {
        if (TextUtils.isEmpty(str)) {
            tv.setVisibility(View.GONE);
        } else {
            tv.setText(str);
        }
//        startAnim();
        if (context != null && !((Activity)context).isFinishing()) {
            super.show();
        }
    }

//    /**
//     * 开始旋转动画
//     */
//    private void startAnim() {
//        if (anim == null) {
//            anim = new MyAnimationDrawable();
//        }
//        anim.animateRawManuallyFromXML(R.drawable.loading, img, null, null);
//    }

//    /**
//     * 结束旋转动画
//     */
//    private void stopAnim() {
//        if (anim != null) {
//            anim.stop();
//        }
//    }
}
