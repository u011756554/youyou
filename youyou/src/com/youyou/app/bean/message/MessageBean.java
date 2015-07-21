package com.youyou.app.bean.message;

import java.util.List;

import com.youyou.app.bean.Message;

public class MessageBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Message> data;

	public List<Message> getData() {
		return data;
	}

	public void setData(List<Message> data) {
		this.data = data;
	}
	
	

}
