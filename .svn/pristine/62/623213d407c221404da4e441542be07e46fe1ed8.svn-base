package usung.com.mqttclient.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import usung.com.mqttclient.R;
import usung.com.mqttclient.bean.db.Contact;
import usung.com.mqttclient.bean.user.UserSimpleInfo;
import usung.com.mqttclient.utils.StickyHeaderAdapter;
import usung.com.mqttclient.utils.cn.CNPinyin;

/**
 * Created by you on 2017/9/11.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactHolder> implements StickyHeaderAdapter<HeaderHolder> {
    private onItemClickListener listener;
    private final List<CNPinyin<Contact>> cnPinyinList;

    public ContactAdapter(List<CNPinyin<Contact>> cnPinyinList) {
        this.cnPinyinList = cnPinyinList;
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return cnPinyinList.size();
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_mail_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        Contact contact = cnPinyinList.get(position).data;
        holder.iv_header.setImageResource(contact.imgUrl);
        holder.tv_name.setText(contact.userSimpleInfo.getId() + "");
        holder.ll_item_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, contact.userSimpleInfo);
            }
        });
    }

    @Override
    public long getHeaderId(int childAdapterPosition) {
        return cnPinyinList.get(childAdapterPosition).getFirstChar();
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder holder, int childAdapterPosition) {
        holder.tv_header.setText(String.valueOf(cnPinyinList.get(childAdapterPosition).getFirstChar()));
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return new HeaderHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_header, parent, false));
    }


    public interface onItemClickListener{
        void onItemClick(View view, UserSimpleInfo userSimpleInfo);
    }
}
