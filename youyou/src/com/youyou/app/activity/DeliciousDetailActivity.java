package com.youyou.app.activity;

import java.util.ArrayList;
import java.util.List;

import com.youyou.app.AppContext;
import com.youyou.app.R;
import com.youyou.app.adapter.MarkAdapter;
import com.youyou.app.bean.FoodStory;
import com.youyou.app.bean.Mark;
import com.youyou.app.bean.message.BaseBean;
import com.youyou.app.bean.message.MarkMsgBean;
import com.youyou.app.net.Event;
import com.youyou.app.net.EventBus;
import com.youyou.app.net.EventCode;
import com.youyou.app.net.EventListener;
import com.youyou.app.utils.ImageLoaderUtil;
import com.youyou.app.widget.MyListView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DeliciousDetailActivity extends BaseActivity{

	private ImageView ivDelicious;
	private TextView tvName;
	private TextView tvConent;
	private TextView tvMarkCount;
	private ImageView ivZan;
	private EditText edtMark;
	private Button btnMark;
	private MyListView lvMark;
	
	public static final String KEY_FOODSTORY = "food_story";
	private FoodStory mFoodStory;
	
	private List<Mark> markList = new ArrayList<Mark>();
	private MarkAdapter adapter;
	private int markesize = 10;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_deliciousdetail);
		adapter = new MarkAdapter(markList);
		getData();
		initView();
	}

	private void initView() {
		ivDelicious = (ImageView) findViewById(R.id.iv_deliciousdetail_img);
		tvName = (TextView) findViewById(R.id.tv_deliciousdetail_name);
		tvConent = (TextView) findViewById(R.id.tv_deliciousdetail_content);
		tvMarkCount = (TextView) findViewById(R.id.tv_deliciousdetail_markcount);
		ivZan = (ImageView) findViewById(R.id.iv_deliciousdetail_write);
		edtMark = (EditText) findViewById(R.id.edt_deliciousdetail_mark);
		btnMark = (Button) findViewById(R.id.btn_deliciousdetail_mark);
		lvMark = (MyListView) findViewById(R.id.lv_deliciousdetail_mark);
		lvMark.setAdapter(adapter);
		
		if (mFoodStory != null) {
			ImageLoaderUtil.diaPlayNormal(mFoodStory.getUrl(), ivDelicious, R.drawable.logo, null);
			tvName.setText(mFoodStory.getTitle());
			tvConent.setText(mFoodStory.getDescription());
			setTitle(mFoodStory.getTitle());
		}
		
		ivZan.setOnClickListener(this);
		btnMark.setOnClickListener(this);
		
		addBack(true);
	}
	
	private void getData() {
		Intent intent = getIntent();
		mFoodStory = (FoodStory) intent.getSerializableExtra(KEY_FOODSTORY);
		
		if (mFoodStory != null) {
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
			eventBus.pushEvent(EventCode.HTTP_GETCOMMENTS, AppContext.TYPE_FOOD,mFoodStory.getId(),adapter.mObjectList == null ? "0" : adapter.mObjectList.size()+"",markesize+"");
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		final int id = arg0.getId();
		switch(id) {
		case R.id.btn_deliciousdetail_mark:
			String markContent =edtMark.getText().toString().trim();
			if (!TextUtils.isEmpty(markContent) && mFoodStory != null) {
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
				eventBus.pushEvent(EventCode.HTTP_COMMENT, AppContext.TYPE_FOOD,mFoodStory.getId(),"",markContent);
			}
			break;
		}
	}
	
	
	
}
