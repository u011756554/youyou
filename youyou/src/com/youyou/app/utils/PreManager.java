package com.youyou.app.utils;

import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * 数据存储封装保存数据到手机的各种方法，如文件存储，sharePreference,网络存储
 * 
 * @author
 * 
 */

public class PreManager {

	private static Gson gson = new Gson();
	private static PreManager preManager;

	private PreManager() {

	}

	public static synchronized PreManager instance() {
		if (preManager == null)
			preManager = new PreManager();
		return preManager;
	}

	/**
	 * 保存是否第一次使用app
	 * 
	 * @param context
	 * @param flag
	 */
	public void saveIsFirstUse(Context context, boolean flag) {
		PreferenceManager.getDefaultSharedPreferences(context).edit()
				.putBoolean("app_first", flag).commit();
	}

	/**
	 * 获得是否是第�?次使�?
	 * 
	 * @param context
	 * @return
	 */
	public boolean getIsFirstUse(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean("app_first", true);
	}
	
	/**
	 * 取出�?个对�?
	 * 
	 * @param context
	 * @param key
	 * @param clazz
	 * @return
	 */
	public static <T> T get(Context context, String key, Class<T> clazz) {
		final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
		T t = null;
		String value = settings.getString(key, null);
		if (value != null) {
			t = gson.fromJson(value, clazz);
		}
		return t;
	}

	/**
	 * 
	 * 放入�?个对�?
	 * @param context
	 * @param key
	 * @param t
	 */
	public static <T> void put(Context context, String key, T t) {
		final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
		String value = gson.toJson(t);
		settings.edit().putString(key, value).commit();
	}
	
	public static void saveCookie(Context context,String cookie) {
		PreferenceManager.getDefaultSharedPreferences(context).edit()
		.putString("cookie", cookie).commit();
	}
	
	public static String getCookie(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getString("cookie", "");
	}

}