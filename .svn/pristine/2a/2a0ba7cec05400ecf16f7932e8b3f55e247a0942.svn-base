package usung.com.mqttclient;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import usung.com.mqttclient.adapter.AdapterChatRecyclerView;
import usung.com.mqttclient.base.APPConstants;
import usung.com.mqttclient.base.BaseActivity;
import usung.com.mqttclient.base.MqttHelper;
import usung.com.mqttclient.bean.HrMqttMessage;
import usung.com.mqttclient.bean.user.LoginResultData;
import usung.com.mqttclient.bean.user.OssLoginCredencial;
import usung.com.mqttclient.bean.user.UserSimpleInfo;
import usung.com.mqttclient.utils.UserUtil;

/**
 * @author herui
 */
public class ActivityChat extends BaseActivity {
    @BindView(R.id.header_title)
    TextView headerTitle;
    @BindView(R.id.et_message)
    EditText etMessage;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.rv_listview)
    RecyclerView mRecyclerView;
    private AdapterChatRecyclerView adapterChatRceyclerView;
    private List<HrMqttMessage> messageLists = new ArrayList<>();
    private MqttHelper mqttHelper;
    private MqttAndroidClient mqttAndroidClient;
    final String subscriptionTopic = "369";
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APPConstants.UPDATE_RECYCLEVIEW:
                    Bundle bundle = msg.getData();
                    HrMqttMessage hrMqttMessage = new HrMqttMessage();
                    hrMqttMessage.setText("Message Arreved: " + subscriptionTopic + " : " + bundle.get("data"));
                    hrMqttMessage.setType(APPConstants.TYPE_LEFT_TEXT);
                    messageLists.add(hrMqttMessage);
                    adapterChatRceyclerView.notifyDataSetChanged();
                    mRecyclerView.smoothScrollToPosition(messageLists.size());
                    break;
                case 2:
                    Bundle bundle1 = msg.getData();
                    messageLists.add((HrMqttMessage) bundle1.getSerializable("data"));
                    adapterChatRceyclerView.notifyDataSetChanged();
                    mRecyclerView.smoothScrollToPosition(messageLists.size());
                    break;
                default:
                    break;
            }
        }
    };

    private OSS oss;
    private LoginResultData loginResultData;
    private OssLoginCredencial ossLoginCredencial;
    private static final int RESULT_LOAD_IMAGE = 1;
    private String mPicturePath = "";
    private UserSimpleInfo userSimpleInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        userSimpleInfo = (UserSimpleInfo) getIntent().getSerializableExtra("userSimpleInfo");
        mqttHelper = MqttHelper.getInstance(getActivity());
        mqttAndroidClient = mqttHelper.getMqttAndroidClient();
        loginResultData = UserUtil.getUser(getActivity());
        ossLoginCredencial = loginResultData.getOssCredencial();
        initViews();
        initOss();
    }

    @Override
    public void initViews() {
        super.initViews();

        headerTitle.setText(userSimpleInfo.getNickName());
        adapterChatRceyclerView = new AdapterChatRecyclerView(getActivity(), messageLists);
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(adapterChatRceyclerView);

        try {
            mqttAndroidClient.subscribe(subscriptionTopic, 0, new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    // message Arrived!
                    Log.e("Chat messageArrived", "Message: " + topic + " : " + new String(message.getPayload()));
                    Message message1 = new Message();
                    message1.what = APPConstants.UPDATE_RECYCLEVIEW;
                    Bundle bundle = new Bundle();
                    bundle.putString("data", new String(message.getPayload()));
                    message1.setData(bundle);
                    handler.sendMessage(message1);
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化oss
     */
    public void initOss() {
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(ossLoginCredencial.getId(), ossLoginCredencial.getKey());
        oss = new OSSClient(getApplicationContext(), ossLoginCredencial.getHost(), credentialProvider);
    }

    @OnClick({R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                HrMqttMessage hrMqttMessage = new HrMqttMessage();
                hrMqttMessage.setText(etMessage.getText().toString());
                hrMqttMessage.setType(APPConstants.TYPE_RIGHT_TEXT);
                messageLists.add(hrMqttMessage);
                adapterChatRceyclerView.notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition(messageLists.size());
                mqttHelper.publishMessage(etMessage.getText().toString());

//                // 传图片
//                Intent i = new Intent(
//                        Intent.ACTION_PICK,
//                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // 传文件
//                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//                i.setType("video/*;image/*");
//                i.addCategory(Intent.CATEGORY_OPENABLE);

//                startActivityForResult(i, RESULT_LOAD_IMAGE);
                break;
            default:
                break;
        }
    }

    /**
     * 上传文件
     */
    public void putObject() {
        //构造上传请求
        PutObjectRequest put = new PutObjectRequest(ossLoginCredencial.getBucket(), "test", mPicturePath);
        //异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.e("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
            }
        });
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                //请求成功
                Log.e("PutObject", "UploadSuccess");
                Log.e("ETag", result.getETag());
                Log.e("RequestId", result.getRequestId());
                getObject();
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientException, ServiceException serviceException) {
                // 请求异常
                if (clientException != null) {
                    // 本地异常如网络异常等
                    clientException.printStackTrace();
                }

                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }

    /**
     * 下载文件
     */
    public void getObject() {
        // 构造下载文件请求
        GetObjectRequest get = new GetObjectRequest(ossLoginCredencial.getBucket(), "test");

        // 设置下载进度回调
        get.setProgressListener(new OSSProgressCallback<GetObjectRequest>() {
            @Override
            public void onProgress(GetObjectRequest request, long currentSize, long totalSize) {
                Log.e("GetObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
            }
        });

        OSSAsyncTask task = oss.asyncGetObject(get, new OSSCompletedCallback<GetObjectRequest, GetObjectResult>() {
            @Override
            public void onSuccess(GetObjectRequest request, GetObjectResult result) {
                //请求成功
                InputStream inputStream = result.getObjectContent();
                try {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[2048];
                    int len;
                    //需要根据对应的View大小来自适应缩放
                    while ((len = inputStream.read(buffer)) != -1) {
                        //处理下载的数据
                        outputStream.write(buffer, 0, len);
                    }
                    outputStream.close();
                    inputStream.close();

//                    final BitmapFactory.Options options = new BitmapFactory.Options();
//                    options.inJustDecodeBounds = true;
//                    options.inSampleSize = calculateInSampleSize(options, 240, 240);
//                    options.inJustDecodeBounds = false;

                    Bitmap bitmap = BitmapFactory.decodeByteArray(outputStream.toByteArray(), 0, outputStream.toByteArray().length);

                    HrMqttMessage hrMqttMessage = new HrMqttMessage();
                    hrMqttMessage.setText(etMessage.getText().toString());
                    hrMqttMessage.setBitmap(bitmap);
                    hrMqttMessage.setType(APPConstants.TYPE_LEFT_IMAGE);

                    Message message1 = new Message();
                    message1.what = 2;
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", hrMqttMessage);
                    message1.setData(bundle);
                    handler.sendMessage(message1);

//                    messageLists.add(hrMqttMessage);
//                    adapterChatRceyclerView.notifyDataSetChanged();
//                    mRecyclerView.smoothScrollToPosition(messageLists.size());
//                    mqttHelper.publishMessage(etMessage.getText().toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(GetObjectRequest request, ClientException clientException, ServiceException serviceException) {
                // 请求异常
                if (clientException != null) {
                    // 本地异常如网络异常等
                    clientException.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }

    //计算图片缩放比例
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            mPicturePath = cursor.getString(columnIndex);
            Log.d("PickPicture", mPicturePath);
            cursor.close();
            //这是本机的图片路径
            String img_url = selectedImage.getPath();
            ContentResolver cr = this.getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(selectedImage));

                HrMqttMessage hrMqttMessage = new HrMqttMessage();
                hrMqttMessage.setText(etMessage.getText().toString());
                hrMqttMessage.setBitmap(bitmap);
                hrMqttMessage.setType(APPConstants.TYPE_RIGHT_IMAGE);
                messageLists.add(hrMqttMessage);
                adapterChatRceyclerView.notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition(messageLists.size());
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            }

            putObject();
        }
    }
}
