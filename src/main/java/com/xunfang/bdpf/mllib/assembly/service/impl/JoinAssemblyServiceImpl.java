package com.xunfang.bdpf.mllib.assembly.service.impl;

import com.xunfang.bdpf.mllib.assembly.entity.JoinAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.JoinAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.mapper.JoinAssemblyMapper;
import com.xunfang.bdpf.mllib.assembly.service.JoinAssemblyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName JoinAssemblyServiceImpl
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
public class JoinAssemblyServiceImpl implements JoinAssemblyService {

	@Autowired
	private JoinAssemblyMapper joinAssemblyMapper;
	
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(JoinAssemblyExample example) {
		return joinAssemblyMapper.countByExample(example);
	}

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int deleteByExample(JoinAssemblyExample example) {
		return joinAssemblyMapper.deleteByExample(example);
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
		return joinAssemblyMapper.deleteByPrimaryKey(id);
	}

    /**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insert(JoinAssembly record) {
		return joinAssemblyMapper.insert(record);
	}

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insertSelective(JoinAssembly record) {
		return joinAssemblyMapper.insertSelective(record);
	}

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<NormalizationChildAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<JoinAssembly> selectByExample(JoinAssemblyExample example) {
		return joinAssemblyMapper.selectByExample(example);
	}

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  NormalizationChildAssembly 返回类型
   	  * @throws
   	 */
	@Override
	public JoinAssembly selectByPrimaryKey(String id) {
		return joinAssemblyMapper.selectByPrimaryKey(id);
	}

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExampleSelective(JoinAssembly record, JoinAssemblyExample example) {
		return joinAssemblyMapper.updateByExampleSelective(record, example);
	}

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByExample(JoinAssembly record, JoinAssemblyExample example) {
		return joinAssemblyMapper.updateByExample(record, example);
	}

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(JoinAssembly record) {
		return joinAssemblyMapper.updateByPrimaryKeySelective(record);
	}

    /**
   	 * 
   	  * @Title: updateByPrimaryKey
   	  * @Description: 按主键更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByPrimaryKey(JoinAssembly record) {
		return joinAssemblyMapper.updateByPrimaryKey(record);
	}

}
