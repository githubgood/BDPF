package com.xunfang.bdpf.mllib.assembly.entity;

import java.io.Serializable;

public class StandardizationChildAssembly implements Serializable {
    private String id;

    private String bdpfMllibAssemblyStandardizationId;

    private String columnName;

    private String dataType;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBdpfMllibAssemblyStandardizationId() {
        return bdpfMllibAssemblyStandardizationId;
    }

    public void setBdpfMllibAssemblyStandardizationId(String bdpfMllibAssemblyStandardizationId) {
        this.bdpfMllibAssemblyStandardizationId = bdpfMllibAssemblyStandardizationId == null ? null : bdpfMllibAssemblyStandardizationId.trim();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }
}