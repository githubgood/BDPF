package com.xunfang.bdpf.mllib.assembly.entity;

/**
 * 
 * @ClassName NormalizationChildAssembly
 * @Description: 归一化明细实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月19日 下午2:32:45
 * @version V1.0
 */
public class NormalizationChildAssembly {
	//主键
    private String id;

    //归一化主键
    private String bdpfMllibAssemblyNormalizationId;

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

    public String getBdpfMllibAssemblyNormalizationId() {
        return bdpfMllibAssemblyNormalizationId;
    }

    public void setBdpfMllibAssemblyNormalizationId(String bdpfMllibAssemblyNormalizationId) {
        this.bdpfMllibAssemblyNormalizationId = bdpfMllibAssemblyNormalizationId == null ? null : bdpfMllibAssemblyNormalizationId.trim();
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