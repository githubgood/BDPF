package com.xunfang.bdpf.experiment.exp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openstack4j.model.common.ActionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xunfang.bdpf.experiment.course.entity.Course;
import com.xunfang.bdpf.experiment.course.entity.CourseExample;
import com.xunfang.bdpf.experiment.course.service.CourseService;
import com.xunfang.bdpf.experiment.exp.entity.ExpCloud;
import com.xunfang.bdpf.experiment.grade.entity.Grade;
import com.xunfang.bdpf.experiment.grade.entity.GradeExample;
import com.xunfang.bdpf.experiment.grade.entity.GradeExample.Criteria;
import com.xunfang.bdpf.experiment.grade.entity.StudentVirtual;
import com.xunfang.bdpf.experiment.grade.service.GradeService;
import com.xunfang.bdpf.experiment.grade.service.StudentVirtualService;
import com.xunfang.bdpf.experiment.task.entity.TaskExample;
import com.xunfang.bdpf.experiment.task.service.TaskService;
import com.xunfang.bdpf.login.entity.Servers;
import com.xunfang.bdpf.system.time.entity.Time;
import com.xunfang.bdpf.system.time.service.TimeService;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.FileMD5;
import com.xunfang.utils.OpenStackApi;
import com.xunfang.utils.Page;
import com.xunfang.utils.PropertiesUtil;
import com.xunfang.utils.Rest;
import com.xunfang.utils.office.util.Office2PdfUtils;

/**
 * 
 * @ClassName ExpController
 * @Description: 课程实验控制器
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年6月27日 下午4:48:35
 * @version V1.0
 */
@Controller
@RequestMapping(value = "exp")
public class ExpController {

