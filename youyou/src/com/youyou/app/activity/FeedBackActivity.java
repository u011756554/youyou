package com.youyou.app.activity;

import com.youyou.app.R;

import android.os.Bundle;

public class FeedBackActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_feedback);
		addBack(true);
		setTitle("反馈");
	}

	
}
