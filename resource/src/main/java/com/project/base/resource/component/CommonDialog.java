package com.project.base.resource.component;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.project.base.resource.R;
import com.project.base.resource.basic.BasicApp;
import com.project.base.resource.databinding.DialogCommonBinding;

public class CommonDialog extends Dialog {
	private DialogCommonBinding dataBinding;
	
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;
	
	/** 否定 */
	private String negativeText;
	private View.OnClickListener negativeClickListener;
	/** 肯定 */
	private String positiveText;
	private View.OnClickListener positiveClickListener;
	
	public CommonDialog(Context context) {
		super(context, R.style.DialogTransparentTheme);
	}
	
	/**
	 * 设置标题
	 *
	 * @param title 标题
	 * @return 窗口实例
	 */
	public CommonDialog setTitle(String title) {
		this.title = title;
		return this;
	}
	
	/**
	 * 设置标题
	 *
	 * @param titleResId 标题ResId
	 * @return 窗口实例
	 */
	public CommonDialog setTitleResId(int titleResId) {
		this.title = getContext().getString(titleResId);
		return this;
	}
	
	/**
	 * 设置内容
	 *
	 * @param content 内容
	 * @return 窗口实例
	 */
	public CommonDialog setContent(String content) {
		this.content = content;
		return this;
	}
	
	/**
	 * 设置内容
	 *
	 * @param contentResId 内容ResId
	 * @return 窗口实例
	 */
	public CommonDialog setContentResId(int contentResId) {
		this.content = getContext().getString(contentResId);
		return this;
	}
	
	/**
	 * 设置否定监听
	 *
	 * @param negativeText 否定文本
	 * @param listener     否定监听器
	 * @return 窗口实例
	 */
	public CommonDialog setNegativeClickListener(String negativeText, View.OnClickListener listener) {
		this.negativeText = negativeText;
		this.negativeClickListener = listener;
		return this;
	}
	
	/**
	 * 设置否定监听
	 *
	 * @param negativeResId 否定文本ResId
	 * @param listener      否定监听器
	 * @return 窗口实例
	 */
	public CommonDialog setNegativeClickListener(int negativeResId, View.OnClickListener listener) {
		this.negativeText = getContext().getString(negativeResId);
		this.negativeClickListener = listener;
		return this;
	}
	
	/**
	 * 设置肯定监听
	 *
	 * @param positiveText 肯定文本
	 * @param listener     肯定监听器
	 * @return 窗口实例
	 */
	public CommonDialog setPositiveClickListener(String positiveText, View.OnClickListener listener) {
		this.positiveText = positiveText;
		this.positiveClickListener = listener;
		return this;
	}
	
	/**
	 * 设置肯定监听
	 *
	 * @param positiveResId 肯定文本ResId
	 * @param listener      肯定监听器
	 * @return 窗口实例
	 */
	public CommonDialog setPositiveClickListener(int positiveResId, View.OnClickListener listener) {
		this.positiveText = getContext().getString(positiveResId);
		this.positiveClickListener = listener;
		return this;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.dataBinding = DataBindingUtil.inflate(
				getLayoutInflater(),
				R.layout.dialog_common,
				null, false
		);
		setContentView(this.dataBinding.getRoot());
		
		this.dataBinding.setTitle(title);
		this.dataBinding.setContent(content);
		
		observeNegative();
		observePositive();
	}
	
	/**
	 * 否定操作
	 */
	private void observeNegative() {
		this.dataBinding.setNegativeText(this.negativeText);
		this.dataBinding.setNegativeClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
				if (negativeClickListener != null) {
					negativeClickListener.onClick(v);
				}
			}
		});
	}
	
	/**
	 * 肯定操作
	 */
	private void observePositive() {
		this.dataBinding.setPositiveText(this.positiveText);
		this.dataBinding.setPositiveClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
				if (positiveClickListener != null) {
					positiveClickListener.onClick(v);
				}
			}
		});
	}
	
	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		
		Window window = getWindow();
		if (window == null) {
			return;
		}
		
		WindowManager.LayoutParams layoutParams = window.getAttributes();
		layoutParams.width = (int) (BasicApp.SCREEN_WIDTH * 0.8);
		window.setAttributes(layoutParams);
	}
}
