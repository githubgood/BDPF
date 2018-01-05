package com.xunfang.bdpf.vmstemplates.virtual.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.compute.ServerCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunfang.bdpf.experiment.clas.service.ClasService;
import com.xunfang.bdpf.experiment.grade.entity.StudentVirtual;
import com.xunfang.bdpf.experiment.grade.entity.StudentVirtualExample;
import com.xunfang.bdpf.experiment.grade.mapper.GradeResourceMapper;
import com.xunfang.bdpf.experiment.grade.service.StudentVirtualService;
import com.xunfang.bdpf.experiment.plan.mapper.PlanLineMapper;
import com.xunfang.bdpf.experiment.plan.service.PlanService;
import com.xunfang.bdpf.experiment.task.entity.Group;
import com.xunfang.bdpf.experiment.task.entity.GroupExample;
import com.xunfang.bdpf.experiment.task.entity.Task;
import com.xunfang.bdpf.experiment.task.mapper.GroupMapper;
import com.xunfang.bdpf.experiment.task.service.TaskService;
import com.xunfang.bdpf.login.entity.Servers;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.bdpf.system.user.entity.UserExample;
import com.xunfang.bdpf.system.user.service.UserService;
import com.xunfang.bdpf.test.CreatePython;
import com.xunfang.bdpf.test.RunPythonScript;
import com.xunfang.bdpf.vmstemplates.introduction.entity.IntroData;
import com.xunfang.bdpf.vmstemplates.virtual.entity.ServerEntity;
import com.xunfang.bdpf.vmstemplates.virtual.entity.SystemVirtual;
import com.xunfang.bdpf.vmstemplates.virtual.service.SystemVirtualService;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.Identities;
import com.xunfang.utils.OpenStackApi;
import com.xunfang.utils.Page;
/**
 * 
 * @ClassName VirtualController
 * @Description: 虚拟机模板-虚拟机控制层
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月19日 下午5:02:52
 * @version V1.0
 */
@Controller
@RequestMapping(value = "virtual")
public class VirtualController {

	/**
	 * @Fields log : 输出日志
	 */
	private static Logger log = Logger.getLogger(VirtualController.class);
	
	@Autowired
	//学生虚拟机关联Service接口
	private StudentVirtualService studentVirtualService;
	@Autowired
	//用户Service接口
	private UserService userService;
	@Autowired
	//班级Service接口
	private ClasService clasService;

	@Autowired
	//课程资源管理Service接口
	private TaskService taskService;
	
	@Autowired
	//课程教学计划Service接口
	private PlanService planService;
	
	@Autowired
	// 教学计划Mapper映射接口类
	private PlanLineMapper planLineMapper;
	
	@Autowired
	// 学生成绩资源关联Mapper映射接口类
	private GradeResourceMapper gradeResourceMapper;
	
	@Autowired
	// 学生分组Mapper映射接口类
	private GroupMapper groupMapper;
	
	@Autowired
	// 创建虚拟机信息Mapper映射接口类
	private SystemVirtualService systemVirtualService;
	
	private CreatePython createPython=new CreatePython();
	
	private RunPythonScript runPythonScript= new RunPythonScript();
	

	
	/**
	 * 
	 * @Title: index 
	 * @Description: 打开虚拟机页面
	 *  @param @param request 
	 *  @param @param session 
	 *  @param @return 设定文件 
	 * @return String 
	 * 返回类型 @throws
	 */
	@RequestMapping(value = "")
	public String index(HttpServletRequest request, HttpSession session,Model model) {
		User user=(User) session.getAttribute("loginuser");
		if(user == null){
			return "login/login";
		}
		String  account = "";
		if(user.getUserType()!=2){//管理员查询所有班级
			account = user.getAccount();
		}
		//老师去对应的虚拟机
		if(user.getUserType()!=1){
			//查询班级
			model.addAttribute("clas", clasService.queryClassUser(account));
			return "vmstemplates/virtual/index";
		}else{
			//学生去虚拟机 
			return "vmstemplates/virtual/student";
		}
	}
	
