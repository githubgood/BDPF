package com.xunfang.bdpf.mllib.assembly.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xunfang.bdpf.mllib.assembly.entity.FeatureengineeringAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FeatureengineeringAssemblyExample;

/**
 * 
 * @ClassName FeatureengineeringAssemblyMapper
 * @Description: 特征工程表Mapper类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月31日 上午9:10:30
 * @version V1.0
 */
public interface FeatureengineeringAssemblyMapper {
	
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
    long countByExample(FeatureengineeringAssemblyExample example);

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int deleteByExample(FeatureengineeringAssemblyExample example);

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
    int insert(FeatureengineeringAssembly record);

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
    int insertSelective(FeatureengineeringAssembly record);

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<FeatureengineeringAssembly> 返回类型
	  * @throws
	 */
    List<FeatureengineeringAssembly> selectByExample(FeatureengineeringAssemblyExample example);

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  FeatureengineeringChildAssembly 返回类型
   	  * @throws
   	 */
    FeatureengineeringAssembly selectByPrimaryKey(String id);

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByExampleSelective(@Param("record") FeatureengineeringAssembly record, @Param("example") FeatureengineeringAssemblyExample example);

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int updateByExample(@Param("record") FeatureengineeringAssembly record, @Param("example") FeatureengineeringAssemblyExample example);

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKeySelective(FeatureengineeringAssembly record);

    /**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKey(FeatureengineeringAssembly record);
    /**
   	 * 
   	  * @Title: queryFeatureengineeringAssemblyAssembly
   	  * @Description: 根据主键ID查询数据
   	  *  @return  List<StatisticalanalysisAssembly>  返回类型
   	  * @throws
   	 */
    List<FeatureengineeringAssembly> queryFeatureengineeringAssembly(Map<String, Object> param);
    /**
     * 
      * @Title: batchInsertFeatureengineeringAssembly
      * @Description: 批量增加字段
      *  @param list
      *  @return int 返回类型
      * @throws
     */
	int batchInsertFeatureengineeringAssembly(List<FeatureengineeringAssembly> list);
	/**
	* 
	* @Title: updateBybdpfMllibAssemblyId
	* @Description: 按BybdpfMllibAssemblyId更新
	*  @return  int 返回类型
	* @throws
	*/
	int updateBybdpfMllibAssemblyId(FeatureengineeringAssembly record);
}