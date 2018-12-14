package usung.com.mqttclient.http.observers;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import usung.com.mqttclient.http.base.BaseResult;
import usung.com.mqttclient.widget.LoadingDialog;

public abstract class NoBaseResultObserver<R> implements Observer<R> {
    private WeakReference<Context> context;
    private boolean showLoadingDialog;
    private String loadingMsg;

    public NoBaseResultObserver(Context context) {
        this(context, false);
    }

    public NoBaseResultObserver(Context context, boolean showLoadingDialog) {
        this(context, showLoadingDialog, "");
    }

    public NoBaseResultObserver(Context context, boolean showLoadingDialog, String loadingMsg) {
        this.context = new WeakReference<>(context);
        this.showLoadingDialog = showLoadingDialog;
        this.loadingMsg = loadingMsg;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onSubscribe(Disposable d) {
        if (showLoadingDialog) {
            LoadingDialog.getInstance(context.get()).show(loadingMsg);
        }
    }

    @Override
    public void onNext(R t) {
        if (context.get() == null || context.get() instanceof Activity && ((Activity) context.get()).isFinishing()) {
            return;
        }
        onResponse(t);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onError(Throwable e) {
        if (context.get() == null || context.get() instanceof Activity && ((Activity) context.get()).isFinishing()) {
            return;
        }
        /**
         *  转圈消失
         */
        LoadingDialog.getInstance(context.get()).dismiss();
        String msg = "";
        /**
         *  如果是超时
         */
        /*if (e instanceof SocketTimeoutException) {
            msg = context.get().getString(com.usung.gxzy_logistics.R.string.http_failure_cause_time_out);
        } else if (e instanceof HttpException) {
            if (((HttpException) e).code() == 504) {
                msg = context.get().getString(com.usung.gxzy_logistics.R.string.http_failure_cause_network);
            } else {
                msg = context.get().getString(com.usung.gxzy_logistics.R.string.http_exception_cause_network);
            }
        } else {
            msg = context.get().getString(com.usung.gxzy_logistics.R.string.http_exception_cause_network);
        }*/
        /**
         *  弹出提示框
         */
        /*AlertDialog.getInstance(context.get()).setMsg(msg);
        if (AlertDialog.getInstance(context.get()).isShowing()) {
            AlertDialog.getInstance(context.get()).dismiss();
        }
        AlertDialog.getInstance(context.get()).show();*/
//        if (showLoadingDialog) {
//            LoadingDialog.getInstance(context.get()).dismiss();
//        }
//        String msg;
//        if (!NetWorkUtil.isNetConnected(BaseApplication.getInstance())) {
//            msg = context.get().getString(com.usung.gxzy_logistics.R.string.http_failure_cause_network);
//        } else {
//            msg = e.getMessage();
//        }
//        onFailure(msg, 404, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onComplete() {
        if (showLoadingDialog) {
            LoadingDialog.getInstance(context.get()).dismiss();
        }
    }

    /**
     *  返回的结果
     * @param r  返回的结果类
     */
    public abstract void onResponse(R r);
}
