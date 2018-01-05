package com.xunfang.bdpf.mllib.assembly.service.impl;


import com.xunfang.bdpf.mllib.assembly.entity.JoinChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.JoinChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.mapper.JoinChildAssemblyMapper;
import com.xunfang.bdpf.mllib.assembly.service.JoinChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.vo.JoinAssemblyVo;
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
 * @ClassName JoinChildAssemblyServiceImpl
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
public class JoinChildAssemblyServiceImpl implements JoinChildAssemblyService {

	@Autowired
	private JoinChildAssemblyMapper joinChildAssemblyMapper;
	
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(JoinChildAssemblyExample example) {
		return joinChildAssemblyMapper.countByExample(example);
	}

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int deleteByExample(JoinChildAssemblyExample example) {
		return joinChildAssemblyMapper.deleteByExample(example);
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
		return joinChildAssemblyMapper.deleteByPrimaryKey(id);
	}

    /**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insert(JoinChildAssembly record) {
		return joinChildAssemblyMapper.insert(record);
	}

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insertSelective(JoinChildAssembly record) {
		return joinChildAssemblyMapper.insertSelective(record);
	}

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<NormalizationChildAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<JoinChildAssembly> selectByExample(JoinChildAssemblyExample example) {
		return joinChildAssemblyMapper.selectByExample(example);
	}

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  NormalizationChildAssembly 返回类型
   	  * @throws
   	 */
	@Override
	public JoinChildAssembly selectByPrimaryKey(String id) {
		return joinChildAssemblyMapper.selectByPrimaryKey(id);
	}

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExampleSelective(JoinChildAssembly record, JoinChildAssemblyExample example) {
		return joinChildAssemblyMapper.updateByExampleSelective(record, example);
	}

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByExample(JoinChildAssembly record, JoinChildAssemblyExample example) {
		return joinChildAssemblyMapper.updateByExample(record, example);
	}

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(JoinChildAssembly record) {
		return joinChildAssemblyMapper.updateByPrimaryKeySelective(record);
	}

    /**
   	 * 
   	  * @Title: updateByPrimaryKey
   	  * @Description: 按主键更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByPrimaryKey(JoinChildAssembly record) {
		return joinChildAssemblyMapper.updateByPrimaryKey(record);
	}


	/**
	 * join 子表组件编辑设置
	 *
	 * @param vo
	 * @return
	 */
	@Override
	public Feedback save(JoinAssemblyVo vo) {
		List<JoinChildAssembly> oldJcaList;//原子表信息
		List<JoinChildAssembly> newJcaList = new ArrayList<>();//新子表信息
		List<JoinChildAssembly> shouldInsert = new ArrayList<>();//需要插入的子表信息
		List<String> shouldDelete = new ArrayList<>();

		JoinChildAssemblyExample jcae = new JoinChildAssemblyExample();
		jcae.createCriteria().andBdpfMllibAssemblyJoinIdEqualTo(vo.getId());

		oldJcaList = joinChildAssemblyMapper.selectByExample(jcae);

		if (vo.getJoinChildAssemblyId() == null ){//子表信息为空
			for (JoinChildAssembly e : oldJcaList){
				joinChildAssemblyMapper.deleteByPrimaryKey(e.getId());
			}
			return new Feedback(true,"子表字段信息更新成功",newJcaList);
		}

		if (vo.getOperationType().length < 1)
			return new Feedback(true,"字段信息未更新",oldJcaList);
		for (int i = 0; i < vo.getColumnName().length;i++){
			JoinChildAssembly jca = new JoinChildAssembly();
			if (vo.getJoinChildAssemblyId()[i]==null || StringUtils.isEmpty(vo.getJoinChildAssemblyId()[i]))
			{jca.setId(StringTools.getUUID());}
			else
			{ jca.setId(vo.getJoinChildAssemblyId()[i]); }
			jca.setBdpfMllibAssemblyJoinId(vo.getId());
			jca.setColumnIn(vo.getColumnName()[i]);
			jca.setColumnOut(vo.getColumnName()[i]);
			jca.setDataType(vo.getColumnType()[i]);
			jca.setOperationType(vo.getOperationType()[i]);
			newJcaList.add(jca);
		}
		Map<String,JoinChildAssembly> oldJcaMap = new HashMap<>();
		Map<String,JoinChildAssembly> newJcaMap = new HashMap<>();


		for(JoinChildAssembly e : newJcaList){
			newJcaMap.put(e.getId(),e);
		}

		for (JoinChildAssembly e : oldJcaList){
			oldJcaMap.put(e.getId(),e);
			JoinChildAssembly o = newJcaMap.get(e.getId());
			if (null == o && e.getOperationType() == vo.getOperationType()[0])
				shouldDelete.add(e.getId());
		}

		for (JoinChildAssembly e : newJcaList){
			JoinChildAssembly o = oldJcaMap.get(e.getId());
			if (o == null) {
				shouldInsert.add(e);
			}
		}
		//执行删除
		JoinChildAssemblyExample jcaeDel = new JoinChildAssemblyExample();
		if (shouldDelete.size() > 0){
			jcaeDel.createCriteria().andIdIn(shouldDelete);
			joinChildAssemblyMapper.deleteByExample(jcaeDel);
		}
		//执行插入
//		for (JoinChildAssembly e : shouldInsert) {
//			joinChildAssemblyMapper.insert(e);
//		}
		if (!CollectionUtils.isEmpty(shouldInsert))
			joinChildAssemblyMapper.batchInsertChild(shouldInsert);

		return new Feedback(true,"join子表信息设置成功",newJcaList);
	}
}
