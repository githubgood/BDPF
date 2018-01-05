package com.xunfang.bdpf.mllib.assembly.mapper;

import com.xunfang.bdpf.mllib.assembly.entity.UnionAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.UnionAssemblyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UnionAssemblyMapper {
    long countByExample(UnionAssemblyExample example);

    int deleteByExample(UnionAssemblyExample example);

    int deleteByPrimaryKey(String id);

    int insert(UnionAssembly record);

    int insertSelective(UnionAssembly record);

    List<UnionAssembly> selectByExample(UnionAssemblyExample example);

    UnionAssembly selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UnionAssembly record, @Param("example") UnionAssemblyExample example);

    int updateByExample(@Param("record") UnionAssembly record, @Param("example") UnionAssemblyExample example);

    int updateByPrimaryKeySelective(UnionAssembly record);

    int updateByPrimaryKey(UnionAssembly record);
}