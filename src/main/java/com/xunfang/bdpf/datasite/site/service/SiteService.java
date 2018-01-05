package com.xunfang.bdpf.datasite.site.service;

import java.util.List;

import com.xunfang.bdpf.datasite.site.entity.Site;
/**
 * 
 * @ClassName SiteService
 * @Description: TODO(数据站点接口)
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author lizhu
 * @date 2017年6月6日 上午11:19:56
 * @version V1.0
 */
public interface SiteService {

	/**
	 * 
	  * @Title: selectSite
	  * @Description: 查询site数据
	  * @param @param 
	  * @param @return  
	  * @return List<Site>    返回类型
	  * @throws
	 */
	List<Site> selectSite();
	
	/**
	 * 
	  * @Title: selectSiteById
	  * @Description: 查询site数据
	  * @param @param 
	  * @param @return  
	  * @return Site   返回类型
	  * @throws
	 */
	List<Site> selectSiteById(String id);
	
	/**
	 * 
	  * @Title: insertSite
	  * @Description: 保存site数据
	  * @param @param 
	  * @param @return  
	  * @return int    返回类型
	  * @throws
	 */
	int insertSite(Site site);
	
	/**
	 * 
	  * @Title: deleteBySiteId
	  * @Description: 删除site数据
	  * @param @param 
	  * @param @return  
	  * @return int    返回类型
	  * @throws
	 */
	int deleteBySiteId(String id);
	
	/**
	 * 
	  * @Title: updateSite
	  * @Description: 更新site数据
	  * @param @param 
	  * @param @return  
	  * @return int    返回类型
	  * @throws
	 */
	int updateSite(Site site);
	
	
}
