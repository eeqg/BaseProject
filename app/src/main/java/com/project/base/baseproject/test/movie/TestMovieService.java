package com.project.base.baseproject.test.movie;

import com.project.base.baseproject.test.movie.bean.MovieInfoBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wp on 2018/4/10.
 */

public interface TestMovieService {
	/**
	 *
	 * @param start
	 * @param count
	 * @return
	 */
	@GET("top250")
	Observable<MovieInfoBean> listMovie(@Query("start") int start, @Query("count") int count);
}
