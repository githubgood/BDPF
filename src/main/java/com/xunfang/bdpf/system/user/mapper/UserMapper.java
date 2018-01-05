package com.xunfang.bdpf.system.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.bdpf.system.user.entity.UserExample;

public interface UserMapper {
	long countByExample(UserExample example);
	/**
	  * @Title: deleteByExample
	  * @Description: 根据example对象,指定条件删除数据
	  * @param example
	  * @param @return    大于0，表示操作成功。
	  * @return int    返回类型
	  * @throws
	 */
	int deleteByExample(UserExample example);
	/**
	  * @Title: deleteByPrimaryKey
	  * @Description:  根据id删除
	  * @param id
	  * @param @return    大于0，表示操作成功。
	  * @return int    返回类型
	  * @throws
	 */
	int deleteByPrimaryKey(String id);
	/**
	  * @Title: insert
	  * @Description: 新增数据
	  * @param record
	  * @param @return    设定文件
	  * @return int    大于0，表示操作成功。
	  * @throws
	 */
	int insert(User record);

	int insertSelective(User record);
	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据UserExample对象，查询数据
	  * @param example
	  * @param @return    设定文件
	  * @return List<User>    返回类型
	  * @throws
	 */
	List<User> selectByExample(UserExample example);
	/**
	  * @Title: selectByPrimaryKey
	  * @Description: 根据id查询
	  * @param id
	  * @param @return    设定文件
	  * @return User    返回类型
	  * @throws
	 */
	User selectByPrimaryKey(String id);
	
	/**
	  * @Title: selectByAccount
	  * @Description: 根据学号查询
	  * @param account
	  * @param @return    设定文件
	  * @return User    返回类型
	  * @throws
	 */
	User selectByAccount(String account);

	int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

	int updateByExample(@Param("record") User record, @Param("example") UserExample example);

	int updateByPrimaryKeySelective(User record);
	/**
	  * @Title: updateByPrimaryKey
	  * @Description: 根据user对象，更新数据
	  * @param record
	  * @param @return    设定文件
	  * @return int    大于0，表示操作成功。
	  * @throws
	 */
	int updateByPrimaryKey(User record);
	/**
	 * 
	  * @Title: getUserCount
	  * @Description: 根据关键字搜索获取数据总条数
	  * @param keywords
	  * @param @return    设定文件
	  * @return long    返回类型
	  * @throws
	 */
	long getUserCount(Map<String, Object> param);
	/**
	  * @Title: queryUserByPage
	  * @Description: 分页请求数据，根据关键字搜索
	  * @param keywords
	  * @param skip
	  * @param limit
	  * @param @return    设定文件
	  * @return List<User>    返回类型
	  * @throws
	 */
	List<User> queryUserByPage(Map<String, Object> param);
	/**
	  * @Title: queryUserListByClasId
	  * @Description: 通过班级list获取班级表和学生列表的map
	  * @param param
	  * @param @return    大于0，表示操作成功。
	  * @return int    返回类型
	  * @throws
	 */
	List<User>  queryUserListByClasId(String[] classIds);
	/**
	  * @Title: authLogin
	  * @Description: 根据账号和密码判断该用户是否存在
	  * @param  account String   账号
	  * @param password String   密码
	  * @return int    大于0，表示操作该用户存在。
	  * @throws
	 */
	int authLogin(Map<String, Object> param);
	/**
	 * 
	  * @Title: getStudentCount
	  * @Description: 根据关键字搜索获取数据总条数
	  * @param keywords
	  * @param @return    设定文件
	  * @return long    返回类型
	  * @throws
	 */
	long getStudentCount(Map<String, Object> param);
	/**
	  * @Title: queryStudentByPage
	  * @Description: 分页请求数据，根据关键字搜索
	  * @param keywords
	  * @param skip
	  * @param limit
	  * @param @return    设定文件
	  * @return List<User>    返回类型
	  * @throws
	 */
	List<User> queryStudentByPage(Map<String, Object> param);
	/**
	 * 
	  * @Title: greadIds
	  * @Description: 删除之前做判断
	  * @param @return    设定文件
	  * @return List<User>    返回类型
	  * @throws
	 */
	List<User> greadIds();
	/**
	 * 
	  * @Title: selectClasCount
	  * @Description: 获取一个班级下有多少老师。
	  * @param @param clasId
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int selectClasCount(@Param("clasId") String clasId);
}