package usung.com.mqttclient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
import com.google.gson.reflect.TypeToken;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.litepal.LitePal;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import io.github.rockerhieu.emojicon.emoji.Emojicon;
import io.github.rockerhieu.emojiconize.Emojiconize;
import usung.com.mqttclient.adapter.AdapterChatRecyclerView;
import usung.com.mqttclient.adapter.FuncPagerAdapter;
import usung.com.mqttclient.base.APPConstants;
import usung.com.mqttclient.base.BaseActivity;
import usung.com.mqttclient.base.BaseFragment;
import usung.com.mqttclient.base.MqttHelper;
import usung.com.mqttclient.bean.HrMqttMessage;
import usung.com.mqttclient.bean.db.HistoryMessage;
import usung.com.mqttclient.bean.db.InitiaDataResult;
import usung.com.mqttclient.bean.db.KeyValue;
import usung.com.mqttclient.bean.user.LoginResultData;
import usung.com.mqttclient.bean.user.OssLoginCredencial;
import usung.com.mqttclient.bean.user.UserSimpleInfo;
import usung.com.mqttclient.bean.user.UserStateInfo;
import usung.com.mqttclient.fragment.Func1Fragment;
import usung.com.mqttclient.fragment.Func2Fragment;
import usung.com.mqttclient.utils.GsonHelper;
import usung.com.mqttclient.utils.InitiadataUtil;
import usung.com.mqttclient.utils.TimeHelper;
import usung.com.mqttclient.utils.UserUtil;
import usung.com.mqttclient.widget.DotView;

/**
 * @author herui
 */
