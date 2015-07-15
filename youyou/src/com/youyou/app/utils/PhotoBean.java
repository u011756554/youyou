/**
 * 
 */
package com.youyou.app.utils;

import java.io.Serializable;

/**
 * 功能：图片类,包含原图和缩略图地址<br>
 * 作者：骆巍<br>
 * 时间：2014-12-18<br>
 */
public class PhotoBean implements Serializable {

	public int id;
	public String img;
	public String thumbImg;
	public int thumbImgWidth=400;
	public int thumbImgHeight=400;
	public String filePath;

	public String photo2Json() {
		return JSONHelper.getInstance().objectToJson(this);
	}
}
