package com.youyou.app.activity;

import com.youyou.app.AppContext;
import com.youyou.app.R;
import com.youyou.app.bean.User;
import com.youyou.app.bean.message.LoginMsgBean;
import com.youyou.app.net.Event;
import com.youyou.app.net.EventBus;
import com.youyou.app.net.EventCode;
import com.youyou.app.net.EventListener;
import com.youyou.app.utils.PreManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class StartActivity extends BaseActivity{

	private ProgressBar pd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		pd = (ProgressBar) findViewById(R.id.pd_loading);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {				
				User user = PreManager.instance().get(getApplicationContext(), AppContext.LOGIN_USER, User.class);
				if (user != null) {
					pd.setVisibility(View.VISIBLE);
					EventBus eventBus = new EventBus();
					eventBus.setListener(new EventListener() {
						
						@Override
						public void onEventRunEnd(Event event) {
							// TODO Auto-generated method stub
							pd.setVisibility(View.GONE);
							if (event.isSuccess() && EventCode.HTTP_LOGIN == event.getEventCode()) {
								LoginMsgBean msg = (LoginMsgBean) event.getReturnParamAtIndex(0);
								PreManager.instance().put(getApplicationContext(), AppContext.LOGIN_USER, msg.getData());
								Intent intent = new Intent(StartActivity.this,MainActivity.class);
								startActivity(intent);
								finish();	
							} else {
								if (event.getFailException() != null) {
									showToast(event.getFailException().getMessage());
								} else {
									showToast("用户名或密码错误");
								}
								Intent intent = new Intent(StartActivity.this,LoginActivity.class);
								startActivity(intent);
								finish();	
							}
						}
					});
					eventBus.pushEvent(EventCode.HTTP_LOGIN, user.getUsername(),user.getPassword());
				} else {
					Intent intent = new Intent(StartActivity.this,LoginActivity.class);
					startActivity(intent);
					finish();	
				}
			}
			
		}, 3000);
	}
	
}
