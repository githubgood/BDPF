package com.xunfang.bdpf.mllib.assembly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunfang.bdpf.mllib.assembly.entity.MachinelearningAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.MachinelearningAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.mapper.MachinelearningAssemblyMapper;
import com.xunfang.bdpf.mllib.assembly.service.MachinelearningAssemblyService;

/**
 * 
 * @ClassName MachinelearningAssemblyServiceImpl
 * @Description: 机器学习Service实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月28日 上午9:29:23
 * @version V1.0
 */
@Service
@Transactional
public class MachinelearningAssemblyServiceImpl implements MachinelearningAssemblyService {

	@Autowired
	private MachinelearningAssemblyMapper machinelearningAssemblyMapper;
	
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(MachinelearningAssemblyExample example) {
		return machinelearningAssemblyMapper.countByExample(example);
	}

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int deleteByExample(MachinelearningAssemblyExample example) {
		return machinelearningAssemblyMapper.deleteByExample(example);
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
		return machinelearningAssemblyMapper.deleteByPrimaryKey(id);
	}

    /**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insert(MachinelearningAssembly record) {
		return machinelearningAssemblyMapper.insert(record);
	}

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insertSelective(MachinelearningAssembly record) {
		return machinelearningAssemblyMapper.insertSelective(record);
	}

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<MachinelearningAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<MachinelearningAssembly> selectByExample(MachinelearningAssemblyExample example) {
		return machinelearningAssemblyMapper.selectByExample(example);
	}

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  MachinelearningAssembly 返回类型
   	  * @throws
   	 */
	@Override
	public MachinelearningAssembly selectByPrimaryKey(String id) {
		return machinelearningAssemblyMapper.selectByPrimaryKey(id);
	}

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExampleSelective(MachinelearningAssembly record, MachinelearningAssemblyExample example) {
		return machinelearningAssemblyMapper.updateByExampleSelective(record, example);
	}

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByExample(MachinelearningAssembly record, MachinelearningAssemblyExample example) {
		return machinelearningAssemblyMapper.updateByExample(record, example);
	}

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(MachinelearningAssembly record) {
		return machinelearningAssemblyMapper.updateByPrimaryKeySelective(record);
	}

    /**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKey(MachinelearningAssembly record) {
		return machinelearningAssemblyMapper.updateByPrimaryKey(record);
	}

}
