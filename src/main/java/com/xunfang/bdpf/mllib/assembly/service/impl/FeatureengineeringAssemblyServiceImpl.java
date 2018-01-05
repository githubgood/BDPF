package com.xunfang.bdpf.mllib.assembly.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunfang.bdpf.mllib.assembly.entity.FeatureengineeringAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FeatureengineeringAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.StatisticalanalysisAssembly;
import com.xunfang.bdpf.mllib.assembly.mapper.FeatureengineeringAssemblyMapper;
import com.xunfang.bdpf.mllib.assembly.service.FeatureengineeringAssemblyService;

/**
 * 
 * @ClassName FeatureengineeringAssemblyServiceImpl
 * @Description: 特征工程表Service实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月31日 上午9:25:05
 * @version V1.0
 */
@Service
@Transactional
public class FeatureengineeringAssemblyServiceImpl implements FeatureengineeringAssemblyService {

	@Autowired
	private FeatureengineeringAssemblyMapper featureengineeringAssemblyMapper;

	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(FeatureengineeringAssemblyExample example) {
		return featureengineeringAssemblyMapper.countByExample(example);
	}

	 /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int deleteByExample(FeatureengineeringAssemblyExample example) {
		return featureengineeringAssemblyMapper.deleteByExample(example);
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
		return featureengineeringAssemblyMapper.deleteByPrimaryKey(id);
	}

	/**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insert(FeatureengineeringAssembly record) {
		return featureengineeringAssemblyMapper.insert(record);
	}

	/**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insertSelective(FeatureengineeringAssembly record) {
		return featureengineeringAssemblyMapper.insertSelective(record);
	}

	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<FeatureengineeringAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<FeatureengineeringAssembly> selectByExample(FeatureengineeringAssemblyExample example) {
		return featureengineeringAssemblyMapper.selectByExample(example);
	}

	/**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  FeatureengineeringChildAssembly 返回类型
   	  * @throws
   	 */
	@Override
	public FeatureengineeringAssembly selectByPrimaryKey(String id) {
		return featureengineeringAssemblyMapper.selectByPrimaryKey(id);
	}

	/**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExampleSelective(FeatureengineeringAssembly record, FeatureengineeringAssemblyExample example) {
		return featureengineeringAssemblyMapper.updateByExampleSelective(record, example);
	}

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByExample(FeatureengineeringAssembly record, FeatureengineeringAssemblyExample example) {
		return featureengineeringAssemblyMapper.updateByExample(record, example);
	}

	/**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(FeatureengineeringAssembly record) {
		return featureengineeringAssemblyMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKey(FeatureengineeringAssembly record) {
		return featureengineeringAssemblyMapper.updateByPrimaryKey(record);
	}
	
	/**
	 * 
	  * @Title: queryFeatureengineeringAssembly
	  * @Description: 根据主键ID查询数据
	  *  @return  List<StatisticalanalysisAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<FeatureengineeringAssembly> queryFeatureengineeringAssembly(String id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		return featureengineeringAssemblyMapper.queryFeatureengineeringAssembly(param);
	}
	
	   /**
     * 
      * @Title: batchInsertFeatureengineeringAssembly
      * @Description: 批量增加字段
      *  @param list
      *  @return int 返回类型
      * @throws
     */
	@Override
	public int batchInsertFeatureengineeringAssembly(List<FeatureengineeringAssembly> list) {
		if(list.size() == 0){
			return 0;
		}
		return featureengineeringAssemblyMapper.batchInsertFeatureengineeringAssembly(list);
	}

	/**
	* 
	* @Title: updateBybdpfMllibAssemblyId
	* @Description: 按BybdpfMllibAssemblyId更新
	*  @return  int 返回类型
	* @throws
	*/
	@Override
	public int updateBybdpfMllibAssemblyId(FeatureengineeringAssembly record) {
		return featureengineeringAssemblyMapper.updateBybdpfMllibAssemblyId(record);
	}

}
