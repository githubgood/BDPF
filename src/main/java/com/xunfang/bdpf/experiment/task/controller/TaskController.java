package com.xunfang.bdpf.experiment.task.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xunfang.bdpf.experiment.clas.service.ClasService;
import com.xunfang.bdpf.experiment.grade.entity.StudentVirtualExample.Criteria;
import com.xunfang.bdpf.experiment.grade.entity.StudentVirtual;
import com.xunfang.bdpf.experiment.grade.entity.StudentVirtualExample;
import com.xunfang.bdpf.experiment.grade.service.StudentVirtualService;
import com.xunfang.bdpf.experiment.plan.entity.PlanExample;
import com.xunfang.bdpf.experiment.plan.entity.PlanLine;
import com.xunfang.bdpf.experiment.plan.service.PlanService;
import com.xunfang.bdpf.experiment.task.entity.Task;
import com.xunfang.bdpf.experiment.task.entity.TaskExample;
import com.xunfang.bdpf.experiment.task.service.TaskService;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.bdpf.system.user.entity.UserExample;
import com.xunfang.bdpf.system.user.service.UserService;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.OpenStackApi;
import com.xunfang.utils.Page;
import com.xunfang.utils.PropertiesUtil;

/**
 * 
 * @ClassName TaskController
 * @Description: 课程发布控制器
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月7日 上午10:27:48
 * @version V1.0
 */
@Controller
@RequestMapping(value = "task")
public class TaskController {
	/**
	 * @Fields log : 输出日志
	 * 
	 */
	private static Logger log = Logger.getLogger(TaskController.class);

	@Autowired
	//课程资源管理Service接口
	private TaskService taskService;
	
	@Autowired
	//课程计划Service接口
	private PlanService planService;
	
	@Autowired
	//班级管理Service接口
	private ClasService clasService;
	
	@Autowired
	//班级管理Service接口
	private UserService userService;
	
	@Autowired
	//班级管理Service接口
	private StudentVirtualService studentVirtualService;
	
