package com.project.base.baseproject.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.project.base.baseproject.test.TestFragment;
import com.project.base.baseproject.test.movie.TestMovieFragment;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class HomeFragmentAdapter extends FragmentStatePagerAdapter {
	public HomeFragmentAdapter(FragmentManager fm) {
		super(fm);
	}
	
	@Override
	public Fragment getItem(int position) {
		if(position == 2){
			return new TestMovieFragment();
		}
		return TestFragment.getInstance(position);
	}
	
	@Override
	public int getCount() {
		return 4;
	}
}
