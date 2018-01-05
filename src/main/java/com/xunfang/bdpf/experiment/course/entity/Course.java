package com.xunfang.bdpf.experiment.course.entity;

import java.util.Date;

/**
 * 
 * @ClassName Course
 * @Description: 课程资源实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年6月9日 下午4:47:07
 * @version V1.0
 */
public class Course {
	//主键
    private String id;

    //序号
    private Integer xh;

    //名称
    private String title;

    //课程资源分类：0 安装维护类 1 应用开发类 2 数据挖掘分析类 
    private Integer courseResourceType;

    //文件路径
    private String path;
    
    //文件加密
    private String md5;

    //文件类型
    private Integer fileType;

    //课程模板Id
    private String courseMirrorId;
    
    //课程模板名称
    private String courseMirrorName;

    //描述
    private String description;

    //创建人
    private String createUser;

    //创建时间
    private Date createTime;
    
    //父及第
    private String parentId;
    
	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }
    
    public String getCourseMirrorId() {
        return courseMirrorId;
    }

    public void setCourseMirrorId(String courseMirrorId) {
        this.courseMirrorId = courseMirrorId;
    }

    public String getCourseMirrorName() {
		return courseMirrorName;
	}

	public void setCourseMirrorName(String courseMirrorName) {
		this.courseMirrorName = courseMirrorName;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
   
}