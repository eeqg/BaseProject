package com.project.base.baseproject.basic;

import android.content.Context;

import com.project.base.resource.basic.BasicApp;
import com.project.base.resource.network.Network;

/**
 * Created by wp on 2018/4/11.
 */

public class BasicAppImpl extends BasicApp {
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		Network.configRealUrl("");
	}
	
	@Override
	public void logout(Context context) {
	
	}
	
	@Override
	public void cleanTop(Context context) {
	
	}
	
	@Override
	public void requestLogin(Context context, int requestCode) {
	
	}
}
