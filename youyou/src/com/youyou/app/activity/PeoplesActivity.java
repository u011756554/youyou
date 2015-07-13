package com.youyou.app.activity;

import java.util.ArrayList;
import java.util.List;

import com.youyou.app.R;
import com.youyou.app.adapter.PeoplesAdapter;
import com.youyou.app.bean.CurrentActivity;
import com.youyou.app.bean.User;
import com.youyou.app.net.Event;
import com.youyou.app.net.EventBus;
import com.youyou.app.net.EventCode;
import com.youyou.app.net.EventListener;
import com.youyou.app.widget.MyListView;

import android.R.integer;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class PeoplesActivity extends BaseActivity{

	private ListView lvPeople;
	private List<User> peopleList = new ArrayList<User>();
	private PeoplesAdapter adapter;
	
	public static final String 	KEY_NEW = "new";
	private CurrentActivity mCurrentActivity;
	private int pagesize = 10;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_peoples);
		getData();
		initView();
	}

	private void initView() {
		setTitle("参与人员");
		addBack(true);
		
		lvPeople = (ListView) findViewById(R.id.lv_peoples);
		adapter = new PeoplesAdapter(peopleList);
		lvPeople.setAdapter(adapter);
	}
	
	private void getData() {
		Intent inent = getIntent();
		mCurrentActivity = (CurrentActivity) inent.getSerializableExtra(KEY_NEW);
		if (mCurrentActivity != null) {
			EventBus eventBus = new EventBus();
			eventBus.setListener(new EventListener() {
				
				@Override
				public void onEventRunEnd(Event event) {
					if (event.isSuccess()) {
						List<User> tmpPeopleList = (List<User>) event.getReturnParamAtIndex(0);
						adapter.addAll(tmpPeopleList);
					} else {
						if (event.getFailException() != null) {
							showToast(event.getFailException().getMessage());
						} else {
							
						}
					}
				}
			});
			eventBus.pushEvent(EventCode.HTTP_GETACTIVITYUSERS, mCurrentActivity.getId(),peopleList.size()+"",pagesize+"");			
		}
	}
}
