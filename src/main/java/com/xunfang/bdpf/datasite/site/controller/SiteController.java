package com.xunfang.bdpf.datasite.site.controller;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sun.star.io.IOException;
import com.xunfang.bdpf.datasite.api.entity.Api;
import com.xunfang.bdpf.datasite.api.service.ApiService;
import com.xunfang.bdpf.datasite.area.entity.Area;
import com.xunfang.bdpf.datasite.area.service.AreaService;
import com.xunfang.bdpf.datasite.item.entity.Item;
import com.xunfang.bdpf.datasite.item.service.ItemService;
import com.xunfang.bdpf.datasite.resources.entity.Resources;
import com.xunfang.bdpf.datasite.resources.service.ResourcesService;
import com.xunfang.bdpf.datasite.site.entity.Site;
import com.xunfang.bdpf.datasite.site.service.SiteService;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.utils.Identities;
import com.xunfang.utils.Page;

import net.sf.json.JSONObject;


/**
 * @ClassName SiteController
 * @Description: TODO(用一句话描述该文件做什么)
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author lizhu
 * @date 2017年7月20日 下午4:42:35
 * @version V1.0
 */
@Controller
@RequestMapping(value = "datasite")
public class SiteController {
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ApiService apiService;
	
	
	@Autowired
	private ResourcesService resourcesService;
	
	
	
	/**
	  * api(返回api页面)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: api
	  * @Description: TODO
	  * @param @param request
	  * @param @param session
	  * @param @param id
	  * @param @param siteName
	  * @param @param model
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	  */
	@RequestMapping(value = "api")
	public String api(HttpServletRequest request, HttpSession session,String id,String siteName,Model model) {
		model.addAttribute("id", id);
		model.addAttribute("siteName",siteName);
		User user=(User)session.getAttribute("loginuser");
		if(user == null){
			return "login/login";
		}
		if(user.getUserType()==1){
			return "datasite/api/stu-api";
		}else{
			return "datasite/api/api";
		}
		
		
	}
	
	
	/**
	  * area(返回数据站点主页页面)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: area
	  * @Description: TODO
	  * @param @param request
	  * @param @param session
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	  */
	@RequestMapping(value = "site")
	public String area(HttpServletRequest request, HttpSession session) {
		User user=(User)session.getAttribute("loginuser");
		if(user == null){
			return "login/login";
		}
		if(user.getUserType()==1){
			return "datasite/stu-site";
		}else{
			return "datasite/site";
		}
		
		
	}
	
		
	
	/**
	  * area(返回数据领域页面)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: student
	  * @Description: TODO
	  * @param @param request
	  * @param @param session
	  * @param @param id
	  * @param @param siteName
	  * @param @param model
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	  */
	@RequestMapping(value = "area")
	public String area(HttpServletRequest request, HttpSession session,String id,String siteName,Model model) {
	
		model.addAttribute("id", id);
		model.addAttribute("siteName", siteName);
		User user=(User)session.getAttribute("loginuser");	
		if(user == null){
			return "login/login";
		}
		if(user.getUserType()==1){
			return "datasite/area/stu-area";
		}else{
			return "datasite/area/area";
		}
	}
	
