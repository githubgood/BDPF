package com.xunfang.bdpf.datasite.site.entity;

public class Site {
	//主键id
    private String id;

    //资源名称
    private String name;

    //图片名称
    private String imgName;

    //资源描述
    private String siteAbstract;
    
	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

	public String getSiteAbstract() {
		return siteAbstract;
	}

	public void setSiteAbstract(String siteAbstract) {
		this.siteAbstract = siteAbstract;
	}

}