package com.project.base.baseproject.test;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.base.baseproject.R;
import com.project.base.baseproject.databinding.FragmentTestBinding;
import com.project.base.resource.basic.BasicFragment;

import java.security.SecureRandom;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class TestFragment extends BasicFragment {
	private static final String PAGE_TYPE = "page_type";
	private FragmentTestBinding dataBinding;
	private int pageType;
	
	public static TestFragment getInstance(int pageType){
		Bundle args = new Bundle();
		args.putInt(PAGE_TYPE, pageType);
		TestFragment fragment = new TestFragment();
		fragment.setArguments(args);
		
		return fragment;
	}
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pageType = getArguments().getInt(PAGE_TYPE);
	}
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_test, container, false);
		return dataBinding.getRoot();
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		//this.dataBinding.rootView.setBackgroundColor(ColorUtils.getRandomColor());
		this.dataBinding.tvTips.setBackgroundColor(getRandomColor(0xFF));
		this.dataBinding.setTips("TestFragment #"+pageType);
	}
	
	public int getRandomColor(int alpha) {
		SecureRandom rgen = new SecureRandom();
		return Color.HSVToColor(alpha, new float[]{(float)rgen.nextInt(359), 1.0F, 1.0F});
	}
}
