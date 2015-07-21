package com.youyou.app.bean.message;

import java.util.List;

import com.youyou.app.bean.Game;

public class GameMsgBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Game> data;

	public List<Game> getData() {
		return data;
	}

	public void setData(List<Game> data) {
		this.data = data;
	}
	

}
