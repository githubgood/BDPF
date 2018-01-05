package com.xunfang.bdpf.system.role.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunfang.bdpf.system.role.entity.RoleCompetence;
import com.xunfang.bdpf.system.role.entity.RoleCompetenceExample;
import com.xunfang.bdpf.system.role.mapper.RoleCompetenceMapper;
import com.xunfang.bdpf.system.role.service.RoleCompetenceService;
/**
 * 
 * @ClassName RoleCompetenceServiceImpl
 * @Description: 角色与权限的关联管理服务
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年6月2日 上午9:16:38
 * @version V1.0
 */
@Service
public class RoleCompetenceServiceImpl implements RoleCompetenceService {

	@Autowired
	//角色与权限的关联管理接口
	private RoleCompetenceMapper roleCompetenceMapper;
	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 查询数据
	  * @param @param example
	  * @param @return    设定文件
	  * @return List<RoleCompetence>    返回类型
	  * @throws
	 */
	@Override
	public List<RoleCompetence> selectByExample(RoleCompetenceExample example) {
		// TODO Auto-generated method stub
		return roleCompetenceMapper.selectByExample(example);
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
	public int deleteByExample(RoleCompetenceExample example) {
		// TODO Auto-generated method stub
		return roleCompetenceMapper.deleteByExample(example);
	}
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
	public int insert(RoleCompetence record) {
		// TODO Auto-generated method stub
		return roleCompetenceMapper.insert(record);
	}

}
