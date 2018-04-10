package com.project.base.resource.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kycq.library.refresh.RefreshHeader;
import com.project.base.resource.R;
import com.project.base.resource.network.StatusInfo;

public class BasicRefreshHeader extends LinearLayout
		implements RefreshHeader<StatusInfo> {
	private ImageView ivStatus;
	private TextView tvStatus;
	
	private AnimatorSet animatorSet;
	
	public BasicRefreshHeader(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		this.ivStatus = (ImageView) findViewById(R.id.ivStatus);
		this.tvStatus = (TextView) findViewById(R.id.tvStatus);
		
		ObjectAnimator flipAnimator = ObjectAnimator.ofFloat(this.ivStatus, View.ROTATION_Y, 0, 90);
		flipAnimator.setRepeatMode(ValueAnimator.REVERSE);
		flipAnimator.setRepeatCount(ValueAnimator.INFINITE);
		
		ObjectAnimator recoveryAnimator = ObjectAnimator.ofFloat(this.ivStatus, View.ROTATION_Y, 90, 0);
		recoveryAnimator.setRepeatMode(ValueAnimator.REVERSE);
		recoveryAnimator.setRepeatCount(ValueAnimator.INFINITE);
		
		this.animatorSet = new AnimatorSet();
		this.animatorSet.setDuration(150);
		this.animatorSet.setInterpolator(new LinearInterpolator());
		this.animatorSet.play(flipAnimator).before(recoveryAnimator);
	}
	
	@Override
	public int getRefreshOffsetPosition() {
		return (int) (getMeasuredHeight() * 0.8);
	}
	
	@Override
	public void onRefreshScale(float scale) {
	}
	
	@Override
	public void onPullToRefresh() {
		this.animatorSet.end();
		this.ivStatus.setRotationY(0);
		this.tvStatus.setText(R.string.pull_to_refresh);
	}
	
	@Override
	public void onReleaseToRefresh() {
		this.animatorSet.end();
		this.ivStatus.setRotationY(0);
		this.tvStatus.setText(R.string.release_to_refresh);
	}
	
	@Override
	public void onRefresh() {
		this.animatorSet.start();
		this.tvStatus.setText(R.string.loading_dot);
	}
	
	@Override
	public void onRefreshComplete(StatusInfo statusInfo) {
		this.animatorSet.end();
		this.ivStatus.setRotationY(0);
		this.tvStatus.setText(R.string.refresh_complete);
	}
}
