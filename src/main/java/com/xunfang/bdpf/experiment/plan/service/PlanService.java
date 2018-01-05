package com.xunfang.bdpf.experiment.plan.service;

import java.util.List;
import java.util.Map;
import com.xunfang.bdpf.experiment.plan.entity.Plan;
import com.xunfang.bdpf.experiment.plan.entity.PlanExample;
import com.xunfang.bdpf.experiment.plan.entity.PlanLine;
import com.xunfang.bdpf.experiment.plan.entity.PlanLineExample;
/**
 * 
 * @ClassName PlanService
 * @Description: 课程教学计划Service接口服务
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月9日 下午5:16:05
 * @version V1.0
 */
public interface PlanService {

	/**
	 * 
	  * @Title: getPlanCount
	  * @Description: 课程教学计划总数
	  *  @param keywords String 课程教学计划名称
	  *  @return  long 返回类型
	  * @throws
	 */
	long getPlanCount(String keywords,String createUser);

	/**
	 * 
	  * @Title: queryPlanByPage
	  * @Description: 课程教学计划查询列表，带分页
	  *  @param keywords String 课程教学计划名称
	  *  @param skip int 从第几条开始取数据
	  *  @param limit int 每页显示多少条数据
	  *  @return  List<Course> 返回类型
	  * @throws
	 */
	List<Plan> queryPlanByPage(String keywords, int skip, int limit,String createUser);

	/**
	 * 
	  * @Title: save
	  * @Description: 课程教学计划保存
	  *  @param plan  Plan 课程教学计划model
	  *  @return  boolean 返回类型
	  * @throws
	 */
	boolean save(Plan plan, String courseId);

	/**
	 * 
	  * @Title: deleteByExample
	  * @Description: 课程教学计划删除
	  *  @param example CourseExample 课程资源CourseExample 课程计划
	  *  @return  boolean 返回类型
	  * @throws
	 */
	boolean deleteByExample(PlanExample plan, PlanLineExample planLine);

	/**
	 * 
	  * @Title: selectByPrimaryKey
	  * @Description: 根据课课程教学计划主键ID查询课程教学计划
	  *  @param id String 课程教学计划主键ID
	  *  @return  Course 返回类型
	  * @throws
	 */
	Plan selectByPrimaryKey(String id);

	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据课程资字段查询课程资源
	  *  @param example 课程资源实体对象
	  *  @return  List<Course> 返回类型
	  * @throws
	 */
	List<Plan> selectByExample(PlanExample example);
	
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
