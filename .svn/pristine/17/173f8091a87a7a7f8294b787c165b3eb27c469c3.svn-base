package usung.com.mqttclient.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lqr.emoji.MoonUtil;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import usung.com.mqttclient.R;
import usung.com.mqttclient.base.APPConstants;
import usung.com.mqttclient.bean.HrMqttMessage;

/**
 * 聊天界面适配器
 *
 * @author herui
 * @date 2018/11/28
 */

public class AdapterChatRecyclerView extends RecyclerView.Adapter<AdapterChatRecyclerView.ViewHolder> {
    private Context context;
    private List<HrMqttMessage> messageLists = new ArrayList<>();
    private onItemClicked onItemClicked;

    public void setOnItemClicked(AdapterChatRecyclerView.onItemClicked onItemClicked) {
        this.onItemClicked = onItemClicked;
    }

    public AdapterChatRecyclerView(Context context, List<HrMqttMessage> messageLists) {
        this.context = context;
        this.messageLists = messageLists;
    }

    @Override
    public int getItemViewType(int position) {
        HrMqttMessage mqttMessage = messageLists.get(position);
        return mqttMessage.getType();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = null;

        switch (viewType) {
            // 左边文字
            case APPConstants.TYPE_LEFT_TEXT:
                holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat_recycler_view_left_text, parent, false));
                break;
            // 右边文字
            case APPConstants.TYPE_RIGHT_TEXT:
                holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat_recycler_view_right_text, parent, false));
                break;
            // 左边图片
            case APPConstants.TYPE_LEFT_IMAGE:
                holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat_recycler_view_left_img, parent, false));
                break;
            // 右边图片
            case APPConstants.TYPE_RIGHT_IMAGE:
                holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat_recycler_view_right_img, parent, false));
                break;
            // 左边语音
            case APPConstants.TYPE_LEFT_VOICE:
                holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat_recycler_view_left_voice, parent, false));
                break;
            // 右边语音
            case APPConstants.TYPE_RIGHT_VOICE:
                holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat_recycler_view_right_voice, parent, false));
                break;
            default:
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (getItemViewType(position) == APPConstants.TYPE_LEFT_VOICE || getItemViewType(position) == APPConstants.TYPE_RIGHT_VOICE){
            holder.tvDuration.setText("3''");
        } else {
            holder.tvMessage.setText(messageLists.get(position).getContent());
            holder.imageView.setImageBitmap(messageLists.get(position).getBitmap());
            holder.tvDate.setText(messageLists.get(position).getTime().substring(10, messageLists.get(position).getTime().length()));
            //识别并显示表情
            MoonUtil.identifyFaceExpression(context, holder.tvMessage, messageLists.get(position).getContent(), ImageSpan.ALIGN_BOTTOM);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClicked.onItemClick(getItemViewType(position), holder.ivAudio, messageLists.get(position).getVoiceFile());
            }
        });
    }

    @Override
    public int getItemCount() {
        return messageLists == null ? 0 : messageLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_message)
        TextView tvMessage;
        @BindView(R.id.iv_img)
        ImageView imageView;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.item_view)
        ConstraintLayout itemView;
        @BindView(R.id.ivAudio)
        ImageView ivAudio;
        @BindView(R.id.tvDuration)
        TextView tvDuration;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface onItemClicked{
        void onItemClick(int itemType, ImageView ivAudio, File voiceFile);
    }
}
