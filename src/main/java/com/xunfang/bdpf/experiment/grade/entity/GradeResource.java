package com.xunfang.bdpf.experiment.grade.entity;

/**
 * 
 * @ClassName GradeResource
 * @Description: 学生成绩与课程资源关联model
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月23日 上午11:01:58
 * @version V1.0
 */
public class GradeResource {
	//主键
    private String id;
    //成绩表主键
    private String experimentGradeId;
    //课程资源主键
    private String courseResourceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getExperimentGradeId() {
        return experimentGradeId;
    }

    public void setExperimentGradeId(String experimentGradeId) {
        this.experimentGradeId = experimentGradeId == null ? null : experimentGradeId.trim();
    }

    public String getCourseResourceId() {
        return courseResourceId;
    }

    public void setCourseResourceId(String courseResourceId) {
        this.courseResourceId = courseResourceId == null ? null : courseResourceId.trim();
    }
}