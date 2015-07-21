package com.youyou.app.activity;

import java.util.ArrayList;
import java.util.List;

import com.youyou.app.R;
import com.youyou.app.adapter.GameAdapter;
import com.youyou.app.adapter.MessageAdapter;
import com.youyou.app.bean.Game;
import com.youyou.app.bean.Message;
import com.youyou.app.net.Event;
import com.youyou.app.net.EventBus;
import com.youyou.app.net.EventCode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

public class MessageActivity extends RefreshAndLoadActivity {

	private List<Message> list = new ArrayList<Message>();
	private MessageAdapter adapter;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_message);
		addBack(true);
		setTitle("消息中心");
		initView();
		refresh();
	}

	private void initView() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Message message = list.get(position);
				Intent intent = new Intent();
				intent.putExtra(MessageDeatilActivity.KEY_MESSAGE, message);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void initAdapter() {
		adapter = new MessageAdapter(context);
		adapter.setData(list);
		mAdapter = adapter;
		page_size = 20;
	}

	@Override
	protected void refreshData() {
		adapter.clear();
		EventBus eventBus = new EventBus();
		eventBus.setListener(RefreshListener);
		eventBus.pushEvent(EventCode.HTTP_GETNOTICE, list.size()+"",page_size+"");		
	}

	@Override
	protected void loadData() {
		EventBus eventBus = new EventBus();
		eventBus.setListener(loadMoreListener);
		eventBus.pushEvent(EventCode.HTTP_GETNOTICE, list.size()+"",page_size+"");		
	}

	@Override
	protected void loadEventRunEnd(Event event) {
		if (event.isSuccess()) {
			List<Message> messageList = (List<Message>) event.getReturnParamAtIndex(0);
			adapter.addAll(messageList);
		} else {
			if (event.getFailException() != null) {
				showToast(event.getFailException().getMessage());
			} else {
				showToast("获取数据失败");
			}
		}		
	}

	@Override
	protected void refreshEventRunEnd(Event event) {
		if (event.isSuccess()) {
			List<Message> messageList = (List<Message>) event.getReturnParamAtIndex(0);
			adapter.addAll(messageList);
		} else {
			if (event.getFailException() != null) {
				showToast(event.getFailException().getMessage());
			} else {
				showToast("获取数据失败");
			}
		}		
	}

	
}
