package com.project.base.resource.basic;

import android.content.Context;

import com.trello.rxlifecycle.LifecycleTransformer;

import rx.Subscription;

public interface BasicContract {
	
	interface View<P> {
		
		<L> LifecycleTransformer<L> bindUntilEvent(ViewEvent event);
		
		<L> LifecycleTransformer<L> bindToLifecycle();
		
		Context getContext();
		
		boolean needLogin();
		
		void tokenTimeOut();
		
		void tokenNotFound();
		
		void setBasicPresenter(P presenter);
		
		void showLoading();
		
		void hideLoading();
		
		void promptMessage(int resId);
		
		void promptMessage(String message);
	}
	
	interface Presenter {
		
		void subscribe(Subscription subscription);
		
		void unSubscribe();
	}
}