	/**
	  * getArea(获取数据站点列表)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: getArea
	  * @Description: TODO
	  * @param @param request
	  * @param @param session
	  * @param @return    设定文件
	  * @return List<Site>    返回类型
	  * @throws
	  */
	@RequestMapping(value = "getArea")
	@ResponseBody
	public List<Site> getArea(HttpServletRequest request, HttpSession session) {
		return siteService.selectSite();
	}
	
	
	/**
	  * toAdd(跳转到数据领域添加页面)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: toAdd
	  * @Description: TODO
	  * @param @param request
	  * @param @param session
	  * @param @param model
	  * @param @param id
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	  */
	@RequestMapping(value = "to-add")
	public String toAdd(HttpServletRequest request, HttpSession session,Model model,String id) {
		model.addAttribute("site", siteService.selectSiteById(id));
		return "datasite/area/add";
	}
	
	
	/**
	  * toApiAdd(跳转到api添加页面)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: toApiAdd
	  * @Description: TODO
	  * @param @param request
	  * @param @param session
	  * @param @param model
	  * @param @param id
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	  */
	@RequestMapping(value = "to-api-add")
	public String toApiAdd(HttpServletRequest request, HttpSession session,Model model,String id) {
		model.addAttribute("site", siteService.selectSiteById(id));
		return "datasite/api/add";
	}
	
	
	/**
	  * toEdit(跳转到数据领域修改页面)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: toEdit
	  * @Description: TODO
	  * @param @param request
	  * @param @param session
	  * @param @param model
	  * @param @param id
	  * @param @param type
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	  */
	@RequestMapping(value = "to-edit")
	public String toEdit(HttpServletRequest request, HttpSession session,Model model,String id,String type) {
		model.addAttribute("site", siteService.selectSite());
		model.addAttribute("area", areaService.selectAreaById(id,type));
		model.addAttribute("item", itemService.selectByAreaId(id));
		return "datasite/area/edit";
	}
	
	
	
	/**
	  * toApiEdit(跳转到api编辑页面)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: toApiEdit
	  * @Description: TODO
	  * @param @param request
	  * @param @param session
	  * @param @param model
	  * @param @param id
	  * @param @param type
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	  */
	@RequestMapping(value = "to-api-edit")
	public String toApiEdit(HttpServletRequest request, HttpSession session,Model model,String id,String type) {
		model.addAttribute("site", siteService.selectSite());
		model.addAttribute("area", areaService.selectAreaById(id,type));
		model.addAttribute("item", itemService.selectByAreaId(id));
		model.addAttribute("api", apiService.selectApiByAreaId(id));
		return "datasite/api/edit";
	}
	
	
	/**
	  * toDetail(跳转到数据领域详细信息页面)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: toDetail
	  * @Description: TODO
	  * @param @param request
	  * @param @param session
	  * @param @param model
	  * @param @param id
	  * @param @param type
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	  */
	@RequestMapping(value = "to-detail")
	public String toDetail(HttpServletRequest request, HttpSession session,Model model,String id,String type) {
		model.addAttribute("site", siteService.selectSite());
		model.addAttribute("area", areaService.selectAreaById(id,type));
		model.addAttribute("item", itemService.selectByAreaId(id));
		return "datasite/area/detail";
	}
	
	
	/**
	  * toApiDetail(api详细页面)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: toApiDetail
	  * @Description: TODO
	  * @param @param request
	  * @param @param session
	  * @param @param model
	  * @param @param id
	  * @param @param type
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	  */
	@RequestMapping(value = "to-api-detail")
	public String toApiDetail(HttpServletRequest request, HttpSession session,Model model,String id,String type) {
		model.addAttribute("site", siteService.selectSite());
		model.addAttribute("area", areaService.selectAreaById(id,type));
		model.addAttribute("item", itemService.selectByAreaId(id));
		model.addAttribute("api", apiService.selectApiByAreaId(id));
		return "datasite/api/detail";
	}
	
	
	
