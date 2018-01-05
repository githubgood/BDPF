package com.xunfang.bdpf.experiment.grade.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xunfang.bdpf.experiment.grade.entity.Grade;
import com.xunfang.bdpf.experiment.grade.entity.GradeExample;
import com.xunfang.bdpf.experiment.task.entity.Task;
import com.xunfang.bdpf.system.user.entity.User;

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
public interface GradeService {

	/**
	 * 
	  * @Title: getGradeCount
	  * @Description: 查询课程资源总数
	  *  @param keywords String 课程资源名称
	  *  @return  long 返回类型
	  * @throws
	 */
	List<Grade> getGradeCount(String taskId,String classId,String studentId,String teacherId);
	
	/**
	 * 
	  * @Title: queryGradeByPage
	  * @Description: 课程资源查询列表，带分页
	  *  @param taskId String 任务id
	  *  @param classId String 班级id
	  *  @param skip int 从第几条开始取数据
	  *  @param limit int 每页显示多少条数据
	  *  @return  List<Course> 返回类型
	  * @throws
	 */
	List<Grade> queryGradeByPage(String taskId,String classId,String studentId,String teacherId, int skip, int limit);

	
	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据课程资字段查询课程资源
	  *  @param example 课程资源实体对象
	  *  @return  List<Course> 返回类型
	  * @throws
	 */
	List<Grade> selectByExample(GradeExample example);

	/**
	 * 
	  * @Title: selectByPrimaryKey
	  * @Description: 根据课程资源主键ID查询课程资源
	  *  @param id String 课程资源主键ID
	  *  @return  Course 返回类型
	  * @throws
	 */
	Grade selectByPrimaryKey(String id);

	/**
	 * 
	  * @Title: deleteByExample
	  * @Description: 课程资源删除
	  *  @param example CourseExample 课程资源CourseExample
	  *  @return  boolean 返回类型
	  * @throws
	 */
	boolean deleteByExample(GradeExample example);
	
	/**
     * 
      * @Title: updateByExampleSelective
      * @Description: 按条件更新值不为null的字段
      *  @param record
      *  @param example
      *  @return  int 返回类型
      * @throws
     */
    int updateByExampleSelective(@Param("record") Grade record, @Param("example") GradeExample example);

	/**
	 * 
	  * @Title: getDetailCount
	  * @Description: 查询成绩详情
	  *  @param taskId
	  *  @param classId
	  *  @param studentId
	  *  @return  List<Grade> 返回类型
	  * @throws
	 */
	List<Grade> getDetailCount(String taskId, String classId, String studentId);

	/**
	 * 
	  * @Title: queryDetailByPage
	  * @Description: 查询成绩详情
	  *  @param taskId
	  *  @param classId
	  *  @param studentId
	  *  @param skip
	  *  @param max
	  *  @return  List<Grade> 返回类型
	  * @throws
	 */
	List<Grade> queryDetailByPage(String taskId, String classId, String studentId, int skip, int max);
    

	/**
	 * 
	  * @Title: getCheckCount
	  * @Description: 查询批改实验
	  *  @param taskId
	  *  @param classId
	  *  @param title
	  *  @param teacherId
	  *  @return  List<Grade> 返回类型
	  * @throws
	 */
	List<Grade> getCheckCount(String taskId, String classId, String title,String teacherId);
    
	/**
	 * 
	  * @Title: queryCheckByPage
	  * @Description: 分页查询批改实验
	  *  @param taskId
	  *  @param classId
	  *  @param title
	  *  @param teacherId
	  *  @param skip
	  *  @param max
	  *  @return  List<Grade> 返回类型
	  * @throws
	 */
	List<Grade> queryCheckByPage(String taskId, String classId, String title,String teacherId, int skip, int max);

	/**
	 * 
	  * @Title: getPersonListCount
	  * @Description: 不分页单人实验查询
	  *  @param taskId
	  *  @param courseResourceId
	  *  @param classId
	  *  @param teacherId
	  *  @param taskStatus
	  *  @return  List<Task> 返回类型
	  * @throws
	 */
	List<Task> getPersonListCount(String taskId, String courseResourceId, String classId,String taskStatus,String teacherId);

