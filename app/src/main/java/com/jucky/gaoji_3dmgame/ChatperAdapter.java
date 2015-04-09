package com.jucky.gaoji_3dmgame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ChatperAdapter extends FragmentPagerAdapter {
	private static final String[] chapter = {  "文章首页", "新闻", "游戏杂谈", "硬件信息",
            "游戏前瞻", "游戏评测", "原创精品", "游戏盘点", "时事焦点", "攻略中心", };
	private static final int[] typeIds = { 1, 2, 151, 152, 153, 154, 196, 197,
			199, 25, };

	public ChatperAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		Fragment fragment = new ChatperFragment();
		Bundle bundle = new  Bundle();
		bundle.putInt("typeId", typeIds[arg0]);
		bundle.putString("chapter", chapter[arg0]);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public int getCount() {
		return typeIds.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return chapter[position];
	}

}
