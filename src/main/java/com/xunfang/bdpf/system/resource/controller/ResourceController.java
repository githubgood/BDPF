package com.xunfang.bdpf.system.resource.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunfang.bdpf.system.resource.entity.Resource;
import com.xunfang.bdpf.system.resource.service.ResourceService;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.Identities;

/**
 * 
 * @ClassName ResourceController
 * @Description: 实验资源设置控制层
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年7月20日 下午4:58:23
 * @version V1.0
 */
@Controller
@RequestMapping(value = "resource")
public class ResourceController {
	@Autowired
	//实验资源设置服务接口
	private ResourceService resourceService;
	/**
	 * 表数据id 固定值
	 */
	/**
	 * @Fields log : 输出日志
	 */
	private static Logger log = Logger.getLogger(ResourceController.class);
	private String id = "1";
	/**
	 * 
	  * @Title: index
	  * @Description: 打开资源比例设置页面
	  * @param @param request
	  * @param @param model
	  * @param @param session
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "")
	public String index(HttpServletRequest request,Model model, HttpSession session) {
		//获取资源比例设置对象并显示在页面上
		Resource resource = resourceService.selectByPrimaryKey(id);
		model.addAttribute("resource", resource);
		return "system/resource/index";
		
	}
	/**
	 * 
	  * @Title: save
	  * @Description: 保存数据
	  * @param @param time
	  * @param @param session
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Feedback save(Resource resource, HttpSession session) {
		Feedback feedback = null;// 返回值
		try {
			if (!"".equals(resource.getId())) {// 更新数据
				//根据用户id更新数据
				resourceService.updateByPrimaryKey(resource);
			} else {// 新增数据
				resource.setId(id);
				// 保存数据
				resourceService.insert(resource);
			}
			feedback = new Feedback(true, "保存成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
			feedback = new Feedback(true, "保存失败！");
		}
		return feedback;
	}
	
}
