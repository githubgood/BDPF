package com.xunfang.bdpf.experiment.task.entity;
/**
 * 
 * @ClassName Task
 * @Description: 课程发布model
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月23日 上午10:56:01
 * @version V1.0
 */
public class Task {
	//主键
    private String id;
    //任务名字
    private String name;
    //课程计划
    private String planName;
    //帮记名字
    private String className;
    //任务状态
    private String taskStatus;
    //教学计划主键
	private String teachingPlanId;
    //描述
    private String description;
    //班级主键
    private String classId;
    //组名
    private String groupName;
    //分组类型
    private int groupType;
    //小组人数
    private String groupNum;
    //实验名称
    private String title;
    //报告状态
    private int reportStatus;
    //报告状态
    private String teacherId;

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
    public String getTeachingPlanId() {
        return teachingPlanId;
    }

    public void setTeachingPlanId(String teachingPlanId) {
        this.teachingPlanId = teachingPlanId == null ? null : teachingPlanId.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
    
    public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getGroupType() {
		return groupType;
	}

	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}

	public String getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(String groupNum) {
		this.groupNum = groupNum;
	}   
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(int reportStatus) {
		this.reportStatus = reportStatus;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
}