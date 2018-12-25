package usung.com.mqttclient.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import usung.com.mqttclient.R;

/**
 * 仿ios对话框
 * created by xiezhenyu on 2016/6/1
 */
public class AlertDialog {
    private static AlertDialog instance;
    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private TextView txt_title;
    private TextView txt_msg;
    private View line_horizontal;
    private Button btn_neg;
    private Button btn_pos;
    private ImageView img_line;
    private DisplayMetrics dm;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;

    public static AlertDialog getInstance(Context context) {
        if (instance == null) {
            synchronized (AlertDialog.class) {
                if (instance == null) {
                    instance = new AlertDialog(context).builder();
                    instance.setCancelable(false)
                            .setCancledOnTouchOutside(false)
                            .setPositiveButton(context.getString(R.string.ok), new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            });
                }
            }
        }
        if (instance.context == null || instance.context != context) {
            try {
                instance.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
            instance = new AlertDialog(context).builder();
            instance.setCancelable(false)
                    .setCancledOnTouchOutside(false)
                    .setPositiveButton(context.getString(R.string.ok), new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    });
        }
        return instance;
    }

    /**
     * 销毁context引用
     */
    public static void destroy(Context context) {
        if (instance != null && context == instance.context) {
            instance.context = null;
        }
    }

    public AlertDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
    }

    public AlertDialog builder() {
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_alertdialog, (ViewGroup) dialog.getWindow().getDecorView(), false);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setVisibility(View.GONE);
        txt_msg = (TextView) view.findViewById(R.id.txt_msg);
        txt_msg.setVisibility(View.GONE);
        line_horizontal = view.findViewById(R.id.line_horizontal);
        btn_neg = (Button) view.findViewById(R.id.btn_neg);
        btn_neg.setVisibility(View.GONE);
        btn_pos = (Button) view.findViewById(R.id.btn_pos);
        btn_pos.setVisibility(View.GONE);
        img_line = (ImageView) view.findViewById(R.id.img_line);
        img_line.setVisibility(View.GONE);

        // 定义Dialog布局和参数
        dialog.setContentView(view);

        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (dm.widthPixels * 0.85), LayoutParams.WRAP_CONTENT));
        return this;
    }

    public AlertDialog setTitle(String title) {
        showTitle = true;
        if ("".equals(title)) {
            txt_title.setText("标题");
        } else {
            txt_title.setText(title);
        }
        return this;
    }

    public AlertDialog setTitle(String title, int style) {
        showTitle = true;
        if ("".equals(title)) {
            txt_title.setText("标题");
        } else {
            txt_title.setText(title);
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            txt_title.setTextAppearance(style);
//        } else {
        txt_title.setTextAppearance(context, style);
//        }
        return this;
    }

    public AlertDialog setMsg(String msg) {
        showMsg = true;
        if ("".equals(msg)) {
            txt_msg.setText("内容");
        } else {
            txt_msg.setText(msg);
        }
        return this;
    }

    public AlertDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public AlertDialog setCancledOnTouchOutside(boolean cancledOnTouchOutside) {
        dialog.setCanceledOnTouchOutside(false);
        return this;
    }

    public AlertDialog setPositiveButton(String text,
                                         final OnClickListener listener) {
        showPosBtn = true;
        if (TextUtils.isEmpty(text)) {
            btn_pos.setText("确定");
        } else {
            btn_pos.setText(text);
        }
        btn_pos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                }
                dialog.dismiss();
            }
        });
        return this;
    }

    public AlertDialog setNegativeButton(String text,
                                         final OnClickListener listener) {
        showNegBtn = true;
        if (TextUtils.isEmpty(text)) {
            btn_neg.setText("取消");
        } else {
            btn_neg.setText(text);
        }
        btn_neg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                }
                dialog.dismiss();
            }
        });
        return this;
    }

    public AlertDialog setNegativeButton() {
        return setNegativeButton("", null);
    }

    private void setLayout() {
        if (!showTitle && !showMsg) {
            txt_title.setText("提示");
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showTitle) {
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showMsg) {
            txt_msg.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && !showNegBtn) {
            dialog.setCancelable(true);
            line_horizontal.setVisibility(View.GONE);
//            btn_pos.setText("确定");
//            btn_pos.setVisibility(View.VISIBLE);
//            btn_pos.setBackgroundResource(R.drawable.alertdialog_single_selector);
//            btn_pos.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
        }

        if (showPosBtn && showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.alertdialog_right_selector);
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.alertdialog_left_selector);
            img_line.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.alertdialog_single_selector);
        }

        if (!showPosBtn && showNegBtn) {
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.alertdialog_single_selector);
        }
    }

    public void show() {
        setLayout();
        if (dialog != null && context != null && dialog.getWindow() != null) {
            dialog.show();
        }
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }
}
