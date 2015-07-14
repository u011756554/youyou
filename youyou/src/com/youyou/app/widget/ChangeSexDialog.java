package com.youyou.app.widget;

import com.youyou.app.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * 更改封面对话框
 * 
 * @author ywl
 *
 */
public class ChangeSexDialog extends Dialog implements android.view.View.OnClickListener {

	private View lyChangeSex;
	private View lyChangeSexChild;
	private OnSexClick mySexClick;

	private RadioButton rbBoy;
	private RadioButton rbGirl;
	private RadioGroup rgSex;

	private Context context;
	private SEX sex;
	private int count = 1;

	public enum SEX {
		MAN, WOMAN
	}

	public ChangeSexDialog(Context context) {
		super(context, R.style.ShareDialog);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_change_sex);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}

		rgSex = (RadioGroup) findViewById(R.id.rg_change_sex);
		rbBoy = (RadioButton) findViewById(R.id.rb_change_boy);
		rbGirl = (RadioButton) findViewById(R.id.rb_change_girl);
		lyChangeSex = findViewById(R.id.ly_change_sex);
		lyChangeSexChild = findViewById(R.id.ly_change_sex_child);

		lyChangeSex.setOnClickListener(this);
		lyChangeSexChild.setOnClickListener(this);

		rgSex.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				rbBoy.invalidate();
				rbGirl.invalidate();
				SEX sex = SEX.MAN;
				if (checkedId == R.id.rb_change_boy) {
					rbBoy.setChecked(true);
					sex = SEX.MAN;
					count++;

				} else if (checkedId == R.id.rb_change_girl) {
					rbGirl.setChecked(true);
					sex = SEX.WOMAN;
					count++;
				}
				if (mySexClick != null) {
					mySexClick.onClick(sex);
				}
				if (count > 2) {

					new Handler().postDelayed(new Runnable() {

						@Override
						public void run() {
							dismiss();
						}
					}, 300);
				}
			}
		});

	}

	public void setOnSexClickListener(OnSexClick myClick) {
		this.mySexClick = myClick;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == lyChangeSexChild) {
			return;
		} else {
			dismiss();
		}
		dismiss();
	}

	public interface OnSexClick {
		public void onClick(SEX sex);
	}

	public void initSex(SEX sex) {
		rbBoy.invalidate();
		rbGirl.invalidate();
		if (sex == SEX.MAN) {
			rbBoy.setChecked(true);
		} else {
			rbGirl.setChecked(true);
		}
	}

}