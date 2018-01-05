package com.xunfang.bdpf.experiment.task.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openstack4j.api.Builders;
import org.openstack4j.model.compute.ServerCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.xunfang.bdpf.experiment.clas.entity.Clas;
import com.xunfang.bdpf.experiment.clas.mapper.ClasMapper;
import com.xunfang.bdpf.experiment.grade.entity.Grade;
import com.xunfang.bdpf.experiment.grade.entity.GradeExample;
import com.xunfang.bdpf.experiment.grade.entity.GradeResource;
import com.xunfang.bdpf.experiment.grade.entity.GradeResourceExample;
import com.xunfang.bdpf.experiment.grade.entity.StudentVirtual;
import com.xunfang.bdpf.experiment.grade.entity.StudentVirtualExample;
import com.xunfang.bdpf.experiment.grade.mapper.GradeMapper;
import com.xunfang.bdpf.experiment.grade.mapper.GradeResourceMapper;
import com.xunfang.bdpf.experiment.grade.mapper.StudentVirtualMapper;
import com.xunfang.bdpf.experiment.plan.entity.PlanLine;
import com.xunfang.bdpf.experiment.plan.mapper.PlanLineMapper;
import com.xunfang.bdpf.experiment.task.entity.Group;
import com.xunfang.bdpf.experiment.task.entity.GroupExample;
import com.xunfang.bdpf.experiment.task.entity.GroupResource;
import com.xunfang.bdpf.experiment.task.entity.GroupResourceExample;
import com.xunfang.bdpf.experiment.task.entity.Task;
import com.xunfang.bdpf.experiment.task.entity.TaskExample;
import com.xunfang.bdpf.experiment.task.entity.TaskLine;
import com.xunfang.bdpf.experiment.task.entity.TaskLineExample;
import com.xunfang.bdpf.experiment.task.mapper.GroupMapper;
import com.xunfang.bdpf.experiment.task.mapper.GroupResourceMapper;
import com.xunfang.bdpf.experiment.task.mapper.TaskLineMapper;
import com.xunfang.bdpf.experiment.task.mapper.TaskMapper;
import com.xunfang.bdpf.experiment.task.service.TaskService;
import com.xunfang.bdpf.login.entity.Servers;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.bdpf.system.user.entity.UserExample;
import com.xunfang.bdpf.system.user.mapper.UserMapper;
import com.xunfang.bdpf.vmstemplates.introduction.entity.Flavors;
import com.xunfang.bdpf.vmstemplates.introduction.entity.Images;
import com.xunfang.bdpf.vmstemplates.introduction.entity.KeyPair;
import com.xunfang.bdpf.vmstemplates.introduction.entity.Networks;
import com.xunfang.bdpf.vmstemplates.introduction.entity.Secgroup;
import com.xunfang.bdpf.vmstemplates.introduction.entity.Zone;
import com.xunfang.bdpf.vmstemplates.virtual.entity.ServerEntity;
import com.xunfang.utils.Identities;
import com.xunfang.utils.OpenStackApi;
import com.xunfang.utils.PropertiesUtil;
import com.xunfang.utils.VirtualThread;

