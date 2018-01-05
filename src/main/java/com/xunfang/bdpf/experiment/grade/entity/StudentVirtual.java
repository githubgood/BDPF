package com.xunfang.bdpf.experiment.grade.entity;

/**
 * 
 * @ClassName StudentVirtual
 * @Description: 学生成绩与虚拟机关联model
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月23日 上午11:01:58
 * @version V1.0
 */
public class StudentVirtual {
	//主键ID
    private String id;

    //学生学号
    private String account;

    //虚拟机ID
    private String virtualId;

    //虚拟机名称
    private String virtualName;

    //班级ID
    private String classId;
    
    //分组id
    private String groupId;
    
    //课程任务主键ID
    private String courseTaskId;
    
    //虚拟机类型：0 分组实验 1 个人实验 2 开放实验
    private int type;
    
    //镜像模板ID
    private String imageId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getVirtualId() {
        return virtualId;
    }

    public void setVirtualId(String virtualId) {
        this.virtualId = virtualId == null ? null : virtualId.trim();
    }

    public String getVirtualName() {
        return virtualName;
    }

    public void setVirtualName(String virtualName) {
        this.virtualName = virtualName == null ? null : virtualName.trim();
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
    
}