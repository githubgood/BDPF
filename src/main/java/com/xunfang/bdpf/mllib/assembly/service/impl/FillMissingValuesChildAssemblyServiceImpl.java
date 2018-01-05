package com.xunfang.bdpf.mllib.assembly.service.impl;



import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.mapper.FillMissingValuesChildAssemblyMapper;
import com.xunfang.bdpf.mllib.assembly.service.FillMissingValuesChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.vo.FillMissingValuesAssemblyVo;
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
 * @ClassName FillMissingValuesChildAssemblyServiceImpl
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
public class FillMissingValuesChildAssemblyServiceImpl implements FillMissingValuesChildAssemblyService {

	@Autowired
	private FillMissingValuesChildAssemblyMapper fillMissingValuesChildAssemblyMapper;
	
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(FillMissingValuesChildAssemblyExample example) {
		return fillMissingValuesChildAssemblyMapper.countByExample(example);
	}

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int deleteByExample(FillMissingValuesChildAssemblyExample example) {
		return fillMissingValuesChildAssemblyMapper.deleteByExample(example);
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
		return fillMissingValuesChildAssemblyMapper.deleteByPrimaryKey(id);
	}

    /**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insert(FillMissingValuesChildAssembly record) {
		return fillMissingValuesChildAssemblyMapper.insert(record);
	}

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insertSelective(FillMissingValuesChildAssembly record) {
		return fillMissingValuesChildAssemblyMapper.insertSelective(record);
	}

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<NormalizationChildAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<FillMissingValuesChildAssembly> selectByExample(FillMissingValuesChildAssemblyExample example) {
		return fillMissingValuesChildAssemblyMapper.selectByExample(example);
	}

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  NormalizationChildAssembly 返回类型
   	  * @throws
   	 */
	@Override
	public FillMissingValuesChildAssembly selectByPrimaryKey(String id) {
		return fillMissingValuesChildAssemblyMapper.selectByPrimaryKey(id);
	}

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExampleSelective(FillMissingValuesChildAssembly record, FillMissingValuesChildAssemblyExample example) {
		return fillMissingValuesChildAssemblyMapper.updateByExampleSelective(record, example);
	}

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByExample(FillMissingValuesChildAssembly record, FillMissingValuesChildAssemblyExample example) {
		return fillMissingValuesChildAssemblyMapper.updateByExample(record, example);
	}

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(FillMissingValuesChildAssembly record) {
		return fillMissingValuesChildAssemblyMapper.updateByPrimaryKeySelective(record);
	}

    /**
   	 * 
   	  * @Title: updateByPrimaryKey
   	  * @Description: 按主键更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByPrimaryKey(FillMissingValuesChildAssembly record) {
		return fillMissingValuesChildAssemblyMapper.updateByPrimaryKey(record);
	}

	/**
	 * 缺失值填充字段设置
	 *
	 * @param vo
	 * @return
	 */
	@Override
	public Feedback save(FillMissingValuesAssemblyVo vo) {
		List<FillMissingValuesChildAssembly> oldFmvcaList;//原子表信息
		List<FillMissingValuesChildAssembly> newFmvcaList = new ArrayList<>();//新子表信息
		List<FillMissingValuesChildAssembly> shouldInsert = new ArrayList<>();//需要插入的子表信息
		List<String> shouldDelete = new ArrayList<>();

		FillMissingValuesChildAssemblyExample fmvcae = new FillMissingValuesChildAssemblyExample();
		fmvcae.createCriteria().andBdpfMllibAssemblyFillMissingValuesIdEqualTo(vo.getId());

		oldFmvcaList = fillMissingValuesChildAssemblyMapper.selectByExample(fmvcae);

		if (vo.getFillMissingValuesAssemblyId() == null){//子表信息为空
			for (FillMissingValuesChildAssembly e : oldFmvcaList){
				fillMissingValuesChildAssemblyMapper.deleteByPrimaryKey(e.getId());
			}
			return new Feedback(true,"子表字段信息更新成功",newFmvcaList);
		}

		for (int i = 0; i < vo.getColumnName().length;i++){
			FillMissingValuesChildAssembly fmvca = new FillMissingValuesChildAssembly();
			if (vo.getFillMissingValuesAssemblyId()[i]==null || StringUtils.isEmpty(vo.getFillMissingValuesAssemblyId()[i]))
			{fmvca.setId(StringTools.getUUID());}
			else
			{ fmvca.setId(vo.getFillMissingValuesAssemblyId()[i]); }
			fmvca.setBdpfMllibAssemblyFillMissingValuesId(vo.getId());
			fmvca.setColumnName(vo.getColumnName()[i]);
			fmvca.setDataType(vo.getColumnType()[i]);
			newFmvcaList.add(fmvca);
		}
		Map<String,FillMissingValuesChildAssembly> oldFmvcaMap = new HashMap<>();
		Map<String,FillMissingValuesChildAssembly> newFmvcaMap = new HashMap<>();


		for(FillMissingValuesChildAssembly e : newFmvcaList){
			newFmvcaMap.put(e.getId(),e);
		}

		for (FillMissingValuesChildAssembly e : oldFmvcaList){
			oldFmvcaMap.put(e.getId(),e);
			FillMissingValuesChildAssembly o = newFmvcaMap.get(e.getId());
			if (null == o)
				shouldDelete.add(e.getId());
		}

		for (FillMissingValuesChildAssembly e : newFmvcaList){
			FillMissingValuesChildAssembly o = oldFmvcaMap.get(e.getId());
			if (o == null)
				shouldInsert.add(e);
		}
		//执行删除
		FillMissingValuesChildAssemblyExample fmvcaeDel = new FillMissingValuesChildAssemblyExample();
		if (shouldDelete.size() > 0){
			fmvcaeDel.createCriteria().andIdIn(shouldDelete);
			fillMissingValuesChildAssemblyMapper.deleteByExample(fmvcaeDel);
		}
		//执行插入
//		for (FillMissingValuesChildAssembly e : shouldInsert) {
//			fillMissingValuesChildAssemblyMapper.insert(e);
//		}
		if (!CollectionUtils.isEmpty(shouldInsert))
			fillMissingValuesChildAssemblyMapper.batchInsertChild(shouldInsert);
		return new Feedback(true,"缺失值填充字段信息设置成功",newFmvcaList);
	}

}
