package com.youyou.app.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youyou.app.R;
import com.youyou.app.bean.Mark;

public class MarkAdapter extends SetBaseAdapter<Mark>{

	public void replaceAll(List<Mark> newList) {
		this.mObjectList.clear();
//		Collections.sort(newList);
		this.mObjectList.addAll(newList);
		notifyDataSetChanged();
	}
	
	public void addAll(List<Mark> newList) {
		this.mObjectList.addAll(newList);
//		Collections.sort(this.mObjectList);
		notifyDataSetChanged();
	}
	
	public MarkAdapter(List<Mark> mObjectList) {
		super(mObjectList);
		// TODO Auto-generated constructor stub
	}

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		ViewHolder holder = null;
		if (contentView == null) {
			contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mark, null);
			holder = new ViewHolder();
			holder.ivHead = (ImageView) contentView.findViewById(R.id.iv_adapter_mark);
			holder.tvTime = (TextView) contentView.findViewById(R.id.tv_mark_time);
			holder.tvName = (TextView) contentView.findViewById(R.id.tv_adapter_name);
			holder.tvConent = (TextView) contentView.findViewById(R.id.tv_mark_content);
			contentView.setTag(holder);
		} else {
			holder = (ViewHolder) contentView.getTag();
		}
		Mark mark = mObjectList.get(position);
		holder.tvName.setText(mark.getMobile());
		holder.tvConent.setText(mark.getContent());
		holder.tvTime.setText(sdf.format(new Date(Long.valueOf(mark.getCreateDate()))));
		return contentView;
	}

	private class ViewHolder {
		private ImageView ivHead;
		private TextView tvName;
		private TextView tvTime;
		private TextView tvConent;
	}
}
