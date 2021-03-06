package com.xunfang.bdpf.mllib.assembly.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xunfang.bdpf.mllib.assembly.entity.ConvertAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.ConvertAssemblyExample;

/**
 * 
 * @ClassName ConvertAssemblyMapper
 * @Description: 类型转换Mapper类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月19日 下午3:01:40
 * @version V1.0
 */
public interface ConvertAssemblyMapper {
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
    long countByExample(ConvertAssemblyExample example);

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int deleteByExample(ConvertAssemblyExample example);

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
    int insert(ConvertAssembly record);

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
    int insertSelective(ConvertAssembly record);

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<ConvertAssembly> 返回类型
	  * @throws
	 */
    List<ConvertAssembly> selectByExample(ConvertAssemblyExample example);

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  ConvertAssembly 返回类型
   	  * @throws
   	 */
    ConvertAssembly selectByPrimaryKey(String id);

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByExampleSelective(@Param("record") ConvertAssembly record, @Param("example") ConvertAssemblyExample example);

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int updateByExample(@Param("record") ConvertAssembly record, @Param("example") ConvertAssemblyExample example);

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKeySelective(ConvertAssembly record);

    /**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKey(ConvertAssembly record);
    
    /**
   	 * 
   	  * @Title: queryConvertAssembly
   	  * @Description: 根据主键ID查询数据
   	  *  @return  List<ConvertAssembly> 返回类型
   	  * @throws
   	 */
    List<ConvertAssembly> queryConvertAssembly(Map<String, Object> param);
    
    /**
     * 
      * @Title: batchInsertConvertAssembly
      * @Description: 批量增加字段
      *  @param list
      *  @return int 返回类型
      * @throws
     */
	int batchInsertConvertAssembly(List<ConvertAssembly> list);
}