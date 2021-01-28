package com.cy.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.UUID;

public class SecretUtil {
	private static String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
			"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z"};

	/**
	 * 获取32位的UUID并去掉中间的-
	 *
	 * @return
	 */
	public static String createUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 生成6位随机数
	 *
	 * @return
	 */
	public static String randomCode() {
		// 随机产生6位数字
		int intCount = 0;
		intCount = (new Random()).nextInt(999999);// 最大值位999999
		if (intCount < 100000) {
			intCount += 100000; // 最小值位100001
		}
		return intCount + "";
	}

	/**
	 * 根据传入pwd和私有salt（盐） 生成PWD
	 *
	 * @param rawPass 明文密码
	 * @return
	 */
	public static String getMd5Pwd(String rawPass) {
		return getMd5Pwd(rawPass, null);
	}

	/**
	 * 根据传入pwd和私有salt（盐） 生成PWD
	 *
	 * @param rawPass 明文密码
	 * @param salt    盐（可空）
	 * @return
	 */
	public static String getMd5Pwd(String rawPass, Object salt) {
		if (StringUtils.isBlank(rawPass)) {
			return null;
		}
		return new Md5Password().encodePassword(rawPass, salt);
	}

	/**
	 * 校验密码是否匹配
	 *
	 * @param encPass 密文密码
	 * @param rawPass 明文密码
	 * @return
	 */
	public static boolean checkPassword(String encPass, String rawPass) {
		return checkPassword(encPass, rawPass, null);
	}

	/**
	 * 校验密码是否匹配
	 *
	 * @param encPass 密文密码
	 * @param rawPass 明文密码
	 * @param salt    盐（可空）
	 * @return
	 */
	public static boolean checkPassword(String encPass, String rawPass, Object salt) {
		if (StringUtils.isBlank(encPass) || StringUtils.isBlank(rawPass)) {
			return false;
		}
		return new Md5Password().isPasswordValid(encPass, rawPass, salt);
	}

	/**
	 * 创建流水号，采用时间格式精确到毫秒 + 8位UUID
	 *
	 * @return
	 */
	public static String createFlow() {
		return DateUtils.getNow("yyyyMMddHHmmssSSS") + generateShortUuid();
	}

	/**
	 * 生成UUID（8位）
	 *
	 * @return UUID
	 */
	public static String generateShortUuid() {
		StringBuilder shortBuffer = new StringBuilder();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();
	}

	/**
	 * 去除uri上重复的斜杠
	 *
	 * @param str 带斜杠的字符串
	 */
	public static String slashRemoveRepeat(String str) {
		if (str == null) {
			return null;
		}
		String[] arr = str.split("/");
		StringBuilder sb = new StringBuilder();
		for (String s : arr) {
			if (StringUtils.isNotBlank(s)) {
				sb.append("/").append(s);
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(getMd5Pwd("123456", "PyxNLEcB"));
	}
}
