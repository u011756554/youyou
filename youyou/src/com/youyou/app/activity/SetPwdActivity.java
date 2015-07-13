package com.youyou.app.activity;

import com.youyou.app.R;
import com.youyou.app.bean.message.BaseBean;
import com.youyou.app.net.Event;
import com.youyou.app.net.EventBus;
import com.youyou.app.net.EventCode;
import com.youyou.app.net.EventListener;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SetPwdActivity extends BaseActivity{

	private EditText edtPwd;
	private EditText edtPwdAgain;
	private Button btnSet;
	
	public static final String KEY_PHONE = "phone";
	public static final String KEY_CODE = "code";
	
	private String phone;
	private String code;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_setpwd);
		getData();
		initView();
	}

	private void initView() {
		addBack(true);
		setTitle("重置密码");
		
		edtPwd = (EditText) findViewById(R.id.edt_setpwd_inputcode);
		edtPwdAgain = (EditText) findViewById(R.id.edt_setpwd_pwd);
		btnSet = (Button) findViewById(R.id.btn_setpwd);
		
		btnSet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String pwd = edtPwd.getText().toString();
				String pwdAgain = edtPwdAgain.getText().toString();
				if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(code) || TextUtils.isEmpty(pwdAgain) || TextUtils.isEmpty(pwd)) {
					showToast("参数错误");
					return;
				}
				if (!isSame(pwd, pwdAgain)) {
					showToast("密码不一致，请重新输入");
					return;
				}
				EventBus eventBus = new EventBus();
				eventBus.setListener(new EventListener() {
					
					@Override
					public void onEventRunEnd(Event event) {
						if (event.isSuccess()) {
							showToast("设置密码成功，请重新登陆");
						} else {
							if (event.getFailException() != null) {
								showToast(event.getFailException().getMessage());
							} else {
								BaseBean msg = (BaseBean) event.getReturnParamAtIndex(0);
								showToast(msg.getMessage());
							}
						}
					}
				});
				eventBus.pushEvent(EventCode.HTTP_GETBACKPWD, phone,code,pwd);
			}
		});
		
	}
	
	private boolean isSame(String pwd,String pwdAgain) {
		if (pwd.equals(pwdAgain)) {
			return true;
		}
		return false;
	}
	
	private void getData() {
		Intent intent = getIntent();
		phone = intent.getStringExtra(KEY_PHONE);
		code = intent.getStringExtra(KEY_CODE);
	}
}
