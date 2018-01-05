package com.xunfang.bdpf.mllib.assembly.mapper;

import com.xunfang.bdpf.mllib.assembly.entity.AddSerialNumAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.AddSerialNumAssemblyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AddSerialNumAssemblyMapper {
    long countByExample(AddSerialNumAssemblyExample example);

    int deleteByExample(AddSerialNumAssemblyExample example);

    int deleteByPrimaryKey(String id);

    int insert(AddSerialNumAssembly record);

    int insertSelective(AddSerialNumAssembly record);

    List<AddSerialNumAssembly> selectByExample(AddSerialNumAssemblyExample example);

    AddSerialNumAssembly selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AddSerialNumAssembly record, @Param("example") AddSerialNumAssemblyExample example);

    int updateByExample(@Param("record") AddSerialNumAssembly record, @Param("example") AddSerialNumAssemblyExample example);

    int updateByPrimaryKeySelective(AddSerialNumAssembly record);

    int updateByPrimaryKey(AddSerialNumAssembly record);
}