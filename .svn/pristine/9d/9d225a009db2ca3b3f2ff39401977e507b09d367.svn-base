package usung.com.mqttclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import usung.com.mqttclient.R;
import usung.com.mqttclient.activity.ActivityLogin;

/**
 * 记住登录账号的适配器
 *
 * @author fenghui 2016.06.01
 */
public class AdapterRecordLoginInfo extends BaseAdapter{
    private Context ctx;
    private ArrayList<String> filterBeans=null;
    public final class ViewHolder {
        public TextView ItemName;
        private ImageView ItemImage;
    }

    public AdapterRecordLoginInfo(Context ctx, ArrayList<String> filterBeans) {
    	this.ctx = ctx;
        this.filterBeans=getBeans(filterBeans);
    }
    
    @Override
    public int getCount() {
    	return this.filterBeans.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
    	return this.filterBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	ViewHolder viewHolder=null;
        if (convertView == null) {
        	viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_record_userinfo, null);
            viewHolder.ItemName = (TextView) convertView.findViewById(R.id.ItemName);
            viewHolder.ItemImage = (ImageView) convertView.findViewById(R.id.ItemImage);
            convertView.setTag(viewHolder);
        } else {
        	viewHolder = (ViewHolder) convertView.getTag();
        }

        final String loginName=this.filterBeans.get(position);
        viewHolder.ItemName.setText(loginName);
        viewHolder.ItemImage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((ActivityLogin)ctx).deleteName(loginName);
			}
		});
        return convertView;
    }
    
    public ArrayList<String> getList() {
        return this.filterBeans;
    }
    
    public void addListAndRefresh(ArrayList<String> filterBeans) {
    	this.filterBeans.addAll(getBeans(filterBeans));
    	notifyDataSetChanged();
    }

    public void setListAndRefresh(ArrayList<String> filterBeans) {
       this.filterBeans=getBeans(filterBeans);
       notifyDataSetChanged();
    }
    
    private ArrayList<String> getBeans(ArrayList<String> filterBeans){
    	if(filterBeans==null){
    		return new ArrayList<String>();
    	}
    	return filterBeans;
    }
}