package com.xunfang.bdpf.mllib.assembly.entity;

/**
 * 
 * @ClassName FilterMappingChildAssembly
 * @Description: 过滤与映射子表实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月30日 上午10:45:58
 * @version V1.0
 */
public class FilterMappingChildAssembly {
	//主键ID
    private String id;

    //过滤与映射表主键ID
    private String bdpfMllibAssemblyFilterMappingId;

    //字段名称
    private String name;

    //字段类型
    private String dataType;

    //序号
    private int xh;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBdpfMllibAssemblyFilterMappingId() {
        return bdpfMllibAssemblyFilterMappingId;
    }

    public void setBdpfMllibAssemblyFilterMappingId(String bdpfMllibAssemblyFilterMappingId) {
        this.bdpfMllibAssemblyFilterMappingId = bdpfMllibAssemblyFilterMappingId == null ? null : bdpfMllibAssemblyFilterMappingId.trim();
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

	public int getXh() {
		return xh;
	}

	public void setXh(int xh) {
		this.xh = xh;
	}
    
}