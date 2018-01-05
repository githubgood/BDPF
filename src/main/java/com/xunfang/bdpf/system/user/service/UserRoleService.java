package com.xunfang.bdpf.system.user.service;

import java.util.List;

import com.xunfang.bdpf.system.user.entity.UserRole;
import com.xunfang.bdpf.system.user.entity.UserRoleExample;
/**
 * 
 * @ClassName UserRoleService
 * @Description: 用户与角色关联表service层接口
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年5月31日 下午2:20:27
 * @version V1.0
 */
public interface UserRoleService {

	/**
	 * 
	  * @Title: insert
	  * @Description: 新增数据
	  * @param @param record
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int insert(UserRole record);
	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 查询数据
	  * @param @param example
	  * @param @return    设定文件
	  * @return List<UserRole>    返回类型
	  * @throws
	 */
	List<UserRole> selectByExample(UserRoleExample example);
	/**
	 * 
	  * @Title: deleteByExample
	  * @Description: 删除数据
	  * @param @param example
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int deleteByExample(UserRoleExample example);
}
