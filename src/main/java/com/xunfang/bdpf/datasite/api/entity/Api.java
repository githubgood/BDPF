package com.xunfang.bdpf.datasite.api.entity;

public class Api {
	//主键id
    private String id;

    //数据领域明细表id
    private String dataAreaId;

    //接口描述
    private String apiDescription;

    //接口地址
    private String apiAddress;

    //支持格式
    private String apiFormat;

    //请求示例
    private String apiExample;
    
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

    public String getApiDescription() {
        return apiDescription;
    }

    public void setApiDescription(String apiDescription) {
        this.apiDescription = apiDescription;
    }

    public String getApiAddress() {
        return apiAddress;
    }

    public void setApiAddress(String apiAddress) {
        this.apiAddress = apiAddress;
    }

    public String getApiFormat() {
        return apiFormat;
    }

    public void setApiFormat(String apiFormat) {
        this.apiFormat = apiFormat;
    }

    public String getApiExample() {
        return apiExample;
    }

    public void setApiExample(String apiExample) {
        this.apiExample = apiExample;
    }
    
}