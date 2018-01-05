package com.xunfang.bdpf.experiment.plan.entity;
/**
 * 
 * @ClassName PlanLine
 * @Description: 课程教学计划子表
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月9日 下午5:45:54
 * @version V1.0
 */
public class PlanLine {
	//主键
    private String id;

    //课程教学计划主键
    private String teachingPlanId;

    //课程资源类型
    private int courseResourceType;
    
	private String courseResourceId;
 
    //课程资源名称
    private String title;

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTeachingPlanId() {
        return teachingPlanId;
    }

    public void setTeachingPlanId(String teachingPlanId) {
        this.teachingPlanId = teachingPlanId == null ? null : teachingPlanId.trim();
    }

    public String getCourseResourceId() {
        return courseResourceId;
    }

    public void setCourseResourceId(String courseResourceId) {
        this.courseResourceId = courseResourceId == null ? null : courseResourceId.trim();
    }
    
    public int getCourseResourceType() {
		return courseResourceType;
	}

	public void setCourseResourceType(int courseResourceType) {
		this.courseResourceType = courseResourceType;
	}
	
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
   
}