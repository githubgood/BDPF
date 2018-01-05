package com.xunfang.bdpf.experiment.student.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.TextUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xunfang.bdpf.experiment.clas.entity.Clas;
import com.xunfang.bdpf.experiment.clas.entity.ClasExample;
import com.xunfang.bdpf.experiment.clas.service.ClasService;
import com.xunfang.bdpf.experiment.grade.entity.Grade;
import com.xunfang.bdpf.experiment.grade.entity.GradeExample;
import com.xunfang.bdpf.experiment.grade.entity.StudentVirtualExample;
import com.xunfang.bdpf.experiment.grade.service.GradeService;
import com.xunfang.bdpf.experiment.grade.service.StudentVirtualService;
import com.xunfang.bdpf.system.role.entity.Role;
import com.xunfang.bdpf.system.role.entity.RoleExample;
import com.xunfang.bdpf.system.role.service.RoleService;
import com.xunfang.bdpf.system.time.entity.Time;
import com.xunfang.bdpf.system.time.service.TimeService;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.bdpf.system.user.entity.UserExample;
import com.xunfang.bdpf.system.user.entity.UserRole;
import com.xunfang.bdpf.system.user.entity.UserRoleExample;
import com.xunfang.bdpf.system.user.service.UserRoleService;
import com.xunfang.bdpf.system.user.service.UserService;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.Identities;
import com.xunfang.utils.Page;
import com.xunfang.utils.PropertiesUtil;
/**
 * 
 * @ClassName StudentController
 * @Description: 学生管理控制层
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年5月31日 下午5:45:01
 * @version V1.0
 */
