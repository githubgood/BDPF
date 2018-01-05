package com.xunfang.bdpf.experiment.grade.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunfang.bdpf.experiment.clas.service.ClasService;
import com.xunfang.bdpf.experiment.grade.entity.Grade;
import com.xunfang.bdpf.experiment.grade.service.GradeService;
import com.xunfang.bdpf.experiment.task.service.TaskService;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.utils.Page;
import com.xunfang.utils.PropertiesUtil;

/**
 * 
 * @ClassName GradeController
 * @Description: 成绩查询控制器
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月7日 上午10:27:48
 * @version V1.0
 */
@Controller
@RequestMapping(value = "grade")
public class GradeController {
	/**
	 * @Fields log : 输出日志
	 * 
	 */
	private static Logger log = Logger.getLogger(GradeController.class);

	@Autowired
	//课程资源管理Service接口
	private GradeService gradeService;
	
	@Autowired
	//课程资源管理Service接口
	private TaskService taskService;
	
	@Autowired
	//班级Service接口
	private ClasService clasService;
	

	/**
	 * 
	  * @Title: task
	  * @Description: 成绩查询跳转方法，进入查询页
	  *  @param request HttpServletRequest http请求
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "")
	public String task(Model model,HttpServletRequest request, HttpSession session) {
		//获取所有的任务信息
		String account = null;
		User user = (User)session.getAttribute("loginuser");
		if(user == null){
			return "login/login";
		}
		if(user.getUserType()!=2){
			account = user.getAccount();
		}
	    model.addAttribute("clas", clasService.queryClassUser(account));
		model.addAttribute("task", taskService.selectTaskByAccount(account));
		return "experiment/grade/index";
	}


	/**
	 * 
	  * @Title: listCourseByPage
	  * @Description: 课程资源管理查询方法，带分页
	  *  @param keywords String 课程资源名称
	  *  @param pageNo String 从第几条开始取数据
	  *  @param pageSize String 每页显示多少条数据
	  *  @return  Page<Course> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Page<Grade> listCourseByPage(String taskId,String classId, String pageNo, String pageSize,HttpSession session) {
		String studentId="";
		String teacherId=null;
		User user=(User) session.getAttribute("loginuser");
		if(user == null){
			return null;
		}
		if(user.getUserType()==1){
			classId=user.getClassId();
			studentId=user.getAccount();
		}else if(user.getUserType()==0){
			teacherId=user.getAccount();
		}
		int skip = Integer.parseInt(PropertiesUtil.getValue("page.pageNo"));//从第几条开始取数据
		int max = Integer.parseInt(PropertiesUtil.getValue("page.pageSize"));//每页显示多少条数据
		Page<Grade> page = new Page<Grade>();//Page类
		List<Grade> list = new ArrayList<Grade>();//课程资源列表
		if (StringUtils.isNotBlank(pageSize)) {
			max = Integer.parseInt(pageSize);
		}
		if (StringUtils.isNotBlank(pageNo)) {
			skip = max * (Integer.parseInt(pageNo) - 1);// mysql中记录从0开始
			page.setPageNo(Integer.parseInt(pageNo));
		} else {
			page.setPageNo(1);
		}
		try {
			page.setTotalCount(gradeService.getGradeCount(taskId,classId,studentId,teacherId).size());//根据查询条件获取课程资源总条数
			list = gradeService.queryGradeByPage(taskId,classId,studentId,teacherId, skip, max);//根据查询条件获取课程资源列表
			page.setResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return page;
	}
	
	/**
	 * 
	  * @Title: toDetail
	  * @Description: 跳转详情界面
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-detail")
	public String toDetail() {
		return "experiment/grade/detail";
	}

	/**
	 * 
	  * @Title: queryDetailByPage
	  * @Description: 查询成绩详情
	  *  @param taskId
	  *  @param classId
	  *  @param studentId
	  *  @param pageNo
	  *  @param pageSize
	  *  @return  Page<Task> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "detail")
	@ResponseBody
	public Page<Grade> queryDetailByPage(String taskId, String classId,String studentId,String pageNo, String pageSize) {
		int skip = Integer.parseInt(PropertiesUtil.getValue("page.pageNo"));//从第几条开始取数据
		int max = Integer.parseInt(PropertiesUtil.getValue("page.pageSize"));//每页显示多少条数据
		Page<Grade> page = new Page<Grade>();//Page类
		List<Grade> list = new ArrayList<Grade>();//课程资源列表
		if (StringUtils.isNotBlank(pageSize)) {
			max = Integer.parseInt(pageSize);
		}
		if (StringUtils.isNotBlank(pageNo)) {
			skip = max * (Integer.parseInt(pageNo) - 1);// mysql中记录从0开始
			page.setPageNo(Integer.parseInt(pageNo));
		} else {
			page.setPageNo(1);
		}
		try {
			page.setTotalCount(gradeService.getDetailCount(taskId,classId,studentId).size());//根据查询条件获取课程资源总条数
			list = gradeService.queryDetailByPage(taskId,classId,studentId, skip, max);//根据查询条件获取课程资源列表
			page.setResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return page;
	}
}