package com.youyou.app.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.youyou.app.R;
import com.youyou.app.bean.Game;
import com.youyou.app.utils.CommonUtil;
import com.youyou.app.utils.ImageLoaderUtil;

public class GameAdapter extends BaseAdapter {

	private List<Game> list;
	private Context context;
	private Activity activity;
	private UpdateListener listener;
	
	public GameAdapter(Context context) {
		this.context = context;
		this.activity = (Activity) context;
	}
	
	public void setData(List<Game> gameList) {
		this.list = gameList;
	}
	
	public synchronized void clear() {
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				list.clear();
				notifyDataSetChanged();			
			}
		});
	}
	
	public synchronized void replaceAll(final List<Game> newList) {
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				list.clear();
				list.addAll(newList);
				notifyDataSetChanged();			
			}
		});
	}
	
	public synchronized  void addAll(final List<Game> newList) {
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				list.addAll(newList);
				notifyDataSetChanged();				
			}
		});
	}
	
	private int getWidth() {
		return (CommonUtil.getScreenWidth(context)-CommonUtil.dip2px(context, 20)) / 3;
	}
	
	private int getHeight() {
		return (int)(getWidth() * 1.5);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_game, null);
			holder = new ViewHolder();
			
			LayoutParams params = new LayoutParams(getWidth(), getHeight());
			holder.iv1 = (ImageView) convertView.findViewById(R.id.iv_adapter_game_iv1);
			holder.iv2 = (ImageView) convertView.findViewById(R.id.iv_adapter_game_iv2);
			holder.iv3 = (ImageView) convertView.findViewById(R.id.iv_adapter_game_iv3);
			holder.iv1.setLayoutParams(params);
			holder.iv2.setLayoutParams(params);
			holder.iv3.setLayoutParams(params);
			
			holder.name1 = (TextView) convertView.findViewById(R.id.tv_adpater_game_name1);
			holder.name2 = (TextView) convertView.findViewById(R.id.tv_adpater_game_name2);
			holder.name3 = (TextView) convertView.findViewById(R.id.tv_adpater_game_name3);
			
			holder.rl1 = (RelativeLayout) convertView.findViewById(R.id.adapter_game_id1);
			holder.rl2 = (RelativeLayout) convertView.findViewById(R.id.adapter_game_id2);
			holder.rl3 = (RelativeLayout) convertView.findViewById(R.id.adapter_game_id3);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final Game game1 = list.get(position * 3);
		ImageLoaderUtil.diaPlayNormal(game1.getPicUrl(), holder.iv1, R.drawable.icon_default_bg, null);
		holder.name1.setText(game1.getName());
		holder.rl1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listener.click(game1);
			}
		});
		
		if ((position * 3 +1) < list.size()) {
			holder.iv2.setVisibility(View.VISIBLE);
			holder.name2.setVisibility(View.VISIBLE);
			holder.rl2.setVisibility(View.VISIBLE);
			final Game game2 = list.get(position * 3 +1);
			ImageLoaderUtil.diaPlayNormal(game2.getPicUrl(), holder.iv2, R.drawable.icon_default_bg, null);
			holder.name2.setText(game2.getName());	
			holder.rl2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					listener.click(game2);
				}
			});
			
		} else {
			holder.iv2.setVisibility(View.GONE);
			holder.name2.setVisibility(View.GONE);
			holder.rl2.setVisibility(View.GONE);
		}
		
		if ((position * 3 +2) < list.size()) {
			holder.iv3.setVisibility(View.VISIBLE);
			holder.name3.setVisibility(View.VISIBLE);
			holder.rl3.setVisibility(View.VISIBLE);
			final Game game3 = list.get(position * 3 +2);
			ImageLoaderUtil.diaPlayNormal(game3.getPicUrl(), holder.iv3, R.drawable.icon_default_bg, null);
			holder.name3.setText(game3.getName());	
			holder.rl3.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					listener.click(game3);
				}
			});			
		} else {
			holder.iv3.setVisibility(View.GONE);
			holder.name3.setVisibility(View.GONE);
			holder.rl3.setVisibility(View.GONE);
		}
		return convertView;
	}
	
	@Override
	public int getCount() {
		// 每列三项
		if (list.size() % 3 == 0) {
			return list.size() / 3;
		}
		return list.size() / 3 + 1;
	}


	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}


	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	private class ViewHolder {
		RelativeLayout rl1;
		ImageView iv1;
		TextView name1;
		
		RelativeLayout rl2;
		ImageView iv2;
		TextView name2;
		
		RelativeLayout rl3;
		ImageView iv3;
		TextView name3;
	}
	
	public void setListener(UpdateListener updateListener) {
		this.listener = updateListener;
	}
	
	public interface UpdateListener {
		void click(Game game);
	}

}
