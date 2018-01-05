package com.xunfang.bdpf.mllib.assembly.vo;

import com.xunfang.bdpf.mllib.assembly.entity.StandardizationAssembly;

/**
 * @author ylh
 * @version V1.0
 * @ClassName: StandardizationAssemblyVo
 * @Description: 标准化对象参数扩展类
 * Copyright: Copyright (c) 2017
 * Company:深圳市讯方技术股份有限公司
 * @date 2017/10/19
 */
public class StandardizationAssemblyVo extends StandardizationAssembly{

    private String[] columnName;
    private String[] columnType;
    private String[] standardizationChildAssemblyId;

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

    public void setStandardizationChildAssemblyId(String[] standardizationChildAssemblyId) {
        this.standardizationChildAssemblyId = standardizationChildAssemblyId.length == 0 ? new String[]{""} : standardizationChildAssemblyId;
    }

    public String[] getStandardizationChildAssemblyId() {
        return standardizationChildAssemblyId;
    }
}
