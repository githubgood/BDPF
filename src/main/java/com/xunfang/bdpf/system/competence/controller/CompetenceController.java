package com.xunfang.bdpf.system.competence.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunfang.bdpf.system.competence.entity.Competence;
import com.xunfang.bdpf.system.competence.entity.CompetenceExample;
import com.xunfang.bdpf.system.competence.service.CompetenceService;
import com.xunfang.bdpf.system.role.entity.Role;
import com.xunfang.bdpf.system.role.entity.RoleCompetenceExample;
import com.xunfang.bdpf.system.role.entity.RoleExample;
import com.xunfang.bdpf.system.role.service.RoleCompetenceService;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.Identities;
import com.xunfang.utils.Page;
import com.xunfang.utils.PropertiesUtil;
/**
 * 
 * @ClassName CompetenceController
 * @Description: 权限管理控制层
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年6月1日 下午3:10:56
 * @version V1.0
 */
@Controller
@RequestMapping(value = "competence")
public class CompetenceController {

	@Autowired
	//权限管理服务
	private CompetenceService competenceService;
	
	@Autowired
	//角色与权限的关联管理服务
	private RoleCompetenceService roleCompetenceService;
	/**
	 * @Fields log : 输出日志
	 */
	private static Logger log = Logger.getLogger(CompetenceController.class);

	/**
	 * 
	  * @Title: index
	  * @Description: 用户权限首页
	  * @param @param request
	  * @param @param session
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "")
	public String index(HttpServletRequest request, HttpSession session) {
		return "system/competence/index";

	}

	
	/**
	 * 
	  * @Title: listByPage
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
	public Page<Competence> listByPage(String keywords, String pageNo, String pageSize) {
		int skip = Integer.parseInt(PropertiesUtil.getValue("page.pageNo"));//从第几条开始取数据
		int max = Integer.parseInt(PropertiesUtil.getValue("page.pageSize"));//每页显示多少条数据
		Page<Competence> page = new Page<Competence>();
		List<Competence> list = new ArrayList<Competence>();
		if (StringUtils.isNotBlank(pageSize)) {
			max = Integer.parseInt(pageSize);
		}
		if (StringUtils.isNotBlank(pageNo)) {
			skip = max * (Integer.parseInt(pageNo) - 1);// mysql中记录从0开始
			page.setPageNo(Integer.parseInt(pageNo));//设置页码
		} else {
			page.setPageNo(1);
		}
		try {
			page.setTotalCount(competenceService.getCompCount(keywords));//获取总条数
			list = competenceService.queryCompByPage(keywords, skip, max);//获取分页数据，根据关键字查找
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
	  * @Description: 跳转添加权限页面
	  * @param @param model
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-add")
	public String toAdd(Model model) {
		//获取系统 权限分组列表
		Map<String, List<Competence>> map = new HashMap<String, List<Competence>>();
		List<String> parentList = competenceService.selectGroupByParentId();
		for (String str : parentList) {
			CompetenceExample example = new CompetenceExample();
			example.createCriteria().andParentIdEqualTo(str);
			List<Competence> lbsc = competenceService.selectByExample(example);
			map.put(str, lbsc);
		}
		model.addAttribute("competenceList", map);
		return "system/competence/add";
	}
	
	/**
	 * 
	  * @Title: save
	  * @Description: 保存数据并跳转权限管理页面
	  * @param @param competence
	  * @param @param session
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Feedback save(Competence comp, HttpSession session) {
		String compId;//权限id
		Feedback feedback = null;// 返回值
		try {
			if (!"".equals(comp.getId())) {// 更新数据
				compId = comp.getId();
				//根据用户id更新数据
				competenceService.updateByPrimaryKey(comp);
			} else {// 新增数据
				compId = Identities.uuid2();
				comp.setId(compId);
				// 保存数据
				competenceService.insert(comp);
			}
			feedback = new Feedback(true, "删除成功！");
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
			feedback = new Feedback(false, "删除失败！");
		}
		return feedback;
	}

	
	/**
	 * 
	  * @Title: toEdit
	  * @Description: 跳转到修改页面
	  * @param @param compId
	  * @param @param model
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-edit/{compId}")
	public String toEdit(@PathVariable String compId, Model model) {
		//根据userId查询权限信息
		Competence comp = competenceService.selectByPrimaryKey(compId);
		model.addAttribute("comp", comp);
		return "system/competence/edit";
	}
	
	/**
	 * 
	  * @Title: delete
	  * @Description: 批量删除
	  * @param @param ids
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Feedback delete(String ids) {
		Feedback feedback = null;// 返回值
		try {
			String[] idArr = ids.split(",");
			//批量删除
			CompetenceExample example = new CompetenceExample();
			//andIdIn  可一次删除多条数据
			example.createCriteria().andIdIn(Arrays.asList(idArr));
			competenceService.deleteByExample(example);
			feedback = new Feedback(true, "删除成功！");
			// 同时删除权限与角色的关联信息
			RoleCompetenceExample ure = new RoleCompetenceExample();
			//andUserIdIn  删除多条数据
			ure.createCriteria().andCompetenceIdIn(Arrays.asList(idArr));
			roleCompetenceService.deleteByExample(ure);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			feedback = new Feedback(false, "删除失败！");
		}
		return feedback;
	}
	
	
	/**
	 * 
	  * @Title: checkName
	  * @Description: 验证权限是否存在
	  *  @param name
	  *  @return  boolean 返回类型
	  * @throws
	 */
	@RequestMapping(value="checkName", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkName(String name, String id){
		boolean falg = true;
		if(id.equals("undefined")||"".equals(id)){//课程资源主键ID判空已经未定义判断
			id = "";
		}
		try {
			//根据权限名查询是否存在重复的权限
			CompetenceExample competenceExample = new CompetenceExample();
			competenceExample.createCriteria().andIdNotEqualTo(id).andNameEqualTo(name);
		   List<Competence> list= competenceService.selectByExample(competenceExample);
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
}
