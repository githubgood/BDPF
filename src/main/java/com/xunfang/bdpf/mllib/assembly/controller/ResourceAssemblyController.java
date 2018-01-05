package com.xunfang.bdpf.mllib.assembly.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aspose.p2cbca448.re;
import com.xunfang.bdpf.mllib.assembly.entity.ResourceAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.ResourceAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.service.ResourceAssemblyService;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.FileMD5;
import com.xunfang.utils.PropertiesUtil;
import com.xunfang.utils.office.util.Office2PdfUtils;


/**
 * @ClassName AssemblyController
 * @Description: 机械学习首页控制器
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 * @author wjp
 * @date 2017年11月5日 下午16:45:45
 * @version V1.0
 */
@Controller
@RequestMapping(value = "resourceAssembly")
public class ResourceAssemblyController {
	@Autowired
	private ResourceAssemblyService  resourceAssemblyService;
	private static Logger log = Logger.getLogger(ResourceAssemblyController.class);
	
	/**
	 * 
	  * @Title: save
	  * @Description: 案例保存
	  *  @param session HttpSession http缓存
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "fileSave")
	@ResponseBody
	public Feedback save(@RequestParam(value = "file", required = false) MultipartFile file,
			ResourceAssembly resource,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		request.setCharacterEncoding("utf-8");
		Feedback feedback = null;
		try {
		if(StringUtils.isBlank(resource.getId())){
			User user=(User) session.getAttribute("loginuser");
			if(user == null){
				feedback = new Feedback(false, "添加失败！");
	    		return feedback;
			}
			resource.setCreateUser(user.getAccount());
			resource.setCreateTime(new Date());
	        String md5= FileMD5.getMD5Gt2G(file.getInputStream());
			//验证文件是否存已经存在
	        ResourceAssemblyExample example = new ResourceAssemblyExample();
	    	example.createCriteria().andMd5EqualTo(md5);
	    	List<ResourceAssembly> list= resourceAssemblyService.selectByExample(example);
	    	if(list.size()>0){
	    		feedback = new Feedback(false, "文件名已经存在！");
	    		return feedback;
	    	}
		    String path = PropertiesUtil.getValue("DIR_FILE")+"/doc/";
		    String fileName = file.getOriginalFilename();
		    String last=fileName.substring(fileName.lastIndexOf("."), fileName.length());						
		    String name=md5+last;
		    File targetFile = new File(path, name);
			targetFile.mkdirs();
			file.transferTo(targetFile);
			if("doc".equals(last.toLowerCase())){
					boolean flag = new Office2PdfUtils().toConversion(name, file.getInputStream(), path+name);
					if(flag){//转换成功
						resource.setPath(PropertiesUtil.getValue("ADDRESS")+"/res/doc/"+name+".pdf");
					}else{//转换失败
						feedback = new Feedback(false, "文件格式不符合！");
						return feedback;
					}
			}else{
				resource.setPath(PropertiesUtil.getValue("ADDRESS")+"/res/doc/"+name);
			}
						resource.setMd5(md5);
		   }
			resourceAssemblyService.save(resource);//保存
		   feedback = new Feedback(true, "添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			feedback = new Feedback(false, "添加失败！"); 
	 }
		return feedback;
	}
	/**
	 * 
	  * @Title: checkTitle
	  * @Description: 验证案例名称
	  *  @param title案例名称
	  *  @return  boolean 返回类型
	  * @throws
	 */
	@RequestMapping(value="checkTitle")
	@ResponseBody
	public boolean checkTitle(@RequestParam(value = "title")String title){
		boolean falg = true;
		try {
			//验证案例名称或者文件名是否有重复
			ResourceAssemblyExample example = new ResourceAssemblyExample();
			  /*if(file!=null){
				  String md5= FileMD5.getMD5Gt2G(file.getInputStream());
				  example.createCriteria().andMd5EqualTo(md5);  */
			  //}else{
				  example.createCriteria().andTitleEqualTo(title); 
			 // }
			
		   List<ResourceAssembly> list= resourceAssemblyService.selectByExample(example);
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
	  * @Title: list
	  * @Description: 查询所有的案例
	  *  @return  List<ResourceAssembly>
	  * @throws
	 */
	@RequestMapping(value="resourcelist")
	@ResponseBody
	public List<ResourceAssembly> list(HttpSession session){
		User user = (User)session.getAttribute("loginuser");
		if(user == null){
			return null;
		}
		ResourceAssemblyExample resourceAssemblyExample = new ResourceAssemblyExample();
		resourceAssemblyExample.createCriteria().andCreateUserEqualTo(user.getId());
		return resourceAssemblyService.selectByExample(resourceAssemblyExample) ;
	}
	
	/**
	 * 
	  * @Title: selectById
	  * @Description: 浏览案例文件
	  * *  @param id案例主键
	  *  @return  ResourceAssembly
	  * @throws
	 */
	@RequestMapping(value="readFile")
	@ResponseBody
	public ResourceAssembly selectById(@RequestParam(value = "id")String id){
		if (StringUtils.isBlank(id)) {//课程资源主键ID判空
			return new ResourceAssembly();
		}
		ResourceAssemblyExample resourceExample=new ResourceAssemblyExample();
		resourceExample.createCriteria().andIdEqualTo(id);
		ResourceAssembly resource=resourceAssemblyService.selectByPrimaryKey(id);
		
		return resource==null||resource.getPath()==null?new ResourceAssembly():resource;
	}
	/**
	 * 
	  * @Title: resourceDel
	  * @Description: 查询所有的案例
	  * *  @param id案例主键
	  *  @return  Feedback
	  * @throws 
	 */
	@RequestMapping(value="resourceDel")
	@ResponseBody
	public Feedback resourceDel(@RequestParam(value = "id")String id){
		if (StringUtils.isBlank(id)) {//课程资源主键ID判空
			return new Feedback(false,"没有删除的资源！");
		}
		ResourceAssemblyExample resource=new ResourceAssemblyExample();
		resource.createCriteria().andIdEqualTo(id);
		ResourceAssembly assembly=resourceAssemblyService.selectByPrimaryKey(id);
		String stu="删除失败！";
		if(assembly!=null&&assembly.getMd5()!=null){
			 try {	String path1= PropertiesUtil.getValue("DIR_FILE")+"/doc/"+assembly.getMd5()+".doc";
					String path2=PropertiesUtil.getValue("DIR_FILE")+StringUtils.substringAfterLast(assembly.getPath(), "res");
					File file1=new File(path1);
					File file2=new File(path2);
				
					if(file1.exists()){
						 file1.delete();
					}
					 if(file2.exists()){
						 file2.delete();
					}
					if(resourceAssemblyService.deleteByPrimaryKey(id)>0){
						 stu="删除成功";
					 }
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			} 
		}
		Feedback feed=new Feedback(false,stu);
		if(stu.equals("删除成功")){
			feed.setSuccessful(true);
		}
		
		return feed;
	}
}
