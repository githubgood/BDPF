package com.xunfang.bdpf.system.competence.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xunfang.bdpf.system.competence.entity.Competence;
import com.xunfang.bdpf.system.competence.entity.CompetenceExample;

public interface CompetenceMapper {
	long countByExample(CompetenceExample example);
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

	int deleteByPrimaryKey(String id);
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

	int insertSelective(Competence record);
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
	  * @Title: selcetCompListByAccount
	  * @Description: 查询数据
	  * @param @param account
	  * @param @return    设定文件
	  * @return List<Competence>    返回类型
	  * @throws
	 */
	List<Competence> selcetCompListByAccount(Map<String, Object> param);

	int updateByExampleSelective(@Param("record") Competence record, @Param("example") CompetenceExample example);

	int updateByExample(@Param("record") Competence record, @Param("example") CompetenceExample example);

	int updateByPrimaryKeySelective(Competence record);
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
	 * @Title: selectGroupByParentId
	 * @Description: TODO(根据parentId分组)
	 * @return List<String>
	 */
	List<String> selectGroupByParentId();
	/**
	 * 
	  * @Title: getCompCount
	  * @Description: 根据关键字获取数据长度
	  * @param @param keywords
	  * @param @return    设定文件
	  * @return long    返回类型
	  * @throws
	 */
	long getCompCount(Map<String, Object> param);
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
	List<Competence> queryCompByPage(Map<String, Object> param);
}