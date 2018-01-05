package com.xunfang.bdpf.system.role.entity;

import java.util.Date;
/**
 * 
 * @ClassName Role
 * @Description: 角色实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年7月20日 下午4:52:22
 * @version V1.0
 */
public class Role {
	/**
	 * 角色id
	 */
    private String id;
    /**
	 * 角色名称
	 */
    private String name;
    /**
	 * 角色描述
	 */
    private String description;
    /**
	 * 角色创建时间
	 */
    private Date createTime;

    /**
     * 自定义属性，保存所拥有的权限列表
     */
    private String array;
    
    
    
    public String getArray() {
		return array;
	}

	public void setArray(String array) {
		this.array = array;
	}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}