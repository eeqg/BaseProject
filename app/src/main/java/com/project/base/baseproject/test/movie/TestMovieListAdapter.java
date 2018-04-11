package com.project.base.baseproject.test.movie;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.base.baseproject.R;
import com.project.base.baseproject.databinding.ItemMovieListBinding;
import com.project.base.baseproject.test.movie.bean.MovieInfoBean;
import com.project.base.resource.basic.BasicAdapter;

/**
 * Created by wp on 2018/4/10.
 */

public class TestMovieListAdapter extends BasicAdapter<MovieInfoBean> {
	
	private final LayoutInflater inflater;
	
	public TestMovieListAdapter(Context context) {
		this.inflater = LayoutInflater.from(context);
	}
	
	@Override
	public void updateAdapterInfo(@NonNull MovieInfoBean movieInfoBean) {
		this.mAdapterInfo.movieList.addAll(movieInfoBean.movieList);
	}
	
	@Override
	public boolean hasMore() {
		if (this.mAdapterInfo == null) {
			return false;
		}
		return this.mAdapterInfo.total > this.getItemCount();
	}
	
	@Override
	public int getItemCount() {
		int count = 0;
		if (this.mAdapterInfo != null && this.mAdapterInfo.movieList != null) {
			count = this.mAdapterInfo.movieList.size();
		}
		return count;
	}
	
	@Override
	public ItemHolder onCreateItemHolder(int viewType) {
		return new ItemHolder() {
			ItemMovieListBinding dataBinding;
			
			@Override
			protected View onCreateView(ViewGroup parent) {
				this.dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_movie_list, parent, false);
				return this.dataBinding.getRoot();
			}
			
			@Override
			protected void onBindView(int position) {
				this.dataBinding.setIndex(String.valueOf(position + 1));
				this.dataBinding.setMovieItemBean(mAdapterInfo.movieList.get(position));
				this.dataBinding.executePendingBindings();
			}
		};
	}
}