public class ActivityChat extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {
    @BindView(R.id.header_title)
    TextView headerTitle;
    @BindView(R.id.et_message)
    EditText etMessage;
    @BindView(R.id.rv_listview)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout mRefreshLayou;
    @BindView(R.id.ivEmo)
    ImageView ivEmo;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.vpFunc)
    ViewPager mVpFunc;
    @BindView(R.id.dv)
    DotView mDv;
    @BindView(R.id.flBottom)
    FrameLayout frameLayout;
    /**
     * 聊天界面适配器
     */
    private AdapterChatRecyclerView adapterChatRceyclerView;
    /**
     * mqtt消息列表
     */
    private List<HrMqttMessage> messageLists = new ArrayList<>();
    /**
     * mqtt帮助类
     */
    private MqttHelper mqttHelper;
    /**
     * mqtt客户端
     */
    private MqttAndroidClient mqttAndroidClient;
    /**
     * 订阅的主题
     */
    private String subscriptionTopic = "";
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 更新列表
                case APPConstants.UPDATE_RECYCLEVIEW:
                    Bundle bundle = msg.getData();
                    HrMqttMessage hrMqttMessage = new HrMqttMessage();
                    hrMqttMessage = GsonHelper.getGson().fromJson((String) bundle.get("data"), HrMqttMessage.class);
                    hrMqttMessage.setContent("Message Arreved: " + hrMqttMessage.getContent());
                    hrMqttMessage.setType(APPConstants.TYPE_LEFT_TEXT);
                    hrMqttMessage.setTime(TimeHelper.getSystemTime());
                    messageLists.add(hrMqttMessage);
                    adapterChatRceyclerView.notifyDataSetChanged();
                    mRecyclerView.smoothScrollToPosition(messageLists.size());
                    break;
                // 更新列表  - -图片
                case APPConstants.UPDATE_RECYCLEVIEW_IMG:
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
    /**
     * 阿里云oss
     */
    private OSS oss;
    /**
     * 登录返回结果
     */
    private LoginResultData loginResultData;
    /**
     * 初始化数据
     */
    private InitiaDataResult initiaDataResult;
    /**
     * oss 登录参数
     */
    private OssLoginCredencial ossLoginCredencial;
    /**
     * 图片路径
     */
    private String mPicturePath = "";
    /**
     * 用户简要信息
     */
    private UserSimpleInfo userSimpleInfo;
    /**
     * 用户主题
     */
    private String topic = "";
    /**
     * 主题列表
     */
    private List<KeyValue> keyValues = new ArrayList<>();
    /**
     * 历史消息
     */
    private List<HistoryMessage> historyMessageList = new ArrayList<>();
    /**
     * 接受者id
     */
    private String recipientId;
    private List<BaseFragment> mFragments;
    private FuncPagerAdapter mFuncPagerAdapter;
    private boolean move = false;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Emojiconize the whole activity, must call before `super.onCreate()`
        Emojiconize.activity(this).go();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        initDatas();
        initViews();
        initListener();
        initOss();
        initRefreshLayout();
    }

    @Override
    public void initViews() {
        super.initViews();

        historyMessageList = LitePal.where("senderId = ? and recipientId = ?", initiaDataResult.getData().getSelfInfo().getId(), recipientId).find(HistoryMessage.class);
        if (historyMessageList.size() > 0) {
            messageLists = GsonHelper.getGson().fromJson(historyMessageList.get(0).getMessageJson(), new TypeToken<List<HrMqttMessage>>() {
            }.getType());
        }

        headerTitle.setText(recipientId);
        adapterChatRceyclerView = new AdapterChatRecyclerView(getActivity(), messageLists);
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        // 添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(adapterChatRceyclerView);

        initBottomFunc();
    }

    /**
     * 初始化事件
     */
    public void initListener() {
        try {
            // 订阅主题
            mqttAndroidClient.subscribe("/Im/" + subscriptionTopic, 0, new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    // message Arrived! 收到消息
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

        //监听文本输入框，有值则显示发送按钮，无值则隐藏发送按钮
        etMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(etMessage.getText().toString())) {
                    ivAdd.setVisibility(View.VISIBLE);
                    btnSend.setVisibility(View.GONE);
                } else {
                    ivAdd.setVisibility(View.GONE);
                    btnSend.setVisibility(View.VISIBLE);
                }
            }
        });

        //监听ViewPager的滑动，改变底部小圆点的样式
        mVpFunc.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //改变小圆点位置
                mDv.changeCurrentPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //监听文本输入框的焦点获取，当获取焦点显示软键盘时，将消息列表滚动到最后一行
        etMessage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    moveToPosition(messageLists.size() - 1);
                    frameLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 隐藏软键盘(可用于Activity，Fragment)
     */
    public static void hideSoftKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 滚动到指定位置
     *
     * @param index
     */
    private void moveToPosition(int index) {
        //获取当前recycleView屏幕可见的第一项和最后一项的Position
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        //然后区分情况
        if (index <= firstItem) {
            //当要置顶的项在当前显示的第一个项的前面时
            mRecyclerView.scrollToPosition(index);
        } else if (index <= lastItem) {
            //当要置顶的项已经在屏幕上显示时，计算它离屏幕原点的距离
            int top = mRecyclerView.getChildAt(index - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            mRecyclerView.scrollToPosition(index);
            //记录当前需要在RecyclerView滚动监听里面继续第二次滚动
            move = true;
        }
    }

    /**
     * 初始化数据
     */
    public void initDatas() {
        // 获取用户简要信息
//        userSimpleInfo = (UserSimpleInfo) getIntent().getSerializableExtra("userSimpleInfo");
        recipientId = getIntent().getStringExtra("recipientId");
        // mqtt帮助类
        mqttHelper = MqttHelper.getInstance(getActivity());
        // mqtt客户端
        mqttAndroidClient = mqttHelper.getMqttAndroidClient();
        // 登录返回结果数据
        loginResultData = UserUtil.getUser(getActivity());
        // oss 登录参数
        ossLoginCredencial = loginResultData.getOssCredencial();
        // 初始化数据
        initiaDataResult = InitiadataUtil.getInitiadata(getActivity());
        // 订阅的主题
        subscriptionTopic = initiaDataResult.getData().getSelfTopic();
        // 好友的主题集合
        keyValues = initiaDataResult.getData().getAllTopic();
        // 获取好友的主题
        for (KeyValue keyValue : keyValues) {
            if (keyValue.getKey().equalsIgnoreCase(recipientId)) {
                topic = keyValue.getValue();
            }
        }
    }

    /**
     * 初始化oss
     */
    public void initOss() {
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(ossLoginCredencial.getId(), ossLoginCredencial.getKey());
        oss = new OSSClient(getApplicationContext(), ossLoginCredencial.getHost(), credentialProvider);
    }

    /**
     * 初始化下拉刷新
     */
    public void initRefreshLayout() {
        // 设置代理
        mRefreshLayou.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, false);
        // 设置下拉刷新和上拉加载更多的风格
        mRefreshLayou.setRefreshViewHolder(refreshViewHolder);
    }

    @OnClick({R.id.btn_send_topic, R.id.iv_add, R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send_topic:
                UserStateInfo userStateInfo = new UserStateInfo();
                userStateInfo.setState(1);
                userStateInfo.setTopic(initiaDataResult.getData().getSelfTopic());
                HrMqttMessage hrMqttMessage1 = new HrMqttMessage();
                hrMqttMessage1.setTime(TimeHelper.getSystemTime());
                hrMqttMessage1.setMessageType(23);
                hrMqttMessage1.setContent(GsonHelper.getGson().toJson(userStateInfo));
                mqttHelper.publishMessage(GsonHelper.getGson().toJson(hrMqttMessage1), "/Im/" + topic);
                break;
            case R.id.btn_send:
                // 发送文字消息
                HrMqttMessage hrMqttMessage = new HrMqttMessage();
                hrMqttMessage.setType(APPConstants.TYPE_RIGHT_TEXT);
                hrMqttMessage.setTime(TimeHelper.getSystemTime());
                hrMqttMessage.setContent(etMessage.getText().toString());
                hrMqttMessage.setMessageType(0);
                hrMqttMessage.setGroup(false);
                hrMqttMessage.setMessageId(UUID.randomUUID().toString());
                hrMqttMessage.setSession(recipientId);
                hrMqttMessage.setSenderId(initiaDataResult.getData().getSelfInfo().getId());
                hrMqttMessage.setRecipientId(recipientId);
//                List<HrMqttMessage>  hrMqttMessageList = LitePal.findAll(HrMqttMessage.class);
                messageLists.add(hrMqttMessage);
                adapterChatRceyclerView.notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition(messageLists.size());
                mqttHelper.publishMessage(GsonHelper.getGson().toJson(hrMqttMessage), "/Im/" + topic);
                etMessage.setText("");

                if (historyMessageList.size() > 0) {
                    HistoryMessage historyMessage = new HistoryMessage();
                    historyMessage.setMessageJson(GsonHelper.getGson().toJson(messageLists));
                    historyMessage.updateAll("senderId = ? and recipientId = ?", initiaDataResult.getData().getSelfInfo().getId(), recipientId);
                } else {
                    HistoryMessage historyMessage = new HistoryMessage();
                    historyMessage.setSenderId(initiaDataResult.getData().getSelfInfo().getId());
                    historyMessage.setRecipientId(recipientId);
                    historyMessage.setMessageJson(GsonHelper.getGson().toJson(messageLists));
                    historyMessage.save();
                }
                break;
            case R.id.iv_add:
                if (frameLayout.getVisibility() == View.VISIBLE) {
                    frameLayout.setVisibility(View.GONE);
                } else {
                    frameLayout.setVisibility(View.VISIBLE);
//                    hideSoftKeyboard(ActivityChat.this);
                }
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

                    // 通过下载outputStream获取bitmap
                    Bitmap bitmap = BitmapFactory.decodeByteArray(outputStream.toByteArray(), 0, outputStream.toByteArray().length);
                    // 构造mqtt消息
                    HrMqttMessage hrMqttMessage = new HrMqttMessage();
                    hrMqttMessage.setContent(etMessage.getText().toString());
                    hrMqttMessage.setBitmap(bitmap);
                    hrMqttMessage.setType(APPConstants.TYPE_LEFT_IMAGE);
                    hrMqttMessage.setTime(TimeHelper.getSystemTime());
                    // 通过hangdler发送消息处理
                    Message message1 = new Message();
                    message1.what = APPConstants.UPDATE_RECYCLEVIEW_IMG;
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", hrMqttMessage);
                    message1.setData(bundle);
                    handler.sendMessage(message1);
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
        // 数据不为null，并且requestCode==RESULT_LOAD_IMAGE
        if (requestCode == APPConstants.RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            // 获取所选图片的uri
            Uri selectedImage = data.getData();
            // 文件路径
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
                // 构造mqtt消息
                HrMqttMessage hrMqttMessage = new HrMqttMessage();
                hrMqttMessage.setContent(etMessage.getText().toString());
                hrMqttMessage.setBitmap(bitmap);
                hrMqttMessage.setType(APPConstants.TYPE_RIGHT_IMAGE);
                hrMqttMessage.setTime(TimeHelper.getSystemTime());
                messageLists.add(hrMqttMessage);
                adapterChatRceyclerView.notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition(messageLists.size());
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            }

            putObject();
        }
    }

    /**
     * 初始化底部功能区
     */
    private void initBottomFunc() {
        //底部功能区
        mFragments = new ArrayList<>();
        Func1Fragment func1Fragment1 = new Func1Fragment();
        Func2Fragment func1Fragment2 = new Func2Fragment();
        mFragments.add(func1Fragment1);
        mFragments.add(func1Fragment2);
        mFuncPagerAdapter = new FuncPagerAdapter(getSupportFragmentManager(), mFragments);
        mVpFunc.setAdapter(mFuncPagerAdapter);

        //初始化圆点的个数及当前被选中的位置
        mDv.initData(mFragments.size(), 0);
    }

    /*================== 下拉刷新、上拉加载更多监听 begin ==================*/
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
//        loadHistoryMsgFromRemote();
        mRefreshLayou.endRefreshing();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        Log.d("test", "000");
        return true;
    }
    /*================== 下拉刷新、上拉加载更多监听 end ==================*/
}
