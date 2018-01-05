package com.xunfang.bdpf.system.competence.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunfang.bdpf.system.competence.entity.Competence;
import com.xunfang.bdpf.system.competence.entity.CompetenceExample;
import com.xunfang.bdpf.system.competence.mapper.CompetenceMapper;
import com.xunfang.bdpf.system.competence.service.CompetenceService;
/**
 * 
 * @ClassName CompetenceServiceImpl
 * @Description: 权限管理服务
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年6月1日 下午4:49:39
 * @version V1.0
 */
@Service
public class CompetenceServiceImpl implements CompetenceService {

	@Autowired
	//权限管理服务接口
	private CompetenceMapper CompetenceMapper;
	/**
	 * 
	* @Title: selectGroupByParentId
	* @Description: TODO(根据parentId分组)
	* @return
	* List<String>
	 */
	@Override
	public List<String> selectGroupByParentId() {
		// TODO Auto-generated method stub
		return CompetenceMapper.selectGroupByParentId();
	}
	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 查询数据
	  * @param @param example
	  * @param @return    设定文件
	  * @return List<Competence>    返回类型
	  * @throws
	 */
	@Override
	public List<Competence> selectByExample(CompetenceExample example) {
		// TODO Auto-generated method stub
		return CompetenceMapper.selectByExample(example);
	}
	/**
	 * 
	  * @Title: getCompCount
	  * @Description: 根据关键字获取数据长度
	  * @param @param keywords
	  * @param @return    设定文件
	  * @return long    返回类型
	  * @throws
	 */
	@Override
	public long getCompCount(String keywords) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keywords", keywords);
		return CompetenceMapper.getCompCount(param);
	}
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
	@Override
	public List<Competence> queryCompByPage(String keywords, int skip, int limit) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keywords", keywords);
		param.put("skip", skip);
		param.put("limit", limit);
		return CompetenceMapper.queryCompByPage(param);
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
	public int updateByPrimaryKey(Competence record) {
		// TODO Auto-generated method stub
		return CompetenceMapper.updateByPrimaryKey(record);
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
	public int insert(Competence record) {
		// TODO Auto-generated method stub
		return CompetenceMapper.insert(record);
	}
	/**
	 * 
	  * @Title: selectByPrimaryKey
	  * @Description: 根据id查询数据
	  * @param @param id
	  * @param @return    设定文件
	  * @return Competence    返回类型
	  * @throws
	 */
	@Override
	public Competence selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return CompetenceMapper.selectByPrimaryKey(id);
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
	public int deleteByExample(CompetenceExample example) {
		// TODO Auto-generated method stub
		return CompetenceMapper.deleteByExample(example);
	}
	/**
	 * 
	  * @Title: selcetCompListByAccount
	  * @Description: 查询数据
	  * @param @param account
	  * @param @return    设定文件
	  * @return List<Competence>    返回类型
	  * @throws
	 */
	@Override
	public List<Competence> selcetCompListByAccount(String account) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("accounts", account);
		return CompetenceMapper.selcetCompListByAccount(param);
	}

}
