package com.xunfang.bdpf.experiment.task.entity;
/**
 * 
 * @ClassName TaskLine
 * @Description: 班级课程任务关联model
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月23日 上午10:57:32
 * @version V1.0
 */
public class TaskLine {
	//主键
    private String id;
    //班级主键
    private String classId;
    //课程任务主键
    private String courseTaskId;

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

    public String getCourseTaskId() {
        return courseTaskId;
    }

    public void setCourseTaskId(String courseTaskId) {
        this.courseTaskId = courseTaskId == null ? null : courseTaskId.trim();
    }
}