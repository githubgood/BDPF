package com.xunfang.bdpf.vmstemplates.virtual.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xunfang.bdpf.datasite.area.entity.Area;
import com.xunfang.bdpf.vmstemplates.virtual.entity.SystemVirtual;

public interface SystemVirtualMapper {
    
    int deleteSystemVirtualByPrimaryKey(String id);
    
    int deleteSystemVirtualByVirtualId(@Param("id")String id);

    int insertSystemVirtual(SystemVirtual record);

    List<SystemVirtual> selectSystemVirtualByPrimaryKey(String id);
   
    int updateSystemVirtual(SystemVirtual record);
   
}