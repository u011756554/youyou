package com.youyou.app.activity;

import com.youyou.app.R;
import com.youyou.app.widget.AddressDialog;
import com.youyou.app.widget.ChangeBirthDialog;
import com.youyou.app.widget.ChangeBirthDialog.OnBirthClick;
import com.youyou.app.widget.ChangeSexDialog;
import com.youyou.app.widget.ChangeSexDialog.OnSexClick;
import com.youyou.app.widget.ChangeSexDialog.SEX;
import com.youyou.app.widget.GetPicTureDialog;
import com.youyou.app.widget.GetPicTureDialog.DialogListener;
import com.youyou.app.widget.NickNameDialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyInfoActivity extends BaseActivity {

	private ImageView ivHead;
	private RelativeLayout rlNickName;
	private RelativeLayout rlGender;
	private RelativeLayout rlAddress;
	private RelativeLayout rlBirthday;
	
	private TextView tvNickName;
	private TextView tvGender;
	private TextView tvAddress;
	private TextView tvBirthday;
	
	private GetPicTureDialog getPicTureDialog;
	private AddressDialog addressDialog;
	private NickNameDialog nickNameDialog;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_myinfo);
		initView();
	}

	private void initView() {
		ivHead = (ImageView) findViewById(R.id.iv_myinfo_head);
		rlNickName = (RelativeLayout) findViewById(R.id.ll_nickname);
		rlGender = (RelativeLayout) findViewById(R.id.ll_gender);
		rlAddress = (RelativeLayout) findViewById(R.id.ll_address);
		rlBirthday = (RelativeLayout) findViewById(R.id.ll_birthday);
		
		tvNickName = (TextView) findViewById(R.id.tv_myinfo_nickname);
		tvGender = (TextView) findViewById(R.id.tv_myinfo_gender);
		tvAddress = (TextView) findViewById(R.id.tv_myinfo_address);
		tvBirthday = (TextView) findViewById(R.id.tv_myinfo_birthday);
		
		getPicTureDialog = new GetPicTureDialog(this);
		addressDialog = new AddressDialog(this);
		nickNameDialog = new NickNameDialog(this);
		
		ivHead.setOnClickListener(this);
		rlNickName.setOnClickListener(this);
		rlGender.setOnClickListener(this);
		rlAddress.setOnClickListener(this);
		rlBirthday.setOnClickListener(this);
		rlAddress.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		final int id = arg0.getId();
		switch (id) {
		case R.id.iv_myinfo_head:
			getPicTureDialog.setListener(new DialogListener() {
				
				@Override
				public void update(String mode) {
					if (TextUtils.isEmpty(mode)) {
						return;
					}
					if (mode.equals(GetPicTureDialog.MODE_CAMERA)) {
						
					}
					if (mode.equals(GetPicTureDialog.MODE_PICTURE)) {
						
					}
				}
			});
			getPicTureDialog.show();
			break;
			
		case R.id.ll_nickname:
			nickNameDialog.setListener(new com.youyou.app.widget.NickNameDialog.DialogListener() {

				@Override
				public void update(String nickname) {
					if (!TextUtils.isEmpty(nickname)) {
						tvNickName.setText(nickname);
					}
				}
				
			});
			nickNameDialog.show();
			break;
			
		case R.id.ll_gender:
			final ChangeSexDialog mChangeSexDialog = new ChangeSexDialog(this);
			mChangeSexDialog.show();
			mChangeSexDialog.setOnSexClickListener(new OnSexClick() {

				@Override
				public void onClick(SEX sex) {
					// TODO Auto-generated method stub
					if (sex == SEX.MAN) {
						tvGender.setText("男");
					} else {
						tvGender.setText("女");
					}
				}
			});
			break;
			
		case R.id.ll_address:
			addressDialog.setListener(new AddressDialog.DialogListener() {

				@Override
				public void update(String province, String city) {
					if (!TextUtils.isEmpty(province) && !TextUtils.isEmpty(city)) {
						tvAddress.setText(province+"-"+city);
					}
				}

			});
			addressDialog.show();
			break;
			
		case R.id.ll_birthday:

			String[] birth = null;
			birth = "1989-06-12".split("-");
			ChangeBirthDialog mBirthDialog = new ChangeBirthDialog(this);
			if (birth != null && birth.length == 3) {
				mBirthDialog.setDate(Integer.parseInt(birth[0]), Integer.parseInt(birth[1]), Integer.parseInt(birth[2]));
			}

			mBirthDialog.show();

			mBirthDialog.setOnBirthClickListener(new OnBirthClick() {

				@Override
				public void onClick(String year, String month, String day) {
					// TODO Auto-generated method stub
					String birthday = year + "-" + month + "-" + day;
					tvBirthday.setText(birthday);
				}
			});
		
			break;

		default:
			break;
		}
	}
	
	
}
