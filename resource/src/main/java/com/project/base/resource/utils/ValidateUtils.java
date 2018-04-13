package com.project.base.resource.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {
	
	private ValidateUtils() {
		throw new RuntimeException("can't instantiate");
	}
	
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	public static boolean notEmpty(String str) {
		return !isEmpty(str);
	}
	
	public static boolean isMobile(String mobile) {
		return notEmpty(mobile) && mobile.startsWith("1") && mobile.length() == 11;
	}
	
	public static boolean isSizeOf(String str, int length) {
		return str != null && str.length() == length;
	}
	
	public static boolean isEquals(String str1, String str2) {
		// noinspection StringEquality
		return str1 == str2 || str1.equals(str2);
	}
	
	public static boolean notEquals(String str1, String str2) {
		return !isEquals(str1, str2);
	}
	
	public static boolean isPassword(String password) {
		return password != null && password.length() >= 6;
	}
	
	public static boolean notPassword(String password) {
		return !isPassword(password);
	}
	
	/**
	 * 匹配中文、英文字母和数字
	 *
	 * @param str 匹配字符串
	 * @return true匹配
	 */
	public static boolean isCombination(String str) {
		Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fa5_a-zA-Z0-9]+$");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 判断银行卡号
	 *
	 * @param cardNumber 银行卡号
	 * @return true正确的银行卡号
	 */
	public static boolean isBankCardNumber(String cardNumber) {
		return cardNumber.length() >= 16 && cardNumber.length() <= 19;
	}
	
	public static boolean notIdentityCardNumber(String identityCardNumber) {
		return !isIdentityCardNumber(identityCardNumber);
	}
	
	/**
	 * 验证身份证号
	 *
	 * @param identityCardNumber 身份证号
	 * @return true正确
	 */
	public static boolean isIdentityCardNumber(String identityCardNumber) {
		String[] verifyCode = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
		String[] weight = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
		
		String pattern = "^\\d{15}(\\d{2}[0-9xX])?$";
		
		if (!identityCardNumber.matches(pattern)) {
			return false;
		}
		
		if (identityCardNumber.length() == 15) {
			return true;
		}
		
		int sum = 0;
		for (int index = 0; index < 17; index++) {
			sum = sum + Integer.parseInt(String.valueOf(identityCardNumber.charAt(index))) * Integer.parseInt(weight[index]);
		}
		
		return verifyCode[sum % 11].equalsIgnoreCase(identityCardNumber.substring(17));
	}
}
