package com.youyou.app.activity;

import com.youyou.app.R;
import com.youyou.app.bean.Advert;
import com.youyou.app.utils.ImageLoaderUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AdvertDetailActivity extends BaseActivity{

	private ImageView ivAdvert;
	private TextView tvName;
	private TextView tvContent;
	
	private Advert mAdvert;
	public static final String 	KEY_ADVERT = "key_advert";
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
        setContentView(R.layout.activity_advertdetail);
        initData();
        initView();
	}

	private void initView() {
		ivAdvert = (ImageView) findViewById(R.id.iv_advertdetail);
		tvName = (TextView) findViewById(R.id.tv_advertdetail_name);
		tvContent = (TextView) findViewById(R.id.tv_advertdetail_content);
		
		if (mAdvert != null) {
			ImageLoaderUtil.diaPlayNormal(mAdvert.getUrl(), ivAdvert, R.drawable.logo, null);
			tvName.setText(mAdvert.getTitle());
			setTitle(mAdvert.getTitle());
			tvContent.setText(mAdvert.getDescription());
		}
		addBack(true);
	}
	
	private void initData() {
		Intent intent = getIntent();
		mAdvert = (Advert) intent.getSerializableExtra(KEY_ADVERT);
	}
}
