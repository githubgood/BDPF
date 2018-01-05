package com.xunfang.bdpf.experiment.course.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xunfang.bdpf.experiment.course.entity.Course;
import com.xunfang.bdpf.experiment.course.entity.CourseExample;
import com.xunfang.bdpf.experiment.course.mapper.CourseMapper;
import com.xunfang.bdpf.experiment.course.service.CourseService;
import com.xunfang.utils.Identities;

/**
 * @ClassName CourseServiceImpl
 * @Description:课程资源Service接口实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月7日 下午3:09:56
 * @version V1.0
 */
@Service
@Transactional
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	//课程资源Mapper映射接口类
	private CourseMapper courseMapper;

	/**
	 * 
	  * @Title: getCourseCount
	  * @Description: 查询课程资源总数
	  *  @param keywords String 课程资源名称
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long getCourseCount(String keywords,String createUser) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keywords", keywords);
		param.put("createUser", createUser);
		return courseMapper.getCourseCount(param);
	}

	/**
	 * 
	  * @Title: queryCourseByPage
	  * @Description: 课程资源查询列表，带分页
	  *  @param keywords String 课程资源名称
	  *  @param skip int 从第几条开始取数据
	  *  @param limit int 每页显示多少条数据
	  *  @return  List<Course> 返回类型
	  * @throws
	 */
	@Override
	public List<Course> queryCourseByPage(String keywords, int skip, int limit,String createUser) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keywords", keywords);
		param.put("createUser", createUser);
		param.put("skip", skip);
		param.put("limit", limit);
		return courseMapper.queryCourseByPage(param);
	}

	/**
	 * 
	  * @Title: save
	  * @Description: 课程资源保存
	  *  @param course  Course 课程资源model
	  *  @return  boolean 返回类型
	  * @throws
	 */
	@Override
	public boolean save(Course course) {
		boolean flag = true;
		if (StringUtils.isBlank(course.getId())) {
			course.setId(Identities.uuid2());
			flag = courseMapper.insert(course) > 0 ? true : false;
		} else {
			flag = courseMapper.updateByPrimaryKeySelective(course) > 0 ? true : false;
		}
		return flag;
	}

	/**
	 * 
	  * @Title: selectByPrimaryKey
	  * @Description: 根据课程资源主键ID查询课程资源
	  *  @param id String 课程资源主键ID
	  *  @return  Course 返回类型
	  * @throws
	 */
	@Override
	public boolean deleteByExample(CourseExample example) {
		return  courseMapper.deleteByExample(example) > 0 ? true : false;
	}

	/**
	 * 
	  * @Title: deleteByExample
	  * @Description: 课程资源删除
	  *  @param example CourseExample 课程资源CourseExample
	  *  @return  boolean 返回类型
	  * @throws
	 */
	@Override
	public Course selectByPrimaryKey(String param) {
		return courseMapper.selectByPrimaryKey(param);
	}

	/**
	 * 
	  * @Title: queryMaxXh
	  * @Description: 查询课程资源中最大的课程资源序号
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long queryMaxXh() {
		return courseMapper.queryMaxXh();
	}

	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据课程资字段查询课程资源
	  *  @param example 课程资源实体对象
	  *  @return  List<Course> 返回类型
	  * @throws
	 */
	@Override
	public List<Course> selectByExample(CourseExample example) {
		return courseMapper.selectByExample(example);
	}

	/**
	 * 
	  * @Title: getCourseTypeCount
	  * @Description: 获取 资料列表 根据id和类型
	  * @param @param id
	  * @param @param keywords
	  * @param @param type
	  * @param @return    设定文件
	  * @return long    返回类型
	  * @throws
	 */
	@Override
	public long getCourseTypeCount(String id,String keywords, int type) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("keywords", keywords);
		param.put("type", type);
		return courseMapper.getCourseTypeCount(param);
	}

	/**
	 * 
	  * @Title: queryCourseByTypePage
	  * @Description: 分页获取 资料列表 根据id和类型
	  * @param @param id
	  * @param @param keywords
	  * @param @param type
	  * @param @param skip
	  * @param @param limit
	  * @param @return    设定文件
	  * @return List<Course>    返回类型
	  * @throws
	 */
	@Override
	public List<Course> queryCourseByTypePage(String id,String keywords, int type, int skip, int limit) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("keywords", keywords);
		param.put("type", type);
		param.put("skip", skip);
		param.put("limit", limit);
		return courseMapper.queryCourseByTypePage(param);
	}
}
