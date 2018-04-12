package com.project.base.resource.web;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

import com.project.base.resource.R;
import com.project.base.resource.basic.BasicActivity;
import com.project.base.resource.basic.BasicContract;
import com.project.base.resource.databinding.WebActivityWebBinding;

public class LoadUrlWebActivity extends BasicActivity<BasicContract.Presenter> {
	/** 标题 */
	public static final String TITLE = "title";
	/** 链接地址 */
	public static final String URL = "url";
	
	private WebActivityWebBinding dataBinding;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.dataBinding = DataBindingUtil.setContentView(this, R.layout.web_activity_web);
		this.dataBinding.setLeftAction(createLeftBack());
		
		Bundle bundle = getIntent().getExtras();
		if (bundle == null) {
			finish();
			return;
		}
		
		observeWeb();
		
		this.dataBinding.setTitle(bundle.getString(TITLE));
		String url = bundle.getString(URL);
		
		this.dataBinding.webView.loadUrl(url);
	}
	
	/**
	 * 浏览器
	 */
	@SuppressLint("SetJavaScriptEnabled")
	private void observeWeb() {
		this.dataBinding.webView.setWebViewClient(new WebViewClient());
		this.dataBinding.webView.setWebChromeClient(new WebChromeClient());
		this.dataBinding.webView.getSettings().setJavaScriptEnabled(true);
	}
	
	@Override
	public void onBackPressed() {
		if (this.dataBinding.webView.canGoBack()) {
			this.dataBinding.webView.goBack();
			return;
		}
		super.onBackPressed();
	}
}
