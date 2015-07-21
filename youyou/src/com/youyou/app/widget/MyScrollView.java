package com.youyou.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 功能：自定义Scrollview
 * 作者：李达
 * 时间：2015-04-10
 */
public class MyScrollView extends ScrollView{

	private OnScrollChangedListener mScrollListener;
	
	public MyScrollView(Context context) {
		super(context);
	}

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setOnScrollChangedListener(OnScrollChangedListener l){
		this.mScrollListener = l;
	}
		
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if(mScrollListener != null)
			mScrollListener.onScrollChanged(l, t, oldl, oldt);
	}
	
	public interface OnScrollChangedListener{
		void onScrollChanged(int l, int t, int oldl, int oldt);
	}
}
