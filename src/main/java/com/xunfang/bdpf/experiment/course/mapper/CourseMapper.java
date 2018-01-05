package com.xunfang.bdpf.experiment.course.mapper;

import com.xunfang.bdpf.experiment.course.entity.Course;
import com.xunfang.bdpf.experiment.course.entity.CourseExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @ClassName CourseMapper
 * @Description:课程资源Mapper映射接口类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月7日 下午3:09:56
 * @version V1.0
 */
public interface CourseMapper {
	
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
    long countByExample(CourseExample example);

    /**
	 * 
	  * @Title: deleteByExample
	  * @Description: 根据条件删除多条数据
	  *  @return  int 返回类型
	  * @throws
	 */
    int deleteByExample(CourseExample example);

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
    int insert(Course record);

    /**
	 * 
	  * @Title: insertSelective
	  * @Description: 插入数据,不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int insertSelective(Course record);

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<Course> 返回类型
	  * @throws
	 */
    List<Course> selectByExample(CourseExample example);

    /**
	 * 
	  * @Title: selectByPrimaryKey
	  * @Description: 根据主键查询数据
	  *  @return  Course 返回类型
	  * @throws
	 */
    Course selectByPrimaryKey(String id);

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByExampleSelective(@Param("record") Course record, @Param("example") CourseExample example);

    /**
	 * 
	  * @Title: updateByExample
	  * @Description: 按条件更新
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByExample(@Param("record") Course record, @Param("example") CourseExample example);

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKeySelective(Course record);

    /**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKey(Course record);
    
    /**
	 * 
	  * @Title: getCourseCount
	  * @Description: 查询课程资源总数
	  *  @param keywords String 课程资源名称
	  *  @return  long 返回类型
	  * @throws
	 */
	long getCourseCount(Map<String, Object> param);

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
	List<Course> queryCourseByPage(Map<String, Object> param);

	
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
	  * @param @return    设定文件
	  * @return long    返回类型
	  * @throws
	 */
	long getCourseTypeCount(Map<String, Object> param);
	
	/**
	 * 
	  * @Title: queryCourseByTypePage
	  * @Description: 分页获取 资料列表 根据id和类型
	  * @param @param limit
	  * @param @return    设定文件
	  * @return List<Course>    返回类型
	  * @throws
	 */
	List<Course> queryCourseByTypePage(Map<String, Object> param);
}