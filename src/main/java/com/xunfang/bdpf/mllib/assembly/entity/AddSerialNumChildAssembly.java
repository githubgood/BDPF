package com.xunfang.bdpf.mllib.assembly.entity;

import java.io.Serializable;

public class AddSerialNumChildAssembly implements Serializable {
    private String id;

    private String bdpfMllibAssemblyAddSerialNumId;

    private String columnName;

    private String dataType;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBdpfMllibAssemblyAddSerialNumId() {
        return bdpfMllibAssemblyAddSerialNumId;
    }

    public void setBdpfMllibAssemblyAddSerialNumId(String bdpfMllibAssemblyAddSerialNumId) {
        this.bdpfMllibAssemblyAddSerialNumId = bdpfMllibAssemblyAddSerialNumId == null ? null : bdpfMllibAssemblyAddSerialNumId.trim();
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