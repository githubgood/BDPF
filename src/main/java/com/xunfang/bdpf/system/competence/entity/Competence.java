package com.xunfang.bdpf.system.competence.entity;

import java.util.Date;
/**
 * 
 * @ClassName Competence
 * @Description: 用户权限实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年7月20日 下午5:26:58
 * @version V1.0
 */
public class Competence{
	/**
	 * 权限id
	 */
    private String id;
    /**
	 * 权限id
	 */
    private String parentId;
    /**
	 * 权限名称
	 */
    private String name;
    /**
	 * 权限描述
	 */
    private String description;
    /**
	 * 权限状态   状态(0启用，1禁用)
	 */
    private Integer status;
    /**
	 * 权限创建时间
	 */
    private Date createTime;
    /**
	 * 权限代码
	 */
    private String resCode;
    /**
	 * 权限url地址
	 */
    private String reqMapping;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode == null ? null : resCode.trim();
    }

    public String getReqMapping() {
        return reqMapping;
    }

    public void setReqMapping(String reqMapping) {
        this.reqMapping = reqMapping == null ? null : reqMapping.trim();
    }
}