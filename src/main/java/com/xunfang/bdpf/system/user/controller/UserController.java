package com.xunfang.bdpf.system.user.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
import com.xunfang.bdpf.system.role.entity.Role;
import com.xunfang.bdpf.system.role.service.RoleService;
import com.xunfang.bdpf.system.time.entity.Time;
import com.xunfang.bdpf.system.time.service.TimeService;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.bdpf.system.user.entity.UserExample;
import com.xunfang.bdpf.system.user.entity.UserRole;
import com.xunfang.bdpf.system.user.entity.UserRoleExample;
import com.xunfang.bdpf.system.user.service.UserRoleService;
import com.xunfang.bdpf.system.user.service.UserService;
import com.xunfang.utils.ConsJson;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.HttpUtils;
import com.xunfang.utils.Identities;
import com.xunfang.utils.Page;
import com.xunfang.utils.PropertiesUtil;

/**
 * 
 * @ClassName UserController
 * @Description: 用户管理控制层 
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年5月31日 下午5:45:01
 * @version V1.0
 */
@Controller
@RequestMapping(value = "user")
public class UserController {
	@Autowired
	//用户信息接口
	private UserService userService;
	@Autowired
	//角色管理接口
	private RoleService roleService;
	@Autowired
	//用户与角色关联表service层接口
	private UserRoleService userRoleService;
	@Autowired
	//班级管理Service接口服务
	private ClasService clasService;

	@Autowired
	//实验时间设置服务接口
	private TimeService timeService;
	/**
	 * @Fields log : 输出日志
	 */
	private static Logger log = Logger.getLogger(UserController.class);

	/**
	 * 
	 * @Title: index 
	 * @Description: 用户管理首页
	 *  @param @param request 
	 *  @param @param session 
	 *  @param @return 设定文件 
	 * @return String 
	 * 返回类型 @throws
	 */
	@RequestMapping(value = "")
	public String index(HttpServletRequest request, HttpSession session) {
		return "system/user/index";

	}

	/**
	 * 
	 * @Title: listUserByPage 
	 * @Description: 分页获取数据
	 * @param @param keywords  关键字
	 * @param @param pageNo    从第几条开始取数据
	 * @param @param pageSize  每页显示多少条数据
	 * @param @return 设定文件 
	 * @return Page<User> 
	 * 返回类型 @throws
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Page<User> listUserByPage(String keywords, String pageNo, String pageSize) {
		int skip = Integer.parseInt(PropertiesUtil.getValue("page.pageNo"));//从第几条开始取数据
		int max = Integer.parseInt(PropertiesUtil.getValue("page.pageSize"));//每页显示多少条数据
		Page<User> page = new Page<User>();
		List<User> list = new ArrayList<User>();
		if (StringUtils.isNotBlank(pageSize)) {
			max = Integer.parseInt(pageSize);
		}
		if (StringUtils.isNotBlank(pageNo)) {
			skip = max * (Integer.parseInt(pageNo) - 1);// mysql中记录从0开始
			page.setPageNo(Integer.parseInt(pageNo));// 设置页码
		} else {
			page.setPageNo(1);
		}
		try {
			page.setTotalCount(userService.getUserCount(keywords));// 获取总条数
			list = userService.queryUserByPage(keywords, skip, max);// 获取分页数据，根据关键字查找
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
	 * @Description: 跳转添加用户页面 
	 * @param @param model 
	 * @param @return 设定文件
	 * @return String 返回类型 
	 * @throws
	 */
	@RequestMapping(value = "to-add")
	public String toAdd(Model model, HttpSession session) {
		// 获取系统角色的列表数据
		List<Role> roleList = roleService.selectByExample(null);
		model.addAttribute("rolelist", roleList);
		// 获取班级列表
		List<Clas> classList = new ArrayList<Clas>();
		User  user = (User) session.getAttribute("loginuser");
		if(user.getUserType() == 2){//管理员
			classList = clasService.selectByExample(null);
		}else if(user.getUserType() == 1){//老师
			ClasExample classExample = new ClasExample();
			classExample.createCriteria().andIdIn(Arrays.asList(user.getClassId().split(",")));
			classList = clasService.selectByExample(null);
		}
		model.addAttribute("classlist", classList);
		return "system/user/add";
	}

