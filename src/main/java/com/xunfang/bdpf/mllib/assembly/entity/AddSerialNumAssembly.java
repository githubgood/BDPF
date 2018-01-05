package com.xunfang.bdpf.mllib.assembly.entity;

import java.io.Serializable;

public class AddSerialNumAssembly implements Serializable {
    private String id;

    private String bdpfMllibAssemblyId;

    private Integer coreNumber;

    private Integer memory;

    private String serialNum;

    private static final long serialVersionUID = 1L;

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

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }
}