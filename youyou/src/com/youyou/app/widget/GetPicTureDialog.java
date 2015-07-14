package com.youyou.app.widget;

import com.youyou.app.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class GetPicTureDialog extends Dialog implements android.view.View.OnClickListener{

	private Context context;
	private DialogListener listener;
	private RelativeLayout rlCameraLayout;
	private RelativeLayout rlPictureLayout;
	
	public static final String MODE_CAMERA = "1";
	public static final String MODE_PICTURE = "2";
	public GetPicTureDialog(Context context) {
		super(context,R.style.ShareDialog);
		this.context = context;
	}
	
	@Override
	public void onClick(View v) {
		final int id = v.getId();
		switch (id) {
		case R.id.ly_camera:
			dismiss();
			listener.update(MODE_CAMERA);
			break;
			
		case R.id.ly_getpicture:
			dismiss();
			listener.update(MODE_PICTURE);
			break;

		default:
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_getpicture);
		rlCameraLayout = (RelativeLayout) findViewById(R.id.ly_camera);
		rlPictureLayout = (RelativeLayout) findViewById(R.id.ly_getpicture);
		rlCameraLayout.setOnClickListener(this);
		rlPictureLayout.setOnClickListener(this);
	}

	public void setListener(DialogListener listener) {
		this.listener = listener;
	}
	
	public interface DialogListener {
		void update(String mode);
	}
	
}
