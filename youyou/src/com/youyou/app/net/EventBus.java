package com.youyou.app.net;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.youyou.app.AppContext;
import com.youyou.app.bean.Advert;
import com.youyou.app.bean.message.AdvertListBean;
import com.youyou.app.bean.message.BaseBean;
import com.youyou.app.bean.message.CurrentActivityListBean;
import com.youyou.app.bean.message.FoodStoryListBean;
import com.youyou.app.bean.message.LoginMsgBean;
import com.youyou.app.bean.message.MarkMsgBean;
import com.youyou.app.bean.message.PeopleMsgBean;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

public class EventBus {

	private Gson g = new Gson();
	private EventListener mListener;
	
	public void setListener(EventListener listener) {
		this.mListener = listener;
	}
	
	public void pushEvent(int eventCode,Object...params) {
		final Event event = new Event(eventCode, params);
		new AsyncTask<Object, Object, Event>() {
			
			@Override
			protected Event doInBackground(Object... arg0) {
				try {
					if(event.getEventCode() == EventCode.HTTP_LOGIN) {
						String username = (String) event.getParamAtIndex(0);
						String password = (String) event.getParamAtIndex(1);
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("username", username);
						map.put("password", password);
						
						String result = HttpUtils.doPost(URLUtils.LOGIN,map,true);
						if (TextUtils.isEmpty(result)) {
							event.setSuccess(false);
							event.setFailException(new Exception("网络连失败，请检查网络"));
							return event;
						}
						Log.i(AppContext.APP_TAG, result);
						System.out.println("login result:"+result);

						LoginMsgBean msg = g.fromJson(result, LoginMsgBean.class);
						event.addReturnParam(msg);
						if (msg.isSuccess()) {
							event.setSuccess(true);
						} else {
							event.setSuccess(false);
						}

					}
					
					if(event.getEventCode() == EventCode.HTTP_GETVALIDATECODE) {
						String mobile = (String) event.getParamAtIndex(0);
						String type = (String) event.getParamAtIndex(1);
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("mobile", mobile);
						map.put("type", type);
						
						String result = HttpUtils.doPost(URLUtils.GETVALIDATECODE,map,true);
						if (TextUtils.isEmpty(result)) {
							event.setSuccess(false);
							event.setFailException(new Exception("网络连失败，请检查网络"));
							return event;
						}
						Log.i(AppContext.APP_TAG, result);
						System.out.println("login result:"+result);
						BaseBean msg = g.fromJson(result, BaseBean.class);
						event.addReturnParam(msg);
						if (msg.isSuccess()) {
							event.setSuccess(true);
						} else {
							event.setSuccess(false);
						}
					}
					
					if(event.getEventCode() == EventCode.HTTP_REGISTER) {
						String mobile = (String) event.getParamAtIndex(0);
						String password = (String) event.getParamAtIndex(1);
						String nickName = (String) event.getParamAtIndex(2);
						String validateCode = (String) event.getParamAtIndex(3);
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("mobile", mobile);
						map.put("password", password);
						map.put("nickName", nickName);
						map.put("validateCode", validateCode);
						
						String result = HttpUtils.doPost(URLUtils.REGISTER,map,true);
						if (TextUtils.isEmpty(result)) {
							event.setSuccess(false);
							event.setFailException(new Exception("网络连失败，请检查网络"));
							return event;
						}
						Log.i(AppContext.APP_TAG, result);
						System.out.println("login result:"+result);
						BaseBean msg = g.fromJson(result, BaseBean.class);
						event.addReturnParam(msg);
						if (msg.isSuccess()) {
							event.setSuccess(true);
						} else {
							event.setSuccess(false);
						}
						
					}	
					
					if(event.getEventCode() == EventCode.HTTP_GETBACKPWD) {
						String mobile = (String) event.getParamAtIndex(0);
						String validateCode = (String) event.getParamAtIndex(1);
						String password = (String) event.getParamAtIndex(2);
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("mobile", mobile);
						map.put("validateCode", validateCode);
						map.put("password", password);
						
						String result = HttpUtils.doPost(URLUtils.GETBACKPWD,map,true);
						if (TextUtils.isEmpty(result)) {
							event.setSuccess(false);
							event.setFailException(new Exception("网络连失败，请检查网络"));
							return event;
						}
						Log.i(AppContext.APP_TAG, result);
						System.out.println("login result:"+result);
						BaseBean msgBaseBean = g.fromJson(result, BaseBean.class);
						event.addReturnParam(msgBaseBean);
						if (msgBaseBean.isSuccess()) {
							event.setSuccess(true);
						} else {
							event.setSuccess(false);
						}
					}		
					
					if(event.getEventCode() == EventCode.HTTP_COMMEND) {
						String type = (String) event.getParamAtIndex(0);
						String itemId = (String) event.getParamAtIndex(1);
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("type", type);
						map.put("itemId", itemId);
						
						String result = HttpUtils.doPost(URLUtils.COMMEND,map,true);
						if (TextUtils.isEmpty(result)) {
							event.setSuccess(false);
							event.setFailException(new Exception("网络连失败，请检查网络"));
							return event;
						}
						Log.i(AppContext.APP_TAG, result);
						System.out.println("login result:"+result);
						
					}	
					
					if(event.getEventCode() == EventCode.HTTP_GETCOMMENTS) {
						String type = (String) event.getParamAtIndex(0);
						String itemId = (String) event.getParamAtIndex(1);
						String start = (String) event.getParamAtIndex(2);
						String limit = (String) event.getParamAtIndex(3);
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("type", type);
						map.put("itemId", itemId);
						map.put("start", start);
						map.put("limit", limit);
						
						String result = HttpUtils.doPost(URLUtils.GETCOMMENTS,map,true);
						if (TextUtils.isEmpty(result)) {
							event.setSuccess(false);
							event.setFailException(new Exception("网络连失败，请检查网络"));
							return event;
						}
						Log.i(AppContext.APP_TAG, result);
						System.out.println("login result:"+result);
						MarkMsgBean msg = g.fromJson(result, MarkMsgBean.class);
						event.addReturnParam(msg);
						if (msg.isSuccess()) {
							event.setSuccess(true);
						} else {
							event.setSuccess(false);
						}
					}		
					
					if(event.getEventCode() == EventCode.HTTP_CHECKCOMMEND) {
						String type = (String) event.getParamAtIndex(0);
						String itemId = (String) event.getParamAtIndex(1);
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("type", type);
						map.put("itemId", itemId);
						
						String result = HttpUtils.doPost(URLUtils.CHECKCOMMEND,map,true);
						if (TextUtils.isEmpty(result)) {
							event.setSuccess(false);
							event.setFailException(new Exception("网络连失败，请检查网络"));
							return event;
						}
						Log.i(AppContext.APP_TAG, result);
						System.out.println("login result:"+result);
						
					}	
					
					if(event.getEventCode() == EventCode.HTTP_CANCELCOMMEND) {
						String id = (String) event.getParamAtIndex(0);
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("id", id);
						
						String result = HttpUtils.doPost(URLUtils.CANCELCOMMEND,map,true);
						if (TextUtils.isEmpty(result)) {
							event.setSuccess(false);
							event.setFailException(new Exception("网络连失败，请检查网络"));
							return event;
						}
						Log.i(AppContext.APP_TAG, result);
						System.out.println("login result:"+result);
						
					}
					
					if(event.getEventCode() == EventCode.HTTP_COMMENT) {
						String type = (String) event.getParamAtIndex(0);
						String itemId = (String) event.getParamAtIndex(1);
						String rank = (String) event.getParamAtIndex(2);
						String content = (String) event.getParamAtIndex(3);
						
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("type", type);
						map.put("itemId", itemId);
						map.put("rank", rank);
						map.put("content", content);
						
						String result = HttpUtils.doPost(URLUtils.COMMENT,map,true);
						if (TextUtils.isEmpty(result)) {
							event.setSuccess(false);
							event.setFailException(new Exception("网络连失败，请检查网络"));
							return event;
						}
						Log.i(AppContext.APP_TAG, result);
						System.out.println("login result:"+result);
						BaseBean msg = g.fromJson(result, BaseBean.class);
						event.addReturnParam(msg);
						if (msg.isSuccess()) {
							event.setSuccess(true);
						} else {
							event.setSuccess(false);
						}
						
					}	
					
					if(event.getEventCode() == EventCode.HTTP_COMMENT) {
						String type = (String) event.getParamAtIndex(0);
						String itemId = (String) event.getParamAtIndex(1);
						String rank = (String) event.getParamAtIndex(2);
						String content = (String) event.getParamAtIndex(3);
						
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("type", type);
						map.put("itemId", itemId);
						map.put("rank", rank);
						map.put("content", content);
						
						String result = HttpUtils.doPost(URLUtils.COMMENT,map,true);
						if (TextUtils.isEmpty(result)) {
							event.setSuccess(false);
							event.setFailException(new Exception("网络连失败，请检查网络"));
							return event;
						}
						Log.i(AppContext.APP_TAG, result);
						System.out.println("login result:"+result);
						
					}	
					
					if(event.getEventCode() == EventCode.HTTP_CHECKCOMMENT) {
						String type = (String) event.getParamAtIndex(0);
						String itemId = (String) event.getParamAtIndex(1);
						
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("type", type);
						map.put("itemId", itemId);
						
						String result = HttpUtils.doPost(URLUtils.CHECKCOMMENT,map,true);
						if (TextUtils.isEmpty(result)) {
							event.setSuccess(false);
							event.setFailException(new Exception("网络连失败，请检查网络"));
							return event;
						}
						Log.i(AppContext.APP_TAG, result);
						System.out.println("login result:"+result);
						
					}	
					
					if(event.getEventCode() == EventCode.HTTP_CANCELCOMMENT) {
						String id = (String) event.getParamAtIndex(0);
						
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("id", id);
						
						String result = HttpUtils.doPost(URLUtils.CANCELCOMMENT,map,true);
						if (TextUtils.isEmpty(result)) {
							event.setSuccess(false);
							event.setFailException(new Exception("网络连失败，请检查网络"));
							return event;
						}
						Log.i(AppContext.APP_TAG, result);
						System.out.println("login result:"+result);
						
					}	
					
					if(event.getEventCode() == EventCode.HTTP_GETADVERTS) {
						String start = (String) event.getParamAtIndex(0);
						String limit = (String) event.getParamAtIndex(1);
						
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("start", start);
						map.put("limit", limit);
						
						String result = HttpUtils.doPost(URLUtils.GETADVERTS,map,true);
						if (TextUtils.isEmpty(result)) {
							event.setSuccess(false);
							event.setFailException(new Exception("网络连失败，请检查网络"));
							return event;
						}
						Log.i(AppContext.APP_TAG, result);
						System.out.println("login result:"+result);
						AdvertListBean msg = g.fromJson(result, AdvertListBean.class);
						event.addReturnParam(msg);
						if (msg.isSuccess()) {
							event.setSuccess(true);
						} else {
							event.setSuccess(false);
						}
					}	
					
					if(event.getEventCode() == EventCode.HTTP_GETFOODS) {
						String start = (String) event.getParamAtIndex(0);
						String limit = (String) event.getParamAtIndex(1);
						
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("start", start);
						map.put("limit", limit);
						
						String result = HttpUtils.doPost(URLUtils.GETFOODS,map,true);
						if (TextUtils.isEmpty(result)) {
							event.setSuccess(false);
							event.setFailException(new Exception("网络连失败，请检查网络"));
							return event;
						}
						Log.i(AppContext.APP_TAG, result);
						System.out.println("login result:"+result);
						FoodStoryListBean msg = g.fromJson(result, FoodStoryListBean.class);
						event.addReturnParam(msg.getData());
						if (msg.isSuccess()) {
							event.setSuccess(true);
						} else {
							event.setSuccess(false);
						}
						
					}	
					
					if(event.getEventCode() == EventCode.HTTP_GETACTIVITIES) {
						String start = (String) event.getParamAtIndex(0);
						String limit = (String) event.getParamAtIndex(1);
						
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("start", start);
						map.put("limit", limit);
						
						String result = HttpUtils.doPost(URLUtils.GETACTIVITIES,map,true);
						if (TextUtils.isEmpty(result)) {
							event.setSuccess(false);
							event.setFailException(new Exception("网络连失败，请检查网络"));
							return event;
						}
						Log.i(AppContext.APP_TAG, result);
						System.out.println("login result:"+result);
						
						CurrentActivityListBean msg = g.fromJson(result, CurrentActivityListBean.class);
						event.addReturnParam(msg);
						if (msg.isSuccess()) {
							event.setSuccess(true);
						} else {
							event.setSuccess(false);
						}
						
					}	
					
					if(event.getEventCode() == EventCode.HTTP_GETACTIVITYUSERS) {
						String id = (String) event.getParamAtIndex(0);
						String start = (String) event.getParamAtIndex(1);
						String limit = (String) event.getParamAtIndex(2);
						
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("id", id);
						map.put("start", start);
						map.put("limit", limit);
						
						String result = HttpUtils.doPost(URLUtils.GETACTIVITYUSERS,map,true);
						if (TextUtils.isEmpty(result)) {
							event.setSuccess(false);
							event.setFailException(new Exception("网络连失败，请检查网络"));
							return event;
						}
						Log.i(AppContext.APP_TAG, result);
						System.out.println("login result:"+result);
						PeopleMsgBean msg = g.fromJson(result, PeopleMsgBean.class);
						event.addReturnParam(msg.getData());
						if (msg.isSuccess()) {
							event.setSuccess(true);
						} else {
							event.setSuccess(false);
						}
					}	
					
					if(event.getEventCode() == EventCode.HTTP_JOINACTIVITY) {
						String id = (String) event.getParamAtIndex(0);
						
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("id", id);
						
						String result = HttpUtils.doPost(URLUtils.JOINACTIVITY,map,true);
						if (TextUtils.isEmpty(result)) {
							event.setSuccess(false);
							event.setFailException(new Exception("网络连失败，请检查网络"));
							return event;
						}
						Log.i(AppContext.APP_TAG, result);
						System.out.println("login result:"+result);
						BaseBean msg = g.fromJson(result, BaseBean.class);
						event.addReturnParam(msg);
						if (msg.isSuccess()) {
							event.setSuccess(true);
						} else {
							event.setSuccess(false);
						}
					}	
					
					if(event.getEventCode() == EventCode.HTTP_CHECKJOINACTIVITY) {
						String id = (String) event.getParamAtIndex(0);
						
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("id", id);
						
						String result = HttpUtils.doPost(URLUtils.CHECKJOINACTIVITY,map,true);
						if (TextUtils.isEmpty(result)) {
							event.setSuccess(false);
							event.setFailException(new Exception("网络连失败，请检查网络"));
							return event;
						}
						Log.i(AppContext.APP_TAG, result);
						System.out.println("login result:"+result);
						
					}	
					
					if(event.getEventCode() == EventCode.HTTP_LOGOUTACTIVITY) {
						String id = (String) event.getParamAtIndex(0);
						
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("id", id);
						
						String result = HttpUtils.doPost(URLUtils.LOGOUTACTIVITY,map,true);
						if (TextUtils.isEmpty(result)) {
							event.setSuccess(false);
							event.setFailException(new Exception("网络连失败，请检查网络"));
							return event;
						}
						Log.i(AppContext.APP_TAG, result);
						System.out.println("login result:"+result);
						
					}
					
					if(event.getEventCode() == EventCode.HTTP_GETGAMES) {
						String start = (String) event.getParamAtIndex(0);
						String limit = (String) event.getParamAtIndex(1);
						
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("start", start);
						map.put("limit", limit);
						
						String result = HttpUtils.doPost(URLUtils.GETGAMES,map,true);
						if (TextUtils.isEmpty(result)) {
							event.setSuccess(false);
							event.setFailException(new Exception("网络连失败，请检查网络"));
							return event;
						}
						Log.i(AppContext.APP_TAG, result);
						System.out.println("login result:"+result);
						
					}				
				} catch (Exception e) {
					event.setFailException(e);
					event.setSuccess(false);
				}
				return event;
			}

			@Override
			protected void onPostExecute(Event result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				mListener.onEventRunEnd(result);
			}
			
			
		}.execute();
	} 

}
