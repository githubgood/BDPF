package com.xunfang.bdpf.mllib.assembly.mapper;

import com.xunfang.bdpf.mllib.assembly.entity.JoinAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.JoinAssemblyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JoinAssemblyMapper {
    long countByExample(JoinAssemblyExample example);

    int deleteByExample(JoinAssemblyExample example);

    int deleteByPrimaryKey(String id);

    int insert(JoinAssembly record);

    int insertSelective(JoinAssembly record);

    List<JoinAssembly> selectByExample(JoinAssemblyExample example);

    JoinAssembly selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") JoinAssembly record, @Param("example") JoinAssemblyExample example);

    int updateByExample(@Param("record") JoinAssembly record, @Param("example") JoinAssemblyExample example);

    int updateByPrimaryKeySelective(JoinAssembly record);

    int updateByPrimaryKey(JoinAssembly record);
}