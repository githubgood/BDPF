package com.xunfang.bdpf.mllib.assembly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunfang.bdpf.mllib.assembly.entity.FilterMappingAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FilterMappingAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.mapper.FilterMappingAssemblyMapper;
import com.xunfang.bdpf.mllib.assembly.service.FilterMappingAssemblyService;

/**
 * 
 * @ClassName FilterMappingAssemblyServiceImpl
 * @Description: 过滤与映射表Service实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月30日 上午10:30:06
 * @version V1.0
 */
@Service
@Transactional
public class FilterMappingAssemblyServiceImpl implements FilterMappingAssemblyService {

	@Autowired
	private FilterMappingAssemblyMapper filterMappingAssemblyMapper;
	
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(FilterMappingAssemblyExample example) {
		return filterMappingAssemblyMapper.countByExample(example);
	}

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int deleteByExample(FilterMappingAssemblyExample example) {
		return filterMappingAssemblyMapper.deleteByExample(example);
	}

    /**
 	 * 
 	  * @Title: deleteByPrimaryKey
 	  * @Description: 根据条件删除单条数据
 	  *  @return  int 返回类型
 	  * @throws
 	 */
	@Override
	public int deleteByPrimaryKey(String id) {
		return filterMappingAssemblyMapper.deleteByPrimaryKey(id);
	}

    /**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insert(FilterMappingAssembly record) {
		return filterMappingAssemblyMapper.insert(record);
	}

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insertSelective(FilterMappingAssembly record) {
		return filterMappingAssemblyMapper.insertSelective(record);
	}

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<FilterMappingAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<FilterMappingAssembly> selectByExample(FilterMappingAssemblyExample example) {
		return filterMappingAssemblyMapper.selectByExample(example);
	}

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  FilterMappingAssembly 返回类型
   	  * @throws
   	 */
	@Override
	public FilterMappingAssembly selectByPrimaryKey(String id) {
		return filterMappingAssemblyMapper.selectByPrimaryKey(id);
	}

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExampleSelective(FilterMappingAssembly record, FilterMappingAssemblyExample example) {
		return filterMappingAssemblyMapper.updateByExampleSelective(record, example);
	}

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByExample(FilterMappingAssembly record, FilterMappingAssemblyExample example) {
		return filterMappingAssemblyMapper.updateByExample(record, example);
	}

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(FilterMappingAssembly record) {
		return filterMappingAssemblyMapper.updateByPrimaryKeySelective(record);
	}

    /**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKey(FilterMappingAssembly record) {
		return filterMappingAssemblyMapper.updateByPrimaryKey(record);
	}

}
