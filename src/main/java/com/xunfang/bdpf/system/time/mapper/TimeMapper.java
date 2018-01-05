package com.xunfang.bdpf.system.time.mapper;

import com.xunfang.bdpf.system.time.entity.Time;
import com.xunfang.bdpf.system.time.entity.TimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 
 * @ClassName TimeMapper
 * @Description: 实验时间设置接口
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年7月20日 下午2:36:40
 * @version V1.0
 */
public interface TimeMapper {
    long countByExample(TimeExample example);

    int deleteByExample(TimeExample example);

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
    int insert(Time record);

    int insertSelective(Time record);

    List<Time> selectByExample(TimeExample example);
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

    int updateByExampleSelective(@Param("record") Time record, @Param("example") TimeExample example);

    int updateByExample(@Param("record") Time record, @Param("example") TimeExample example);

    int updateByPrimaryKeySelective(Time record);
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
}