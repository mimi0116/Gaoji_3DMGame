package com.jucky.gaoji_3dmgame;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {
	private Context context;
	private List<ChapterBean> data;
	
	public ItemAdapter(Context context, List<ChapterBean> data) {
		this.context = context;
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		if(arg1 == null){
			arg1 = LayoutInflater.from(context).inflate(R.layout.item, arg2, false);
			arg1.setTag(new ViewHolder(arg1));
		}
		ViewHolder holder = (ViewHolder) arg1.getTag();
		ChapterBean bean = data.get(arg0);
		holder.text.setText(bean.getTitle());
		BitmapHelper.getUtils().display(holder.image, UrlUtils.home + bean.getImage(), 
				new BitmapLoadCallBack<ImageView>() {

					@Override
					public void onLoadCompleted(ImageView arg0, String arg1,
							Bitmap arg2, BitmapDisplayConfig arg3,
							BitmapLoadFrom arg4) {
						int width = arg2.getWidth();
						Bitmap bitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);

						Canvas canvas = new Canvas(bitmap);
						Paint paint = new Paint();

						paint.setAntiAlias(true);

						paint.setShader(new BitmapShader(arg2, TileMode.CLAMP, TileMode.CLAMP));

						canvas.drawCircle(width / 2, width / 2, width / 2, paint);
						arg0.setImageBitmap(bitmap);
						
					}


					@Override
					public void onLoadFailed(ImageView arg0, String arg1,
							Drawable arg2) {
						arg0.setImageDrawable(arg2);
					}
		});
		return arg1;
	}
	public void addAll(List<ChapterBean> beans) {
		data.addAll(beans);
		notifyDataSetChanged();
	}
	public static class ViewHolder{
		private View itemView;
		@ViewInject(R.id.item_image)
		private ImageView image;
		@ViewInject(R.id.item_text)
		private TextView text;
		public ViewHolder(View itemView) {
			this.itemView = itemView;
			ViewUtils.inject(this, itemView);
		}
		
	}

}
