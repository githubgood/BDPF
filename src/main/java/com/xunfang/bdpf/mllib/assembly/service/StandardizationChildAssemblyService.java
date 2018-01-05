package com.xunfang.bdpf.mllib.assembly.service;

import com.xunfang.bdpf.mllib.assembly.entity.StandardizationChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.StandardizationChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.vo.StandardizationAssemblyVo;
import com.xunfang.utils.Feedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName StandardizationChildAssemblyService
 * @Description: 组件Service接口服务类
 * Copyright: Copyright (c) 2017
 * Company:深圳市讯方技术股份有限公司
 *
 * @author ylh
 * @date 2017年10月17日
 * @version V1.0
 */
public interface StandardizationChildAssemblyService {
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
    long countByExample(StandardizationChildAssemblyExample example);

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int deleteByExample(StandardizationChildAssemblyExample example);

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
    int insert(StandardizationChildAssembly record);

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
    int insertSelective(StandardizationChildAssembly record);

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<NormalizationAssembly> 返回类型
	  * @throws
	 */
    List<StandardizationChildAssembly> selectByExample(StandardizationChildAssemblyExample example);

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  NormalizationAssembly 返回类型
   	  * @throws
   	 */
	StandardizationChildAssembly selectByPrimaryKey(String id);

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByExampleSelective(@Param("record") StandardizationChildAssembly record, @Param("example") StandardizationChildAssemblyExample example);

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int updateByExample(@Param("record") StandardizationChildAssembly record, @Param("example") StandardizationChildAssemblyExample example);

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKeySelective(StandardizationChildAssembly record);

    /**
   	 * 
   	  * @Title: updateByPrimaryKey
   	  * @Description: 按主键更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int updateByPrimaryKey(StandardizationChildAssembly record);

	/**
	 * 标准化子表字段信息设置
	 * @param vo
	 * @return
	 */
    Feedback save(StandardizationAssemblyVo vo);
}
