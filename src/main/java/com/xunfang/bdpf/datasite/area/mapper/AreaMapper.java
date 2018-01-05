package com.xunfang.bdpf.datasite.area.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xunfang.bdpf.datasite.area.entity.Area;
import com.xunfang.bdpf.datasite.item.entity.Item;
import com.xunfang.bdpf.datasite.item.entity.ItemExample;

public interface AreaMapper {
	
	List<Area> selectAreaByPrimaryKey(Map<String, Object> param);
	
	List<Area> selectAreaByTitle(Map<String, Object> param);
	
	List<Area> selectAreaBySiteId(@Param("id") String id);
    
	int deleteAreaByPrimaryKey(String id);
	
	int deleteAreaBySiteId(String id);
	
	int insertArea(Area area);
	
	long getAreaCount(Map<String, Object> param);
	
	Area selectAreaById(Map<String, Object> param);
	
	int updateArea(@Param("record") Area area);
	
}