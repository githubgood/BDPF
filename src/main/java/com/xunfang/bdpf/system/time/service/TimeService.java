package com.xunfang.bdpf.system.time.service;

import com.xunfang.bdpf.system.time.entity.Time;

/**
 * 
 * @ClassName TimeService
 * @Description: 实验时间设置服务接口
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年6月2日 下午2:38:11
 * @version V1.0
 */
public interface TimeService { 

	/**
	 * 
	  * @Title: selectByPrimaryKey
	  * @Description: 根据id，查询数据
	  * @param @param id
	  * @param @return    设定文件
	  * @return Time    返回类型
	  * @throws
	 */
	Time selectByPrimaryKey(String id);
	
	/**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 更新数据
	  * @param @param record
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int updateByPrimaryKey(Time record);
	
	/**
	 * 
	  * @Title: insert
	  * @Description: 新增数据
	  * @param @param record
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int insert(Time record);
}
