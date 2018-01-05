package com.xunfang.bdpf.system.user.entity;
/**
 * 
 * @ClassName UserRole
 * @Description: 用户与角色的关联类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年7月19日 下午4:01:18
 * @version V1.0
 */
public class UserRole {
	/**
	 * 用户与角色关联表id
	 */
    private String id;
    /**
	 * 用户id
	 */
    private String userId;
    /**
	 * 角色id
	 */
    private String roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}