package com.xunfang.bdpf.system.competence.service;

import java.util.List;

import com.xunfang.bdpf.system.competence.entity.Competence;
import com.xunfang.bdpf.system.competence.entity.CompetenceExample;
/**
 * 
 * @ClassName CompetenceService
 * @Description: 权限管理服务接口
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年7月19日 下午3:15:30
 * @version V1.0
 */

public interface CompetenceService {
	/**
	 * 
	* @Title: selectGroupByParentId
	* @Description: TODO(根据parentId分组)
	* @return
	* List<String>
	 */
	List<String> selectGroupByParentId();
	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 查询数据
	  * @param @param example
	  * @param @return    设定文件
	  * @return List<Competence>    返回类型
	  * @throws
	 */
	List<Competence> selectByExample(CompetenceExample example);
	
	/**
	 * 
	  * @Title: getCompCount
	  * @Description: 根据关键字获取数据长度
	  * @param @param keywords
	  * @param @return    设定文件
	  * @return long    返回类型
	  * @throws
	 */
	long getCompCount(String keywords);
	/**
	 * 
	  * @Title: queryCompByPage
	  * @Description: 根据条件获取分页数据
	  * @param @param keywords
	  * @param @param skip
	  * @param @param limit
	  * @param @return    设定文件
	  * @return List<Competence>    返回类型
	  * @throws
	 */
	List<Competence> queryCompByPage(String keywords, int skip, int limit);

	/**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 更新数据
	  * @param @param record
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int updateByPrimaryKey(Competence record);
	/**
	 * 
	  * @Title: insert
	  * @Description: 新增数据
	  * @param @param record
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int insert(Competence record);
	/**
	 * 
	  * @Title: selectByPrimaryKey
	  * @Description: 根据id查询数据
	  * @param @param id
	  * @param @return    设定文件
	  * @return Competence    返回类型
	  * @throws
	 */
	Competence selectByPrimaryKey(String id);
	/**
	 * 
	  * @Title: deleteByExample
	  * @Description: 删除数据
	  * @param @param example
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int deleteByExample(CompetenceExample example);
	/**
	 * 
	  * @Title: selcetCompListByAccount
	  * @Description: 查询数据
	  * @param @param account
	  * @param @return    设定文件
	  * @return List<Competence>    返回类型
	  * @throws
	 */
	List<Competence> selcetCompListByAccount(String account);
}
