package com.jucky.gaoji_3dmgame;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class ChatperFragment extends ListFragment implements OnRefreshListener {
	@ViewInject(R.id.swipe)
	private SwipeRefreshLayout swipe;
	private ItemAdapter adapter;
	private HttpUtils http;
	private int typeId;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragmwent_layout, container, false);
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ViewUtils.inject(this, view);
		typeId = getArguments().getInt("typeId");
		adapter = new ItemAdapter(getActivity(), new ArrayList<ChapterBean>());
		http = new HttpUtils();
		setListAdapter(adapter);
		swipe.setOnRefreshListener(this);
	}
	@Override
	public void onRefresh() {
		http.send(HttpMethod.GET, 
				UrlUtils.getChapter(30, String.valueOf(typeId), 1), 
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						Toast.makeText(getActivity(), arg1, Toast.LENGTH_LONG).show();
						swipe.setRefreshing(false);
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						try {
							JSONObject object = new JSONObject(arg0.result);
							JSONObject data = object.getJSONObject("data");
							List<ChapterBean> beans = new ArrayList<ChapterBean>();
							int index = 0;
							while (!data.isNull(String.valueOf(index))) {
								JSONObject chapter = data.getJSONObject(String.valueOf(index++));
								ChapterBean bean = new ChapterBean();
								bean.setId(chapter.getInt("id"));
								bean.setTypeId(chapter.getInt("typeid"));
								bean.setSortrank(chapter.getInt("sortrank"));
								bean.setTitle(chapter.getString("shorttitle"));
								bean.setImage(chapter.getString("litpic"));
								bean.setArcurl(chapter.getString("arcurl"));
								beans.add(bean);
							}
							adapter.addAll(beans);
						} catch (JSONException e) {
							e.printStackTrace();
						}
						swipe.setRefreshing(false);
					}
				});
	}
}
