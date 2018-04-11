package com.project.base.resource.network;

import android.support.annotation.NonNull;

import com.project.base.resource.basic.BasicApp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/12/7 0007.
 */

public class Network {
	
	private static Network INSTANCE;
	private String realUrl;
	private Retrofit.Builder retrofitBuilder;
	
	public static <T> T create(Class<T> service) {
		if (INSTANCE == null) {
			throw new NullPointerException("config realUrl before create service!");
		}
		return INSTANCE.createService(service);
	}
	
	public static <T> T createWith(String baseUrl, Class<T> service) {
		if (INSTANCE == null) {
			throw new NullPointerException("config realUrl before create service!");
		}
		return INSTANCE.createServiceWith(baseUrl, service);
	}
	
	private <T> T createService(Class<T> service) {
		return this.retrofitBuilder
				.baseUrl(getReplaceUrl())
				.build()
				.create(service);
	}
	
	private <T> T createServiceWith(String baseUrl, Class<T> service) {
		return retrofitBuilder
				.baseUrl(baseUrl)
				.build()
				.create(service);
	}
	
	private OkHttpClient createOkHttpClient() {
		return (new okhttp3.OkHttpClient.Builder()).addInterceptor(new Interceptor() {
			public Response intercept(@NonNull Chain chain) throws IOException {
				Request request = chain.request();
				
				String requestUrl = request.url().toString();
				String replaceUrl = getReplaceUrl();
				String httpUrl;
				if (requestUrl.startsWith(replaceUrl)) {
					httpUrl = getRealUrl() + requestUrl.substring(replaceUrl.length());
				} else {
					System.out.println(requestUrl);
					return chain.proceed(request);
				}
				if (BasicApp.isDebug()) {
					System.out.println("httpUrl = " + httpUrl);
				}
				
				RequestBody requestBody = request.body();
				if (requestBody instanceof FormBody) {
					request = request.newBuilder()
							.url(httpUrl)
							// .headers(getHeaders(true))
							// .method(request.method(), convertBody((FormBody) requestBody))
							.build();
				} else if (requestBody instanceof MultipartBody) {
					request = request.newBuilder()
							.url(httpUrl)
							// .method(request.method(), convertBody((MultipartBody) requestBody))
							// .headers(getHeaders(false))
							.build();
				}
				// else if (requestBody instanceof BasicBody) {
				// 	request = request.newBuilder()
				// 			.url(requestUrl)
				// 			.headers(getHeaders(((BasicBody) requestBody).isEncrypt()))
				// 			.build();
				// }
				else {
					request = request.newBuilder()
							.url(httpUrl)
							// .method(request.method(), convertBody())
							// .headers(getHeaders(true))
							.build();
				}
				
				return chain.proceed(request);
			}
		}).writeTimeout(30L, TimeUnit.SECONDS).build();
	}
	
	private Headers getHeaders(boolean isEncrypt) {
		return new Headers.Builder()
				.add("cloudlicence", "Q29mZmVlQmVhbjEyMzQ1Ng%3D%3D")
				.add("isencrypt", isEncrypt ? "1" : "0")
				.add("Content-Type", "application/json")
				.add("User-Agent", "imgfornote")
				.build();
	}
	
	private String getReplaceUrl() {
		return "http://www.url.com/";
	}
	
	private String getRealUrl() {
		return this.realUrl;
	}
	
	private void setRealUrl(String realUrl) {
		this.realUrl = realUrl;
	}
	
	private void iniBuilder() {
		if (this.retrofitBuilder == null) {
			this.retrofitBuilder = new Retrofit.Builder()
					.client(createOkHttpClient())
					// .addConverterFactory(GsonConverterFactory.create())
					.addConverterFactory(CustomGsonConverterFactory.create())//custom
					.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
		}
	}
	
	public static void configRealUrl(String realUrl) {
		if (INSTANCE == null) {
			synchronized (Network.class) {
				if (INSTANCE == null) {
					INSTANCE = new Network();
				}
			}
		}
		INSTANCE.iniBuilder();
		INSTANCE.setRealUrl(realUrl);
	}
}