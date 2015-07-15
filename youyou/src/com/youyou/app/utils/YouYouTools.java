package com.youyou.app.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class YouYouTools {

	private static YouYouTools instance;

	private YouYouTools() {

	}

	public static synchronized YouYouTools instance() {
		if (instance == null)
			instance = new YouYouTools();
		return instance;
	}
	
	public String getFormedTime(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date(time);
		return sdf.format(date);
	}
	
	public String getDate() {
		Calendar c = Calendar.getInstance();

		String year = String.valueOf(c.get(Calendar.YEAR));
		String month = String.valueOf(c.get(Calendar.MONTH) + 1);
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String mins = String.valueOf(c.get(Calendar.MINUTE));

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":"
				+ mins);

		return sbBuffer.toString();
	}
}