	/**
	 * 
	 * @Title: save 
	 * @Description: 保存数据并跳转用户管理页面 
	 * @param @param user     用户对象
	 * @param @param session  session对象
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Feedback save(User user, HttpSession session) {
		Feedback feedback = null;
		String userId = null;// 用户id
		String tClassId = user.gettClassId();//老师id
		
		if(tClassId != null){
			user.setClassId(tClassId);
			user.setUserType(0);//教师
		}else{
			if("".equals(user.getClassId())&&user.gettClassId()==null){
				user.setUserType(2);//管理员
			}else {
				user.setUserType(1);//学生
			}
		}
		try {
			if (user.getId() != null && !"".equals(user.getId())) {// 更新数据
				userId = user.getId();
				if(user.getUserType() == 2){
					// 根据userId查询角色信息
					UserRoleExample ureExample = new UserRoleExample();
					ureExample.createCriteria().andUserIdEqualTo(userId);
					List<UserRole> checklist = userRoleService.selectByExample(ureExample);
					if(checklist.size()>0){
						String roleId = checklist.get(0).getRoleId();
						user.setRoleId(roleId);
					}
				}
				// 根据用户id更新数据
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
					// 保存数据
					userService.insert(user);
				}else{
					if(max>size){
						//新增用户
						userId = Identities.uuid2();
						user.setId(userId);
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
			//用户与角色的关联对象
			UserRole userRole = new UserRole();
			userRole.setId(Identities.uuid2());//随机生成的id
			userRole.setUserId(userId);//用户id
			userRole.setRoleId(user.getRoleId());//角色id
			// 插入新数据
			userRoleService.insert(userRole);
			
			//调用在线考试保存接口
//			String itcAddress = (String)session.getAttribute("itcAddress");
			
//			String urls = itcAddress+"/without/create/ictilearning/normaluser";
//			String pamars = "loginName="+user.getName()+"&password="+user.getPassword()
//			               +"&loginAccount="+user.getAccount()+"&userType="+user.getUserType();
//			
//			String result = HttpUtils.sendPost(urls, pamars);
//			
//			Map toMap = ConsJson.toMap(result);
//			
//			if("SUCCESS".equals(toMap.get("isv_code"))){
//				feedback = new Feedback(true,"保存成功！");
//			}else{
//				feedback = new Feedback(true,toMap.get("description")+"");
//			}
			feedback = new Feedback(true,"保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			feedback = new Feedback(false, "保存失败！");
		}
		return feedback;
	}

	/**
	 * 
	 * @Title: toEdit 
	 * @Description: 跳转到修改页面 
	 * @param @param userId  用户id
	 * @param @param model 
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	@RequestMapping(value = "to-edit/{userId}")
	public String toEdit(@PathVariable String userId, Model model) {
		// 根据userId查询用户信息
		User user = userService.selectByPrimaryKey(userId);
		// 根据userId查询角色信息
		UserRoleExample ureExample = new UserRoleExample();
		ureExample.createCriteria().andUserIdEqualTo(userId);
		List<UserRole> checklist = userRoleService.selectByExample(ureExample);
		// 获取系统角色的列表数据
		List<Role> rolelist = roleService.selectByExample(null);

		// 获取班级列表
		List<Clas> classList = clasService.selectByExample(null);
		model.addAttribute("classlist", classList);
		
		model.addAttribute("user", user);
		model.addAttribute("checklist", checklist);
		model.addAttribute("rolelist", rolelist);
		return "system/user/edit";
	}

	/**
	 * 
	 * @Title: delete 
	 * @Description: 批量删除 
	 * @param @param ids  删除数据的id集合
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Feedback delete(String ids,HttpSession session) {
		Feedback feedback = null;// 返回值
		try {
			String[] idArr = ids.split(",");
			List<User> greadIds = userService.greadIds();
			for (int i = 0; i < greadIds.size(); i++) {
				User user = greadIds.get(i);
				if(user.getId().contains(ids)){
					feedback = new Feedback(false, "此用户已经在任务中，不能删除");
					return feedback;
				}
			}
			// 批量删除
			UserExample example = new UserExample();
			// andIdIn 可一次删除多条数据
			example.createCriteria().andIdIn(Arrays.asList(idArr));
			
//			List<User> userList = userService.selectByExample(example);
//			//调用ict删除接口
//			for (User user2 : userList) {
//				String urls = PropertiesUtil.getValue("ITC_ADDRESS")+"/without/delete/ictilearning/user";
//				String pamars = "loginName="+user2.getName()+"&userType="+user2.getUserType();
//				
//				String results = HttpUtils.sendPost(urls, pamars);
//			}
			
			userService.deleteByExample(example);
			feedback = new Feedback(true, "删除成功!");
			// 同时删除用户与角色的关联信息
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
	
	/**
	 * 
	  * @Title: changePwd
	  * @Description: 修改密码
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "to_updpassword")
	public String changePwd(HttpServletRequest request, HttpSession session) {
		return "change_pwd";
	}
	
	/**
	 * 
	  * @Title: toImport
	  * @Description: 教师管理管理跳转方法，进入导入页
	  *  @param model Model 教师管理model
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-import")
	public String toImport(Model model, HttpSession session) {
		return "system/user/import";
	}
	
	/**
	 * 
	  * @Title: changepwd
	  * @Description: 修改用户密码
	  * @param @param user  用户信息
	  * @param @return    设定文件
	  * @return Feedback    返回类型
	  * @throws
	 */
	@RequestMapping(value = "changepwd", method = RequestMethod.POST)
	@ResponseBody
	public Feedback changepwd(User user) {
		Feedback feedback = null;
		User users = userService.selectByPrimaryKey(user.getId());
		//修改密码
		users.setPassword(user.getPassword());
		try {
			userService.updateByPrimaryKey(users);
			feedback = new Feedback(true, "修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			feedback = new Feedback(true, "修改失败!");
		}
		return feedback;
	}
	
	
	/**
	 * 
	  * @Title: checkName
	  * @Description: 验证账号是否存在
	  *  @param account
	  *  @return  boolean 返回类型
	  * @throws
	 */
	@RequestMapping(value="checkName", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkName(String account, String id){
		boolean falg = true;
		if(id.equals("undefined")||"".equals(id)){//课程资源主键ID判空已经未定义判断
			id = "";
		}
		try {
			//根据账号查询是否存在重复的用户
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
	  * @Title: checkPassword
	  * @Description: 验证密码是否存在
	  *  @param account
	  *  @param old_pwd
	  *  @return  boolean 返回类型
	  * @throws
	 */
	@RequestMapping(value="checkPassword", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkPassword(String Id, String old_pwd){
		boolean falg = true;
		if(old_pwd.equals("undefined")||"".equals(old_pwd)){
			old_pwd = "";
		}
		try {
			//根据账号查询是否存在重复的用户
			UserExample userExample = new UserExample();
			userExample.createCriteria().andPasswordEqualTo(old_pwd).andIdEqualTo(Id);
		   List<User> list= userService.selectByExample(userExample);
			if(list.size()>0){
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
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public Feedback upload(@RequestParam(value = "file", required = false) MultipartFile btnFile,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Feedback feedback = new Feedback(false, "");
		//上传文件名字
		String name = btnFile.getOriginalFilename();
		//获取当前系统人数
		long userCount = userService.getUserCount(null);
		//获取当前系统最大人数
		Time time = timeService.selectByPrimaryKey("1");
		String maxNumber = time.getMaxNumber();
		int max = Integer.parseInt(maxNumber);
		try {
			feedback = userService.batchImport(name, btnFile,"true",max,userCount);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			feedback = new Feedback(false, "导入EXCEL失败！");
		}
		return feedback;
	}
}