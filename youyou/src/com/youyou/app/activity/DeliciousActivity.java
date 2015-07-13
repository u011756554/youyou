package com.youyou.app.activity;

import java.util.ArrayList;
import java.util.List;

import com.youyou.app.R;
import com.youyou.app.adapter.DeliciousAdapter;
import com.youyou.app.bean.FoodStory;
import com.youyou.app.net.Event;
import com.youyou.app.net.EventBus;
import com.youyou.app.net.EventCode;
import com.youyou.app.net.EventListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class DeliciousActivity extends BaseActivity{

	private ListView lvDelicious;
	private List<FoodStory> foodstoryList = new ArrayList<FoodStory>();
	private DeliciousAdapter adapter;
	private int pagesize = 10;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_dilicious);
		initView();
		getData();
	}
	
	private void initView() {
		lvDelicious = (ListView) findViewById(R.id.lv_dilicious);
		adapter = new DeliciousAdapter(foodstoryList);
		lvDelicious.setAdapter(adapter);
		
		lvDelicious.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Intent intent = new Intent(DeliciousActivity.this,DeliciousDetailActivity.class);
				intent.putExtra(DeliciousDetailActivity.KEY_FOODSTORY, adapter.mObjectList.get(position));
				startActivity(intent);
			}
		});
		
		setTitle("美食故事");
		addBack(true);
	}

	private void getData() {
		EventBus eventbus = new EventBus();
		eventbus.setListener(new EventListener() {
			
			@Override
			public void onEventRunEnd(Event event) {
				if (event.isSuccess()) {
					List<FoodStory> tepFoodstoryList = (List<FoodStory>) event.getReturnParamAtIndex(0);
					adapter.addAll(tepFoodstoryList);
				}
			}
		});
		eventbus.pushEvent(EventCode.HTTP_GETFOODS, foodstoryList.size()+"",pagesize+"");
	}
}
