package com.xunfang.bdpf.mllib.assembly.mapper;

import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesChildAssemblyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FillMissingValuesChildAssemblyMapper {
    long countByExample(FillMissingValuesChildAssemblyExample example);

    int deleteByExample(FillMissingValuesChildAssemblyExample example);

    int deleteByPrimaryKey(String id);

    int insert(FillMissingValuesChildAssembly record);

    int insertSelective(FillMissingValuesChildAssembly record);

    List<FillMissingValuesChildAssembly> selectByExample(FillMissingValuesChildAssemblyExample example);

    FillMissingValuesChildAssembly selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") FillMissingValuesChildAssembly record, @Param("example") FillMissingValuesChildAssemblyExample example);

    int updateByExample(@Param("record") FillMissingValuesChildAssembly record, @Param("example") FillMissingValuesChildAssemblyExample example);

    int updateByPrimaryKeySelective(FillMissingValuesChildAssembly record);

    int updateByPrimaryKey(FillMissingValuesChildAssembly record);

    void batchInsertChild(List<FillMissingValuesChildAssembly> fillMissingValuesChildAssemblies);
}