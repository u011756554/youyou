package com.youyou.app.bean.message;

import java.util.List;

import com.youyou.app.bean.Mark;

public class MarkMsgBean extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Mark> data;

	public List<Mark> getData() {
		return data;
	}

	public void setData(List<Mark> data) {
		this.data = data;
	}
	
	
}
