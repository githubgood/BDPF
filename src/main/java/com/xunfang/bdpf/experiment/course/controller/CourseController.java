package com.xunfang.bdpf.experiment.course.controller;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xunfang.bdpf.experiment.course.entity.Course;
import com.xunfang.bdpf.experiment.course.entity.CourseExample;
import com.xunfang.bdpf.experiment.course.service.CourseService;
import com.xunfang.bdpf.experiment.plan.entity.PlanLine;
import com.xunfang.bdpf.experiment.plan.service.PlanService;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.bdpf.vmstemplates.introduction.entity.Images;
import com.xunfang.bdpf.vmstemplates.introduction.entity.IntroData;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.FileMD5;
import com.xunfang.utils.OpenStackApi;
import com.xunfang.utils.Page;
import com.xunfang.utils.PropertiesUtil;
import com.xunfang.utils.office.util.Office2PdfUtils;


/**
 * 
 * @ClassName CourseController
 * @Description: 课程资源控制器
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月7日 上午10:27:48
 * @version V1.0
 */
@Controller
@RequestMapping(value = "course")
public class CourseController {
	/**
	 * @Fields log : 输出日志
	 * 
	 */
	private static Logger log = Logger.getLogger(CourseController.class);

	@Autowired
	//课程资源管理Service接口
	private CourseService courseService;
	
	@Autowired
	//教学计划管理Service接口
	private PlanService planService;

	/**
	 * 
	  * @Title: exp
	  * @Description: 课程资源管理跳转方法，进入查询页
	  *  @param request HttpServletRequest http请求
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "")
	public String exp(HttpServletRequest request, HttpSession session) {
		if(session.getAttribute("image")==null){
			this.getData(session);
		}
		return "experiment/course/index";
	}

	/**
	 * 
	  * @Title: toAdd
	  * @Description: 课程资源管理跳转方法，进入添加页
	  *  @param model Model 课程资源model
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-add")
	public String toAdd(Model model, HttpSession session) {
		//获取当前数据库中最大的课程资源序号
		model.addAttribute("maxxh", courseService.queryMaxXh());
		//获取当前课程资源信息
		CourseExample courseExample=new CourseExample();
		courseExample.createCriteria().andFileTypeEqualTo("0");
		List<Course> list=courseService.selectByExample(courseExample);
		if(list.size()==0){
			list=null;
		}
		model.addAttribute("courselist",list );
		return "experiment/course/add";
	}
	
	/**
	 * 
	  * @Title: getData
	  * @Description: 获取模板名称
	  *  @param model
	  *  @param session
	  *  @return  IntroData 返回类型
	  * @throws
	 */
	private void getData(HttpSession session) {
		Map<String,String> map=new HashMap<String,String>();
		String token = (String)session.getAttribute("token");
		IntroData introData = new IntroData();
		List<Images> images = null;
		if(token!=null&&!"".equals(token)){
			images=OpenStackApi.cloudImage("",token);
			if(images != null){
				introData.setImageList(images);
				for(int i=0;i<images.size();i++){
					map.put(images.get(i).getId(), images.get(i).getName());
				}
				introData.setImageList(images);
			}
		}
		session.setAttribute("image", introData);
		session.setAttribute("imageMap", map);
	}	

