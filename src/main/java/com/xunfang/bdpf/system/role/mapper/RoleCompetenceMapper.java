package com.xunfang.bdpf.system.role.mapper;

import com.xunfang.bdpf.system.role.entity.RoleCompetence;
import com.xunfang.bdpf.system.role.entity.RoleCompetenceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleCompetenceMapper {
    long countByExample(RoleCompetenceExample example);

    int deleteByExample(RoleCompetenceExample example);

    int deleteByPrimaryKey(String id);

    int insert(RoleCompetence record);

    int insertSelective(RoleCompetence record);

    List<RoleCompetence> selectByExample(RoleCompetenceExample example);

    RoleCompetence selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RoleCompetence record, @Param("example") RoleCompetenceExample example);

    int updateByExample(@Param("record") RoleCompetence record, @Param("example") RoleCompetenceExample example);

    int updateByPrimaryKeySelective(RoleCompetence record);

    int updateByPrimaryKey(RoleCompetence record);
}