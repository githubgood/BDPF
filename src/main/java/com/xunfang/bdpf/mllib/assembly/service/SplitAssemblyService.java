package com.xunfang.bdpf.mllib.assembly.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xunfang.bdpf.mllib.assembly.entity.SplitAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.SplitAssemblyExample;

/**
 * @ClassName SplitAssemblyService
 * @Description: 拆分服务接口类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月13日 下午4:19:15
 * @version V1.0
 */
public interface SplitAssemblyService {
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
    long countByExample(SplitAssemblyExample example);

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int deleteByExample(SplitAssemblyExample example);

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
    int insert(SplitAssembly record);

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
    int insertSelective(SplitAssembly record);

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<SplitAssembly> 返回类型
	  * @throws
	 */
    List<SplitAssembly> selectByExample(SplitAssemblyExample example);

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  SplitAssembly 返回类型
   	  * @throws
   	 */
    SplitAssembly selectByPrimaryKey(String id);

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByExampleSelective(@Param("record") SplitAssembly record, @Param("example") SplitAssemblyExample example);

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int updateByExample(@Param("record") SplitAssembly record, @Param("example") SplitAssemblyExample example);

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKeySelective(SplitAssembly record);

    /**
   	 * 
   	  * @Title: updateByPrimaryKey
   	  * @Description: 按主键更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int updateByPrimaryKey(SplitAssembly record);
}
