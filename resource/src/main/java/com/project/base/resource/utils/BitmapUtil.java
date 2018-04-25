package com.project.base.resource.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Environment;
import android.util.Base64;

import com.project.base.resource.R;
import com.project.base.resource.basic.BasicApp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;

public class BitmapUtil {
	
	private static final String BASE_PATH = "base";
	
	public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.JPEG, 100, output);
		if (needRecycle) {
			bmp.recycle();
		}
		byte[] result = output.toByteArray();
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	public static void saveBitmap(Bitmap bitmap) {
		File dir = Environment.getExternalStoragePublicDirectory(BASE_PATH);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		File file = new File(dir, "test.jpg");
		try {
			FileOutputStream out = new FileOutputStream(file);
			bitmap.compress(CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();
		} catch (Exception ex) {
			LogUtils.e("保存图片失败", ex);
		}
	}
	
	@SuppressLint("NewApi")
	public static double getSize(Bitmap bitmap) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {    //API 19
			return bitmap.getAllocationByteCount();
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {  //API 12
			return bitmap.getByteCount();
		}
		return bitmap.getRowBytes() * bitmap.getHeight();                //earlier version
	}
	
	public static Bitmap compressImage(Bitmap image, int size) {
		if (image == null) {
			LogUtils.d("compressImage方法传入Bitmap为空");
			image = BitmapFactory.decodeResource(BasicApp.INSTANCE.getResources(), R.drawable.ic_launcher_background);
			return image;
		}
		int options = 100;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(CompressFormat.JPEG, options, baos);        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		size *= 1024;
		LogUtils.d("压缩前：" + baos.toByteArray().length);
		if (baos.toByteArray().length > size) {
			options = (size * 100) / baos.toByteArray().length;
			LogUtils.d("压缩比：" + options);
			baos.reset();
			image.compress(CompressFormat.JPEG, options, baos);
			LogUtils.d("压缩后：" + baos.toByteArray().length);
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}
	
	/**
	 * 图片按比例大小压缩方法（根据路径获取图片并压缩）
	 *
	 * @param srcPath
	 * @return
	 */
	public static Bitmap getimage(String srcPath) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
		
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 800f;// 这里设置高度为800f
		float ww = 480f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		return compressImage(bitmap, 150);// 压缩好比例大小后再进行质量压缩
	}
	
	/**
	 * 图片按比例大小压缩方法（根据Bitmap图片压缩）
	 *
	 * @param image
	 * @return
	 */
	public static Bitmap comp(Bitmap image) {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(CompressFormat.JPEG, 100, baos);
		if (baos.toByteArray().length / 1024 > 1024) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
			baos.reset();// 重置baos即清空baos
			image.compress(CompressFormat.JPEG, 30, baos);// 这里压缩50%，把压缩后的数据存放到baos中
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 800f;// 这里设置高度为800f
		float ww = 480f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		isBm = new ByteArrayInputStream(baos.toByteArray());
		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		return compressImage(bitmap, 150);// 压缩好比例大小后再进行质量压缩
	}
	
	public static String imgToBase64(Bitmap bitmap) {
		String base64Str = "";
		if (bitmap == null) {
			return base64Str;
		}
		
		ByteArrayOutputStream out = null;
		try {
			out = new ByteArrayOutputStream();
			bitmap.compress(CompressFormat.JPEG, 100, out);
			
			out.flush();
			out.close();
			
			byte[] imgBytes = out.toByteArray();
			base64Str = Base64.encodeToString(imgBytes, Base64.DEFAULT);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return base64Str.replaceAll("\n", "").replaceAll(" ", "");
			
		}
		
	}
	
	/**
	 * @param bitmap     原图
	 * @param edgeLength 希望得到的正方形部分的边长
	 * @return 缩放截取正中部分后的位图。
	 */
	public static Bitmap centerSquareScaleBitmap(Bitmap bitmap, int edgeLength) {
		if (null == bitmap || edgeLength <= 0) {
			return null;
		}
		
		Bitmap result = bitmap;
		int widthOrg = bitmap.getWidth();
		int heightOrg = bitmap.getHeight();
		
		if (widthOrg > edgeLength && heightOrg > edgeLength) {
			// 压缩到一个最小长度是edgeLength的bitmap
			int longerEdge = (int) (edgeLength * Math.max(widthOrg, heightOrg) / Math.min(widthOrg,
					heightOrg));
			int scaledWidth = widthOrg > heightOrg ? longerEdge : edgeLength;
			int scaledHeight = widthOrg > heightOrg ? edgeLength : longerEdge;
			Bitmap scaledBitmap;
			
			try {
				scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);
			} catch (Exception e) {
				return null;
			}
			
			// 从图中截取正中间的正方形部分。
			int xTopLeft = (scaledWidth - edgeLength) / 2;
			int yTopLeft = (scaledHeight - edgeLength) / 2;
			
			try {
				result = Bitmap.createBitmap(scaledBitmap, xTopLeft, yTopLeft, edgeLength,
						edgeLength);
				scaledBitmap.recycle();
			} catch (Exception e) {
				return null;
			}
		}
		
		return result;
	}
	
	public static Bitmap createBitmap(Bitmap src, Bitmap watermark) {
		if (src == null) {
			return null;
		}
		
		int w = src.getWidth();
		int h = src.getHeight();
		int ww = watermark.getWidth();
		BigDecimal rate = new BigDecimal(w).divide(new BigDecimal(ww), 2, BigDecimal.ROUND_FLOOR);
		int wh = new BigDecimal(watermark.getHeight()).multiply(rate).intValue();
		watermark = ThumbnailUtils.extractThumbnail(watermark, w, wh);
		Bitmap newb = Bitmap.createBitmap(w, h, Config.RGB_565);//创建一个新的和SRC长度宽度一样的位图
		Canvas cv = new Canvas(newb);
		cv.drawBitmap(src, 0, 0, null);                        //在 0，0坐标开始画入src
		cv.drawBitmap(watermark, 0, h - wh, null);            //在src的右下角画入水印
		cv.save(Canvas.ALL_SAVE_FLAG);                        //保存
		cv.restore();                                        //存储
		return newb;
	}
	
	public static Bitmap mergeBitmap(Activity activity, Bitmap ewmBitmap, Bitmap srcBitmap) {
		int w = srcBitmap.getWidth();
		int ww = ewmBitmap.getWidth();
		BigDecimal rate = new BigDecimal(w).divide(new BigDecimal(ww), 2, BigDecimal.ROUND_FLOOR);
		int wh = new BigDecimal(ewmBitmap.getHeight()).multiply(rate).intValue();
		ewmBitmap = ThumbnailUtils.extractThumbnail(ewmBitmap, w, wh);
		
		int statuBarHeight = getBarHeight(activity);
		int height = ewmBitmap.getHeight() + srcBitmap.getHeight() - statuBarHeight;
		Bitmap bitmap = Bitmap.createBitmap(
				srcBitmap.getWidth(), height, Config.RGB_565
		);
		Canvas canvas = new Canvas(bitmap);
		canvas.drawBitmap(srcBitmap, 0, ewmBitmap.getHeight() - statuBarHeight, null);
		canvas.drawBitmap(ewmBitmap, 0, 0, null);
		canvas.save(Canvas.ALL_SAVE_FLAG);                        //保存
		canvas.restore();                                        //存储
		if (!ewmBitmap.isRecycled()) {
			ewmBitmap.recycle();
			ewmBitmap = null;
		}
		
		if (!srcBitmap.isRecycled()) {
			srcBitmap.recycle();
			srcBitmap = null;
		}
		
		bitmap = BitmapUtil.compressImage(bitmap, 150);
		return bitmap;
	}
	
	public static int getBarHeight(Activity activity) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 38;//默认为38，貌似大部分是这样的
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = activity.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return sbar;
	}
	
	public static String compressImageToLocal(String src) {
		Bitmap bm = getimage(src);
		File dir = new File(Environment.getExternalStorageDirectory() + "/" + BASE_PATH);
		if (!dir.exists()) dir.mkdirs();
		File file = new File(dir, System.currentTimeMillis() + ".jpg");
		try {
			if (!file.exists()) file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			bm.compress(CompressFormat.JPEG, 100, fos);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file.getAbsolutePath();
	}
	
	//使用Bitmap加Matrix来缩放
	public static Bitmap zoomImage(Bitmap bitmap, int w, int h) {
		Bitmap BitmapOrg = bitmap;
		int width = BitmapOrg.getWidth();
		int height = BitmapOrg.getHeight();
		int newWidth = w;
		int newHeight = h;
		
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
				height, matrix, true);
		return resizedBitmap;
	}
	
}