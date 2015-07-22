package com.youyou.app.activity;

import java.util.ArrayList;
import java.util.List;

import com.youyou.app.R;
import com.youyou.app.adapter.GameAdapter;
import com.youyou.app.adapter.GameAdapter.UpdateListener;
import com.youyou.app.bean.Game;
import com.youyou.app.net.Event;
import com.youyou.app.net.EventBus;
import com.youyou.app.net.EventCode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class GameActivity extends RefreshAndLoadActivity {

	private List<Game> list = new ArrayList<Game>();
	private GameAdapter adapter;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_game);
		addBack(true);
		setTitle("游戏中心");
		initView();
		refresh();
	}
	
	private void initView() {

	}

	@Override
	protected void initAdapter() {
		adapter = new GameAdapter(context);
		adapter.setData(list);
		adapter.setListener(new UpdateListener() {

			@Override
			public void click(Game game) {
				Intent gameIntent = new Intent(GameActivity.this,GameDetailActivity.class);
				gameIntent.putExtra(GameDetailActivity.KEY_GAME, game);
				startActivity(gameIntent);
			}
			
		});
		mAdapter = adapter;
	}

	@Override
	protected void refreshData() {
		adapter.clear();
		EventBus eventBus = new EventBus();
		eventBus.setListener(RefreshListener);
		eventBus.pushEvent(EventCode.HTTP_GETGAMES, list.size()+"",page_size+"");
	}

	@Override
	protected void loadData() {
		EventBus eventBus = new EventBus();
		eventBus.setListener(loadMoreListener);
		eventBus.pushEvent(EventCode.HTTP_GETGAMES, list.size()+"",page_size+"");
	}

	@Override
	protected void loadEventRunEnd(Event event) {
		if (event.isSuccess()) {
			List<Game> gameList = (List<Game>) event.getReturnParamAtIndex(0);
			adapter.addAll(gameList);
		} else {
			if (event.getFailException() != null) {
				showToast(event.getFailException().getMessage());
			} else {
				showToast("获取数据失败");
			}
		}
	}

	@Override
	protected void refreshEventRunEnd(Event event) {
		if (event.isSuccess()) {
			List<Game> gameList = (List<Game>) event.getReturnParamAtIndex(0);
			adapter.addAll(gameList);
		} else {
			if (event.getFailException() != null) {
				showToast(event.getFailException().getMessage());
			} else {
				showToast("获取数据失败");
			}
		}
	}

	
}
