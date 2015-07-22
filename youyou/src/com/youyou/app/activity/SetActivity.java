package com.youyou.app.activity;

import com.youyou.app.R;
import com.youyou.app.YouYouApplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class SetActivity extends BaseActivity{

	private RelativeLayout rlFeedback;
	private RelativeLayout rlAbout;
	private RelativeLayout rlExit;
	
	private RelativeLayout rlTranslate;
	private RelativeLayout rlBtnClose;
	private RelativeLayout rlBtnExit;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_set);
		addBack(true);
		setTitle("设置");
		initView();
	}

	private void initView() {
		rlFeedback = (RelativeLayout) findViewById(R.id.rl_set_feedback);
		rlAbout = (RelativeLayout) findViewById(R.id.rl_set_about);
		rlExit = (RelativeLayout) findViewById(R.id.rl_set_exit);
		rlTranslate = (RelativeLayout) findViewById(R.id.rl_set_exit_layout);
		rlBtnClose = (RelativeLayout) findViewById(R.id.rl_set_close_btn);
		rlBtnExit = (RelativeLayout) findViewById(R.id.rl_set_exit_btn);
		
		rlFeedback.setOnClickListener(this);
		rlAbout.setOnClickListener(this);
		rlExit.setOnClickListener(this);
		rlBtnClose.setOnClickListener(this);
		rlBtnExit.setOnClickListener(this);
		rlTranslate.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		final int id = arg0.getId();
		switch (id) {
		case R.id.rl_set_feedback:
			Intent intent = new Intent(SetActivity.this,FeedBackActivity.class);
			startActivity(intent);
			break;
			
		case R.id.rl_set_about:
			Intent abountIntent = new Intent(SetActivity.this,AboutActivity.class);
			startActivity(abountIntent);
			break;
			
		case R.id.rl_set_exit:
			rlTranslate.setVisibility(View.VISIBLE);
			break;		
			
		case R.id.rl_set_close_btn:
			rlTranslate.setVisibility(View.GONE);
			YouYouApplication.instance.exit();
			break;
			
		case R.id.rl_set_exit_btn:
			rlTranslate.setVisibility(View.GONE);
			Intent exitIntent = new Intent(SetActivity.this,LoginActivity.class);
			startActivity(exitIntent);
			finish();
			break;
			
		case R.id.rl_set_exit_layout:
			rlTranslate.setVisibility(View.GONE);
			break;

		default:
			break;
		}
	}
	
}
