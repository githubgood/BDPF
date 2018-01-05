package com.xunfang.bdpf.mllib.assembly.service.impl;

import com.xunfang.bdpf.mllib.assembly.entity.AddSerialNumChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.AddSerialNumChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.mapper.AddSerialNumChildAssemblyMapper;
import com.xunfang.bdpf.mllib.assembly.service.AddSerialNumChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.vo.AddSerialNumAssemblyVo;
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
 * @ClassName AddSerialNumChildAssemblyServiceImpl
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
public class AddSerialNumChildAssemblyServiceImpl implements AddSerialNumChildAssemblyService {

	@Autowired
	private AddSerialNumChildAssemblyMapper addSerialNumChildAssemblyMapper;
	
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(AddSerialNumChildAssemblyExample example) {
		return addSerialNumChildAssemblyMapper.countByExample(example);
	}

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int deleteByExample(AddSerialNumChildAssemblyExample example) {
		return addSerialNumChildAssemblyMapper.deleteByExample(example);
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
		return addSerialNumChildAssemblyMapper.deleteByPrimaryKey(id);
	}

    /**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insert(AddSerialNumChildAssembly record) {
		return addSerialNumChildAssemblyMapper.insert(record);
	}

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insertSelective(AddSerialNumChildAssembly record) {
		return addSerialNumChildAssemblyMapper.insertSelective(record);
	}

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<NormalizationChildAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<AddSerialNumChildAssembly> selectByExample(AddSerialNumChildAssemblyExample example) {
		return addSerialNumChildAssemblyMapper.selectByExample(example);
	}

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  NormalizationChildAssembly 返回类型
   	  * @throws
   	 */
	@Override
	public AddSerialNumChildAssembly selectByPrimaryKey(String id) {
		return addSerialNumChildAssemblyMapper.selectByPrimaryKey(id);
	}

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExampleSelective(AddSerialNumChildAssembly record, AddSerialNumChildAssemblyExample example) {
		return addSerialNumChildAssemblyMapper.updateByExampleSelective(record, example);
	}

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByExample(AddSerialNumChildAssembly record, AddSerialNumChildAssemblyExample example) {
		return addSerialNumChildAssemblyMapper.updateByExample(record, example);
	}

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(AddSerialNumChildAssembly record) {
		return addSerialNumChildAssemblyMapper.updateByPrimaryKeySelective(record);
	}

    /**
   	 * 
   	  * @Title: updateByPrimaryKey
   	  * @Description: 按主键更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByPrimaryKey(AddSerialNumChildAssembly record) {
		return addSerialNumChildAssemblyMapper.updateByPrimaryKey(record);
	}

	/**
	 * 增加序号列设置
	 *
	 * @param vo
	 * @return
	 */
	@Override
	public Feedback save(AddSerialNumAssemblyVo vo) {
		List<AddSerialNumChildAssembly> oldAsncaList;//原子表信息
		List<AddSerialNumChildAssembly> newAsncaList = new ArrayList<>();//新子表信息
		List<AddSerialNumChildAssembly> shouldInsert = new ArrayList<>();//需要插入的子表信息
		List<String> shouldDelete = new ArrayList<>();

		AddSerialNumChildAssemblyExample asncae = new AddSerialNumChildAssemblyExample();
		asncae.createCriteria().andBdpfMllibAssemblyAddSerialNumIdEqualTo(vo.getId());

		oldAsncaList = addSerialNumChildAssemblyMapper.selectByExample(asncae);

		if (vo.getAddSerialNumAssemblyId() == null ){//子表信息为空
			for (AddSerialNumChildAssembly e : oldAsncaList){
				addSerialNumChildAssemblyMapper.deleteByPrimaryKey(e.getId());
			}
			return new Feedback(true,"子表字段信息更新成功",newAsncaList);
		}
		for (int i = 0; i < vo.getColumnName().length;i++){
			AddSerialNumChildAssembly asnca = new AddSerialNumChildAssembly();
			if (vo.getAddSerialNumAssemblyId()[i]==null || StringUtils.isEmpty(vo.getAddSerialNumAssemblyId()[i]))
			{asnca.setId(StringTools.getUUID());}
			else
			{ asnca.setId(vo.getAddSerialNumAssemblyId()[i]); }
			asnca.setBdpfMllibAssemblyAddSerialNumId(vo.getId());
			asnca.setColumnName(vo.getColumnName()[i]);
			asnca.setDataType(vo.getColumnType()[i]);
			newAsncaList.add(asnca);
		}
		
		Map<String,AddSerialNumChildAssembly> oldAsncaMap = new HashMap<>();
		Map<String,AddSerialNumChildAssembly> newAsncaMap = new HashMap<>();


		for(AddSerialNumChildAssembly e : newAsncaList){
			newAsncaMap.put(e.getId(),e);
		}

		for (AddSerialNumChildAssembly e : oldAsncaList){
			oldAsncaMap.put(e.getId(),e);
			AddSerialNumChildAssembly o = newAsncaMap.get(e.getId());
			if (null == o)
				shouldDelete.add(e.getId());
		}

		for (AddSerialNumChildAssembly e : newAsncaList){
			AddSerialNumChildAssembly o = oldAsncaMap.get(e.getId());
			if (o == null)
				shouldInsert.add(e);
		}
		//执行删除
		AddSerialNumChildAssemblyExample asncaeDel = new AddSerialNumChildAssemblyExample();
		if (shouldDelete.size() > 0){
			asncaeDel.createCriteria().andIdIn(shouldDelete);
			addSerialNumChildAssemblyMapper.deleteByExample(asncaeDel);
		}
		//执行插入
//		for (AddSerialNumChildAssembly e : shouldInsert) {
//			addSerialNumChildAssemblyMapper.insert(e);
//		}
		if (!CollectionUtils.isEmpty(shouldInsert))
			addSerialNumChildAssemblyMapper.batchInsertChild(shouldInsert);
		return new Feedback(true,"增加序号列字段信息设置成功",newAsncaList);
	}

}
