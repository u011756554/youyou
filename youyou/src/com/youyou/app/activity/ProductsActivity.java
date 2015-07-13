package com.youyou.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.youyou.app.R;

public class ProductsActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_products);
		addBack(true);
		setTitle("商品商城");
		ImageView ivImageView = (ImageView) findViewById(R.id.iv_products);
		ivImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ProductsActivity.this,WaitActivity.class);
				startActivity(intent);
			}
		});
	}
}
