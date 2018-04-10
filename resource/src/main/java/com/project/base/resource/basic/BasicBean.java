package com.project.base.resource.basic;

import android.databinding.BaseObservable;

import com.project.base.resource.network.StatusInfo;

public class BasicBean extends BaseObservable {
	public StatusInfo statusInfo = new StatusInfo();
	
	public String resultData;
}
