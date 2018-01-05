package com.xunfang.bdpf.experiment.plan.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunfang.bdpf.experiment.course.entity.Course;
import com.xunfang.bdpf.experiment.course.entity.CourseExample;
import com.xunfang.bdpf.experiment.plan.entity.Plan;
import com.xunfang.bdpf.experiment.plan.entity.PlanExample;
import com.xunfang.bdpf.experiment.plan.entity.PlanLine;
import com.xunfang.bdpf.experiment.plan.entity.PlanLineExample;
import com.xunfang.bdpf.experiment.plan.mapper.PlanLineMapper;
import com.xunfang.bdpf.experiment.plan.mapper.PlanMapper;
import com.xunfang.bdpf.experiment.plan.service.PlanService;
import com.xunfang.utils.Identities;

/**
 * 
 * @ClassName PlanServiceImpl
 * @Description: 课程教学计划Service接口实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月9日 下午5:23:04
 * @version V1.0
 */
@Service
@Transactional
public class PlanServiceImpl implements PlanService {
	@Autowired
	//课程教学计划Mapper映射接口类
	private PlanMapper planMapper;

	@Autowired
	//课程教学计划子表Mapper映射接口类
	private PlanLineMapper planLineMapper;

	/**
	 * 
	  * @Title: getPlanCount
	  * @Description: 查询课程教学计划总数
	  *  @param keywords String 课程教学计划名称
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long getPlanCount(String keywords,String createUser) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keywords", keywords);
		param.put("createUser", createUser);
		return planMapper.getPlanCount(param);
	}

	/**
	 * 
	  * @Title: queryPlanByPage
	  * @Description: 课程教学计划查询列表，带分页
	  *  @param keywords String 课程教学计划名称
	  *  @param skip int 从第几条开始取数据
	  *  @param limit int 每页显示多少条数据
	  *  @return  List<Plan> 返回类型
	  * @throws
	 */
	@Override
	public List<Plan> queryPlanByPage(String keywords, int skip, int limit,String createUser) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keywords", keywords);
		param.put("createUser", createUser);
		param.put("skip", skip);
		param.put("limit", limit);
		return planMapper.queryPlanByPage(param);
	}

	/**
	 * 
	  * @Title: save
	  * @Description: 课程教学计划保存
	  *  @param plan  Plan 课程教学计划model
	  *  @return  boolean 返回类型
	  * @throws
	 */
	@Override
	public boolean save(Plan plan, String courseId) {
		boolean flag = true;
		if (StringUtils.isBlank(plan.getId())) {
			plan.setId(Identities.uuid2());
			flag = planMapper.insert(plan) > 0 ? true : false;
			String arr[] = courseId.split(",");
			if (arr.length > 0) {
				List<PlanLine> list = new ArrayList<PlanLine>();
				PlanLine p = null;
				for (int i = 0; i < arr.length; i++) {
					p = new PlanLine();
					p.setTeachingPlanId(plan.getId());
					p.setId(Identities.uuid2());
					p.setCourseResourceId(arr[i]);
					list.add(p);
				}
				flag = planLineMapper.batchInsert(list) > 0 ? true : false;
			}
		} else {
			flag = planMapper.updateByPrimaryKeySelective(plan) > 0 ? true : false;
			// 删除line子表 再添加
			PlanLineExample planLine = new PlanLineExample();
			planLine.createCriteria().andTeachingPlanIdIn(Arrays.asList(plan.getId()));
			flag = planLineMapper.deleteByExample(planLine) > 0 ? true : false;
			String arr[] = courseId.split(",");
			if (arr.length > 0) {
				List<PlanLine> list = new ArrayList<PlanLine>();
				PlanLine p = null;
				for (int i = 0; i < arr.length; i++) {
					p = new PlanLine();
					p.setTeachingPlanId(plan.getId());
					p.setId(Identities.uuid2());
					p.setCourseResourceId(arr[i]);
					list.add(p);
				}
				flag = planLineMapper.batchInsert(list) > 0 ? true : false;
			}
		}
		return flag;
	}

	/**
	 * 
	  * @Title: deleteByExample
	  * @Description: 课程教学计划删除
	  *  @param example CourseExample 课程资源CourseExample课程教学计划
	  *  @return  boolean 返回类型
	  * @throws
	 */
	@Override
	public boolean deleteByExample(PlanExample example, PlanLineExample planLine) {
		boolean flag = true;
		flag = planMapper.deleteByExample(example) > 0 ? true : false;
		flag = planLineMapper.deleteByExample(planLine) > 0 ? true : false;
		return flag;
	}

	/**
	 * 
	  * @Title: selectByPrimaryKey
	  * @Description: 根据课程教学计划主键ID查询课程资源
	  *  @param id String 课程资源主键ID
	  *  @return  Course 返回类型
	  * @throws
	 */
	@Override
	public Plan selectByPrimaryKey(String param) {
		return planMapper.selectByPrimaryKey(param);
	}

	   /**
	    * 
	     * @Title: queryPlanLineById
	     * @Description: 联合查询课程资源与教学计划信息
	     *  @param param
	     *  @return  List<Plan> 返回类型
	     * @throws
	    */
	@Override
	public List<PlanLine> queryPlanLineById(Map<String, Object> param) {
		return planLineMapper.queryPlanLineById(param);
	}
	
	   /**
	    * 
	     * @Title: queryPlanLineById
	     * @Description: 联合查询课程资源与教学计划信息
	     *  @param param
	     *  @return  List<PlanLine> 返回类型
	     * @throws
	    */	
	@Override
	public List<Plan> selectByExample(PlanExample example) {
		return planMapper.selectByExample(example);
	}
}
