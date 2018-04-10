package com.project.base.baseproject.test.movie;

import com.project.base.baseproject.test.movie.bean.MovieInfoBean;
import com.project.base.resource.basic.BasicContract;

import rx.Subscription;

/**
 * Created by wp on 2018/4/10.
 */

interface TestMovieContract {
	interface View extends BasicContract.View<Presenter> {
		void updateMovieList(MovieInfoBean movieInfoBean);
	}
	
	interface Presenter extends BasicContract.Presenter {
		Subscription listMovie(int start, int count);
	}
}