	/**
	 * @Fields log : 输出日志
	 * 
	 */
	private static Logger log = Logger.getLogger(ExpController.class);
	@Autowired
	//课程资源Service接口
	private GradeService gradeService;
	@Autowired
	//任务管理Service接口
	private TaskService taskService;
	@Autowired
	//课程资源管理Service接口
	private CourseService courseService;
	@Autowired
	//实验时间设置Service接口
	private TimeService timeService;
	@Autowired
	//班级管理Service接口
	private StudentVirtualService studentVirtualService;
	/**
	 * 
	  * @Title: exp
	  * @Description: 课程实验管理跳转方法，进入查询页
	  *  @param request HttpServletRequest http请求
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "")
	public String exp(Model model,HttpServletRequest request, HttpSession session) {
		//获取所有的任务信息
		TaskExample taskExample=new TaskExample();
        User user = (User) session.getAttribute("loginuser");
        String classId = user.getClassId();
		model.addAttribute("task", taskService.queryTaskByClass(classId));
		return "experiment/exp/index";
	}
	
	/**
	 * 
	  * @Title: listExpByPage
	  * @Description: 课程实验管理查询方法，带分页
	  * @param @param taskId   任务id
	  * @param @param classId  班级id
	  * @param @param studentId学生id
	  * @param @param keywords 课程实验名称
	  * @param @param pageNo   从第几条开始取数据
	  * @param @param pageSize 每页显示多少条数据
	  * @param @return    设定文件
	  * @return Page<Grade>    返回类型
	  * @throws
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Page<Grade> listExpByPage(String taskId,String keywords, String pageNo, String pageSize,HttpServletRequest request) {
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
			String classId = null;
			String studentId = null;
			//如果userid为空，则重定向到指定的路径
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("loginuser");
			if (user != null) {
				studentId = user.getAccount();
				classId = user.getClassId();
			}
			//获取数据总数
			page.setTotalCount(gradeService.getExpListCount(taskId, classId, studentId, keywords, skip, max));//根据查询条件获取课程资源总条数
			//获取分页数据
			list = gradeService.queryExpListByPage(taskId, classId, studentId,keywords, skip, max);//根据查询条件获取课程资源列表
			page.setResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return page;
	}

	
	/**
	 * 
	  * @Title: toSend
	  * @Description: 发送实验报告页面
	  * @param @param id
	  * @param @param model
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value="to-send/{id}")
	public String toSend(@PathVariable String id, Model model){
		//通过id获取 实验课程数据
		Grade grade = gradeService.selectExpById(id);
		model.addAttribute("grade", grade);
		return "experiment/exp/send";
	}
	
	/**
	 * 
	  * @Title: 进入实验页面
	  * @Description: TODO
	  * @param @param id
	  * @param @param model
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value="to-exp/{id}/{type}")
	public String toDetailDoc(@PathVariable String id,@PathVariable int type,Model model,HttpServletRequest request){
		//获取到上传实验报告文件路径
		Grade grade = gradeService.selectExpById(id);
		//获取组下面的学生列表
		String account = "";
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginuser");
		if(user==null){
			return "";
		}
		//如果分组id为空，则为个人实验
		if((grade.getGroupId() == null||"".equals(grade.getGroupId()))&&grade.getStudentId().equals(user.getAccount())){
			account = user.getAccount();
		}
		//获取分组数据或个人数据
		List<User> listUser = gradeService.selectByExampleGroupName(grade.getCourseTaskId(), grade.getClassId(), grade.getGroupId(),type,account);
		account = user.getAccount();
		//获取实验工具列表
		CourseExample courseExample = new CourseExample();
		courseExample.createCriteria().andParentIdEqualTo(grade.getCourseResourceId());
		List<Course> listCourse = courseService.selectByExample(courseExample);
		//获取实验时间  “1”是固定值 
		Time times = timeService.selectByPrimaryKey("1");
		//虚拟机ID
		String vmId = "";
		for (User user2 : listUser) {
			vmId += user2.getId()+",";
		}
		if(vmId!=null&&!"".equals(vmId)&&vmId.length()>0){
			vmId = vmId.substring(0,vmId.length()-1);
		}
		//保存
		model.addAttribute("grade", grade);//课程资源
		model.addAttribute("listUser", listUser);//分组人员
		model.addAttribute("listCourse", listCourse);//工具
		model.addAttribute("times", times);//实验时间设置数据
		model.addAttribute("vmId", vmId);//分组下的虚拟机id
		
		return "experiment/exp/to_exp";
	}
	
	/**
	 * 
	  * @Title: getExpInfo
	  * @Description: 获取实验地址
	  * @param @param id
	  * @param @param request
	  * @param @return    设定文件
	  * @return List<ExpCloud>    返回类型
	  * @throws
	 */
	@RequestMapping(value="getExpInfo")
	@ResponseBody
	public List<ExpCloud> getExpInfo(String id,String vmId,String groupId,HttpServletRequest request,HttpSession session){
		String token = (String)session.getAttribute("token");
		if(token==null||"".equals(token)){
			return null;
		}
		Map<String, String> studentMap = new HashMap<String, String>();
		User  user = (User) session.getAttribute("loginuser");
		if(user==null){
			return null;
		}else{
			if(user.getUserType() == 2){//超级管理员，置空账号和班级信息
				studentMap.put("type", user.getUserType()+"");
			}
		}
		List<String> classIdlist =null;
		if(user.getUserType()==0){//教师
			String[] strs=user.getClassId().split(",");
			if(user.getClassId().split(",").length>0){
				strs=user.getClassId().split(",");
			}else{
				strs=new String[]{user.getClassId()};
			}
			classIdlist = Arrays.asList(strs);
		}
		List<StudentVirtual> studentVirtuals = null;
		//如果分组id为空，则为个人实验
		studentVirtuals = studentVirtualService.selectByClassIdAndAccount(user.getAccount(),groupId, classIdlist);
		
		if(studentVirtuals == null){
			return null;
		}else{
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
		if(vmId!=null&&!"".equals(vmId)){
			return Rest.expCloudList(cloudServerMap,vmId,token);
		}else{
			return null;
		}
		
	}
	
	/**
	 * 
	  * @Title: updateTime
	  * @Description: 通过id更新实验剩余时间和加时次数
	  * @param @param grade   判断条件 
	  * @param @param request 更新的数据对象 
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value="updateTime")
	@ResponseBody
	public void updateTime(Grade grade,HttpServletRequest request){
		//通过id更新实验剩余时间和加时次数
		GradeExample gradeExample = new GradeExample();
		if(grade.getGroupId() == null ||"".equals(grade.getGroupId())){
			gradeExample.createCriteria().andIdEqualTo(grade.getId());
		}else{
			gradeExample.createCriteria().andGroupIdEqualTo(grade.getGroupId());
		}
//		//更新数据
		grade.setId(null);
		gradeService.updateByExampleSelective(grade, gradeExample);
	}
	
	/**
	 * 
	  * @Title: updateLastTime
	  * @Description: 通过id更新实验结束时间
	  * @param @param grade      判断条件
	  * @param @param request    更新对象
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value="updateLastTime")
	@ResponseBody
	public Feedback updateLastTime(Grade grade,HttpServletRequest request,HttpSession session){
		//通过id更新实验结束时间
		//设置当前时间为结束时间
		Feedback feedback = null;
		String token = (String)session.getAttribute("token");
		if(token==null||"".equals(token)){
			feedback = new Feedback(false, "关闭虚拟机失败!");
			return feedback;
		}
		grade.setLastAttendance(new Date());
		GradeExample gradeExample = new GradeExample();
		if(grade.getGroupId() == null ||"".equals(grade.getGroupId())){
			gradeExample.createCriteria().andIdEqualTo(grade.getId());
		}else{
			gradeExample.createCriteria().andGroupIdEqualTo(grade.getGroupId());
		}
		//关闭虚拟机
		String ids = "";
		String type = "STOP";//STOP
		ids = grade.getComment();
		ActionResponse msg = OpenStackApi.batchCloudServerAction(type, ids,token);
		
//		//更新数据
		grade.setId(null);
		gradeService.updateByExampleSelective(grade, gradeExample);
		if(msg.getCode()==200){
            feedback = new Feedback(true, "关闭虚拟机成功!");
        }else{
      	  	feedback = new Feedback(false, "关闭虚拟机失败!");
       }
		return feedback;
	}
	
	/**
	 * 
	  * @Title: updateStartTime
	  * @Description: TODO
	  * @param @param grade
	  * @param @param request    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value="updateStartTime")
	@ResponseBody
	public void updateStartTime(Grade grade,HttpServletRequest request){
		//通过id更新实验开始时间
		//设置当前时间为开始时间
		grade.setBeginAttendance(new Date());
		GradeExample gradeExample = new GradeExample();
		if(grade.getGroupId() == null ||"".equals(grade.getGroupId())){
			gradeExample.createCriteria().andIdEqualTo(grade.getId());
		}else{
			gradeExample.createCriteria().andGroupIdEqualTo(grade.getGroupId());
		}
//		//更新数据
		grade.setId(null);
		gradeService.updateByExampleSelective(grade, gradeExample);
	}
	
	
	
	/**
	 * 
	  * @Title: upload
	  * @Description: 上传实验报告
	  * @param @param file   文件对象
	  * @param @param course 实验内容
	  * @param @param request 
	  * @param @param response
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return Feedback    返回类型
	  * @throws
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public Feedback upload(@RequestParam(value = "file", required = false) MultipartFile file,
			Grade grade,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//设置字符集编码。防止乱码
		request.setCharacterEncoding("utf-8");
		Feedback feedback = null;
		try {
		if(!StringUtils.isBlank(grade.getId())){
	        String md5= FileMD5.getMD5Gt2G(file.getInputStream());
			//验证文件是否存已经存在
	        GradeExample example = new GradeExample();
	        //验证md5值，防止一个文件多次提交
	    	example.createCriteria().andMd5EqualTo(md5);
	    	List<Grade> list= gradeService.selectByExample(example);
	    	if(list.size()>0){
	    		feedback = new Feedback(false, "文件名已经存在！");
	    		return feedback;
	    	}
	    	//判断是否已经上传文件
	    	GradeExample example2 = new GradeExample();
	    	Criteria createCriteria = example2.createCriteria();
	    	createCriteria.andIdEqualTo(grade.getId());
	    	createCriteria.andGroupIdEqualTo(grade.getGroupId());
	    	List<Grade> list2= gradeService.selectByExample(example2);
	    	if(list2 != null){
	    		for (Grade grade2 : list2) {
	    			String path = grade2.getReportBook();
	    			if(path != null && !"".equals(path)){
	    				//找到文件路径，并删除
		    			String replace = path.replace(PropertiesUtil.getValue("ADDRESS")+"/res", "");
		    			 String lodPath = PropertiesUtil.getValue("DIR_FILE")+replace;
		    			File files = new File(lodPath);
		    			if(files.exists()){
		    				files.delete();
		    				if(lodPath.endsWith(".pdf")){
		    					String substring = lodPath.substring(0, lodPath.length()-4);
		    					File file2 = new File(substring);
		    					if(file2.exists()){
		    						file2.delete();
		    					}
		    				}
		    			}
	    			}
				}
	    	}
	    	
	    	String path;
	    	//文件要上传的位置
	    	if(!StringUtils.isBlank(grade.getGroupId())){//分组的实验报告，要加上分组id
	    		path = PropertiesUtil.getValue("DIR_FILE")+"/report/"+grade.getGroupId();
	    	}else{
	    		path = PropertiesUtil.getValue("DIR_FILE")+"/report";
	    	}
		    String fileName = file.getOriginalFilename();//上传文件的名称，包含后辍名
			String name = md5+fileName.substring(fileName.lastIndexOf("."), fileName.length());
			File targetFile = new File(path, name);
			targetFile.mkdirs();
			file.transferTo(targetFile);
				boolean flag = new Office2PdfUtils().toConversion(name, file.getInputStream(), path+"/"+name);
				if(flag){//转换成功
					//保存转换后的文件路径
					if(!StringUtils.isBlank(grade.getGroupId())){//分组的实验报告，要加上分组id
						grade.setReportBook(PropertiesUtil.getValue("ADDRESS")+"/res/report/"+grade.getGroupId()+"/"+name+".pdf");
					}else{
						grade.setReportBook(PropertiesUtil.getValue("ADDRESS")+"/res/report/"+name+".pdf");
					}
					//设置实验报告提交状态为已提交。 
					grade.setReportStatus(1);
				}else{//转换失败
					//course.setPath(PropertiesUtil.getValue("ADDRESS")+"/res/"+report+"/"+name);
					feedback = new Feedback(false, "文件转换失败！");
					return feedback;
				}
				grade.setMd5(md5);
	      }
		//执行更新操作
		GradeExample example = new GradeExample();
		if(!StringUtils.isBlank(grade.getGroupId())){//如果组id不为空。就更新组下面的所有数据
			example.createCriteria().andGroupIdEqualTo(grade.getGroupId());
		}else{//如果组为空，就更新id对应 的数据
			example.createCriteria().andIdEqualTo(grade.getId());
		}
		grade.setId(null);
		gradeService.updateByExampleSelective(grade, example);
		   feedback = new Feedback(true, "文件上传成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			feedback = new Feedback(false, "文件上传失败！");
	 }
		return feedback;
	}
}
