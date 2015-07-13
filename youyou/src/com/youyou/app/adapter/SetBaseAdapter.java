package com.youyou.app.adapter;

import java.util.List;
import android.widget.BaseAdapter;

public abstract class SetBaseAdapter<T> extends BaseAdapter{

	public List<T> mObjectList;
	
	public SetBaseAdapter (List<T> mObjectList) {
		this.mObjectList = mObjectList;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mObjectList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mObjectList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	
	public void clear() {
		this.mObjectList.clear();
		notifyDataSetChanged();
	}
	
}