@Controller
@RequestMapping(value = "student")
public class StudentController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private ClasService clasService;
	@Autowired
	private RoleService  roleService;
	@Autowired
	private GradeService  gradeService;
	@Autowired
	//学生虚拟机关联Service接口
	private StudentVirtualService studentVirtualService;
	
	@Autowired
	//实验时间设置服务接口
	private TimeService timeService;
	//默认用户类型
	private final static int USER_TYPE = 1; 
	
	/**
	 * @Fields log : 输出日志
	 */
	private static Logger log = Logger.getLogger(StudentController.class);

	/**
	 * 
	  * @Title: index
	  * @Description: 管理首页
	  * @param @param request
	  * @param @param session
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "")
	public String index(HttpServletRequest request, HttpSession session) {
		return "experiment/student/index";

	}

	/**
	 * 
	  * @Title: listUserByPage
	  * @Description: 分页获取数据
	  * @param @param keywords
	  * @param @param pageNo
	  * @param @param pageSize
	  * @param @return    设定文件
	  * @return Page<User>    返回类型
	  * @throws
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Page<User> listUserByPage(String keywords, String pageNo, String pageSize,HttpSession session) {
		int skip = Integer.parseInt(PropertiesUtil.getValue("page.pageNo"));//从第几条开始取数据
		int max = Integer.parseInt(PropertiesUtil.getValue("page.pageSize"));//每页显示多少条数据
		Page<User> page = new Page<User>();
		List<User> list = new ArrayList<User>();
		if (StringUtils.isNotBlank(pageSize)) {
			max = Integer.parseInt(pageSize);
		}
		if (StringUtils.isNotBlank(pageNo)) {
			skip = max * (Integer.parseInt(pageNo) - 1);// mysql中记录从0开始
			page.setPageNo(Integer.parseInt(pageNo));//设置页码
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
			page.setTotalCount(userService.getStudentCount(keywords,account));//获取总条数
			list = userService.queryStudentByPage(keywords, skip, max,account);//获取分页数据，根据关键字查找
			page.setResult(list);
			page.setPageSize(max);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return page;
	}

	/**
	 * 
	  * @Title: toAdd
	  * @Description: 跳转添加页面
	  * @param @param model
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-add")
	public String toAdd(Model model, HttpSession session) {
		// 获取班级列表
		List<Clas> classList = new ArrayList<Clas>();
		User  user = (User) session.getAttribute("loginuser");
		if(user.getUserType() == 2){//管理员
			classList = clasService.selectByExample(null);
		}else if(user.getUserType() == 0){//老师
			ClasExample classExample = new ClasExample();
			classExample.createCriteria().andIdIn(Arrays.asList(user.getClassId().split(",")));
			classList = clasService.selectByExample(classExample);
		}
		model.addAttribute("classlist", classList);
		return "experiment/student/add";
	}
	
	/**
	 * 
	  * @Title: toImport
	  * @Description: 学生管理管理跳转方法，进入导入页
	  *  @param model Model 学生管理model
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-import")
	public String toImport(Model model, HttpSession session) {
		return "experiment/student/import";
	}

	/**
	 * 
	  * @Title: save
	  * @Description: 保存数据并跳转学生管理页面
	  *  @param user
	  *  @param session
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Feedback save(User user, HttpSession session) {
		Feedback feedback = null;
		String userId = null;// 班级id
		try {
			if (user.getId() != null && !"".equals(user.getId())) {// 更新数据
				userId = user.getId();
				// 根据班级id更新数据
				userService.updateByPrimaryKey(user);
			} else {// 新增数据
				int max = 0;
				//查询本系统最大的人数限制
				Time time = timeService.selectByPrimaryKey("1");
				String maxNumber = time.getMaxNumber();
				if(!TextUtils.isEmpty(maxNumber)){
					max = Integer.parseInt(maxNumber);
				}
				//如果大于最大人数，就提示不能增加用户，已达到最大。
				List<User> userList = userService.selectByExample(null);
				int size = userList.size();
				if(max == 0){
					//新增用户
					userId = Identities.uuid2();
					user.setId(userId);
					user.setPassword(PropertiesUtil.getValue("ADMINPASS"));
					user.setUserType(USER_TYPE);
					// 保存数据
					userService.insert(user);
				}else{
					if(max>size){
						//新增用户
						userId = Identities.uuid2();
						user.setId(userId);
						user.setPassword(PropertiesUtil.getValue("ADMINPASS"));
						user.setUserType(USER_TYPE);
						// 保存数据
						userService.insert(user);
					}else{
						feedback = new Feedback(false, "保存失败,已达到系统的最大用户数量！");
						return feedback;
					}
				}
			}
			// 对角色的关联操作
			UserRoleExample ure = new UserRoleExample();
			ure.createCriteria().andUserIdEqualTo(userId);
			// 先删除数据
			userRoleService.deleteByExample(ure);
			//根据角色名称查询角色列表
			RoleExample studentRole = new RoleExample();
			studentRole.createCriteria().andNameLike("学生");
			List<Role> roles = roleService.selectByExample(studentRole);
			//角色ID
			String roleid = "";
			if(roles!=null&&roles.size()>0){
				roleid = roles.get(0).getId();
			}
			//给当前学生信息建立学生对应的角色
			UserRole userRole = new UserRole();
			userRole.setId(Identities.uuid2());
			userRole.setUserId(userId);
			userRole.setRoleId(roleid);
			// 插入新数据
			userRoleService.insert(userRole);
			feedback = new Feedback(true, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			feedback = new Feedback(false, "保存失败！");
		}
		return feedback;
	}

	/**
	 * 
	  * @Title: checkAccount
	  * @Description: 验证学号
	  *  @param account
	  *  @param id
	  *  @return  boolean 返回类型
	  * @throws
	 */
	@RequestMapping(value="checkAccount", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkAccount(String account, String id){
		boolean falg = true;
		if(id.equals("undefined")||"".equals(id)){//学生管理主键ID判空已经未定义判断
			id = "";
		}
		try {
			//根据学生管理主键ID和序号查询是否存在重复的学生管理序号
			UserExample userExample = new UserExample();
			userExample.createCriteria().andIdNotEqualTo(id).andAccountEqualTo(account);
		   List<User> list= userService.selectByExample(userExample);
			if(list.size()==0){
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
	
	/**
	 * 
	  * @Title: checkAccount
	  * @Description: 验证学号
	  *  @param account
	  *  @param id
	  *  @return  boolean 返回类型
	  * @throws
	 */
	@RequestMapping(value="checkStudentAccount", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkAccount(String account){
		boolean falg = true;
		try {
			//根据学生管理主键ID和序号查询是否存在重复的学生管理序号
			UserExample userExample = new UserExample();
			userExample.createCriteria().andUserTypeEqualTo(1).andAccountEqualTo(account);
		   List<User> list= userService.selectByExample(userExample);
			if(list.size()==0){
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
	
	/**
	 * 
	  * @Title: toEdit
	  * @Description: 跳转到修改页面
	  *  @param userId
	  *  @param model
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-edit/{userId}")
	public String toEdit(@PathVariable String userId, Model model) {
		// 根据userId查询班级信息
		User user = userService.selectByPrimaryKey(userId);
		// 根据userId查询角色信息
		UserRoleExample ureExample = new UserRoleExample();
		ureExample.createCriteria().andUserIdEqualTo(userId);
		List<UserRole> checklist = userRoleService.selectByExample(ureExample);

		// 获取班级列表
		List<Clas> classList = clasService.selectByExample(null);
		model.addAttribute("classlist", classList);
		
		model.addAttribute("user", user);
		model.addAttribute("checklist", checklist);
		return "experiment/student/edit";
	}

	/**
	 * 
	  * @Title: delete
	  * @Description: 批量删除
	  *  @param ids
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Feedback delete(String ids) {
		Feedback feedback = null;// 返回值
		try {
			String[] idArr = ids.split(",");
			UserExample example = new UserExample();
			// andIdIn 可一次删除多条数据
			example.createCriteria().andIdIn(Arrays.asList(idArr));
			List<User> listUser=userService.selectByExample(example);
			String[] account=new String[listUser.size()] ;
			for(int i=0;i<listUser.size();i++){
				account[i]=listUser.get(i).getAccount();
				//根据account删除虚拟机关系
				StudentVirtualExample student = new StudentVirtualExample();
				student.createCriteria().andAccountEqualTo(account[i]);
				studentVirtualService.deleteByExample(student);
			}
			GradeExample gradeExample = new GradeExample();
			//班级管理如果在学生表中已经存在给予提示
				gradeExample.createCriteria().andStudentIdIn(Arrays.asList(account));
				List<Grade> list= gradeService.selectByExample(gradeExample);//根据班级ID查询学生列表
				if(list.size()>0){
					feedback = new Feedback(false, "请先删除学生对应的任务!");
					return feedback;
				}
			// 批量删除
			userService.deleteByExample(example);
			feedback = new Feedback(true, "删除成功!");
			// 同时删除班级与角色的关联信息
			UserRoleExample ure = new UserRoleExample();
			// andUserIdIn 删除多条数据
			ure.createCriteria().andUserIdIn(Arrays.asList(idArr));
			userRoleService.deleteByExample(ure);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			feedback = new Feedback(false, "删除失败");
		}
		return feedback;
	}
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public Feedback upload(@RequestParam(value = "file", required = false) MultipartFile btnFile,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Feedback feedback = new Feedback(false, "");
		//上传文件名字
		String name = btnFile.getOriginalFilename();
		//获取当前系统人数
		long userCount = userService.getUserCount("");
		if(userCount>0){
			//获取当前系统最大人数
			Time time = timeService.selectByPrimaryKey("1");
			String maxNumber = time.getMaxNumber();
			int max = Integer.parseInt(maxNumber);
			if(max> userCount){
				try {
					feedback = userService.batchImport(name, btnFile,"false",max,userCount);
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e.getMessage(), e);
					feedback = new Feedback(false, "导入EXCEL失败！");
				}
			}else{
				feedback = new Feedback(false, "已超出系统最大人数限制！");
			}
		}
		return feedback;
	}
}