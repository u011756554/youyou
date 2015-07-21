package com.youyou.app.adapter;

import java.util.List;

import com.youyou.app.R;
import com.youyou.app.bean.Game;
import com.youyou.app.bean.Product;
import com.youyou.app.utils.CommonUtil;
import com.youyou.app.utils.ImageLoaderUtil;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class ProductAdapter extends BaseAdapter {

	private List<Product> list;
	private Context context;
	private Activity activity;
	
	public ProductAdapter(Context context) {
		this.context = context;
		this.activity = (Activity) context;
	}
	
	public void setData(List<Product> productsList) {
		this.list = productsList;
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
	
	public synchronized void replaceAll(final List<Product> newList) {
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				list.clear();
				list.addAll(newList);
				notifyDataSetChanged();			
			}
		});
	}
	
	public synchronized  void addAll(final List<Product> newList) {
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				list.addAll(newList);
				notifyDataSetChanged();				
			}
		});
	}
	
	private int getWidth() {
		return (CommonUtil.getScreenWidth(context)-CommonUtil.dip2px(context, 20)) / 3;
	}
	
	private int getHeight() {
		return (int)(getWidth() * 1.5);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product, null);
			holder = new ViewHolder();
			
			LayoutParams params = new LayoutParams(getWidth(), getHeight());
			holder.iv1 = (ImageView) convertView.findViewById(R.id.iv_adapter_product_iv1);
			holder.iv2 = (ImageView) convertView.findViewById(R.id.iv_adapter_product_iv2);
			holder.iv3 = (ImageView) convertView.findViewById(R.id.iv_adapter_product_iv3);
			holder.iv1.setLayoutParams(params);
			holder.iv2.setLayoutParams(params);
			holder.iv3.setLayoutParams(params);
			
			holder.name1 = (TextView) convertView.findViewById(R.id.tv_adpater_product_name1);
			holder.name2 = (TextView) convertView.findViewById(R.id.tv_adpater_product_name2);
			holder.name3 = (TextView) convertView.findViewById(R.id.tv_adpater_product_name3);
			
			holder.count1 = (TextView) convertView.findViewById(R.id.tv_adpater_product_count1);
			holder.count2 = (TextView) convertView.findViewById(R.id.tv_adpater_product_count2);
			holder.count3 = (TextView) convertView.findViewById(R.id.tv_adpater_product_count3);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Product product1 = list.get(position * 3);
		ImageLoaderUtil.diaPlayNormal(product1.getUrlString(), holder.iv1, R.drawable.icon_default_bg, null);
		holder.name1.setText(product1.getNameString());
		holder.count1.setText(product1.getCountString());
		
		if ((position * 3 +1) < list.size()) {
			holder.iv2.setVisibility(View.VISIBLE);
			holder.name2.setVisibility(View.VISIBLE);
			Product product2 = list.get(position * 3 +1);
			ImageLoaderUtil.diaPlayNormal(product2.getUrlString(), holder.iv2, R.drawable.icon_default_bg, null);
			holder.name2.setText(product2.getNameString());
			holder.count2.setText(product2.getCountString());
		} else {
			holder.iv2.setVisibility(View.GONE);
			holder.name2.setVisibility(View.GONE);
			holder.count2.setVisibility(View.GONE);
		}
		
		if ((position * 3 +2) < list.size()) {
			holder.iv3.setVisibility(View.VISIBLE);
			holder.name3.setVisibility(View.VISIBLE);
			Product product3 = list.get(position * 3 +2);
			ImageLoaderUtil.diaPlayNormal(product3.getUrlString(), holder.iv3, R.drawable.icon_default_bg, null);
			holder.name3.setText(product3.getNameString());	
			holder.count3.setText(product3.getCountString());
		} else {
			holder.iv3.setVisibility(View.GONE);
			holder.name3.setVisibility(View.GONE);
			holder.count3.setVisibility(View.GONE);
		}
		return convertView;
	}
	
	@Override
	public int getCount() {
		// 每列三项
		if (list.size() % 3 == 0) {
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
		ImageView iv1;
		TextView name1;
		TextView count1;
		
		ImageView iv2;
		TextView name2;
		TextView count2;
		
		ImageView iv3;
		TextView name3;
		TextView count3;
	}

}
