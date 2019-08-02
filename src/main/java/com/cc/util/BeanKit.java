package com.cc.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class BeanKit {

	public static Map<String, Object> bean2map(Object bean) {
		Map<String, Object> result = null;
		try {
			result = new HashMap<>(BeanUtils.describe(bean));
		} catch (Exception e) {
			System.out.println("BEAN 转换失败 ！！！");
			result = new HashMap<>();
		}
		return result;
	}

	public static <T> T map2bean(Map<String, Object> target, Class<T> clazz) {
		T result = null;
		try {
			result = clazz.newInstance();
			BeanUtils.populate(result, target);
		} catch (Exception e) {
			System.out.println("Map 转换失败 ！！！");
		}
		return result;
	}

}
