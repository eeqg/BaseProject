package com.project.base.resource.network;

import com.project.base.resource.R;
import com.project.base.resource.basic.BasicBean;
import com.project.base.resource.basic.BasicContract;
import com.project.base.resource.log.LogUtils;

import java.lang.ref.SoftReference;

public abstract class TaskSubscriber<T extends BasicBean> extends AbstractNetworkSubscriber<T> {
	private SoftReference<BasicContract.View> basicViewReference;
	
	protected TaskSubscriber(BasicContract.View basicView) {
		basicViewReference = new SoftReference<>(basicView);
	}
	
	@Override
	public void taskStart() {
		LogUtils.d("test_wp", "taskStart()");
		BasicContract.View basicView = this.basicViewReference.get();
		if (basicView != null) {
			basicView.showLoading();
		}
	}
	
	@Override
	public void taskStop() {
		LogUtils.d("test_wp", "taskStop()");
		BasicContract.View basicView = this.basicViewReference.get();
		if (basicView != null) {
			basicView.hideLoading();
		}
	}
	
	@Override
	public void taskSuccess(T basicBean) {
		LogUtils.d("");
		BasicContract.View basicView = this.basicViewReference.get();
		if (basicView != null) {
			basicView.hideLoading();
		}
	}
	
	@Override
	public void taskOther(T basicBean) {
		LogUtils.d("test_wp", "taskOther()");
		BasicContract.View basicView = this.basicViewReference.get();
		if (basicView != null) {
			if (basicBean.statusInfo.isTokenTimeout()) {
				basicView.tokenTimeOut();
			} else if (basicBean.statusInfo.isTokenNotFound()) {
				basicView.tokenNotFound();
			} else {
				basicView.promptMessage(basicBean.statusInfo.statusMessage);
			}
		}
	}
	
	@Override
	public void taskFailure(T basicBean) {
		LogUtils.d("test_wp", "taskFailure()");
		BasicContract.View basicView = this.basicViewReference.get();
		if (basicView != null) {
			basicView.promptMessage(basicBean.statusInfo.statusMessage);
		}
	}
	
	@Override
	public void taskError(Throwable throwable) {
		LogUtils.d("test_wp", "taskError()");
		BasicContract.View basicView = this.basicViewReference.get();
		if (basicView != null) {
			basicView.promptMessage(R.string.network_request_error);
		}
	}
}
