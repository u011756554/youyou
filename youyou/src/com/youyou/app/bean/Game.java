package com.youyou.app.bean;

import java.io.Serializable;

public class Game implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String url;
	private String name;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
