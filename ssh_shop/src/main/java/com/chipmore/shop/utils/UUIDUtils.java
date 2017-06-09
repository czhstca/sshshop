package com.chipmore.shop.utils;

import java.util.UUID;

/**
 * 生成随机32位字符串的工具类
 * @author SHAWN
 *
 */
public class UUIDUtils {

	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
