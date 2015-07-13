package com.youyou.app.activity;

import com.youyou.app.R;
import com.youyou.app.YouYouApplication;
import com.youyou.app.net.Event;
import com.youyou.app.net.EventListener;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends FragmentActivity implements OnClickListener {

	protected Context context = null;
	protected ProgressDialog pg = null;
	protected YouYouApplication mApplication;
	protected NotificationManager notificationManager;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		context = this;
		mApplication = (YouYouApplication) getApplication();
		pg = new ProgressDialog(context);
		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		mApplication.addActivity(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public ProgressDialog getProgressDialog() {
		return pg;
	}
	
	protected void setProgressTitle(String text) {
		pg.setTitle(text);
	}
	
	/*********************     标题栏 START              **********************/
	protected void setTitle(String title) {
		TextView tvTitle = (TextView) findViewById(R.id.tv_titile);
		tvTitle.setText(title);
	}
	
	protected void addBack(boolean haveBack) {
		RelativeLayout rlBack = (RelativeLayout) findViewById(R.id.rl_back);
		if (haveBack) {
			rlBack.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					finish();
				}
			});	
		} else {
			rlBack.setVisibility(View.GONE);
		}
	}
	
	protected void addRight(String text) {
		TextView rightText = (TextView) findViewById(R.id.tv_right);
		rightText.setText(text);
		rightText.setVisibility(View.VISIBLE);
		rightText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				clickRight();
			}
		});
	}
	
	protected void addRight(int id) {
		ImageView rightImg = (ImageView) findViewById(R.id.iv_right);
		rightImg.setBackgroundResource(id);
		rightImg.setVisibility(View.VISIBLE);
		rightImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				clickRight();
			}
		});
	}	
	
	protected void clickRight() {
		
	}	
	/*********************     标题栏 END    **********************/
	
	protected void isExit() {
		new AlertDialog.Builder(context).setTitle("ȷ���˳���")
				.setNeutralButton("ȷ��", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						mApplication.exit();
					}
				})
				.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				}).show();
	}
	
	public void checkMemoryCard() {
		if (!Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			new AlertDialog.Builder(context)
					.setTitle("��ʾ")
					.setMessage("�����ڴ濨")
					.setPositiveButton("����",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
									Intent intent = new Intent(
											Settings.ACTION_SETTINGS);
									context.startActivity(intent);
								}
							})
					.setNegativeButton("�ر�",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
									mApplication.exit();
								}
							}).create().show();
		}
	}	
	
	public boolean hasInternetConnected() {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(context.CONNECTIVITY_SERVICE);
		if (manager != null) {
			NetworkInfo network = manager.getActiveNetworkInfo();
			if (network != null && network.isConnectedOrConnecting()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean validateInternet() {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(context.CONNECTIVITY_SERVICE);
		if (manager == null) {
			openWirelessSet();
			return false;
		} else {
			NetworkInfo[] info = manager.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		openWirelessSet();
		return false;
	}
	
	public void openWirelessSet() {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
		dialogBuilder
				.setTitle("��ʾ")
				.setMessage("�޿����������ӣ������������ã�")
				.setPositiveButton("����",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
								Intent intent = new Intent(
										Settings.ACTION_WIRELESS_SETTINGS);
								context.startActivity(intent);
							}
						})
				.setNegativeButton("�ر�",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int whichButton) {
								dialog.cancel();
							}
						});
		dialogBuilder.show();
	}

	public void showToast(String text, int longint) {
		Toast.makeText(context, text, longint).show();
	}

	public void showToast(String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}	
	
}
