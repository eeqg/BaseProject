package com.project.base.resource.network;

import com.project.base.resource.R;
import com.project.base.resource.basic.BasicBean;
import com.project.base.resource.basic.BasicContract;

import java.lang.ref.SoftReference;

public abstract class TaskSubscriber<T extends BasicBean> extends AbstractNetworkSubscriber<T> {
	private SoftReference<BasicContract.View> basicViewReference;
	
	protected TaskSubscriber(BasicContract.View basicView) {
		basicViewReference = new SoftReference<>(basicView);
	}
	
	@Override
	public void taskStart() {
		BasicContract.View basicView = this.basicViewReference.get();
		if (basicView != null) {
			basicView.showLoading();
		}
	}
	
	@Override
	public void taskStop() {
		BasicContract.View basicView = this.basicViewReference.get();
		if (basicView != null) {
			basicView.hideLoading();
		}
	}
	
	@Override
	public void taskSuccess(T basicBean) {
		BasicContract.View basicView = this.basicViewReference.get();
		if (basicView != null) {
			basicView.hideLoading();
		}
	}
	
	@Override
	public void taskOther(T basicBean) {
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
		BasicContract.View basicView = this.basicViewReference.get();
		if (basicView != null) {
			basicView.promptMessage(basicBean.statusInfo.statusMessage);
		}
	}
	
	@Override
	public void taskError(Throwable throwable) {
		BasicContract.View basicView = this.basicViewReference.get();
		if (basicView != null) {
			basicView.promptMessage(R.string.network_request_error);
		}
	}
}
