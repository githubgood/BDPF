package com.xunfang.bdpf.mllib.assembly.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xunfang.bdpf.mllib.assembly.entity.StatisticalanalysisAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.StatisticalanalysisAssemblyExample;

/**
 * 
 * @ClassName StatisticalanalysisAssemblyService
 * @Description: 统计分析Service接口类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月26日 上午11:09:54
 * @version V1.0
 */
public interface StatisticalanalysisAssemblyService {
	
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
    long countByExample(StatisticalanalysisAssemblyExample example);

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int deleteByExample(StatisticalanalysisAssemblyExample example);

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
    int insert(StatisticalanalysisAssembly record);

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
    int insertSelective(StatisticalanalysisAssembly record);

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<StatisticalanalysisAssembly> 返回类型
	  * @throws
	 */
    List<StatisticalanalysisAssembly> selectByExample(StatisticalanalysisAssemblyExample example);

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  StatisticalanalysisAssembly 返回类型
   	  * @throws
   	 */
    StatisticalanalysisAssembly selectByPrimaryKey(String id);

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByExampleSelective(@Param("record") StatisticalanalysisAssembly record, @Param("example") StatisticalanalysisAssemblyExample example);

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int updateByExample(@Param("record") StatisticalanalysisAssembly record, @Param("example") StatisticalanalysisAssemblyExample example);

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKeySelective(StatisticalanalysisAssembly record);

    /**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKey(StatisticalanalysisAssembly record);
    
    /**
     * 
      * @Title: batchInsertStatisticalanalysisAssembly
      * @Description: 批量增加字段
      *  @param list
      *  @return int 返回类型
      * @throws
     */
	int batchInsertStatisticalanalysisAssembly(List<StatisticalanalysisAssembly> list);
	
	/**
	 * 
	  * @Title: queryStatisticalanalysisAssembly
	  * @Description: 根据主键ID查询数据
	  *  @return  List<ConvertAssembly> 返回类型
	  * @throws
	 */
	List<StatisticalanalysisAssembly> queryStatisticalanalysisAssembly(String id);
	 /**
     * 
      * @Title: queryColumn
      * @Description: 根据assemblyLibraryId查询出StatisticalanalysisAssembly集合
      *  @param String 
      *  @return List<StatisticalanalysisAssembly> 返回类型
      * @throws
     */
	 List<StatisticalanalysisAssembly> queryColumn(String assemblyLibraryId);
	 
}
