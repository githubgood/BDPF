package com.xunfang.bdpf.mllib.assembly.service;


import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesAssemblyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName FillMissingValuesAssemblyService
 * @Description: 组件Service接口服务类
 * Copyright: Copyright (c) 2017
 * Company:深圳市讯方技术股份有限公司
 *
 * @author ylh
 * @date 2017年10月18日
 * @version V1.0
 */
public interface FillMissingValuesAssemblyService {
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
    long countByExample(FillMissingValuesAssemblyExample example);

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int deleteByExample(FillMissingValuesAssemblyExample example);

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
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
    int insert(FillMissingValuesAssembly record);

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
    int insertSelective(FillMissingValuesAssembly record);

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<NormalizationAssembly> 返回类型
	  * @throws
	 */
    List<FillMissingValuesAssembly> selectByExample(FillMissingValuesAssemblyExample example);

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  NormalizationAssembly 返回类型
   	  * @throws
   	 */
	FillMissingValuesAssembly selectByPrimaryKey(String id);

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByExampleSelective(@Param("record") FillMissingValuesAssembly record, @Param("example") FillMissingValuesAssemblyExample example);

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int updateByExample(@Param("record") FillMissingValuesAssembly record, @Param("example") FillMissingValuesAssemblyExample example);

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKeySelective(FillMissingValuesAssembly record);

    /**
   	 * 
   	  * @Title: updateByPrimaryKey
   	  * @Description: 按主键更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int updateByPrimaryKey(FillMissingValuesAssembly record);
}
