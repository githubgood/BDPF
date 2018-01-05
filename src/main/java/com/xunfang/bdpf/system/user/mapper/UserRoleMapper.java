package com.xunfang.bdpf.system.user.mapper;

import com.xunfang.bdpf.system.user.entity.UserRole;
import com.xunfang.bdpf.system.user.entity.UserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    long countByExample(UserRoleExample example);
    /**
	 * 
	  * @Title: deleteByExample
	  * @Description: 删除数据
	  * @param @param example
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
    int deleteByExample(UserRoleExample example);
    /**
	 * 
	  * @Title: deleteByExample
	  * @Description: 删除数据
	  * @param @param example
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
    int insert(UserRole record);

    int insertSelective(UserRole record);
    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 查询数据
	  * @param @param example
	  * @param @return    设定文件
	  * @return List<UserRole>    返回类型
	  * @throws
	 */
    List<UserRole> selectByExample(UserRoleExample example);

    UserRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}