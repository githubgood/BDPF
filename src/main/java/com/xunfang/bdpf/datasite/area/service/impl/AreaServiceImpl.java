package com.xunfang.bdpf.datasite.area.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunfang.bdpf.datasite.area.entity.Area;
import com.xunfang.bdpf.datasite.area.mapper.AreaMapper;
import com.xunfang.bdpf.datasite.area.service.AreaService;
/**
 * 
 * @ClassName AreaServiceImpl
 * @Description: Area接口实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author lizhu
 * @date 2017年6月6日 上午11:20:44
 * @version V1.0
 */
@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaMapper areaMapper;
	/**
	 * 
	  * @Title: selectAreaByPrimaryKey
	  * @Description: 查询site数据
	  * @param @param 
	  * @param @return  
	  * @return List<Area>    返回类型
	  * @throws
	 */
	@Override
	public List<Area> selectAreaByPrimaryKey(String keywords,int skip,int limit,String id,String type) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keywords", keywords);
		param.put("skip", skip);
		param.put("limit", limit);
		param.put("id", id);
		param.put("type", type);
		return areaMapper.selectAreaByPrimaryKey(param);
	}
	@Override
	public int deleteAreaByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return areaMapper.deleteAreaByPrimaryKey(id);
	}
	@Override
	public long getAreaCount(String id,String keywords,String type) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keywords", keywords);
		param.put("id", id);
		param.put("type", type);
		return areaMapper.getAreaCount(param);
	}
	@Override
	public int insertArea(Area area) {
		// TODO Auto-generated method stub
		return areaMapper.insertArea(area);
	}
	@Override
	public Area selectAreaById(String id,String type) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("type", type);
		return areaMapper.selectAreaById(param);
	}
	@Override
	public int updateArea(Area area) {
		// TODO Auto-generated method stub
		return areaMapper.updateArea(area);
	}
	@Override
	public int deleteAreaBySiteId(String id) {
		// TODO Auto-generated method stub
		return areaMapper.deleteAreaBySiteId(id);
	}
	@Override
	public List<Area> selectAreaBySiteId(String id) {
		// TODO Auto-generated method stub
		return areaMapper.selectAreaBySiteId(id);
	}
	@Override
	public List<Area> selectAreaByTitle(String title, String type) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("title", title);
		param.put("type", type);
		return areaMapper.selectAreaByTitle(param);
	}

	
}
