package com.project.base.baseproject.test.movie;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kycq.library.refresh.RecyclerAdapter;
import com.project.base.baseproject.R;
import com.project.base.baseproject.databinding.FragmentTestMovieBinding;
import com.project.base.baseproject.test.movie.bean.MovieInfoBean;
import com.project.base.resource.basic.BasicFragment;
import com.project.base.resource.component.NormalItemDecoration;
import com.project.base.resource.utils.LogUtils;

import rx.Subscription;

/**
 * Created by wp on 2018/4/10.
 */

public class TestMovieFragment extends BasicFragment<TestMovieContract.Presenter>
		implements TestMovieContract.View {
	
	private FragmentTestMovieBinding dataBinding;
	private TestMovieListAdapter testMovieListAdapter;
	private boolean isCreated, isVisible;
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		this.dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_test_movie, container, false);
		return this.dataBinding.getRoot();
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		new TestMoviePresenter(this);
		
		observeContent();
		
		isCreated = true;
		if (isVisible) {
			this.testMovieListAdapter.swipeRefresh();
		}
		this.testMovieListAdapter.swipeRefresh();
	}
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		
		isVisible = isVisibleToUser;
		if (isCreated && isVisible && this.testMovieListAdapter.getItemCount() == 0) {
			this.testMovieListAdapter.swipeRefresh();//request data.
		}
	}
	
	private void observeContent() {
		this.dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
		this.dataBinding.recyclerView.addItemDecoration(new NormalItemDecoration(this.getContext()));
		
		this.testMovieListAdapter = new TestMovieListAdapter(this.getContext());
		this.testMovieListAdapter.setRefreshLayout(this.dataBinding.refreshLayout);
		this.testMovieListAdapter.setRecyclerView(this.dataBinding.recyclerView);
		this.testMovieListAdapter.setOnTaskListener(new RecyclerAdapter.OnTaskListener<Subscription>() {
			@Override
			public Subscription onTask() {
				int currentPage = testMovieListAdapter.getCurrentPage();
				// LogUtils.d("currentPage=" + currentPage);
				int count = 20;
				int start = (currentPage - 1) * count;
				return basicPresenter.listMovie(start, count);
			}
			
			@Override
			public void onCancel(Subscription subscription) {
				subscription.unsubscribe();
			}
		});
	}
	
	@Override
	public void updateMovieList(MovieInfoBean movieInfoBean) {
		LogUtils.d("movieInfoBean" + movieInfoBean);
		this.testMovieListAdapter.swipeResult(movieInfoBean);
	}
}