	/**
	 * 
	  * @Title: add
	  * @Description: 打开创建虚拟机界面
	  *  @param model
	  *  @param request
	  *  @param session
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-add")
	public String add(Model model,HttpServletRequest request, HttpSession session) {
		return "vmstemplates/introduction/add";
	}

	/**
	 * 
	  * @Title: listVirtual
	  * @Description: 虚拟机查询
	  *  @param keywords
	  *  @return  Map<String,List<Server>> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Page<Servers> listVirtual(String keywords,String classId,String status, HttpSession session) {
		try {
			Page<Servers> page = new Page<Servers>();//Page类
			String token = (String)session.getAttribute("token");
			String account = "";
			if(token==null||"".equals(token)){
				return page;
			}
			User  user = (User) session.getAttribute("loginuser");
			if(user==null){
				return page;
			}
			String classStr = "";
			if(classId!=null &&classId.length()>0){
				classStr = classId;
			}else{
				if(user.getUserType() == 2){//超级管理员，置空账号和班级信息
					account = null;
				}else{
					classStr = user.getClassId();
					account = user.getAccount();
				}
			}
			Map<String, String> studentMap = new HashMap<String, String>();
			List<String> classIdlist = null;
			if(classStr != null&&!"".equals(classStr)){
				String[] strs=classStr.split(",");
				if(classStr.length()>0){
					strs=classStr.split(",");
				}else{
					strs=new String[]{classStr};
				}
				classIdlist = Arrays.asList(strs);
			}
				List<StudentVirtual> studentVirtuals = studentVirtualService.selectByClassIdAndAccount(account,null, classIdlist);
				if(studentVirtuals == null){
					return null;
				}else{
					for (StudentVirtual studentVirtual : studentVirtuals) {
						studentMap.put(studentVirtual.getVirtualId(), studentVirtual.getVirtualId());
					}
				}
			if(status!=null&& status.length()>0){
				studentMap.put("states", status);
			}else{
				studentMap.put("states", "");
			}
			long startTime = new Date().getTime();
			Map<String, List<Servers>> serverMap = OpenStackApi.cloudServerList(keywords,token,studentMap);
			log.info("获取列表   所用时间为 ======"+(new Date().getTime() - startTime));//输出信息
			if(serverMap==null){
				return page;
			}
			List<Servers> servers=serverMap.get("server");
			if(servers==null){
				if(classIdlist != null){
					log.info("删除没有虚拟机的信息");
					for (String claid : classIdlist) {//删除无虚拟机的数据
						StudentVirtualExample ste = new StudentVirtualExample();
						ste.createCriteria().andClassIdEqualTo(claid);
						studentVirtualService.deleteByExample(ste);
					}
				}
				return page;
			}
				page.setTotalCount(servers.size());
				page.setResult(servers);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
		
	}
	
	/**
	 * 
	  * @Title: listVirtual
	  * @Description: 查询学生用户的虚拟机
	  *  @param keywords
	  *  @return  Server 返回类型
	  * @throws
	 */
	@RequestMapping(value = "studentlist")
	@ResponseBody
	public Page<Servers> studentlist(String keywords,HttpSession session) {
		try {
			Page<Servers> page = new Page<Servers>();//Page类
			String token = (String)session.getAttribute("token");
			if(token==null||"".equals(token)){
				return page;
			}
			User  user = (User) session.getAttribute("loginuser");
			if(user==null){
				return page;
			}
			List<StudentVirtual> studentVirtuals = studentVirtualService.selectByClassIdAndAccount(user.getAccount(),null,null);
			Map<String, String> studentMap = new HashMap<String, String>();
			if(studentVirtuals == null){
				return null;
			}else{
				for (StudentVirtual studentVirtual : studentVirtuals) {
					studentMap.put(studentVirtual.getVirtualId(), studentVirtual.getVirtualId());
				}
				studentMap.put("states", "");
			}
			Map<String, List<Servers>> serverMap = OpenStackApi.cloudServerList(keywords,token,studentMap);
			if(serverMap==null){
				return page;
			}
			List<Servers> servers=serverMap.get("server");
			if(servers==null){
				return page;
			}
			
			page.setTotalCount(servers.size());
			page.setResult(servers);
			return page;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}

	}
	
