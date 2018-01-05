package com.xunfang.bdpf.mllib.assembly.mapper;

import com.xunfang.bdpf.mllib.assembly.entity.StandardizationAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.StandardizationAssemblyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StandardizationAssemblyMapper {
    long countByExample(StandardizationAssemblyExample example);

    int deleteByExample(StandardizationAssemblyExample example);

    int deleteByPrimaryKey(String id);

    int insert(StandardizationAssembly record);

    int insertSelective(StandardizationAssembly record);

    List<StandardizationAssembly> selectByExample(StandardizationAssemblyExample example);

    StandardizationAssembly selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StandardizationAssembly record, @Param("example") StandardizationAssemblyExample example);

    int updateByExample(@Param("record") StandardizationAssembly record, @Param("example") StandardizationAssemblyExample example);

    int updateByPrimaryKeySelective(StandardizationAssembly record);

    int updateByPrimaryKey(StandardizationAssembly record);
}