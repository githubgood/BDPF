package com.xunfang.bdpf.datasite.item.entity;

public class Item {
	//主键id
    private String id;

    //数据领域明细表id
    private String dataAreaId;

    //名称
    private String name;

    //类型
    private String type;

    //描述
    private String description;
    
    public String getId() {
        return id;
    }

	public void setId(String id) {
        this.id = id;
    }

    public String getDataAreaId() {
        return dataAreaId;
    }

    public void setDataAreaId(String dataAreaId) {
        this.dataAreaId = dataAreaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}