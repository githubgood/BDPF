package com.xunfang.bdpf.experiment.clas.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xunfang.bdpf.experiment.clas.entity.Clas;
import com.xunfang.bdpf.experiment.clas.entity.ClasExample;

/**
 * @ClassName ClasMapper
 * @Description:班级管理Mapper映射接口类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年6月7日 下午3:09:56
 * @version V1.0
 */
public interface ClasMapper {
	
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
    long countByExample(ClasExample example);

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int deleteByExample(ClasExample example);

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
    int insert(Clas record);

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
    int insertSelective(Clas record);

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<Clas> 返回类型
	  * @throws
	 */
    List<Clas> selectByExample(ClasExample example);

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  Clas 返回类型
   	  * @throws
   	 */
    Clas selectByPrimaryKey(String id);

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByExampleSelective(@Param("record") Clas record, @Param("example") ClasExample example);

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
    int updateByExample(@Param("record") Clas record, @Param("example") ClasExample example);

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKeySelective(Clas record);

    /**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
    int updateByPrimaryKey(Clas record);
    
    /**
	 * 
	  * @Title: getClasCount
	  * @Description: 查询班级总数
	  *  @param keywords String 班级名称
	  *  @return  long 返回类型
	  * @throws
	 */
   	long getClasCount(Map<String, Object> param);

   	/**
   	 * 
   	  * @Title: queryClasByPage
   	  * @Description: 班级管理查询列表，带分页
   	  *  @param keywords String 班级名称
   	  *  @param skip int 从第几条开始取数据
   	  *  @param limit int 每页显示多少条数据
   	  *  @return  List<Clas> 返回类型
   	  * @throws
   	 */
   	List<Clas> queryClasByPage(Map<String, Object> param);
   	
	/**
	 * 
	  * @Title: queryClassUser
	  * @Description: TODO连表查询班级与学生
	  *  @return  List<Clas> 返回类型
	  * @throws
	 */
	List<Clas> queryClassUser(Map<String, Object> param);
	
	/**
	 * 
	 * @Title: batchImport
	 * @Description: 导入班级信息
	 * @param List<Clas> 班级信息
	 * @return boolean
	 */
	boolean batchImport(List<Clas> claslist);
	
}