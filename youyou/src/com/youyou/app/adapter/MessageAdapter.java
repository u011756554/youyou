package com.youyou.app.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.youyou.app.R;
import com.youyou.app.activity.NewActivity;
import com.youyou.app.bean.Game;
import com.youyou.app.bean.Message;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MessageAdapter extends BaseAdapter {

	private List<Message> list;
	private Context context;
	private Activity activity;
	
	public MessageAdapter(Context context) {
		this.context = context;
		this.activity = (Activity) context;
	}
	
	public void setData(List<Message> messageList) {
		this.list = messageList;
	}
	
	public synchronized void clear() {
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				list.clear();
				notifyDataSetChanged();			
			}
		});
	}
	
	public synchronized void replaceAll(final List<Message> newList) {
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				list.clear();
				list.addAll(newList);
				notifyDataSetChanged();			
			}
		});
	}
	
	public synchronized  void addAll(final List<Message> newList) {
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				list.addAll(newList);
				notifyDataSetChanged();				
			}
		});
	}	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	private SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_message, null);
			holder = new ViewHolder();
			holder.contentTextView = (TextView) convertView.findViewById(R.id.adapter_message_conent);
			holder.timeTextView = (TextView) convertView.findViewById(R.id.adapter_message_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Message msgMessage = list.get(position);
		holder.contentTextView.setText(msgMessage.getContent());
		String timeString = sdFormat.format(new Date(Long.valueOf(msgMessage.getTime())));
		holder.timeTextView.setText(timeString);
		return convertView;
	}

	private class ViewHolder {
		TextView contentTextView;
		TextView timeTextView;
	}
}
