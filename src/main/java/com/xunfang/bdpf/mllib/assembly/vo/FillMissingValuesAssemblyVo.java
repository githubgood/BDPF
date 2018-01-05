package com.xunfang.bdpf.mllib.assembly.vo;

import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesChildAssembly;

import java.util.List;

/**
 * @author ylh
 * @version V1.0
 * @ClassName: FillMissingValuesAssemblyVo
 * @Description: 缺失值填充属性扩展类
 * Copyright: Copyright (c) 2017
 * Company:深圳市讯方技术股份有限公司
 * @date 2017/10/20
 */
public class FillMissingValuesAssemblyVo extends FillMissingValuesAssembly{

    private String[] columnName;
    private String[] columnType;
    private String[] fillMissingValuesAssemblyId;

    public void setColumnType(String[] columnType) {
        this.columnType = columnType;
    }

    public String[] getColumnType() {
        return columnType;
    }

    public void setColumnName(String[] columnName) {
        this.columnName = columnName;
    }

    public String[] getColumnName() {
        return columnName;
    }

    public void setFillMissingValuesAssemblyId(String[] fillMissingValuesAssemblyId) {
        this.fillMissingValuesAssemblyId = fillMissingValuesAssemblyId.length == 0 ? new String[]{""}:fillMissingValuesAssemblyId;
    }

    public String[] getFillMissingValuesAssemblyId() {
        return fillMissingValuesAssemblyId;
    }
}

