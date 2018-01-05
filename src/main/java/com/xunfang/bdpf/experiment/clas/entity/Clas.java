package com.xunfang.bdpf.experiment.clas.entity;

/**
 * 
 * @ClassName Clas
 * @Description: 班级管理实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年6月9日 下午4:47:07
 * @version V1.0
 */
public class Clas {
	//主键
    private String id;

    //代号
    private String code;

    //名称
    private String name;

    //人数
    private Integer num;

    //描述
    private String description;
    
	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}