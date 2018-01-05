package com.xunfang.bdpf.experiment.task.mapper;

import com.xunfang.bdpf.experiment.task.entity.Task;
import com.xunfang.bdpf.experiment.task.entity.TaskExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
/**
 * 
 * @ClassName TaskMapper
 * @Description: 课程人发布Mapper接口
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月23日 上午10:40:35
 * @version V1.0
 */
public interface TaskMapper {
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @param example
	  *  @return  long 返回类型
	  * @throws
	 */
    long countByExample(TaskExample example);

    /**
     * 
      * @Title: deleteByExample
      * @Description: 根据条件删除多条数据
      *  @param example
      *  @return  int 返回类型
      * @throws
     */
    int deleteByExample(TaskExample example);

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
    int insert(Task record);

    /**
     * 
      * @Title: insertSelective
      * @Description: 插入数据,不为null的字段
      *  @param record
      *  @return  int 返回类型
      * @throws
     */
    int insertSelective(Task record);

    /**
     * 
      * @Title: selectByExample
      * @Description: 根据条件查询数据
      *  @param example
      *  @return  List<Task> 返回类型
      * @throws
     */
    List<Task> selectByExample(TaskExample example);

    /**
     * 
      * @Title: selectByPrimaryKey
      * @Description: 根据主键查询数据
      *  @param id
      *  @return  Task 返回类型
      * @throws
     */
    Task selectByPrimaryKey(String id);

    /**
     * 
      * @Title: updateByExampleSelective
      * @Description: 按条件更新值不为null的字段
      *  @param record
      *  @param example
      *  @return  int 返回类型
      * @throws
     */
    int updateByExampleSelective(@Param("record") Task record, @Param("example") TaskExample example);

    /**
     * 
      * @Title: updateByExample
      * @Description: 按条件更新
      *  @param record
      *  @param example
      *  @return  int 返回类型
      * @throws
     */
    int updateByExample(@Param("record") Task record, @Param("example") TaskExample example);

    /**
     * 
      * @Title: updateByPrimaryKeySelective
      * @Description: 按条件更新,带主键且不为null的字段
      *  @param record
      *  @return  int 返回类型
      * @throws
     */
    int updateByPrimaryKeySelective(Task record);

    /**
     * 
      * @Title: updateByPrimaryKey
      * @Description: 按主键更新
      *  @param record
      *  @return  int 返回类型
      * @throws
     */
    int updateByPrimaryKey(Task record);
    
    /**
     * 
      * @Title: batchInsert
      * @Description: 批量插入
      *  @param taskList
      *  @return  int 返回类型
      * @throws
     */
	int batchInsert(List<Task> taskList);
    /**
	 * 
	  * @Title: getTaskCount
	  * @Description: 查询课程资源总数
	  *  @param keywords String 课程资源名称
	  *  @return  long 返回类型
	  * @throws
	 */
	long getTaskCount(Map<String, Object> param);

	/**
	 * 
	  * @Title: queryTaskByPage
	  * @Description: 课程资源查询列表，带分页
	  *  @param keywords String 课程资源名称
	  *  @param skip int 从第几条开始取数据
	  *  @param limit int 每页显示多少条数据
	  *  @return  List<Task> 返回类型
	  * @throws
	 */
	List<Task> queryTaskByPage(Map<String, Object> param);

	/**
	 * 
	  * @Title: queryTaskByClass
	  * @Description: 班级课程资源查询列表
	  *  @param classId String 班级id
	  *  @return  List<Task> 返回类型
	  * @throws
	 */
	List<Task> queryTaskByClass(Map<String, Object> param);

    
	/**
	 * 
	  * @Title: getDetailCount
	  * @Description: 任务详情查询总数
	  *  @param className
	  *  @param id
	  *  @return  List<Task> 返回类型
	  * @throws
	 */
	List<Task> getDetailCount(Map<String, Object> param);
	
	/**
	 * 
	  * @Title: getDetailCount
	  * @Description: 任务详情查询
	  *  @param className
	  *  @param id
	  *  @return  long 返回类型
	  * @throws
	 */
	List<Task> queryDetailByPage(Map<String, Object> param);
	
	/**
	 * 
	  * @Title: selectTaskByAccount
	  * @Description: 根据任务发布字段查询课程资源
	  *  @param account 用户账号
	  *  @return  List<Task> 返回类型
	  * @throws
	 */
	List<Task> selectTaskByAccount(Map<String, Object> param);
}