package com.xunfang.bdpf.system.role.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.xunfang.bdpf.system.role.entity.RoleCompetence;
import com.xunfang.bdpf.system.role.entity.RoleCompetenceExample;
import com.xunfang.bdpf.system.role.entity.RoleExample;
import com.xunfang.bdpf.system.role.service.RoleCompetenceService;
import com.xunfang.bdpf.system.role.service.RoleService;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.bdpf.system.user.entity.UserRole;
import com.xunfang.bdpf.system.user.entity.UserRoleExample;
import com.xunfang.bdpf.system.user.service.UserRoleService;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.Identities;
import com.xunfang.utils.Page;
import com.xunfang.utils.PropertiesUtil;
/**
 * 
 * @ClassName RoleController
 * @Description: 角色管理控制层
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年6月1日 上午11:21:04
 * @version V1.0
 */
@Controller
@RequestMapping(value = "role")
public class RoleController {

	@Autowired
	//角色管理接口
	private RoleService roleService;
	@Autowired
	//权限管理服务
	private CompetenceService competenceService;
	@Autowired
	//角色与权限的关联管理服务接口
	private RoleCompetenceService roleCompetenceService;
	@Autowired
	private UserRoleService userRoleService;
	/**
	 * @Fields log : 输出日志
	 */
	private static Logger log = Logger.getLogger(RoleController.class);

