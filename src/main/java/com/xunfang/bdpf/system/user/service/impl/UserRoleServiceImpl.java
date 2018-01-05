package com.xunfang.bdpf.system.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.xunfang.bdpf.system.user.entity.UserRole;
import com.xunfang.bdpf.system.user.entity.UserRoleExample;
import com.xunfang.bdpf.system.user.mapper.UserRoleMapper;
import com.xunfang.bdpf.system.user.service.UserRoleService;
/**
 * 
 * @ClassName UserRoleServiceImpl
 * @Description: 用户与角色关联表service层接口实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年5月31日 上午11:32:47
 * @version V1.0
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	//用户与角色关联接口
	private UserRoleMapper userRoleMapper;
	/**
	 * 
	  * @Title: insert
	  * @Description: 新增数据
	  * @param @param record
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	@Override
	public int insert(UserRole record) {
		// TODO Auto-generated method stub
		return userRoleMapper.insert(record);
	}
	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 查询数据
	  * @param @param example
	  * @param @return    设定文件
	  * @return List<UserRole>    返回类型
	  * @throws
	 */
	@Override
	public List<UserRole> selectByExample(UserRoleExample example) {
		// TODO Auto-generated method stub
		return userRoleMapper.selectByExample(example);
	}
	/**
	 * 
	  * @Title: deleteByExample
	  * @Description: 删除数据
	  * @param @param example
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	@Override
	public int deleteByExample(UserRoleExample example) {
		// TODO Auto-generated method stub
		return userRoleMapper.deleteByExample(example);
	}

}
