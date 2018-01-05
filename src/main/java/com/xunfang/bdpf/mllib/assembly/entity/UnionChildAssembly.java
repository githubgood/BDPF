package com.xunfang.bdpf.mllib.assembly.entity;

import java.io.Serializable;

public class UnionChildAssembly implements Serializable {
    private String id;

    private String bdpfMllibAssemblyUnionId;

    private String columnIn;

    private String columnOut;

    private String dataType;

    private Integer operationType;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBdpfMllibAssemblyUnionId() {
        return bdpfMllibAssemblyUnionId;
    }

    public void setBdpfMllibAssemblyUnionId(String bdpfMllibAssemblyUnionId) {
        this.bdpfMllibAssemblyUnionId = bdpfMllibAssemblyUnionId == null ? null : bdpfMllibAssemblyUnionId.trim();
    }

    public String getColumnIn() {
        return columnIn;
    }

    public void setColumnIn(String columnIn) {
        this.columnIn = columnIn == null ? null : columnIn.trim();
    }

    public String getColumnOut() {
        return columnOut;
    }

    public void setColumnOut(String columnOut) {
        this.columnOut = columnOut == null ? null : columnOut.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }
}