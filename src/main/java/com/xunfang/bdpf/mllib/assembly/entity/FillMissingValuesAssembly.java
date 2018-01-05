package com.xunfang.bdpf.mllib.assembly.entity;

import java.io.Serializable;

public class FillMissingValuesAssembly implements Serializable {
    private String id;

    private String bdpfMllibAssemblyId;

    private String originValue;

    private String replaceValue;

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

    public String getOriginValue() {
        return originValue;
    }

    public void setOriginValue(String originValue) {
        this.originValue = originValue == null ? null : originValue.trim();
    }

    public String getReplaceValue() {
        return replaceValue;
    }

    public void setReplaceValue(String replaceValue) {
        this.replaceValue = replaceValue == null ? null : replaceValue.trim();
    }
}