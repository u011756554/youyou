package com.youyou.app.bean.message;

import java.util.List;

import com.youyou.app.bean.CurrentActivity;

public class CurrentActivityListBean extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CurrentActivity> data;

	public List<CurrentActivity> getData() {
		return data;
	}

	public void setData(List<CurrentActivity> data) {
		this.data = data;
	}
	
	
}
