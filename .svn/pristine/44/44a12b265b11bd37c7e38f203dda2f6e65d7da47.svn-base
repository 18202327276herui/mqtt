package usung.com.mqttclient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import usung.com.mqttclient.R;
import usung.com.mqttclient.base.APPConstants;
import usung.com.mqttclient.base.BaseFragment;
import usung.com.mqttclient.widget.CustomDialog;

/**
 * @创建者 CSDN_LQR
 * @描述 聊天界面功能页面1
 */
public class Func1Fragment extends BaseFragment {

    private View mContentView;
    private CustomDialog mDialog;
    private TextView mTvOne;
    private TextView mTvTwo;

    @BindView(R.id.llPic)
    LinearLayout mLlPic;
    @BindView(R.id.llRecord)
    LinearLayout mLlRecord;
    @BindView(R.id.llRedPacket)
    LinearLayout mLlRedPacket;
    @BindView(R.id.llTransfer)
    LinearLayout mLlTransfer;

    @BindView(R.id.llCollection)
    LinearLayout mLlCollection;
    @BindView(R.id.llLocation)
    LinearLayout mLlLocation;
    @BindView(R.id.llVideo)
    LinearLayout mLlVideo;
    @BindView(R.id.llBusinessCard)
    LinearLayout mLlBusinessCard;

    Intent mIntent;

    @OnClick({R.id.llPic, R.id.llRecord, R.id.llRedPacket, R.id.llTransfer, R.id.llLocation, R.id.llVideo})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.llPic:
                // 发送图片消息
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //传文件
//                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//                i.setType("video/*;image/*");
//                i.addCategory(Intent.CATEGORY_OPENABLE);

                getActivity().startActivityForResult(i, APPConstants.RESULT_LOAD_IMAGE);
//                mIntent = new Intent(getActivity(), ImageGridActivity.class);
//                startActivityForResult(mIntent, IMAGE_PICKER);
                break;
            case R.id.llRecord:
//                ((SessionActivity) getActivity()).showPlayVideo();
                break;
            case R.id.llRedPacket:
//                mIntent = new Intent(getActivity(), RedPacketActivity.class);
//                startActivity(mIntent);
                break;
            case R.id.llTransfer:
//                mIntent = new Intent(getActivity(), TransferActivity.class);
//                startActivity(mIntent);
                break;
            case R.id.llLocation:
//                mContentView = View.inflate(getActivity(), R.layout.dialog_menu_two_session, null);
//                mDialog = new CustomDialog(getActivity(), mContentView, R.style.dialog);
//                mDialog.show();
//                mTvOne = (TextView) mContentView.findViewById(tvOne);
//                mTvTwo = (TextView) mContentView.findViewById(tvTwo);
//                mTvOne.setText("发送位置");
//                mTvTwo.setText("共享实时位置");
//                mTvOne.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mDialog.dismiss();
//                    }
//                });
//                mTvTwo.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mDialog.dismiss();
//                    }
//                });

                break;
            case R.id.llVideo:
//                mContentView = View.inflate(getActivity(), R.layout.dialog_menu_two_session, null);
//                mDialog = new CustomDialog(getActivity(), mContentView, R.style.dialog);
//                mDialog.show();
//                mTvOne = (TextView) mContentView.findViewById(tvOne);
//                mTvTwo = (TextView) mContentView.findViewById(tvTwo);
//                mTvOne.setText("视频聊天");
//                mTvTwo.setText("语音聊天");
//                mTvOne.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mDialog.dismiss();
//                    }
//                });
//                mTvTwo.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mDialog.dismiss();
//                    }
//                });
                break;
            default:
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return initView();
    }

    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_func_page1, null);
        ButterKnife.bind(this, view);
        return view;
    }
}
