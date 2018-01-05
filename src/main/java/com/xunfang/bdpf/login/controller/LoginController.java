package com.xunfang.bdpf.login.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunfang.bdpf.login.service.ServiceFactory;
import com.xunfang.bdpf.system.competence.entity.Competence;
import com.xunfang.bdpf.system.competence.service.CompetenceService;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.bdpf.system.user.entity.UserExample;
import com.xunfang.bdpf.system.user.service.UserService;
import com.xunfang.utils.ConsJson;
import com.xunfang.utils.HttpUtils;
import com.xunfang.utils.PropertiesUtil;
/**
 * 
 * @ClassName LoginController
 * @Description: 登录控制层
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年5月25日 下午5:46:10
 * @version V1.0
 */
@Controller
@RequestMapping(value = "login")
public class LoginController {
	@Autowired
	//用户信息接口
	private UserService userService;
	//权限管理接口
	@Autowired
	private CompetenceService competenceService;
	/**
	 * @Fields log : 输出日志
	 */
	private static Logger log = Logger.getLogger(LoginController.class);

	/**
	 * 
	  * @Title: index
	  * @Description: 跳转登录页面
	  * @param @param request
	  * @param @param session
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "")
	public String index(HttpServletRequest request, HttpSession session) {
		return "login";
	}

	/**
	 * 
	  * @Title: check
	  * @Description: 执行登录操作并保存数据
	  * @param @param username    用户名
	  * @param @param password    密码 
	  * @param @param session     session
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "check")
	@ResponseBody
	public String check(String username, String password, HttpSession session) {
		String result = "";//返回结果
		//条件判断，如果用户名和密码都对，就保存数据到session中
		UserExample example = new UserExample();
		UserExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andAccountEqualTo(username);
		createCriteria.andPasswordEqualTo(password);
		try {
			// 根据UserExample对象，查询数据
			List<User> list = userService.selectByExample(example);
			if(list.size()>0){
				User user = list.get(0);
				if(user.getUserType()==0){
					user.setName(user.getName()+"老师");
				}else if(user.getUserType()==1){
					user.setName(user.getName()+"同学");
				}
				//将当前登录的用户信息存到session中
				session.setAttribute("loginuser", user);
				Map<String, Object>  tokenMap = ServiceFactory.verification(PropertiesUtil.getValue("ACCOUNT"), PropertiesUtil.getValue("PASSWORD"));
				if(tokenMap==null){
					return "0";
				}else{
					String token = (String)tokenMap.get("token");
					session.setAttribute("token", token);
				}
				//获取当前用户所有的权限集合
				List<Competence> menulist = competenceService.selcetCompListByAccount(user.getAccount());
				session.setAttribute("menulist", menulist);
				//按功能模块保存对应的二级菜单
				LinkedHashMap<Competence,List<Competence>> menuMap = new LinkedHashMap<Competence,List<Competence>>();
				List<Competence> modellist = new ArrayList<Competence>();
				for (Competence menu : menulist) {//遍历所有菜单
					if("".equals(menu.getParentId())||menu.getParentId() == null){//一级菜单
						modellist.add(menu);
					}
				}
				for (Competence model : modellist) {//遍历一级菜单
					List<Competence> menulistnew = new ArrayList<Competence>();
					for (Competence menu : menulist) {//遍历二级菜单 
						if(menu.getParentId().equals(model.getResCode())){//分类添加数据。
							menulistnew.add(menu);
						}
					}
					//一级菜单所对应的二级菜单，组装数据。
					menuMap.put(model, menulistnew);
				}
				session.setAttribute("menuMap", menuMap);
				
				if(user.getUserType()==0){//教师
					result = "1";
				}else{//学生
					result = "2";
				}
				//从第几条开始取数据
				session.setAttribute("pageNo", PropertiesUtil.getValue("page.pageNo"));
				//每页显示多少条数据
				session.setAttribute("pageSize", PropertiesUtil.getValue("page.pageSize"));
			}else{
				result = "0";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 
	  * @Title: loginout
	  * @Description: 退出登录
	  * @param @param session
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "loginout")
	public String loginout(HttpSession session) {
		//调用在线考试保存接口
		User user = (User)session.getAttribute("loginuser");
		if(user==null){
			return "login";
		}
		
//		String sid = (String)session.getAttribute("sid");
//		String urls = PropertiesUtil.getValue("ITC_ADDRESS")+"/without/logout/ictilearning";
//		String pamars = "loginName="+user.getName().substring(0, user.getName().length()-2)
//		+"&userType="+user.getUserType()+"&sid="+sid;
//		
//		String results = HttpUtils.sendPost(urls, pamars);
		
		//移除所有的数据
		session.removeAttribute("loginuser");
		session.removeAttribute("menulist");
		session.removeAttribute("menuMap");
		session.removeAttribute("pageNo");
		session.removeAttribute("pageSize");
		session.removeAttribute("itcAddress");
		session.removeAttribute("sid");
		
		return "login";
	}
	/**
	 * 
	  * @Title: nopermission
	  * @Description: 无页面数据显示 ，错误页显示 
	  * @param @param session
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "nopermission")
	public String nopermission(HttpSession session) {
		return "error/403";
	}
}