package com.youyou.app.activity;

import com.youyou.app.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class GameActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_game);
		addBack(true);
		setTitle("游戏中心");
		ImageView ivImageView = (ImageView) findViewById(R.id.iv_game);
		ivImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(GameActivity.this,WaitActivity.class);
				startActivity(intent);
			}
		});
	}

	
}
