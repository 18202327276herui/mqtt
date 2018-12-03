package usung.com.mqttclient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
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

/**
 * 聊天界面适配器
 * @author herui
 * @date 2018/11/28
 */

public class AdapterChatRecyclerView extends RecyclerView.Adapter<AdapterChatRecyclerView.ViewHolder> {
    private Context context;
    private List<String> messageLists = new ArrayList<>();

    public AdapterChatRecyclerView(Context context, List<String> messageLists) {
        this.context = context;
        this.messageLists = messageLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat_recycler_view, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMessage.setText(messageLists.get(position));
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
