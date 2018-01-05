package com.xunfang.bdpf.mllib.assembly.mapper;

import com.xunfang.bdpf.mllib.assembly.entity.UnionChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.UnionChildAssemblyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UnionChildAssemblyMapper {
    long countByExample(UnionChildAssemblyExample example);

    int deleteByExample(UnionChildAssemblyExample example);

    int deleteByPrimaryKey(String id);

    int insert(UnionChildAssembly record);

    int insertSelective(UnionChildAssembly record);

    List<UnionChildAssembly> selectByExample(UnionChildAssemblyExample example);

    UnionChildAssembly selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UnionChildAssembly record, @Param("example") UnionChildAssemblyExample example);

    int updateByExample(@Param("record") UnionChildAssembly record, @Param("example") UnionChildAssemblyExample example);

    int updateByPrimaryKeySelective(UnionChildAssembly record);

    int updateByPrimaryKey(UnionChildAssembly record);

    int batchInsertChild(List<UnionChildAssembly> unionChildAssemblies);
}