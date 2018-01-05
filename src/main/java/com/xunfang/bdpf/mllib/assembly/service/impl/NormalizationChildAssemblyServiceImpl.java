package com.xunfang.bdpf.mllib.assembly.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunfang.bdpf.mllib.assembly.entity.NormalizationChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.NormalizationChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.mapper.NormalizationChildAssemblyMapper;
import com.xunfang.bdpf.mllib.assembly.service.NormalizationChildAssemblyService;

/**
 * @ClassName NormalizationChildAssemblyServiceImpl
 * @Description: 归一化明细服务实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月14日 上午9:01:29
 * @version V1.0
 */
@Service
@Transactional
public class NormalizationChildAssemblyServiceImpl implements NormalizationChildAssemblyService {

	@Autowired
	private NormalizationChildAssemblyMapper normalizationChildAssemblyMapper;
	
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(NormalizationChildAssemblyExample example) {
		return normalizationChildAssemblyMapper.countByExample(example);
	}

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int deleteByExample(NormalizationChildAssemblyExample example) {
		return normalizationChildAssemblyMapper.deleteByExample(example);
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
		return normalizationChildAssemblyMapper.deleteByPrimaryKey(id);
	}

    /**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insert(NormalizationChildAssembly record) {
		return normalizationChildAssemblyMapper.insert(record);
	}

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insertSelective(NormalizationChildAssembly record) {
		return normalizationChildAssemblyMapper.insertSelective(record);
	}

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<NormalizationChildAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<NormalizationChildAssembly> selectByExample(NormalizationChildAssemblyExample example) {
		return normalizationChildAssemblyMapper.selectByExample(example);
	}

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  NormalizationChildAssembly 返回类型
   	  * @throws
   	 */
	@Override
	public NormalizationChildAssembly selectByPrimaryKey(String id) {
		return normalizationChildAssemblyMapper.selectByPrimaryKey(id);
	}

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExampleSelective(NormalizationChildAssembly record, NormalizationChildAssemblyExample example) {
		return normalizationChildAssemblyMapper.updateByExampleSelective(record, example);
	}

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByExample(NormalizationChildAssembly record, NormalizationChildAssemblyExample example) {
		return normalizationChildAssemblyMapper.updateByExample(record, example);
	}

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(NormalizationChildAssembly record) {
		return normalizationChildAssemblyMapper.updateByPrimaryKeySelective(record);
	}

    /**
   	 * 
   	  * @Title: updateByPrimaryKey
   	  * @Description: 按主键更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByPrimaryKey(NormalizationChildAssembly record) {
		return normalizationChildAssemblyMapper.updateByPrimaryKey(record);
	}

	/**
	 * 
	  * @Title: queryNormalizationChild
	  * @Description: 根据条件查询数据
	  *  @return  List<NormalizationChildAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<NormalizationChildAssembly> queryNormalizationChild(String id) {
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("id", id);
		return normalizationChildAssemblyMapper.queryNormalizationChild(param);
	}

	/**
     * 
      * @Title: batchInsertNormalizationChildAssembly
      * @Description: 批量插入归一化明细表数据
      *  @param list
      *  @return  int 返回类型
      * @throws
     */
	@Override
	public int batchInsertNormalizationChildAssembly(List<NormalizationChildAssembly> list) {
		return normalizationChildAssemblyMapper.batchInsertNormalizationChildAssembly(list);
	}

}
