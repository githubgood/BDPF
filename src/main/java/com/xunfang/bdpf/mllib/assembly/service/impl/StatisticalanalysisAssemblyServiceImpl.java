package com.xunfang.bdpf.mllib.assembly.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunfang.bdpf.mllib.assembly.entity.StatisticalanalysisAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.StatisticalanalysisAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.mapper.StatisticalanalysisAssemblyMapper;
import com.xunfang.bdpf.mllib.assembly.service.StatisticalanalysisAssemblyService;

/**
 * 
 * @ClassName StatisticalanalysisAssemblyServiceImpl
 * @Description: 统计分析Service实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月26日 上午11:09:30
 * @version V1.0
 */
@Service
@Transactional
public class StatisticalanalysisAssemblyServiceImpl implements StatisticalanalysisAssemblyService {
	
	@Autowired
	private StatisticalanalysisAssemblyMapper statisticalanalysisAssemblyMapper;

	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(StatisticalanalysisAssemblyExample example) {
		return statisticalanalysisAssemblyMapper.countByExample(example);
	}

	/**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int deleteByExample(StatisticalanalysisAssemblyExample example) {
		return statisticalanalysisAssemblyMapper.deleteByExample(example);
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
		return statisticalanalysisAssemblyMapper.deleteByPrimaryKey(id);
	}

	/**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insert(StatisticalanalysisAssembly record) {
		return statisticalanalysisAssemblyMapper.insert(record);
	}

	/**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insertSelective(StatisticalanalysisAssembly record) {
		return statisticalanalysisAssemblyMapper.insertSelective(record);
	}

	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<StatisticalanalysisAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<StatisticalanalysisAssembly> selectByExample(StatisticalanalysisAssemblyExample example) {
		return statisticalanalysisAssemblyMapper.selectByExample(example);
	}

	/**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  StatisticalanalysisAssembly 返回类型
   	  * @throws
   	 */
	@Override
	public StatisticalanalysisAssembly selectByPrimaryKey(String id) {
		return statisticalanalysisAssemblyMapper.selectByPrimaryKey(id);
	}

	/**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExampleSelective(StatisticalanalysisAssembly record,
			StatisticalanalysisAssemblyExample example) {
		return statisticalanalysisAssemblyMapper.updateByExampleSelective(record, example);
	}

	/**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByExample(StatisticalanalysisAssembly record, StatisticalanalysisAssemblyExample example) {
		return statisticalanalysisAssemblyMapper.updateByExample(record, example);
	}

	/**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(StatisticalanalysisAssembly record) {
		return statisticalanalysisAssemblyMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKey(StatisticalanalysisAssembly record) {
		return statisticalanalysisAssemblyMapper.updateByPrimaryKey(record);
	}

	/**
     * 
      * @Title: batchInsertStatisticalanalysisAssembly
      * @Description: 批量增加字段
      *  @param list
      *  @return int 返回类型
      * @throws
     */
	@Override
	public int batchInsertStatisticalanalysisAssembly(List<StatisticalanalysisAssembly> list) {
		if(list.size() == 0){
			return 0;
		}
		return statisticalanalysisAssemblyMapper.batchInsertStatisticalanalysisAssembly(list);
	}
	
	/**
	 * 
	  * @Title: queryStatisticalanalysisAssembly
	  * @Description: 根据主键ID查询数据
	  *  @return  List<StatisticalanalysisAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<StatisticalanalysisAssembly> queryStatisticalanalysisAssembly(String id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		return statisticalanalysisAssemblyMapper.queryStatisticalanalysisAssembly(param);
	}
	
	 /**
     * 
      * @Title: queryColumn
      * @Description: 根据assemblyLibraryId查询出StatisticalanalysisAssembly集合
      *  @param String 
      *  @return List<StatisticalanalysisAssembly> 返回类型
      * @throws
     */
	@Override
	public List<StatisticalanalysisAssembly> queryColumn(String assemblyLibraryId){
		 List<StatisticalanalysisAssembly> lists = new ArrayList<StatisticalanalysisAssembly>();
		 lists =statisticalanalysisAssemblyMapper.queryColumn(assemblyLibraryId);
		return lists;
	}
	
}