	/**
	 * 
	  * @Title: exp
	  * @Description: 课程发布跳转方法，进入查询页
	  *  @param request HttpServletRequest http请求
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "")
	public String exp(HttpServletRequest request, HttpSession session) {
		return "experiment/task/index";
	}

	/**
	 * 
	  * @Title: toAdd
	  * @Description: 课程发布跳转方法，进入添加页
	  *  @param model Model 课程资源model
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-add")
	public String toAdd(Model model, HttpSession session) {
		PlanExample example=new PlanExample();
		User user = (User)session.getAttribute("loginuser");
		if(user == null){
			return "";
		}
		//获取课程资源
		model.addAttribute("plan", planService.selectByExample(example));
		String account = null;
		if(user.getUserType()!=2){
			account = user.getAccount();
		}
	    model.addAttribute("clas", clasService.queryClassUser(account));
		return "experiment/task/add";
	}

	/**
	 * 
	  * @Title: detail
	  * @Description: 课程发布跳转方法，进入修改页
	  *  @param id String 当前课程资源主键ID
	  *  @param model Model 课程资源model
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-detail")
	public String detail( String classId,String id, Model model) {
		model.addAttribute("classId", classId);
		model.addAttribute("id", id);
		return "experiment/task/detail";
	}
	
	/**
	 * 
	  * @Title: detail
	  * @Description: 课程发布详查询
	  *  @param className
	  *  @param id
	  *  @param pageNo
	  *  @param pageSize
	  *  @return  Page<Task> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "detail")
	@ResponseBody
	public Page<Task> detail(String classId, String id,String pageNo, String pageSize) {
		int skip = Integer.parseInt(PropertiesUtil.getValue("page.pageNo"));//从第几条开始取数据
		int max = Integer.parseInt(PropertiesUtil.getValue("page.pageSize"));//每页显示多少条数据
		Page<Task> page = new Page<Task>();//Page类
		List<Task> list = new ArrayList<Task>();//课程资源列表
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
			page.setTotalCount(taskService.getDetailCount(classId,id).size());//根据查询条件获取课程资源总条数
			list = taskService.queryDetailByPage(classId,id, skip, max);//根据查询条件获取课程资源列表
			page.setResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return page;
	}

	/**
	 * 
	  * @Title: listTaskByPage
	  * @Description: 课程资源管理查询方法，带分页
	  *  @param keywords String 课程资源名称
	  *  @param pageNo String 从第几条开始取数据
	  *  @param pageSize String 每页显示多少条数据
	  *  @return  Page<Course> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Page<Task> listTaskByPage(String keywords, String pageNo, String pageSize,HttpSession session) {
		int skip = Integer.parseInt(PropertiesUtil.getValue("page.pageNo"));//从第几条开始取数据
		int max = Integer.parseInt(PropertiesUtil.getValue("page.pageSize"));//每页显示多少条数据
		Page<Task> page = new Page<Task>();//Page类
		List<Task> list = new ArrayList<Task>();//课程资源列表
		if (StringUtils.isNotBlank(pageSize)) {
			max = Integer.parseInt(pageSize);
		}
		if (StringUtils.isNotBlank(pageNo)) {
			skip = max * (Integer.parseInt(pageNo) - 1);// mysql中记录从0开始
			page.setPageNo(Integer.parseInt(pageNo));
		} else {
			page.setPageNo(1);
		}
		//获取当前登录用户信息
		User  user = (User)session.getAttribute("loginuser");
		if(user == null){
			return page;
		}
		String account = null;
		if(user.getUserType() != 2){
			account = user.getAccount();
		}
		try {
			page.setTotalCount(taskService.getTaskCount(keywords,account));//根据查询条件获取课程资源总条数
			list = taskService.queryTaskByPage(keywords, skip, max,account);//根据查询条件获取课程资源列表
			page.setResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return page;
	}

	@RequestMapping(value = "queryCourse")
	@ResponseBody
	public String queryCourse(String teachingPlanId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("teachingPlanId", teachingPlanId);
		List<PlanLine> list=planService.queryPlanLineById(param);
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<list.size();i++){
			sb=sb.append(list.get(i).getCourseResourceType()+"-");		
		}
		return sb.toString();
	}

	/**
	 * 
	  * @Title: delete
	  * @Description: 课程发布管理删除方法
	  *  @param ids String 课程资源主键ID
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Feedback delete(String ids, HttpServletResponse response,HttpServletRequest request,HttpSession session) {
		Feedback feedback = null;
		String token = (String)session.getAttribute("token");
		if(token==null||"".equals(token)){
			feedback = new Feedback(false, "删除失败!");
			return feedback;
		}
		if (StringUtils.isBlank(ids)) {//课程资源主键ID判空
			feedback = new Feedback(false, "删除失败!");
			return feedback;
		}
		try {
			String[] idArr = ids.split(",");
			//批量删除
			//删除对应的虚拟机
			StringBuffer sb=new StringBuffer();
			StudentVirtualExample studentVirtualExample=new StudentVirtualExample();
			studentVirtualExample.createCriteria().andCourseTaskIdIn(Arrays.asList(idArr));
			List<StudentVirtual> listStudentVirtual=studentVirtualService.selectByExample(studentVirtualExample);
			for(StudentVirtual v:listStudentVirtual){
				if(v.getVirtualId()!=null&&v.getVirtualId().length()>0){
					sb.append(v.getVirtualId()+",");
				}
			}
			if(sb.length()>0){
				sb=sb.deleteCharAt(sb.length()-1);
			}
		     OpenStackApi.batchServerDel(sb.toString(),token);
		     StudentVirtualExample studentVirtual =new StudentVirtualExample();
		     Criteria createCriteria = studentVirtual.createCriteria();
			 createCriteria.andVirtualIdIn(Arrays.asList(ids));
			 createCriteria.andTypeNotEqualTo(2);//删除不为开放性实验
		     studentVirtualService.deleteByExample(studentVirtual);
			 taskService.deleteByExample(Arrays.asList(idArr));//批量删除课程资源管理信息
			feedback = new Feedback(true, "删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			feedback = new Feedback(false, "删除失败");
		}
		return feedback;
	}

	/**
	 * 
	  * @Title: upload
	  * @Description: 文件上传以及课程任务保存
	  *  @param file MultipartFile 上传的文件流
	  *  @param task Task 课程实验任务model
	  *  @param request  HttpServletRequest request请求
	  *  @param response HttpServletResponse response 响应
	  *  @param session HttpSession 缓存
	  *  @return
	  *  @throws Exception  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public Feedback upload(@RequestParam(value = "file", required = false) MultipartFile file,
			Task task,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		request.setCharacterEncoding("utf-8");
		Feedback feedback = null;
		try {
			User user=(User) session.getAttribute("loginuser");
			if(user == null){
		    	log.error("user error.");
		    	System.out.println("user error.");
				feedback = new Feedback(false, "保存失败!");
				return feedback;
			}
			String  token  = (String)session.getAttribute("token");//从session中获取token
			if(token==null||"".equals(token)){
		    	//log.error("token error.");
		    	//System.out.println("token error.");
				 feedback = new Feedback(false, "查找秘钥失败!");
				 return feedback;
			}
		    task.setTeacherId(user.getAccount());
		    boolean save = taskService.save(task,file,token,user);//保存
		    if(save){
		    	feedback = new Feedback(true, "保存成功！");
		    }else{
		    	log.error("save error.");
		    	System.out.println("save error.");
		    	feedback = new Feedback(false, "保存虚拟机失败！");
		    }
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			feedback = new Feedback(false, "保存失败！");
	 }
		return feedback;
	}
	
	/**
	 * 
	  * @Title: checkTaskName
	  * @Description: 任务名称查询是否重复的方法
	  *  @param name String 任务名称
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value="checkTaskName", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkTaskName(String id,String name){
		boolean falg = true;
		if(id.equals("undefined")||"".equals(id)){//任务主键ID判空已经未定义判断
			id = "";
		}
		try {
			//根据班级主键和班级代码查询是否存在重复的课程班级代码
			TaskExample example = new TaskExample();
			example.createCriteria().andNameEqualTo(name).andIdNotEqualTo(id);
			 List<Task> tasklist = taskService.selectByExample(example);
			if(tasklist.size()==0){
				falg = true;
			}else{
				falg = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return falg;
	}
	
	@RequestMapping(value="checkGroupNum", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkGroupNum(String classId, int groupNum ){
		boolean falg = true;
		try {
			//根据班级验证小组人数
			if(classId.length()==0){
				 falg = true;
				 return falg;
			}
			String [] str=classId.split(",");
			UserExample userExample=new UserExample();
			for(int i=0;i<str.length;i++){
			userExample.createCriteria().andClassIdEqualTo(str[i]);
			List<User> listUser=userService.selectByExample(userExample);
			if(listUser.size()>=groupNum){
				falg = true;
			}else{
				falg = false;
				break;
			  }
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return falg;
	}
}