package com.xunfang.bdpf.mllib.assembly.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunfang.bdpf.mllib.assembly.entity.ConvertAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.ConvertAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.mapper.ConvertAssemblyMapper;
import com.xunfang.bdpf.mllib.assembly.service.ConvertAssemblyService;

/**
 * @ClassName ConvertAssemblyServiceImpl
 * @Description: 类型转换接口实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月12日 上午9:55:14
 * @version V1.0
 */
@Service
@Transactional
public class ConvertAssemblyServiceImpl implements ConvertAssemblyService {

	@Autowired
	private ConvertAssemblyMapper convertAssemblyMapper;

	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(ConvertAssemblyExample example) {
		return convertAssemblyMapper.countByExample(example);
	}

	/**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int deleteByExample(ConvertAssemblyExample example) {
		return convertAssemblyMapper.deleteByExample(example);
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
		return convertAssemblyMapper.deleteByPrimaryKey(id);
	}

	/**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insert(ConvertAssembly record) {
		return convertAssemblyMapper.insert(record);
	}

	 /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insertSelective(ConvertAssembly record) {
		return convertAssemblyMapper.insertSelective(record);
	}

	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<ConvertAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<ConvertAssembly> selectByExample(ConvertAssemblyExample example) {
		return convertAssemblyMapper.selectByExample(example);
	}

	 /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  ConvertAssembly 返回类型
   	  * @throws
   	 */
	@Override
	public ConvertAssembly selectByPrimaryKey(String id) {
		return convertAssemblyMapper.selectByPrimaryKey(id);
	}

	/**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExampleSelective(ConvertAssembly record, ConvertAssemblyExample example) {
		return convertAssemblyMapper.updateByExampleSelective(record, example);
	}

	 /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByExample(ConvertAssembly record, ConvertAssemblyExample example) {
		return convertAssemblyMapper.updateByExample(record, example);
	}

	/**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(ConvertAssembly record) {
		return convertAssemblyMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKey(ConvertAssembly record) {
		return convertAssemblyMapper.updateByPrimaryKey(record);
	}

	/**
	 * 
	  * @Title: queryConvertAssembly
	  * @Description: 根据主键ID查询数据
	  *  @return  List<ConvertAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<ConvertAssembly> queryConvertAssembly(String id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		return convertAssemblyMapper.queryConvertAssembly(param);
	}

	@Override
	public int batchInsertConvertAssembly(List<ConvertAssembly> list) {
		return convertAssemblyMapper.batchInsertConvertAssembly(list);
	}
	
}
