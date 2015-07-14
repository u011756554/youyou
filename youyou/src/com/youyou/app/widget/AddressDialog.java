package com.youyou.app.widget;

import com.youyou.app.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddressDialog extends Dialog implements android.view.View.OnClickListener {

	private Context context;
	private DialogListener listener;
	private EditText edtProvinceEditText;
	private EditText edtCityEditText;
	private Button btnSure;
	private Button btnCancel;
	public AddressDialog(Context context) {
		super(context,R.style.ShareDialog);
		this.context = context;
	}
	
	@Override
	public void onClick(View v) {
		final int id = v.getId();
		switch (id) {
		case R.id.btn_input_sure:
			if (TextUtils.isEmpty(edtProvinceEditText.getText().toString()) 
					|| TextUtils.isEmpty(edtCityEditText.getText().toString())) {
				Toast.makeText(context, "请输入完整", Toast.LENGTH_LONG).show();
				return;
			}
			listener.update(edtProvinceEditText.getText().toString(), edtCityEditText.getText().toString());
			dismiss();
			break;
			
		case R.id.btn_input_cancel:
			dismiss();
			break;

		default:
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_nickname);
		edtProvinceEditText = (EditText) findViewById(R.id.met_input_province);
		edtCityEditText = (EditText) findViewById(R.id.met_input_city);
		btnSure = (Button) findViewById(R.id.btn_input_sure);
		btnCancel = (Button) findViewById(R.id.btn_input_cancel);
		btnCancel.setOnClickListener(this);
		btnSure.setOnClickListener(this);
	}
	
	public void setListener(DialogListener listener) {
		this.listener = listener;
	}
	
	public interface DialogListener {
		void update(String province,String city);
	}

}
