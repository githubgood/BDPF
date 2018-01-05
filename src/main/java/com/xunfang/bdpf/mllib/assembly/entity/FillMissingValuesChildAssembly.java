package com.xunfang.bdpf.mllib.assembly.entity;

import java.io.Serializable;

public class FillMissingValuesChildAssembly implements Serializable {
    private String id;

    private String bdpfMllibAssemblyFillMissingValuesId;

    private String columnName;

    private String dataType;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBdpfMllibAssemblyFillMissingValuesId() {
        return bdpfMllibAssemblyFillMissingValuesId;
    }

    public void setBdpfMllibAssemblyFillMissingValuesId(String bdpfMllibAssemblyFillMissingValuesId) {
        this.bdpfMllibAssemblyFillMissingValuesId = bdpfMllibAssemblyFillMissingValuesId == null ? null : bdpfMllibAssemblyFillMissingValuesId.trim();
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