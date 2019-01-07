package usung.com.mqttclient.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import usung.com.mqttclient.R;
import usung.com.mqttclient.bean.db.Contact;
import usung.com.mqttclient.bean.user.UserSimpleInfo;
import usung.com.mqttclient.utils.cn.CNPinyinIndex;

/**
 * Created by you on 2017/9/12.
 */

public class SearchAdapter extends RecyclerView.Adapter<ContactHolder> {
    private onItemClickListener listener;
    private final List<CNPinyinIndex<Contact>> contactList;

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public SearchAdapter(List<CNPinyinIndex<Contact>> contactList) {
        this.contactList = contactList;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_mail_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        CNPinyinIndex<Contact> index = contactList.get(position);
        Contact contact = index.cnPinyin.data;
        holder.iv_header.setImageResource(contact.imgUrl);

        SpannableStringBuilder ssb = new SpannableStringBuilder(contact.chinese());
        ForegroundColorSpan span = new ForegroundColorSpan(Color.BLUE);
        ssb.setSpan(span, index.start, index.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.tv_name.setText(ssb);

        holder.ll_item_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, contact.userSimpleInfo);
            }
        });
    }

    public void setNewDatas(List<CNPinyinIndex<Contact>> newDatas) {
        this.contactList.clear();
        if (newDatas != null && !newDatas.isEmpty()) {
            this.contactList.addAll(newDatas);
        }
        notifyDataSetChanged();
    }

    public interface onItemClickListener{
        void onItemClick(View view, UserSimpleInfo userSimpleInfo);
    }
}
