package com.project.base.resource.network;

import com.project.base.resource.basic.BasicBean;
import com.project.base.resource.utils.LogUtils;

import rx.Subscriber;

public abstract class AbstractNetworkSubscriber<T extends BasicBean> extends Subscriber<T> {
	
	@Override
	public final void onStart() {
		// LogUtils.d("test_wp", "onStart()");
		taskStart();
	}
	
	@Override
	public final void onNext(T basicBean) {
		// LogUtils.d("test_wp", "onNext()");
		if (basicBean.statusInfo.isSuccessful()) {
			taskSuccess(basicBean);
		} else if (basicBean.statusInfo.isOther()) {
			taskOther(basicBean);
		} else {
			taskFailure(basicBean);
		}
	}
	
	@Override
	public final void onCompleted() {
		// LogUtils.d("test_wp", "onCompleted()");
		taskStop();
	}
	
	@Override
	public final void onError(Throwable throwable) {
		// LogUtils.d("test_wp", "onError()");
		taskStop();
		throwable.printStackTrace();
		taskError(throwable);
	}
	
	/**
	 * 任务开始
	 */
	public abstract void taskStart();
	
	/**
	 * 任务结束
	 */
	public abstract void taskStop();
	
	/**
	 * 任务成功
	 *
	 * @param basicBean 结果信息
	 */
	public abstract void taskSuccess(T basicBean);
	
	/**
	 * 任务失败
	 *
	 * @param basicBean 结果信息
	 */
	public abstract void taskFailure(T basicBean);
	
	/**
	 * 任务特殊处理
	 *
	 * @param basicBean 结果信息
	 */
	public abstract void taskOther(T basicBean);
	
	/**
	 * 任务错误
	 *
	 * @param throwable 错误信息
	 */
	public abstract void taskError(Throwable throwable);
}
