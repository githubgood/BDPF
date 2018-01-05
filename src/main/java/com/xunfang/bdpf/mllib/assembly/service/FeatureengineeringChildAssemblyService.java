package com.xunfang.bdpf.mllib.assembly.service;

import java.util.List;

import com.xunfang.bdpf.mllib.assembly.vo.FeatureEngineeringAssemblyVo;
import com.xunfang.utils.Feedback;
import org.apache.ibatis.annotations.Param;

import com.xunfang.bdpf.mllib.assembly.entity.FeatureengineeringChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FeatureengineeringChildAssemblyExample;

/**
 * 
 * @ClassName FeatureengineeringChildAssemblyService
 * @Description: 特征工程子表Service接口类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月31日 上午9:10:30
 * @version V1.0
 */
public interface FeatureengineeringChildAssemblyService {
	
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
    long countByExample(FeatureengineeringChildAssemblyExample example);

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int deleteByExample(FeatureengineeringChildAssemblyExample example);

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
    int insert(FeatureengineeringChildAssembly record);

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
    int insertSelective(FeatureengineeringChildAssembly record);

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<FeatureengineeringChildAssembly> 返回类型
	  * @throws
	 */
    List<FeatureengineeringChildAssembly> selectByExample(FeatureengineeringChildAssemblyExample example);

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  FeatureengineeringChildAssembly 返回类型
   	  * @throws
   	 */
    FeatureengineeringChildAssembly selectByPrimaryKey(String id);

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByExampleSelective(@Param("record") FeatureengineeringChildAssembly record, @Param("example") FeatureengineeringChildAssemblyExample example);

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int updateByExample(@Param("record") FeatureengineeringChildAssembly record, @Param("example") FeatureengineeringChildAssemblyExample example);

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKeySelective(FeatureengineeringChildAssembly record);

    /**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKey(FeatureengineeringChildAssembly record);
    
    /**
     * 
      * @Title: batchInsertFeatureengineeringChild
      * @Description: 批量插入统计分析明细表数据
      *  @param list
      *  @return  int 返回类型
      * @throws
     */
    int batchInsertFeatureengineeringChild(List<FeatureengineeringChildAssembly> list);

	/**
	 * 特征工程子表保存
	 * @param vo
	 * @return
	 */
	Feedback save(FeatureEngineeringAssemblyVo vo);
	
	 /**
     * 
      * @Title: queryByMainTableId
      * @Description: 根据主表id查询附表统计分析明细表数据
      *  @param FeatureengineeringChildAssembly
      *  @return  FeatureengineeringChildAssembly 返回类型
      * @throws
     */
    FeatureengineeringChildAssembly queryByMainTableId(FeatureengineeringChildAssembly record);
    /**
     * 
      * @Title: deleteByMainTableId
      * @Description: 根据主表id删除附表数据
      *  @param FeatureengineeringChildAssembly
      *  @return  int 返回类型
      * @throws
     */
    int  deleteByMainTableId(FeatureengineeringChildAssembly record);
}
