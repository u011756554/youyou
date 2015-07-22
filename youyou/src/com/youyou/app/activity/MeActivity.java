package com.youyou.app.activity;

import com.youyou.app.R;
import com.youyou.app.widget.RoundImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MeActivity extends BaseActivity{

	private RoundImageView ivHead;
	
	private LinearLayout llDuiHuanQuan;
	private LinearLayout llScore;
	private LinearLayout llUpdate;
	private LinearLayout llSet;
	private LinearLayout llMessage;
	
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
		ivHead = (RoundImageView) findViewById(R.id.iv_me_head);
		ivHead.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MeActivity.this,MyInfoActivity.class);
				startActivity(intent);
			}
		});
		
		llDuiHuanQuan = (LinearLayout) findViewById(R.id.ll_duihuanquan);
		llDuiHuanQuan.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		llMessage = (LinearLayout) findViewById(R.id.ll_message);
		llMessage.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent messageIntent = new Intent(MeActivity.this,MessageActivity.class);
				startActivity(messageIntent);
			}
		});
		
		llScore = (LinearLayout) findViewById(R.id.ll_score);
		llScore.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		llSet = (LinearLayout) findViewById(R.id.ll_set);
		llSet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MeActivity.this,SetActivity.class);
				startActivity(intent);
			}
		});
		
		llUpdate = (LinearLayout) findViewById(R.id.ll_update);
		llUpdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}
