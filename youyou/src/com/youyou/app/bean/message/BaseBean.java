package com.youyou.app.bean.message;

import java.io.Serializable;

import android.text.TextUtils;

public class BaseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private String success;
	
	public boolean isSuccess() {
		if (!TextUtils.isEmpty(success) && "true".equals(success)) {
			return true;
		}
		return false;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public String getSuccess() {
		return success;
	}



	public void setSuccess(String success) {
		this.success = success;
	}
}
