package com.project.base.baseproject.test.movie;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.base.baseproject.R;
import com.project.base.baseproject.databinding.FragmentTestMovieBinding;
import com.project.base.resource.basic.BasicFragment;

/**
 * Created by wp on 2018/4/10.
 */

public class TestMovieFragment extends BasicFragment {
	
	private FragmentTestMovieBinding dataBinding;
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		this.dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_test_movie, container, false);
		return this.dataBinding.getRoot();
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}
}
