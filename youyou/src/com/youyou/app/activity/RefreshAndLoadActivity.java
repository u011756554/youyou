package com.youyou.app.activity;

import java.util.List;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyou.app.R;
import com.youyou.app.net.Event;
import com.youyou.app.net.EventListener;
import com.youyou.app.widget.MyListView;
import com.youyou.app.widget.MyScrollView;
import com.youyou.app.widget.MyScrollView.OnScrollChangedListener;

public abstract class RefreshAndLoadActivity extends BaseActivity implements OnScrollChangedListener {

	private MyScrollView mScrollView;
	protected MyListView listView;
	private RelativeLayout rlMore;
	private RelativeLayout rlRefresh;
	
	protected int page_size = 10;
	private TextView noMoreText;
	private TextView loadMoreText;
	private TextView refreshText;
	private boolean isLoading = false;
	private boolean isOver = false;
	private boolean isRefreshing = false;
	
	protected BaseAdapter mAdapter;
	
	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		initAdapter();
		initView();
	}
	
	/**
	 * 为适配器初始化数据,比如初始化page_size，mList，mAdapter
	 * 必须初始化mAdapter
	 */
	protected abstract void initAdapter();
	
	private void initView() {
		mScrollView = (MyScrollView) findViewById(R.id.sv);
		listView = (MyListView) findViewById(R.id.mlv);
		rlMore = (RelativeLayout) findViewById(R.id.ll_more);
		rlRefresh = (RelativeLayout) findViewById(R.id.ll_refresh);
		noMoreText = (TextView) findViewById(R.id.no_more);
		loadMoreText = (TextView) findViewById(R.id.load_more);
		refreshText = (TextView) findViewById(R.id.tv_refresh);
		
		mScrollView.setOnScrollChangedListener(this);
		listView.setAdapter(mAdapter);
	}
	
	protected void refresh() {
		isRefreshing =  true;
		refreshText.setVisibility(View.VISIBLE);
		refreshData();
	}
	
	/**
	 * 下拉刷新数据
	 */
	protected abstract void refreshData();
	
	protected void load() {
		isLoading = true;
		loadMoreText.setVisibility(View.VISIBLE);
		noMoreText.setVisibility(View.INVISIBLE);
		loadData();
	}
	
	/**
	 * 上拉加载数据
	 */
	protected abstract void loadData();
	
	@Override
	public void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		int svHeight = mScrollView.getHeight();
		int lvHeight = listView.getHeight();
		int llLoadHeight = rlMore.getHeight();
		int llRefreshHight = rlRefresh.getHeight();
		int mLastY = lvHeight+llLoadHeight+llRefreshHight-svHeight;
		if (t == mLastY) {
			if (!isLoading && !isOver && !isRefreshing) {
				isOver = false;
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
												
					}
				});
				load();			}
		} else {
			if (t == 0 && !isLoading && !isRefreshing && !isOver) {
				isOver = false;
				refresh();
			}
		}
		System.out.println("caifu测试： "+"svHeight:"+svHeight+"  "+" lvHeight："+lvHeight+"  "+" llMore:"+llLoadHeight+"  "+"mLastY:"+mLastY+"  l:"+l+" t:"+t+"  old1:"+oldl+"  oldt:"+oldt);
	}
	
	protected EventListener loadMoreListener = new EventListener() {
		
		@Override
		public void onEventRunEnd(Event event) {
			isLoading = false;
			if (event.isSuccess()) {
				List list = (List) event.getReturnParamAtIndex(0);
				if (list.size() < page_size) {
					noMoreText.setVisibility(View.VISIBLE);
					loadMoreText.setVisibility(View.INVISIBLE);
					isOver = true;
				}				
			}
			loadEventRunEnd(event);
		}
	};
	
	/**
	 * 加载完成，必须设置加载回调为loadMoreListener
	 * @param event
	 */
	protected abstract void loadEventRunEnd(Event event);
	
	protected EventListener RefreshListener = new EventListener() {
		
		@Override
		public void onEventRunEnd(Event event) {
			isRefreshing = false;
			refreshText.setVisibility(View.GONE);
			if (event.isSuccess()) {
				List list = (List) event.getReturnParamAtIndex(0);
				if (list.size() < page_size) {
					isOver = true;
				}				
			}
			refreshEventRunEnd(event);
		}
	};	
	
	/**
	 * 刷新完成，必须设置刷新回调为RefreshListener
	 * @param event
	 */
	protected abstract void refreshEventRunEnd(Event event);
}
