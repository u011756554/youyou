package com.youyou.app.activity;

import java.io.File;
import java.io.InputStream;

import com.youyou.app.AppContext;
import com.youyou.app.R;
import com.youyou.app.net.Event;
import com.youyou.app.net.EventBus;
import com.youyou.app.net.EventCode;
import com.youyou.app.net.EventListener;
import com.youyou.app.utils.ImageUtil;
import com.youyou.app.utils.PictureUtils;
import com.youyou.app.widget.AddressDialog;
import com.youyou.app.widget.ChangeBirthDialog;
import com.youyou.app.widget.ChangeBirthDialog.OnBirthClick;
import com.youyou.app.widget.ChangeSexDialog;
import com.youyou.app.widget.ChangeSexDialog.OnSexClick;
import com.youyou.app.widget.ChangeSexDialog.SEX;
import com.youyou.app.widget.GetPicTureDialog;
import com.youyou.app.widget.GetPicTureDialog.DialogListener;
import com.youyou.app.widget.NickNameDialog;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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

	private static final int CODE_IMAGE_CAPTURE = 0x11;
	private static final int SELECT_PIC_KITKAT = 0x12;
	private static final int SELECT_PIC = 0x13;
	private static final int CROP_IMAGE = 0x14;
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
						Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						intent.putExtra(MediaStore.EXTRA_OUTPUT,
								Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+AppContext.FILE_SAVE_ROOT_DIRECTORY+System.currentTimeMillis()+".png")));
						startActivityForResult(intent, CODE_IMAGE_CAPTURE);	
					}
					if (mode.equals(GetPicTureDialog.MODE_PICTURE)) {
						Intent intent2 =new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						startActivityForResult(intent2, SELECT_PIC);  
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
	
	/**
	 * 裁剪图片方法实现
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 设置裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 320);
		intent.putExtra("outputY", 320);
		intent.putExtra("return-data", true);
		intent.putExtra("scale", true);// 去黑边
		intent.putExtra("scaleUpIfNeeded", true);// 去黑边
		startActivityForResult(intent, CROP_IMAGE);
	}
	
	public void startPhotoZoom(File file) {
//		Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null)
		Intent intent = new Intent("com.android.camera.action.CROP")
        .setType("image/*")
        .setData(Uri.fromFile(file))
        .putExtra("crop", "true")
        .putExtra("aspectX", 1)
        .putExtra("aspectY", 1)
        .putExtra("outputX", 320)
        .putExtra("outputY", 320)
        .putExtra("scale", true)//黑边
        .putExtra("scaleUpIfNeeded", true)//黑边
        .putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		startActivityForResult(intent, CROP_IMAGE);
	}
	
	/**
	 * 获取裁剪之后的图片数据
	 * 
	 * @param data
	 */
	private InputStream getImageToView(Intent data) {
		Bundle extras = data.getExtras();
		if (extras != null) {
			try {
				Bitmap photo = extras.getParcelable("data");
				ivHead.setImageBitmap(photo);
				return ImageUtil.bitmap2InputStream(photo, 90);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode) {
		case SELECT_PIC:
			if(data == null) {
				showToast("获取图片失败");
				return;
			}
			Uri selectedImage = data.getData();
			String[] filePathColumns = { MediaStore.Images.Media.DATA };
	        Cursor c = this.getContentResolver().query(selectedImage, filePathColumns, null, null, null);
	        c.moveToFirst();
	        int columnIndex = c.getColumnIndex(filePathColumns[0]);
	        String picturePath = c.getString(columnIndex);
	        startPhotoZoom(new File(picturePath));
			break;
		
		case CROP_IMAGE:
			if (data != null) {
				InputStream tmpPhoto = getImageToView(data);
				if (tmpPhoto != null) {
					File file = ImageUtil.inputStreamToFile(tmpPhoto);
					EventBus eventBus = new EventBus();
					eventBus.setListener(new EventListener() {
						
						@Override
						public void onEventRunEnd(Event event) {
							// TODO Auto-generated method stub
							
						}
					});
					eventBus.pushEvent(EventCode.HTTP_PORTRAIT, file);
				}
			}
			break;
			
		case CODE_IMAGE_CAPTURE:
			if(data == null) {
				try {
				     String caputrePicturePath = PictureUtils.instance().getUriPath();
				     System.out.println("path:"+caputrePicturePath);
				     String resultpath = PictureUtils.instance().compressFileToFile(caputrePicturePath);
				     startPhotoZoom(new File(resultpath));
				} catch (Exception e) {
					// TODO: handle exception
					showToast("获取头像失败");
					e.printStackTrace();
				}
				 
			} else {
				Uri uri = data.getData();
				if(uri == null){  
			    	 System.out.println("拍照测试，返回isnull");
			    	 Bundle bundle = data.getExtras();    
			    	 if (bundle != null) {                 
			    		 Bitmap  photo = (Bitmap) bundle.get("data");  
			    		 String path = PictureUtils.instance().compressBitmapToFile(photo);
			    		 startPhotoZoom(new File(path));
			    	 }   
			       }else{
			    	   System.out.println(uri.toString());
				       String[] cameraKeys = { MediaStore.Images.Media.DATA };
				       Cursor cur = this.getContentResolver().query(uri, cameraKeys, null, null, null);
				       cur.moveToFirst();
				       int index = cur.getColumnIndex(cameraKeys[0]);
				       String filePath = cur.getString(index);
				       System.out.println("path:"+filePath);
				       String path = PictureUtils.instance().compressFileToFile(filePath);
				       System.out.println("最终路径："+path);
				       cur.close();
				       startPhotoZoom(new File(path));
			       }   
			}
			break;
		}
	}
	
}
