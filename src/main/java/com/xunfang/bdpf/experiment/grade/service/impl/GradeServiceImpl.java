package com.xunfang.bdpf.experiment.grade.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xunfang.bdpf.experiment.grade.entity.Grade;
import com.xunfang.bdpf.experiment.grade.entity.GradeExample;
import com.xunfang.bdpf.experiment.grade.mapper.GradeMapper;
import com.xunfang.bdpf.experiment.grade.service.GradeService;
import com.xunfang.bdpf.experiment.task.entity.Task;
import com.xunfang.bdpf.system.user.entity.User;

/**
 * @ClassName GradeServiceImpl
 * @Description:成绩Service接口实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月7日 下午3:09:56
 * @version V1.0
 */
@Service
@Transactional
public class GradeServiceImpl implements GradeService {
	
	@Autowired
	//成绩管理Mapper映射接口类
	private GradeMapper gradeMapper;

	/**
	 * 
	  * @Title: getCourseCount
	  * @Description: 查询课程资源总数
	  *  @param keywords String 课程资源名称
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public List<Grade> getGradeCount(String taskId,String classId,String studentId,String teacherId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("taskId", taskId);
		param.put("classId", classId);
		param.put("studentId", studentId);
		param.put("teacherId", teacherId);
		return gradeMapper.getGradeCount(param);
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
	public List<Grade> queryGradeByPage(String taskId,String classId, String studentId,String teacherId,int skip, int limit) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("taskId", taskId);
		param.put("classId", classId);
		param.put("studentId", studentId);
		param.put("teacherId", teacherId);
		param.put("skip", skip);
		param.put("limit", limit);
		return gradeMapper.queryGradeByPage(param);
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
	public boolean deleteByExample(GradeExample example) {
		return  gradeMapper.deleteByExample(example) > 0 ? true : false;
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
	public Grade selectByPrimaryKey(String param) {
		return gradeMapper.selectByPrimaryKey(param);
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
	public List<Grade> selectByExample(GradeExample example) {
		return gradeMapper.selectByExample(example);
	}

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
	@Override
	public List<Grade> getDetailCount(String taskId, String classId, String studentId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("taskId", taskId);
		param.put("classId", classId);
		param.put("studentId", studentId);
		return gradeMapper.getDetailCount(param);
	}

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
	@Override
	public List<Grade> queryDetailByPage(String taskId, String classId, String studentId, int skip, int limit) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("taskId", taskId);
		param.put("classId", classId);
		param.put("studentId", studentId);
		param.put("skip", skip);
		param.put("limit", limit);
		return gradeMapper.queryDetailByPage(param);
	}

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
	@Override
	public List<Grade> getCheckCount(String taskId, String classId, String title,String teacherId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("taskId", taskId);
		param.put("classId", classId);
		param.put("title", title);
		param.put("teacherId", teacherId);
		return gradeMapper.getCheckCount(param);
	}

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
	@Override
	public List<Grade> queryCheckByPage(String taskId, String classId, String title,String teacherId, int skip, int limit) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("taskId", taskId);
		param.put("classId", classId);
		param.put("title", title);
		param.put("teacherId", teacherId);
		param.put("skip", skip);
		param.put("limit", limit);
		return gradeMapper.queryCheckByPage(param);
	}

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
	@Override
	public List<Task> getPersonListCount(String taskId, String courseResourceId, String classId,String taskStatus,String teacherId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("taskId", taskId);
		param.put("classId", classId);
		param.put("courseResourceId", courseResourceId);
		param.put("taskStatus", taskStatus);
		param.put("teacherId", teacherId);
		return gradeMapper.getPersonListCount(param);
	}

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
	@Override
	public List<Grade> queryPersonListByPage(String taskId, String courseResourceId, String classId,String taskStatus,String teacherId, int skip,
			int limit) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("taskId", taskId);
		param.put("classId", classId);
		param.put("courseResourceId", courseResourceId);
		param.put("taskStatus", taskStatus);
		param.put("teacherId", teacherId);
		param.put("skip", skip);
		param.put("limit", limit);
		return gradeMapper.queryPersonListByPage(param);
	}

	/**
	 * 
	  * @Title: save
	  * @Description: 数据保存
	  *  @param grade
	  *  @return  boolean 返回类型
	  * @throws
	 */
	@Override
	public boolean save(Grade grade) {
		boolean flag = true;
		if (StringUtils.isBlank(grade.getId())) {
			flag = gradeMapper.insert(grade) > 0 ? true : false;
		} else {
			flag = gradeMapper.updateByPrimaryKeySelective(grade) > 0 ? true : false;
		}
		return flag;
	}

	
	/**
	 * 
	  * @Title: getExpListCount
	  * @Description: 获取课程实验任务总数
	  * @param @return    设定文件
	  * @return long    返回类型
	  * @throws
	 */
	@Override
	public long getExpListCount(String taskId,String classId,String studentId,String keywords, int skip, int limit) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("taskId", taskId);
		param.put("classId", classId);
		param.put("studentId", studentId);
		param.put("keywords", keywords);
		param.put("skip", skip);
		param.put("limit", limit);
		return gradeMapper.getExpListCount(param);
	}

	
	/**
	 * 
	  * @Title: queryExpListByPage
	  * @Description: 分页查询 课程实验任务列表
	  * @param @param limit
	  * @param @return    设定文件
	  * @return List<Course>    返回类型
	  * @throws
	 */
	@Override
	public List<Grade> queryExpListByPage(String taskId,String classId,String studentId,String keywords, int skip, int limit) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("taskId", taskId);
		param.put("classId", classId);
		param.put("studentId", studentId);
		param.put("keywords", keywords);
		param.put("skip", skip);
		param.put("limit", limit);
		return gradeMapper.queryExpListByPage(param);
	}

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
	@Override
	public List<Grade> queryGroupListByPage(String taskId, String courseResourceId, String classId, String taskStatus,
			String teacherId, int skip, int limit) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("taskId", taskId);
		param.put("classId", classId);
		param.put("courseResourceId", courseResourceId);
		param.put("taskStatus", taskStatus);
		param.put("teacherId", teacherId);
		param.put("skip", skip);
		param.put("limit", limit);
		return gradeMapper.queryGroupListByPage(param);
	}

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
	@Override
	public List<Grade> getGroupListCount(String taskId, String courseResourceId, String classId, String taskStatus,
			String teacherId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("taskId", taskId);
		param.put("classId", classId);
		param.put("courseResourceId", courseResourceId);
		param.put("taskStatus", taskStatus);
		param.put("teacherId", teacherId);
		return gradeMapper.getGroupListCount(param);
	}

	@Override
	public boolean groupGrade(Grade grade) {
		boolean flag = true;
		GradeExample gradeExample=new GradeExample();
		gradeExample.createCriteria().andGroupIdEqualTo(grade.getGroupId());
		flag = gradeMapper.updateByExampleSelective(grade, gradeExample)> 0 ? true : false;
		return flag;
	}

	
	/**
     * 
      * @Title: selectExpById
      * @Description: 根据主键查询数据
      *  @param id
      *  @return  Grade 返回类型
      * @throws
     */
	@Override
	public Grade selectExpById(String id) {
		return gradeMapper.selectExpById(id);
	}

	
	/**
     * 
      * @Title: updateByExampleSelective
      * @Description: 按条件更新值不为null的字段
      *  @param record
      *  @param example
      *  @return  int 返回类型
      * @throws
     */
	@Override
	public int updateByExampleSelective(Grade record, GradeExample example) {
		return gradeMapper.updateByExampleSelective(record, example);
	}


	@Override
	public List<User> selectByExampleGroupName(String task_id, String class_id, String group_id,int type,String account) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("taskId", task_id);
		param.put("classId", class_id);
		param.put("groupId", group_id);
		param.put("account", account);
		param.put("type", type);
		return gradeMapper.selectByExampleGroupName(param);
	}

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
	@Override
	public List<Grade> queryListDetail(String taskId, String classId, String courseResourceId, String teacherId,
			int skip, int limit) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("taskId", taskId);
		param.put("classId", classId);
		param.put("courseResourceId", courseResourceId);
		param.put("teacherId", teacherId);
		param.put("skip", skip);
		param.put("limit", limit);
		return gradeMapper.queryListDetail(param);
	}

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
	@Override
	public List<Task> queryListCount(String taskId, String classId, String courseResourceId, String teacherId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("taskId", taskId);
		param.put("classId", classId);
		param.put("courseResourceId", courseResourceId);
		param.put("teacherId", teacherId);
		return gradeMapper.queryListCount(param);
	}
	
	 
}
