package com.xunfang.bdpf.system.user.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.util.TextUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xunfang.bdpf.experiment.clas.entity.Clas;
import com.xunfang.bdpf.experiment.clas.entity.ClasExample;
import com.xunfang.bdpf.experiment.clas.service.ClasService;
import com.xunfang.bdpf.system.role.entity.Role;
import com.xunfang.bdpf.system.role.entity.RoleExample;
import com.xunfang.bdpf.system.role.service.RoleService;
import com.xunfang.bdpf.system.time.service.TimeService;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.bdpf.system.user.entity.UserExample;
import com.xunfang.bdpf.system.user.entity.UserRole;
import com.xunfang.bdpf.system.user.entity.UserRoleExample;
import com.xunfang.bdpf.system.user.mapper.UserMapper;
import com.xunfang.bdpf.system.user.service.UserRoleService;
import com.xunfang.bdpf.system.user.service.UserService;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.Identities;
import com.xunfang.utils.PropertiesUtil;
import com.xunfang.utils.Tools;

/**
 * 
 * @ClassName UserServiceImpl
 * @Description: User接口实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年5月26日 上午11:20:44
 * @version V1.0
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	//用户管理接口
	private UserMapper userMapper;
	
    @Autowired
    private UserRoleService userRoleService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private ClasService clasService;
    
    @Autowired
    private TimeService timeService;
    
	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据UserExample对象，查询数据
	  * @param example
	  * @param @return    设定文件
	  * @return List<User>    返回类型
	  * @throws
	 */
	@Override
	public List<User> selectByExample(UserExample example) {
		return userMapper.selectByExample(example);
	}

	/**
	 * 
	  * @Title: getUserCount
	  * @Description: 根据关键字搜索获取数据总条数
	  * @param keywords
	  * @param @return    设定文件
	  * @return long    返回类型
	  * @throws
	 */
	@Override
	public long getUserCount(String keywords) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keywords", keywords);
		return userMapper.getUserCount(param);
	}
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
	@Override
	public List<User> queryUserByPage(String keywords, int skip, int limit) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keywords", keywords);
		param.put("skip", skip);
		param.put("limit", limit);
		return userMapper.queryUserByPage(param);
	}
	/**
	  * @Title: insert
	  * @Description: 新增数据
	  * @param record
	  * @param @return    设定文件
	  * @return int    大于0，表示操作成功。
	  * @throws
	 */
	@Override
	public int insert(User record) {
		return userMapper.insert(record);
	}
	/**
	  * @Title: selectByPrimaryKey
	  * @Description: 根据id查询
	  * @param id
	  * @param @return    设定文件
	  * @return User    返回类型
	  * @throws
	 */
	@Override
	public User selectByPrimaryKey(String id) {
		return userMapper.selectByPrimaryKey(id);
	}
	/**
	  * @Title: updateByPrimaryKey
	  * @Description: 根据user对象，更新数据
	  * @param record
	  * @param @return    设定文件
	  * @return int    大于0，表示操作成功。
	  * @throws
	 */
	@Override
	public int updateByPrimaryKey(User record) {
		return userMapper.updateByPrimaryKey(record);
	}
	/**
	  * @Title: deleteByPrimaryKey
	  * @Description:  根据id删除
	  * @param id
	  * @param @return    大于0，表示操作成功。
	  * @return int    返回类型
	  * @throws
	 */
	@Override
	public int deleteByPrimaryKey(String id) {
		return userMapper.deleteByPrimaryKey(id);
	}
	/**
	  * @Title: deleteByExample
	  * @Description: 根据example对象,指定条件删除数据
	  * @param example
	  * @param @return    大于0，表示操作成功。
	  * @return int    返回类型
	  * @throws
	 */
	@Override
	public int deleteByExample(UserExample example) {
		return userMapper.deleteByExample(example);
	}

	/**
	  * @Title: queryUserListByClasId
	  * @Description: 通过班级list获取班级表和学生列表的map
	  * @param param
	  * @param @return    大于0，表示操作成功。
	  * @return int    返回类型
	  * @throws
	 */
	@Override
	public Map<User, List<User>> queryUserListByClasId(String[] classIds) {
		Map<User, List<User>> usermap = new HashMap<User, List<User>>();
		List<User>  userlist = userMapper.queryUserListByClasId(classIds);
		//1.首先获取当前所有班级list
		List<User> classList = new ArrayList<User>();
		for (User user : userlist) {
			if(!"".equals(user.getClassId())&&user.getClassId() != null){
				classList.add(user);
			}
		}
		//2.根据班级ID获取该班级下的学生信息
		for (User user1 : classList) {//遍历班级
			List<User> userlistnew = new ArrayList<User>();
			for (User user2 : userlist) {//遍历学生
				if(user1.getClassId().equals(user2.getClassId())){//分班级添加数据。
					userlistnew.add(user2);
				}
			}
			//班级对应学生信息
			usermap.put(user1, userlistnew);
		}
		return usermap;
	}

	/**
	  * @Title: authLogin
	  * @Description: 根据账号和密码判断该用户是否存在
	  * @param  account String   账号
	  * @param password String   密码
	  * @return int    大于0，表示操作该用户存在。
	  * @throws
	 */
	@Override
	public int authLogin(String account, String password) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("account", account);
		param.put("password",password);
		return userMapper.authLogin(param);
	}
	
	/**
	 * 
	  * @Title: getStudentCount
	  * @Description: 根据关键字搜索获取数据总条数
	  * @param keywords
	  * @param @return    设定文件
	  * @return long    返回类型
	  * @throws
	 */
	@Override
	public long getStudentCount(String keywords,String createUser) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keywords", keywords);
		param.put("createUser", createUser);
		return userMapper.getStudentCount(param);
	}
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
	@Override
	public List<User> queryStudentByPage(String keywords, int skip, int limit,String createUser) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keywords", keywords);
		param.put("createUser", createUser);
		param.put("skip", skip);
		param.put("limit", limit);
		return userMapper.queryStudentByPage(param);
	}

	@Override
	public List<User> greadIds() {
		return userMapper.greadIds();
	}

	/**
	 * 
	  * @Title: selectClasCount
	  * @Description: 获取一个班级下有多少老师。
	  * @param @param clasId
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	@Override
	public int selectClasCount(String clasId) {
		return userMapper.selectClasCount(clasId);
	}

	@Override
	public Feedback batchImport(String fileName, MultipartFile Mfile,String type,int max,long number) {
		Feedback feedback = null;
		CommonsMultipartFile cf = (CommonsMultipartFile) Mfile;
		File file = new File(PropertiesUtil.getValue("DIR_FILE")+"/fileupload");

		if (!file.exists()){
			file.mkdirs();
		}

		File file1 = new File(PropertiesUtil.getValue("DIR_FILE")+"/fileupload/upload" + new Date().getTime() + ".xls");

		try {
			cf.getFileItem().write(file1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		InputStream is = null;
		try {
			boolean isExcel2003 = true;
			if (Tools.isExcel2007(fileName)) {
				isExcel2003 = false;
			}
			is = new FileInputStream(file1);
			feedback = getExcelInfo(is, isExcel2003,type,max,number);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			feedback = new Feedback(false, "导入EXCEL失败！");
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}
		return feedback;
	}
	
	/***
	 * @Title: getExcelInfo
	 * @Description:
	 * @param is
	 * @param isExcel2003
	 * @return Feedback
	 */
	public Feedback getExcelInfo(InputStream is, boolean isExcel2003,String type,int max,long number) {
		Feedback feedback = null;
		try {
			/** 根据版本选择创建Workbook的方式 */
			Workbook wb = null;
			// 当excel是2003时
			if (isExcel2003) {
				wb = new HSSFWorkbook(is);
			} else {// 当excel是2007时
				wb = new HSSFWorkbook(is);
			}
			// 读取Excel里面客户的信息
			if("true".equals(type)){
				feedback = readExcelTeacher(wb,max,number);
			}else{
				feedback = readExcelStudent(wb,max,number);
			}
		} catch (IOException e) {
			e.printStackTrace();
			feedback = new Feedback(false, "导入EXCEL失败！");
		}
		return feedback;
	}
	
	/***
	 * @Title: readExcelTeacher
	 * @Description: 解析excel
	 * @param wb
	 * @return Feedback
	 */
	public Feedback readExcelTeacher(Workbook wb,int max,long number) {
		Feedback feedback = null;
		// 用于界面提示信息
		StringBuffer message = new StringBuffer();
		StringBuffer clasinfo = new StringBuffer();
		String startclas = "教师信息:第";
		clasinfo.append(startclas);
		String countStr = "";
		Sheet dd = wb.getSheet("教师信息");
		int totalRows_clas = dd.getPhysicalNumberOfRows();
		int totalCells_clas = 0;
		if (totalRows_clas >= 1 && dd.getRow(0) != null) {
			totalCells_clas = dd.getRow(0).getPhysicalNumberOfCells();
		}
		List<User> userList = new ArrayList<User>();
		Map<String,String> userMap = new HashMap<String,String>();
		User user;
		for (int r = 1; r < totalRows_clas; r++) {
			Row row = dd.getRow(r);
			if (row == null){
				continue;
			}
			user = new User();
			
			for (int c = 0; c < totalCells_clas; c++) {
				Cell cell = row.getCell(c);
					if (c == 0) {//班级代码
						if(cell == null){
							clasinfo.append(r+"行，"+(c+1)+"列的班级代码为空!");
						}else{
							if(cell.getStringCellValue().equals("")){
								break;
							}else{
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if(cell.getStringCellValue() != null && cell.getStringCellValue().trim().length() > 0){
									user.setClassId(cell.getStringCellValue());
								}else{
									clasinfo.append(r+"行，"+(c+1)+"列的班级代码为空!");
								}
							}
						}
					} else if (c == 1) {//教师名称
						if(cell == null){
							clasinfo.append(r+"行，"+(c+1)+"列的教师名称为空!");
						}else{
							cell.setCellType(Cell.CELL_TYPE_STRING);
							if(cell.getStringCellValue() != null && cell.getStringCellValue().trim().length() > 0){
								user.setName(cell.getStringCellValue());
							}else{
								clasinfo.append(r+"行，"+(c+1)+"列的教师名称为空!");
							}
						}
					} else if (c == 2) {//教师工号
						if(cell == null){
							clasinfo.append(r+"行，"+(c+1)+"列的教师工号为空!");
						}else{
							cell.setCellType(Cell.CELL_TYPE_STRING);
							if(cell.getStringCellValue() != null && cell.getStringCellValue().trim().length() > 0){
								user.setAccount(cell.getStringCellValue());
							}else{
								clasinfo.append(r+"行，"+(c+1)+"列的教师工号为空!");
							}
						}
					} else if (c == 3) {//邮箱
						if(cell == null){
							user.setEmail("");
						}else{
							cell.setCellType(Cell.CELL_TYPE_STRING);
							if (cell.getStringCellValue() != null && cell.getStringCellValue().trim().length() > 0) {
								user.setEmail(cell.getStringCellValue());
							} else {
								user.setEmail("");
							}
						}
					} else if (c == 4) {//电话
						if(cell == null){
							user.setMobile("");
						}else{
							cell.setCellType(Cell.CELL_TYPE_STRING);
							if (cell.getStringCellValue() != null && cell.getStringCellValue().trim().length() > 0) {
								user.setMobile(cell.getStringCellValue());
							} else {
								user.setMobile("");
							}
						}
					}
			}
			if (user.getClassId() != null && user.getClassId().length() > 0 && user.getName() != null && user.getName().length() > 0
					&& user.getAccount() != null && user.getAccount().length() > 0 && userMap.get(user.getAccount())==null) {
				userMap.put(user.getAccount(), user.getAccount());
				user.setUserType(0);//用户类型：教师
				user.setPassword(PropertiesUtil.getValue("ADMINPASS"));
				user.setCreateTime(new Date());//创建时间
				userList.add(user);
			}
		}
		
		//判断系统人数，如果当前系统人数+要插入的人数<= 系统最大人数。则插入数据
		if(max>= (number+userList.size())){
			countStr = "教师信息：导入总数为："+(totalRows_clas-1)+"，其中导入成功数为："+userList.size();
			boolean flag = this.importUserInfo(userList);
			
			String enddd = "信息导入失败,请检查班级代号、教师名称、教师工号、学号!\n";
			clasinfo.append(enddd);
			// 长度没有发生变化不进行提示
			if (clasinfo.length() != startclas.length() + enddd.length()) {
				message.append(clasinfo+countStr);
			}
			if(flag){
				feedback = new Feedback(true, message.toString());
			}else{
				feedback = new Feedback(false, enddd);
			}
		}else{
			feedback = new Feedback(false,"已超出系统最大人数限制！");
		}

		return feedback;
	}
	
	/***
	 * @Title: readExcelStudent
	 * @Description: 解析excel
	 * @param wb
	 * @return Feedback
	 */
	public Feedback readExcelStudent(Workbook wb,int max,long number) {
		Feedback feedback = null;
		// 用于界面提示信息
		StringBuffer message = new StringBuffer();
		StringBuffer clasinfo = new StringBuffer();
		String startclas = "学生信息:第";
		clasinfo.append(startclas);
		String countStr = "";
		Sheet dd = wb.getSheet("学生信息");
		int totalRows_clas = dd.getPhysicalNumberOfRows();
		int totalCells_clas = 0;
		if (totalRows_clas >= 1 && dd.getRow(0) != null) {
			totalCells_clas = dd.getRow(0).getPhysicalNumberOfCells();
		}
		List<User> userList = new ArrayList<User>();
		Map<String,String> userMap = new HashMap<String,String>();
		User user;
		for (int r = 1; r < totalRows_clas; r++) {
			Row row = dd.getRow(r);
			if (row == null){
				continue;
			}
			user = new User();
			
			for (int c = 0; c < totalCells_clas; c++) {
				Cell cell = row.getCell(c);
					if (c == 0) {//班级代码
						if(cell == null){
							clasinfo.append(r+"行，"+(c+1)+"列的班级代码为空!");
						}else{
							if(cell.getStringCellValue().equals("")){
								break;
							}else{
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if(cell.getStringCellValue() != null && cell.getStringCellValue().trim().length() > 0){
									user.setClassId(cell.getStringCellValue());
								}else{
									clasinfo.append(r+"行，"+(c+1)+"列的班级代码为空!");
								}
							}
						}
					} else if (c == 1) {//学生名称
						if(cell == null){
							clasinfo.append(r+"行，"+(c+1)+"列的学生名称为空!");
						}else{
							cell.setCellType(Cell.CELL_TYPE_STRING);
							if(cell.getStringCellValue() != null && cell.getStringCellValue().trim().length() > 0){
								user.setName(cell.getStringCellValue());
							}else{
								clasinfo.append(r+"行，"+(c+1)+"列的学生名称为空!");
							}
						}
					} else if (c == 2) {//学生学号
						if(cell == null){
							clasinfo.append(r+"行，"+(c+1)+"列的学生学号为空!");
						}else{
							cell.setCellType(Cell.CELL_TYPE_STRING);
							if(cell.getStringCellValue() != null && cell.getStringCellValue().trim().length() > 0){
								user.setAccount(cell.getStringCellValue());
							}else{
								clasinfo.append(r+"行，"+(c+1)+"列的学生学号为空!");
							}
						}
					} else if (c == 3) {//邮箱
						if(cell == null){
							user.setEmail("");
						}else{
							cell.setCellType(Cell.CELL_TYPE_STRING);
							if (cell.getStringCellValue() != null && cell.getStringCellValue().trim().length() > 0) {
								user.setEmail(cell.getStringCellValue());
							} else {
								user.setEmail("");
							}
						}
					} else if (c == 4) {//电话
						if(cell == null){
							user.setMobile("");
						}else{
							cell.setCellType(Cell.CELL_TYPE_STRING);
							if (cell.getStringCellValue() != null && cell.getStringCellValue().trim().length() > 0) {
								user.setMobile(cell.getStringCellValue());
							} else {
								user.setMobile("");
							}
						}
					} 
			}
			if (user.getClassId() != null && user.getClassId().length() > 0 && user.getName() != null && user.getName().length() > 0
					&& user.getAccount() != null && user.getAccount().length() > 0 && userMap.get(user.getAccount())==null) {
				userMap.put(user.getAccount(), user.getAccount());
				user.setUserType(1);//用户类型：学生
				user.setPassword(PropertiesUtil.getValue("ADMINPASS"));
				user.setCreateTime(new Date());//创建时间
				userList.add(user);
			}
		}
		//判断系统人数，如果当前系统人数+要插入的人数<= 系统最大人数。则插入数据
		if(max>= (number+userList.size())){
			countStr = "学生信息：导入总数为："+(totalRows_clas-1)+"，其中导入成功数为："+userList.size();
			boolean flag = this.importUserInfo(userList);
			String enddd = "信息导入失败,请检查班级代号、学生名称、学生学号!\n";
			clasinfo.append(enddd);
			// 长度没有发生变化不进行提示
			if (clasinfo.length() != startclas.length() + enddd.length()) {
				message.append(clasinfo+countStr);
			}
			if(flag){
				feedback = new Feedback(true, message.toString());
			}else{
				feedback = new Feedback(false, enddd);
			} 
		}else{
			feedback = new Feedback(false,"已超出系统最大人数限制！");
		}
		return feedback;
	}
	
	@Override
	public boolean importUserInfo(List<User> userList) {
		boolean b = false;
		if (userList != null) {
			b = true;
		}
		//查询用户信息
		UserExample example = new UserExample();
		List<User> userlist = this.selectByExample(example);
		Map<String,String> userMap = new HashMap<String,String>();
		for (User user : userlist) {
			userMap.put(user.getAccount(), user.getId());
		}
		//查询班级信息
		ClasExample  clasExample  = new ClasExample();
		List<Clas> clasList = clasService.selectByExample(clasExample);
		Map<String,String> clasMap = new HashMap<String,String>();
		for (Clas clas : clasList) {
			clasMap.put(clas.getCode(), clas.getId());
		}
		//查询角色信息
		RoleExample roleExample = new RoleExample();
		Map<String,String> roleMap = new HashMap<String,String>();
		List<Role>  roles = roleService.selectByExample(roleExample);
		for (Role role : roles) {
			if(role.getName().contains("教师") || role.getName().contains("老师")){
				roleMap.put("0", role.getId()); 
			}else if(role.getName().contains("学生")){
				roleMap.put("1", role.getId());
			}else{
				roleMap.put("2", role.getId());
			}
		}
		
		// 迭代添加班级信息
		for (User user : userList) {
			if (userMap.get(user.getAccount())==null) {
				user.setId(Identities.uuid2());
				user.setPassword(PropertiesUtil.getValue("ADMINPASS"));
				if(user.getUserType()==0){//教师
					String classidStr = "";
					String[] classids = user.getClassId().split(",");
					for(int i=0;classids!=null&&i<classids.length;i++){
						classidStr += clasMap.get(classids[i])+",";
					}
					String clasId = classidStr.substring(0,classidStr.length()-1);
					user.setClassId(clasId);
					if(clasId == null){
						continue;
					}
				}else{//学生
					String clasId = clasMap.get(user.getClassId());
					if(TextUtils.isEmpty(clasId)){
						return false;
					}
					user.setClassId(clasId);
				}
				this.insert(user);
			} else {
				user.setId(userMap.get(user.getAccount()));
				if(user.getUserType()==0){//教师
					String classidStr = "";
					String[] classids = user.getClassId().split(",");
					for(int i=0;classids!=null&&i<classids.length;i++){
						classidStr += clasMap.get(classids[i])+",";
					}
					String clasId = classidStr.substring(0,classidStr.length()-1);
					user.setClassId(clasId);
					if(clasId == "null"){
						continue;
					}
				}else{//学生
					String clasId = clasMap.get(user.getClassId());
					user.setClassId(clasId);
					if(TextUtils.isEmpty(clasId)){
						return false;
					}
				}
				this.updateByPrimaryKey(user);
			}
			// 对角色的关联操作
			UserRoleExample ure = new UserRoleExample();
			ure.createCriteria().andUserIdEqualTo(user.getId());
			// 先删除数据
			userRoleService.deleteByExample(ure);
			
			//用户与角色的关联对象
			UserRole userRole = new UserRole();
			userRole.setId(Identities.uuid2());//随机生成的id
			userRole.setUserId(user.getId());//用户id
			userRole.setRoleId(roleMap.get(user.getUserType()+""));//角色id
			// 插入新数据
			userRoleService.insert(userRole);
		}
		return b;
	}

	@Override
	public User selectByAccount(String account) {
		// TODO Auto-generated method stub
		return userMapper.selectByAccount(account);
	}
}