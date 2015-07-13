package com.youyou.app.bean;

import java.io.Serializable;

public class CurrentActivity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String picPath;
	private String id;
	private String title;
	private String merchantPhone;
	private String merchant;
	private String description;
	private String endDate;
	private String published;
	private String picName;
	private String createDate;
	private String url;
	private String merchantAddress;
	private String startDate;
	private String lastUpdateDate;
	private String peoples;
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
	public String getMerchantPhone() {
		return merchantPhone;
	}
	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone;
	}
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPublished() {
		return published;
	}
	public void setPublished(String published) {
		this.published = published;
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
	public String getMerchantAddress() {
		return merchantAddress;
	}
	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getPeoples() {
		return peoples;
	}
	public void setPeoples(String peoples) {
		this.peoples = peoples;
	}
	
}
