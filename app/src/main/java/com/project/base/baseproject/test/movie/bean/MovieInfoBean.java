package com.project.base.baseproject.test.movie.bean;

import com.google.gson.annotations.SerializedName;
import com.project.base.resource.basic.BasicBean;

import java.util.ArrayList;

/**
 * Created by wp on 2018/4/10.
 */

public class MovieInfoBean extends BasicBean {
	public String title;
	public int total;
	@SerializedName("subjects")
	public ArrayList<MovieItemBean> movieList;
}