	/**
	 * 
	  * @Title: delete
	  * @Description:单个删除虚拟机
	  *  @param ids
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Feedback delete(String ids,HttpSession session) {
		Feedback feedback = null;
		String[] idArr = ids.split(",");
		String token = (String)session.getAttribute("token");
		if(token==null||"".equals(token)){
			feedback = new Feedback(false, "删除失败");
			return feedback;
		}
		if (StringUtils.isBlank(ids)) {//课程资源主键ID判空
			feedback = new Feedback(false, "删除虚拟机失败");
			return feedback;
		}try {
			ActionResponse msg = OpenStackApi.serverDel(ids,token);
	        if(msg.getCode()==200){
	        	//删除虚拟机关系表对应数据
	        	StudentVirtualExample studentVirtualExample =new StudentVirtualExample();
	        	studentVirtualExample.createCriteria().andVirtualIdIn(Arrays.asList(idArr));
	        	studentVirtualService.deleteByExample(studentVirtualExample);
	            feedback = new Feedback(true, "删除虚拟机成功");
	        }else{
	      	  feedback = new Feedback(false, "删除虚拟机失败");
	       }
		} catch (Exception e) {
			 log.error(e.getMessage());
			 feedback = new Feedback(false, "删除虚拟机失败");
	}
	return feedback;
}
	
	/**
	 * 
	  * @Title: stop
	  * @Description: 单个停止虚拟机
	  *  @param id
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "stop/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Feedback stop(@PathVariable String id,HttpSession session) {
		Feedback feedback = null;
		String token = (String)session.getAttribute("token");
		if(token==null||"".equals(token)){
			feedback = new Feedback(false, "关闭虚拟机失败!");
			return feedback;
		}
		if (StringUtils.isBlank(id)) {//虚拟机主键ID判空
			feedback = new Feedback(false, "关闭虚拟机失败!");
			return feedback;
		}
		String type = "STOP";//STOP
		ActionResponse msg = OpenStackApi.cloudServerAction(type, id,token);
        if(msg.getCode()==200){
              feedback = new Feedback(true, "关闭虚拟机成功!");
          }else{
        	  feedback = new Feedback(false, "关闭虚拟机失败!");
         }
		return feedback;
	}
	
	/**
	 * 
	  * @Title: start
	  * @Description: 单个启动虚拟机
	  *  @param id
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "start/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Feedback start(@PathVariable String id,HttpSession session) {
		Feedback feedback = null;
		String token = (String)session.getAttribute("token");
		if(token==null||"".equals(token)){
			feedback = new Feedback(false, "启动虚拟机失败!");
			return feedback;
		}
		if (StringUtils.isBlank(id)) {//虚拟机主键ID判空
			feedback = new Feedback(false, "启动虚拟机失败!");
			return feedback;
		}
		String type = "START";
		ActionResponse msg = OpenStackApi.cloudServerAction(type, id,token);
        if(msg.getCode()==200){
              feedback = new Feedback(true, "启动虚拟机成功!");
          }else{
        	  feedback = new Feedback(false, "启动虚拟机失败!");
         }
		return feedback;
	}
	
	/**
	 * 
	  * @Title: restart
	  * @Description: 虚拟机重启
	  *  @param id
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "restart/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Feedback restart(@PathVariable String id,HttpSession session) {
		Feedback feedback = null;
		String token = (String)session.getAttribute("token");
		if(token==null||"".equals(token)){
			feedback = new Feedback(false, "重启虚拟机失败!");
			return feedback;
		}
		if (StringUtils.isBlank(id)) {//虚拟机主键ID判空
			feedback = new Feedback(false, "重启虚拟机失败!");
			return feedback;
		}
		String type = "SOFT";
		ActionResponse msg = OpenStackApi.cloudServerReboot(type, id,token);
        if(msg.getCode()==200){
              feedback = new Feedback(true, "重启虚拟机成功!");
          }else{
        	  feedback = new Feedback(false, "重启虚拟机失败!");
         }
		return feedback;
	}


	/**
	 * 
	  * @Title: batchRestart
	  * @Description: 批量重启
	  *  @param ids
	  *  @param type
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "batchRestart", method = RequestMethod.POST)
	@ResponseBody
	public Feedback batchRestart( String ids,HttpSession session) {
		Feedback feedback = null;
		String token = (String)session.getAttribute("token");
		if(token==null||"".equals(token)){
			feedback = new Feedback(false, "批量重启虚拟机失败!");
			return feedback;
		}
		if (StringUtils.isBlank(ids)) {//虚拟机主键ID判空
			feedback = new Feedback(false, "批量重启虚拟机失败!");
			return feedback;
		}
		String type = "SOFT";
		ActionResponse msg = OpenStackApi.batchCloudServerReboot(type, ids,token);
        if(msg.getCode()==200){
              feedback = new Feedback(true, "");
          }else{
        	  feedback = new Feedback(false, "批量重启虚拟机失败!");
         }
		return feedback;
	}

	/**
	 * 
	  * @Title: batchServerAction
	  * @Description: 虚拟机批量操作
	  *  @param ids
	  *  @param type
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "batchServerAction", method = RequestMethod.POST)
	@ResponseBody
	public Feedback batchServerAction( String ids,String type,HttpSession session) {
		Feedback feedback = null;
		String token = (String)session.getAttribute("token");
		if(token==null||"".equals(token)){
			feedback = new Feedback(false, "批量关闭虚拟机失败!");
			return feedback;
		}
		String  msgs="";
		if(type.equals("STOP")){
			msgs="批量关闭虚拟机失败!";	
		}else{
			msgs="批量启动虚拟机失败!";
		}
		if (StringUtils.isBlank(ids)) {//虚拟机主键ID判空
			feedback = new Feedback(false, msgs);
			return feedback;
		}
		long startTime = new Date().getTime();
		ActionResponse msg = OpenStackApi.batchCloudServerAction(type, ids,token);
		log.info(msg.isSuccess()+"/////"+msg.getCode()+"////"+msg.toString()+"所用时间为 ======"+(new Date().getTime() - startTime));//输出信息
        if(msg.getCode()==200){
              feedback = new Feedback(true, msgs);
          }else{
        	  feedback = new Feedback(false, msgs);
         }
		 log.info(type);
		return feedback;
	}
	
	/**
	 * 
	  * @Title: batchDel
	  * @Description: 批量删除虚拟机
	  *  @param ids
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "batchServerDel", method = RequestMethod.POST)
	@ResponseBody
	public Feedback batchDel( String ids,HttpSession session) {
		Feedback feedback = null;
		String token = (String)session.getAttribute("token");
		if(token==null||"".equals(token)){
			feedback = new Feedback(false, "批量删除虚拟机失败");
			return feedback;
		}
		if (StringUtils.isBlank(ids)) {//虚拟机主键ID判空
			feedback = new Feedback(false, "批量删除虚拟机失败");
			return feedback;
		}
		
		try {
			ActionResponse msg = OpenStackApi.batchServerDel(ids,token);
	        if(msg.getCode()==200){
		        	StudentVirtualExample studentVirtualExample =new StudentVirtualExample();
		        	studentVirtualExample.createCriteria().andVirtualIdIn(Arrays.asList(ids));
		        	studentVirtualService.deleteByExample(studentVirtualExample);
		        	String[] id=ids.split(",");
		        	for(int i=0;i<id.length;i++){
		        	systemVirtualService.deleteSystemVirtualByVirtualId(id[i]);
		        	}
	              feedback = new Feedback(true, "批量删除虚拟机成功");
	          }else{
	        	  feedback = new Feedback(false, "批量删除虚拟机失败");
	         }
		  } catch (Exception e) {
			  log.error(e.getMessage());
			  feedback = new Feedback(false, "批量删除虚拟机失败");
		}
		return feedback;
	}
	
	/**
	 * 
	  * @Title: getData
	  * @Description: 获取 创建虚拟机所需要的娄的
	  * @param @param keywords
	  * @param @param request
	  * @param @return    设定文件
	  * @return IntroData    返回类型
	  * @throws
	 */
	@RequestMapping(value = "getData")
	@ResponseBody
	public IntroData getData(String keywords,HttpServletRequest request, HttpSession session) {
		IntroData introData = new IntroData();
		String token = (String)session.getAttribute("token");
		if(token==null||"".equals(token)){
			return introData;
		}
		//源
		introData.setImageList(OpenStackApi.cloudImage(keywords,token));
		//云主机类型
		introData.setFlavorsList(OpenStackApi.cloudFlavors("",token));
		//秘银对
		introData.setKeyPairList(OpenStackApi.cloudKeypair(token));
		//安全组
		introData.setSecgroupList(OpenStackApi.cloudSecgroup(token));
		//可用域
		introData.setZoneList(OpenStackApi.cloudZone(token));
		//网络
		introData.setNetworkList(OpenStackApi.cloudNetwork(token));
		//获取所有任务列表
		List<Task> taskList = taskService.selectByExample(null);
		introData.setTaskList(taskList);
		return introData;
	}


