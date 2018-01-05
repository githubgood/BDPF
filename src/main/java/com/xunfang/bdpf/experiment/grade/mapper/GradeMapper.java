package com.xunfang.bdpf.experiment.grade.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xunfang.bdpf.experiment.grade.entity.Grade;
import com.xunfang.bdpf.experiment.grade.entity.GradeExample;
import com.xunfang.bdpf.experiment.task.entity.Task;
import com.xunfang.bdpf.system.user.entity.User;
/**
 * 
 * @ClassName GradeMapper
 * @Description: 课程成绩mapper层接口
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月23日 上午10:45:43
 * @version V1.0
 */
public interface GradeMapper {
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @param example
	  *  @return  long 返回类型
	  * @throws
	 */
    long countByExample(GradeExample example);

    /**
     * 
      * @Title: deleteByExample
      * @Description: 根据条件删除多条数据
      *  @param example
      *  @return  int 返回类型
      * @throws
     */
    int deleteByExample(GradeExample example);

    /**
     * 
      * @Title: deleteByPrimaryKey
      * @Description: 根据条件删除单条数据
      *  @param id
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
    int insert(Grade record);

    /**
     * 
      * @Title: insertSelective
      * @Description: 插入数据,不为null的字段
      *  @param record
      *  @return  int 返回类型
      * @throws
     */
    int insertSelective(Grade record);

    /**
     * 
      * @Title: selectByExample
      * @Description: 根据条件查询数据
      *  @param example
      *  @return  List<Grade> 返回类型
      * @throws
     */
    List<Grade> selectByExample(GradeExample example);

    /**
     * 
      * @Title: selectByPrimaryKey
      * @Description: 根据主键查询数据
      *  @param id
      *  @return  Grade 返回类型
      * @throws
     */
    Grade selectByPrimaryKey(String id);

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
      * @Title: updateByExample
      * @Description: 按条件更新
      *  @param record
      *  @param example
      *  @return  int 返回类型
      * @throws
     */
    int updateByExample(@Param("record") Grade record, @Param("example") GradeExample example);

    /**
     * 
      * @Title: updateByPrimaryKeySelective
      * @Description: 按条件更新,带主键且不为null的字段
      *  @param record
      *  @return  int 返回类型
      * @throws
     */
    int updateByPrimaryKeySelective(Grade record);

    /**
     * 
      * @Title: updateByPrimaryKey
      * @Description: 按主键更新
      *  @param record
      *  @return  int 返回类型
      * @throws
     */
    int updateByPrimaryKey(Grade record);
    
    /**
     * 
      * @Title: batchInsert
      * @Description: 批量插入
      *  @param gradeList
      *  @return  int 返回类型
      * @throws
     */
	int batchInsert(List<Grade> gradeList);
	
    /**
     * 
      * @Title: queryGradeByPage
      * @Description: 课程成绩的查询
      *  @param param
      *  @return  List<Grade> 返回类型
      * @throws
     */
	List<Grade> queryGradeByPage(Map<String, Object> param);

	/**
	 * 
	  * @Title: getGradeCount
	  * @Description: 查询课程成绩总数
	  *  @param param
	  *  @return  List<Grade> 返回类型
	  * @throws
	 */
	List<Grade> getGradeCount(Map<String, Object> param);

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
	List<Grade> getDetailCount(Map<String, Object> param);
    
	/**
	 * 
	  * @Title: queryDetailByPage
	  * @Description: 查询成绩详情
	  *  @param param
	  *  @return  List<Grade> 返回类型
	  * @throws
	 */
	List<Grade> queryDetailByPage(Map<String, Object> param);

	/**
	 * 
	  * @Title: getCheckCount
	  * @Description: 查询批改实验
	  *  @param taskId
	  *  @param classId
	  *  @param title
	  *  @return  List<Grade> 返回类型
	  * @throws
	 */
	List<Grade> getCheckCount(Map<String, Object> param);

	/**
	 * 
	  * @Title: queryCheckByPage
	  * @Description: 分页查询批改实验
	  *  @param taskId
	  *  @param classId
	  *  @param title
	  *  @param skip
	  *  @param max
	  *  @return  List<Grade> 返回类型
	  * @throws
	 */
	List<Grade> queryCheckByPage(Map<String, Object> param);

	/**
	 * 
	  * @Title: getPersonListCount
	  * @Description: 单人实验不分页查询
	  *  @param param
	  *  @return  List<Task> 返回类型
	  * @throws
	 */
	List<Task> getPersonListCount(Map<String, Object> param);

	/**
	 * 
	  * @Title: queryPersonListByPage
	  * @Description: 单人实验查询
	  *  @param param
	  *  @return  List<Grade> 返回类型
	  * @throws
	 */
	List<Grade> queryPersonListByPage(Map<String, Object> param);
	
	/**
	 * 
	  * @Title: getExpListCount
	  * @Description: 获取课程实验任务总数
	  * @param @return    设定文件
	  * @return long    返回类型
	  * @throws
	 */
	long getExpListCount(Map<String, Object> param);
	
	/**
	 * 
	  * @Title: queryExpListByPage
	  * @Description: 分页查询 课程实验任务列表
	  * @param @param limit
	  * @param @return    设定文件
	  * @return List<Course>    返回类型
	  * @throws
	 */
	List<Grade> queryExpListByPage(Map<String, Object> param);

	/**
	 * 
	  * @Title: getGroupListCount
	  * @Description: 查询分组学生
	  *  @param param
	  *  @return  List<Grade> 返回类型
	  * @throws
	 */
	List<Grade> getGroupListCount(Map<String, Object> param);

	/**
	 * 
	  * @Title: queryGroupListByPage
	  * @Description: 查询分组学生
	  *  @param param
	  *  @return  List<Grade> 返回类型
	  * @throws
	 */
	List<Grade> queryGroupListByPage(Map<String, Object> param);
	
	/**
     * 
      * @Title: selectExpById
      * @Description: 根据主键查询数据
      *  @param id
      *  @return  Grade 返回类型
      * @throws
     */
    Grade selectExpById(@Param("id") String id);
    /**
     * 
      * @Title: selectByExampleGroupName
      * @Description: 根据任务id，班级id,组id查询数据
      * @param @param param
      * @param @return    设定文件
      * @return List<User>    返回类型
      * @throws
     */
    List<User> selectByExampleGroupName(Map<String, Object> param);

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
	List<Grade> queryListDetail(Map<String, Object> param);

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
	List<Task> queryListCount(Map<String, Object> param);
	
}