package com.xunfang.bdpf.experiment.task.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.xunfang.bdpf.experiment.task.entity.Task;
import com.xunfang.bdpf.experiment.task.entity.TaskExample;
import com.xunfang.bdpf.system.user.entity.User;
/**
 * 
 * @ClassName TaskService
 * @Description: 发布任务接口
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月14日 上午10:39:56
 * @version V1.0
 */
public interface TaskService {

	/**
	 * 
	  * @Title: getTaskCount
	  * @Description: 查询任务发布总数
	  *  @param keywords String 任务发布名称
	  *  @return  long 返回类型
	  * @throws
	 */
	long getTaskCount(String keywords,String account);
	
	/**
	 * 
	  * @Title: queryTaskByPage
	  * @Description: 任务发布查询列表，带分页
	  *  @param keywords String 课程资源名称
	  *  @param skip int 从第几条开始取数据
	  *  @param limit int 每页显示多少条数据
	  *  @return  List<Task> 返回类型
	  * @throws
	 */
	List<Task> queryTaskByPage(String keywords, int skip, int limit,String account);

	/**
	 * 
	  * @Title: queryTaskByClass
	  * @Description: 班级课程资源查询列表
	  *  @param classId String 班级id
	  *  @return  List<Task> 返回类型
	  * @throws
	 */
	List<Task> queryTaskByClass(String classId);

	/**
	 * 
	  * @Title: save
	  * @Description: 任务发布保存
	  *  @param Task  Task 课程资源model
	  *  @return  boolean 返回类型
	  * @throws
	 */
	boolean save(Task Task,MultipartFile file,String token,User user);
	
	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据任务发布字段查询课程资源
	  *  @param example 任务发布实体对象
	  *  @return  List<Task> 返回类型
	  * @throws
	 */
	List<Task> selectByExample(TaskExample example);

	/**
	 * 
	  * @Title: selectByPrimaryKey
	  * @Description: 根据任务发布主键ID查询任务发布
	  *  @param id String 任务发布主键ID
	  *  @return  Task 返回类型
	  * @throws
	 */
	Task selectByPrimaryKey(String id);

	/**
	 * 
	  * @Title: deleteByExample
	  * @Description: 任务发布删除
	  *  @param example TaskExample 任务发布TaskExample
	  *  @return  boolean 返回类型
	  * @throws
	 */
	boolean deleteByExample(List<String> asList);
    
	/**
	 * 
	  * @Title: getDetailCount
	  * @Description: 任务详情查询总数
	  *  @param classId
	  *  @param id
	  *  @return  List<Task> 返回类型
	  * @throws
	 */
	List<Task> getDetailCount(String classId, String id);
	
    /**
     * 
      * @Title: queryDetailByPage
      * @Description: 任务详情查询
      *  @param classId
      *  @param id
      *  @param skip
      *  @param max
      *  @return  List<Task> 返回类型
      * @throws
     */
	List<Task> queryDetailByPage(String classId, String id, int skip, int max);
	
	/**
	 * 
	  * @Title: selectTaskByAccount
	  * @Description: 根据任务发布字段查询课程资源
	  *  @param account 用户账号
	  *  @return  List<Task> 返回类型
	  * @throws
	 */
	List<Task> selectTaskByAccount(String account);
}