/**
 * 
 * @ClassName TaskServiceImpl
 * @Description: 课程发布实现层
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月23日 上午10:09:37
 * @version V1.0
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	// 课程发布Mapper映射接口类
	private TaskMapper taskMapper;

	@Autowired
	// 课程班级任务关联表Mapper映射接口类
	private TaskLineMapper taskLineMapper;

	@Autowired
	// 实验分组Mapper映射接口类
	private GroupMapper groupMapper;

	@Autowired
	// 实验分组课程关联Mapper映射接口类
	private GroupResourceMapper groupResourceMapper;

	@Autowired
	// 学生成绩Mapper映射接口类
	private GradeMapper gradeMapper;

	@Autowired
	// 学生成绩资源关联Mapper映射接口类
	private GradeResourceMapper gradeResourceMapper;

	@Autowired
	// 教学计划Mapper映射接口类
	private PlanLineMapper planLineMapper;

	@Autowired
	// 用户Mapper映射接口类
	private UserMapper userMapper;

	@Autowired
	// 班级Mapper映射接口类
	private ClasMapper clasMapper;
	
	@Autowired
	//学生虚拟机关联表Mapper映射接口类
	private StudentVirtualMapper studentVirtualMapper;

	/**
	 * 
	  * @Title: getTaskCount
	  * @Description: 查询任务发布总数
	  *  @param keywords String 任务发布名称
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long getTaskCount(String keywords,String account) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keywords", keywords);
		param.put("account", account);
		return taskMapper.getTaskCount(param);
	}

	/**
	 * 
	  * @Title: queryTaskByPage
	  * @Description: 任务发布查询列表，带分页
	  *  @param keywords String 课程资源名称
	  *  @param skip int 从第几条开始取数据
	  *  @param limit int 每页显示多少条数据
	  *  @return  List<Task> 返回类型
	  * @throws
	 */
	@Override
	public List<Task> queryTaskByPage(String keywords, int skip, int limit,String account) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keywords", keywords);
		param.put("account", account);
		param.put("skip", skip);
		param.put("limit", limit);
		return taskMapper.queryTaskByPage(param);
	}

	/**
	 * 
	  * @Title: queryTaskByClass
	  * @Description: 班级课程资源查询列表
	  *  @param classId String 班级id
	  *  @return  List<Task> 返回类型
	  * @throws
	 */
	@Override
	public List<Task> queryTaskByClass(String classId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("classId", classId);
		return taskMapper.queryTaskByClass(param);
	}

	/**
	 * 
	  * @Title: save
	  * @Description: 任务发布保存
	  *  @param Task  Task 课程资源model
	  *  @return  boolean 返回类型
	  * @throws
	 */
	@Override
	public boolean save(Task task, MultipartFile file,String token,User teacher) {
		List<Group> groupExcelList = new ArrayList<Group>();// 存excel分组数据避免多次解析
		List<TaskLine> taskLineList = new ArrayList<TaskLine>();// 存任务班级关联表数据
		List<Grade> gradeList = new ArrayList<Grade>();//存学生成绩信息
		List<GradeResource> gradeResourceList = new ArrayList<GradeResource>();//存学生资源关联表信息
		List<Group> groupList = new ArrayList<Group>();//存分组信息
		List<GroupResource> groupResourceList = new ArrayList<GroupResource>();//存分组与资源关联表信息
		List<User> classStudent = null;//存班级学生列表信息，这里指单人实验
		List<User> studentList = new ArrayList<User>();//存学生列表信息
		Map<String, Object> param = new  HashMap<String, Object>();
		if(teacher.getUserType()!=2){
			param.put("createUser",task.getTeacherId());
		}
		List<Clas> clasList = clasMapper.queryClassUser(param);//获取班级信息
		List<StudentVirtual> studentVirtualList = new ArrayList<StudentVirtual>();//存学生与虚拟机绑定关系信息
		StudentVirtual studentVirtual = null;//学生与虚拟机表Model
		UserExample  userExample = new UserExample();//用户example
		List<User> users = userMapper.selectByExample(userExample);//获取用户信息
		TaskLine taskLine = null;//课程任务班级关联表Model
		Grade grade = null;//学生成绩表Model
		GradeResource gradeResource = null;//学生成绩资源关联表Model
		Group group = null;//分组表Model
		GroupResource groupResource = null;//分组资源关联表Model
		int num = 0;//判断学生人数/当前每组人数是否整除
		boolean flag = false;//返回类型
		String[] arrClas = null;//班级数组
		task.setId(Identities.uuid2());//课程任务主键ID
		String errorString = "";//错误信息提示
		// 获取选择的那些课程资源
		Map<String, Object> params = new HashMap<String, Object>();//传参map
		params.put("teachingPlanId", task.getTeachingPlanId());//课程教学计划id
		List<PlanLine> list = planLineMapper.queryPlanLineById(params);//根据课程教学计划获取课程资源列表
		// 插入课程任务关联表
		if(task.getGroupType()==0){//自动分组
			Random random = new Random();//随机类
			int groupValue=0;//小组人数
			int groupNum = 0;//小组数
			arrClas = task.getClassId().split(",");//页面选择的班级，进行分组
			// 通过班级ID查询学生列表信息
			List<User> userList = userMapper.queryUserListByClasId(arrClas);//根据班级ID获取学生信息列表
			Map<String, List<User>> stumap = this.privateClasId(userList);//对学生信息进行封装
			if(task.getGroupNum()!=null&&!"".equals(task.getGroupNum())){//判空
				 groupValue=Integer.parseInt(task.getGroupNum());//获取当前小组人数
			}
			//遍历循环班级信息
			for (int k = 0; k < arrClas.length; k++) {
				taskLine = new TaskLine();//new一个课程任务课程关联表Model
				taskLine.setId(Identities.uuid2());//生成随机课程任务课程关联表主键ID
				taskLine.setClassId(arrClas[k]);//班级ID
				taskLine.setCourseTaskId(task.getId());//任务ID
				taskLineList.add(taskLine);//添加到课程任务课程关联表List
				// 查询班级及所有的学生
				for (int i = 0; i < list.size(); i++) {
					// 判断是单人还是分组实验 安装维护类就给他分组
					if (list.get(i).getCourseResourceType() == 0) {
							//当前分组数：班级人数/小组人数
							groupNum = stumap.get(arrClas[k]).size() / groupValue;
							// 如果除不够取余数
							num = stumap.get(arrClas[k]).size() % groupValue;
							if(groupNum==0){//如果当前分组数为0，则默认为1
								groupNum = 1;
								num=0;
								groupValue=stumap.get(arrClas[k]).size();
							}
							//循环分组数
							for (int n = 0; n < groupNum; n++) {
								if (n == 0) {//获取当前学生信息
									studentList = new ArrayList<User>(stumap.get(arrClas[k]));
								}
								group = new Group();//new分组Model
								groupResource = new GroupResource();//new 分组资源关联表Model
								group.setId(Identities.uuid2());//分组主键ID
								group.setGroupType(task.getGroupType());//分组类型
								group.setClassId(arrClas[k]);//班级ID
								group.setName(task.getGroupName() + "小组" + (n + 1));//小组名称
								if (num > 0) {
									//每组人数
									group.setGroupNum(groupValue + 1);
									num--;
								} else {
									//每组人数
									group.setGroupNum(groupValue);
								}
								group.setTaskId(task.getId());//任务ID
								groupResource.setId(Identities.uuid2());//分组资料关联表主键ID
								groupResource.setExperimentGroupId(group.getId());//分组ID
								groupResource.setCourseResourceId(list.get(i).getCourseResourceId());//课程资源ID
								groupList.add(group);//添加到分组list
								groupResourceList.add(groupResource);//添加到分组资源list
								//循环分组数
								for (int f = 0; f < group.getGroupNum(); f++) {
									int result = random.nextInt(studentList.size());//随机数
									grade = new Grade();//new学生成绩表Model
									gradeResource = new GradeResource();//new学生成绩资源关联表Model
									grade.setId(Identities.uuid2());//学生成绩表主键ID
									grade.setClassId(studentList.get(result).getClassId());//班级ID
									grade.setStudentId(studentList.get(result).getAccount());// 随机抽取学生ID
									grade.setTeacherId(task.getTeacherId());//教师ID
									grade.setGroupId(group.getId());//分组ID
									grade.setCourseTaskId(task.getId());//课程任务ID
									grade.setTaskStatus(0);//任务状态
									grade.setReportStatus(0);//提交实验报告状态
									studentList.remove(result);// 抽取过的移除掉
									gradeResource.setId(Identities.uuid2());//学生成绩资源关联表主键ID
									gradeResource.setExperimentGradeId(grade.getId());//学生成绩主键ID
									gradeResource.setCourseResourceId(list.get(i).getCourseResourceId());//课程资源主键ID
									gradeList.add(grade);//加入到学生成绩list
									gradeResourceList.add(gradeResource);//加入到学生成绩关联表list
									
									//保存学生与虚拟机绑定信息
									studentVirtual = new StudentVirtual();//new学生虚拟机表Model
									studentVirtual.setId(Identities.uuid2());//学生虚拟机表主键ID
									studentVirtual.setAccount(grade.getStudentId());//学生学号
									studentVirtual.setVirtualName("w-"+grade.getStudentId());//虚拟机名称
									studentVirtual.setClassId(grade.getClassId());//班级ID
									studentVirtual.setGroupId(grade.getGroupId());//分组ID
									studentVirtual.setCourseTaskId(task.getId());//课程任务ID
									studentVirtual.setType(0);//虚拟机类型：0 分组实验 1 个人实验 2 开放实验
									studentVirtual.setImageId(list.get(i).getTeachingPlanId());//镜像模板ID
									studentVirtualList.add(studentVirtual);//加入到学生虚拟机关联表list
								}
							}
					} else {
						// 单人情况
						classStudent = stumap.get(arrClas[k]);//获取学生列表
						//循环学生信息
						for (int j = 0; j < classStudent.size(); j++) {
							grade = new Grade();//new学生成绩表Model
							gradeResource = new GradeResource();//new学生成绩资源关联表Model
							grade.setId(Identities.uuid2());//学生成绩表主键ID
							grade.setClassId(classStudent.get(j).getClassId());//班级ID
							grade.setStudentId(classStudent.get(j).getAccount());// 取学生ID
							grade.setTeacherId(task.getTeacherId());//教师ID
							grade.setCourseTaskId(task.getId());//课程任务ID
							grade.setTaskStatus(0);//任务状态
							grade.setReportStatus(0);//提交实验报告状态
							gradeResource.setId(Identities.uuid2());//学生成绩资源关联表主键ID
							gradeResource.setExperimentGradeId(grade.getId());//学生成绩主键ID
							gradeResource.setCourseResourceId(list.get(i).getCourseResourceId());//课程资源主键ID
							gradeList.add(grade);//加入到学生成绩list
							gradeResourceList.add(gradeResource);//加入到学生成绩关联表list
							
							//保存学生与虚拟机绑定信息
							studentVirtual = new StudentVirtual();//new学生虚拟机表Model
							studentVirtual.setId(Identities.uuid2());//学生虚拟机表主键ID
							studentVirtual.setAccount(grade.getStudentId());//学生学号
							studentVirtual.setVirtualName("k-"+grade.getStudentId());//虚拟机名称
							studentVirtual.setClassId(grade.getClassId());//班级ID
							studentVirtual.setGroupId("");//分组ID
							studentVirtual.setCourseTaskId(task.getId());//课程任务ID
							studentVirtual.setType(1);//虚拟机类型：0 分组实验 1 个人实验 2 开放实验
							studentVirtual.setImageId(list.get(i).getTeachingPlanId());//镜像模板ID
							studentVirtualList.add(studentVirtual);//加入到学生虚拟机关联表list
						}
					}
				}
			}
		}else{//手动分组
			//存储班级名称和班级id信息的对应关系HashMap
			HashMap<String, String> clasMap = new HashMap<String,String>();
			for(Clas clas:clasList){
				if(clas.getName()!=null && clasMap.get(clas.getName())==null){
					clasMap.put(clas.getName(), clas.getId());
				}
			}
			//存储学生学号与学生信息的对应关系HashMap
			Map<String, User> userMap = new HashMap<String, User>();
			for (User user : users) {
			      if(user.getAccount()!=null&&userMap.get(user.getAccount())==null){
			    	  userMap.put(user.getAccount(), user);
			      }
			}
			//存储班级ID+分组名称  与 小组人数+分组ID的对应关系HashMap
			Map<String,String> groupNumAndIdMap = new HashMap<String,String>();
			//解析上传的分组信息
			if (file.getSize() != 0) {
				try {
					InputStream is = file.getInputStream();//获取文件输入流
					Workbook wb = new HSSFWorkbook(is);//new Workbook对象
					Sheet aa = wb.getSheet("分组信息");//获取当前Sheet
					int totalRows = aa.getPhysicalNumberOfRows();//有效的行
					int totalCells = 0;//列
					if (totalRows >= 1 && aa.getRow(0) != null) {
						// 获取excel分组信息有效数据列数
						totalCells = aa.getRow(0).getPhysicalNumberOfCells();
					} // 第一个for循环用于遍历行数
					for (int r = 1; r < totalRows; r++) {
						Row row = aa.getRow(r);
						if (row == null) {
							continue;
						}
						group = new Group();
						// for循环用于遍历列数
						for (int c = 0; c < totalCells; c++) {
							Cell cell = row.getCell(c);
							if (null != cell) {
								if (c == 0) {
									cell.setCellType(Cell.CELL_TYPE_STRING);
									//通过班级名称获取班级ID
									if(clasMap.get(cell.getStringCellValue())==null){
										errorString +=",第"+(r+1)+"行班级名称错误!";
									}else{
										if(cell.getStringCellValue().equals("")){
											break;
										}else{
											group.setClassName(cell.getStringCellValue());// 获取班级名字
											group.setClassId(clasMap.get(group.getClassName()));// 获取班级id
										}
									}
								} else if (c == 1) {
									cell.setCellType(Cell.CELL_TYPE_STRING);
									// 获取小组名字
									if(cell.getStringCellValue()!=null&&!"".equals(cell.getStringCellValue())){
										group.setName(cell.getStringCellValue());//分组名称
										if (groupNumAndIdMap.get(group.getClassId()+""+group.getName())==null) {
											group.setId(Identities.uuid2());//分组ID
											groupNumAndIdMap.put(group.getClassId()+""+group.getName(), 1+","+group.getId());
										}else{
											groupNumAndIdMap.put(group.getClassId()+""+group.getName(), (Integer.parseInt(groupNumAndIdMap.get(group.getClassId()+""+group.getName()).split(",")[0])+1)+","+groupNumAndIdMap.get(group.getClassId()+""+group.getName()).toString().split(",")[1]);
										}
									}else{
										errorString +=",第"+(r+1)+"行小组名称错误!";
									}
								} else if (c == 2) {
									// 获取学生学号
									cell.setCellType(Cell.CELL_TYPE_STRING);
									if(userMap.get(cell.getStringCellValue())!=null){
										group.setStudentId(cell.getStringCellValue());//学生学号
									}else{
										errorString +=",第"+(r+1)+"行学生学号错误!";
									}
								}
							}
						}
						if("".equals(errorString)){
							groupExcelList.add(group);//加入到分组list
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			//获取班级信息
			arrClas = new String[clasMap.size()];
			Iterator<String> iterator = clasMap.keySet().iterator();
			int x = 0;
		    //遍历获取班级数组
			while (iterator.hasNext()) {
				String key = iterator.next();
				arrClas[x] = clasMap.get(key);
				x ++;
			}
			// 通过班级ID查询学生列表信息
			List<User> userList = userMapper.queryUserListByClasId(arrClas);
			//获取班级ID与学生信息的HashMap
			Map<String, List<User>> stumap = this.privateClasId(userList);
			//用于过滤分组数据，防止重复添加
			Map<String,String> groupMap = new HashMap<String,String>();
			
			//循环班级信息
			for (int k = 0; k < arrClas.length; k++) {
				taskLine = new TaskLine();//new一个课程任务课程关联表Model
				taskLine.setId(Identities.uuid2());//生成随机课程任务课程关联表主键ID
				taskLine.setClassId(arrClas[k]);//班级ID
				taskLine.setCourseTaskId(task.getId());//任务ID
				taskLineList.add(taskLine);//添加到课程任务课程关联表List
				// 查询班级及所有的学生
				for (int i = 0; i < list.size(); i++) {
					// 判断是单人还是分组实验 安装维护类就给他分组
					if (list.get(i).getCourseResourceType() == 0) {
							// 插入实验分组表
							for (Group group2 : groupExcelList) {
								if(group2.getClassId().equals(arrClas[k])){//当前班级
									if(group2.getName()!=null&&groupMap.get(arrClas[k]+""+group2.getName())==null){
										groupMap.put(arrClas[k]+""+group2.getName(), arrClas[k]+""+group2.getName());
										groupResource = new GroupResource();//new学生成绩资源关联表Model
										group2.setId(groupNumAndIdMap.get(group2.getClassId()+""+group2.getName()).split(",")[1]);//学生成绩表主键ID
										group2.setGroupType(task.getGroupType());//分组类型
										group2.setTaskId(task.getId());//课程任务ID
										group2.setGroupNum(Integer.parseInt(groupNumAndIdMap.get(group2.getClassId()+""+group2.getName()).split(",")[0]));//分组人数
										groupResource.setId(Identities.uuid2());//分组资源关联表主键ID
										groupResource.setExperimentGroupId(groupNumAndIdMap.get(group2.getClassId()+""+group2.getName()).split(",")[1]);//分组ID
										groupResource.setCourseResourceId(list.get(i).getCourseResourceId());//课程资源ID
										groupList.add(group2);//加入分组表list
										groupResourceList.add(groupResource);//加入分组资源表关联list
									}
									grade = new Grade();//new学生成绩表Model
									gradeResource = new GradeResource();//new学生成绩资源关联表Model
									grade.setId(Identities.uuid2());//学生成绩表主键ID
									grade.setClassId(group2.getClassId());//班级ID
									grade.setStudentId(group2.getStudentId());//学生ID
									grade.setTeacherId(task.getTeacherId());//教师ID
									grade.setGroupId(groupNumAndIdMap.get(group2.getClassId()+""+group2.getName()).split(",")[1]);//分组ID
									grade.setCourseTaskId(task.getId());//课程任务ID
									grade.setTaskStatus(0);//任务状态
									grade.setReportStatus(0);//提交实验报告状态
									gradeResource.setId(Identities.uuid2());//学生成绩资源关联表主键ID
									gradeResource.setExperimentGradeId(grade.getId());//学生成绩主键ID
									gradeResource.setCourseResourceId(list.get(i).getCourseResourceId());//课程资源主键ID
									gradeList.add(grade);//加入到学生成绩list
									gradeResourceList.add(gradeResource);//加入到学生成绩关联表list
									
									//保存学生与虚拟机绑定信息
									studentVirtual = new StudentVirtual();//new学生虚拟机表Model
									studentVirtual.setId(Identities.uuid2());//学生虚拟机表主键ID
									studentVirtual.setAccount(group2.getStudentId());//学生学号
									studentVirtual.setVirtualName("w-"+grade.getStudentId());//虚拟机名称
									studentVirtual.setClassId(group2.getClassId());//班级ID
									studentVirtual.setGroupId(grade.getGroupId());//分组ID
									studentVirtual.setCourseTaskId(task.getId());//课程任务ID
									studentVirtual.setType(0);//虚拟机类型：0 分组实验 1 个人实验 2 开放实验
									studentVirtual.setImageId(list.get(i).getTeachingPlanId());//镜像模板ID
									studentVirtualList.add(studentVirtual);//加入到学生虚拟机关联表list
								}
							}
					} else {
						// 单人情况
						classStudent = stumap.get(arrClas[k]);//获取学生列表
						//循环学生信息
						for (int j = 0; j < classStudent.size(); j++) {
							grade = new Grade();//new学生成绩表Model
							gradeResource = new GradeResource();//new学生成绩资源关联表Model
							grade.setId(Identities.uuid2());//学生成绩表主键ID
							grade.setClassId(classStudent.get(j).getClassId());//班级ID
							grade.setStudentId(classStudent.get(j).getAccount());// 取学生ID
							grade.setTeacherId(task.getTeacherId());//教师ID
							grade.setCourseTaskId(task.getId());//课程任务ID
							grade.setTaskStatus(0);//任务状态
							grade.setReportStatus(0);//提交实验报告状态
							gradeResource.setId(Identities.uuid2());//学生成绩资源关联表主键ID
							gradeResource.setExperimentGradeId(grade.getId());//学生成绩主键ID
							gradeResource.setCourseResourceId(list.get(i).getCourseResourceId());//课程资源主键ID
							gradeList.add(grade);//加入到学生成绩list
							gradeResourceList.add(gradeResource);//加入到学生成绩关联表list
							
							//保存学生与虚拟机绑定信息
							studentVirtual = new StudentVirtual();//new学生虚拟机表Model
							studentVirtual.setId(Identities.uuid2());//学生虚拟机表主键ID
							studentVirtual.setAccount(classStudent.get(j).getAccount());//学生学号
							studentVirtual.setVirtualName("k-"+grade.getStudentId());//虚拟机名称
							studentVirtual.setClassId(classStudent.get(j).getClassId());//班级ID
							studentVirtual.setGroupId("");//分组ID
							studentVirtual.setCourseTaskId(task.getId());//课程任务ID
							studentVirtual.setType(1);//虚拟机类型：0 分组实验 1 个人实验 2 开放实验
							studentVirtual.setImageId(list.get(i).getTeachingPlanId());//镜像模板ID
							studentVirtualList.add(studentVirtual);//加入到学生虚拟机关联表list
						}
					}
				}
			}
		}

		//如果上传的数据验证通过，则进行保存操作
		if("".equals(errorString)){
			//获取当前学生虚拟机信息
			StudentVirtualExample studentVirtualExample = new StudentVirtualExample();
			List<StudentVirtual> studentVirtuals = studentVirtualMapper.selectByExample(studentVirtualExample);
			Map<String, String> studentVirtualMap = new HashMap<String,String>();
			for (StudentVirtual studentVirtual2 : studentVirtuals) {
				if(studentVirtual2.getVirtualId()!=null){
					studentVirtualMap.put(studentVirtual2.getVirtualName(), studentVirtual2.getVirtualId());
				} 
			}
		    //模板名称默认为：安装【centos6.5_no_ui】  开发【centos6.5-ps-dis】
			List<Images> imageList = OpenStackApi.cloudImage("", token);
			//存放模板名称和模板ID的对应关系
			Map<String, String> imageMap = new HashMap<String,String>();
			for (Images image : imageList) {
				if(imageMap.get(image.getName())==null){
					imageMap.put(image.getName(), image.getId());
				}
			}
			//云主机类型默认为：安装【install】  开发【bigdata】
			List<Flavors> flavorsList = OpenStackApi.cloudFlavors("", token);
			//存放云主机类型名称和云主机类型ID的对应关系
			Map<String, String> flavorMap = new HashMap<String,String>();
			for (Flavors flavors : flavorsList) {
				if(flavorMap.get(flavors.getName())==null){
					flavorMap.put(flavors.getName(), flavors.getId());
				}
			}
			//秘钥对默认为：mykey
			List<KeyPair> keypairList = OpenStackApi.cloudKeypair(token);
			//安全组默认为：default
			List<Secgroup> secgroupList = OpenStackApi.cloudSecgroup(token);
			//可用域默认为：nova
			List<Zone> zoneList = OpenStackApi.cloudZone(token);
			//网络默认为：selfservice
			List<Networks> networkList = OpenStackApi.cloudNetwork(token);
			List<StudentVirtual> studentVirtualListnew = new ArrayList<StudentVirtual>();//存学生与虚拟机绑定关系信息
			List<StudentVirtual> virtuals = new ArrayList<StudentVirtual>();//存放虚拟机信息
			//创建虚拟机
			Map<String, String> virtualMap = new HashMap<String, String>(); 
			for(int i=0;i<studentVirtualList.size();i++){
				StudentVirtual studentVirtual2 = studentVirtualList.get(i);
				if(studentVirtualMap.get(studentVirtual2.getVirtualName())==null){
					if(virtualMap.get(studentVirtual2.getVirtualName())==null){
						virtualMap.put(studentVirtual2.getVirtualName(), studentVirtual2.getVirtualName());
						//批量创建云主机
						ServerEntity serverEntity = new ServerEntity();
						//命名规则：维护【M】 开发【K】开放【O】
						serverEntity.setName(studentVirtual2.getVirtualName());//虚拟机名称
						//虚拟机类型：0 分组实验 1 个人实验 2 开放实验
						if(studentVirtual2.getType()==0){//分组实验
							if(studentVirtual2.getImageId()!=null&&!"".equals(studentVirtual2.getImageId())){
								serverEntity.setImageId(studentVirtual2.getImageId());//模板ID
							}else{
								serverEntity.setImageId(imageMap.get(PropertiesUtil.getValue("IMAGE_NAME_W")));//模板ID
							}
							serverEntity.setFlavorId(flavorMap.get(PropertiesUtil.getValue("FLAVORS_NAME_W")));//虚拟机类型ID
						}else if(studentVirtual2.getType()==1){//个人实验
							if(studentVirtual2.getImageId()!=null&&!"".equals(studentVirtual2.getImageId())){
								serverEntity.setImageId(studentVirtual2.getImageId());//模板ID
							}else{
								serverEntity.setImageId(imageMap.get(PropertiesUtil.getValue("IMAGE_NAME_K")));//模板ID
							}
							serverEntity.setFlavorId(flavorMap.get(PropertiesUtil.getValue("FLAVORS_NAME_K")));//虚拟机类型ID
						}
						serverEntity.setKeyName(keypairList.get(0).getName());//秘钥对名称
						serverEntity.setSecurityGroupsName(secgroupList.get(0).getName());//安全组名称
						serverEntity.setAdminPass(PropertiesUtil.getValue("ADMINPASS"));//管理密码默认为：123456
						serverEntity.setAvailabilityZone(zoneList.get(0).getZoneName());//可用域名称
						List<String> networkParams = new ArrayList<String>();
						for(Networks network:networkList){
							if(PropertiesUtil.getValue("NETWORK_NAME").equals(network.getName())){
								networkParams.add(network.getId());
							}
						}
						
						//返回结果
						ServerCreate sc = Builders.server().name(serverEntity.getName())
								.flavor(serverEntity.getFlavorId())
								.image(serverEntity.getImageId())
								.networks(networkParams)
								.addSecurityGroup(serverEntity.getSecurityGroupsName())
								.availabilityZone(serverEntity.getAvailabilityZone())
								.addAdminPass(serverEntity.getAdminPass())
								.keypairName(serverEntity.getKeyName()).build();
						Servers server = OpenStackApi.cloudServerCreate(sc, token);
						//更新学生虚拟机关联表信息
						if(server != null){
							studentVirtual2.setVirtualId(server.getId());
							virtualMap.put(studentVirtual2.getVirtualName(), server.getId());
							virtuals.add(studentVirtual2);
							studentVirtualListnew.add(studentVirtual2);
						}
					}
				 }
			}
			//插入课程任务表信息
			if(task!=null){
				flag = taskMapper.insert(task) > 0 ? true : false;
			}
			//批量插入任务班级关联表信息
			taskLineMapper.batchInsert(taskLineList);
			if (groupList.size() > 0) {
				//批量插入分组信息
				groupMapper.batchInsert(groupList);
			}
			if (groupResourceList.size() > 0) {
				//批量插入分组资源关联表信息
				groupResourceMapper.batchInsert(groupResourceList);
			}
			if(gradeList.size()>0){
				//批量插入学生成绩表信息
				gradeMapper.batchInsert(gradeList);
			}
			if(gradeResourceList.size()>0){
				//批量插入学生成绩资源关联表信息
				gradeResourceMapper.batchInsert(gradeResourceList);
			}
			if(studentVirtualListnew.size()>0){
				//批量插入学生虚拟机关联表信息
				studentVirtualMapper.batchInsertStudentVirtual(studentVirtualListnew);
			}
			//创建线程,将新创建的虚拟机关机
			Thread thread = (Thread) new VirtualThread(virtuals,token);
			thread.start();
		}else{
			return false;
		}
		//返回结果
		return flag;
	}
	
	/**
	 * 
	  * @Title: privateClasId
	  * @Description: 自定义封装方法
	  *  @param userlist
	  *  @return  Map<String,List<User>> 返回类型
	  * @throws
	 */
	public Map<String, List<User>> privateClasId(List<User> userlist) {
		Map<String, List<User>> usermap = new HashMap<String, List<User>>();
		// 1.首先获取当前所有班级list
		List<User> classList = new ArrayList<User>();
		for (User user : userlist) {
			if (!"".equals(user.getClassId()) && user.getClassId() != null) {
				classList.add(user);
			}
		}
		// 2.根据班级ID获取该班级下的学生信息
		for (User user1 : classList) {// 遍历班级
			List<User> userlistnew = new ArrayList<User>();
			for (User user2 : userlist) {// 遍历学生
				if (user1.getClassId().equals(user2.getClassId())) {// 分班级添加数据。
					userlistnew.add(user2);
				}
			}
			// 班级对应学生信息
			usermap.put(user1.getClassId(), userlistnew);
		}
		return usermap;
	}

	/**
	 * 
	  * @Title: deleteByExample
	  * @Description: 任务发布删除
	  *  @param example TaskExample 任务发布TaskExample
	  *  @return  boolean 返回类型
	  * @throws
	 */
	@Override
	public boolean deleteByExample(List<String> asList) {
		boolean flag = true;
		List<String> groupId = new ArrayList<String>();//分组ID  list
		List<String> gradeId = new ArrayList<String>();//学生成绩ID list
		TaskExample task = new TaskExample();//任务信息
		TaskLineExample taskLine = new TaskLineExample();//任务班级关联信息
		GroupExample group = new GroupExample();//分组信息
		GradeExample grade = new GradeExample();//学生成绩信息
		StudentVirtualExample  studentVirtual = new StudentVirtualExample();//学生虚拟机关联信息
		task.createCriteria().andIdIn(asList);//课程任务查询条件
		taskLine.createCriteria().andCourseTaskIdIn(asList);//课程任务资源关联查询条件
		group.createCriteria().andTaskIdIn(asList);//分组查询条件
		grade.createCriteria().andCourseTaskIdIn(asList);//学生成绩查询条件
		studentVirtual.createCriteria().andCourseTaskIdIn(asList);//学生虚拟机关联信息
		flag = taskMapper.deleteByExample(task) > 0 ? true : false;//删除课程任务表信息
		flag = taskLineMapper.deleteByExample(taskLine) > 0 ? true : false;//删除任务资源关联信息
		// 查询实验课程资源分组表的数据
		List<Group> groupList = groupMapper.selectByExample(group);
		for (Group v : groupList) {
			groupId.add(v.getId());
		}
		//分组资源信息
		GroupResourceExample groupResource = new GroupResourceExample();
		groupResource.createCriteria().andExperimentGroupIdIn(groupId);//分组资源信息查询条件
		flag = groupMapper.deleteByExample(group) > 0 ? true : false;//删除分组信息
		if (groupId.size() > 0) {
			flag = groupResourceMapper.deleteByExample(groupResource) > 0 ? true : false;//删除分组资源关联信息
		}
		//获取学生成绩表信息
		List<Grade> gradeList = gradeMapper.selectByExample(grade);
		for (Grade v : gradeList) {
			gradeId.add(v.getId());
		}
		//学生成绩资源关联表信息
		GradeResourceExample gradeResourceExample = new GradeResourceExample();
		gradeResourceExample.createCriteria().andExperimentGradeIdIn(gradeId);//学生成绩资源关联查询条件
		flag = gradeMapper.deleteByExample(grade) > 0 ? true : false;//删除学生成绩表信息
		flag = gradeResourceMapper.deleteByExample(gradeResourceExample) > 0 ? true : false;//删除学生成绩资源关联表信息
		flag = studentVirtualMapper.deleteByExample(studentVirtual) > 0 ? true : false;//删除学生虚拟机关联表信息
		
		return flag;
	}

	/**
	 * 
	  * @Title: selectByPrimaryKey
	  * @Description: 根据任务发布主键ID查询任务发布
	  *  @param id String 任务发布主键ID
	  *  @return  Task 返回类型
	  * @throws
	 */
	@Override
	public Task selectByPrimaryKey(String param) {
		return taskMapper.selectByPrimaryKey(param);
	}

	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据任务发布字段查询课程资源
	  *  @param example 任务发布实体对象
	  *  @return  List<Task> 返回类型
	  * @throws
	 */
	@Override
	public List<Task> selectByExample(TaskExample example) {
		return taskMapper.selectByExample(example);
	}

	/**
	 * 
	  * @Title: getDetailCount
	  * @Description: 任务详情查询总数
	  *  @param classId String 班级ID
	  *  @param id String  任务ID
	  *  @return  List<Task> 返回类型
	  * @throws
	 */
	@Override
	public List<Task> getDetailCount(String classId, String id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("classId", classId);
		param.put("id", id);
		return taskMapper.getDetailCount(param);
	}

    /**
     * 
      * @Title: queryDetailByPage
      * @Description: 任务详情查询
      *  @param classId String 班级ID
      *  @param id String 任务ID
      *  @param skip int //从第几条开始取数据
      *  @param max int //每页显示多少条数据
      *  @return  List<Task> 返回类型
      * @throws
     */
	@Override
	public List<Task> queryDetailByPage(String classId, String id, int skip, int limit) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("classId", classId);
		param.put("id", id);
		param.put("skip", skip);
		param.put("limit", limit);
		return taskMapper.queryDetailByPage(param);
	}

	/**
	 * 
	  * @Title: selectTaskByAccount
	  * @Description: 根据任务发布字段查询课程资源
	  *  @param account 用户账号
	  *  @return  List<Task> 返回类型
	  * @throws
	 */
	@Override
	public List<Task> selectTaskByAccount(String account) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("account", account);
		return taskMapper.selectTaskByAccount(param);
	}
}
