package com.xunfang.bdpf.mllib.assembly.entity;

/**
 * 
 * @ClassName FeatureengineeringChildAssembly
 * @Description: 特征工程子表实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月31日 上午9:06:29
 * @version V1.0
 */
public class FeatureengineeringChildAssembly {
	//主键
    private String id;

    //特征工程表主键
    private String bdpfMllibAssemblyFeatureengineeringId;

    //列名
    private String name;

    //数据类型
    private String dataType;
    
    //序号
    private Integer xh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBdpfMllibAssemblyFeatureengineeringId() {
        return bdpfMllibAssemblyFeatureengineeringId;
    }

    public void setBdpfMllibAssemblyFeatureengineeringId(String bdpfMllibAssemblyFeatureengineeringId) {
        this.bdpfMllibAssemblyFeatureengineeringId = bdpfMllibAssemblyFeatureengineeringId == null ? null : bdpfMllibAssemblyFeatureengineeringId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}
    
}