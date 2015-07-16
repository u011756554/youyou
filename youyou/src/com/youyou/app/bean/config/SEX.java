package com.youyou.app.bean.config;

public enum SEX {
	MAN("M"),FEMAL("F");
	private String attribute;
	
	SEX(String attribute) {
		this.attribute = attribute;
	}
	
	public String getAttribute() {
		return this.attribute;
	}
}
