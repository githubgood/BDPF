package com.xunfang.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ConsJson {
	/**
	 * 将JavaBean转换成JSONObject（通过Map中转）
	 * 
	 * @param bean
	 *            javaBean
	 * @return json对象
	 */
	public static JSONObject toJSON(Object bean) {
		return new JSONObject(toMap(bean));
	}

	/**
	 * 将Json对象转换成Map
	 * 
	 * @param jsonObject
	 *            json对象
	 * @return Map对象
	 * @throws JSONException
	 */
	public static Map toMap(String jsonString) throws JSONException {
		JSONObject jsonObject = new JSONObject(jsonString);
		Map result = new HashMap();
		Iterator iterator = jsonObject.keys();
		String key = null;
		String value = null;
		while (iterator.hasNext()) {
			key = (String) iterator.next();
			value = jsonObject.getString(key);
			result.put(key, value);
		}
		return result;

	}

	/**
	 * 将Javabean转换为Map
	 * 
	 * @param javaBean
	 *            javaBean
	 * @return Map对象
	 */
	public static Map toMap(Object javaBean) {
		Map result = new HashMap();
		Method[] methods = javaBean.getClass().getDeclaredMethods();
		for (Method method : methods) {
			try {
				if (method.getName().startsWith("get")) {
					String field = method.getName();
					field = field.substring(field.indexOf("get") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);
					Object value = method.invoke(javaBean, (Object[]) null);
					result.put(field, null == value ? "" : value.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
