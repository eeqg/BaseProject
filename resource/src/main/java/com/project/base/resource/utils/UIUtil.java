package com.project.base.resource.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.project.base.resource.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 控制UI界面工具类
 */

public class UIUtil {
	
	/**
	 * 设置页面的透明度
	 *
	 * @param bgAlpha 0~1 ; 1表示透明
	 */
	public static void setBackgroundAlpha(Activity activity, float bgAlpha) {
		WindowManager.LayoutParams params = activity.getWindow().getAttributes();
		params.alpha = bgAlpha;
		if (bgAlpha == 1) {
			activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
		} else {
			activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//此行代码主要是解决在华为手机上半透明效果无效的bug
		}
		activity.getWindow().setAttributes(params);
	}
	
	/**
	 * 高亮字符串
	 *
	 * @param content 内容
	 * @param start   开始下标
	 * @param end     结束下标
	 * @param color   高亮颜色
	 * @return 高亮的字符串
	 */
	public static SpannableStringBuilder convert2HighLightString(String content,
	                                                             int start,
	                                                             int end,
	                                                             int color) {
		if (start == -1 || end == -1) return new SpannableStringBuilder(content);
		return convert2HighLightString(new SpannableStringBuilder(content), start, end, color);
	}
	
	/**
	 * 高亮字符串
	 *
	 * @param content 内容
	 * @param start   开始下标
	 * @param end     结束下标
	 * @param color   高亮颜色
	 * @return 高亮的字符串
	 */
	public static SpannableStringBuilder convert2HighLightString(SpannableStringBuilder content,
	                                                             int start,
	                                                             int end,
	                                                             int color) {
		//字体颜色
		ForegroundColorSpan foreColor = new ForegroundColorSpan(color);
		content.setSpan(foreColor, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return content;
	}
	
	/**
	 * 改变字符串大小
	 *
	 * @param content 内容
	 * @param start   开始下标
	 * @param end     结束下标
	 * @param size    字体大小
	 * @return 高亮的字符串
	 */
	public static SpannableStringBuilder convert2HighSizeString(String content,
	                                                            int start,
	                                                            int end,
	                                                            int size) {
		
		return convert2HighSizeString(new SpannableStringBuilder(content), start, end, size);
	}
	
	
	/**
	 * 改变字符串大小
	 *
	 * @param content 内容
	 * @param start   开始下标
	 * @param end     结束下标
	 * @param size    字体大小
	 * @return 高亮的字符串
	 */
	public static SpannableStringBuilder convert2HighSizeString(SpannableStringBuilder content,
	                                                            int start,
	                                                            int end,
	                                                            int size) {
		// 字体大小
		AbsoluteSizeSpan span = new AbsoluteSizeSpan(size);
		content.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return content;
	}
	
	
	/**
	 * 高亮并改变大小字符串
	 *
	 * @param content 内容
	 * @param start   开始下标
	 * @param end     结束下标
	 * @param color   高亮颜色
	 * @param size    字体大小
	 * @return 高亮的字符串
	 */
	public static SpannableStringBuilder convert2HighLightSizeString(String content,
	                                                                 int start,
	                                                                 int end,
	                                                                 int color,
	                                                                 int size) {
		
		SpannableStringBuilder spannableString = new SpannableStringBuilder(content);
		//字体颜色
		ForegroundColorSpan foreColor = new ForegroundColorSpan(color);
		// 字体大小
		AbsoluteSizeSpan span = new AbsoluteSizeSpan(size);
		spannableString.setSpan(foreColor, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		spannableString.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return spannableString;
	}
	
	/**
	 * 高亮并改变大小字符串
	 *
	 * @param content 内容
	 * @param start   开始下标
	 * @param end     结束下标
	 * @param color   高亮颜色
	 * @param size    字体大小
	 * @return 高亮的字符串
	 */
	public static SpannableStringBuilder convert2HighLightSizeString(Spanned content,
	                                                                 int start,
	                                                                 int end,
	                                                                 int color,
	                                                                 int size) {
		
		SpannableStringBuilder spannableString = new SpannableStringBuilder(content);
		//字体颜色
		ForegroundColorSpan foreColor = new ForegroundColorSpan(color);
		// 字体大小
		AbsoluteSizeSpan span = new AbsoluteSizeSpan(size);
		spannableString.setSpan(foreColor, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		spannableString.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return spannableString;
	}
	
	/**
	 * 改变字符串大小并加粗
	 *
	 * @param content 内容
	 * @param start   开始下标
	 * @param end     结束下标
	 * @param size    字体大小
	 * @return 高亮的字符串
	 */
	public static SpannableStringBuilder convert2LargeBoldString(String content,
	                                                             int start,
	                                                             int end,
	                                                             int size) {
		
		SpannableStringBuilder spannableString = new SpannableStringBuilder(content);
		//字体加粗
		spannableString.setSpan(new StyleSpan(Typeface.BOLD),
				start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// 字体大小
		AbsoluteSizeSpan span = new AbsoluteSizeSpan(size);
		spannableString.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return spannableString;
	}
	
	/**
	 * 改变字符串大小并高亮加粗
	 *
	 * @param content 内容
	 * @param start   开始下标
	 * @param end     结束下标
	 * @param size    字体大小
	 * @return 高亮的字符串
	 */
	public static SpannableStringBuilder convert2LargeHighLightBoldString(String content,
	                                                                      int start,
	                                                                      int end,
	                                                                      int color,
	                                                                      int size) {
		
		SpannableStringBuilder spannableString = new SpannableStringBuilder(content);
		//字体加粗
		spannableString.setSpan(new StyleSpan(Typeface.BOLD),
				start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// 字体大小
		AbsoluteSizeSpan span = new AbsoluteSizeSpan(size);
		spannableString.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		//字体颜色
		ForegroundColorSpan foreColor = new ForegroundColorSpan(color);
		spannableString.setSpan(foreColor, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return spannableString;
	}
	
	/**
	 * 改变字符串大小并高亮显示（不同部位）
	 *
	 * @param content    内容
	 * @param sizeStart  开始下标
	 * @param sizeEnd    结束下标
	 * @param colorStart 开始下标
	 * @param colorEnd   结束下标
	 * @param size       字体大小
	 * @return 高亮的字符串
	 */
	public static SpannableStringBuilder convert2LargeHighLightString(String content,
	                                                                  int colorStart,
	                                                                  int colorEnd,
	                                                                  int color,
	                                                                  int sizeStart,
	                                                                  int sizeEnd,
	                                                                  int size) {
		
		SpannableStringBuilder spannableString = new SpannableStringBuilder(content);
		//        //字体颜色
		ForegroundColorSpan foreColor = new ForegroundColorSpan(color);
		spannableString.setSpan(foreColor, colorStart, colorEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		// 字体大小
		RelativeSizeSpan span = new RelativeSizeSpan(1.5f);
		spannableString.setSpan(span, sizeStart, sizeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		return spannableString;
	}
	
	/**
	 * 粗体字符串
	 *
	 * @param content 内容
	 * @param start   开始下标
	 * @param end     结束下标
	 */
	public static SpannableStringBuilder convert2BoldString(String content,
	                                                        int start,
	                                                        int end) {
		SpannableStringBuilder spannableString = new SpannableStringBuilder(content);
		spannableString.setSpan(new StyleSpan(Typeface.BOLD),
				start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return spannableString;
	}
	
	/**
	 * 粗体字符串
	 *
	 * @param content 内容
	 * @param start   开始下标
	 * @param end     结束下标
	 */
	public static SpannableStringBuilder convert2LargeString(String content,
	                                                         int size,
	                                                         int start,
	                                                         int end) {
		
		SpannableStringBuilder spannableString = new SpannableStringBuilder(content);
		// 字体大小
		AbsoluteSizeSpan span = new AbsoluteSizeSpan(size);
		spannableString.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return spannableString;
	}
	
	/**
	 * 隐藏状态栏
	 *
	 * @param act act
	 */
	public static void hideStatusBar(Activity act) {
		View decorView = act.getWindow().getDecorView();
		decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				| View.SYSTEM_UI_FLAG_FULLSCREEN
				| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		//        decorView.setSystemUiVisibility(
		//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
		//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
		//                        | View.SYSTEM_UI_FLAG_FULLSCREEN
		//                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
	}
	
	/**
	 * 隐藏状态栏
	 *
	 * @param act act
	 */
	public static void hideStatusBarWithNoLimits(Activity act) {
		hideStatusBar(act);
		addFlagLayoutNoLimits(act);
	}
	
	/**
	 * 隐藏导航栏
	 *
	 * @param act act
	 */
	public static void hideNavigationBar(Activity act) {
		View decorView = act.getWindow().getDecorView();
		decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
	}
	
	/**
	 * 隐藏导航栏
	 *
	 * @param act act
	 */
	public static void hideNavigationBarWithNoLimits(Activity act) {
		hideNavigationBar(act);
		addFlagLayoutNoLimits(act);
	}
	
	/**
	 * 隐藏状态栏和导航栏
	 *
	 * @param act act
	 */
	public static void hideStatusAndNavigationBar(Activity act) {
		View decorView = act.getWindow().getDecorView();
		decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_FULLSCREEN
				| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
	}
	
	/**
	 * 隐藏状态栏和导航栏
	 *
	 * @param act act
	 */
	public static void hideStatusAndNavigationBarWithNoLimits(Activity act) {
		hideStatusAndNavigationBar(act);
		addFlagLayoutNoLimits(act);
	}
	
	/**
	 * 显示状态栏和导航栏
	 *
	 * @param act act
	 */
	public static void showStatusAndNavigationBar(Activity act) {
		View decorView = act.getWindow().getDecorView();
		decorView.setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
		act.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
	}
	
	/**
	 * 显示状态栏和导航栏
	 *
	 * @param act act
	 */
	public static void showStatusAndNavigationBarWithClearNoLimits(Activity act) {
		showStatusAndNavigationBar(act);
		clearFlagLayoutNoLimits(act);
	}
	
	/**
	 * 添加去除布局限制flag
	 *
	 * @param act act
	 */
	public static void addFlagLayoutNoLimits(Activity act) {
		act.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		
	}
	
	/**
	 * 清除去除布局限制flag
	 *
	 * @param act act
	 */
	public static void clearFlagLayoutNoLimits(Activity act) {
		act.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		
	}
	
	/**
	 * 带转场动画的跳转
	 *
	 * @param intent intent
	 * @param view   带动画的View
	 * @param nameId transitionName的Id
	 */
	public static void startActivityWithTransition(Activity act,
	                                               Intent intent,
	                                               View view,
	                                               int nameId) {
		ActivityOptionsCompat transitionActivityOptions =
				ActivityOptionsCompat.makeSceneTransitionAnimation(act, view,
						act.getString(nameId));
		
		ActivityCompat.startActivity(act,
				intent, transitionActivityOptions.toBundle());
	}
	
	/**
	 * 带转场动画的finish
	 */
	public static void finishWithTransition(Activity act) {
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			act.finishAfterTransition();
		} else {
			act.finish();
		}
	}
	
	/**
	 * 隐藏导航栏
	 *
	 * @param act act
	 */
	public static int getSystemUiVisibility(Activity act) {
		View decorView = act.getWindow().getDecorView();
		return decorView.getSystemUiVisibility();
	}
	
	public static void setSystemUiVisibility(Activity act, int systemUiVisibility) {
		View decorView = act.getWindow().getDecorView();
		decorView.setSystemUiVisibility(systemUiVisibility);
	}
	
	/** 设置状态栏颜色 */
	public static void setStatusTransparent(Activity act) {
		setStatusColor(act, R.color.base_transparent);
		setStatusBarFlagFullScreen(act);
	}
	
	/** 设置全景状态栏flag */
	public static void setStatusBarFlagFullScreen(Activity act) {
		act.getWindow()
				.getDecorView()
				.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
						| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
	}
	
	
	/** 改变状态栏颜色 */
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public static void setStatusColor(Activity act, int colorId) {
		colorId = ResourceUtil.getColor(act, colorId);
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			act.getWindow().setStatusBarColor(colorId);
		}
	}
	
	/** 改变状态栏颜色,深色 */
	public static void setStatusBarLightMode(Activity activity, int color, boolean darkMode) {
		//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
		//            //判断是否为小米或魅族手机，如果是则将状态栏文字改为黑色
		//            if (setStatusBarLightMode4MIUI(activity, darkMode) || setStatusBarLightMode4Flyme(activity, darkMode)) {
		//                //设置状态栏为指定颜色
		//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0
		//                    activity.getWindow().setStatusBarColor(color);
		//                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4
		//                    //调用修改状态栏颜色的方法
		//                    setStatusColor(activity, color);
		//                }
		//            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
		//                if (darkMode) {
		//                    //如果是6.0以上将状态栏文字改为黑色，并设置状态栏颜色
		//                    activity.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		//                    activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
		//                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		//                }else{
		//                    activity.getWindow().setBackgroundDrawable(null);
		//                    activity.getWindow().setStatusBarColor(color);
		//                }
		//
		//                //fitsSystemWindow 为 false, 不预留系统栏位置.
		//                ViewGroup mContentView = (ViewGroup) activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
		//                View mChildView = mContentView.getChildAt(0);
		//                if (mChildView != null) {
		//                    ViewCompat.setFitsSystemWindows(mChildView, darkMode);
		//                    ViewCompat.requestApplyInsets(mChildView);
		//                }
		//
		//            }else {
		//                UIUtil.setStatusColor(activity,color);
		//            }
		//        }
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			setStatusColor(activity, color);
			if (darkMode) {
				//如果是6.0以上将状态栏文字改为黑色，并设置状态栏颜色
				activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
						View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
			} else {
				activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
						View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
			}
			
		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			if (darkMode) {
				setStatusColor(activity, R.color.base_transparent_half);
			} else {
				setStatusColor(activity, color);
			}
		}
	}
	
	
	/** 小米MiUi修改状态栏方法 */
	private static boolean setStatusBarLightMode4MIUI(Activity activity, boolean darkMode) {
		boolean result = false;
		Class<? extends Window> clazz = activity.getWindow().getClass();
		try {
			int darkModeFlag = 0;
			Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
			Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
			darkModeFlag = field.getInt(layoutParams);
			Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
			extraFlagField.invoke(activity.getWindow(), darkMode ? darkModeFlag : 0, darkModeFlag);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Window window = activity.getWindow();
		if (darkMode) {
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		} else {
			window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			setStatusBarFlagFullScreen(activity);
		}
		return result;
	}
	
	/** Flyme修改状态栏方法 */
	private static boolean setStatusBarLightMode4Flyme(Activity activity, boolean darkMode) {
		boolean result = false;
		try {
			WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
			Field darkFlag = WindowManager.LayoutParams.class
					.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
			Field meizuFlags = WindowManager.LayoutParams.class
					.getDeclaredField("meizuFlags");
			darkFlag.setAccessible(true);
			meizuFlags.setAccessible(true);
			int bit = darkFlag.getInt(null);
			int value = meizuFlags.getInt(lp);
			if (darkMode) {
				value |= bit;
			} else {
				value &= ~bit;
			}
			meizuFlags.setInt(lp, value);
			activity.getWindow().setAttributes(lp);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 在适配器更新后，根据适配器计算listview的高度，然后动态的设置高度
	 * 用于解决使用内嵌ListView后最后一项显示不全的问题
	 *
	 * @param listView
	 */
	public static void setListViewHeightBasedOnChildren(Context context, ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		if (listAdapter == null) {
			// pre-condition
			return;
		}
		int totalHeight = 0;
		View view;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			view = listAdapter.getView(i, null, listView);
			//宽度为屏幕宽度
			int i1 = View.MeasureSpec.makeMeasureSpec(ScreenUtils.getScreenWidth(context),
					View.MeasureSpec.EXACTLY);
			//根据屏幕宽度计算高度
			int i2 = View.MeasureSpec.makeMeasureSpec(i1, View.MeasureSpec.UNSPECIFIED);
			view.measure(i1, i2);
			totalHeight += view.getMeasuredHeight();
		}
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}
	
}
