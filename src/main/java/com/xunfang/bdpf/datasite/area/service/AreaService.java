package com.xunfang.bdpf.datasite.area.service;

import java.util.List;

import com.xunfang.bdpf.datasite.area.entity.Area;
/**
 * 
 * @ClassName AreaService
 * @Description: TODO(数据站点接口)
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author lizhu
 * @date 2017年6月6日 上午11:19:56
 * @version V1.0
 */
public interface AreaService {

	/**
	 * 
	  * @Title: selectAreaByPrimaryKey
	  * @Description: 查询Area数据
	  * @param @param 
	  * @param @return  
	  * @return List<Area>    返回类型
	  * @throws
	 */
	List<Area> selectAreaByPrimaryKey(String keywords,int skip, int limit,String id,String type);
	
	/**
	 * 
	  * @Title: selectAreaBySiteId
	  * @Description: 查询Area数据
	  * @param @param 
	  * @param @return  
	  * @return List<Area>    返回类型
	  * @throws
	 */
	List<Area> selectAreaBySiteId(String id);
	
	/**
	 * 
	  * @Title: getAreaCount
	  * @Description: 查询Area数据总数
	  * @param @param 
	  * @param @return  
	  * @return long    返回类型
	  * @throws
	 */
	long getAreaCount(String id,String keywords,String type);
	
	/**
	 * 
	  * @Title: deleteAreaByPrimaryKey
	  * @Description: 删除Area数据
	  * @param @param 
	  * @param @return  
	  * @return int    返回类型
	  * @throws
	 */
	int deleteAreaByPrimaryKey(String id);
	
	/**
	 * 
	  * @Title: deleteAreaBySiteId
	  * @Description: 根据siteId删除Area数据
	  * @param @param 
	  * @param @return  
	  * @return int    返回类型
	  * @throws
	 */
	int deleteAreaBySiteId(String id);
	
	/**
	 * 
	  * @Title: insertArea
	  * @Description: 添加Area数据
	  * @param @param 
	  * @param @return  
	  * @return int    返回类型
	  * @throws
	 */
	int insertArea(Area area);
	
	/**
	 * 
	  * @Title: selectAreaByPrimaryKey
	  * @Description: 查询Area数据
	  * @param @param 
	  * @param @return  
	  * @return Area    返回类型
	  * @throws
	 */
	Area selectAreaById(String id,String type);
	
	/**
	 * 
	  * @Title: updateArea
	  * @Description: 更新Area数据
	  * @param @param 
	  * @param @return  
	  * @return int    返回类型
	  * @throws
	 */
	int updateArea(Area area);
	
	/**
	 * 
	  * @Title: selectAreaByTitle
	  * @Description: 查询Area数据
	  * @param @param 
	  * @param @return  
	  * @return List<Area>    返回类型
	  * @throws
	 */
	List<Area> selectAreaByTitle(String title,String type);
	

	
}
