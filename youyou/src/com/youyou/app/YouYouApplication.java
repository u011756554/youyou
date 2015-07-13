package com.youyou.app;

import java.util.LinkedList;
import java.util.List;

import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;

public class YouYouApplication extends Application{

	public static YouYouApplication instance;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;
		initImageLoader();
	}

	private void initImageLoader() {
		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
		// .displayer(new FadeInBitmapDisplayer(300))
				.bitmapConfig(Bitmap.Config.RGB_565).imageScaleType(ImageScaleType.EXACTLY).build();

		ImageLoaderConfiguration imageconfig = new ImageLoaderConfiguration.Builder(getApplicationContext())
				// 缓存到内存的图片大小范围
				.threadPoolSize(1).memoryCacheExtraOptions(480, 800)
				.memoryCache(new UsingFreqLimitedMemoryCache(5* 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现   
				.memoryCacheSize(5 * 1024 * 1024)  
				// 100Mb
				.diskCacheSize(100 * 1024 * 1024).threadPriority(Thread.NORM_PRIORITY - 2)
				.defaultDisplayImageOptions(options)
				.denyCacheImageMultipleSizesInMemory().build();
		ImageLoader.getInstance().init(imageconfig);
	}
	
	private List<Activity> activityList = new LinkedList<Activity>();

	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	public void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
	}
	
}
