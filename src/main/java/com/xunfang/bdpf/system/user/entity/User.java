package com.xunfang.bdpf.system.user.entity;

import java.util.Date;
/**
 * 
 * @ClassName User
 * @Description: 用户信息类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年7月19日 下午3:59:50
 * @version V1.0
 */
public class User {
	/**
	 * 用户id
	 */
    private String id;
    /**
	 * 用户账号
	 */
    private String account;
    /**
	 * 用户密码
	 */
    private String password;
    /**
	 * 用户类型: 0 教师 1学生
	 */
    private Integer userType;
    /**
	 * 用户姓名
	 */
    private String name;
    /**
	 * 手机号码
	 */
    private String mobile;
    /**
	 * 邮箱
	 */
    private String email;
    /**
	 * 登录时间
	 */
    private Date loginTime;
    /**
	 * 最后一次的登录时间
	 */
    private Date lastLoginTime;
    /**
	 * 登录次数
	 */
    private Integer loginCount;
    /**
	 * 用户状态(0启用，1禁用)
	 */
    private Integer status;
    /**
	 * 用户创建时间
	 */
    private Date createTime;
    /**
	 * 学生所在班级id
	 */
    private String classId;
    /**
     * 老师所在班级id
     */
    private String tClassId;
    /**
	 * 角色id,方便操作
	 */
    private String roleId;
    
    private String sure_pwd;
    
    private String old_pwd;

    public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
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

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String gettClassId() {
		return tClassId;
	}

	public void settClassId(String tClassId) {
		this.tClassId = tClassId;
	}

	public String getSure_pwd() {
		return sure_pwd;
	}

	public void setSure_pwd(String sure_pwd) {
		this.sure_pwd = sure_pwd;
	}

	public String getOld_pwd() {
		return old_pwd;
	}

	public void setOld_pwd(String old_pwd) {
		this.old_pwd = old_pwd;
	}
   
}