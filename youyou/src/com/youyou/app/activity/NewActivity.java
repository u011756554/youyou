package com.youyou.app.activity;

import java.util.ArrayList;
import java.util.List;

import com.youyou.app.R;
import com.youyou.app.adapter.CurrentActivityAdapter;
import com.youyou.app.bean.CurrentActivity;
import com.youyou.app.bean.message.CurrentActivityListBean;
import com.youyou.app.net.Event;
import com.youyou.app.net.EventBus;
import com.youyou.app.net.EventCode;
import com.youyou.app.net.EventListener;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class NewActivity extends BaseActivity{

	private ListView lvActivity;
	private CurrentActivityAdapter adapter;
	private List<CurrentActivity> currentActivityList = new ArrayList<CurrentActivity>();
	
	private int pagesize = 10;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_activity);
		initView();
		getData();
	}
	
	private void initView() {
		lvActivity = (ListView) findViewById(R.id.lv_activity);
		adapter = new CurrentActivityAdapter(currentActivityList);
		lvActivity.setAdapter(adapter);
		
		setTitle("最新活动");
		addBack(true);
		
		lvActivity.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				if (currentActivityList.size() > 0) {
					Intent intent = new Intent(NewActivity.this,NewDetailActivity.class);
					intent.putExtra(NewDetailActivity.KEY_CURRENTACTIVITY, adapter.mObjectList.get(position));
					startActivity(intent);
				}
			}
		});
	}

	private void getData() {
		EventBus eventBus = new EventBus();
		eventBus.setListener(new EventListener() {
			
			@Override
			public void onEventRunEnd(Event event) {
				if (event.isSuccess()) {
					CurrentActivityListBean msg = (CurrentActivityListBean) event.getReturnParamAtIndex(0);
					adapter.addAll(msg.getData());
				} else {
					if (event.getFailException() != null) {
						showToast(event.getFailException().getMessage());
					} else {
						CurrentActivityListBean msg = (CurrentActivityListBean) event.getReturnParamAtIndex(0);
						if (msg != null && TextUtils.isEmpty(msg.getMessage())) {
							showToast(msg.getMessage());
						}
					}
				}
			}
		});
		eventBus.pushEvent(EventCode.HTTP_GETACTIVITIES, currentActivityList.size()+"",pagesize+"");
	}

}
