package com.xunfang.bdpf.mllib.assembly.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xunfang.bdpf.mllib.assembly.vo.FeatureEngineeringAssemblyVo;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.StringTools;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunfang.bdpf.mllib.assembly.entity.FeatureengineeringChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FeatureengineeringChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.mapper.FeatureengineeringChildAssemblyMapper;
import com.xunfang.bdpf.mllib.assembly.service.FeatureengineeringChildAssemblyService;

/**
 * 
 * @ClassName FeatureengineeringChildAssemblyServiceImpl
 * @Description: 特征工程子表Service实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月31日 上午9:25:10
 * @version V1.0
 */
@Service
@Transactional
public class FeatureengineeringChildAssemblyServiceImpl implements FeatureengineeringChildAssemblyService {

	@Autowired
	private FeatureengineeringChildAssemblyMapper featureengineeringChildAssemblyMapper;
	
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(FeatureengineeringChildAssemblyExample example) {
		return featureengineeringChildAssemblyMapper.countByExample(example);
	}

	 /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int deleteByExample(FeatureengineeringChildAssemblyExample example) {
		return featureengineeringChildAssemblyMapper.deleteByExample(example);
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
		return featureengineeringChildAssemblyMapper.deleteByPrimaryKey(id);
	}

	/**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insert(FeatureengineeringChildAssembly record) {
		return featureengineeringChildAssemblyMapper.insert(record);
	}

	/**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insertSelective(FeatureengineeringChildAssembly record) {
		return featureengineeringChildAssemblyMapper.insertSelective(record);
	}

	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<FeatureengineeringChildAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<FeatureengineeringChildAssembly> selectByExample(FeatureengineeringChildAssemblyExample example) {
		return featureengineeringChildAssemblyMapper.selectByExample(example);
	}

	/**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  FeatureengineeringChildAssembly 返回类型
   	  * @throws
   	 */
	@Override
	public FeatureengineeringChildAssembly selectByPrimaryKey(String id) {
		return featureengineeringChildAssemblyMapper.selectByPrimaryKey(id);
	}

	/**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExampleSelective(FeatureengineeringChildAssembly record,
			FeatureengineeringChildAssemblyExample example) {
		return featureengineeringChildAssemblyMapper.updateByExampleSelective(record, example);
	}

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByExample(FeatureengineeringChildAssembly record, FeatureengineeringChildAssemblyExample example) {
		return featureengineeringChildAssemblyMapper.updateByExample(record, example);
	}

	/**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(FeatureengineeringChildAssembly record) {
		return featureengineeringChildAssemblyMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKey(FeatureengineeringChildAssembly record) {
		return featureengineeringChildAssemblyMapper.updateByPrimaryKey(record);
	}

	/**
     * 
      * @Title: batchInsertFeatureengineeringChild
      * @Description: 批量插入统计分析明细表数据
      *  @param list
      *  @return  int 返回类型
      * @throws
     */
	@Override
	public int batchInsertFeatureengineeringChild(List<FeatureengineeringChildAssembly> list) {
		return featureengineeringChildAssemblyMapper.batchInsertFeatureengineeringChild(list);
	}

