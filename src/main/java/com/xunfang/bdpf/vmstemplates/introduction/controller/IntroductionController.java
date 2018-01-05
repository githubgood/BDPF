package com.xunfang.bdpf.vmstemplates.introduction.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.openstack4j.api.Builders;
import org.openstack4j.model.compute.ServerCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xunfang.bdpf.experiment.grade.entity.GradeResource;
import com.xunfang.bdpf.experiment.grade.entity.GradeResourceExample;
import com.xunfang.bdpf.experiment.grade.entity.StudentVirtual;
import com.xunfang.bdpf.experiment.grade.entity.StudentVirtualExample;
import com.xunfang.bdpf.experiment.grade.mapper.GradeResourceMapper;
import com.xunfang.bdpf.experiment.grade.service.StudentVirtualService;
import com.xunfang.bdpf.experiment.plan.entity.Plan;
import com.xunfang.bdpf.experiment.plan.entity.PlanLine;
import com.xunfang.bdpf.experiment.plan.entity.PlanLineExample;
import com.xunfang.bdpf.experiment.plan.mapper.PlanLineMapper;
import com.xunfang.bdpf.experiment.plan.service.PlanService;
import com.xunfang.bdpf.experiment.task.entity.Group;
import com.xunfang.bdpf.experiment.task.entity.GroupExample;
import com.xunfang.bdpf.experiment.task.entity.Task;
import com.xunfang.bdpf.experiment.task.entity.TaskExample;
import com.xunfang.bdpf.experiment.task.mapper.GroupMapper;
import com.xunfang.bdpf.experiment.task.service.TaskService;
import com.xunfang.bdpf.login.entity.Servers;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.bdpf.system.user.entity.UserExample;
import com.xunfang.bdpf.system.user.service.UserService;
import com.xunfang.bdpf.vmstemplates.introduction.entity.Flavors;
import com.xunfang.bdpf.vmstemplates.introduction.entity.Images;
import com.xunfang.bdpf.vmstemplates.introduction.entity.IntroData;
import com.xunfang.bdpf.vmstemplates.template.entity.ImageParms;
import com.xunfang.bdpf.vmstemplates.virtual.entity.ImageEntity;
import com.xunfang.bdpf.vmstemplates.virtual.entity.ServerEntity;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.Identities;
import com.xunfang.utils.OpenStackApi;
import com.xunfang.utils.PropertiesUtil;
import com.xunfang.utils.Rest;
/***
 * 
 * @ClassName IntroductionController
 * @Description: 虚拟机创建控制器
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年7月13日 上午10:58:38
 * @version V1.0
 */
@Controller
@RequestMapping(value = "introduction")
public class IntroductionController {
	
	@Autowired
	//用户Service接口
	private UserService userService;
	
	@Autowired
	//学生虚拟机关联Service接口
	private StudentVirtualService studentVirtualService;
	
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
	
	/**
	 * @Fields log : 输出日志
	 */
	private static Logger log = Logger.getLogger(IntroductionController.class);

	/**
	 * 
	 * @Title: index 
	 * @Description: 打开入门页面
	 *  @param @param request 
	 *  @param @param session 
	 *  @param @return 设定文件 
	 * @return String 
	 * 返回类型 @throws
	 */
	@RequestMapping(value = "")
	public String index(HttpServletRequest request, HttpSession session) {
		return "vmstemplates/introduction/index";

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
		String token = (String)session.getAttribute("token");
		//查询开发类模板id
				List<Flavors> flavorsList = OpenStackApi.cloudFlavors("",token);
				for (Flavors flavors : flavorsList) {
					if(flavors.getName().equals("develop")){
						model.addAttribute("develop", flavors.getId());
					}
				}
		return "vmstemplates/introduction/add";
	}
	
	/**
	 * 
	  * @Title: getData
	  * @Description: 获取 创建虚拟机所需要的数据
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
		User  user = (User) session.getAttribute("loginuser");
		List<Task> taskList = new ArrayList<Task>();
		String account = null;
		if(user != null){
			if(user.getUserType()!=2){
				account = user.getAccount();
			}
			taskList = taskService.selectTaskByAccount(account);
		}
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
	  *  @param taskId 任务id
	  *  @param groupId 分组id
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public Feedback save(String name,String imageId,String flavorId,String keyName,String securityGroupsName,String adminPass,String availabilityZone,String count ,String account ,String[] id , String taskId,String groupId,HttpSession session) {
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
			ServerEntity serverEntity = new ServerEntity();
			serverEntity.setName(name);
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
			/*if(user.getUserType()==1){
				studentVirtual.setClassId(list.get(0).getClassId());//班级ID
			}*/
			if(groupId.equals("")){
				studentVirtual.setType(1);//虚拟机类型：0 分组实验 1 个人实验  2 开放实验
			}else if(!groupId.equals("")){
				studentVirtual.setType(0);//虚拟机类型：0 分组实验 1 个人实验  2 开放实验
			}else if(taskId.equals("")){
				studentVirtual.setType(2);//虚拟机类型：0 分组实验 1 个人实验  2 开放实验
			}
			studentVirtual.setImageId(serverEntity.getImageId());//镜像模板ID
			
			resultnum += studentVirtualService.insert(studentVirtual);
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
	 * @Title: toAdd 
	 * @Description: 跳转添加用户页面 
	 * @param @param model 
	 * @param @return 设定文件
	 * @return String 返回类型 
	 * @throws
	 */
	@RequestMapping(value = "to-import")
	public String toAdd(Model model) {
		return "vmstemplates/introduction/import-template";
	}
	
	
	/**
	 * 
	  * @Title: imporT
	  * @Description: 导入模板文件
	  * @param @param file   文件对象
	  * @param @param course 数据对像
	  * @param @param request
	  * @param @param response
	  * @param @param session
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return Feedback    返回类型
	  * @throws
	 */
	@RequestMapping(value = "imports")
	@ResponseBody
	public Feedback imports(@RequestParam(value = "file", required = false) MultipartFile file,
			ImageParms image,String description, HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		request.setCharacterEncoding("utf-8");
		Feedback feedback = null;
		String token = (String)session.getAttribute("token");
		if(token==null||"".equals(token)){
			feedback = new Feedback(false, "创建虚拟机失败");
			return feedback;
		}
		String report="template";//虚拟机模板模板
		try {
		    String path = PropertiesUtil.getValue("DIR_FILE")+"/"+report+"/";
		    String fileName = file.getOriginalFilename();
			String name =fileName.substring(fileName.lastIndexOf("."), fileName.length());
			File targetFile = new File(path, fileName);
			targetFile.mkdirs();
			file.transferTo(targetFile);
			image.setImageUrl(PropertiesUtil.getValue("ADDRESS")+"/res/"+report+"/"+fileName);
			
			ImageEntity imageEntity = new ImageEntity(); 
			imageEntity.setImageUrl(image.getImageUrl());
			imageEntity.setName(image.getName());
			//测试上传模板信息接口
			Images cloudImageSave = OpenStackApi.cloudImageSave(imageEntity,token);
			System.out.println("imageId : " + cloudImageSave.getId());
			Rest.addImageDesc(cloudImageSave.getId() , description);
			feedback = new Feedback(true, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			feedback = new Feedback(false, "保存失败！");
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
		
		//分组列表
		GroupExample groupExample= new GroupExample();
		groupExample.createCriteria().andTaskIdEqualTo(taskId);
		List<Group> groupList = groupMapper.selectByExample(groupExample);
		return groupList;
	}
}
