package com.youyou.app.widget;

import com.youyou.app.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class InputPayPasswordDialog extends Dialog implements android.view.View.OnClickListener {
	
	private TextView tvCount;
	private TextView tvName;
	private EditText metCountInput;
	private Button btnCancel;
	private Button btnSure;
	private RelativeLayout rlSeePwd;
	private ImageView ivSeePwd;
	private String sendCount;
	
	private Context context;
	public InputPayPasswordDialog(Context context) {
		// TODO Auto-generated constructor stub
		super(context, R.style.ShareDialog);
		this.context = context;
	}

	public void initSendCount(String sendCount) {
		this.sendCount = sendCount;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_input_paypassword);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
		tvCount = (TextView) findViewById(R.id.tv_input_paypassword_count);
		tvCount.setText(this.sendCount+"嗨币");
		metCountInput = (MaterialEditText) findViewById(R.id.met_input_passsword);
		tvName = (TextView) findViewById(R.id.tv_input_paypassword_name);
		tvName.setText("向"+msg.getAlias()+"赠送：");
		btnSure = (Button) findViewById(R.id.btn_input_sure);
		btnCancel = (Button) findViewById(R.id.btn_input_cancel);
		ivSeePwd = (ImageView) findViewById(R.id.iv_input_see);
		rlSeePwd = (RelativeLayout) findViewById(R.id.rl_input_see);
		rlSeePwd.setOnClickListener(this);
		metCountInput.setTextColor(context.getResources().getColor(R.color.no13_black));
		metCountInput.setHintTextColor(context.getResources().getColor(R.color.no8_gray_silver));
		btnCancel.setOnClickListener(this);
		btnSure.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		final int id = v.getId();
		switch (id) {
		case R.id.btn_input_cancel:
			dismiss();
			listener.showUI();
			break;
			
		case R.id.rl_input_see:
			// 不可见
			if (metCountInput.getInputType() == 0x81) {
				metCountInput.setInputType(0x90);
				ivSeePwd.setBackgroundResource(R.drawable.icon_pwd_show);
			} else {
				// 可见
				metCountInput.setInputType(0x81);
				ivSeePwd.setBackgroundResource(R.drawable.icon_pwd_hide);
			}			
			break;
			
		case R.id.btn_input_sure:
			if ("0".equals(this.sendCount)) {
				metCountInput.setError("赠送币格式错误");
				return;
			}
			if (TextUtils.isEmpty(metCountInput.getText().toString())) {
				metCountInput.setError("请输入密码");
			} else {
				//接入转账接口
				try {
					ServiceFactory.getPayNewService().transfer(this.msg.getUser_id(), getHaiYouCount(sendCount+""), DigestUtil.encryptSHA1(AppConfig.SECURITY_KEY + metCountInput.getText().toString()), new HttpTask<BaseMessage>() {

						@Override
						public void onSuccess(BaseMessage data) {
							// TODO Auto-generated method stub
							dismiss();
							listener.successfulVerification();
						}

						@Override
						public void onFailure(String errCode, String msg) {
							// TODO Auto-generated method stub
							Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
							listener.failureVerification(errCode, msg);
						}
					});
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			break;

		default:
			break;
		}
	}

	private String getHaiYouCount(String count) {
		float f = Float.valueOf(count);
		return (long) f + "";
	}

}
