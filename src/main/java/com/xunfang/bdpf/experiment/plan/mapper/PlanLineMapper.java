package com.xunfang.bdpf.experiment.plan.mapper;

import com.xunfang.bdpf.experiment.plan.entity.PlanLine;
import com.xunfang.bdpf.experiment.plan.entity.PlanLineExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
/**
 * 
 * @ClassName PlanLineMapper
 * @Description:课程教学计划子表Mapper映射接口类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月9日 下午5:37:51
 * @version V1.0
 */
public interface PlanLineMapper {
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
    long countByExample(PlanLineExample example);

    /**
	 * 
	  * @Title: deleteByExample
	  * @Description: 根据条件删除多条数据
	  *  @return  int 返回类型
	  * @throws
	 */
    int deleteByExample(PlanLineExample example);

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
    int insert(PlanLine record);

    /**
	 * 
	  * @Title: insertSelective
	  * @Description: 插入数据,不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int insertSelective(PlanLine record);

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<PlanLine> 返回类型
	  * @throws
	 */
    List<PlanLine> selectByExample(PlanLineExample example);

    /**
	 * 
	  * @Title: selectByPrimaryKey
	  * @Description: 根据主键查询数据
	  *  @return  PlanLine 返回类型
	  * @throws
	 */
    PlanLine selectByPrimaryKey(String id);

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByExampleSelective(@Param("record") PlanLine record, @Param("example") PlanLineExample example);

    /**
	 * 
	  * @Title: updateByExample
	  * @Description: 按条件更新
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByExample(@Param("record") PlanLine record, @Param("example") PlanLineExample example);

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKeySelective(PlanLine record);

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKey(PlanLine record);

    /**
     * 
      * @Title: batchInsert
      * @Description: 批量增加课计划子表
      *  @param list
      *  @return  int 返回类型
      * @throws
     */
	int batchInsert(List<PlanLine> list);
	
	/**
	 * 
	  * @Title: queryPlanLineById
	  * @Description: 联合查询课程资源与教学计划信息
	  *  @param param
	  *  @return  List<PlanLine> 返回类型
	  * @throws
	 */ 
	List<PlanLine> queryPlanLineById(Map<String, Object> param);
}