	/**
	 * 
	  * @Title: toEdit
	  * @Description: 课程资源管理跳转方法，进入修改页
	  *  @param id String 当前课程资源主键ID
	  *  @param model Model 课程资源model
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-edit/{id}")
	public String toEdit(@PathVariable String id, Model model) {
		CourseExample courseExample=new CourseExample();
		model.addAttribute("courselist", courseService.selectByExample(courseExample));
		model.addAttribute("course", courseService.selectByPrimaryKey(id));
		return "experiment/course/edit";
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
	public Page<Course> listCourseByPage(String keywords, String pageNo, String pageSize,HttpSession session) {
		int skip = Integer.parseInt(PropertiesUtil.getValue("page.pageNo"));//从第几条开始取数据
		int max = Integer.parseInt(PropertiesUtil.getValue("page.pageSize"));//每页显示多少条数据
		Page<Course> page = new Page<Course>();//Page类
		List<Course> list = new ArrayList<Course>();//课程资源列表
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
//		User  user = (User)session.getAttribute("loginuser");
		String account = null;
//		if(user.getUserType() != 2){
//			account = user.getAccount();
//		}
		try {
			page.setTotalCount(courseService.getCourseCount(keywords,account));//根据查询条件获取课程资源总条数
			list = courseService.queryCourseByPage(keywords, skip, max,account);//根据查询条件获取课程资源列表
			//过滤模板id	
			Map<String,String> map = (Map<String, String>) session.getAttribute("imageMap");
			if(map != null && map.size()>0){
				for(int i=0;i<list.size();i++){
					if(list.get(i).getCourseMirrorId()!=null && list.get(i).getCourseMirrorId().length()>0){
						if(map.get(list.get(i).getCourseMirrorId()) != null){
							list.get(i).setCourseMirrorName(map.get(list.get(i).getCourseMirrorId()).toString());
						}else{
							list.get(i).setCourseMirrorName("");
						}
					}
				}
			}
			page.setResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return page;
	}

	/**
	 * 
	  * @Title: delete
	  * @Description: 课程资源管理删除方法
	  *  @param ids String 课程资源主键ID
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Feedback delete(String ids) {
		Feedback feedback = null;
		if (StringUtils.isBlank(ids)) {//课程资源主键ID判空
			feedback = new Feedback(false, "删除失败!");
			return feedback;
		}
		try {
			String[] idArr = ids.split(",");
			Map<String, Object> param = new HashMap<String, Object>();;
			//课程资源如果在课程计划中已经存在给予提示
			for(int i=0;i<idArr.length;i++){
				param.put("courseResourceId", idArr[i]);
				List<PlanLine> list= planService.queryPlanLineById(param);//根据ID查询教学计划列表
				if(list.size()>0){
					feedback = new Feedback(false, "请先删除关联的课程计划信息!");
					return feedback;
				}
			}
			//批量删除
			CourseExample example = new CourseExample();
			example.createCriteria().andIdIn(Arrays.asList(idArr));
			//查询数据对文件的删除
			List<Course> list=courseService.selectByExample(example);
			String path1="";
			String path2="";
			File file1 =null;
			File file2 =null;
			for(int k=0;k<list.size();k++){
				 path2=PropertiesUtil.getValue("DIR_FILE")+StringUtils.substringAfterLast(list.get(k).getPath(), "res");
				 file2 = new File(path2);
				if(list.get(k).getFileType()==0){
					path1= PropertiesUtil.getValue("DIR_FILE")+"/doc/"+list.get(k).getMd5()+".doc";
					   file1 = new File(path1);
					   if(file1.exists()){
					      file1.delete();
					   }
				}
				if(file2.exists()){
				  file2.delete();
			   }	
			}
			courseService.deleteByExample(example);//批量删除课程资源管理信息
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
	  * @Title: checkCourseXh
	  * @Description: 课程资源管理删除方法
	  *  @param xh String 课程资源序号
	  *  @param id String 课程资源主键ID
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value="checkXh", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkCourseXh(int xh, String id){
		boolean falg = true;
		if(id.equals("undefined")||"".equals(id)){//课程资源主键ID判空已经未定义判断
			id = "";
		}
		try {
			//根据课程资源主键ID和序号查询是否存在重复的课程资源序号
			CourseExample courseExample = new CourseExample();
		    courseExample.createCriteria().andIdNotEqualTo(id).andXhEqualTo(xh);
		   List<Course> list= courseService.selectByExample(courseExample);
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
	  * @Title: checkName
	  * @Description: 验证课程资源名称
	  *  @param title
	  *  @param id
	  *  @return  boolean 返回类型
	  * @throws
	 */
	@RequestMapping(value="checkName", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkName(String title, String id){
		boolean falg = true;
		if(id.equals("undefined")||"".equals(id)){//课程资源主键ID判空已经未定义判断
			id = "";
		}
		try {
			//根据课程资源主键ID和序号查询是否存在重复的课程资源序号
			CourseExample courseExample = new CourseExample();
		    courseExample.createCriteria().andIdNotEqualTo(id).andTitleEqualTo(title);
		   List<Course> list= courseService.selectByExample(courseExample);
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
	  * @Title: save
	  * @Description:课程资源数据保存
	  *  @param file
	  *  @param course
	  *  @param request
	  *  @param response
	  *  @param session
	  *  @return
	  *  @throws Exception  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public Feedback save(@RequestParam(value = "file", required = false) MultipartFile file,
			Course course,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		request.setCharacterEncoding("utf-8");
		Feedback feedback = null;
		String report="";
		try {
		if(StringUtils.isBlank(course.getId())){
			User user=(User) session.getAttribute("loginuser");
			if(user == null){
				feedback = new Feedback(false, "保存失败！");
	    		return feedback;
			}
			course.setCreateUser(user.getAccount());
			course.setCreateTime(new Date());
	        String md5= FileMD5.getMD5Gt2G(file.getInputStream());
			//验证文件是否存已经存在
	    	CourseExample example = new CourseExample();
	    	example.createCriteria().andMd5EqualTo(md5);
	    	List<Course> list= courseService.selectByExample(example);
	    	if(list.size()>0){
	    		feedback = new Feedback(false, "文件名已经存在！");
	    		return feedback;
	    	}
			if(course.getFileType()==0){
				//文档
				report="doc";
			}else if(course.getFileType()==1){
				//视频
				report="video";
			}else if(course.getFileType()==2){
				//图片
				report="picture";
			}else if(course.getFileType()==3){
				//数据
				report="data";
			}else if(course.getFileType()==4){
				//工具
				report="tool";
			}else if(course.getFileType()==5){
				//其他
				report="other";
			}
		    String path = PropertiesUtil.getValue("DIR_FILE")+"/"+report+"/";
		   
		    String fileName = file.getOriginalFilename();
			String name = md5+fileName.substring(fileName.lastIndexOf("."), fileName.length());
			File targetFile = new File(path, name);
			 System.err.println(path+"::"+targetFile+":::"+name);
			targetFile.mkdirs();
			file.transferTo(targetFile);
			if(course.getFileType()==0){
				if(name.endsWith(".pdf")){
					course.setPath(PropertiesUtil.getValue("ADDRESS")+"/res/"+report+"/"+name);
				}else{
					boolean flag = new Office2PdfUtils().toConversion(name, file.getInputStream(), path+name);
					if(flag){//转换成功
						course.setPath(PropertiesUtil.getValue("ADDRESS")+"/res/"+report+"/"+name+".pdf");
					}else{//转换失败
						//course.setPath(PropertiesUtil.getValue("ADDRESS")+"/res/"+report+"/"+name);
						feedback = new Feedback(false, "文件转换失败！");
						return feedback;
					}
				}
			}else{
				course.setPath(PropertiesUtil.getValue("ADDRESS")+"/res/"+report+"/"+name);
			}
			course.setMd5(md5);
	      }
		   courseService.save(course);//保存
		   feedback = new Feedback(true, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			feedback = new Feedback(false, "保存失败！"); 
	 }
		return feedback;
	}

	
}