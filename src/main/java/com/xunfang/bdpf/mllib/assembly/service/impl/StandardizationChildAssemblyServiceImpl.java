package com.xunfang.bdpf.mllib.assembly.service.impl;


import com.xunfang.bdpf.mllib.assembly.entity.StandardizationChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.StandardizationChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.mapper.StandardizationChildAssemblyMapper;
import com.xunfang.bdpf.mllib.assembly.service.StandardizationChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.vo.StandardizationAssemblyVo;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.StringTools;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StandardizationChildAssemblyServiceImpl
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
public class StandardizationChildAssemblyServiceImpl implements StandardizationChildAssemblyService {

	@Autowired
	private StandardizationChildAssemblyMapper standardizationChildAssemblyMapper;

	/**
	 * 标准化子表字段信息更新方法
	 *
	 * @param vo
	 * @return
	 */
	@Override
	public Feedback save(StandardizationAssemblyVo vo) {

		List<StandardizationChildAssembly> oldScaList;//原子表信息
		List<StandardizationChildAssembly> newScaList = new ArrayList<>();//新子表信息
		List<StandardizationChildAssembly> shouldInsert = new ArrayList<>();//需要插入的子表信息
		List<String> shouldDelete = new ArrayList<>();//需要删除的字表信息

		StandardizationChildAssemblyExample scae = new StandardizationChildAssemblyExample();
		scae.createCriteria().andBdpfMllibAssemblyStandardizationIdEqualTo(vo.getId());

		oldScaList = standardizationChildAssemblyMapper.selectByExample(scae);

		if (vo.getStandardizationChildAssemblyId() == null){//子表信息为空
			for (StandardizationChildAssembly e : oldScaList){
				standardizationChildAssemblyMapper.deleteByPrimaryKey(e.getId());
			}
			return new Feedback(true,"子表字段信息更新成功",newScaList);
		}


		for (int i = 0; i < vo.getColumnName().length;i++){
			StandardizationChildAssembly sca = new StandardizationChildAssembly();
			if (vo.getStandardizationChildAssemblyId()[i]==null || StringUtils.isEmpty(vo.getStandardizationChildAssemblyId()[i]))
			{sca.setId(StringTools.getUUID());}
			else
			{ sca.setId(vo.getStandardizationChildAssemblyId()[i]); }
			sca.setBdpfMllibAssemblyStandardizationId(vo.getId());
			sca.setColumnName(vo.getColumnName()[i]);
			sca.setDataType(vo.getColumnType()[i]);
			newScaList.add(sca);
		}
		Map<String,StandardizationChildAssembly> oldScaMap = new HashMap<>();
		Map<String,StandardizationChildAssembly> newScaMap = new HashMap<>();


		for(StandardizationChildAssembly e : newScaList){
			newScaMap.put(e.getId(),e);
		}

		for (StandardizationChildAssembly e : oldScaList){
			oldScaMap.put(e.getId(),e);
			StandardizationChildAssembly o = newScaMap.get(e.getId());
			if (null == o)
				shouldDelete.add(e.getId());
		}

		for (StandardizationChildAssembly e : newScaList){
			StandardizationChildAssembly o = oldScaMap.get(e.getId());
			if (o == null)
				shouldInsert.add(e);
		}
		//执行删除
		StandardizationChildAssemblyExample scaeDel = new StandardizationChildAssemblyExample();
		if (shouldDelete.size() > 0){
			scaeDel.createCriteria().andIdIn(shouldDelete);
			standardizationChildAssemblyMapper.deleteByExample(scaeDel);
		}
		//执行插入
//		for (StandardizationChildAssembly e : shouldInsert) {
//			standardizationChildAssemblyMapper.insert(e);
//		}
		if (!CollectionUtils.isEmpty(shouldInsert))
			standardizationChildAssemblyMapper.batchInsertChild(shouldInsert);
		return new Feedback(true,"子表字段信息更新成功",newScaList);
	}

	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(StandardizationChildAssemblyExample example) {
		return standardizationChildAssemblyMapper.countByExample(example);
	}

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int deleteByExample(StandardizationChildAssemblyExample example) {
		return standardizationChildAssemblyMapper.deleteByExample(example);
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
		return standardizationChildAssemblyMapper.deleteByPrimaryKey(id);
	}

    /**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insert(StandardizationChildAssembly record) {
		return standardizationChildAssemblyMapper.insert(record);
	}

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insertSelective(StandardizationChildAssembly record) {
		return standardizationChildAssemblyMapper.insertSelective(record);
	}

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<NormalizationChildAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<StandardizationChildAssembly> selectByExample(StandardizationChildAssemblyExample example) {
		return standardizationChildAssemblyMapper.selectByExample(example);
	}

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  NormalizationChildAssembly 返回类型
   	  * @throws
   	 */
	@Override
	public StandardizationChildAssembly selectByPrimaryKey(String id) {
		return standardizationChildAssemblyMapper.selectByPrimaryKey(id);
	}

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExampleSelective(StandardizationChildAssembly record, StandardizationChildAssemblyExample example) {
		return standardizationChildAssemblyMapper.updateByExampleSelective(record, example);
	}

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByExample(StandardizationChildAssembly record, StandardizationChildAssemblyExample example) {
		return standardizationChildAssemblyMapper.updateByExample(record, example);
	}

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(StandardizationChildAssembly record) {
		return standardizationChildAssemblyMapper.updateByPrimaryKeySelective(record);
	}

    /**
   	 * 
   	  * @Title: updateByPrimaryKey
   	  * @Description: 按主键更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByPrimaryKey(StandardizationChildAssembly record) {
		return standardizationChildAssemblyMapper.updateByPrimaryKey(record);
	}

}
