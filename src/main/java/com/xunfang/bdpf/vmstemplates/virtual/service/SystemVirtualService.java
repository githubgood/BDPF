package com.xunfang.bdpf.vmstemplates.virtual.service;

import java.util.List;

import com.xunfang.bdpf.vmstemplates.virtual.entity.SystemVirtual;

/**
* @ClassName: SystemVirtualService
* @Description: 创建虚拟机记录接口
* @author lizhu
* @date 2017年11月31日 上午9:49:47
*
*/
public interface SystemVirtualService{
	
	/**
	 * 
	  * @Title: insertSystemVirtual
	  * @Description: 添加SystemVirtual数据
	  * @param @param 
	  * @param @return  
	  * @return int    返回类型
	  * @throws
	 */
	 int insertSystemVirtual(SystemVirtual record);
	 
	 /**
		 * 
		  * @Title: selectSystemVirtualByPrimaryKey
		  * @Description: 查询SystemVirtual数据
		  * @param @param 
		  * @param @return  
		  * @return SystemVirtual    返回类型
		  * @throws
		 */
	 List<SystemVirtual> selectSystemVirtualByPrimaryKey(String userId);
	 
	 /**
		 * 
		  * @Title: deleteSystemVirtualByPrimaryKey
		  * @Description: 删除SystemVirtual数据
		  * @param @param 
		  * @param @return  
		  * @return int    返回类型
		  * @throws
		 */
	 int deleteSystemVirtualByPrimaryKey(String id);
	 
	 
	 /**
		 * 
		  * @Title: deleteSystemVirtualByPrimaryKey
		  * @Description: 根据虚拟机id删除SystemVirtual数据
		  * @param @param 
		  * @param @return  
		  * @return int    返回类型
		  * @throws
		 */
	 int deleteSystemVirtualByVirtualId(String id);
	 
	 /**
		 * 
		  * @Title: updateSystemVirtual
		  * @Description: 更新SystemVirtual数据
		  * @param @param 
		  * @param @return  
		  * @return int    返回类型
		  * @throws
		 */
		 int updateSystemVirtual(SystemVirtual record);
	 
}