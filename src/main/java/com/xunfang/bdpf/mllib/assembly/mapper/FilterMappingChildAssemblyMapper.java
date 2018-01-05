package com.xunfang.bdpf.mllib.assembly.mapper;

import com.xunfang.bdpf.mllib.assembly.entity.FilterMappingChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FilterMappingChildAssemblyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @ClassName FilterMappingChildAssemblyMapper
 * @Description: 过滤与映射子表Mapper类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月30日 上午10:48:58
 * @version V1.0
 */
public interface FilterMappingChildAssemblyMapper {
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
    long countByExample(FilterMappingChildAssemblyExample example);

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int deleteByExample(FilterMappingChildAssemblyExample example);

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
    int insert(FilterMappingChildAssembly record);

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
    int insertSelective(FilterMappingChildAssembly record);

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<FilterMappingChildAssembly> 返回类型
	  * @throws
	 */
    List<FilterMappingChildAssembly> selectByExample(FilterMappingChildAssemblyExample example);

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  FilterMappingChildAssembly 返回类型
   	  * @throws
   	 */
    FilterMappingChildAssembly selectByPrimaryKey(String id);

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByExampleSelective(@Param("record") FilterMappingChildAssembly record, @Param("example") FilterMappingChildAssemblyExample example);

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int updateByExample(@Param("record") FilterMappingChildAssembly record, @Param("example") FilterMappingChildAssemblyExample example);

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKeySelective(FilterMappingChildAssembly record);

    /**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKey(FilterMappingChildAssembly record);
    
    /**
     * 
      * @Title: batchInsertFilterMappingChildAssembly
      * @Description: 批量插入过滤与映射明细表数据
      *  @param list
      *  @return  int 返回类型
      * @throws
     */
    int batchInsertFilterMappingChildAssembly(List<FilterMappingChildAssembly> list);
    
    /**
	 * 
	  * @Title: queryFilterMappingChildAssembly
	  * @Description: 根据条件查询数据
	  *  @return  List<FilterMappingChildAssembly> 返回类型
	  * @throws
	 */
    List<FilterMappingChildAssembly> queryFilterMappingChildAssembly(String id);
}