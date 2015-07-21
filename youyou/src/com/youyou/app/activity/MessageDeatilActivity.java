package com.youyou.app.activity;

import com.youyou.app.R;
import com.youyou.app.bean.Message;

import android.os.Bundle;
import android.widget.TextView;

public class MessageDeatilActivity extends BaseActivity{

	public static final String KEY_MESSAGE = "message";
	private TextView contentTextView;
	private TextView timeTextView;
	
	private Message message;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_messagedetail);
		initData();
		initView();
	}

	private void initData() {
		message = (Message) getIntent().getSerializableExtra(KEY_MESSAGE);
	}
	
	private void initView() {
		contentTextView = (TextView) findViewById(R.id.tv_messagedetail_content);
		timeTextView = (TextView) findViewById(R.id.tv_messagedetail_time);
		
		if (message != null) {
			contentTextView.setText(message.getContent());
			timeTextView.setText(message.getTime());
		}
	}
	
}
