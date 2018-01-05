package com.xunfang.bdpf.mllib.assembly.service;


import com.xunfang.bdpf.mllib.assembly.entity.AddSerialNumChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.AddSerialNumChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.vo.AddSerialNumAssemblyVo;
import com.xunfang.utils.Feedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName AddSerialNumChildAssemblyService
 * @Description: 组件Service接口服务类
 * Copyright: Copyright (c) 2017
 * Company:深圳市讯方技术股份有限公司
 *
 * @author ylh
 * @date 2017年10月17日
 * @version V1.0
 */
public interface AddSerialNumChildAssemblyService {
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
    long countByExample(AddSerialNumChildAssemblyExample example);

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int deleteByExample(AddSerialNumChildAssemblyExample example);

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
    int insert(AddSerialNumChildAssembly record);

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
    int insertSelective(AddSerialNumChildAssembly record);

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<NormalizationAssembly> 返回类型
	  * @throws
	 */
    List<AddSerialNumChildAssembly> selectByExample(AddSerialNumChildAssemblyExample example);

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  NormalizationAssembly 返回类型
   	  * @throws
   	 */
	AddSerialNumChildAssembly selectByPrimaryKey(String id);

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByExampleSelective(@Param("record") AddSerialNumChildAssembly record, @Param("example") AddSerialNumChildAssemblyExample example);

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int updateByExample(@Param("record") AddSerialNumChildAssembly record, @Param("example") AddSerialNumChildAssemblyExample example);

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKeySelective(AddSerialNumChildAssembly record);

    /**
   	 * 
   	  * @Title: updateByPrimaryKey
   	  * @Description: 按主键更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int updateByPrimaryKey(AddSerialNumChildAssembly record);

	/**
	 * 增加序号列设置
	 * @param vo
	 * @return
	 */
	Feedback save(AddSerialNumAssemblyVo vo);
}
