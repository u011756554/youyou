package com.youyou.app.activity;

import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.youyou.app.R;
import com.youyou.app.bean.Advert;
import com.youyou.app.bean.Product;
import com.youyou.app.bean.message.AdvertListBean;
import com.youyou.app.net.Event;
import com.youyou.app.net.EventBus;
import com.youyou.app.net.EventCode;
import com.youyou.app.net.EventListener;
import com.youyou.app.utils.ImageLoaderUtil;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends BaseActivity {

	private ImageView ivAdvert;
	private ImageView ivProduct;
	
	private ImageView ivTabMe;
	private RelativeLayout rlTabScore;
	private RelativeLayout rlTabNew;
	private RelativeLayout rlTabDelicious;
	private RelativeLayout rlTabGame;
	private RelativeLayout rlTabMessage;
	
	private List<Advert> advertList = new ArrayList<Advert>();
	private List<Product> productsList = new ArrayList<Product>();
	
	private int advertPageSize = 1;
	private int productsPageSize = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initAdvert();
        initProduct();
    }
    
    private void initView() {
    	ivAdvert = (ImageView) findViewById(R.id.iv_main_guanggao);
    	ivProduct = (ImageView) findViewById(R.id.iv_main_qianggou);
    	ivTabMe = (ImageView) findViewById(R.id.iv_tab_me);
    	
    	rlTabScore = (RelativeLayout) findViewById(R.id.rl_tab_score);
    	rlTabDelicious = (RelativeLayout) findViewById(R.id.rl_tab_delicious);
    	rlTabGame = (RelativeLayout) findViewById(R.id.rl_tab_game);
    	rlTabMessage = (RelativeLayout) findViewById(R.id.rl_tab_message);
    	rlTabNew = (RelativeLayout) findViewById(R.id.rl_tab_new);
    	
    	ivAdvert.setOnClickListener(this);
    	rlTabDelicious.setOnClickListener(this);
    	rlTabGame.setOnClickListener(this);
    	rlTabMessage.setOnClickListener(this);
    	rlTabNew.setOnClickListener(this);
    	rlTabScore.setOnClickListener(this);
    	
    	ivTabMe.setOnClickListener(this);
    }
    
    
    
    private void initAdvert() {
    	EventBus eventBus = new EventBus();
    	eventBus.setListener(new EventListener() {
			
			@Override
			public void onEventRunEnd(Event event) {
				if (event.isSuccess()) {
					AdvertListBean msg = (AdvertListBean) event.getReturnParamAtIndex(0);
					advertList = msg.getData();
					if (advertList.size() > 0) {
						ImageLoaderUtil.diaPlayNormal(advertList.get(0).getUrl(), ivAdvert, R.drawable.logo, null);						
					}
				} else {
					if (event.getFailException() != null) {
						showToast(event.getFailException().getMessage());
					} else {
						AdvertListBean msg = (AdvertListBean) event.getReturnParamAtIndex(0);
						if (msg != null && !TextUtils.isEmpty(msg.getMessage())) {
							showToast(msg.getMessage());
						} 
					}
				}
			}
		});
    	eventBus.pushEvent(EventCode.HTTP_GETADVERTS, "0",advertPageSize+"");
    }
    
    private void initProduct() {
    	
    }

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		final int id = view.getId();
		switch (id) {
		case R.id.iv_main_guanggao:
			if (advertList.size() > 0) {
				Intent advertIntent = new Intent(MainActivity.this,AdvertDetailActivity.class);
				advertIntent.putExtra(AdvertDetailActivity.KEY_ADVERT, advertList.get(0));
				startActivity(advertIntent);				
			}
			break;
		case R.id.rl_tab_delicious:
			Intent intent = new Intent(MainActivity.this,DeliciousActivity.class);
			startActivity(intent);
			break;
			
		case R.id.rl_tab_game:
			Intent gameIntent = new Intent(MainActivity.this,GameActivity.class);
			startActivity(gameIntent);
			break;
			
		case R.id.rl_tab_message:
			Intent unionIntent = new Intent(MainActivity.this,UnionActivity.class);
			startActivity(unionIntent);
			break;
			
		case R.id.rl_tab_new:
			Intent newIntent = new Intent(MainActivity.this,NewActivity.class);
			startActivity(newIntent);
			break;
			
		case R.id.rl_tab_score:
			Intent scoreIntent = new Intent(MainActivity.this,ProductsActivity.class);
			startActivity(scoreIntent);
			break;	
			
		case R.id.iv_tab_me:
			Intent meIntent = new Intent(MainActivity.this,MeActivity.class);
			startActivity(meIntent);
			break;

		default:
			break;
		}
	}
}
