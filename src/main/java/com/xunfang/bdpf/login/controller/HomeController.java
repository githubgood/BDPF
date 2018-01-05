package com.xunfang.bdpf.login.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.openstack4j.api.compute.ComputeService;
import org.openstack4j.model.compute.AbsoluteLimit;
import org.openstack4j.model.compute.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunfang.bdpf.experiment.grade.entity.StudentVirtual;
import com.xunfang.bdpf.experiment.grade.service.StudentVirtualService;
import com.xunfang.bdpf.login.entity.HomeInfo;
import com.xunfang.bdpf.login.entity.Servers;
import com.xunfang.bdpf.login.service.ServiceFactory;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.bdpf.vmstemplates.introduction.entity.Networks;
import com.xunfang.utils.OpenStackApi;
/** * 
 * @ClassName HomeController
 * @Description: 资源监控控制器
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @version V1.0
 */
@Controller
@RequestMapping(value = "home")
public class HomeController {

		@Autowired
		//班级管理Service接口
		private StudentVirtualService studentVirtualService;
	 
	/**
	 * 
	  * @Title: index
	  * @Description: 跳转到资源监控页面
	  * @param @param model
	  * @param @param request
	  * @param @param session
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "")
	public String index(Model model ,HttpServletRequest request, HttpSession session) {
		return "home";
	}
	
	/**
	 * 
	  * @Title: getCloudInfo
	  * @Description: 获取云服务器数据
	  * @param @param request
	  * @param @return    设定文件
	  * @return Map<String,List<Server>>    返回类型
	  * @throws
	 */
	@RequestMapping(value="getCloudInfo")
	@ResponseBody
	public List<Servers> getCloudInfo(HttpServletRequest request, HttpSession session){
		String token = (String)session.getAttribute("token");
		if(token==null||"".equals(token)){
			return null;
		}
		Map<String, String> studentMap = new HashMap<String, String>();
		List<StudentVirtual> studentVirtuals = new ArrayList<StudentVirtual>();
		User  user = (User) session.getAttribute("loginuser");
		if(user==null){
			return null;
		}else{
			String account = null;
			List<String> classIdlist =null;
			if(user.getUserType()==2){//管理员
				account  = null;
				classIdlist = null;
			}else{//学生和教师
				String[] strs=user.getClassId().split(",");
				if(user.getClassId().split(",").length>0){
					strs=user.getClassId().split(",");
				}else{
					strs=new String[]{user.getClassId()};
				}
				classIdlist = Arrays.asList(strs);
				if(user.getUserType() == 1){
					classIdlist  = null;
				}
				account = user.getAccount();
			}
			studentVirtuals = studentVirtualService.selectByClassIdAndAccount(account,null, classIdlist);
			for (StudentVirtual studentVirtual : studentVirtuals) {
				studentMap.put(studentVirtual.getVirtualId(), studentVirtual.getVirtualId());
			}
			studentMap.put("states", "");
		}
		//通过云主机名称和授权码获取云主机信息
		Map<String, List<Servers>> cloudServerMap = OpenStackApi.cloudServerList("",token,studentMap);
		if(cloudServerMap==null){
			return null;
		}
		List<Servers> statuslist = cloudServerMap.get("status");
		if(statuslist==null){
			return null;
		}
		
		return statuslist;
	}
	
	
	/**
	 * 
	  * @Title: getNetworkInfo
	  * @Description: TODO 获取首页显示 信息
	  * @param @param request
	  * @param @param session
	  * @param @return    设定文件
	  * @return HomeInfo    返回类型
	  * @throws
	 */
	@RequestMapping(value="getNetworkInfo")
	@ResponseBody
	public HomeInfo getNetworkInfo(HttpServletRequest request, HttpSession session){
		String token = (String)session.getAttribute("token");
		int start = 0;//运行中
		int stop = 0;//停止
		int other = 0;//其它
		HomeInfo homeInfo = new HomeInfo();
		//查询开发类模板id
				List<Networks> networksList = OpenStackApi.cloudNetwork(token);
				homeInfo.setAllsize(networksList.size()+"");
				for (Networks networks : networksList) {
					String status = networks.getStatus();
					if(status.equals("ACTIVE")){//运行中
						start++;
					}else if(status.equals("STOP")){//停止
						stop++;
					}else{//其它
						other++;
					}
				}
				homeInfo.setStart(start+"");//运行中数量
				homeInfo.setStop(stop+"");//停止数量
				homeInfo.setOther(other+"");//其它数量
				ComputeService computeService = ServiceFactory.computeService(token);
				Limits limits = computeService.quotaSets().limits();
				AbsoluteLimit absolute = limits.getAbsolute();
				//云主机资源信息
				homeInfo.setRam(absolute.getMaxTotalRAMSize()/1024+"");
				homeInfo.setRamUsed(absolute.getTotalRAMUsed()/1024+"");
				homeInfo.setInstances(absolute.getMaxTotalInstances()+"");
				homeInfo.setInstancesUsed(absolute.getTotalInstancesUsed()+"");
				homeInfo.setCores(absolute.getMaxTotalCores()+"");
				homeInfo.setCoresUsed(absolute.getTotalCoresUsed()+"");
		
		return homeInfo;
		
	}
	
}
