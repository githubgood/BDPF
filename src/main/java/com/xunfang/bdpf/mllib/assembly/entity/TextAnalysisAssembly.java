package com.xunfang.bdpf.mllib.assembly.entity;

/**
 * 
 * @ClassName TextAnalysisAssembly
 * @Description: 分本分析实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年11月7日 上午10:45:59
 * @version V1.0
 */
public class TextAnalysisAssembly {
	//主键ID
    private String id;

    //组件ID
    private String bdpfMllibAssemblyId;

    //过滤列名称
    private String filterColumn;

    //过滤列类型
    private String filterType;
    
    //序号
    private Integer xh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBdpfMllibAssemblyId() {
        return bdpfMllibAssemblyId;
    }

    public void setBdpfMllibAssemblyId(String bdpfMllibAssemblyId) {
        this.bdpfMllibAssemblyId = bdpfMllibAssemblyId == null ? null : bdpfMllibAssemblyId.trim();
    }

    public String getFilterColumn() {
        return filterColumn;
    }

    public void setFilterColumn(String filterColumn) {
        this.filterColumn = filterColumn == null ? null : filterColumn.trim();
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType == null ? null : filterType.trim();
    }

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}
    
}