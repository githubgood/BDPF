package com.xunfang.bdpf.mllib.experiment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunfang.bdpf.mllib.experiment.entity.Experiment;
import com.xunfang.bdpf.mllib.experiment.entity.ExperimentExample;
import com.xunfang.bdpf.mllib.experiment.mapper.ExperimentMapper;
import com.xunfang.bdpf.mllib.experiment.service.ExperimentService;

/**
 * @ClassName ExperimentServiceImpl
 * @Description: 实验实现层
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月20日 上午8:56:11
 * @version V1.0
 */
@Service
@Transactional
public class ExperimentServiceImpl  implements ExperimentService {
	
	@Autowired
	private ExperimentMapper experimentMapper;
	
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(ExperimentExample example) {
		return experimentMapper.countByExample(example);
	}

	/**
  	 * 
  	  * @Title: deleteByExample
  	  * @Description: 根据条件删除多条数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int deleteByExample(ExperimentExample example) {
		return experimentMapper.deleteByExample(example);
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
		return experimentMapper.deleteByPrimaryKey(id);
	}

	 /**
 	 * 
 	  * @Title: insert
 	  * @Description: 插入数据
 	  *  @return  int 返回类型
 	  * @throws
 	 */
	@Override
	public int insert(Experiment record) {
		return experimentMapper.insert(record);
	}

	 /**
 	 * 
 	  * @Title: insertSelective
 	  * @Description: 插入数据,不为null的字段
 	  *  @return  int 返回类型
 	  * @throws
 	 */
	@Override
	public int insertSelective(Experiment record) {
		return experimentMapper.insertSelective(record);
	}

	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<Experiment> 返回类型
	  * @throws
	 */
	@Override
	public List<Experiment> selectByExample(ExperimentExample example) {
		return experimentMapper.selectByExample(example);
	}

	/**
  	 * 
  	  * @Title: selectByPrimaryKey
  	  * @Description: 根据主键查询数据
  	  *  @return  Experiment 返回类型
  	  * @throws
  	 */
	@Override
	public Experiment selectByPrimaryKey(String id) {
		return experimentMapper.selectByPrimaryKey(id);
	}

	/**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExampleSelective(Experiment record, ExperimentExample example) {
		return experimentMapper.updateByExampleSelective(record, example);
	}

	/**
  	 * 
  	  * @Title: updateByExample
  	  * @Description: 按条件更新
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int updateByExample(Experiment record, ExperimentExample example) {
		return experimentMapper.updateByExample(record, example);
	}

	/**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(Experiment record) {
		return experimentMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKey(Experiment record) {
		return experimentMapper.updateByPrimaryKey(record);
	}
}