	/**
	 * 
	  * @Title: queryPersonListByPage
	  * @Description: 分页单人实验查询
	  *  @param taskId
	  *  @param courseResourceId
	  *  @param classId
	  *  @param taskStatus
	  *  @param teacherId
	  *  @param skip
	  *  @param max
	  *  @return  List<Grade> 返回类型
	  * @throws
	 */
	List<Grade> queryPersonListByPage(String taskId, String courseResourceId, String classId,String taskStatus,String teacherId, int skip, int max);
	
	/**
	 * 
	  * @Title: save
	  * @Description: 数据保存
	  *  @param grade
	  *  @return  boolean 返回类型
	  * @throws
	 */
	boolean save(Grade grade);

	/**
	 * 
	  * @Title: getExpListCount
	  * @Description: 获取课程实验任务总数
	  * @param @return    设定文件
	  * @return long    返回类型
	  * @throws
	 */
	long getExpListCount(String taskId,String classId,String studentId,String keywords, int skip, int limit);
	
	/**
	 * 
	  * @Title: queryExpListByPage
	  * @Description: 分页查询 课程实验任务列表
	  * @param @param limit
	  * @param @return    设定文件
	  * @return List<Course>    返回类型
	  * @throws
	 */
	List<Grade> queryExpListByPage(String taskId,String classId,String studentId,String keywords, int skip, int limit);

	/**
	 * 
	  * @Title: queryGroupListByPage
	  * @Description: 分页查询分组学生
	  *  @param taskId
	  *  @param courseResourceId
	  *  @param classId
	  *  @param taskStatus
	  *  @param teacherId
	  *  @param skip
	  *  @param max
	  *  @return  List<Grade> 返回类型
	  * @throws
	 */
	List<Grade> queryGroupListByPage(String taskId, String courseResourceId, String classId, String taskStatus,
			String teacherId, int skip, int max);

	/**
	 * 
	  * @Title: getGroupListCount
	  * @Description: 不分页查询分组
	  *  @param taskId
	  *  @param courseResourceId
	  *  @param classId
	  *  @param taskStatus
	  *  @param teacherId
	  *  @return  List<Grade> 返回类型
	  * @throws
	 */
	List<Grade> getGroupListCount(String taskId, String courseResourceId, String classId, String taskStatus,
			String teacherId);
	
    /**
     * 
      * @Title: groupGrade
      * @Description: 分组数据类型保存
      *  @param grade
      *  @return  boolean 返回类型
      * @throws
     */
	boolean groupGrade(Grade grade);
	
	/**
     * 
      * @Title: selectExpById
      * @Description: 根据主键查询数据
      *  @param id
      *  @return  Grade 返回类型
      * @throws
     */
    Grade selectExpById(String id);
	
    /**
     * 
      * @Title: selectByExampleGroupName
      * @Description: 根据任务id，班级id,组id查询数据
      * @param @param task_id   任务id
      * @param @param class_id  班级id
      * @param @param group_id  组id
      * @param @return    设定文件
      * @return List<User>    返回类型
      * @throws
     */
    List<User> selectByExampleGroupName(@Param("task_id") String task_id,@Param("class_id") String class_id,@Param("group_id") String group_id,@Param("type") int type,@Param("account")String account);

    /**
     * 
      * @Title: queryListDetail
      * @Description: 分页查询课程批改
      *  @param taskId
      *  @param classId
      *  @param courseResourceId
      *  @param teacherId
      *  @param skip
      *  @param max
      *  @return  List<Grade> 返回类型
      * @throws
     */
	List<Grade> queryListDetail(String taskId, String classId, String courseResourceId, String teacherId, int skip,
			int max);

	/**
	 * 
	  * @Title: queryListCount
	  * @Description: 不分页查询课程批改
	  *  @param taskId
	  *  @param classId
	  *  @param courseResourceId
	  *  @param teacherId
	  *  @return  List<Task> 返回类型
	  * @throws
	 */
	List<Task> queryListCount(String taskId, String classId, String courseResourceId, String teacherId);
	
}