package com.xunfang.bdpf.mllib.assembly.vo;

import com.xunfang.bdpf.mllib.assembly.entity.JoinAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.JoinChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.UnionAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.UnionChildAssembly;

import java.util.List;

/**
 * @author ylh
 * @version V1.0
 * @ClassName: UnionAssemblyVo
 * @Description: union连接属性扩展类
 * Copyright: Copyright (c) 2017
 * Company:深圳市讯方技术股份有限公司
 * @date 2017/10/20
 */
public class UnionAssemblyVo extends UnionAssembly {


    private String[] columnName;
    private String[] columnType;
    private String[] unionChildAssemblyId;
    private int[] operationType;

    public void setOperationType(int[] operationType) {
        this.operationType = operationType;
    }

    public int[] getOperationType() {
        return operationType;
    }

    public void setColumnName(String[] columnName) {
        this.columnName = columnName;
    }

    public String[] getColumnName() {
        return columnName;
    }

    public void setColumnType(String[] columnType) {
        this.columnType = columnType;
    }

    public String[] getColumnType() {
        return columnType;
    }

    public void setUnionChildAssemblyId(String[] unionChildAssemblyId) {
        this.unionChildAssemblyId = unionChildAssemblyId.length == 0 ? new String[]{""} : unionChildAssemblyId;
    }

    public String[] getUnionChildAssemblyId() {
        return unionChildAssemblyId;
    }
}
