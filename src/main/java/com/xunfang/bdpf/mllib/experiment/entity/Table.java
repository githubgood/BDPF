package com.xunfang.bdpf.mllib.experiment.entity;

/**
 * 
 * @ClassName Table
 * @Description: 创建表实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月19日 上午9:25:36
 * @version V1.0
 */
public class Table {
	//主键
    private String id;

    //表名称
    private String name;

    //保存时长
    private Integer time;

    //文件路径
    private String path;

    //用户ID
    private String account;

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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }
}