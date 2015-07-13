package com.youyou.app.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.youyou.app.AppContext;
import com.youyou.app.R;
import com.youyou.app.bean.message.BaseBean;
import com.youyou.app.net.Event;
import com.youyou.app.net.EventBus;
import com.youyou.app.net.EventCode;
import com.youyou.app.net.EventListener;
import com.youyou.app.utils.MatchUtils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class GetBackPwdActivity extends BaseActivity{

	private EditText edtPhone;
	private Button btnGetCode;
	private EditText edtInputCode;
	private Button btnBackPwd;
	
	private boolean isCode = true;
	private static final int GET_CODE = 0x001;
	private static final int GET_OVER = 0x002;
	private int CODE_TIME = 60;
	// 改变获取验证码时间定时器
	private Timer timer = new Timer();
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			final int id = msg.what;
			switch (id) {
				
			case GET_OVER:
				btnGetCode.setEnabled(true);
				btnGetCode.setText("重新获取"); 
				break;
				
			case GET_CODE:
				if (CODE_TIME > 0) {
					CODE_TIME = CODE_TIME - 1;
					btnGetCode.setEnabled(false);
					btnGetCode.setText("获取验证码" + "(" + CODE_TIME + ")"); 
				} else {
					isCode = true; 
				}
				break;

			default:
				break;
			}
		};
	};

	/**
	 * 开始验证码倒计时
	 */
	private void startTimer() {
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (isCode) {
					CODE_TIME = 60;
					Message msgMessage = new Message();
					msgMessage.what = GET_OVER;
					handler.sendMessage(msgMessage);					
					cancel();
				} else {
					Message msgMessage = new Message();
					msgMessage.what = GET_CODE;
					handler.sendMessage(msgMessage);
				}
			}
		}, 0, 1000);
	}
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_getbackpwd);
		initView();
	}

	private void initView() {
		addBack(true);
		setTitle("找回密码");
		
		edtPhone = (EditText) findViewById(R.id.edt_backpwd_phone);
		btnGetCode = (Button) findViewById(R.id.btn_backpwd_getcode);
		edtInputCode = (EditText) findViewById(R.id.edt_backpwd_inputcode);
		btnBackPwd = (Button) findViewById(R.id.btn_backpwd);
		
		btnGetCode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String phone = edtPhone.getText().toString().trim();
				if (TextUtils.isEmpty(phone)) {
					showToast("请输入手机号");
					return;
				} else if (!MatchUtils.matchPhone(phone)) {
					showToast("手机号格式不正确");
					return;
				}
				if (isCode) {
					isCode = false;
					startTimer();
					EventBus eventBus = new EventBus();
					eventBus.setListener(new EventListener() {
						
						@Override
						public void onEventRunEnd(Event event) {
							if (event.isSuccess()) {
								showToast("验证码已发送到您手机，注意查收");
							} else {
								isCode = true;
								if (event.getFailException() != null) {
									showToast(event.getFailException().getMessage());
								} else {
									showToast("验证码发送失败");
								}
							}
						}
					});
					eventBus.pushEvent(EventCode.HTTP_GETVALIDATECODE, phone,AppContext.VALIDMODE_GETBACKPWD);					
				}
			}
		});
		
		btnBackPwd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(edtPhone.getText().toString())
						|| TextUtils.isEmpty(edtInputCode.getText().toString())) {
					showToast("请输入完整");
					return;
				}			
				String phone = edtPhone.getText().toString().trim();
				String code = edtInputCode.getText().toString().trim();
				
				Intent intent = new Intent(GetBackPwdActivity.this,SetPwdActivity.class);
				intent.putExtra(SetPwdActivity.KEY_PHONE, phone);
				intent.putExtra(SetPwdActivity.KEY_CODE, code);
				startActivity(intent);
			}
		});
	}
}
