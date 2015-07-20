package com.youyou.app.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.youyou.app.bean.Game;
import com.youyou.app.bean.User;

public class GameAdapter extends BaseAdapter {

	private List<Game> list;
	private Context context;
	
	public GameAdapter(Context context) {
		this.context = context;
	}
	
	public void setData(List<Game> gameList) {
		this.list = gameList;
	}
	
	public void replaceAll(List<Game> newList) {
		this.list.clear();
//		Collections.sort(newList);
		this.list.addAll(newList);
		notifyDataSetChanged();
	}
	
	public void addAll(List<Game> newList) {
		this.list.addAll(newList);
//		Collections.sort(this.mObjectList);
		notifyDataSetChanged();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getCount() {
		// 每列两项
		if (list.size() % 23 == 0) {
			return list.size() / 3;
		}
		return list.size() / 3 + 1;
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
	
	private class ViewHolder {
		private ImageView iv1;
		private TextView name1;
	}

}
