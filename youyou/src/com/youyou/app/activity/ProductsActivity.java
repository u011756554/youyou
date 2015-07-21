package com.youyou.app.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

import com.youyou.app.R;
import com.youyou.app.adapter.GameAdapter;
import com.youyou.app.adapter.ProductAdapter;
import com.youyou.app.bean.Game;
import com.youyou.app.bean.Product;
import com.youyou.app.net.Event;

public class ProductsActivity extends RefreshAndLoadActivity{

	private List<Product> list = new ArrayList<Product>();
	private ProductAdapter adapter;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_products);
		addBack(true);
		setTitle("商品商城");
		initView();
		refresh();
	}
	
	private void initView() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Product product = list.get(position);
			}
		});
	}

	@Override
	protected void initAdapter() {
		adapter = new ProductAdapter(context);
		adapter.setData(list);
		mAdapter = adapter;
	}

	@Override
	protected void refreshData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void loadEventRunEnd(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void refreshEventRunEnd(Event event) {
		// TODO Auto-generated method stub
		
	}
}
