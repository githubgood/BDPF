package com.xunfang.bdpf.vmstemplates.template.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.openstack4j.model.common.ActionResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.bdpf.vmstemplates.introduction.entity.Images;
import com.xunfang.bdpf.vmstemplates.template.entity.ImageParms;
import com.xunfang.bdpf.vmstemplates.virtual.entity.ImageEntity;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.OpenStackApi;
import com.xunfang.utils.Page;
import com.xunfang.utils.PropertiesUtil;
import com.xunfang.utils.Rest;
/**
 * 
 * @ClassName TemplateController
 * @Description: 虚拟机模板-模板控制层
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年6月19日 下午5:02:52
 * @version V1.0
 */
@Controller
@RequestMapping(value = "template")
public class TemplateController {

	/**
	 * @Fields log : 输出日志
	 */
	private static Logger log = Logger.getLogger(TemplateController.class);

	/**
	 * 
	 * @Title: index 
	 * @Description: 打开模板页面
	 *  @param @param request 
	 *  @param @param session 
	 *  @param @return 设定文件 
	 * @return String 
	 * 返回类型 @throws
	 */
	@RequestMapping(value = "")
	public String index(HttpServletRequest request, HttpSession session) {
		//获取用户数据
		User user = (User) session.getAttribute("loginuser");
		if(user == null){
			return "login/login";
		}
		if(user.getUserType() == 1){//老师
			return "vmstemplates/template/index";
		}else{//学生
			return "vmstemplates/template/index_teacher";
		}

	}
	
	/**
	 * 
	  * @Title: listExpByPage
	  * @Description: 获取虚拟机模板列表数据
	  * @param @return    设定文件
	  * @return Page<Image>    返回类型
	  * @throws
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Page<Images> listExpByPage(String keywords,HttpServletRequest request, HttpSession session) {
		Page<Images> page = new Page<Images>();//Page类
		String token = (String)session.getAttribute("token");
		if(token==null||"".equals(token)){
			return page;
		}
		List<Images> list = new ArrayList<Images>();//课程资源列表
		try {
			list = OpenStackApi.cloudImage(keywords,token);//根据查询条件获取课程资源列表
			if(list == null){
				return page;
			}
			page.setTotalCount(list.size());
			page.setResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return page;
	}

	/**
	 * 
	 * @Title: toAdd 
	 * @Description: 跳转添加模板页面 
	 * @param @param model 
	 * @param @return 设定文件
	 * @return String 返回类型 
	 * @throws
	 */
	@RequestMapping(value = "to-add")
	public String toAdd(Model model) {
		return "vmstemplates/template/add";
	}
	
	/**
	 * 
	 * @Title: toEdit 
	 * @Description: 跳转修改模板页面 
	 * @param @param model 
	 * @param @return 设定文件
	 * @return String 返回类型 
	 * @throws
	 */
	@RequestMapping(value = "to-edit/{id}")
	public String toEdit(@PathVariable String id, Model model, HttpSession session) {
		String token = (String)session.getAttribute("token");
		if(token==null||"".equals(token)){
			return "vmstemplates/template/index";
		}
		Images image = OpenStackApi.cloudImageById(id, token);
		model.addAttribute("template", image);
		return "vmstemplates/template/edit";
	}

	/**
	 * 
	  * @Title: save
	  * @Description: 上传模板文件
	  * @param @param file    文件对像
	  * @param @param course   虚拟机对象
	  * @param @param request
	  * @param @param response
	  * @param @param session
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return Feedback    返回类型
	  * @throws
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public Feedback save(@RequestParam(value = "file", required = false) MultipartFile file,
			ImageParms image, HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		request.setCharacterEncoding("utf-8");
		Feedback feedback;
		long size;
		if(file != null){
			size = file.getSize();
		}
		else{
			size = 0;
		}
		if(size>1024000000){
			feedback = new Feedback(false, "文件大小不能超过10M,请去云实验平台上传！");
			return feedback;
		}
		String token = (String)session.getAttribute("token");
		if(token==null||"".equals(token)){
			feedback = new Feedback(false, "没有秘钥！");
			return feedback;
		}
		String report="template";//虚拟机模板模板
		try {
			if(image.getId()==null||"".equals(image.getId())){
				String path = PropertiesUtil.getValue("DIR_FILE")+"/"+report+"/";
			    String fileName = file.getOriginalFilename();
				String name =fileName.substring(fileName.lastIndexOf("."), fileName.length());
				File targetFile = new File(path, fileName);
				targetFile.mkdirs();
				file.transferTo(targetFile);
				image.setImageUrl(PropertiesUtil.getValue("ADDRESS")+"/res/"+report+"/"+fileName);
			   //测试上传模板文件接口
				ImageEntity  imageEntity = new ImageEntity(); 
				imageEntity.setImageUrl(image.getImageUrl());
				imageEntity.setName(image.getName());
			   //测试上传模板信息接口
			   Images cloudImageSave = OpenStackApi.cloudImageSave(imageEntity,token);
			   Rest.addImageDesc(cloudImageSave.getId() , image.getDescription());
			}
			else{
				Rest.addImageDesc(image.getId() , image.getDescription());
			}
		    
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
	 * @Title: delete 
	 * @Description: 批量删除 
	 * @param @param ids 集合
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Feedback delete(String ids, HttpSession session) {
		Feedback feedback = null;// 返回值
		String token = (String)session.getAttribute("token");
		if(token==null||"".equals(token)){
			feedback = new Feedback(false, "删除失败");
			return feedback;
		}
		try {
			// 批量删除
			ActionResponse bel =  OpenStackApi.batchCloudImageDel(ids,token);
			if(bel.getCode()>0){
				feedback = new Feedback(true, "删除成功!");
			}else{
				feedback = new Feedback(false, "删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			feedback = new Feedback(false, "删除失败");
		}
		return feedback;
	}
	/**
	 * 
	 * @Title: delete 
	 * @Description: 删除 
	 * @param @param ids 
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	@RequestMapping(value = "deleteById", method = RequestMethod.POST)
	@ResponseBody
	public Feedback deleteById(String id, HttpSession session) {
		Feedback feedback = null;// 返回值
		String token = (String)session.getAttribute("token");
		if(token==null||"".equals(token)){
			feedback = new Feedback(false, "删除失败");
			return feedback;
		}
		try {
			// 批量删除
			ActionResponse bel = OpenStackApi.cloudImageDel(id,token);
			if(bel.getCode()>0){
				feedback = new Feedback(true, "删除成功!");
			}else{
				feedback = new Feedback(false, "删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			feedback = new Feedback(false, "删除失败");
		}
		return feedback;
	}
	
}