	/**
	 * 特征工程子表保存
	 *
	 * @param vo
	 * @return
	 */
	@Override
	public Feedback save(FeatureEngineeringAssemblyVo vo) {
		List<FeatureengineeringChildAssembly> oldFcaList;//原子表信息
		List<FeatureengineeringChildAssembly> newFcaList = new ArrayList<>();//新子表信息
		List<FeatureengineeringChildAssembly> shouldInsert = new ArrayList<>();//需要插入的子表信息
		List<String> shouldDelete = new ArrayList<>();//需要删除的字表信息

		FeatureengineeringChildAssemblyExample fcae = new FeatureengineeringChildAssemblyExample();
		fcae.createCriteria().andBdpfMllibAssemblyFeatureengineeringIdEqualTo(vo.getId());

		oldFcaList = featureengineeringChildAssemblyMapper.selectByExample(fcae);

		if (vo.getFeatureEngineeringChildAssemblyId() == null ){//子表信息为空
			for (FeatureengineeringChildAssembly e : oldFcaList){
				featureengineeringChildAssemblyMapper.deleteByPrimaryKey(e.getId());
			}
			return new Feedback(true,"子表字段信息更新完成",newFcaList);
		}

		if (vo.getFeatureEngineeringChildAssemblyId().length < 1){//子表信息为空
			return new Feedback(true,"子表字段信息未进行更新",oldFcaList);
		}
		for (int i = 0; i < vo.getColumnName().length;i++){
			FeatureengineeringChildAssembly fca = new FeatureengineeringChildAssembly();
			if (vo.getFeatureEngineeringChildAssemblyId()[i]==null || StringUtils.isEmpty(vo.getFeatureEngineeringChildAssemblyId()[i]))
			{fca.setId(StringTools.getUUID());}
			else
			{ fca.setId(vo.getFeatureEngineeringChildAssemblyId()[i]); }
			fca.setBdpfMllibAssemblyFeatureengineeringId(vo.getId());
			fca.setName(vo.getColumnName()[i]);
			fca.setDataType(vo.getColumnType()[i]);
			fca.setXh(i+1);
			newFcaList.add(fca);
		}
		Map<String,FeatureengineeringChildAssembly> oldFcaMap = new HashMap<>();
		Map<String,FeatureengineeringChildAssembly> newFcaMap = new HashMap<>();


		for(FeatureengineeringChildAssembly e : newFcaList){
			newFcaMap.put(e.getId(),e);
		}

		for (FeatureengineeringChildAssembly e : oldFcaList){
			oldFcaMap.put(e.getId(),e);
			FeatureengineeringChildAssembly o = newFcaMap.get(e.getId());
			if (null == o)
				shouldDelete.add(e.getId());
		}

		for (FeatureengineeringChildAssembly e : newFcaList){
			FeatureengineeringChildAssembly o = oldFcaMap.get(e.getId());
			if (o == null)
				shouldInsert.add(e);
		}
		//执行删除
		FeatureengineeringChildAssemblyExample scaeDel = new FeatureengineeringChildAssemblyExample();
		if (shouldDelete.size() > 0){
			scaeDel.createCriteria().andIdIn(shouldDelete);
			featureengineeringChildAssemblyMapper.deleteByExample(scaeDel);
		}
		//执行插入
//		for (FeatureengineeringChildAssembly e : shouldInsert) {
//			featureengineeringChildAssemblyMapper.insert(e);
//		}
		if (!CollectionUtils.isEmpty(shouldInsert))
			featureengineeringChildAssemblyMapper.batchInsertFeatureengineeringChild(shouldInsert);
		return new Feedback(true,"子表字段信息更新成功",newFcaList);
	}

	/**
     * 
      * @Title: queryByMainTableId
      * @Description: 根据主表id查询附表统计分析明细表数据
      *  @param FeatureengineeringChildAssembly
      *  @return  FeatureengineeringChildAssembly 返回类型
      * @throws
     */
	@Override
	public FeatureengineeringChildAssembly queryByMainTableId(FeatureengineeringChildAssembly record) {
		List<FeatureengineeringChildAssembly> featureengineeringChildAssemblys = new ArrayList<FeatureengineeringChildAssembly>();
		featureengineeringChildAssemblys = featureengineeringChildAssemblyMapper.queryByMainTableId(record);
		if(featureengineeringChildAssemblys.size()>0){
			return featureengineeringChildAssemblys.get(0);
		}
		return null;
	}
	
	/**
     * 
      * @Title: deleteByMainTableId
      * @Description: 根据主表id删除附表数据
      *  @param FeatureengineeringChildAssembly
      *  @return  int 返回类型
      * @throws
     */
	@Override
	public int deleteByMainTableId(FeatureengineeringChildAssembly record) {
		
		return featureengineeringChildAssemblyMapper.deleteByMainTableId(record);
	}

}
