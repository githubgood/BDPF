package com.xunfang.bdpf.experiment.plan.entity;
/**
 * 
 * @ClassName Plan
 * @Description: 课程教学计划实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月9日 下午5:44:01
 * @version V1.0
 */
public class Plan {
	//主键
	private String id;

	//名称
	private String name;

	//计划课时
	private String learningTime;

	//人数
	private Integer num;

	//课程类型
	private Integer courseType;

	//描述
	private String description;

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

	public String getLearningTime() {
		return learningTime;
	}

	public void setLearningTime(String learningTime) {
		this.learningTime = learningTime == null ? null : learningTime.trim();
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getCourseType() {
		return courseType;
	}

	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

}