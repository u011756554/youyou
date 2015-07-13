package com.youyou.app.bean.message;

import java.util.List;

import com.youyou.app.bean.Advert;

public class AdvertListBean extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Advert> data;
	public List<Advert> getData() {
		return data;
	}
	public void setData(List<Advert> data) {
		this.data = data;
	}
	
}
