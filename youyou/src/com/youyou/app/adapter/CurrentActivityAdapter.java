package com.youyou.app.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youyou.app.R;
import com.youyou.app.bean.CurrentActivity;
import com.youyou.app.utils.ImageLoaderUtil;

public class CurrentActivityAdapter extends SetBaseAdapter<CurrentActivity>{

	public void replaceAll(List<CurrentActivity> newList) {
		this.mObjectList.clear();
//		Collections.sort(newList);
		this.mObjectList.addAll(newList);
		notifyDataSetChanged();
	}
	
	public void addAll(List<CurrentActivity> newList) {
		this.mObjectList.addAll(newList);
//		Collections.sort(this.mObjectList);
		notifyDataSetChanged();
	}
	
	public CurrentActivityAdapter(List<CurrentActivity> mObjectList) {
		super(mObjectList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		ViewHolder holder = null;
		if (contentView == null) {
			contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_activity, null);
			holder = new ViewHolder();
			holder.ivActivity = (ImageView) contentView.findViewById(R.id.iv_adapter_activity);
			holder.tvName = (TextView) contentView.findViewById(R.id.tv_adapter_name);
			holder.tvCount = (TextView) contentView.findViewById(R.id.tv_activity_mount);
			holder.tvContent = (TextView) contentView.findViewById(R.id.tv_activity_content);
			contentView.setTag(holder);
		} else {
			holder = (ViewHolder) contentView.getTag();
		}
		CurrentActivity  ca = mObjectList.get(position);
		ImageLoaderUtil.diaPlayNormal(ca.getUrl(), holder.ivActivity, R.drawable.logo, null);
		holder.tvName.setText(ca.getTitle());
		holder.tvCount.setText(ca.getPeoples()+" 人参加");
		holder.tvContent.setText(ca.getDescription());
		return contentView;
	}
	
	private class ViewHolder {
		private ImageView ivActivity;
		private TextView tvName;
		private TextView tvCount;
		private TextView tvContent;
	}

}
