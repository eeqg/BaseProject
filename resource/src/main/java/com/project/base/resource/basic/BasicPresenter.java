package com.project.base.resource.basic;

import rx.Subscription;

public abstract class BasicPresenter<V extends BasicContract.View> implements BasicContract.Presenter {
	protected V basicView;
	
	public BasicPresenter(V basicView) {
		this.basicView = basicView;
		// noinspection unchecked
		this.basicView.setBasicPresenter(this);
	}
	
	@Override
	public void subscribe(Subscription subscription) {
		
	}
	
	@Override
	public void unSubscribe() {
		
	}
}
