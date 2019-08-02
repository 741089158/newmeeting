package com.cc.util;

import java.util.UUID;

/**
 * 主键生成器
 */
public class PKGenerate {

	public static String generateUUID() {
		UUID u = UUID.randomUUID();
		String uStr = u.toString();
		return uStr.replace("-", "");
	}

	public static void main(String[] args) {
		System.out.println(PKGenerate.generateUUID());
	}
}
