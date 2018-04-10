package com.project.base.baseproject.test.movie;

import com.project.base.baseproject.test.movie.bean.MovieInfoBean;
import com.project.base.resource.basic.BasicPresenter;
import com.project.base.resource.basic.ViewEvent;
import com.project.base.resource.network.Network;
import com.project.base.resource.network.TaskSubscriber;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wp on 2018/4/10.
 */

class TestMoviePresenter extends BasicPresenter<TestMovieContract.View>
		implements TestMovieContract.Presenter {
	TestMoviePresenter(TestMovieContract.View basicView) {
		super(basicView);
		Network.configRealUrl("https://api.douban.com/v2/movie/");
	}
	
	@Override
	public Subscription listMovie(int start, int count) {
		return Network.create(TestMovieService.class)
				.listMovie(start, count)
				.compose(basicView.<MovieInfoBean>bindUntilEvent(ViewEvent.DESTROY))
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new TaskSubscriber<MovieInfoBean>(basicView) {
					@Override
					public void taskSuccess(MovieInfoBean basicBean) {
						super.taskSuccess(basicBean);
						basicView.updateMovieList(basicBean);
					}
					
					@Override
					public void taskFailure(MovieInfoBean basicBean) {
						super.taskFailure(basicBean);
						basicView.updateMovieList(basicBean);
					}
					
					@Override
					public void taskError(Throwable throwable) {
						super.taskError(throwable);
						basicView.updateMovieList(null);
					}
				});
	}
}
