package usung.com.mqttclient.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.PopupWindow;

import usung.com.mqttclient.R;

/**
 * 记住登录账号的弹出框
 * @author fenghui 2016.05.18
 */
public class RecordLoginPopWindow extends PopupWindow {
	
	private CheckBox chk_select;
	public RecordLoginPopWindow(Context ctx){
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xffffff);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
		this.setBackgroundDrawable(dw);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.PopupWindowAnimation);
	}
		
	public void showPopupWindow(View contentView, View parentView, CheckBox chk_select) {
		this.chk_select=chk_select;
		this.setContentView(contentView);
		this.setWidth(parentView.getWidth());
		this.showAsDropDown(parentView, 0,1);
	}
	
	@Override
	public void showAsDropDown(View anchor, int xoff, int yoff){
		super.showAsDropDown(anchor,xoff,yoff);
		chk_select.setChecked(true);
	}
	
	@Override
	public void dismiss(){
		super.dismiss();
		chk_select.setChecked(false);
	}
}
