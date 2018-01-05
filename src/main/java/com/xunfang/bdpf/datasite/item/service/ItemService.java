package com.xunfang.bdpf.datasite.item.service;

import java.util.List;

import com.xunfang.bdpf.datasite.item.entity.Item;
/**
 * 
 * @ClassName ItemService
 * @Description: TODO(数据站点接口)
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author lizhu
 * @date 2017年6月6日 上午11:19:56
 * @version V1.0
 */
public interface ItemService {

	
	/**
	 * 
	  * @Title: insertItem
	  * @Description: 添加Item数据
	  * @param @param 
	  * @param @return  
	  * @return int    返回类型
	  * @throws
	 */
	int insertItem(Item item);
	
	/**
	 * 
	  * @Title: deleteByAreaId
	  * @Description:删除Item数据
	  * @param @param 
	  * @param @return  
	  * @return int    返回类型
	  * @throws
	 */
	int deleteByAreaId(String id);
	
	/**
	 * 
	  * @Title: selectByAreaId
	  * @Description:查询Item数据
	  * @param @param 
	  * @param @return  
	  * @return List<Item>    返回类型
	  * @throws
	 */
	List<Item> selectByAreaId(String id);
	
	
}
