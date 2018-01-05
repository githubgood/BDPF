package com.xunfang.bdpf.mllib.experiment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName HomePageController
 * @Description: 机器学习首页控制器
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月26日 下午3:56:52
 * @version V1.0
 */
@Controller
@RequestMapping(value = "homepage")
public class HomePageController {

	/**
	 * 
	  * @Title: exp
	  * @Description: 班级管理管理跳转方法，进入查询页
	  *  @param request HttpServletRequest http请求
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "")
	public String homepage(HttpServletRequest request, HttpSession session) {
		return "/mllib/homepage/index";
	}

}