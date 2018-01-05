package com.xunfang.bdpf.datasite.area.entity;

public class Area {
	//主键id
    private String id;

    //数据站点目录表id
    private String dataSiteId;

    //摘要
    private String areAbstract;

    //关键字
    private String keyword;

    //数据提供方单位
    private String dataProviderEmployer;

    //更新日期
    private String updateDate;
    
    //发布日期
    private String releaseDate;
    
    //名称
    private String name;
    
    //标题
    private String title;

    //类型
    private String type;
    
    public String getId() {
        return id;
    }

	public void setId(String id) {
        this.id = id;
    }

    public String getDataSiteId() {
        return dataSiteId;
    }

    public void setDataSiteId(String dataSiteId) {
        this.dataSiteId = dataSiteId;
    }

    public String getAreAbstract() {
		return areAbstract;
	}

	public void setAreAbstract(String areAbstract) {
		this.areAbstract = areAbstract;
	}

	public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDataProviderEmployer() {
        return dataProviderEmployer;
    }

    public void setDataProviderEmployer(String dataProviderEmployer) {
        this.dataProviderEmployer = dataProviderEmployer;
    }

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	} 
	
}