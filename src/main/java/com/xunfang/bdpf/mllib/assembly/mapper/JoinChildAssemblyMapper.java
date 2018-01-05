package com.xunfang.bdpf.mllib.assembly.mapper;

import com.xunfang.bdpf.mllib.assembly.entity.JoinChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.JoinChildAssemblyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JoinChildAssemblyMapper {
    long countByExample(JoinChildAssemblyExample example);

    int deleteByExample(JoinChildAssemblyExample example);

    int deleteByPrimaryKey(String id);

    int insert(JoinChildAssembly record);

    int insertSelective(JoinChildAssembly record);

    List<JoinChildAssembly> selectByExample(JoinChildAssemblyExample example);

    JoinChildAssembly selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") JoinChildAssembly record, @Param("example") JoinChildAssemblyExample example);

    int updateByExample(@Param("record") JoinChildAssembly record, @Param("example") JoinChildAssemblyExample example);

    int updateByPrimaryKeySelective(JoinChildAssembly record);

    int updateByPrimaryKey(JoinChildAssembly record);

    int batchInsertChild(List<JoinChildAssembly> joinChildAssemblies);
}