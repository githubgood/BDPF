package com.xunfang.bdpf.mllib.assembly.service;

import java.util.List;

import com.xunfang.bdpf.mllib.assembly.entity.ResourceAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.ResourceAssemblyExample;
/**
 * @ClassName ResourceAssemblyServiceImpl
 * @Description: 组件Service接口
 * Copyright: Copyright (c) 2017
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wjp
 * @date 2017年11月06日
 * @version V1.0
 */
public interface ResourceAssemblyService {

	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	long countByExample(ResourceAssemblyExample example);

	/**
	 * 
	  * @Title: deleteByExample
	  * @Description: 根据条件删除多条数据
	  *  @return  int 返回类型
	  * @throws
	 */
	int deleteByExample(ResourceAssemblyExample example);

	/**
	 * 
	  * @Title: deleteByExample
	  * @Description: 根据主键删除多条数据
	  *  @return  int 返回类型
	  * @throws
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * 
	  * @Title: insert
	  * @Description: 插入数据
	  *  @return  int 返回类型
	  * @throws
	 */
	int insert(ResourceAssembly record);

	/**
	 * 
	  * @Title: insertSelective
	  * @Description: 插入数据,不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	int insertSelective(ResourceAssembly record);

	/**
		 * 
		  * @Title: selectByExample
		  * @Description: 根据条件查询数据
		  *  @return  List<NormalizationChildAssembly> 返回类型
		  * @throws
		 */
	List<ResourceAssembly> selectByExample(ResourceAssemblyExample example);

	/**
	 * 
	  * @Title: selectByPrimaryKey
	  * @Description: 根据主键查询数据
	  *  @return  NormalizationChildAssembly 返回类型
	  * @throws
	 */
	ResourceAssembly selectByPrimaryKey(String id);

	/**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	int updateByExampleSelective(ResourceAssembly record, ResourceAssemblyExample example);

	/**
	 * 
	  * @Title: updateByExample
	  * @Description: 按条件更新
	  *  @return  int 返回类型
	  * @throws
	 */
	int updateByExample(ResourceAssembly record, ResourceAssemblyExample example);

	/**
		 * 
		  * @Title: updateByPrimaryKeySelective
		  * @Description: 按条件更新,带主键且不为null的字段
		  *  @return  int 返回类型
		  * @throws
		 */
	int updateByPrimaryKeySelective(ResourceAssembly record);

	/**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
	int updateByPrimaryKey(ResourceAssembly record);

	/**
	 * 
	  * @Title: save
	  * @Description: 课程资源保存
	  *  @param resourceAssembly  ResourceAssembly 课程资源model
	  *  @return  boolean 返回类型
	  * @throws
	 */
	boolean save(ResourceAssembly resourceAssembly);

}