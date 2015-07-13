package com.youyou.app.utils;

public class MatchUtils {
	
	private static final String PATTERN_PHONE = "1[3|4|5|6|7|8|9][0-9]{9}";
	
	private static final String PATTERN_EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	
	private static final String PATTERN_HAIYOU = "^\\d{5,}$";
	
	private static final String PATTERN_PWD = "^[\\w[~!@#%&*()_+-=:'<>,.?]]{6,20}$";
	
	private static final String PATTERN_NICKNAME = "[\\w[\u4E00-\u9FA5]-]{2,16}"; 
	
	public static boolean matchPhone(String phone) {
	 	return phone.matches(PATTERN_PHONE);
	}
	
	public static boolean matchEmail(String email) {
		return email.matches(PATTERN_EMAIL);
	}
	
	public static boolean matchHaiYou(String haiyou) {
		return haiyou.matches(PATTERN_HAIYOU);
	}
	
	public static boolean matchPwd(String pwd) {
		return pwd.matches(PATTERN_PWD);
	}
	
	public static boolean matchNickName(String nickName) {
		return nickName.matches(PATTERN_NICKNAME);
	}
}
