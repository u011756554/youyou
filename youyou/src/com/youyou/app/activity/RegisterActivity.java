package com.youyou.app.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.youyou.app.AppContext;
import com.youyou.app.R;
import com.youyou.app.bean.message.BaseBean;
import com.youyou.app.net.Event;
import com.youyou.app.net.EventBus;
import com.youyou.app.net.EventCode;
import com.youyou.app.net.EventListener;
import com.youyou.app.utils.MatchUtils;

public class RegisterActivity extends BaseActivity{

	private EditText phoneEdt;
	private EditText inputCodeEdt;
	private EditText pwdEdt;
	private Button getCodeBtn;
	private Button registerBtn;
	
	private ImageView xieyiImg;
	
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
				getCodeBtn.setEnabled(true);
				getCodeBtn.setText("重新获取"); 
				break;
				
			case GET_CODE:
				if (CODE_TIME > 0) {
					CODE_TIME = CODE_TIME - 1;
					getCodeBtn.setEnabled(false);
					getCodeBtn.setText("获取验证码" + "(" + CODE_TIME + ")"); 
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
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initView();
	}
	
	private void initView() {
		setTitle("注册");
		addBack(true);
		
		phoneEdt = (EditText) findViewById(R.id.edt_register_phone);
		inputCodeEdt = (EditText) findViewById(R.id.edt_register_inputcode);
		pwdEdt = (EditText) findViewById(R.id.edt_register_pwd);
		
		getCodeBtn = (Button) findViewById(R.id.btn_register_getcode);
		registerBtn = (Button) findViewById(R.id.btn_register);
		
		xieyiImg = (ImageView) findViewById(R.id.iv_register_xieyi);
		xieyiImg.setSelected(true);
		xieyiImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (xieyiImg.isSelected()) {
					xieyiImg.setSelected(false);
				} else {
					xieyiImg.setSelected(true);
				}
			}
		});
		
		getCodeBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String phone = phoneEdt.getText().toString().trim();
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
					eventBus.pushEvent(EventCode.HTTP_GETVALIDATECODE, phone,AppContext.VALIDMODE_REGISTER);					
				}
			}
		});
		
		registerBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(phoneEdt.getText().toString())
						|| TextUtils.isEmpty(inputCodeEdt.getText().toString())
						|| TextUtils.isEmpty(pwdEdt.getText().toString())) {
					showToast("请输入完整");
					return;
				}
				if (!xieyiImg.isSelected()) {
					showToast("请阅读用户协议");
					return;
				}				
				if (!MatchUtils.matchPwd(pwdEdt.getText().toString())) {
					showToast("密码格式有误，请重新输入");
					return;
				}
				String phone = phoneEdt.getText().toString().trim();
				String code = inputCodeEdt.getText().toString().trim();
				String pwd = pwdEdt.getText().toString().trim();
				
				EventBus eventBus = new EventBus();
				eventBus.setListener(new EventListener() {
					
					@Override
					public void onEventRunEnd(Event event) {
						if (event.isSuccess()) {
							showToast("注册成功");
							finish();
						} else {
							if (event.getFailException() != null) {
								showToast(event.getFailException().getMessage());
							} else {
								BaseBean msg = (BaseBean) event.getReturnParamAtIndex(0);
								if (msg != null && TextUtils.isEmpty(msg.getMessage())) {
									showToast(msg.getMessage());
								}else {
									showToast("注册失败");									
								}
							}
						}
					}
				});
				eventBus.pushEvent(EventCode.HTTP_REGISTER, phone,pwd,"游客",code);
			}
		});
	}

}
