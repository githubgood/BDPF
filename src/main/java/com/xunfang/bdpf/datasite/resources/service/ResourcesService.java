package com.xunfang.bdpf.datasite.resources.service;

import java.util.List;
import java.util.Map;

import com.xunfang.bdpf.datasite.item.entity.Item;
import com.xunfang.bdpf.datasite.resources.entity.Resources;
/**
 * 
 * @ClassName ResourcesService
 * @Description: TODO(资源接口)
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author lizhu
 * @date 2017年6月20日 上午11:19:56
 * @version V1.0
 */
public interface ResourcesService {

	
	/**
	 * 
	  * @Title: insertResources
	  * @Description: 添加Resources数据
	  * @param @param 
	  * @param @return  
	  * @return int    返回类型
	  * @throws
	 */
	int insertResources(Resources resources);
	
	/**
	 * 
	  * @Title: selectByResByAreaId
	  * @Description: 查找Resources数据
	  * @param @param 
	  * @param @return  
	  * @return List<Resources>    返回类型
	  * @throws
	 */
	 List<Resources> selectByResByAreaId(String id,String type);
	 
	 /**
		 * 
		  * @Title: deleteByAreaId
		  * @Description: 删除Resources数据
		  * @param @param 
		  * @param @return  
		  * @return int    返回类型
		  * @throws
		 */
		int deleteByAreaId(String id);
		
	
	
	
}
