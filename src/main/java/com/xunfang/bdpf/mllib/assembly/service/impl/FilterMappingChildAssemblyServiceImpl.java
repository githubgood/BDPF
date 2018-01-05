package com.xunfang.bdpf.mllib.assembly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunfang.bdpf.mllib.assembly.entity.FilterMappingChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FilterMappingChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.mapper.FilterMappingChildAssemblyMapper;
import com.xunfang.bdpf.mllib.assembly.service.FilterMappingChildAssemblyService;

/**
 * 
 * @ClassName FilterMappingChildAssemblyServiceImpl
 * @Description: 过滤与映射子表Service实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月30日 上午10:30:26
 * @version V1.0
 */
@Service
@Transactional
public class FilterMappingChildAssemblyServiceImpl implements FilterMappingChildAssemblyService {

	@Autowired
	private FilterMappingChildAssemblyMapper filterMappingChildAssemblyMapper;
	
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(FilterMappingChildAssemblyExample example) {
		return filterMappingChildAssemblyMapper.countByExample(example);
	}

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int deleteByExample(FilterMappingChildAssemblyExample example) {
		return filterMappingChildAssemblyMapper.deleteByExample(example);
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
		return filterMappingChildAssemblyMapper.deleteByPrimaryKey(id);
	}

    /**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insert(FilterMappingChildAssembly record) {
		return filterMappingChildAssemblyMapper.insert(record);
	}

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insertSelective(FilterMappingChildAssembly record) {
		return filterMappingChildAssemblyMapper.insertSelective(record);
	}

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<FilterMappingChildAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<FilterMappingChildAssembly> selectByExample(FilterMappingChildAssemblyExample example) {
		return filterMappingChildAssemblyMapper.selectByExample(example);
	}

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  FilterMappingChildAssembly 返回类型
   	  * @throws
   	 */
	@Override
	public FilterMappingChildAssembly selectByPrimaryKey(String id) {
		return filterMappingChildAssemblyMapper.selectByPrimaryKey(id);
	}

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExampleSelective(FilterMappingChildAssembly record, FilterMappingChildAssemblyExample example) {
		return filterMappingChildAssemblyMapper.updateByExampleSelective(record, example);
	}

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByExample(FilterMappingChildAssembly record, FilterMappingChildAssemblyExample example) {
		return filterMappingChildAssemblyMapper.updateByExample(record, example);
	}

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(FilterMappingChildAssembly record) {
		return filterMappingChildAssemblyMapper.updateByPrimaryKeySelective(record);
	}

    /**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKey(FilterMappingChildAssembly record) {
		return filterMappingChildAssemblyMapper.updateByPrimaryKey(record);
	}

	/**
     * 
      * @Title: batchInsertFilterMappingChildAssembly
      * @Description: 批量插入过滤与映射明细表数据
      *  @param list
      *  @return  int 返回类型
      * @throws
     */
	@Override
	public int batchInsertFilterMappingChildAssembly(List<FilterMappingChildAssembly> list) {
		return filterMappingChildAssemblyMapper.batchInsertFilterMappingChildAssembly(list);
	}

}
