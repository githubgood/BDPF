package com.xunfang.bdpf.datasite.resources.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunfang.bdpf.datasite.resources.entity.Resources;
import com.xunfang.bdpf.datasite.resources.mapper.ResourcesMapper;
import com.xunfang.bdpf.datasite.resources.service.ResourcesService;
/**
 * 
 * @ClassName ItemServiceImpl
 * @Description: Item接口实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author lizhu
 * @date 2017年6月6日 上午11:20:44
 * @version V1.0
 */
@Service
public class ResourcesServiceImpl implements ResourcesService {
	@Autowired
	private ResourcesMapper ResourcesMapper;

	@Override
	public int insertResources(Resources resources) {
		// TODO Auto-generated method stub
		return ResourcesMapper.insert(resources);
	}

	@Override
	public List<Resources> selectByResByAreaId(String id,String type) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("type", type);
		return ResourcesMapper.selectByResByAreaId(param);
	}

	@Override
	public int deleteByAreaId(String id) {
		// TODO Auto-generated method stub
		return ResourcesMapper.deleteByAreaId(id);
	}
	
	

	
}
