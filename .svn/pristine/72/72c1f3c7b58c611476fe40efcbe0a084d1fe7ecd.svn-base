package usung.com.mqttclient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        if (viewType == APPConstants.TYPE_LEFT) {
             holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat_recycler_view_left, parent, false));
        } else if (viewType == APPConstants.TYPE_RIGHT) {
             holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat_recycler_view_right, parent, false));
        } else {
            holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat_recycler_view_left, parent, false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMessage.setText(messageLists.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return messageLists.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_message)
        TextView tvMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
