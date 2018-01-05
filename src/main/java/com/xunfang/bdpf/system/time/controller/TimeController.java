package com.xunfang.bdpf.system.time.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunfang.bdpf.system.time.entity.Time;
import com.xunfang.bdpf.system.time.service.TimeService;
import com.xunfang.utils.Feedback;
/**
 * 
 * @ClassName TimeController
 * @Description: TODO(用一句话描述该文件做什么)
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年6月1日 下午3:37:04
 * @version V1.0
 */
@Controller
@RequestMapping(value = "time")
public class TimeController {
	
	@Autowired
	//实验时间设置服务接口
	private TimeService timeService;
	
	/**
	 * 表数据id 固定值
	 */
	private String id = "1";
	/**
	 * @Fields log : 输出日志
	 */
	private static Logger log = Logger.getLogger(TimeController.class);

	/**
	 * 
	  * @Title: index
	  * @Description: 用户管理首页
	  * @param @param request
	  * @param @param session
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "")
	public String index(HttpServletRequest request,Model model, HttpSession session) {
		Time time = timeService.selectByPrimaryKey(id);
		model.addAttribute("time", time);
		return "system/time/index";

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
	public Feedback save(Time time, HttpSession session) {
		String timeId;//角色id
		Feedback feedback = null;// 返回值
		try {
			if (!"".equals(time.getId())) {// 更新数据
				timeId = time.getId();
				//根据用户id更新数据
				timeService.updateByPrimaryKey(time);
			} else {// 新增数据
				time.setId(id);
				// 保存数据
				timeService.insert(time);
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
