package com.xunfang.bdpf.system.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.bdpf.system.user.entity.UserExample;
import com.xunfang.utils.Feedback;
/**
 * 
 * @ClassName UserService
 * @Description: 用户信息接口
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年5月26日 上午11:19:56
 * @version V1.0
 */
public interface UserService {

	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据UserExample对象，查询数据
	  * @param @param example
	  * @param @return    设定文件
	  * @return List<User>    返回类型
	  * @throws
	 */
	List<User> selectByExample(UserExample example);
	
	/**
	 * 
	  * @Title: getUserCount
	  * @Description: 根据关键字搜索获取数据总条数
	  * @param @param keywords
	  * @param @return    设定文件
	  * @return long    返回类型
	  * @throws
	 */
	long getUserCount(String keywords);
	/**
	  * @Title: queryUserByPage
	  * @Description: 分页请求数据，根据关键字搜索
	  * @param @param keywords
	  * @param @param skip
	  * @param @param limit
	  * @param @return    设定文件
	  * @return List<User>    返回类型
	  * @throws
	 */
	List<User> queryUserByPage(String keywords, int skip, int limit);
	/**
	  * @Title: insert
	  * @Description: 新增数据
	  * @param @param record
	  * @param @return    设定文件
	  * @return int    大于0，表示操作成功。
	  * @throws
	 */
	int insert(User record);
	/**
	  * @Title: selectByPrimaryKey
	  * @Description: 根据id查询
	  * @param @param id
	  * @param @return    设定文件
	  * @return User    返回类型
	  * @throws
	 */
	User selectByPrimaryKey(String id);
	
	/**
	  * @Title: selectByAccount
	  * @Description: 根据学号查询
	  * @param @param account
	  * @param @return    设定文件
	  * @return User    返回类型
	  * @throws
	 */
	User selectByAccount(String account);
	
	/**
	  * @Title: updateByPrimaryKey
	  * @Description: 根据user对象，更新数据
	  * @param @param record
	  * @param @return    设定文件
	  * @return int    大于0，表示操作成功。
	  * @throws
	 */
	int updateByPrimaryKey(User record);
	/**
	  * @Title: deleteByPrimaryKey
	  * @Description:  根据id删除
	  * @param @param id
	  * @param @return    大于0，表示操作成功。
	  * @return int    返回类型
	  * @throws
	 */
	int deleteByPrimaryKey(String id);
	/**
	  * @Title: deleteByExample
	  * @Description: 根据example对象,指定条件删除数据
	  * @param @param example
	  * @param @return    大于0，表示操作成功。
	  * @return int    返回类型
	  * @throws
	 */
	int deleteByExample(UserExample example);
	
	/**
	 * 
	  * @Title: queryUserListByClasId
	  * @Description: 通过班级list获取班级表和学生列表的map
	  *  @param param
	  *  @return  Map<Clas,List<User>> 返回类型
	  * @throws
	 */
	Map<User, List<User>>  queryUserListByClasId(String[] classIds);
	
	/**
	  * @Title: authLogin
	  * @Description: 根据账号和密码判断该用户是否存在
	  * @param  account String   账号
	  * @param password String   密码
	  * @return int    大于0，表示操作该用户存在。
	  * @throws
	 */
	int authLogin(String account,String password);
	/**
	 * 
	  * @Title: getStudentCount
	  * @Description: 根据关键字搜索获取数据总条数
	  * @param keywords
	  * @param @return    设定文件
	  * @return long    返回类型
	  * @throws
	 */
	long getStudentCount(String keywords,String createUser);
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
	List<User> queryStudentByPage(String keywords, int skip, int limit,String createUser);
	
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
	int selectClasCount(String clasId);
	
	/**
	  * @Title: batchImport
	  * @Description: TODO(用一句话描述该文件做什么)
	  *  @param fileName
	  *  @param Mfile
	  *  @param type
	  *  @return  Feedback 返回类型
	  * @throws
	  */
	Feedback batchImport(String fileName, MultipartFile Mfile, String type,int max,long number);

	
	/**
	  * @Title: importUserInfo
	  * @Description: TODO(用一句话描述该文件做什么)
	  *  @param userList
	  *  @return  boolean 返回类型
	  * @throws
	  */
	boolean importUserInfo(List<User> userList);
}
