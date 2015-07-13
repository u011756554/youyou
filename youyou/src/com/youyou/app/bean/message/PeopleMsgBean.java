package com.youyou.app.bean.message;

import java.util.List;

import com.youyou.app.bean.User;

public class PeopleMsgBean extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<User> data;

	public List<User> getData() {
		return data;
	}

	public void setData(List<User> data) {
		this.data = data;
	}
	
	
}
