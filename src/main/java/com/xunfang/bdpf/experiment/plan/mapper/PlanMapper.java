package com.xunfang.bdpf.experiment.plan.mapper;

import com.xunfang.bdpf.experiment.plan.entity.Plan;
import com.xunfang.bdpf.experiment.plan.entity.PlanExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
/**
 * 
 * @ClassName PlanMapper
 * @Description: 课程教学计划Mapper映射接口类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月9日 下午5:30:08
 * @version V1.0
 */
public interface PlanMapper {
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	long countByExample(PlanExample example);
	
    /**
	 * 
	  * @Title: deleteByExample
	  * @Description: 根据条件删除多条数据
	  *  @return  int 返回类型
	  * @throws
	 */
	int deleteByExample(PlanExample example);

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
	int insert(Plan record);

    /**
	 * 
	  * @Title: insertSelective
	  * @Description: 插入数据,不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	int insertSelective(Plan record);

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<Plan> 返回类型
	  * @throws
	 */
	List<Plan> selectByExample(PlanExample example);

    /**
	 * 
	  * @Title: selectByPrimaryKey
	  * @Description: 根据主键查询数据
	  *  @return  Plan 返回类型
	  * @throws
	 */
	Plan selectByPrimaryKey(String id);

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	int updateByExampleSelective(@Param("record") Plan record, @Param("example") PlanExample example);

    /**
	 * 
	  * @Title: updateByExample
	  * @Description: 按条件更新
	  *  @return  int 返回类型
	  * @throws
	 */
	int updateByExample(@Param("record") Plan record, @Param("example") PlanExample example);

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	int updateByPrimaryKeySelective(Plan record);

    /**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
	int updateByPrimaryKey(Plan record);

    /**
	 * 
	  * @Title: getPlanCount
	  * @Description: 查询课程教学计划总数
	  *  @param keywords String 课程教学计划名称
	  *  @return  long 返回类型
	  * @throws
	 */
	long getPlanCount(Map<String, Object> param);

	/**
	 * 
	  * @Title: queryPlanByPage
	  * @Description: 课程教学计划查询列表，带分页
	  *  @param keywords String 课程资源名称
	  *  @param skip int 从第几条开始取数据
	  *  @param limit int 每页显示多少条数据
	  *  @return  List<Plan> 返回类型
	  * @throws
	 */
	List<Plan> queryPlanByPage(Map<String, Object> param);
}