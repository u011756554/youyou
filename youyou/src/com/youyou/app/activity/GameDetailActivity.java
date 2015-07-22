package com.youyou.app.activity;

import java.util.ArrayList;
import java.util.List;

import com.youyou.app.AppContext;
import com.youyou.app.R;
import com.youyou.app.adapter.MarkAdapter;
import com.youyou.app.bean.Game;
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
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameDetailActivity extends BaseActivity {

	public static final String KEY_GAME = "game";
	private Game mGame;
	
	private List<Mark> markList = new ArrayList<Mark>();
	private MarkAdapter adapter;
	private int markesize = 10;
	
	private ImageView ivNew;
	private TextView tvNewInfo;
	private RelativeLayout rlDownload;
	private RelativeLayout rlZan;
	private TextView tvContent;
	private MyListView lvMark;
	private ImageView ivZan;
	private EditText edtMark;
	private Button btnMark;
	private TextView tvMarkCount;
	
	private boolean checkCommend = false;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_gamedetail);
		addBack(true);
		initData();
		initView();
		getCommentData();
		getCommendData();
	}

	private void initData() {
		mGame = (Game) getIntent().getSerializableExtra(KEY_GAME);
	}
	
	private void initView() {
		adapter = new MarkAdapter(markList);
		
		ivNew = (ImageView) findViewById(R.id.iv_game_img);
		ivZan = (ImageView) findViewById(R.id.btn_gameinfo_zan);
		tvContent = (TextView) findViewById(R.id.tv_game_content);
		tvNewInfo = (TextView) findViewById(R.id.tv_game_info);
		btnMark = (Button) findViewById(R.id.btn_game_mark);
		edtMark = (EditText) findViewById(R.id.edt_game_mark);
		tvMarkCount = (TextView) findViewById(R.id.tv_game_markcount);
		lvMark = (MyListView) findViewById(R.id.lv_game_mark);
		rlZan = (RelativeLayout) findViewById(R.id.rl_gameinfo_zan);
		rlDownload = (RelativeLayout) findViewById(R.id.rl_gameinfo_download);
		
		lvMark.setAdapter(adapter);
		
		btnMark.setOnClickListener(this);
		rlDownload.setOnClickListener(this);
		rlZan.setOnClickListener(this);
		
		if (mGame != null) {
			setTitle(mGame.getName());
			ImageLoaderUtil.diaPlayNormal(mGame.getPicUrl(), ivNew, R.drawable.logo, null);
			tvContent.setText(mGame.getDescription());
			tvNewInfo.setText(mGame.getName());
		}
	}
	
	private void getCommentData() {
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
		eventBus.pushEvent(EventCode.HTTP_GETCOMMENTS, AppContext.TYPE_GAME,mGame.getId(),adapter.mObjectList == null ? "0" : adapter.mObjectList.size()+"",markesize+"");		
	}
	
	private  void getCommendData() {

	}

	@Override
	public void onClick(View arg0) {
		final int id = arg0.getId();
		switch (id) {
		case R.id.btn_game_mark:
			String markContent =edtMark.getText().toString().trim();
			if (!TextUtils.isEmpty(markContent) && mGame != null) {
				EventBus eventBus = new EventBus();
				eventBus.setListener(new EventListener() {
					
					@Override
					public void onEventRunEnd(Event event) {
						if (event.isSuccess()) {
							getCommentData();
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
				eventBus.pushEvent(EventCode.HTTP_COMMENT, AppContext.TYPE_GAME,mGame.getId(),"",markContent);
			} else {
				showToast("请输入完整");
			}
			break;
			
		case R.id.rl_gameinfo_download:
			if (mGame != null && !TextUtils.isEmpty(mGame.getGameUrl())) {
				Uri uri = Uri.parse(mGame.getGameUrl());
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			} else {
				showToast("安装包尚未上传");
			}
			break;
			
		case R.id.rl_gameinfo_zan:
			if (checkCommend) {
				if (ivZan.isSelected()) {
					ivZan.setSelected(false);
				} else {
					ivZan.setSelected(true);
					EventBus eventbus = new EventBus();
					eventbus.setListener(new EventListener() {
						
						@Override
						public void onEventRunEnd(Event event) {
							if (event.isSuccess()) {
								getCommendData();
							} else {
								if (event.getFailException() != null) {
									showToast(event.getFailException().getMessage());
								} else {
								
								}
							}
						}
					});
					eventbus.pushEvent(EventCode.HTTP_COMMEND, AppContext.TYPE_GAME,mGame.getId());
				}				
			}
			break;

		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		checkCommend();
	}	
	
	private void checkCommend() {
		checkCommend = false;
		if (mGame != null) {
			EventBus eventBus = new EventBus();
			eventBus.setListener(new EventListener() {
				
				@Override
				public void onEventRunEnd(Event event) {
					checkCommend = true;
					if (event.isSuccess()) {
						ivZan.setSelected(true);
					} else {
						if (event.getFailException() != null) {
							showToast(event.getFailException().getMessage());
						} else {
							ivZan.setSelected(false);
						}
					}
				}
			});
			eventBus.pushEvent(EventCode.HTTP_CHECKCOMMEND, AppContext.TYPE_GAME,mGame.getId());
			
		}
	}
	
}
