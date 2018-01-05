package com.xunfang.bdpf.mllib.assembly.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunfang.bdpf.mllib.assembly.entity.ResourceAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.ResourceAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.mapper.ResourceAssemblyMapper;
import com.xunfang.bdpf.mllib.assembly.service.ResourceAssemblyService;
import com.xunfang.utils.Identities;
/**
 * @ClassName ResourceAssemblyServiceImpl
 * @Description: 组件Service接口实现类
 * Copyright: Copyright (c) 2017
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wjp
 * @date 2017年11月06日
 * @version V1.0
 */
@Service
@Transactional
public class ResourceAssemblyServiceImpl implements ResourceAssemblyService{

	@Autowired
	private ResourceAssemblyMapper resourceAssemblyMapper;
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(ResourceAssemblyExample example) {
		return resourceAssemblyMapper.countByExample(example);
	}
	 
	/**
	 * 
	  * @Title: deleteByExample
	  * @Description: 根据条件删除多条数据
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int deleteByExample(ResourceAssemblyExample example) {
		return resourceAssemblyMapper.deleteByExample(example);
	}
	  
	/**
	 * 
	  * @Title: deleteByExample
	  * @Description: 根据主键删除多条数据
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int deleteByPrimaryKey(String id) {
		return resourceAssemblyMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 
	  * @Title: insert
	  * @Description: 插入数据
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int insert(ResourceAssembly record) {
		return resourceAssemblyMapper.insert(record);
	}
	/**
	 * 
	  * @Title: insertSelective
	  * @Description: 插入数据,不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int insertSelective(ResourceAssembly record) {
		return resourceAssemblyMapper.insertSelective(record);
	}
	 

	/**
		 * 
		  * @Title: selectByExample
		  * @Description: 根据条件查询数据
		  *  @return  List<NormalizationChildAssembly> 返回类型
		  * @throws
		 */
	
	@Override
	public List<ResourceAssembly> selectByExample(ResourceAssemblyExample example) {
		return resourceAssemblyMapper.selectByExample(example);
	}
	 
	/**
	 * 
	  * @Title: selectByPrimaryKey
	  * @Description: 根据主键查询数据
	  *  @return  NormalizationChildAssembly 返回类型
	  * @throws
	 */

	@Override
	public ResourceAssembly selectByPrimaryKey(String id) {
		return resourceAssemblyMapper.selectByPrimaryKey(id);
	}

	
	/**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */

	@Override
	public int updateByExampleSelective(ResourceAssembly record, ResourceAssemblyExample example) {
		return resourceAssemblyMapper.updateByExampleSelective(record, example);
	}


	/**
	 * 
	  * @Title: updateByExample
	  * @Description: 按条件更新
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExample(ResourceAssembly record, ResourceAssemblyExample example) {
		return resourceAssemblyMapper.updateByExample(record, example);
	}
	 

	/**
		 * 
		  * @Title: updateByPrimaryKeySelective
		  * @Description: 按条件更新,带主键且不为null的字段
		  *  @return  int 返回类型
		  * @throws
		 */
	@Override
	public int updateByPrimaryKeySelective(ResourceAssembly record) {
		return resourceAssemblyMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKey(ResourceAssembly record) {
		return resourceAssemblyMapper.updateByPrimaryKey(record);
	}
	
	/**
	 * 
	  * @Title: save
	  * @Description: 课程资源保存
	  *  @param resourceAssembly  ResourceAssembly 课程资源model
	  *  @return  boolean 返回类型
	  * @throws
	 */
	@Override
	public boolean save(ResourceAssembly resourceAssembly) {
		boolean flag = true;
		if (StringUtils.isBlank(resourceAssembly.getId())) {
			resourceAssembly.setId(Identities.uuid2());
			flag = resourceAssemblyMapper.insert(resourceAssembly) > 0 ? true : false;
		} else {
			flag = resourceAssemblyMapper.updateByPrimaryKeySelective(resourceAssembly) > 0 ? true : false;
		}
		return flag;
	}
	
}
