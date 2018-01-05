package com.xunfang.bdpf.system.role.service;

import java.util.List;

import com.xunfang.bdpf.system.role.entity.Role;
import com.xunfang.bdpf.system.role.entity.RoleExample;

/**
 * 
 * @ClassName RoleService
 * @Description: 角色管理接口
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年5月31日 上午10:18:25
 * @version V1.0
 */
public interface RoleService {

	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 查询数据
	  * @param @param example
	  * @param @return    设定文件
	  * @return List<Role>    返回类型
	  * @throws
	 */
	List<Role> selectByExample(RoleExample example);
	/**
	 * 
	  * @Title: getRoleCount
	  * @Description: 根据关键字获取数据条数
	  * @param @param keywords
	  * @param @return    设定文件
	  * @return long    返回类型
	  * @throws
	 */
	long getRoleCount(String keywords);
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
	List<Role> queryRoleByPage(String keywords, int skip, int limit);
	/**
	 * 
	  * @Title: insert
	  * @Description: 新增数据
	  * @param @param record
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int insert(Role record);
	/**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 更新数据
	  * @param @param record
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int updateByPrimaryKey(Role record);
	/**
	 * 
	  * @Title: deleteByExample
	  * @Description: 根据examlpe对象删除数据
	  * @param @param example
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int deleteByExample(RoleExample example);
	/**
	 * 
	  * @Title: deleteByPrimaryKey
	  * @Description: 根据id删除数据
	  * @param @param id
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
    int deleteByPrimaryKey(String id);
    
    /**
     * 
      * @Title: selectByPrimaryKey
      * @Description: 根据id查询对象
      * @param @param id
      * @param @return    设定文件
      * @return Role    返回类型
      * @throws
     */
    Role selectByPrimaryKey(String id);
}
