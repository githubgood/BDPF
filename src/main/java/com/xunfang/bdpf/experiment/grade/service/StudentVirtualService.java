package com.xunfang.bdpf.experiment.grade.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xunfang.bdpf.experiment.grade.entity.StudentVirtual;
import com.xunfang.bdpf.experiment.grade.entity.StudentVirtualExample;

/**
 * 
 * @ClassName StudentVirtualService
 * @Description:学生与虚拟机Service接口服务
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年6月7日 下午3:07:52
 * @version V1.0
 */
public interface StudentVirtualService {
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @param example
	  *  @return  long 返回类型
	  * @throws
	 */
    long countByExample(StudentVirtualExample example);

    /**
     * 
      * @Title: deleteByExample
      * @Description: 根据条件删除多条数据
      *  @param example
      *  @return  int 返回类型
      * @throws
     */
    int deleteByExample(StudentVirtualExample example);

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
    int insert(StudentVirtual record);

    /**
     * 
      * @Title: insertSelective
      * @Description: 插入数据,不为null的字段
      *  @param record
      *  @return  int 返回类型
      * @throws
     */
    int insertSelective(StudentVirtual record);

    /**
     * 
      * @Title: selectByExample
      * @Description: 根据条件查询数据
      *  @param example
      *  @return  List<Group> 返回类型
      * @throws
     */
    List<StudentVirtual> selectByExample(StudentVirtualExample example);

    /**
     * 
      * @Title: selectByPrimaryKey
      * @Description: 根据主键查询数据
      *  @param id
      *  @return  Group 返回类型
      * @throws
     */
    StudentVirtual selectByPrimaryKey(String id);

    /**
     * 
      * @Title: updateByExampleSelective
      * @Description: 按条件更新值不为null的字段
      *  @param record
      *  @param example
      *  @return  int 返回类型
      * @throws
     */
    int updateByExampleSelective(@Param("record") StudentVirtual record, @Param("example") StudentVirtualExample example);

    /**
     * 
      * @Title: updateByExample
      * @Description: 按条件更新
      *  @param record
      *  @param example
      *  @return  int 返回类型
      * @throws
     */
    int updateByExample(@Param("record") StudentVirtual record, @Param("example") StudentVirtualExample example);

    /**
     * 
      * @Title: updateByPrimaryKeySelective
      * @Description: 按条件更新,带主键且不为null的字段
      *  @param record
      *  @return  int 返回类型
      * @throws
     */
    int updateByPrimaryKeySelective(StudentVirtual record);

    /**
     * 
      * @Title: updateByPrimaryKey
      * @Description: 按主键更新
      *  @param record
      *  @return  int 返回类型
      * @throws
     */
    int updateByPrimaryKey(StudentVirtual record);
    
    /**
     * 
      * @Title: selectByClassIdAndAccount
      * @Description: 根据条件查询数据
      * @param account String 账号
      * @param classId String 班级ID
      * @return  List<StudentVirtual> 返回类型
      * @throws
     */
    List<StudentVirtual> selectByClassIdAndAccount(String account,String groupId,List<String> classId);
}