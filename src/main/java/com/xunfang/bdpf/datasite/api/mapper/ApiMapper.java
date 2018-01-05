package com.xunfang.bdpf.datasite.api.mapper;

import com.xunfang.bdpf.datasite.api.entity.Api;
import com.xunfang.bdpf.datasite.api.entity.ApiExample;
import com.xunfang.bdpf.datasite.area.entity.Area;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ApiMapper {
    long countByExample(ApiExample example);

    int deleteByExample(ApiExample example);

    int deleteByPrimaryKey(String id);
    
    int deleteApiByAreaId(String id);

    int insert(Api record);

    int insertSelective(Api record);

    List<Api> selectByExample(ApiExample example);

    Api selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Api record, @Param("example") ApiExample example);

    int updateByExample(@Param("record") Api record, @Param("example") ApiExample example);

    int updateByPrimaryKeySelective(Api record);

    int updateByPrimaryKey(Api record);
    
    List<Api> selectApiByAreaId(String id);
    
    int updataApi(Api api);
}