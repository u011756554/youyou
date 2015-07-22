package com.youyou.app.activity;

import com.youyou.app.R;

import android.os.Bundle;

public class AboutActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_about);
		addBack(true);
		setTitle("关于游游");
	}

	
}
