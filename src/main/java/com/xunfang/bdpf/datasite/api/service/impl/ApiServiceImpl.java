package com.xunfang.bdpf.datasite.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunfang.bdpf.datasite.api.entity.Api;
import com.xunfang.bdpf.datasite.api.mapper.ApiMapper;
import com.xunfang.bdpf.datasite.api.service.ApiService;
import com.xunfang.bdpf.datasite.area.entity.Area;
/**
 * 
 * @ClassName ApiServiceImpl
 * @Description: api接口实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author lizhu
 * @date 2017年6月16日 上午11:20:44
 * @version V1.0
 */
@Service
public class ApiServiceImpl implements ApiService {

	@Autowired
	private ApiMapper apiMapper;

	@Override
	public int insertApi(Api api) {
		// TODO Auto-generated method stub
		return apiMapper.insert(api);
	}

	@Override
	public List<Api> selectApiByAreaId(String id) {
		// TODO Auto-generated method stub
		
		return apiMapper.selectApiByAreaId(id);
	}

	@Override
	public int updataApi(Api api) {
		// TODO Auto-generated method stub
		return apiMapper.updataApi(api);
	}

	@Override
	public int deleteApiByAreaId(String id) {
		// TODO Auto-generated method stub
		return apiMapper.deleteApiByAreaId(id);
	}

}
