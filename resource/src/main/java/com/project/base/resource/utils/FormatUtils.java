package com.project.base.resource.utils;

import com.project.base.resource.basic.BasicConst;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatUtils {
	
	private FormatUtils() {
	}
	
	/**
	 * 格式化金额
	 *
	 * @param money 金额
	 * @return 格式化金额
	 */
	public static String formatMoney(String money) {
		return formatDecimal(money, BasicConst.MONEY_FORMAT);
	}
	
	/**
	 * 格式化手机号码
	 *
	 * @param mobile 手机号码
	 * @return 格式化手机号码
	 */
	public static String formatMobile(String mobile) {
		try {
			return mobile.replaceAll("(\\d{3})\\d*(\\d{4})", "$1****$2");
		} catch (Exception ignored) {
		}
		return mobile;
	}
	
	/**
	 * 格式化距离
	 *
	 * @param distance 距离
	 * @return 格式化距离
	 */
	public static String formatDistance(String distance) {
		double distanceNumber = Double.parseDouble(distance);
		try {
			if (distanceNumber > 1000) {
				return String.format(Locale.CHINA, "%.2fkm", distanceNumber / 1000);
			}
			return String.format(Locale.CHINA, "%.2fm", distanceNumber);
		} catch (Exception ignored) {
		}
		return distance;
	}
	
	private static SimpleDateFormat FULL_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
	private static SimpleDateFormat RECENT_FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss", Locale.CHINA);
	
	/**
	 * 格式化最近时间段
	 *
	 * @param time 时间格式
	 * @return 最近时间段
	 */
	public static String formatRecentTime(String time) {
		try {
			Date date = FULL_FORMAT.parse(time);
			return RECENT_FORMAT.format(date);
		} catch (Exception ignored) {
		}
		return time;
	}
	
	/**
	 * 格式化银行卡账号
	 *
	 * @param bankCardNumber 银行卡账号
	 * @return 银行卡账号
	 */
	public static String formatBankCardNumber(String bankCardNumber) {
		int length = bankCardNumber != null ? bankCardNumber.length() : 0;
		if (length > 4) {
			return "**** **** **** " + bankCardNumber.substring(length - 4, length);
		}
		return "**** **** **** " + bankCardNumber;
	}
	
	/**
	 * 格式数字
	 *
	 * @param number 数字
	 * @return 格式化后字符串
	 */
	public static String formatDecimal(double number, String pattern) {
		return formatDecimal(String.valueOf(number), pattern, RoundingMode.DOWN);
	}
	
	/**
	 * 格式数字
	 *
	 * @param number 数字
	 * @return 格式化后字符串
	 */
	public static String formatDecimal(String number, String pattern) {
		return formatDecimal(number, pattern, RoundingMode.DOWN);
	}
	
	/**
	 * 格式数字
	 *
	 * @param number 数字
	 * @return 格式化后字符串
	 */
	public static String formatDecimal(double number, String pattern, RoundingMode roundingMode) {
		return formatDecimal(String.valueOf(number), pattern, roundingMode);
	}
	
	/**
	 * 格式数字
	 *
	 * @param number 数字
	 * @return 格式化后字符串
	 */
	public static String formatDecimal(String number, String pattern, RoundingMode roundingMode) {
		try {
			DecimalFormat format = new DecimalFormat(pattern);
			format.setRoundingMode(roundingMode);
			return format.format(new BigDecimal(number));
		} catch (Exception e) {
			return number;
		}
	}
	
	public static double parseDecimal(String text, String pattern) {
		DecimalFormat format = new DecimalFormat(pattern);
		try {
			return format.parse(text).doubleValue();
		} catch (Exception ignored) {
		}
		return 0;
	}
}
