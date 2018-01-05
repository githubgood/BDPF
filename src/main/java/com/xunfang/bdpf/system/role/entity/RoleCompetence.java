package com.xunfang.bdpf.system.role.entity;
/**
 * 
 * @ClassName RoleCompetence
 * @Description: 角色与权限和关联实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年7月20日 下午4:52:47
 * @version V1.0
 */
public class RoleCompetence {
	//id 主键
    private String id;
    //角色id
    private String roleId;
    //权限id
    private String competenceId;
    //类型
    private Integer type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(String competenceId) {
        this.competenceId = competenceId == null ? null : competenceId.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}