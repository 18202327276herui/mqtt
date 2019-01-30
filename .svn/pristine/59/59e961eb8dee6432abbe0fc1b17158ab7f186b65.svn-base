package usung.com.mqttclient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import usung.com.mqttclient.R;
import usung.com.mqttclient.bean.db.HistoryMessage;

/**
 * 主界面适配器
 *
 * @author herui
 * @date 2018/11/28
 */

public class AdapterMainRecyclerView extends RecyclerView.Adapter<AdapterMainRecyclerView.ViewHolder> {
    private onItemClickListener listener;
    private Context context;
    private List<HistoryMessage> historyMessageList = new ArrayList<>();

    public AdapterMainRecyclerView(Context context, List<HistoryMessage> historyMessageList) {
        this.context = context;
        this.historyMessageList = historyMessageList;
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main_recycler_view, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvName.setText(historyMessageList.get(position).getRecipientId());
        holder.tvTime.setText(historyMessageList.get(position).getRecipientId());
        holder.tvText.setText(historyMessageList.get(position).getRecipientId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, historyMessageList.get(position).getRecipientId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyMessageList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_view)
        ConstraintLayout itemView;
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_text)
        TextView tvText;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface onItemClickListener {
        void onItemClick(View view, String recipientId);
    }
}
