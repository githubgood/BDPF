package com.xunfang.bdpf.mllib.assembly.mapper;

import com.xunfang.bdpf.mllib.assembly.entity.RandomSamplingAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.RandomSamplingAssemblyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RandomSamplingAssemblyMapper {
    long countByExample(RandomSamplingAssemblyExample example);

    int deleteByExample(RandomSamplingAssemblyExample example);

    int deleteByPrimaryKey(String id);

    int insert(RandomSamplingAssembly record);

    int insertSelective(RandomSamplingAssembly record);

    List<RandomSamplingAssembly> selectByExample(RandomSamplingAssemblyExample example);

    RandomSamplingAssembly selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RandomSamplingAssembly record, @Param("example") RandomSamplingAssemblyExample example);

    int updateByExample(@Param("record") RandomSamplingAssembly record, @Param("example") RandomSamplingAssemblyExample example);

    int updateByPrimaryKeySelective(RandomSamplingAssembly record);

    int updateByPrimaryKey(RandomSamplingAssembly record);
}