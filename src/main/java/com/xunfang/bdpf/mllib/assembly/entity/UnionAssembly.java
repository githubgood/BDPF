package com.xunfang.bdpf.mllib.assembly.entity;

import java.io.Serializable;

public class UnionAssembly implements Serializable {
    private String id;

    private String bdpfMllibAssemblyIdLeft;

    private String bdpfMllibAssemblyIdRight;

    private String whereConditionLeft;

    private String whereConditionRight;

    private Integer deduplication;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBdpfMllibAssemblyIdLeft() {
        return bdpfMllibAssemblyIdLeft;
    }

    public void setBdpfMllibAssemblyIdLeft(String bdpfMllibAssemblyIdLeft) {
        this.bdpfMllibAssemblyIdLeft = bdpfMllibAssemblyIdLeft == null ? null : bdpfMllibAssemblyIdLeft.trim();
    }

    public String getBdpfMllibAssemblyIdRight() {
        return bdpfMllibAssemblyIdRight;
    }

    public void setBdpfMllibAssemblyIdRight(String bdpfMllibAssemblyIdRight) {
        this.bdpfMllibAssemblyIdRight = bdpfMllibAssemblyIdRight == null ? null : bdpfMllibAssemblyIdRight.trim();
    }

    public String getWhereConditionLeft() {
        return whereConditionLeft;
    }

    public void setWhereConditionLeft(String whereConditionLeft) {
        this.whereConditionLeft = whereConditionLeft == null ? null : whereConditionLeft.trim();
    }

    public String getWhereConditionRight() {
        return whereConditionRight;
    }

    public void setWhereConditionRight(String whereConditionRight) {
        this.whereConditionRight = whereConditionRight == null ? null : whereConditionRight.trim();
    }

    public Integer getDeduplication() {
        return deduplication;
    }

    public void setDeduplication(Integer deduplication) {
        this.deduplication = deduplication;
    }
}