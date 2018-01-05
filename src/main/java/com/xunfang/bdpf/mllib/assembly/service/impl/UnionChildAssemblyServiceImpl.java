package com.xunfang.bdpf.mllib.assembly.service.impl;


import com.xunfang.bdpf.mllib.assembly.entity.UnionChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.UnionChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.mapper.UnionChildAssemblyMapper;
import com.xunfang.bdpf.mllib.assembly.service.UnionChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.vo.UnionAssemblyVo;
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
 * @ClassName UnionAssemblyServiceImpl
 * @Description: 组件Service接口服务类
 * Copyright: Copyright (c) 2017
 * Company:深圳市讯方技术股份有限公司
 *
 * @author ylh
 * @date 2017年10月18日
 * @version V1.0
 */
@Service
@Transactional
public class UnionChildAssemblyServiceImpl implements UnionChildAssemblyService {

	@Autowired
	private UnionChildAssemblyMapper unionChildAssemblyMapper;
	
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(UnionChildAssemblyExample example) {
		return unionChildAssemblyMapper.countByExample(example);
	}

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int deleteByExample(UnionChildAssemblyExample example) {
		return unionChildAssemblyMapper.deleteByExample(example);
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
		return unionChildAssemblyMapper.deleteByPrimaryKey(id);
	}

    /**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insert(UnionChildAssembly record) {
		return unionChildAssemblyMapper.insert(record);
	}

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insertSelective(UnionChildAssembly record) {
		return unionChildAssemblyMapper.insertSelective(record);
	}

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<NormalizationChildAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<UnionChildAssembly> selectByExample(UnionChildAssemblyExample example) {
		return unionChildAssemblyMapper.selectByExample(example);
	}

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  NormalizationChildAssembly 返回类型
   	  * @throws
   	 */
	@Override
	public UnionChildAssembly selectByPrimaryKey(String id) {
		return unionChildAssemblyMapper.selectByPrimaryKey(id);
	}

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExampleSelective(UnionChildAssembly record, UnionChildAssemblyExample example) {
		return unionChildAssemblyMapper.updateByExampleSelective(record, example);
	}

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByExample(UnionChildAssembly record, UnionChildAssemblyExample example) {
		return unionChildAssemblyMapper.updateByExample(record, example);
	}

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(UnionChildAssembly record) {
		return unionChildAssemblyMapper.updateByPrimaryKeySelective(record);
	}

    /**
   	 * 
   	  * @Title: updateByPrimaryKey
   	  * @Description: 按主键更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByPrimaryKey(UnionChildAssembly record) {
		return unionChildAssemblyMapper.updateByPrimaryKey(record);
	}

	/**
	 * union 子表编辑
	 *
	 * @param vo
	 * @return
	 */
	@Override
	public Feedback save(UnionAssemblyVo vo) {
		List<UnionChildAssembly> oldUcaList;//原子表信息
		List<UnionChildAssembly> newUcaList = new ArrayList<>();//新子表信息
		List<UnionChildAssembly> shouldInsert = new ArrayList<>();//需要插入的子表信息

		List<String> shouldDelete = new ArrayList<>();

		UnionChildAssemblyExample ucae = new UnionChildAssemblyExample();
		ucae.createCriteria().andBdpfMllibAssemblyUnionIdEqualTo(vo.getId());

		oldUcaList = unionChildAssemblyMapper.selectByExample(ucae);

		if (vo.getUnionChildAssemblyId() == null){//子表信息为空
			for (UnionChildAssembly e : oldUcaList){
				unionChildAssemblyMapper.deleteByPrimaryKey(e.getId());
			}
			return new Feedback(true,"子表字段信息更新成功",newUcaList);
		}
		if (vo.getOperationType().length < 1)
			return new Feedback(true,"字段信息未更新",oldUcaList);
		for (int i = 0; i < vo.getColumnName().length;i++){
			UnionChildAssembly uca = new UnionChildAssembly();
			if (vo.getUnionChildAssemblyId()[i]==null || StringUtils.isEmpty(vo.getUnionChildAssemblyId()[i]))
			{uca.setId(StringTools.getUUID());}
			else
			{ uca.setId(vo.getUnionChildAssemblyId()[i]); }
			uca.setBdpfMllibAssemblyUnionId(vo.getId());
			uca.setColumnIn(vo.getColumnName()[i]);
			uca.setColumnOut(vo.getColumnName()[i]);
			uca.setDataType(vo.getColumnType()[i]);
			uca.setOperationType(vo.getOperationType()[i]);
			newUcaList.add(uca);
		}

		Map<String,UnionChildAssembly> oldUcaMap = new HashMap<>();
		Map<String,UnionChildAssembly> newUcaMap = new HashMap<>();


		for(UnionChildAssembly e : newUcaList){
			newUcaMap.put(e.getId(),e);
		}

		for (UnionChildAssembly e : oldUcaList){
			oldUcaMap.put(e.getId(),e);
			UnionChildAssembly o = newUcaMap.get(e.getId());

			if (null == o && e.getOperationType() == vo.getOperationType()[0])
				shouldDelete.add(e.getId());
		}

		for (UnionChildAssembly e : newUcaList){
			UnionChildAssembly o = oldUcaMap.get(e.getId());
			if (o == null)
				shouldInsert.add(e);
		}
		//执行删除
		UnionChildAssemblyExample ucaeDel = new UnionChildAssemblyExample();
		if (shouldDelete.size() > 0){
			ucaeDel.createCriteria().andIdIn(shouldDelete);
			unionChildAssemblyMapper.deleteByExample(ucaeDel);
		}
		//执行插入
//		for (UnionChildAssembly e : shouldInsert) {
//			unionChildAssemblyMapper.insert(e);
//		}
		if (!CollectionUtils.isEmpty(shouldInsert))
			unionChildAssemblyMapper.batchInsertChild(shouldInsert);
		return new Feedback(true,"union子表信息设置成功",newUcaList);
	}
}
