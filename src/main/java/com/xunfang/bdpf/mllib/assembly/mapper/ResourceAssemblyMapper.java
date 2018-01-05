package com.xunfang.bdpf.mllib.assembly.mapper;

import com.xunfang.bdpf.mllib.assembly.entity.ResourceAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.ResourceAssemblyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourceAssemblyMapper {
    long countByExample(ResourceAssemblyExample example);

    int deleteByExample(ResourceAssemblyExample example);

    int deleteByPrimaryKey(String id);

    int insert(ResourceAssembly record);

    int insertSelective(ResourceAssembly record);

    List<ResourceAssembly> selectByExample(ResourceAssemblyExample example);

    ResourceAssembly selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ResourceAssembly record, @Param("example") ResourceAssemblyExample example);

    int updateByExample(@Param("record") ResourceAssembly record, @Param("example") ResourceAssemblyExample example);

    int updateByPrimaryKeySelective(ResourceAssembly record);

    int updateByPrimaryKey(ResourceAssembly record);
}