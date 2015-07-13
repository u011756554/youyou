package com.youyou.app.activity;

import com.youyou.app.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MessageActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_message);
		
		addBack(true);
		setTitle("消息中心");
		ImageView ivImageView = (ImageView) findViewById(R.id.iv_message);
		ivImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MessageActivity.this,WaitActivity.class);
				startActivity(intent);
			}
		});
	}

	
}
