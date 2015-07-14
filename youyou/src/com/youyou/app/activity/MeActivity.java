package com.youyou.app.activity;

import com.youyou.app.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MeActivity extends BaseActivity{

	private ImageView ivHead;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_me);
		addBack(true);
		setTitle("个人中心");
		initView();
	}

	private void initView() {
		ivHead = (ImageView) findViewById(R.id.iv_me_head);
		ivHead.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MeActivity.this,MyInfoActivity.class);
				startActivity(intent);
			}
		});
	}
	
}
