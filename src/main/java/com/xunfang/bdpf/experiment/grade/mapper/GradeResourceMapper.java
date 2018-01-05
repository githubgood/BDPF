package com.xunfang.bdpf.experiment.grade.mapper;

import com.xunfang.bdpf.experiment.grade.entity.GradeResource;
import com.xunfang.bdpf.experiment.grade.entity.GradeResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 
 * @ClassName GradeResourceMapper
 * @Description: 成绩资源关联mapper层接口
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月23日 上午10:47:37
 * @version V1.0
 */
public interface GradeResourceMapper {
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @param example
	  *  @return  long 返回类型
	  * @throws
	 */
    long countByExample(GradeResourceExample example);

    /**
     * 
      * @Title: deleteByExample
      * @Description: 根据条件删除多条数据
      *  @param example
      *  @return  int 返回类型
      * @throws
     */
    int deleteByExample(GradeResourceExample example);

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
    int insert(GradeResource record);

    /**
     * 
      * @Title: insertSelective
      * @Description: 插入数据,不为null的字段
      *  @param record
      *  @return  int 返回类型
      * @throws
     */
    int insertSelective(GradeResource record);

    /**
     * 
      * @Title: selectByExample
      * @Description: 根据条件查询数据
      *  @param example
      *  @return  List<GradeResource> 返回类型
      * @throws
     */
    List<GradeResource> selectByExample(GradeResourceExample example);

    /**
     * 
      * @Title: selectByPrimaryKey
      * @Description: 根据主键查询数据
      *  @param id
      *  @return  Group 返回类型
      * @throws
     */
    GradeResource selectByPrimaryKey(String id);

    /**
     * 
      * @Title: updateByExampleSelective
      * @Description: 按条件更新值不为null的字段
      *  @param record
      *  @param example
      *  @return  int 返回类型
      * @throws
     */
    int updateByExampleSelective(@Param("record") GradeResource record, @Param("example") GradeResourceExample example);

    /**
     * 
      * @Title: updateByExample
      * @Description: 按条件更新
      *  @param record
      *  @param example
      *  @return  int 返回类型
      * @throws
     */
    int updateByExample(@Param("record") GradeResource record, @Param("example") GradeResourceExample example);

    /**
     * 
      * @Title: updateByPrimaryKeySelective
      * @Description: 按条件更新,带主键且不为null的字段
      *  @param record
      *  @return  int 返回类型
      * @throws
     */
    int updateByPrimaryKeySelective(GradeResource record);

    /**
     * 
      * @Title: updateByPrimaryKey
      * @Description: 按主键更新
      *  @param record
      *  @return  int 返回类型
      * @throws
     */
    int updateByPrimaryKey(GradeResource record);
    
    /**
     * 
      * @Title: batchInsert
      * @Description: 批量插入
      *  @param gradeResourceList
      *  @return  int 返回类型
      * @throws
     */
	int batchInsert(List<GradeResource> gradeResourceList);
}