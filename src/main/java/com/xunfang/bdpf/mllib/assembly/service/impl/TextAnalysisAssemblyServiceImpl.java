package com.xunfang.bdpf.mllib.assembly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunfang.bdpf.mllib.assembly.entity.TextAnalysisAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.TextAnalysisAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.mapper.TextAnalysisAssemblyMapper;
import com.xunfang.bdpf.mllib.assembly.service.TextAnalysisAssemblyService;

/**
 * 
 * @ClassName TextAnalysisAssemblyServiceImpl
 * @Description: 文本分析Service实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年11月7日 上午10:50:09
 * @version V1.0
 */
@Service
@Transactional
public class TextAnalysisAssemblyServiceImpl implements TextAnalysisAssemblyService {

	@Autowired
	private TextAnalysisAssemblyMapper textAnalysisAssemblyMapper;

	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(TextAnalysisAssemblyExample example) {
		return textAnalysisAssemblyMapper.countByExample(example);
	}

	/**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int deleteByExample(TextAnalysisAssemblyExample example) {
		return textAnalysisAssemblyMapper.deleteByExample(example);
	}

	/**
 	 * 
 	  * @Title: deleteByPrimaryKey
 	  * @Description: 根据条件删除单条数据
 	  *  @return  int 返回类型
 	  * @throws
 	 */
	@Override
	public int deleteByPrimaryKey(String id) {
		return textAnalysisAssemblyMapper.deleteByPrimaryKey(id);
	}

	/**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insert(TextAnalysisAssembly record) {
		return textAnalysisAssemblyMapper.insert(record);
	}

	/**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insertSelective(TextAnalysisAssembly record) {
		return textAnalysisAssemblyMapper.insertSelective(record);
	}

	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<TextAnalysisAssembly> 返回类型
	  * @throws
	 */
	@Override
	public List<TextAnalysisAssembly> selectByExample(TextAnalysisAssemblyExample example) {
		return textAnalysisAssemblyMapper.selectByExample(example);
	}

	/**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  TextAnalysisAssembly 返回类型
   	  * @throws
   	 */
	@Override
	public TextAnalysisAssembly selectByPrimaryKey(String id) {
		return textAnalysisAssemblyMapper.selectByPrimaryKey(id);
	}

	/**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExampleSelective(TextAnalysisAssembly record, TextAnalysisAssemblyExample example) {
		return textAnalysisAssemblyMapper.updateByExampleSelective(record, example);
	}

	/**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByExample(TextAnalysisAssembly record, TextAnalysisAssemblyExample example) {
		return textAnalysisAssemblyMapper.updateByExample(record, example);
	}

	/**
   	 * 
   	  * @Title: updateByPrimaryKeySelective
   	  * @Description: 按条件更新,带主键且不为null的字段
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByPrimaryKeySelective(TextAnalysisAssembly record) {
		return textAnalysisAssemblyMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKey(TextAnalysisAssembly record) {
		return textAnalysisAssemblyMapper.updateByPrimaryKey(record);
	}

	/**
     * 
      * @Title: batchInsertTextAnalysisAssembly
      * @Description: 批量增加
      *  @param list
      *  @return int 返回类型
      * @throws
     */
	@Override
	public int batchInsertTextAnalysisAssembly(List<TextAnalysisAssembly> list) {
		return textAnalysisAssemblyMapper.batchInsertTextAnalysisAssembly(list);
	}

}
