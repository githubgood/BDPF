package com.xunfang.bdpf.experiment.clas.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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

import com.xunfang.bdpf.experiment.clas.entity.Clas;
import com.xunfang.bdpf.experiment.clas.entity.ClasExample;
import com.xunfang.bdpf.experiment.clas.entity.ClasExample.Criteria;
import com.xunfang.bdpf.experiment.clas.service.ClasService;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.bdpf.system.user.entity.UserExample;
import com.xunfang.bdpf.system.user.service.UserService;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.Page;
import com.xunfang.utils.PropertiesUtil;

/**
 * 
 * @ClassName ClasController
 * @Description: 班级控制器
 * Copyright: Copyright (c) 2017
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年6月7日 上午10:27:48
 * @version V1.0
 */
@Controller
@RequestMapping(value = "clas")
public class ClasController {
	/**
	 * @Fields log : 输出日志
	 * 
	 */
	private static Logger log = Logger.getLogger(ClasController.class);

	@Autowired
	//班级管理Service接口
	private ClasService clasService;
	
	@Autowired
	//学生管理Service接口
	private UserService  userService;
	
	/**
	 * 
	  * @Title: exp
	  * @Description: 班级管理管理跳转方法，进入查询页
	  *  @param request HttpServletRequest http请求
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "")
	public String exp(HttpServletRequest request, HttpSession session) {
		return "experiment/clas/index";
	}

	/**
	 * 
	  * @Title: toAdd
	  * @Description: 班级管理管理跳转方法，进入添加页
	  *  @param model Model 班级管理model
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-add")
	public String toAdd(Model model, HttpSession session) {
		return "experiment/clas/add";
	}
	
	/**
	 * 
	  * @Title: toImport
	  * @Description: 班级管理管理跳转方法，进入导入页
	  *  @param model Model 班级管理model
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-import")
	public String toImport(Model model, HttpSession session) {
		return "experiment/clas/import";
	}

	/**
	 * 
	  * @Title: toEdit
	  * @Description: 班级管理管理跳转方法，进入修改页
	  *  @param id String 当前班级管理主键ID
	  *  @param model Model 班级管理model
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-edit/{id}")
	public String toEdit(@PathVariable String id, Model model) {
		//根据当前班级管理主键ID获取班级管理model
		model.addAttribute("clas", clasService.selectByPrimaryKey(id));
		return "experiment/clas/edit";
	}

	/**
	 * 
	  * @Title: listclasByPage
	  * @Description: 班级管理管理查询方法，带分页
	  *  @param keywords String 班级管理名称
	  *  @param pageNo String 从第几条开始取数据
	  *  @param pageSize String 每页显示多少条数据
	  *  @return  Page<clas> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Page<Clas> listclasByPage(String keywords, String pageNo, String pageSize,HttpSession session) {
		int skip = Integer.parseInt(PropertiesUtil.getValue("page.pageNo"));//从第几条开始取数据
		int max = Integer.parseInt(PropertiesUtil.getValue("page.pageSize"));//每页显示多少条数据
		Page<Clas> page = new Page<Clas>();//Page类
		List<Clas> list = new ArrayList<Clas>();//班级管理列表
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
			page.setTotalCount(clasService.getClasCount(keywords,account));//根据查询条件获取班级管理总条数
			list = clasService.queryClasByPage(keywords, skip, max,account);//根据查询条件获取班级管理列表
			page.setResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return page;
	}

	/**
	 * 
	  * @Title: save
	  * @Description: 班级管理管理保存方法
	  *  @param clas clas 班级管理model
	  *  @param session HttpSession http缓存
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Feedback save(Clas clas, HttpSession session) {
		Feedback feedback = null;
		try {
			clasService.save(clas);//保存
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
	  * @Title: delete
	  * @Description: 班级管理管理删除方法
	  *  @param ids String 班级管理主键ID
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Feedback delete(String ids, HttpSession session) {
		Feedback feedback = null;
		if (StringUtils.isBlank(ids)) {//班级管理主键ID判空
			feedback = new Feedback(false, "删除失败!");
			return feedback;
		}
			//获取当前用户信息
			User user = (User) session.getAttribute("loginuser");
			if(user == null){
				feedback = new Feedback(false, "删除失败!");
				return feedback;
			}
			String myClassId = user.getClassId();//获取当前用户班级id
			String[] myClasIdArr = myClassId.split(",");//拆分
			HashSet<String> clasList = new HashSet<String>();
			//将班级id放入集合中
			for (int j = 0; j < myClasIdArr.length; j++) {
					clasList.add(myClasIdArr[j]);
			}
			//要删除的id
			String[] idArr = ids.split(",");
			UserExample userExample = new UserExample();
			com.xunfang.bdpf.system.user.entity.UserExample.Criteria createCriteria = userExample.createCriteria();
			String outStr = "当前一共选中的班级为："+idArr+"，";
			int error = 0;//删除失败数量
			//班级管理如果在学生表中已经存在给予提示
			for(int i=0;i<idArr.length;i++){
				//判断当前班级下的老师人数
				int clasCount = userService.selectClasCount(idArr[i]);
				if(user.getUserType()==2){//管理员
					if(clasCount>0){
						error ++;
					}else{
						//判断当前班级下的学生
						createCriteria.andClassIdEqualTo(idArr[i]);
						createCriteria.andUserTypeEqualTo(1);
						List<User> list= userService.selectByExample(userExample);//根据班级ID查询学生列表
						if(list.size()>0){//如果有学生，就不能删除班级
							feedback = new Feedback(false, "请先删除该班级下的学生!");
							return feedback;
						}
						ClasExample example = new ClasExample();
						Criteria createCriterias = example.createCriteria();
						createCriterias.andIdEqualTo(idArr[i]);
						clasService.deleteByExample(example);//批量删除班级管理管理信息
					}
				}else{
					if(clasCount>1){//如果人数据大于1，直接解除绑定
						//解除绑定
						for (int j = 0; j < myClasIdArr.length; j++) {
							if(myClasIdArr[j].equals(idArr[i])){
								clasList.remove(myClasIdArr[j]);//移除相同的数据
							}
						}
						//组装数据
						myClasIdArr = clasList.toArray(new String[clasList.size()]);
						StringBuffer sb = new StringBuffer();
						//重新组装 数据
						for(int k = 0; k < myClasIdArr.length; k++){
						 sb. append(myClasIdArr[k]+",");
						}
						String s = sb.toString();
						if(!StringUtils.isEmpty(s)){
							s = s.substring(0, s.length()-1);
						}
						user.setClassId(s);
						userService.updateByPrimaryKey(user);
						feedback = new Feedback(true, "删除成功!");
					}else if(clasCount==1){//只有一个老师时，删除数据同时删除班级下的学生
						createCriteria.andClassIdEqualTo(idArr[i]);
						createCriteria.andUserTypeEqualTo(1);
						List<User> list= userService.selectByExample(userExample);//根据班级ID查询学生列表
						if(list.size()>0){
							feedback = new Feedback(false, "请先删除该班级下的学生!");
							return feedback;
						}
						//解除绑定
						for (int j = 0; j < myClasIdArr.length; j++) {
							if(myClasIdArr[j].equals(idArr[i])){
								clasList.remove(myClasIdArr[j]);//移除相同的数据
							}
						}
						//组装数据
						myClasIdArr = clasList.toArray(new String[clasList.size()]);
						StringBuffer sb = new StringBuffer();
						//重新组装 数据
						for(int k = 0; k < myClasIdArr.length; k++){
						 sb. append(myClasIdArr[k]+",");
						}
						String s = sb.toString();
						if(!StringUtils.isEmpty(s)){
							s = s.substring(0, s.length()-1);
						}
						user.setClassId(s);
						userService.updateByPrimaryKey(user);
						feedback = new Feedback(true, "删除成功!");
						try {
							//删除
							ClasExample example = new ClasExample();
							Criteria createCriterias = example.createCriteria();
							createCriterias.andIdEqualTo(idArr[i]);
							clasService.deleteByExample(example);//批量删除班级管理管理信息
							feedback = new Feedback(true, "删除成功!");
							
						} catch (Exception e) {
							e.printStackTrace();
							log.error(e.getMessage());
							feedback = new Feedback(false, "删除失败");
						}
					}
				}
			}
			if(user.getUserType()==2){
				outStr = "删除成功："+(idArr.length-error)+"个！删除失败："+error+"个！\n请在用户管理中解绑与该班级的绑定关系！";
				if(error==0){
					feedback = new Feedback(true, "删除成功!");
				}else{   
					feedback = new Feedback(false,outStr);
				}
			}
		
		return feedback;
	}

	/**
	 * 
	  * @Title: checkClasCode
	  * @Description: 班级查询是否重复的方法
	  *  @param code String 班级管理代码
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value="checkClasCode", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkClasCode(String id,String code){
		boolean falg = true;
		if(id.equals("undefined")||"".equals(id)){//班级主键ID判空已经未定义判断
			id = "";
		}
		try {
			//根据班级主键和班级代码查询是否存在重复的课程班级代码
			ClasExample example = new ClasExample();
			example.createCriteria().andCodeEqualTo(code).andIdNotEqualTo(id);
			 List<Clas> claslist = clasService.selectByExample(example);
			if(claslist.size()==0){
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
	  * @Title: checkClasName
	  * @Description: 班级查询是否重复的方法
	  *  @param name String 班级管理名称
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value="checkClasName", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkClasName(String id,String name){
		boolean falg = true;
		if(id.equals("undefined")||"".equals(id)){//班级主键ID判空已经未定义判断
			id = "";
		}
		try {
			//根据班级主键和班级名称查询是否存在重复的课程班级名称
			ClasExample example = new ClasExample();
			example.createCriteria().andNameEqualTo(name).andIdNotEqualTo(id);
			 List<Clas> claslist = clasService.selectByExample(example);
			if(claslist.size()==0){
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
		try {
			feedback = clasService.batchImport(name, btnFile);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			feedback = new Feedback(false, "导入EXCEL失败！");
		}
		return feedback;
	}
}