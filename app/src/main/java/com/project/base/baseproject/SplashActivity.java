package com.project.base.baseproject;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import com.project.base.baseproject.home.HomeActivity;
import com.project.base.resource.basic.BasicActivity;
import com.project.base.resource.utils.PermissionUtils;

public class SplashActivity extends BasicActivity implements Handler.Callback {
	
	private final int CODE_HOME = 1;
	/** 退出应用 */
	private static final String EXIT = "exit";
	
	private Handler handler = new Handler(this);
	
	/**
	 * 退出应用
	 *
	 * @param context 设备上下文环境
	 */
	public static void exit(Context context) {
		Intent intent = new Intent(context, SplashActivity.class);
		intent.putExtra(EXIT, true);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DataBindingUtil.setContentView(this, R.layout.activity_splash);
		
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			if (bundle.getBoolean(EXIT)) {
				finish();
				return;
			}
		}
		
		requestPermissions();
	}
	
	/**
	 * 请求权限
	 */
	private void requestPermissions() {
		PermissionUtils.build(this)
				.setOnPermissionListener(new PermissionUtils.OnPermissionListener() {
					@Override
					public void onGranted() {
						handler.sendEmptyMessageDelayed(0, 1000);
					}
					
					@Override
					public void onDenied() {
						promptMessage("onDenied()");
						// TODO: 2018/4/13
					}
					
					@Override
					public void onRationale(String... permissions) {
						this.requestPermission(permissions);
					}
				})
				.requestPermissions(
						Manifest.permission.ACCESS_COARSE_LOCATION,
						Manifest.permission.ACCESS_FINE_LOCATION,
						Manifest.permission.ACCESS_WIFI_STATE,
						Manifest.permission.ACCESS_NETWORK_STATE,
						Manifest.permission.CHANGE_WIFI_STATE,
						Manifest.permission.READ_PHONE_STATE,
						Manifest.permission.WRITE_EXTERNAL_STORAGE,
						Manifest.permission.CAMERA
				);
	}
	
	/**
	 * 跳转首页
	 */
	private void turnHome() {
		Intent intent = new Intent(this, HomeActivity.class);
		startActivityForResult(intent, CODE_HOME);
	}
	
	@Override
	public boolean handleMessage(Message message) {
		if (isFinishing()) {
			return false;
		}
		turnHome();
		return true;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.handler.removeMessages(0);
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		PermissionUtils.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CODE_HOME) {
			finish();
			return;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
