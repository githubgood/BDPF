package com.xunfang.bdpf.system.role.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xunfang.bdpf.system.role.entity.Role;
import com.xunfang.bdpf.system.role.entity.RoleExample;

public interface RoleMapper {
    long countByExample(RoleExample example);
    /**
	 * 
	  * @Title: deleteByExample
	  * @Description: 根据examlpe对象删除数据
	  * @param @param example
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
    int deleteByExample(RoleExample example);
    /**
	 * 
	  * @Title: deleteByPrimaryKey
	  * @Description: 根据id删除数据
	  * @param @param id
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
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
    int insert(Role record);

    int insertSelective(Role record);
    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 查询数据
	  * @param @param example
	  * @param @return    设定文件
	  * @return List<Role>    返回类型
	  * @throws
	 */
    List<Role> selectByExample(RoleExample example);
    /**
     * 
      * @Title: selectByPrimaryKey
      * @Description: 根据id查询对象
      * @param @param id
      * @param @return    设定文件
      * @return Role    返回类型
      * @throws
     */
    Role selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);
    /**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 更新数据
	  * @param @param record
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
    int updateByPrimaryKey(Role record);
    /**
     * 
      * @Title: getRoleCount
      * @Description: 根据条件获取数据条数
      * @param @param param
      * @param @return    设定文件
      * @return long    返回类型
      * @throws
     */
    long getRoleCount(Map<String, Object> param);

    /**
     * 
      * @Title: queryRoleByPage
      * @Description: 据条件获取数据列表 
      * @param @param param
      * @param @return    设定文件
      * @return List<Role>    返回类型
      * @throws
     */
	List<Role> queryRoleByPage(Map<String, Object> param);
}