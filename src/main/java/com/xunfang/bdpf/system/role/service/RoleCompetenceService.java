package com.xunfang.bdpf.system.role.service;

import java.util.List;

import com.xunfang.bdpf.system.role.entity.RoleCompetence;
import com.xunfang.bdpf.system.role.entity.RoleCompetenceExample;

/**
 * 
 * @ClassName RoleCompetenceService
 * @Description: 角色与权限的关联管理服务接口
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年6月2日 上午9:16:07
 * @version V1.0
 */
public interface RoleCompetenceService {

	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 查询数据
	  * @param @param example
	  * @param @return    设定文件
	  * @return List<RoleCompetence>    返回类型
	  * @throws
	 */
	List<RoleCompetence> selectByExample(RoleCompetenceExample example);
	
	/**
	 * 
	  * @Title: deleteByExample
	  * @Description: 删除数据
	  * @param @param example
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int deleteByExample(RoleCompetenceExample example);
	
	/**
	 * 
	  * @Title: insert
	  * @Description: 新增数据
	  * @param @param record
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int insert(RoleCompetence record);
}