	/**
	  * save(数据领域与api添加数据方法)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: save
	  * @Description: TODO
	  * @param @param request
	  * @param @param session
	  * @param @param title
	  * @param @param siteId
	  * @param @param areaAbstract
	  * @param @param keyword
	  * @param @param data_provider_employer
	  * @param @param item
	  * @param @param type
	  * @param @param api_description
	  * @param @param api_address
	  * @param @param api_format
	  * @param @param api_example
	  * @param @param files
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	  */
	@RequestMapping(value = "save")
	@ResponseBody
	public int save(HttpServletRequest request, HttpSession session,String title,String siteId,String areaAbstract,String keyword,String data_provider_employer,String item,String type,
			String api_description,String api_address,String api_format,String api_example,String[] files) {
		
		int state=0;
		
		if(!item.equals("")){
			String[] items = item.split("\\[");
			for(int i=0;i<items.length;i++){
				String[] s = items[i].split("\\]");
				if(s.length>1){
				if(!s[0].equals("")&&!s[0].equals("undefined")){
				if(s[0].length()>20){
				state=2;
				}else if(s[1].length()>50){
				state=3;
				}
				}
				}
				}
				}
		
		
		
		Area area=new Area();
		area.setId(Identities.uuid2());
		area.setDataSiteId(siteId);
		area.setTitle(title);
		area.setAreAbstract(areaAbstract);
		area.setKeyword(keyword);
		area.setDataProviderEmployer(data_provider_employer);
		area.setType(type);
		try {
			if(state==0){
				areaService.insertArea(area);
			}
		} catch (Exception e) {
			e.printStackTrace();
			state=1;
		}
		
		
		if(!item.equals("")){
			String[] items = item.split("\\[");
			for(int i=0;i<items.length;i++){
				
				String[] s = items[i].split("\\]");

					if(s.length>1){
						if(!s[0].equals("")&&!s[0].equals("undefined")){
							
							Item item1=new Item();
							item1.setId(Identities.uuid2());
							item1.setDataAreaId(area.getId());
							item1.setName(s[0]);
							item1.setDescription(s[1]);
							try {
								if(state==0){
							    itemService.insertItem(item1);
								}
							} catch (Exception e) {
								e.printStackTrace();
								state=1;
							}
							
						}
						
					}
								
			}
			
		}
		try {
			if(!api_description.equals("")){
				Api api=new Api();
				api.setId(Identities.uuid2());
				api.setDataAreaId(area.getId());
				api.setApiDescription(api_description);
				api.setApiAddress(api_address);
				api.setApiFormat(api_format);
				api.setApiExample(api_example);
				try {
					 if(state==0){
					apiService.insertApi(api);
					 }
				} catch (Exception e) {
					e.printStackTrace();
					state=1;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if(files.length>0){
				for(int i=0;i<files.length;i++){
					String[] str=files[i].split(":");
					Resources resources=new Resources();
					resources.setId(Identities.uuid2());
					resources.setDataAreaId(area.getId());
					resources.setSize(Double.parseDouble(str[1]));
					resources.setName(str[0]);
					if(str[2].equals("csv")){
						resources.setType(0);
					}else if(str[2].equals("json")){
						resources.setType(1);
					}else if(str[2].equals("xml")){
						resources.setType(2);
					}else if(str[2].equals("xls")){
						resources.setType(3);
					}
					 if(state==0){
					resourcesService.insertResources(resources);
					 }
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		
		
		return state;
		
	}
	
	
	/**
	  * saveSite(数据站点添加方法)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: saveSite
	  * @Description: TODO
	  * @param @param request
	  * @param @param session
	  * @param @param name
	  * @param @param siteAbstract
	  * @param @param imgName
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	  */
	@RequestMapping(value = "saveSite")
	@ResponseBody
	public int saveSite(HttpServletRequest request, HttpSession session,String name,String siteAbstract,String imgName) {
		
		int state=1;
		Site site=new Site();
		site.setId(Identities.uuid2());
		site.setName(name);
		site.setSiteAbstract(siteAbstract);
		site.setImgName(imgName);
		if(name.length()>6){
			state=2;
		}else if(siteAbstract.length()>100){
			state=3;
		}else{
			try {
				state=siteService.insertSite(site);
			//siteService.insertSite(site);
		} catch (Exception e) {
			state=4;
		}
		}
		
		return state;
		
	}
	
	
	
	/**
	  * getSite(根据id获取数据站点数据)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: getSite
	  * @Description: TODO
	  * @param @param request
	  * @param @param session
	  * @param @param id
	  * @param @return    设定文件
	  * @return List<Site>    返回类型
	  * @throws
	  */
	@RequestMapping(value = "getSite")
	@ResponseBody
	public List<Site> getSite(HttpServletRequest request, HttpSession session,String id) {
			
		return siteService.selectSiteById(id);
		
	}
	
	
	/**
	  * editSite(编辑数据站点方法)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: editSite
	  * @Description: TODO
	  * @param @param request
	  * @param @param session
	  * @param @param id
	  * @param @param name
	  * @param @param siteAbstract
	  * @param @param imgName
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	  */
	@RequestMapping(value = "editSite")
	@ResponseBody
	public int editSite(HttpServletRequest request, HttpSession session,String id,String name,String siteAbstract,String imgName,String oldName) {
		int state=1;
		Site site=new Site();
		site.setId(id);
		site.setName(name);
		site.setSiteAbstract(siteAbstract);
		site.setImgName(imgName);
		if(name.length()>6){
			state=2;
		}else if(siteAbstract.length()>100){
			state=3;
		}else{
		try {
			List<Site> list=siteService.selectSite();
			for(Site site1:list){
				if(name.equals(site1.getName())){
					state=0;
				}
			}
			if(name.equals(oldName)){
				state=1;
			}
			if(state==1){
				siteService.updateSite(site);
			}
			
		} catch (Exception e) {
			state=4;
			e.printStackTrace();
		}
		}
		return state;
		
	}
	
	
	
	
	/**
	  * editArea(编辑数据领域方法)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: editArea
	  * @Description: TODO
	  * @param @param request
	  * @param @param session
	  * @param @param id
	  * @param @param title
	  * @param @param siteId
	  * @param @param areaAbstract
	  * @param @param keyword
	  * @param @param data_provider_employer
	  * @param @param item
	  * @param @param api_description
	  * @param @param api_address
	  * @param @param api_format
	  * @param @param api_example
	  * @param @param files
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	  */
	@RequestMapping(value = "editArea")
	@ResponseBody
	public int editArea(HttpServletRequest request, HttpSession session,String id,String title,String siteId,String areaAbstract,String keyword,String data_provider_employer,String item,
			String api_description,String api_address,String api_format,String api_example,String[] files) {
		int state=0;
		if(!item.equals("")){
		String[] items = item.split("\\[");
		for(int i=0;i<items.length;i++){
			
			String[] s = items[i].split("\\]");

				if(s.length>1){
					if(!s[0].equals("")&&!s[0].equals("undefined")){
					if(s[0].length()>20){
								state=2;
							}else if(s[1].length()>50){
								state=3;
							}
					}
					
				}
				
		}
		}
		
		Area area=new Area();
		area.setId(id);
		area.setDataSiteId(siteId);
		area.setTitle(title);
		area.setAreAbstract(areaAbstract);
		area.setKeyword(keyword);
		area.setDataProviderEmployer(data_provider_employer);
		try {
			if(state==0){
				areaService.updateArea(area);
			}
		} catch (Exception e) {
			e.printStackTrace();
			state=1;
		}
		
		
		if(!id.equals("")){
			try {
				if(state==0){
				itemService.deleteByAreaId(id);
			}
			} catch (Exception e) {
				e.printStackTrace();
				state=1;
			}
			if(!item.equals("")){
			String[] items = item.split("\\[");
			for(int i=0;i<items.length;i++){
				
				String[] s = items[i].split("\\]");

					if(s.length>1){
						if(!s[0].equals("")&&!s[0].equals("undefined")){
							
							Item item1=new Item();
							item1.setId(Identities.uuid2());
							item1.setDataAreaId(id);
							item1.setName(s[0]);
							item1.setDescription(s[1]);
							try {
								if(state==0){
								itemService.insertItem(item1);
								}
							} catch (Exception e) {
								e.printStackTrace();
								state=1;
							}
							
						}
						
					}
					
				
			}
			}
		}
		try {
			if(!api_description.equals("")){
				Api api=new Api();
				api.setDataAreaId(id);
				api.setApiDescription(api_description);
				api.setApiAddress(api_address);
				api.setApiFormat(api_format);
				api.setApiExample(api_example);
				try {
					if(state==0){
					apiService.updataApi(api);
					}
				} catch (Exception e) {
					e.printStackTrace();
					state=1;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if(files.length>0){
				resourcesService.deleteByAreaId(area.getId());
				for(int i=0;i<files.length;i++){
					String[] str=files[i].split(":");
					
					Resources resources=new Resources();
					resources.setId(Identities.uuid2());
					resources.setDataAreaId(area.getId());
					resources.setSize(Double.parseDouble(str[1]));
					resources.setName(str[0]);
					if(str[2].equals("csv")){
						resources.setType(0);
					}else if(str[2].equals("json")){
						resources.setType(1);
					}else if(str[2].equals("xml")){
						resources.setType(2);
					}else if(str[2].equals("xls")){
						resources.setType(3);
					}else if(str[2].equals("0")){
						resources.setType(0);
					}else if(str[2].equals("1")){
						resources.setType(1);
					}else if(str[2].equals("2")){
						resources.setType(2);
					}else if(str[2].equals("3")){
						resources.setType(3);
					}
					if(state==0){
					resourcesService.insertResources(resources);
					}
				}
			}else if(files.length==0){
				if(state==0){
				resourcesService.deleteByAreaId(area.getId());
				}
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
		}
	
		
		return state;
		
	}
	
	
	
	/**
	  * delArea(删除数据领域方法)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: delArea
	  * @Description: TODO
	  * @param @param request
	  * @param @param session
	  * @param @param id
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	  */
	@RequestMapping(value = "delArea")
	@ResponseBody
	public int delArea(HttpServletRequest request, HttpSession session,String id) {
		int state=0;
		try {
			areaService.deleteAreaByPrimaryKey(id);
			itemService.deleteByAreaId(id);
			resourcesService.deleteByAreaId(id);
			apiService.deleteApiByAreaId(id);
		} catch (Exception e) {
			state=1;
			e.printStackTrace();
		}
		return state;
		
	}
	
	
	/**
	  * delSite(删除数据站点方法)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: delSite
	  * @Description: TODO
	  * @param @param request
	  * @param @param session
	  * @param @param id
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	  */
	@RequestMapping(value = "delSite")
	@ResponseBody
	public int delSite(HttpServletRequest request, HttpSession session,String id) {
		int state=0;
		try {
			
			List<Area> areaList=areaService.selectAreaBySiteId(id);
			for(Area area:areaList){
				itemService.deleteByAreaId(area.getId());
				resourcesService.deleteByAreaId(area.getId());
				apiService.deleteApiByAreaId(area.getId());
			}
			siteService.deleteBySiteId(id);
			areaService.deleteAreaBySiteId(id);
			
		} catch (Exception e) {
			state=1;
			e.printStackTrace();
		}
		return state;
		
	}
	
	
	
	/**
	  * getAreaList(获取数据站点领域下的列表信息)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: getAreaList
	  * @Description: TODO
	  * @param @param keywords
	  * @param @param pageNo
	  * @param @param pageSize
	  * @param @param type
	  * @param @param id
	  * @param @return    设定文件
	  * @return Page<Area>    返回类型
	  * @throws
	  */
	@RequestMapping(value = "getAreaList")
	@ResponseBody
	public Page<Area> getAreaList(String keywords, String pageNo, String pageSize,String type,String id) {
		int skip = 0;
		int max = 10;
		Page<Area> page = new Page<Area>();
		List<Area> list = new ArrayList<Area>();
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
			page.setTotalCount(areaService.getAreaCount(id, keywords,type));
			
			list = areaService.selectAreaByPrimaryKey(keywords, skip, max, id,type);
			page.setResult(list);
			
			} catch (Exception e) {
			e.printStackTrace();
			}
		return page;
	}
	
	
	/**
	  * loadImg(上传数据站点标题图片方法)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: loadImg
	  * @Description: TODO
	  * @param @param myfile
	  * @param @param request
	  * @param @param response
	  * @param @return    设定文件
	  * @return JSONObject    返回类型
	  * @throws
	  */
	@RequestMapping(value="loadImg")
	@ResponseBody
	public JSONObject  loadImg(@RequestParam(value = "btnFile", required = false) MultipartFile myfile,
			HttpServletRequest request, HttpServletResponse response){
		 
		 Map<String,Object> map= new HashMap<String,Object>();  
         if(myfile.isEmpty()){  
        	 map.put( "result", "error");  
             map.put( "msg", "上传文件不能为空" ); 
        } else{  
        	 try {
				BufferedImage image = ImageIO.read(myfile.getInputStream());
				//System.out.println(image.getWidth());//获取图片宽度，单位px
				// System.out.println(image.getHeight());//获取图片高度，单位px
				
				 if(image.getWidth()>200||image.getHeight()>80){
					 map.put( "result", "上传图片大小不符合要求");
				 }else{
					 String originalFilename=myfile.getOriginalFilename(); 
		              String fileExt = originalFilename.substring(originalFilename.lastIndexOf(".")+1)
		                      .toLowerCase();
					 if(!fileExt.equals("png")){
		            	  map.put( "result", "上传格式不符合要求");  
		              }else{
		            	  
		            	  //String fileBaseName=FilenameUtils.getBaseName(originalFilename);  
		                  try{  
		                         // System.err.println(originalFilename+"!!!!!!!!!!!!!!!!!!!!!!");
		                        //String genePicPath=request.getSession().getServletContext().getRealPath("/resources/file"); 
		                	  //String dir = "D:/material/datasite/titleImg";
		                		String dir = "/usr/local/material/datasite/titleImg";
		                		 File targetFile = new File(dir);  
		                         if (!targetFile.exists()) {  
		                             targetFile.mkdirs();  
		                         }  
		                		//System.out.println(System.getProperty("catalina.home")+"?????"+PropertiesUtil.getValue("UPLOAD_DIR"));
		                      // System.err.println("服务器上次地址:"+dir);
		                       // String url="D:/4GPTN/ptn/src/main/webapp/resources/uploadFiles";
		                       //把上传的图片放到服务器的文件夹下  
		                        FileUtils. copyInputStreamToFile(myfile.getInputStream(), new File(dir,originalFilename));  
		                        //coding  
		                        map.put("result","success");  
		                        map.put("url",originalFilename);
		                        
		                          
		                  } catch (Exception e) {
		                	  e.printStackTrace();
		                        map.put( "result", "error");  
		                        map.put( "msg",e.getMessage());  
		                          
		                  }  
		              }
				 }
				 
			} catch (java.io.IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
          
        }  
       // String result=String.valueOf(JSONObject.fromObject (map));  
        return JSONObject.fromObject (map);  
	}
	
	
	 public static Boolean uploadFile(HttpServletRequest request, MultipartFile file) {  
        // System.out.println("开始");  
        // String path = System.getProperty("catalina.home") + "/" + "webapps/BDPF/resources/uploadFiles";
         //String path = "D:/material/datasite";
         String path = "/usr/local/material/datasite";
         String fileName = file.getOriginalFilename();  
       //  System.out.println(path);  
         File targetFile = new File(path, fileName);  
         if (!targetFile.exists()) {  
             targetFile.mkdirs();  
         }  
         // 保存  
         try {  
             file.transferTo(targetFile);  
             return true;  
         } catch (Exception e) {  
             e.printStackTrace();  
             return false;  
         }  
  
    }  
	
    
    /**
      * uploadFile(数据领域上传文件方法)
      * TODO(这里描述这个方法适用条件 – 可选)
      * TODO(这里描述这个方法的执行流程 – 可选)
      * TODO(这里描述这个方法的使用方法 – 可选)
      * TODO(这里描述这个方法的注意事项 – 可选)
      *
      * @Title: uploadFile
      * @Description: TODO
      * @param @param request
      * @param @param response
      * @param @param file
      * @param @return
      * @param @throws IOException    设定文件
      * @return JSONObject    返回类型
      * @throws
      */
    @RequestMapping("progress")
    @ResponseBody
    public JSONObject uploadFile(HttpServletRequest request,HttpServletResponse response,  
                           @RequestParam("file") MultipartFile file) throws IOException {  
    	 Map<String,Object> map= new HashMap<String,Object>(); 
         response.setContentType("text/html");  
         response.setCharacterEncoding("GBK");  
         boolean flag = false;  
         String originalFilename=file.getOriginalFilename(); 
         long fileSize=file.getSize();
         String fileExt = originalFilename.substring(originalFilename.lastIndexOf(".")+1)
                 .toLowerCase();
         
        // System.out.println("开始");  
         //String path = System.getProperty("catalina.home") + "/" + "webapps/BDPF/resources/uploadFiles";
         //String path = "D:/material/datasite";
         String path = "/usr/local/material/datasite";
         String fileName = file.getOriginalFilename();  
        // System.out.println(path);  
         File targetFile = new File(path, fileName);  
         if (!targetFile.exists()) {  
             targetFile.mkdirs();  
         }  
         // 保存  
         try {  
             file.transferTo(targetFile); 
             flag=true;
          } catch (Exception e) {  
        	  flag=false;
             e.printStackTrace();  
            }  
        
         if (flag == true) {  
            
            map.put("result","success");  
            map.put("url",originalFilename);
            map.put("fileSize",fileSize/1024/1024);
            map.put("fileType",fileExt);
         } else {  
             map.put( "result", "error");
         }  
         return JSONObject.fromObject (map);
    }  
	
	
	@RequestMapping(value="loadFile")
	@ResponseBody
	public JSONObject  loadFile(@RequestParam(value = "file", required = false) MultipartFile myfile,
			HttpServletRequest request, HttpServletResponse response){
		 
		 Map<String,Object> map= new HashMap<String,Object>();  
         if(myfile.isEmpty()){  
        	 map.put( "result", "error");  
             map.put( "msg", "上传文件不能为空" ); 
        } else{  
              String originalFilename=myfile.getOriginalFilename(); 
              long fileSize=myfile.getSize();
              String fileExt = originalFilename.substring(originalFilename.lastIndexOf(".")+1)
                      .toLowerCase();
            //  System.err.println(fileExt+"*************"+fileSize/1024/1024);
              if(!fileExt.equals("csv")&&!fileExt.equals("json")&&!fileExt.equals("xml")&&!fileExt.equals("xls")){
            	  map.put( "result", "上传格式不符合要求");  
              }else{
            	
                  try{  
                       // System.err.println(originalFilename+"!!!!!!!!!!!!!!!!!!!!!!");
                       // String dir = System.getProperty("catalina.home") + "/" + "webapps/BDPF/resources/uploadFiles";
                        String dir = "/usr/local/material/datasite";
                       // String dir = "D:/material/datasite";
                	   //  System.err.println("服务器上次地址:"+dir);
                         //把上传的图片放到服务器的文件夹下  
                        FileUtils. copyInputStreamToFile(myfile.getInputStream(), new File(dir,originalFilename));  
                        //coding  
                        map.put("result","success");  
                        map.put("url",originalFilename);
                        map.put("fileSize",fileSize/1024/1024);
                        map.put("fileType",fileExt);
                          
                  } catch (Exception e) {
                	  e.printStackTrace();
                        map.put( "result", "error");  
                        map.put( "msg",e.getMessage());  
                          
                  }  
              }
            
        }  
       // String result=String.valueOf(JSONObject.fromObject (map));  
        return JSONObject.fromObject (map);  
	}
	
	
	
	/**
	  * getRes(获取资源方法)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: getRes
	  * @Description: TODO
	  * @param @param request
	  * @param @param session
	  * @param @param id
	  * @param @param type
	  * @param @return    设定文件
	  * @return List<Resources>    返回类型
	  * @throws
	  */
	@RequestMapping(value = "getRes")
	@ResponseBody
	public List<Resources> getRes(HttpServletRequest request, HttpSession session,String id,String type) {
		if(type.equals("4")){
			type="";
		}
		return resourcesService.selectByResByAreaId(id,type);
		
	}
	
	
	@RequestMapping(value = "checkAreaTitle")
	@ResponseBody
	public boolean checkAreaTitle(HttpServletRequest request, HttpSession session,String title,String oldTitle,String type) {
		boolean falg = true;
		if(oldTitle.equals(title)){
			falg = true;
		}else{
		List<Area> areas=areaService.selectAreaByTitle(title,type);
		if(areas.size()==0){
			falg=true;
		}else{
			falg = false;
		}
		}
		return falg;
	}
}
