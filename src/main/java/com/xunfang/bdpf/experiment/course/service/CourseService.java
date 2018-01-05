package com.xunfang.bdpf.experiment.course.service;

import java.util.List;

import com.xunfang.bdpf.experiment.course.entity.Course;
import com.xunfang.bdpf.experiment.course.entity.CourseExample;

/**
 * 
 * @ClassName CourseService
 * @Description:课程资源Service接口服务
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月7日 下午3:07:52
 * @version V1.0
 */
public interface CourseService {

	/**
	 * 
	  * @Title: getCourseCount
	  * @Description: 查询课程资源总数
	  *  @param keywords String 课程资源名称
	  *  @return  long 返回类型
	  * @throws
	 */
	long getCourseCount(String keywords,String createUser);
	
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
	List<Course> queryCourseByPage(String keywords, int skip, int limit,String createUser);


	/**
	 * 
	  * @Title: save
	  * @Description: 课程资源保存
	  *  @param course  Course 课程资源model
	  *  @return  boolean 返回类型
	  * @throws
	 */
	boolean save(Course course);
	
	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据课程资字段查询课程资源
	  *  @param example 课程资源实体对象
	  *  @return  List<Course> 返回类型
	  * @throws
	 */
	List<Course> selectByExample(CourseExample example);

	/**
	 * 
	  * @Title: selectByPrimaryKey
	  * @Description: 根据课程资源主键ID查询课程资源
	  *  @param id String 课程资源主键ID
	  *  @return  Course 返回类型
	  * @throws
	 */
	Course selectByPrimaryKey(String id);

	/**
	 * 
	  * @Title: deleteByExample
	  * @Description: 课程资源删除
	  *  @param example CourseExample 课程资源CourseExample
	  *  @return  boolean 返回类型
	  * @throws
	 */
	boolean deleteByExample(CourseExample example);

	/**
	 * 
	  * @Title: queryMaxXh
	  * @Description: 查询课程资源中最大的课程资源序号
	  *  @return  long 返回类型
	  * @throws
	 */
	long queryMaxXh();
	
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
	long getCourseTypeCount(String id,String keywords,int type);
	
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
	List<Course> queryCourseByTypePage(String id,String keywords,int type, int skip, int limit);
}