package com.xunfang.bdpf.system.resource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunfang.bdpf.system.resource.entity.Resource;
import com.xunfang.bdpf.system.resource.mapper.ResourceMapper;
import com.xunfang.bdpf.system.resource.service.ResourceService;
/**
 * 
 * @ClassName ResProServiceImpl
 * @Description: 实验资源设置服务
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年6月2日 上午11:49:24
 * @version V1.0
 */
@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	//实验资源设置dao接口
	private ResourceMapper resourceMapper;
	/**
	 * 
	  * @Title: selectByPrimaryKey
	  * @Description: 查询数据
	  * @param @param id
	  * @param @return    设定文件
	  * @return Resource    返回类型
	  * @throws
	 */
	@Override
	public Resource selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return resourceMapper.selectByPrimaryKey(id);
	}
	/**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 更新数据
	  * @param @param resource
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKey(Resource resource) {
		// TODO Auto-generated method stub
		return resourceMapper.updateByPrimaryKey(resource);
	}
	/**
	 * 
	  * @Title: insert
	  * @Description: 新增数据
	  * @param @param record
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	@Override
	public int insert(Resource record) {
		// TODO Auto-generated method stub
		return resourceMapper.insert(record);
	}

}
