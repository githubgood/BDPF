package com.xunfang.bdpf.vmstemplates.virtual.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunfang.bdpf.vmstemplates.virtual.entity.SystemVirtual;
import com.xunfang.bdpf.vmstemplates.virtual.mapper.SystemVirtualMapper;
import com.xunfang.bdpf.vmstemplates.virtual.service.SystemVirtualService;

/**
 * 
 * @ClassName SystemVirtualServiceImpl
 * @Description: SystemVirtual接口实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author lizhu
 * @date 2017年11月31日 上午10:01:34
 * @version V1.0
 */
@Service
public class SystemVirtualServiceImpl implements SystemVirtualService{

	@Autowired
	private SystemVirtualMapper systemVirtualMapper;
	
	@Override
	public int insertSystemVirtual(SystemVirtual record) {
		// TODO Auto-generated method stub
		return systemVirtualMapper.insertSystemVirtual(record);
	}

	@Override
	public List<SystemVirtual> selectSystemVirtualByPrimaryKey(String userId) {
		// TODO Auto-generated method stub
		return systemVirtualMapper.selectSystemVirtualByPrimaryKey(userId);
	}

	@Override
	public int deleteSystemVirtualByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return systemVirtualMapper.deleteSystemVirtualByPrimaryKey(id);
	}

	@Override
	public int deleteSystemVirtualByVirtualId(String id) {
		// TODO Auto-generated method stub
		return systemVirtualMapper.deleteSystemVirtualByVirtualId(id);
	}

	@Override
	public int updateSystemVirtual(SystemVirtual record) {
		// TODO Auto-generated method stub
		return systemVirtualMapper.updateSystemVirtual(record);
	}
	
}