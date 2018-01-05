package com.xunfang.bdpf.mllib.assembly.entity;

/**
 * 
 * @ClassName LibraryAssembly
 * @Description: 组件库实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月19日 下午2:36:07
 * @version V1.0
 */
public class LibraryAssembly {
	//主键
    private String id;

    //组件名称
    private String name;

    //父及第ID
    private String parentId;

    //是否组件
    private Integer isAssembly;

    //是否常用
    private Integer isCommon;

    //用户ID
    private String account;

    //组件类型
    private Integer type;

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public Integer getIsAssembly() {
        return isAssembly;
    }

    public void setIsAssembly(Integer isAssembly) {
        this.isAssembly = isAssembly;
    }

    public Integer getIsCommon() {
        return isCommon;
    }

    public void setIsCommon(Integer isCommon) {
        this.isCommon = isCommon;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}