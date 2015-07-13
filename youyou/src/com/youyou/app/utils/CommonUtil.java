package com.youyou.app.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;

import com.youyou.app.AppContext;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

/**
 * 常用工具
 * 
 * @author 骆巍
 * 
 */
public class CommonUtil {

	public static float dpToPx(Resources resources, float dp) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
	}

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 获取mac地址
	 * 
	 * @return
	 */
	public static String getLocalMacAddress(Context context) {
		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
	}

	/**
	 * 获得手机总内存
	 * 
	 * @return
	 */
	public static String getmemTotal() {
		long mTotal;
		// /proc/meminfo读出的内核信息进行解释
		String path = "/proc/meminfo";
		String content = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path), 8);
			String line;
			if ((line = br.readLine()) != null) {
				content = line;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// beginIndex
		int begin = content.indexOf(':');
		// endIndex
		int end = content.indexOf('k');
		// 截取字符串信息

		content = content.substring(begin + 1).trim();
		return content;
	}

	/**
	 * 获取屏幕宽度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	/**
	 * 获取屏幕高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}

	/**
	 * 计算控件宽度高度
	 * 
	 * @param view
	 */
	public static void measureView(View view) {
		int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
	}
	
	/**
	 * 得到系统状态栏高度
	 * 
	 * @param activity
	 * @return
	 */
	public static int getSystemStatusHeight(Activity activity) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = activity.getResources().getDimensionPixelSize(x);
			return sbar;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	/**
	 * 格式化电话号码：133***09090
	 * 
	 * @param phone
	 */
	public static String getPhoneFormat(String phone) {
		int length = phone.length();
		String newPhone = phone.substring(0, 3) + "****" + phone.substring(length - 4, length);
		return newPhone;
	}
	
	/**
	 * 获取字符字节长度
	 * @param s
	 * @return
	 */
	public static int getWordCount(String s) {  
        int length = 0;  
        for(int i = 0; i < s.length(); i++)  
        {  
            int ascii = Character.codePointAt(s, i);  
            if(ascii >= 0 && ascii <=255)  
                length++;  
            else  
                length += 2;  
                  
        }  
        return length;   
    }

}
