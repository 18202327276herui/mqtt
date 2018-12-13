package usung.com.mqttclient.http.observers;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import usung.com.mqttclient.http.base.BaseResult;
import usung.com.mqttclient.widget.AlertDialog;
import usung.com.mqttclient.widget.LoadingDialog;

public abstract class NormalObserver<T extends BaseResult<R>, R> implements Observer<T> {
    private WeakReference<Context> context;
    private boolean showLoadingDialog;
    private String loadingMsg;
    private Context mContext;

    public NormalObserver(Context context) {
        this(context, false);
    }

    public NormalObserver(Context context, boolean showLoadingDialog) {
        this(context, showLoadingDialog, "");
    }

    public NormalObserver(Context context, boolean showLoadingDialog, String loadingMsg) {
        this.context = new WeakReference<>(context);
        this.showLoadingDialog = showLoadingDialog;
        this.loadingMsg = loadingMsg;
        this.mContext = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onSubscribe(Disposable d) {
        if (showLoadingDialog) {
            LoadingDialog.getInstance(context.get()).show(loadingMsg);
        }
    }

    @Override
    public void onNext(T t) {
        if (t.getError() == 0) {
            onSuccess(t.getItems(), t.getMsg(), t.getError(), t.getTotal());
        }/*else if (t.getError()==401){

        }*/ else {
            onFailure(t.getMsg(), t.getError(), t.getTotal());
        }
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
//        if (e instanceof SocketTimeoutException) {
//            msg = context.get().getString(com.usung.gxzy_logistics.R.string.http_failure_cause_time_out);
//        } else {
//            msg = context.get().getString(com.usung.gxzy_logistics.R.string.http_failure_cause_network);
//        }
        if (e instanceof SocketTimeoutException) {
            msg = context.get().getString(usung.com.mqttclient.R.string.http_failure_cause_time_out);
        } else if (e instanceof HttpException) {
            if (((HttpException) e).code() == 504) {
                msg = context.get().getString(usung.com.mqttclient.R.string.http_failure_cause_network);
            } else {
                msg = context.get().getString(usung.com.mqttclient.R.string.http_exception_cause_network);
            }
        } else {
            msg = context.get().getString(usung.com.mqttclient.R.string.http_exception_cause_network);
        }

        /**
         *  弹出提示框
         */
        AlertDialog.getInstance(context.get()).setMsg(msg);
        if (AlertDialog.getInstance(context.get()).isShowing()) {
            AlertDialog.getInstance(context.get()).dismiss();
        }
        AlertDialog.getInstance(context.get()).show();

//        onFailure(e.getMessage(), 404, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onComplete() {
        if (showLoadingDialog) {
            LoadingDialog.getInstance(context.get()).dismiss();
        }
    }

    public abstract void onSuccess(R r, String msg, int error, int total);

    public abstract void onFailure(String msg, int error, int total);
}
