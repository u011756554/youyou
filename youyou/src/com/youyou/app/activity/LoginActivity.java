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
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends BaseActivity{

	private EditText accountEdt;
	private EditText pwdEdt;
	private Button loginBtn;
	
	private TextView tvForgetPwd;
	private TextView tvRegister;
	
	private ImageView clearImg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
	}
	
	private void initView() {
		accountEdt = (EditText) findViewById(R.id.edt_login_account);
		pwdEdt = (EditText) findViewById(R.id.edt_login_pwd);
		loginBtn = (Button) findViewById(R.id.btn_login);
		
		tvForgetPwd= (TextView) findViewById(R.id.tv_forgetpwd);
		tvRegister = (TextView) findViewById(R.id.tv_register);
		
		clearImg = (ImageView) findViewById(R.id.iv_bootom_delete);
		clearImg.setOnClickListener(this);
		
		loginBtn.setOnClickListener(this);
		tvForgetPwd.setOnClickListener(this);
		tvRegister.setOnClickListener(this);
	}

	private boolean checkRegiterInput() {
		if(!TextUtils.isEmpty(accountEdt.getText().toString().trim())
				&& !TextUtils.isEmpty(pwdEdt.getText().toString().trim())) {
			return true;
		}
		return false;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		final int id = v.getId();
		switch(id) {
		case R.id.iv_bootom_delete:
			pwdEdt.setText("");
			break;
			
		case R.id.btn_login:
			if (checkRegiterInput()) {
				String accountString = accountEdt.getText().toString();
				String pwd = pwdEdt.getText().toString();
				EventBus eventBus = new EventBus();
				eventBus.setListener(new EventListener() {
					
					@Override
					public void onEventRunEnd(Event event) {
						if (pg.isShowing()) {
							pg.dismiss();							
						}
						if (event.isSuccess() && EventCode.HTTP_LOGIN == event.getEventCode()) {
							LoginMsgBean msg = (LoginMsgBean) event.getReturnParamAtIndex(0);
							PreManager.instance().put(getApplicationContext(), AppContext.LOGIN_USER, msg.getData());
							Intent intent = new Intent(LoginActivity.this,MainActivity.class);
							startActivity(intent);
							finish();	
						} else {
							if (event.getFailException() != null) {
								showToast(event.getFailException().getMessage());
							} else {
								showToast("用户名或密码错误");
							}
						}
					}
				});
				pg.setTitle("正在登录");
				if (!pg.isShowing()) {
					pg.show();	
				}
				eventBus.pushEvent(EventCode.HTTP_LOGIN, accountString,pwd);
			} else {
				showToast("请输入完整");
			}
			break;
			
		case R.id.tv_forgetpwd:
			break;
			
		case R.id.tv_register:
			Intent intent = new Intent(this,RegisterActivity.class);
			startActivity(intent);
			break;
		}
	}
	
}
