package com.youyou.app.activity;

import java.util.ArrayList;
import java.util.List;

import com.youyou.app.AppContext;
import com.youyou.app.R;
import com.youyou.app.adapter.MarkAdapter;
import com.youyou.app.bean.CurrentActivity;
import com.youyou.app.bean.Mark;
import com.youyou.app.bean.message.BaseBean;
import com.youyou.app.bean.message.MarkMsgBean;
import com.youyou.app.net.Event;
import com.youyou.app.net.EventBus;
import com.youyou.app.net.EventCode;
import com.youyou.app.net.EventListener;
import com.youyou.app.utils.ImageLoaderUtil;
import com.youyou.app.widget.MyListView;

import android.R.integer;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NewDetailActivity extends BaseActivity{

	private ImageView ivNew;
	private TextView tvNewInfo;
	private Button btnJoin;
	private TextView tvContent;
	private TextView tvName;
	private TextView tvAddress;
	private RelativeLayout rlCall;
	private MyListView lvMark;
	private ImageView ivZan;
	private EditText edtMark;
	private Button btnMark;
	private TextView tvMarkCount;
	
	public static final String 	KEY_CURRENTACTIVITY = "current_activity";
	private CurrentActivity mCurrentActivity;
	
	private List<Mark> markList = new ArrayList<Mark>();
	private MarkAdapter adapter;
	private int markesize = 10;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_activityinfo);
		adapter = new MarkAdapter(markList);
		getData();
		initView();
	}

	private void getData() {
		Intent intent = getIntent();
		mCurrentActivity = (CurrentActivity) intent.getSerializableExtra(KEY_CURRENTACTIVITY);
		
		if (mCurrentActivity != null) {
			EventBus eventBus = new EventBus();
			eventBus.setListener(new EventListener() {
				
				@Override
				public void onEventRunEnd(Event event) {
					if (event.isSuccess()) {
						MarkMsgBean msg = (MarkMsgBean) event.getReturnParamAtIndex(0);
						adapter.addAll(msg.getData());
						lvMark.setSelection(adapter.mObjectList.size() - 1);
						tvMarkCount.setText(adapter.mObjectList.size() + "条评论");
					} else {
						if (event.getFailException() != null) {
							showToast(event.getFailException().getMessage());
						} else {
							MarkMsgBean msg = (MarkMsgBean) event.getReturnParamAtIndex(0);
							if (msg != null && !TextUtils.isEmpty(msg.getMessage())) {
								showToast(msg.getMessage());
							}
						}
					}					
				}
			});
			eventBus.pushEvent(EventCode.HTTP_GETCOMMENTS, AppContext.TYPE_ACTIVITY,mCurrentActivity.getId(),adapter.mObjectList == null ? "0" : adapter.mObjectList.size()+"",markesize+"");
		}
	}
	
	
	private void initView() {
		ivNew = (ImageView) findViewById(R.id.iv_activityinfo_img);
		ivZan = (ImageView) findViewById(R.id.iv_activityinfo_write);
		tvAddress = (TextView) findViewById(R.id.tv_activityinfo_address);
		tvContent = (TextView) findViewById(R.id.tv_activityinfo_content);
		tvName = (TextView) findViewById(R.id.tv_activityinfo_name);
		tvNewInfo = (TextView) findViewById(R.id.tv_activityinfo_info);
		btnJoin = (Button) findViewById(R.id.btn_activityinfo_injoy);
		btnMark = (Button) findViewById(R.id.btn_activityinfo_mark);
		edtMark = (EditText) findViewById(R.id.edt_activityinfo_mark);
		rlCall = (RelativeLayout) findViewById(R.id.rl_activityinfo_call);
		tvMarkCount = (TextView) findViewById(R.id.tv_activityinfo_markcount);
		lvMark = (MyListView) findViewById(R.id.lv_activityinfo_mark);
		
		lvMark.setAdapter(adapter);
		
		if (mCurrentActivity != null) {
			ImageLoaderUtil.diaPlayNormal(mCurrentActivity.getUrl(), ivNew, R.drawable.logo, null);
			tvAddress.setText(mCurrentActivity.getMerchantAddress());
			tvContent.setText(mCurrentActivity.getDescription());
			tvName.setText(mCurrentActivity.getTitle());
			tvNewInfo.setText(mCurrentActivity.getTitle());
			setTitle(mCurrentActivity.getTitle());
		}
		
		rlCall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(mCurrentActivity.getMerchantPhone())) {
					Toast.makeText(getApplicationContext(), "号码为空", Toast.LENGTH_LONG).show();
					return;
				}
				Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+mCurrentActivity.getMerchantPhone().trim()));
				startActivity(intent);
			}
		});
		
		btnJoin.setOnClickListener(this);
		btnMark.setOnClickListener(this);
		
		addRight(R.drawable.peoples);
		addBack(true);
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		final int id = arg0.getId();
		switch (id) {
		case R.id.btn_activityinfo_injoy:
			if (mCurrentActivity != null) {
				EventBus eventBus = new EventBus();
				eventBus.setListener(new EventListener() {
					
					@Override
					public void onEventRunEnd(Event event) {
						if (event.isSuccess()) {
							showToast("报名成功");
						} else {
							if (event.getFailException() != null) {
								showToast(event.getFailException().getMessage());
							} else {
								BaseBean msgBaseBean = (BaseBean) event.getReturnParamAtIndex(0);
								if (msgBaseBean != null && !TextUtils.isEmpty(msgBaseBean.getMessage())) {
									showToast(msgBaseBean.getMessage());
								}
							}
						}
					}
				});
				eventBus.pushEvent(EventCode.HTTP_JOINACTIVITY, mCurrentActivity.getId());				
			}
			break;
			
		case R.id.btn_activityinfo_mark:
			String markContent =edtMark.getText().toString().trim();
			if (!TextUtils.isEmpty(markContent) && mCurrentActivity != null) {
				EventBus eventBus = new EventBus();
				eventBus.setListener(new EventListener() {
					
					@Override
					public void onEventRunEnd(Event event) {
						if (event.isSuccess()) {
							getData();
						} else {
							if (event.getFailException() != null) {
								showToast(event.getFailException().getMessage());
							} else {
								BaseBean msg = (BaseBean) event.getReturnParamAtIndex(0);
								if (msg != null && !TextUtils.isEmpty(msg.getMessage())) {
									showToast(msg.getMessage());
								}
							}
						}
					}
				});
				eventBus.pushEvent(EventCode.HTTP_COMMENT, AppContext.TYPE_ACTIVITY,mCurrentActivity.getId(),"",markContent);
			}
			break;

		default:
			break;
		}
	}

	@Override
	protected void clickRight() {
		if (mCurrentActivity != null) {
			Intent intent = new Intent(NewDetailActivity.this,PeoplesActivity.class);
			intent.putExtra(PeoplesActivity.KEY_NEW, mCurrentActivity);
			startActivity(intent);
		}
	}
	
	
}