	/**
	 * 
	  * @Title: index
	  * @Description: 用户管理首页
	  * @param @param request
	  * @param @param session
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "")
	public String index(HttpServletRequest request, HttpSession session) {
		return "system/role/index";

	}

	/**
	 * 
	  * @Title: listRoleByPage
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
	public Page<Role> listRoleByPage(String keywords, String pageNo, String pageSize) {
		int skip = Integer.parseInt(PropertiesUtil.getValue("page.pageNo"));//从第几条开始取数据
		int max = Integer.parseInt(PropertiesUtil.getValue("page.pageSize"));//每页显示多少条数据
		Page<Role> page = new Page<Role>();
		List<Role> list = new ArrayList<Role>();
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
			page.setTotalCount(roleService.getRoleCount(keywords));//获取总条数
			list = roleService.queryRoleByPage(keywords, skip, max);//获取分页数据，根据关键字查找
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
	  * @Description: 跳转添加角色页面
	  * @param @param model
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-add")
	public String toAdd(Model model) {
		//获取系统 权限分组列表
		List<Competence> compList = new ArrayList<Competence>();
		CompetenceExample example = new CompetenceExample();
		example.createCriteria().andParentIdEqualTo("");
		List<Competence> muneList = competenceService.selectByExample(example);
		model.addAttribute("muneList", muneList);
		for (Competence comp : muneList) {
			CompetenceExample exam = new CompetenceExample();
			exam.createCriteria().andParentIdEqualTo(comp.getResCode());
			List<Competence> lbsc = competenceService.selectByExample(exam);
			compList.addAll(lbsc);
		}
		model.addAttribute("compList", compList);
		return "system/role/add";
	}
	
	/**
	 * 
	  * @Title: save
	  * @Description: 保存数据并跳转用户管理页面
	  * @param @param role
	  * @param @param session
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "save/{array}", method = RequestMethod.POST)
	@ResponseBody
	public Feedback save(@PathVariable String array,Role role, HttpSession session) {
		String roleId;//角色id
		Feedback feedback = null;// 返回值
		try {
			if (!role.getId().equals("") && role.getId() != null) {// 更新数据
				roleId = role.getId();
				//根据用户id更新数据
				User user = (User) session.getAttribute("loginuser");
				if(user != null){
					UserRoleExample example = new UserRoleExample();
					example.createCriteria().andUserIdEqualTo(user.getId());
					List<UserRole> selectByExample = userRoleService.selectByExample(example);
					if(selectByExample != null){
						String roleid = selectByExample.get(0).getRoleId();
						if(roleId.contains(roleid)){
							feedback = new Feedback(false,"管理员角色不能修改");
							return feedback; 
						}
					}
				}
				roleService.updateByPrimaryKey(role);
			} else {// 新增数据
				roleId = Identities.uuid2();
				role.setId(roleId);
				// 保存数据
				roleService.insert(role);
			}
			// 对权限的关联操作
			RoleCompetenceExample rce = new RoleCompetenceExample();
			rce.createCriteria().andRoleIdEqualTo(roleId);
			// 先删除数据
			roleCompetenceService.deleteByExample(rce);
			String[] compList = array.split("-");
			for(String comp :compList){
				RoleCompetence roleComp = new RoleCompetence();
				roleComp.setId(Identities.uuid2());
				roleComp.setCompetenceId(comp);
				roleComp.setRoleId(roleId);
				// 插入新数据
				roleCompetenceService.insert(roleComp);
			}
			feedback = new Feedback(true, "保存成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	  * @param @param userId
	  * @param @param model
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-edit/{roleId}")
	public String toEdit(@PathVariable String roleId, Model model) {
		//根据userId查询用户信息
		Role role = roleService.selectByPrimaryKey(roleId);
		//获取系统 权限分组列表
				List<Competence> compList = new ArrayList<Competence>();
				CompetenceExample example = new CompetenceExample();
				example.createCriteria().andParentIdEqualTo("");
				List<Competence> muneList = competenceService.selectByExample(example);
				model.addAttribute("muneList", muneList);
				for (Competence comp : muneList) {
					CompetenceExample exam = new CompetenceExample();
					exam.createCriteria().andParentIdEqualTo(comp.getResCode());
					List<Competence> lbsc = competenceService.selectByExample(exam);
					compList.addAll(lbsc);
				}
				model.addAttribute("compList", compList);
				//获取已经获得的权限列表数据
				RoleCompetenceExample examp = new RoleCompetenceExample();
				examp.createCriteria().andRoleIdEqualTo(roleId);
				List<RoleCompetence> checkList = roleCompetenceService.selectByExample(examp);
				model.addAttribute("role", role);
				model.addAttribute("checkList", checkList);
		return "system/role/edit";
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
	public Feedback delete(String ids, HttpSession session) {
		Feedback feedback = null;// 返回值
		User user = (User) session.getAttribute("loginuser");
		if(user != null){
			UserRoleExample example = new UserRoleExample();
			example.createCriteria().andUserIdEqualTo(user.getId());
			List<UserRole> selectByExample = userRoleService.selectByExample(example);
			if(selectByExample != null){
				String roleId = selectByExample.get(0).getRoleId();
				if(ids.contains(roleId)){
					feedback = new Feedback(false,"管理员角色不能删除");
					return feedback; 
				}
			}
		}
		
		int error = 0;//删除失败数量
		try {
			String[] idArr = ids.split(",");
			for (int i = 0; i < idArr.length; i++) {
				UserRoleExample role = new UserRoleExample();
				role.createCriteria().andRoleIdEqualTo(idArr[i]);
				List<UserRole> selectByExample = userRoleService.selectByExample(role);
					if(selectByExample.size()>0){
						error ++;
					}else{
						//删除
						RoleExample example = new RoleExample();
						//andIdIn  可一次删除多条数据
						example.createCriteria().andIdEqualTo(idArr[i]);
						roleService.deleteByExample(example);
						feedback = new Feedback(true, "删除成功!");
						// 同时删除用户与角色的关联信息
						RoleCompetenceExample ure = new RoleCompetenceExample();
						//andUserIdIn  删除多条数据
						ure.createCriteria().andRoleIdEqualTo(idArr[i]);
						roleCompetenceService.deleteByExample(ure);
					}
			}
			String outStr = "当前一共选中的角色为："+idArr.length+"，";
			outStr = "删除成功："+(idArr.length-error)+"个！删除失败："+error+"个！\n请确认该角色下没有绑定用户！";
			if(error==0){
				feedback = new Feedback(true, "删除成功!");
			}else{   
				feedback = new Feedback(false,outStr);
			}
			/*//删除
			RoleExample example = new RoleExample();
			//andIdIn  可一次删除多条数据
			example.createCriteria().andIdIn(Arrays.asList(idArr));
			roleService.deleteByExample(example);
				feedback = new Feedback(true, "删除成功!");
			// 同时删除用户与角色的关联信息
			RoleCompetenceExample ure = new RoleCompetenceExample();
			//andUserIdIn  删除多条数据
			ure.createCriteria().andRoleIdIn(Arrays.asList(idArr));
			roleCompetenceService.deleteByExample(ure);*/
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			feedback = new Feedback(false, "删除失败");
		}
		return feedback;
	}
	
	/**
	 * 
	  * @Title: checkName
	  * @Description: 验证角色是否存在
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
			//根据角色名查询是否存在重复的角色
			RoleExample roleExample = new RoleExample();
			roleExample.createCriteria().andIdNotEqualTo(id).andNameEqualTo(name);
		   List<Role> list= roleService.selectByExample(roleExample);
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
