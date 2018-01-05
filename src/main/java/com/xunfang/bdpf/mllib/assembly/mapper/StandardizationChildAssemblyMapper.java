package com.xunfang.bdpf.mllib.assembly.mapper;

import com.xunfang.bdpf.mllib.assembly.entity.StandardizationChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.StandardizationChildAssemblyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StandardizationChildAssemblyMapper {
    long countByExample(StandardizationChildAssemblyExample example);

    int deleteByExample(StandardizationChildAssemblyExample example);

    int deleteByPrimaryKey(String id);

    int insert(StandardizationChildAssembly record);

    int insertSelective(StandardizationChildAssembly record);

    List<StandardizationChildAssembly> selectByExample(StandardizationChildAssemblyExample example);

    StandardizationChildAssembly selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StandardizationChildAssembly record, @Param("example") StandardizationChildAssemblyExample example);

    int updateByExample(@Param("record") StandardizationChildAssembly record, @Param("example") StandardizationChildAssemblyExample example);

    int updateByPrimaryKeySelective(StandardizationChildAssembly record);

    int updateByPrimaryKey(StandardizationChildAssembly record);

    int batchInsertChild(List<StandardizationChildAssembly> standardizationChildAssemblies);
}