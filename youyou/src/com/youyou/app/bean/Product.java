package com.youyou.app.bean;

import java.io.Serializable;

public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String urlString;
	private String nameString;
	private String countString;
	public String getUrlString() {
		return urlString;
	}
	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	public String getCountString() {
		return countString;
	}
	public void setCountString(String countString) {
		this.countString = countString;
	}
	

}
