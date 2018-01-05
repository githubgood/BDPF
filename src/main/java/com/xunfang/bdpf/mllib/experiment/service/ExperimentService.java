package com.xunfang.bdpf.mllib.experiment.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xunfang.bdpf.mllib.experiment.entity.Experiment;
import com.xunfang.bdpf.mllib.experiment.entity.ExperimentExample;

/**
 * @ClassName ExperimentService
 * @Description: 实验实现接口层
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月20日 上午8:57:22
 * @version V1.0
 */
public interface ExperimentService  {

	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
    long countByExample(ExperimentExample example);

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int deleteByExample(ExperimentExample example);

    /**
 	 * 
 	  * @Title: deleteByPrimaryKey
 	  * @Description: 根据条件删除单条数据
 	  *  @return  int 返回类型
 	  * @throws
 	 */
    int deleteByPrimaryKey(String id);

    /**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
    int insert(Experiment record);

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
    int insertSelective(Experiment record);

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<Experiment> 返回类型
	  * @throws
	 */
    List<Experiment> selectByExample(ExperimentExample example);

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  Experiment 返回类型
   	  * @throws
   	 */
    Experiment selectByPrimaryKey(String id);

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByExampleSelective(@Param("record") Experiment record, @Param("example") ExperimentExample example);

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int updateByExample(@Param("record") Experiment record, @Param("example") ExperimentExample example);

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKeySelective(Experiment record);

    /**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKey(Experiment record);
}