	/**
	 * 
	  * @Title: save
	  * @Description: 虚拟机创建
	  *  @param name  虚拟机名称 
	  *  @param imageId 模板id
	  *  @param flavorId 虚拟机类型
	  *  @param keyName 密钥组名称
	  *  @param securityGroupsName 安全组名称
	  *  @param adminPass 密码
	  *  @param availabilityZone 详情数据
	  *  @param count 小组个数
	  *  @param id 网络类型id
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public Feedback save(String name,String imageId,String flavorId,String keyName,String securityGroupsName,String adminPass,String availabilityZone,String count ,String account,String[] id ,String taskId,String groupId, HttpSession session) {
		Feedback feedback = null;
		String token = (String)session.getAttribute("token");
		if(token==null||"".equals(token)){
			feedback = new Feedback(false, "创建虚拟机失败");
			return feedback;
		}
		//根据学生管理主键ID和序号查询是否存在重复的学生管理序号
		UserExample userExample = new UserExample();
		userExample.createCriteria().andAccountEqualTo(account);
	    List<User> list= userService.selectByExample(userExample);
		if(list==null || list.size()==0){
			feedback = new Feedback(false, "创建虚拟机失败,学号不存在!");
			return feedback;
		}
		UserExample example = new UserExample();
		example.createCriteria().andAccountEqualTo(account);
		List<User> selectByExample = userService.selectByExample(example);
		String classId = selectByExample.get(0).getClassId();
		
		
		User  user = (User) session.getAttribute("loginuser");
		if(user==null){
			feedback = new Feedback(false, "创建虚拟机失败,学号不存在!");
			return feedback;
		}
		List<String> networkParams = new ArrayList<String>();
		for(int i=0;i<id.length;i++){
			networkParams.add(id[i]);
		}
		int resultnum = 0;
		for(int i=0;i<Integer.parseInt(count);i++){
			String servicename = "";
			if(Integer.parseInt(count)==1){
				servicename = name;
			}else{
				servicename = name +"-"+(i+1);
			}
			ServerEntity serverEntity = new ServerEntity();
			serverEntity.setName(servicename);
			serverEntity.setImageId(imageId);
			serverEntity.setFlavorId(flavorId);
			serverEntity.setKeyName(keyName);
			serverEntity.setSecurityGroupsName(securityGroupsName);
			serverEntity.setAdminPass(adminPass);
			serverEntity.setAvailabilityZone(availabilityZone);
			
			ServerCreate sc = Builders.server().name(serverEntity.getName())
					.flavor(serverEntity.getFlavorId())
					.image(serverEntity.getImageId())
					.networks(networkParams)
					.addSecurityGroup(serverEntity.getSecurityGroupsName())
					.availabilityZone(serverEntity.getAvailabilityZone())
					.addAdminPass(serverEntity.getAdminPass())
					.keypairName(serverEntity.getKeyName()).build();
			
			Servers server = OpenStackApi.cloudServerCreate(sc, token);
			
			//保存学生与虚拟机绑定信息
			StudentVirtual studentVirtual = new StudentVirtual();//new学生虚拟机表Model
			studentVirtual.setId(Identities.uuid2());//学生虚拟机表主键ID
			studentVirtual.setAccount(account);//学生学号
			studentVirtual.setVirtualId(server.getId());//虚拟机ID
			studentVirtual.setGroupId(groupId);//分组id
			studentVirtual.setClassId(classId);//班级id
			studentVirtual.setCourseTaskId(taskId);//任务id
			studentVirtual.setVirtualName(serverEntity.getName());//虚拟机名称
			studentVirtual.setVirtualName(serverEntity.getName());//虚拟机名称
			if(groupId.equals("")){
				studentVirtual.setType(1);//虚拟机类型：0 分组实验 1 个人实验  2 开放实验
			}else if(!groupId.equals("")){
				studentVirtual.setType(0);//虚拟机类型：0 分组实验 1 个人实验  2 开放实验
			}else if(taskId.equals("")){
				studentVirtual.setType(2);//虚拟机类型：0 分组实验 1 个人实验  2 开放实验
			}
			studentVirtual.setImageId(serverEntity.getImageId());//镜像模板ID
			
		    resultnum = studentVirtualService.insert(studentVirtual);
		}
	    
        if(resultnum >0){
            feedback = new Feedback(true, "创建虚拟机成功");
        }else{
      	   feedback = new Feedback(false, "创建虚拟机失败");
       }
		return feedback;
	}
	
	/**
	 * 
	  * @Title: checkVirtualName
	  * @Description: 验证虚拟机名称
	  *  @param name String 虚拟机名称
	  *  @return  boolean 返回类型
	  * @throws
	 */
	@RequestMapping(value="checkVirtualName", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkVirtualName(String name){
		if(name==null||"".equals(name)){
			return false;
		}
		try {
			//根据学生管理主键ID和序号查询是否存在重复的学生管理序号
			StudentVirtualExample  studentVirtualExample = new StudentVirtualExample();
			studentVirtualExample.createCriteria().andVirtualNameEqualTo(name);
			long virtualCount =  studentVirtualService.countByExample(studentVirtualExample);
			if(virtualCount==0){
				return false;
			}else{
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return false;
	}
	
	/**
	 * 
	  * @Title: checkStudentAccount
	  * @Description: 验证学号或工号
	  *  @param account
	  *  @param id
	  *  @return  boolean 返回类型
	  * @throws
	 */
	@RequestMapping(value="checkStudentAccount", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkStudentAccount(String account){
		boolean falg = true;
		try {
			//根据账号查询是否存在重复的学号或工号
			UserExample userExample = new UserExample();
			userExample.createCriteria().andAccountEqualTo(account);
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
	  * @Title: getGroupList
	  * @Description: 获取分组
	  * @param @param taskId
	  * @param @param request
	  * @param @return    设定文件
	  * @return IntroData    返回类型
	  * @throws
	 */
	@RequestMapping(value = "getGroupList")
	@ResponseBody
	public List<Group> getGroupList(String taskId,HttpServletRequest request, HttpSession session){
		//任务
		Task task = taskService.selectByPrimaryKey(taskId);
		/*//计划id
		String teachingPlanId = task.getTeachingPlanId();
		//获取资源id列表 
		PlanLineExample planLineExample = new PlanLineExample();
		planLineExample.createCriteria().andTeachingPlanIdEqualTo(teachingPlanId);
		List<PlanLine> resourceList = planLineMapper.selectByExample(planLineExample);
		List<String> values = new ArrayList<>();
		for (PlanLine planLine : resourceList) {
			values.add(planLine.getCourseResourceId());
		}*/
		//分组列表
		GroupExample groupExample= new GroupExample();
		groupExample.createCriteria().andTaskIdEqualTo(taskId);
		List<Group> groupList = groupMapper.selectByExample(groupExample);
		return groupList;
	}
	
	/**
	 * @param request
	 * @param session
	 * @param ip
	 * @return 
	 */
	@RequestMapping(value = "write")
	@ResponseBody
	public Boolean write(HttpServletRequest request, HttpSession session,String[] ips,String[] names,String virName) {
		User user=(User) session.getAttribute("loginuser");
		boolean isDone=false;
		String hosts="";
		String config="";
		String start="";
		String journalnode="";
		String zookeeper="";
		if(ips.length>0){
			String hostnames="";
			String hadoopnamenode="";
			config+="ssh_port = 22"+'\n';
			config+="root_passwd_def = xunfang"+'\n';
			config+="package.jdk = jdk-7u80-linux-x64.tar.gz"+'\n';
			config+="package.zookeeper = zookeeper-3.4.8.tar.gz"+'\n';
			config+="package.hadoop = hadoop-2.6.5.tar.gz"+'\n';
			config+="package.spark = spark-1.6.3-bin-hadoop2.6.tgz"+'\n';
			config+="basedir.install = /usr/local/myhadoop"+'\n';
			config+="basedir.log = /usr/local/myhadoop/logs"+'\n';
			config+="basedir.data = /usr/local/myhadoop/datas"+'\n';
			config+="run.user  = root"+'\n';
			config+="run.group = root"+'\n';
			
			start+="from fabric.api import *"+'\n';
			start+="env.hosts=['"+ips[0]+":22']"+'\n';
			start+="env.password='xunfang'"+'\n';
			start+="def copyhostfile():"+'\n';
			start+="	with settings(warn_only = True):"+'\n';
			start+="		put(\"/opt/conf/"+user.getId()+"/hosts\", \"/usr/local/myhadoop/hadoop-spark-installer-master/conf\")"+'\n';
			start+="		put(\"/opt/conf/"+user.getId()+"/config\", \"/usr/local/myhadoop/hadoop-spark-installer-master/conf\")"+'\n';
			start+="def instart():"+'\n';
			start+="	with cd('/usr/local/myhadoop/hadoop-spark-installer-master'):"+'\n';
			
				System.out.println(virName+"!!!!!!!!!");
				if(virName.equals("hadoop")){
			start+="		run(\"sh hadoop.sh\")"+'\n';
				}
                if(virName.equals("HBase")){
            start+="		run(\"sh hadoop-hbase.sh\")"+'\n';		
				}
                if(virName.equals("HBaseHive")){
            start+="		run(\"sh hadoop-hbase-hive.sh\")"+'\n';		
				}
                if(virName.equals("Spark")){
            start+="		run(\"sh hadoop-spark.sh\")"+'\n';		
				}
                if(virName.equals("all")){
            start+="		run(\"sh allservice.sh\")"+'\n';		
        		}
				
			
			//start+="		run(\"sh install-start.sh\")"+'\n';
			start+="def start():"+'\n';
			start+="	copyhostfile()"+'\n';
			start+="	instart()";
			for(int i=0;i<ips.length;i++){
				//System.err.println(ips[i]+":"+names[i]);
				hosts+=ips[i]+" "+names[i]+'\n';
				hostnames+=names[i]+",";
				hadoopnamenode=names[0]+","+names[1];
				journalnode=names[0];
				zookeeper=names[0]+","+names[1]+","+names[2];
				}
			
			hostnames=hostnames.substring(0, hostnames.length()-1);
			config+="zookeeper.hostnames = "+zookeeper+'\n';
			config+="hadoop.namenode.hostnames = "+hadoopnamenode+'\n';
			config+="hadoop.journalnode.hostnames = "+journalnode+'\n';
			config+="hadoop.datanode.hostnames = "+hostnames+'\n';
			config+="hadoop.datanode.databasedirs = /usr/local/myhadoop/datas/datanodedirspark.master.hostnames = "+hostnames+'\n';
			config+="spark.master.hostnames = "+hostnames+'\n';
			config+="spark.slave.hostnames = "+hostnames;
			//System.out.println(user.getId()+"!!!!!!!!!!!!!!!!!!!!"+"D:/1a/"+user.getId()+"::::"+hostnames);
			try {
//				isDone=createPython.createDir("D:/1a/"+user.getId());
//				isDone=createPython.string2File(hosts, "D:/1a/"+user.getId()+"/hosts");
//				isDone=createPython.string2File(config, "D:/1a/"+user.getId()+"/config");
//				isDone=createPython.string2File(start, "D:/1a/"+user.getId()+"/start.py");
				isDone=createPython.createDir("/opt/conf/"+user.getId());
				isDone=createPython.string2File(hosts, "/opt/conf/"+user.getId()+"/hosts");
				isDone=createPython.string2File(config, "/opt/conf/"+user.getId()+"/config");
				isDone=createPython.string2File(start, "/opt/conf/"+user.getId()+"/start.py");
				
			} catch (Exception e) {
				isDone=false;
			}
			
		}
		
		return isDone;
		
	}
	
	@RequestMapping(value = "install")
	@ResponseBody
	public Boolean install(HttpServletRequest request, HttpSession session) {
		User user=(User) session.getAttribute("loginuser");
		long time1=System.currentTimeMillis();
		runPythonScript.RunPython("fab -f  /opt/conf/"+user.getId()+"/start.py start");
		long time2=System.currentTimeMillis();
		System.out.println(time2+":"+time1+":"+(time2-time1));
		if(time2-time1>=1000*10){
			return true;
		}else{
			return false;
		}
		
	}
	
	@RequestMapping(value = "destroy")
	@ResponseBody
	public Feedback destroy(HttpServletRequest request, HttpSession session,String ids) {
		User user=(User) session.getAttribute("loginuser");
		Feedback feedback = null;
		String token = (String)session.getAttribute("token");
		if(token==null||"".equals(token)){
			feedback = new Feedback(false, "批量删除虚拟机失败");
			return feedback;
		}
		if (StringUtils.isBlank(ids)) {//虚拟机主键ID判空
			feedback = new Feedback(false, "批量删除虚拟机失败");
			return feedback;
		}
		try {
			ActionResponse msg = OpenStackApi.batchServerDel(ids,token);
	        if(msg.getCode()==200){
		        	StudentVirtualExample studentVirtualExample =new StudentVirtualExample();
		        	studentVirtualExample.createCriteria().andVirtualIdIn(Arrays.asList(ids));
		        	studentVirtualService.deleteByExample(studentVirtualExample);
		        	String[] id=ids.split(",");
		        	for(int i=0;i<id.length;i++){
		        	systemVirtualService.deleteSystemVirtualByVirtualId(id[i]);
		        	}
	              feedback = new Feedback(true, "批量删除虚拟机成功");
	              }else{
	        	  feedback = new Feedback(false, "批量删除虚拟机失败");
	         }
		  } catch (Exception e) {
			  log.error(e.getMessage());
			  feedback = new Feedback(false, "批量删除虚拟机失败");
		}
		
		
		return feedback;
	
	}
	
	@RequestMapping(value = "insertVirtual")
	@ResponseBody
	public Boolean insertVirtual(HttpServletRequest request, HttpSession session,String[] ips,String[] ids) {
	
		
		User user=(User) session.getAttribute("loginuser");
		
		if(ips.length>2||ips!=null||!ips.equals("")){
		String slaveIp="";
		String sid="";
		SystemVirtual systemVirtual=new SystemVirtual();
		systemVirtual.setId(Identities.uuid2());
		systemVirtual.setUserId(user.getId());
		systemVirtual.setMaster(ips[0]);
		systemVirtual.setMid(ids[0]);
		for(int i=1;i<ips.length;i++){
			slaveIp+=ips[i]+",";
			sid+=ids[i]+",";
		}
		slaveIp=slaveIp.substring(0, slaveIp.length()-1);
		sid=sid.substring(0, sid.length()-1);
		systemVirtual.setSlave(slaveIp);
		systemVirtual.setSid(sid);
		systemVirtualService.insertSystemVirtual(systemVirtual);
		}
		return true;
	
	}
	
	@RequestMapping(value = "selectVirtual")
	@ResponseBody
	public List<SystemVirtual> selectVirtual(HttpServletRequest request, HttpSession session) {
		User user=(User) session.getAttribute("loginuser");
	    return systemVirtualService.selectSystemVirtualByPrimaryKey(user.getId());
	
	}
	
	@RequestMapping(value = "selectVirtualByUser")
	@ResponseBody
	public List<SystemVirtual> selectVirtualByUser(HttpServletRequest request, HttpSession session) {
		User user=(User) session.getAttribute("loginuser");
		if(user.getUserType()==1){
	    return systemVirtualService.selectSystemVirtualByPrimaryKey(user.getId());
		}else{
			return null;
		}
		
	
	}
	
	
	@RequestMapping(value = "selectVirtualByUserId")
	@ResponseBody
	public List<SystemVirtual> selectVirtualByUserId(HttpServletRequest request, HttpSession session,String userId) {
		
	    return systemVirtualService.selectSystemVirtualByPrimaryKey(userId);
	
	}
	
	
	@RequestMapping(value = "getUser")
	@ResponseBody
	public int getUser(HttpServletRequest request, HttpSession session) {
		User user=(User) session.getAttribute("loginuser");
		return user.getUserType();
	}
	
	@RequestMapping(value = "getAccount")
	@ResponseBody
	public User getAccount(HttpServletRequest request, HttpSession session,String account) {
		return userService.selectByAccount(account);
	}
	
	@RequestMapping(value = "insertVirtualByTeacher")
	@ResponseBody
	public Boolean insertVirtualByTeacher(HttpServletRequest request, HttpSession session,String[] ips,String[] ids,String userId) {
		if(ips.length>2||ips!=null||!ips.equals("")){
		String slaveIp="";
		String sid="";
		SystemVirtual systemVirtual=new SystemVirtual();
		systemVirtual.setId(Identities.uuid2());
		systemVirtual.setUserId(userId);
		systemVirtual.setMaster(ips[0]);
		systemVirtual.setMid(ids[0]);
		for(int i=1;i<ips.length;i++){
			slaveIp+=ips[i]+",";
			sid+=ids[i]+",";
		}
		slaveIp=slaveIp.substring(0, slaveIp.length()-1);
		sid=sid.substring(0, sid.length()-1);
		systemVirtual.setSlave(slaveIp);
		systemVirtual.setSid(sid);
		systemVirtualService.insertSystemVirtual(systemVirtual);
		}
		return true;
	
	}
	
	@RequestMapping(value = "updateVirtual")
	@ResponseBody
	public Integer updateVirtual(HttpServletRequest request, HttpSession session,String virName) {
		SystemVirtual systemVirtual=new SystemVirtual();
		User user=(User) session.getAttribute("loginuser");
		
		System.out.println(virName+"!!!!!!!!!");
			
			
		systemVirtual.setUserId(user.getId());
		systemVirtual.setInstall(virName);
		
		return systemVirtualService.updateSystemVirtual(systemVirtual);
	}
	
	
	
	
	
	
	
}
