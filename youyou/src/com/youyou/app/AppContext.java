package com.youyou.app;

import java.io.File;

public class AppContext {

	public static final String LOGIN_USER = "login_user";
	
	public static final String APP_TAG = "youyou";
	
	/******  获取验证码方式   *****/
	public static final String VALIDMODE_REGISTER = "Register";
	
	public static final String VALIDMODE_GETBACKPWD = "GetBackPwd";
	
	/******  评论或点赞类型    **********/
	public static final String TYPE_FOOD  = "Food";
	
	public static final String TYPE_ACTIVITY  = "Activity";
	
	public static final String TYPE_GAME  = "Game";
	
	/******  默认参数    **********/
	public static final String FILE_SAVE_ROOT_DIRECTORY = File.separator + "youyou" + File.separator;
	public static final String INIT_SHORT = "5";	//初次加载记录数
	public static final String INIT_COUNT = "10";	//初次加载记录数
	public static final String REFRESH_COUNT = "15";	//刷新一次记录数
	public static final String SEARCH_COUNT = "10";	//刷新一次记录数
	
	/**
	 * 图片,音频保存路径
	 */
	public static final String SAVE_PATH = "/youyou";	//压缩文件保存路径
	public static final String TAKEPICTURE_PATH = "/youyou/camera";	//通过相机拍照图片保存文件夹路径
	public static final String TAKEPICTURE_FILE = "temp.jgp";	//通过相机拍照图片保存文件名称
	public static final int PICTURE_SIZE = 30;	//暂且没用到，控制压缩图片大小，单位kb
	
}
