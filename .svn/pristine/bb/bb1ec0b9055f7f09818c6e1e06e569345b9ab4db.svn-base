package usung.com.mqttclient.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Descriptions: 动态注册权限
 * Created by fenghui on 2018/3/23.
 */

public class PermissionHelper {

    private Context context = null;

    private static volatile PermissionHelper instance = null;

    private PermissionHelper(){

    }

    /**
     * 单例的双锁模式
     * @return
     */
    public static PermissionHelper getInstance(){
        if (instance == null){
            synchronized (PermissionHelper.class){
                if (instance == null){
                    instance = new PermissionHelper();
                }
            }
        }
        return instance;
    }

    public PermissionHelper builder(Context context){
        this.context = context;
        return getInstance();
    }

    /**
     * 是否需要检查权限
     */
    public static boolean needCheckPermission() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * 是否拥有权限
     */
    public static boolean hasPermissons(Context context, String... permissions) {
        if (!needCheckPermission()) {
            return true;
        }
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 申请权限<br/>
     * 使用onRequestPermissionsResult方法，实现回调结果或者自己普通处理
     *
     * @return 是否已经获取权限
     */
    public static boolean requestPerssions(Activity activity, int requestCode, String... permissions) {
        if (!needCheckPermission()) {
            return true;
        }
        if (!hasPermissons(activity, permissions)) {
           /* if (deniedRequestPermissonsAgain(activity, permissions)) { // 点击了权限申请对话框中的“禁止后不再询问”复选框
                startApplicationDetailsSettings(activity, requestCode);
                //返回结果onActivityResult
            } else {*/
                List<String> deniedPermissions = getDeniedPermissions(activity, permissions);
                if (deniedPermissions != null && !deniedPermissions.isEmpty()) {
                    ActivityCompat.requestPermissions(activity, deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
                    //返回结果onRequestPermissionsResult
                }
//            }
            return false;
        }
        return true;
    }

    /**
     * 是否拒绝了再次申请权限的请求对话框的弹出（点击了权限申请对话框中的“禁止后不再询问”复选框）
     * @param activity
     * @param permissions 需要申请的权限集合
     * @return
     */
    public static boolean deniedRequestPermissonsAgain(Activity activity, String... permissions) {
        if (!needCheckPermission()) {
            return false;
        }
        List<String> deniedPermissions = getDeniedPermissions(activity, permissions);
        for (String permission : deniedPermissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_DENIED) {

                if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                    //当用户之前已经请求过该权限并且拒绝了授权这个方法返回true
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取被禁止的权限
     * @param activity
     * @param permissions 需要申请的权限集合
     * @return
     */
    public static List<String> getDeniedPermissions(Activity activity, String... permissions) {
        if (!needCheckPermission()) {
            return null;
        }
        List<String> deniedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permission);
            }
        }
        if (!deniedPermissions.isEmpty()) {
            return deniedPermissions;
        }
        return null;
    }

    /**
     * 申请权限后的返回方法，此方法需要在Activity中的onRequestPermissionsResult回调方法中调用
     * @param requestCode
     * @param permissions
     * @param grantResults
     * @param callBack
     */
    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults,
                                                  OnRequestPermissionsResultCallbacks callBack) {
        // Make a collection of granted and denied permissions from the request.
        List<String> granted = new ArrayList<>();
        List<String> denied = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            String perm = permissions[i];
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                granted.add(perm);
            } else {
                denied.add(perm);
            }
        }

        if (null != callBack) {
            if (!granted.isEmpty()) {
                callBack.onPermissionsGranted(requestCode, granted, denied.isEmpty());
            }
            if (!denied.isEmpty()) {
                callBack.onPermissionsDenied(requestCode, denied, granted.isEmpty());
            }
        }
    }

    /**
     * 申请权限返回
     */
    public interface OnRequestPermissionsResultCallbacks {
        /**
         * @param isAllGranted 是否全部同意（是否有被申请通过的权限）
         */
        void onPermissionsGranted(int requestCode, List<String> perms, boolean isAllGranted);

        /**
         * @param isAllDenied 是否全部拒绝（是否有被拒绝的权限）
         */
        void onPermissionsDenied(int requestCode, List<String> perms, boolean isAllDenied);
    }

    /**
     * 所需权限未全部打开时弹出此对话框，用于提示用户是否开启所需权限
     * @param context
     * @param message
     * @param okListener
     */
    public static void showMessageOKCancel(final Activity context, String message, DialogInterface.OnClickListener okListener,
                                            DialogInterface.OnClickListener cancelListener) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setPositiveButton("是", okListener)
                .setNegativeButton("否", cancelListener)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setMessage(message);
        alertDialog.show();
    }

    /**
     * 打开app详细设置界面<br/>
     * <p>
     * 在 onActivityResult() 中没有必要对 resultCode 进行判断，因为用户只能通过返回键才能回到我们的 App 中，<br/>
     * 所以 resultCode 总是为 RESULT_CANCEL，所以不能根据返回码进行判断。<br/>
     * 在 onActivityResult() 中还需要对权限进行判断，因为用户有可能没有授权就返回了！<br/>
     */
    public static void startApplicationDetailsSettings(Activity activity, int requestCode) {
        Toast.makeText(activity, "点击权限，并打开全部权限", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 打开APP权限设置界面
     * @param activity 上下文
     * @param className 即将要跳转的界面
     * @param message 提示语
     * @param directlyToNextActivity true:无论应用所需要的权限是否已全部开启，点击是否按钮后都能直接进入下一个界面
     */
    public static void openSettingActivity(final Activity activity, final String className, String message, final boolean directlyToNextActivity) {
        showMessageOKCancel(activity, message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (!className.equals(activity.getLocalClassName())) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                    intent.setData(uri);
                    activity.startActivity(intent);
                    activity.finish();
                }
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (directlyToNextActivity){
//                    activity.startActivity(new Intent(activity, className.getClass()));
                    activity.finish();
                }
            }
        });
    }

    /**
     * 获读写日历权限
     * 所申请的权限需要同时在清单文件中注册
     * @return 是否已经获取权限，没有自动申请
     */
    public static boolean getCalendarPermissions(@NonNull Activity activity, int requestCode) {
        return requestPerssions(activity, requestCode, PERMISSION_READ_CALENDAR, PERMISSION_WRITE_CALENDAR);
    }

    /**
     * 获取拍照权限
     * 获取拍照权限时需要用到sd存储卡读写权限，所以需一并开启，所申请的权限需要同时在清单文件中注册
     * @return 是否已经获取权限，没有自动申请
     */
    public static boolean getCameraPermissions(@NonNull Activity activity, int requestCode) {
        return requestPerssions(activity, requestCode, PERMISSION_CAMERA, PERMISSION_READ_EXTERNAL_STORAGE, PERMISSION_WRITE_EXTERNAL_STORAGE);
    }

    /**
     * 获取读取联系人权限
     * 所申请的权限需要同时在清单文件中注册
     * @return 是否已经获取权限，没有自动申请
     */
    public static boolean getContactsPermissions(@NonNull Activity activity, int requestCode) {
        return requestPerssions(activity, requestCode, PERMISSION_READ_CONTACTS, PERMISSION_WRITE_CONTACTS, PERMISSION_GET_ACCOUNTS);
    }

    /**
     * 获取定位权限
     * 所申请的权限需要同时在清单文件中注册
     * @return 是否已经获取权限，没有自动申请
     */
    public static boolean getLocationPermissions(@NonNull Activity activity, int requestCode) {
        return requestPerssions(activity, requestCode, PERMISSION_ACCESS_COARSE_LOCATION, PERMISSION_ACCESS_COARSE_LOCATION);
    }

    /**
     * 获取麦克风权限
     * 获取麦克风权限时需要用到sd存储卡读写权限，所以需一并开启，所申请的权限需要同时在清单文件中注册
     * @return 是否已经获取权限，没有自动申请
     */
    public static boolean getAudioPermissions(@NonNull Activity activity, int requestCode) {
        return requestPerssions(activity, requestCode, PERMISSION_RECORD_AUDIO, PERMISSION_READ_EXTERNAL_STORAGE, PERMISSION_WRITE_EXTERNAL_STORAGE);
    }

    /**
     * 获取拨打电话权限
     * 所申请的权限需要同时在清单文件中注册
     * @return 是否已经获取权限，没有自动申请
     */
    public static boolean getCallPhonePermissions(@NonNull Activity activity, int requestCode) {
        return requestPerssions(activity, requestCode, PERMISSION_READ_PHONE_STATE, PERMISSION_CALL_PHONE, PERMISSION_READ_CALL_LOG,
                PERMISSION_WRITE_CALL_LOG, PERMISSION_ADD_VOICEMAIL, PERMISSION_USE_SIP, PERMISSION_PROCESS_OUTGOING_CALLS);
    }

    /**
     * 获取 SENSORS传感器 权限
     * 所申请的权限需要同时在清单文件中注册
     * @return 是否已经获取权限，没有自动申请
     */
    public static boolean getSENSORSPermissions(@NonNull Activity activity, int requestCode) {
        return requestPerssions(activity, requestCode, PERMISSION_BODY_SENSORS);
    }

    /**
     * 获取SMS 短讯服务权限
     * 所申请的权限需要同时在清单文件中注册
     * @return 是否已经获取权限，没有自动申请
     */
    public static boolean getSMSPermissions(@NonNull Activity activity, int requestCode) {
        return requestPerssions(activity, requestCode, PERMISSION_SEND_SMS, PERMISSION_RECEIVE_SMS,
                PERMISSION_READ_SMS, PERMISSION_RECEIVE_WAP_PUSH, PERMISSION_RECEIVE_MMS);
    }

    /**
     * 获取sd存储卡读写权限
     * 所申请的权限需要同时在清单文件中注册
     * @return 是否已经获取权限，没有自动申请
     */
    public static boolean getExternalStoragePermissions(@NonNull Activity activity, int requestCode) {
        return requestPerssions(activity, requestCode, PERMISSION_READ_EXTERNAL_STORAGE, PERMISSION_WRITE_EXTERNAL_STORAGE);
    }


    public static final int CODE_CALENDAR = 0;
    public static final int CODE_CAMERA = 1;
    public static final int CODE_ACCOUNTS = 2;
    public static final int CODE_LOCATION = 3;
    public static final int CODE_MICROPHONE = 4;
    public static final int CODE_PHONE = 5;
    public static final int CODE_SENSORS = 6;
    public static final int CODE_SMS = 7;
    public static final int CODE_STORAGE = 8;
    public static final int CODE_MULTI_PERMISSION = 100;

    // 总共9组危险权限
    // CALENDAR 日期
    public static final String PERMISSION_READ_CALENDAR = Manifest.permission.READ_CALENDAR;
    public static final String PERMISSION_WRITE_CALENDAR = Manifest.permission.WRITE_CALENDAR;
    // CAMERA 相机
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    // CONTACTS 联系人
    public static final String PERMISSION_READ_CONTACTS = Manifest.permission.READ_CONTACTS;
    public static final String PERMISSION_WRITE_CONTACTS = Manifest.permission.WRITE_CONTACTS;
    public static final String PERMISSION_GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS;
    // LOCATION 位置
    public static final String PERMISSION_ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String PERMISSION_ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    // MICROPHONE 麦克风
    public static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    // PHONE 电话
    public static final String PERMISSION_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String PERMISSION_CALL_PHONE = Manifest.permission.CALL_PHONE;
    public static final String PERMISSION_READ_CALL_LOG = Manifest.permission.READ_CALL_LOG;
    public static final String PERMISSION_WRITE_CALL_LOG = Manifest.permission.WRITE_CALL_LOG;
    public static final String PERMISSION_ADD_VOICEMAIL = Manifest.permission.ADD_VOICEMAIL;
    public static final String PERMISSION_USE_SIP = Manifest.permission.USE_SIP;
    public static final String PERMISSION_PROCESS_OUTGOING_CALLS = Manifest.permission.PROCESS_OUTGOING_CALLS;
    // SENSORS 传感器
    public static final String PERMISSION_BODY_SENSORS = Manifest.permission.BODY_SENSORS;
    // SMS 短讯服务
    public static final String PERMISSION_SEND_SMS = Manifest.permission.SEND_SMS;
    public static final String PERMISSION_RECEIVE_SMS = Manifest.permission.RECEIVE_SMS;
    public static final String PERMISSION_READ_SMS = Manifest.permission.READ_SMS;
    public static final String PERMISSION_RECEIVE_WAP_PUSH = Manifest.permission.RECEIVE_WAP_PUSH;
    public static final String PERMISSION_RECEIVE_MMS = Manifest.permission.RECEIVE_MMS;
    // STORAGE 访问内存
    public static final String PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
}