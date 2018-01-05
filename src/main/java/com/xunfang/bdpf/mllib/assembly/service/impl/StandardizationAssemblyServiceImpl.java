package com.xunfang.bdpf.mllib.assembly.service.impl;

import com.xunfang.bdpf.mllib.assembly.entity.StandardizationAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.StandardizationAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.mapper.StandardizationAssemblyMapper;
import com.xunfang.bdpf.mllib.assembly.service.StandardizationAssemblyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName StandardizationAssemblyServiceImpl
 * @Description: 组件Service接口服务类
 * Copyright: Copyright (c) 2017
 * Company:深圳市讯方技术股份有限公司
 *
 * @author ylh
 * @date 2017年10月17日
 * @version V1.0
 */
@Service
@Transactional
public class StandardizationAssemblyServiceImpl implements StandardizationAssemblyService {

	@Autowired
	private StandardizationAssemblyMapper standardizationAssemblyMapper;
	
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(StandardizationAssemblyExample example) {
		return standardizationAssemblyMapper.countByExample(example);
	}

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int deleteByExample(StandardizationAssemblyExample example) {
		return standardizationAssemblyMapper.deleteByExample(example);
	}

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int deleteByPrimaryKey(String id) {
		return standardizationAssemblyMapper.deleteByPrimaryKey(id);
	}

    /**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insert(StandardizationAssembly record) {
		return standardizationAssemblyMapper.insert(record);
	}

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insertSelective(StandardizationAssembly record) {
		return standardizationAssemblyMapper.insertSelective(record);
	}

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<NormalizationChildAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<StandardizationAssembly> selectByExample(StandardizationAssemblyExample example) {
		return standardizationAssemblyMapper.selectByExample(example);
	}

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  NormalizationChildAssembly 返回类型
   	  * @throws
   	 */
	@Override
	public StandardizationAssembly selectByPrimaryKey(String id) {
		return standardizationAssemblyMapper.selectByPrimaryKey(id);
	}

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExampleSelective(StandardizationAssembly record, StandardizationAssemblyExample example) {
		return standardizationAssemblyMapper.updateByExampleSelective(record, example);
	}

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByExample(StandardizationAssembly record, StandardizationAssemblyExample example) {
		return standardizationAssemblyMapper.updateByExample(record, example);
	}

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(StandardizationAssembly record) {
		return standardizationAssemblyMapper.updateByPrimaryKeySelective(record);
	}

    /**
   	 * 
   	  * @Title: updateByPrimaryKey
   	  * @Description: 按主键更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByPrimaryKey(StandardizationAssembly record) {
		return standardizationAssemblyMapper.updateByPrimaryKey(record);
	}

}
