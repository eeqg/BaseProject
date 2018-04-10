package com.project.base.baseproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class HomeFragmentAdapter extends FragmentStatePagerAdapter {
	public HomeFragmentAdapter(FragmentManager fm) {
		super(fm);
	}
	
	@Override
	public Fragment getItem(int position) {
		return TestFragment.getInstance(position);
	}
	
	@Override
	public int getCount() {
		return 4;
	}
}
