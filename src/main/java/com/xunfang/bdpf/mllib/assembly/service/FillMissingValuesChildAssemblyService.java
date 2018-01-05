package com.xunfang.bdpf.mllib.assembly.service;



import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.vo.FillMissingValuesAssemblyVo;
import com.xunfang.utils.Feedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName FillMissingValuesChildAssemblyService
 * @Description: 组件Service接口服务类
 * Copyright: Copyright (c) 2017
 * Company:深圳市讯方技术股份有限公司
 *
 * @author ylh
 * @date 2017年10月18日
 * @version V1.0
 */
public interface FillMissingValuesChildAssemblyService {
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
    long countByExample(FillMissingValuesChildAssemblyExample example);

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int deleteByExample(FillMissingValuesChildAssemblyExample example);

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
    int insert(FillMissingValuesChildAssembly record);

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
    int insertSelective(FillMissingValuesChildAssembly record);

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<NormalizationAssembly> 返回类型
	  * @throws
	 */
    List<FillMissingValuesChildAssembly> selectByExample(FillMissingValuesChildAssemblyExample example);

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  NormalizationAssembly 返回类型
   	  * @throws
   	 */
	FillMissingValuesChildAssembly selectByPrimaryKey(String id);

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByExampleSelective(@Param("record") FillMissingValuesChildAssembly record, @Param("example") FillMissingValuesChildAssemblyExample example);

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int updateByExample(@Param("record") FillMissingValuesChildAssembly record, @Param("example") FillMissingValuesChildAssemblyExample example);

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKeySelective(FillMissingValuesChildAssembly record);

    /**
   	 * 
   	  * @Title: updateByPrimaryKey
   	  * @Description: 按主键更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int updateByPrimaryKey(FillMissingValuesChildAssembly record);

	/**
	 * 缺失值填充字段设置
	 * @param vo
	 * @return
	 */
	Feedback save(FillMissingValuesAssemblyVo vo);
}
