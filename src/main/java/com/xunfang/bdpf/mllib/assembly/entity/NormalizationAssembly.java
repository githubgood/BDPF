package com.xunfang.bdpf.mllib.assembly.entity;

/**
 * 
 * @ClassName NormalizationAssembly
 * @Description: 归一化实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月19日 下午2:34:54
 * @version V1.0
 */
public class NormalizationAssembly {
	//主键
    private String id;

    //组件主键
    private String bdpfMllibAssemblyId;

    //计算核心数
    private Integer coreNumber;

    //每个核心内存
    private Integer memory;

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

    public Integer getCoreNumber() {
        return coreNumber;
    }

    public void setCoreNumber(Integer coreNumber) {
        this.coreNumber = coreNumber;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }
}