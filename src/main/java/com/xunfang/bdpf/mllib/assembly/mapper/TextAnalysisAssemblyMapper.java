package com.xunfang.bdpf.mllib.assembly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xunfang.bdpf.mllib.assembly.entity.TextAnalysisAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.TextAnalysisAssemblyExample;

/**
 * 
 * @ClassName TextAnalysisAssemblyMapper
 * @Description: 文本分析Mapper类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年11月7日 上午10:46:55
 * @version V1.0
 */
public interface TextAnalysisAssemblyMapper {
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
    long countByExample(TextAnalysisAssemblyExample example);

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int deleteByExample(TextAnalysisAssemblyExample example);

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
    int insert(TextAnalysisAssembly record);

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
    int insertSelective(TextAnalysisAssembly record);

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<TextAnalysisAssembly> 返回类型
	  * @throws
	 */
    List<TextAnalysisAssembly> selectByExample(TextAnalysisAssemblyExample example);

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  TextAnalysisAssembly 返回类型
   	  * @throws
   	 */
    TextAnalysisAssembly selectByPrimaryKey(String id);

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByExampleSelective(@Param("record") TextAnalysisAssembly record, @Param("example") TextAnalysisAssemblyExample example);

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int updateByExample(@Param("record") TextAnalysisAssembly record, @Param("example") TextAnalysisAssemblyExample example);

    /**
   	 * 
   	  * @Title: updateByPrimaryKeySelective
   	  * @Description: 按条件更新,带主键且不为null的字段
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int updateByPrimaryKeySelective(TextAnalysisAssembly record);

    /**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKey(TextAnalysisAssembly record);
    
    /**
     * 
      * @Title: batchInsertTextAnalysisAssembly
      * @Description: 批量增加
      *  @param list
      *  @return int 返回类型
      * @throws
     */
	int batchInsertTextAnalysisAssembly(List<TextAnalysisAssembly> list);
}