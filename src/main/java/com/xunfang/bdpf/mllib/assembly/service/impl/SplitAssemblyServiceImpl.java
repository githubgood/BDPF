package com.xunfang.bdpf.mllib.assembly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunfang.bdpf.mllib.assembly.entity.SplitAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.SplitAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.mapper.SplitAssemblyMapper;
import com.xunfang.bdpf.mllib.assembly.service.SplitAssemblyService;

/**
 * @ClassName SplitAssemblyServiceImpl
 * @Description: 拆分服务实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月14日 上午9:01:14
 * @version V1.0
 */
@Service
@Transactional
public class SplitAssemblyServiceImpl implements SplitAssemblyService {
	
	@Autowired
	private SplitAssemblyMapper splitAssemblyMapper;

	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(SplitAssemblyExample example) {
		return splitAssemblyMapper.countByExample(example);
	}

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int deleteByExample(SplitAssemblyExample example) {
		return splitAssemblyMapper.deleteByExample(example);
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
		return splitAssemblyMapper.deleteByPrimaryKey(id);
	}

    /**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insert(SplitAssembly record) {
		return splitAssemblyMapper.insert(record);
	}

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insertSelective(SplitAssembly record) {
		return splitAssemblyMapper.insertSelective(record);
	}

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<SplitAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<SplitAssembly> selectByExample(SplitAssemblyExample example) {
		return splitAssemblyMapper.selectByExample(example);
	}

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  SplitAssembly 返回类型
   	  * @throws
   	 */
	@Override
	public SplitAssembly selectByPrimaryKey(String id) {
		return splitAssemblyMapper.selectByPrimaryKey(id);
	}

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExampleSelective(SplitAssembly record, SplitAssemblyExample example) {
		return splitAssemblyMapper.updateByExampleSelective(record, example);
	}

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByExample(SplitAssembly record, SplitAssemblyExample example) {
		return splitAssemblyMapper.updateByExample(record, example);
	}

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(SplitAssembly record) {
		return splitAssemblyMapper.updateByPrimaryKeySelective(record);
	}

    /**
   	 * 
   	  * @Title: updateByPrimaryKey
   	  * @Description: 按主键更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByPrimaryKey(SplitAssembly record) {
		return splitAssemblyMapper.updateByPrimaryKey(record);
	}

}
