package com.xunfang.bdpf.datasite.resources.entity;

public class Resources {
	//主键id
    private String id;

    //数据领域明细表id
    private String dataAreaId;

    //资源名称
    private String name;

    //资料类型
    private Integer type;
    
    //资料大小
    private double size;
    
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}
    
}