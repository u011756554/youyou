package com.youyou.app.activity;

import com.youyou.app.R;
import com.youyou.app.bean.message.BaseBean;
import com.youyou.app.net.Event;
import com.youyou.app.net.EventBus;
import com.youyou.app.net.EventCode;
import com.youyou.app.net.EventListener;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FeedBackActivity extends BaseActivity{

	private EditText edtFeedBack;
	private Button btnFeedBack;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_feedback);
		addBack(true);
		setTitle("反馈");
		initView();
	}

	private void initView() {
		edtFeedBack = (EditText) findViewById(R.id.edt_feedback);
		btnFeedBack = (Button) findViewById(R.id.btn_feedback);
		
		btnFeedBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(edtFeedBack.getText().toString().trim())) {
					showToast("请输入反馈信息");
				} else {
					EventBus eventbus = new EventBus();
					eventbus.setListener(new EventListener() {
						
						@Override
						public void onEventRunEnd(Event event) {
							if (event.isSuccess()) {
								showToast("反馈成功");
							} else {
								if (event.getFailException() != null) {
									showToast(event.getFailException().getMessage());
								} else {
									BaseBean msg = (BaseBean) event.getReturnParamAtIndex(0);
									if (msg != null) {
										showToast(msg.getMessage());
									}
								}
							}
						}
					});
					eventbus.pushEvent(EventCode.HTTP_USERCOMMENTS, edtFeedBack.getText().toString().trim());
				}
			}
		});
	}
	
}
