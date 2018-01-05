package com.xunfang.bdpf.system.resource.mapper;

import com.xunfang.bdpf.system.resource.entity.Resource;
import com.xunfang.bdpf.system.resource.entity.ResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourceMapper {
    long countByExample(ResourceExample example);

    int deleteByExample(ResourceExample example);

    int deleteByPrimaryKey(String id);
    /**
	 * 
	  * @Title: insert
	  * @Description: 新增数据
	  * @param @param record
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
    int insert(Resource record);

    int insertSelective(Resource record);

    List<Resource> selectByExample(ResourceExample example);
    /**
	 * 
	  * @Title: selectByPrimaryKey
	  * @Description: 查询数据
	  * @param @param id
	  * @param @return    设定文件
	  * @return Resource    返回类型
	  * @throws
	 */
    Resource selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByExample(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByPrimaryKeySelective(Resource record);
    /**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 更新数据
	  * @param @param resource
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
    int updateByPrimaryKey(Resource record);
}