package com.xunfang.bdpf.experiment.task.entity;
/**
 * 
 * @ClassName GroupResource
 * @Description: 学生成绩与课程资源关联model
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月23日 上午10:53:43
 * @version V1.0
 */
public class GroupResource {
	//主键
    private String id;
    //学生成绩主键
    private String experimentGroupId;
    //课程资源主键
    private String courseResourceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getExperimentGroupId() {
        return experimentGroupId;
    }

    public void setExperimentGroupId(String experimentGroupId) {
        this.experimentGroupId = experimentGroupId == null ? null : experimentGroupId.trim();
    }

    public String getCourseResourceId() {
        return courseResourceId;
    }

    public void setCourseResourceId(String courseResourceId) {
        this.courseResourceId = courseResourceId == null ? null : courseResourceId.trim();
    }
}