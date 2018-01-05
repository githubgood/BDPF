package com.xunfang.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.xunfang.bdpf.system.user.entity.User;

/**
 * @ClassName LoginListenner
 * @Description: 登录监听类-处理同一时间只允许账号，单地点登录
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年6月6日 下午3:40:31
 * @version V1.0
 */
public class LoginListenner implements HttpSessionAttributeListener {
	/**
	 * 用于存放账号和session对应关系的map
	 */
	private Map<String, HttpSession> map = new HashMap<String, HttpSession>();

	/**
	 * 当向session中放入数据触发
	 */
	public void attributeAdded(HttpSessionBindingEvent event) {
		String name = event.getName();
		if (name.equals("loginuser")) {
			User user = (User) event.getValue();
			if (map.get(user.getAccount()) != null) {
				HttpSession session = map.get(user.getAccount());
				session.removeAttribute(user.getAccount());
				session.invalidate();
			}
			map.put(user.getAccount(), event.getSession());
		}
	}

	/**
	 * 当向session中移除数据触发
	 */
	public void attributeRemoved(HttpSessionBindingEvent event) {
		String name = event.getName();
		if (name.equals("loginuser")) {
			User user = (User) event.getValue();
			map.remove(user.getAccount());
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {

	}

	public Map<String, HttpSession> getMap() {
		return map;
	}

	public void setMap(Map<String, HttpSession> map) {
		this.map = map;
	}
}