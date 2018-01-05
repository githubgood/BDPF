package com.xunfang.bdpf.experiment.grade.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunfang.bdpf.experiment.grade.entity.StudentVirtual;
import com.xunfang.bdpf.experiment.grade.entity.StudentVirtualExample;
import com.xunfang.bdpf.experiment.grade.mapper.StudentVirtualMapper;
import com.xunfang.bdpf.experiment.grade.service.StudentVirtualService;

/**
 * 
 * @ClassName StudentVirtualServiceImpl
 * @Description:学生与虚拟机Service服务接口实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年6月7日 下午3:07:52
 * @version V1.0
 */

@Service
@Transactional
public class StudentVirtualServiceImpl implements StudentVirtualService {

	//学生虚拟机Mapper
	@Autowired
	private  StudentVirtualMapper studentVirtualMapper;
	
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @param example
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(StudentVirtualExample example) {
		return studentVirtualMapper.countByExample(example);
	}

	/**
     * 
      * @Title: deleteByExample
      * @Description: 根据条件删除多条数据
      *  @param example
      *  @return  int 返回类型
      * @throws
     */
	@Override
	public int deleteByExample(StudentVirtualExample example) {
		return studentVirtualMapper.deleteByExample(example);
	}

	/**
     * 
      * @Title: deleteByPrimaryKey
      * @Description: 根据条件删除单条数据
      *  @param id
      *  @return  int 返回类型
      * @throws
     */
	@Override
	public int deleteByPrimaryKey(String id) {
		return studentVirtualMapper.deleteByPrimaryKey(id);
	}

	/**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insert(StudentVirtual record) {
		return studentVirtualMapper.insert(record);
	}

	/**
     * 
      * @Title: insertSelective
      * @Description: 插入数据,不为null的字段
      *  @param record
      *  @return  int 返回类型
      * @throws
     */
	@Override
	public int insertSelective(StudentVirtual record) {
		return studentVirtualMapper.insertSelective(record);
	}

	 /**
     * 
      * @Title: selectByExample
      * @Description: 根据条件查询数据
      *  @param example
      *  @return  List<Group> 返回类型
      * @throws
     */
	@Override
	public List<StudentVirtual> selectByExample(StudentVirtualExample example) {
		return studentVirtualMapper.selectByExample(example);
	}

	 /**
     * 
      * @Title: selectByPrimaryKey
      * @Description: 根据主键查询数据
      *  @param id
      *  @return  Group 返回类型
      * @throws
     */
	@Override
	public StudentVirtual selectByPrimaryKey(String id) {
		return studentVirtualMapper.selectByPrimaryKey(id);
	}

	 /**
     * 
      * @Title: updateByExampleSelective
      * @Description: 按条件更新值不为null的字段
      *  @param record
      *  @param example
      *  @return  int 返回类型
      * @throws
     */
	@Override
	public int updateByExampleSelective(StudentVirtual record, StudentVirtualExample example) {
		return studentVirtualMapper.updateByExampleSelective(record, example);
	}

	 /**
     * 
      * @Title: updateByExample
      * @Description: 按条件更新
      *  @param record
      *  @param example
      *  @return  int 返回类型
      * @throws
     */
	@Override
	public int updateByExample(StudentVirtual record, StudentVirtualExample example) {
		return studentVirtualMapper.updateByExample(record, example);
	}

	 /**
     * 
      * @Title: updateByPrimaryKeySelective
      * @Description: 按条件更新,带主键且不为null的字段
      *  @param record
      *  @return  int 返回类型
      * @throws
     */
	@Override
	public int updateByPrimaryKeySelective(StudentVirtual record) {
		return studentVirtualMapper.updateByPrimaryKeySelective(record);
	}

	 /**
     * 
      * @Title: updateByPrimaryKey
      * @Description: 按主键更新
      *  @param record
      *  @return  int 返回类型
      * @throws
     */
	@Override
	public int updateByPrimaryKey(StudentVirtual record) {
		return studentVirtualMapper.updateByPrimaryKey(record);
	}

	
	 /**
     * 
      * @Title: selectByClassIdAndAccount
      * @Description: 根据条件查询数据
      * @param account String 账号
      * @param classId String 班级ID
      * @return  List<StudentVirtual> 返回类型
      * @throws
     */
	@Override
	public List<StudentVirtual> selectByClassIdAndAccount(String account,String groupId,List<String> classId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("account", account);
		param.put("groupId", groupId);
		param.put("classId", classId);
		return studentVirtualMapper.selectByClassIdAndAccount(param);
	}
}