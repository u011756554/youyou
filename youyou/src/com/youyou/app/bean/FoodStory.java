package com.youyou.app.bean;

import java.io.Serializable;

public class FoodStory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String picPath;
	private String id;
	private String title;
	private String description;
	private String picName;
	private String createDate;
	private String url;
	private String comment;
	private String commend;
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCommend() {
		return commend;
	}
	public void setCommend(String commend) {
		this.commend = commend;
	}
	
	
}
