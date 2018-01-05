package com.xunfang.bdpf.system.resource.service;

import com.xunfang.bdpf.system.resource.entity.Resource;

/**
 * 
 * @ClassName ResProService
 * @Description: 实验资源设置服务接口
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年6月2日 上午11:49:05
 * @version V1.0
 */
public interface ResourceService {

	/**
	 * 
	  * @Title: selectByPrimaryKey
	  * @Description: 查询数据
	  * @param @param id
	  * @param @return    设定文件
	  * @return Resource    返回类型
	  * @throws
	 */
	Resource selectByPrimaryKey(String id);
	/**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 更新数据
	  * @param @param resource
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int updateByPrimaryKey(Resource resource);
	
	/**
	 * 
	  * @Title: insert
	  * @Description: 新增数据
	  * @param @param record
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int insert(Resource record);
}
