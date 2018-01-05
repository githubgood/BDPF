package com.xunfang.bdpf.datasite.api.service;

import java.util.List;

import com.xunfang.bdpf.datasite.api.entity.Api;
import com.xunfang.bdpf.datasite.area.entity.Area;
/**
 * 
 * @ClassName ApiService
 * @Description: TODO(api接口)
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author lizhu
 * @date 2017年6月16日 上午11:19:56
 * @version V1.0
 */
public interface ApiService {


	/**
	 * 
	  * @Title: insertApi
	  * @Description: 添加api数据
	  * @param @param 
	  * @param @return  
	  * @return int    返回类型
	  * @throws
	 */
	int insertApi(Api api);
	
	/**
	 * 
	  * @Title: selectApiById
	  * @Description: 查询api数据
	  * @param @param 
	  * @param @return  
	  * @return api    返回类型
	  * @throws
	 */
	List<Api> selectApiByAreaId(String id);
	
	/**
	 * 
	  * @Title: updataApi
	  * @Description: 更新api数据
	  * @param @param 
	  * @param @return  
	  * @return int    返回类型
	  * @throws
	 */
	int updataApi(Api api);
	
	/**
	 * 
	  * @Title: deleteApiByAreaId
	  * @Description: 删除api数据
	  * @param @param 
	  * @param @return  
	  * @return int    返回类型
	  * @throws
	 */
	int deleteApiByAreaId(String id);
}
