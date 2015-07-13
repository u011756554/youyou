package com.youyou.app.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youyou.app.R;
import com.youyou.app.bean.CurrentActivity;
import com.youyou.app.bean.User;
import com.youyou.app.utils.ImageLoaderUtil;

public class PeoplesAdapter extends SetBaseAdapter<User>{

	public void replaceAll(List<User> newList) {
		this.mObjectList.clear();
//		Collections.sort(newList);
		this.mObjectList.addAll(newList);
		notifyDataSetChanged();
	}
	
	public void addAll(List<User> newList) {
		this.mObjectList.addAll(newList);
//		Collections.sort(this.mObjectList);
		notifyDataSetChanged();
	}
	
	public PeoplesAdapter(List<User> mObjectList) {
		super(mObjectList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_peoples, null);
			holder = new ViewHolder();
			holder.ivPeoples = (ImageView) convertView.findViewById(R.id.iv_adapter_peoples);
			holder.tvNameTextView = (TextView) convertView.findViewById(R.id.tv_adapter_peoples_name);
			holder.tvStaTextView = (TextView) convertView.findViewById(R.id.tv_adapter_peoples_state);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		User user = mObjectList.get(position);
//		ImageLoaderUtil.diaPlayNormal("", holder.ivPeoples, R.drawable.icon_default_bg, null);
		holder.tvNameTextView.setText(user.getNickName());
		holder.tvStaTextView.setText("正在审核");
		return convertView;
	}

	private class ViewHolder {
		private ImageView ivPeoples;
		private TextView tvNameTextView;
		private TextView tvStaTextView;
	}
}
