package com.xunfang.bdpf.system.time.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunfang.bdpf.system.time.entity.Time;
import com.xunfang.bdpf.system.time.mapper.TimeMapper;
import com.xunfang.bdpf.system.time.service.TimeService;
/**
 * 
 * @ClassName TimeServiceImpl
 * @Description: TODO(用一句话描述该文件做什么)
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年6月2日 下午2:37:59
 * @version V1.0
 */
@Service
public class TimeServiceImpl implements TimeService {

	@Autowired
	//实验时间设置接口
	private TimeMapper timeMapper;
	
	/**
	 * 
	  * @Title: selectByPrimaryKey
	  * @Description: 根据id，查询数据
	  * @param @param id
	  * @param @return    设定文件
	  * @return Time    返回类型
	  * @throws
	 */
	@Override
	public Time selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return timeMapper.selectByPrimaryKey(id);
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
	public int updateByPrimaryKey(Time record) {
		// TODO Auto-generated method stub
		return timeMapper.updateByPrimaryKey(record);
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
	public int insert(Time record) {
		// TODO Auto-generated method stub
		return timeMapper.insert(record);
	}

}
