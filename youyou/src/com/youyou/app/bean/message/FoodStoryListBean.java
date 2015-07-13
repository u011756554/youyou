package com.youyou.app.bean.message;

import java.util.List;

import com.youyou.app.bean.FoodStory;

public class FoodStoryListBean extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<FoodStory> data;

	public List<FoodStory> getData() {
		return data;
	}

	public void setData(List<FoodStory> data) {
		this.data = data;
	}
	
	
}
