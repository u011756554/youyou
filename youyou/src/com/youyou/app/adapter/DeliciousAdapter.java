package com.youyou.app.adapter;

import java.util.Collections;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youyou.app.R;
import com.youyou.app.bean.FoodStory;
import com.youyou.app.utils.ImageLoaderUtil;


public class DeliciousAdapter extends SetBaseAdapter<FoodStory>{

	public DeliciousAdapter(List<FoodStory> mObjectList) {
		super(mObjectList);
		// TODO Auto-generated constructor stub
	}

	public void replaceAll(List<FoodStory> newList) {
		this.mObjectList.clear();
//		Collections.sort(newList);
		this.mObjectList.addAll(newList);
		notifyDataSetChanged();
	}
	
	public void addAll(List<FoodStory> newList) {
		this.mObjectList.addAll(newList);
//		Collections.sort(this.mObjectList);
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		ViewHolder holder = null;
		if (contentView == null) {
			contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_delicious, null);
			holder = new ViewHolder();
			holder.ivFoodStory = (ImageView) contentView.findViewById(R.id.iv_adapter_delicious);
			holder.tvFoodStoryName = (TextView) contentView.findViewById(R.id.tv_adapter_name);
			holder.tvZanCount = (TextView) contentView.findViewById(R.id.tv_adapter_delicious_alamcounts);
			holder.tvCommentCount = (TextView) contentView.findViewById(R.id.tv_adapter_delicious_talkcounts);
			holder.tvStoryContent = (TextView) contentView.findViewById(R.id.tv_delicious_content);
			contentView.setTag(holder);
		} else {
			holder = (ViewHolder) contentView.getTag();
		}
		FoodStory fs = mObjectList.get(position);
		ImageLoaderUtil.diaPlayNormal(fs.getUrl(), holder.ivFoodStory, R.drawable.logo, null);
		holder.tvFoodStoryName.setText(fs.getTitle());
		holder.tvCommentCount.setText(fs.getComment());
		holder.tvZanCount.setText(fs.getCommend());
		holder.tvStoryContent.setText(fs.getDescription());
		return contentView;
	}
	
	private class ViewHolder {
		private ImageView ivFoodStory;
		private TextView tvFoodStoryName;
		private TextView tvZanCount;
		private TextView tvCommentCount;
		private TextView tvStoryContent;
	}
}
