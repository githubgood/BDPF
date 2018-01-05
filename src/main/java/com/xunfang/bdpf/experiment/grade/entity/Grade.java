package com.xunfang.bdpf.experiment.grade.entity;

import java.util.Date;
/**
 * 
 * @ClassName Grade
 * @Description: 学生成绩model
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月23日 上午11:00:48
 * @version V1.0
 */
public class Grade {
	//主键
    private String id;
    //班级主键
    private String classId;
    //班级名字
    private String className;
    //学生
    private String studentId;
    //老师主键
    private String teacherId;
	//学生名字
    private String studentName;
    //分组主键
    private String groupId;
    //组名字
    private String groupName;
    //报告路径
    private String reportBook;
    //成绩
    private Integer score;
    //评语
    private String comment;
    //开始时间
    private Date beginAttendance;
    //结束时间
    private Date lastAttendance;
    //任务状态
    private Integer taskStatus;
    //实验状态
    private Integer reportStatus;
    //任务主键
    private String courseTaskId;
    //资源名称
    private String title;
    //资源类型
    private Integer courseResourceType;
    //课程资源Id
    private String courseResourceId;
    //MD5
    private String md5;
    //增加次数
    private int num;
    //剩余时间：秒 
    private String time;
    //已批改的实验报告总数
    private String sumTaskStatus;
    //学生总数
    private String countStudentId;
    
	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getReportBook() {
        return reportBook;
    }

    public void setReportBook(String reportBook) {
        this.reportBook = reportBook == null ? null : reportBook.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getBeginAttendance() {
        return beginAttendance;
    }

    public void setBeginAttendance(Date beginAttendance) {
        this.beginAttendance = beginAttendance;
    }

    public Date getLastAttendance() {
        return lastAttendance;
    }

    public void setLastAttendance(Date lastAttendance) {
        this.lastAttendance = lastAttendance;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

	public String getCourseTaskId() {
		return courseTaskId;
	}

	public void setCourseTaskId(String courseTaskId) {
		this.courseTaskId = courseTaskId == null ? null : courseTaskId.trim();
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className == null ? null : className.trim();
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName == null ? null : studentName.trim();
	}
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCourseResourceType() {
		return courseResourceType;
	}

	public void setCourseResourceType(Integer courseResourceType) {
		this.courseResourceType = courseResourceType;
	}
    public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId == null ? null : teacherId.trim();
	}

	public String getCourseResourceId() {
		return courseResourceId;
	}

	public void setCourseResourceId(String courseResourceId) {
		this.courseResourceId = courseResourceId == null ? null : courseResourceId.trim();
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName == null ? null : groupName.trim();
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5 == null ? null : md5.trim();
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time == null ? null : time.trim();
	}

	public String getSumTaskStatus() {
		return sumTaskStatus;
	}

	public void setSumTaskStatus(String sumTaskStatus) {
		this.sumTaskStatus = sumTaskStatus;
	}

	public String getCountStudentId() {
		return countStudentId;
	}

	public void setCountStudentId(String countStudentId) {
		this.countStudentId = countStudentId;
	}
	
}