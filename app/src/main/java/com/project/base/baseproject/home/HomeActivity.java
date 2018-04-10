package com.project.base.baseproject.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import com.project.base.baseproject.R;
import com.project.base.baseproject.databinding.ActivityHomeBinding;
import com.project.base.resource.basic.BasicActivity;

public class HomeActivity extends BasicActivity {
	
	private ActivityHomeBinding dataBinding;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
		
		observeContent();
	}
	
	private void observeContent(){
		HomeFragmentAdapter tabFragmentAdapter = new HomeFragmentAdapter(getSupportFragmentManager());
		this.dataBinding.viewPager.setAdapter(tabFragmentAdapter);
		
		//this.dataBinding.tabLayout.setupWithViewPager(this.dataBinding.viewPager);
		
		this.dataBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				dataBinding.viewPager.setCurrentItem(tab.getPosition());
			}
			
			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
			
			}
			
			@Override
			public void onTabReselected(TabLayout.Tab tab) {
			
			}
		});
	}
}
