package com.xunfang.bdpf.experiment.task.mapper;

import com.xunfang.bdpf.experiment.task.entity.Group;
import com.xunfang.bdpf.experiment.task.entity.GroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 
 * @ClassName GroupMapper
 * @Description: 分组Mapper接口
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月23日 上午10:18:00
 * @version V1.0
 */
public interface GroupMapper {
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @param example
	  *  @return  long 返回类型
	  * @throws
	 */
    long countByExample(GroupExample example);

    /**
     * 
      * @Title: deleteByExample
      * @Description: 根据条件删除多条数据
      *  @param example
      *  @return  int 返回类型
      * @throws
     */
    int deleteByExample(GroupExample example);

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
    int insert(Group record);

    /**
     * 
      * @Title: insertSelective
      * @Description: 插入数据,不为null的字段
      *  @param record
      *  @return  int 返回类型
      * @throws
     */
    int insertSelective(Group record);

    /**
     * 
      * @Title: selectByExample
      * @Description: 根据条件查询数据
      *  @param example
      *  @return  List<Group> 返回类型
      * @throws
     */
    List<Group> selectByExample(GroupExample example);

    /**
     * 
      * @Title: selectByPrimaryKey
      * @Description: 根据主键查询数据
      *  @param id
      *  @return  Group 返回类型
      * @throws
     */
    Group selectByPrimaryKey(String id);

    /**
     * 
      * @Title: updateByExampleSelective
      * @Description: 按条件更新值不为null的字段
      *  @param record
      *  @param example
      *  @return  int 返回类型
      * @throws
     */
    int updateByExampleSelective(@Param("record") Group record, @Param("example") GroupExample example);

    /**
     * 
      * @Title: updateByExample
      * @Description: 按条件更新
      *  @param record
      *  @param example
      *  @return  int 返回类型
      * @throws
     */
    int updateByExample(@Param("record") Group record, @Param("example") GroupExample example);

    /**
     * 
      * @Title: updateByPrimaryKeySelective
      * @Description: 按条件更新,带主键且不为null的字段
      *  @param record
      *  @return  int 返回类型
      * @throws
     */
    int updateByPrimaryKeySelective(Group record);

    /**
     * 
      * @Title: updateByPrimaryKey
      * @Description: 按主键更新
      *  @param record
      *  @return  int 返回类型
      * @throws
     */
    int updateByPrimaryKey(Group record);
    
    /**
     * 
      * @Title: batchInsert
      * @Description: 批量插入
      *  @param groupList
      *  @return  int 返回类型
      * @throws
     */
	int batchInsert(List<Group> groupList);
}