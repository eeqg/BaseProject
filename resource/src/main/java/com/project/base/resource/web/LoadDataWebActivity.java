package com.project.base.resource.web;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

import com.project.base.resource.R;
import com.project.base.resource.basic.BasicActivity;
import com.project.base.resource.basic.BasicConst;
import com.project.base.resource.databinding.WebActivityWebBinding;

public class LoadDataWebActivity extends BasicActivity {
	/** 标题 */
	public static final String TITLE = "title";
	/** 内容 */
	public static final String CONTENT = "content";
	
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
		
		this.dataBinding.setTitle(bundle.getString(TITLE));
		String dataContent = bundle.getString(CONTENT);
		
		this.dataBinding.webView.setWebChromeClient(new WebChromeClient());
		this.dataBinding.webView.setWebViewClient(new WebViewClient());
		this.dataBinding.webView.loadData(BasicConst.wrapWebContent(dataContent), BasicConst.WEB_MIME_TYPE, null);
	}
}
