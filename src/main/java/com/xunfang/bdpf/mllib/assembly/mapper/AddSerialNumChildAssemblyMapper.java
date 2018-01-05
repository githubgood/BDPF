package com.xunfang.bdpf.mllib.assembly.mapper;

import com.xunfang.bdpf.mllib.assembly.entity.AddSerialNumChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.AddSerialNumChildAssemblyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AddSerialNumChildAssemblyMapper {
    long countByExample(AddSerialNumChildAssemblyExample example);

    int deleteByExample(AddSerialNumChildAssemblyExample example);

    int deleteByPrimaryKey(String id);

    int insert(AddSerialNumChildAssembly record);

    int insertSelective(AddSerialNumChildAssembly record);

    List<AddSerialNumChildAssembly> selectByExample(AddSerialNumChildAssemblyExample example);

    AddSerialNumChildAssembly selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AddSerialNumChildAssembly record, @Param("example") AddSerialNumChildAssemblyExample example);

    int updateByExample(@Param("record") AddSerialNumChildAssembly record, @Param("example") AddSerialNumChildAssemblyExample example);

    int updateByPrimaryKeySelective(AddSerialNumChildAssembly record);

    int updateByPrimaryKey(AddSerialNumChildAssembly record);

    int batchInsertChild(List<AddSerialNumChildAssembly> addSerialNumChildAssemblies);
}