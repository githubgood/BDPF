package com.xunfang.bdpf.datasite.resources.mapper;

import com.xunfang.bdpf.datasite.resources.entity.Resources;
import com.xunfang.bdpf.datasite.resources.entity.ResourcesExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ResourcesMapper {
    long countByExample(ResourcesExample example);

    int deleteByExample(ResourcesExample example);

    int deleteByPrimaryKey(String id);
    
    int deleteByAreaId(String id);

    int insert(Resources record);

    int insertSelective(Resources record);

    List<Resources> selectByExample(ResourcesExample example);
    
    List<Resources> selectByResByAreaId(Map<String, Object> param);
    
    Resources selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Resources record, @Param("example") ResourcesExample example);

    int updateByExample(@Param("record") Resources record, @Param("example") ResourcesExample example);

    int updateByPrimaryKeySelective(Resources record);

    int updateByPrimaryKey(Resources record);
}