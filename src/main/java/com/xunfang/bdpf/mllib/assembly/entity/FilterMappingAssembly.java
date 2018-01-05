package com.xunfang.bdpf.mllib.assembly.entity;

/**
 * 
 * @ClassName FilterMappingAssembly
 * @Description: 过滤与映射表实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月30日 上午10:45:49
 * @version V1.0
 */
public class FilterMappingAssembly {
	//主键ID
    private String id;

    //组件ID
    private String bdpfMllibAssemblyId;

    //过滤条件
    private String filtrationCondition;

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

    public String getFiltrationCondition() {
        return filtrationCondition;
    }

    public void setFiltrationCondition(String filtrationCondition) {
        this.filtrationCondition = filtrationCondition == null ? null : filtrationCondition.trim();
    }
}