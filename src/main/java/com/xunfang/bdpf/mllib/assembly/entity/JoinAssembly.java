package com.xunfang.bdpf.mllib.assembly.entity;

import java.io.Serializable;

public class JoinAssembly implements Serializable {
    private String id;

    private String bdpfMllibAssemblyLeftId;

    private String bdpfMllibAssemblyRightId;

    private Integer joinType;

    private String joinCondition;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBdpfMllibAssemblyLeftId() {
        return bdpfMllibAssemblyLeftId;
    }

    public void setBdpfMllibAssemblyLeftId(String bdpfMllibAssemblyLeftId) {
        this.bdpfMllibAssemblyLeftId = bdpfMllibAssemblyLeftId == null ? null : bdpfMllibAssemblyLeftId.trim();
    }

    public String getBdpfMllibAssemblyRightId() {
        return bdpfMllibAssemblyRightId;
    }

    public void setBdpfMllibAssemblyRightId(String bdpfMllibAssemblyRightId) {
        this.bdpfMllibAssemblyRightId = bdpfMllibAssemblyRightId == null ? null : bdpfMllibAssemblyRightId.trim();
    }

    public Integer getJoinType() {
        return joinType;
    }

    public void setJoinType(Integer joinType) {
        this.joinType = joinType;
    }

    public String getJoinCondition() {
        return joinCondition;
    }

    public void setJoinCondition(String joinCondition) {
        this.joinCondition = joinCondition == null ? null : joinCondition.trim();
    }
}