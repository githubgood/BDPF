package com.xunfang.bdpf.mllib.assembly.mapper;

import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesAssemblyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FillMissingValuesAssemblyMapper {
    long countByExample(FillMissingValuesAssemblyExample example);

    int deleteByExample(FillMissingValuesAssemblyExample example);

    int deleteByPrimaryKey(String id);

    int insert(FillMissingValuesAssembly record);

    int insertSelective(FillMissingValuesAssembly record);

    List<FillMissingValuesAssembly> selectByExample(FillMissingValuesAssemblyExample example);

    FillMissingValuesAssembly selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") FillMissingValuesAssembly record, @Param("example") FillMissingValuesAssemblyExample example);

    int updateByExample(@Param("record") FillMissingValuesAssembly record, @Param("example") FillMissingValuesAssemblyExample example);

    int updateByPrimaryKeySelective(FillMissingValuesAssembly record);

    int updateByPrimaryKey(FillMissingValuesAssembly record);
}