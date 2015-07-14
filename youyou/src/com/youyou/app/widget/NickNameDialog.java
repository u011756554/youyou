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

public class NickNameDialog extends Dialog implements android.view.View.OnClickListener {
	
	private Context context;
	private DialogListener listener;
	private EditText edtNickName;
	private Button btnSureButton;
	private Button btnCancelButton;
	public NickNameDialog(Context context) {
		// TODO Auto-generated constructor stub
		super(context, R.style.ShareDialog);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_nickname);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
		edtNickName = (EditText) findViewById(R.id.met_input_nickname);
		btnSureButton = (Button) findViewById(R.id.btn_input_sure);
		btnCancelButton = (Button) findViewById(R.id.btn_input_cancel);
		btnSureButton.setOnClickListener(this);
		btnCancelButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		final int id = v.getId();
		switch (id) {
		case R.id.btn_input_sure:
			if (TextUtils.isEmpty(edtNickName.getText().toString())) {
				Toast.makeText(context, "请输入昵称", Toast.LENGTH_LONG).show();
				return;
			}
			listener.update(edtNickName.getText().toString());
			dismiss();
			break;
			
		case R.id.btn_input_cancel:
			dismiss();
			break;

		default:
			break;
		}
	}
	
	public void setListener(DialogListener listener) {
		this.listener = listener;
	}
	
	public interface DialogListener {
		void update(String nickname);
	}
}
