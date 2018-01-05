package com.xunfang.bdpf.system.role.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunfang.bdpf.system.role.entity.Role;
import com.xunfang.bdpf.system.role.entity.RoleExample;
import com.xunfang.bdpf.system.role.mapper.RoleMapper;
import com.xunfang.bdpf.system.role.service.RoleService;
/**
 * 
 * @ClassName RoleServiceImpl
 * @Description: 角色管理接口实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年5月31日 上午10:21:33
 * @version V1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 查询数据
	  * @param @param example
	  * @param @return    设定文件
	  * @return List<Role>    返回类型
	  * @throws
	 */
	@Override
	public List<Role> selectByExample(RoleExample example) {
		// TODO Auto-generated method stub
		return roleMapper.selectByExample(example);
	}
	
	/**
	 * 
	  * @Title: getRoleCount
	  * @Description: 根据关键字获取数据条数
	  * @param @param keywords
	  * @param @return    设定文件
	  * @return long    返回类型
	  * @throws
	 */
	@Override
	public long getRoleCount(String keywords) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keywords", keywords);
		return roleMapper.getRoleCount(param);
	}
	/**
	 * 
	  * @Title: queryRoleByPage
	  * @Description: 根据关键字和分页来获取数据
	  * @param @param keywords  关键字
	  * @param @param skip      从第几条开始查询
	  * @param @param limit     每页显示多少条数据
	  * @param @return    设定文件
	  * @return List<Role>    返回类型
	  * @throws
	 */
	@Override
	public List<Role> queryRoleByPage(String keywords, int skip, int limit) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keywords", keywords);
		param.put("skip", skip);
		param.put("limit", limit);
		return roleMapper.queryRoleByPage(param);
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
	public int insert(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.insert(record);
	}
	/**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 更新数据
	  * @param @param record
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKey(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.updateByPrimaryKey(record);
	}
	/**
	 * 
	  * @Title: deleteByExample
	  * @Description: 根据examlpe对象删除数据
	  * @param @param example
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	@Override
	public int deleteByExample(RoleExample example) {
		// TODO Auto-generated method stub
		return roleMapper.deleteByExample(example);
	}
	/**
	 * 
	  * @Title: deleteByPrimaryKey
	  * @Description: 根据id删除数据
	  * @param @param id
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return roleMapper.deleteByPrimaryKey(id);
	}
	
	/**
     * 
      * @Title: selectByPrimaryKey
      * @Description: 根据id查询对象
      * @param @param id
      * @param @return    设定文件
      * @return Role    返回类型
      * @throws
     */
	@Override
	public Role selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return roleMapper.selectByPrimaryKey(id);
	}

}
