package com.xunfang.bdpf.mllib.assembly.vo;

import com.xunfang.bdpf.mllib.assembly.entity.AddSerialNumAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.AddSerialNumChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesChildAssembly;

import java.util.List;

/**
 * @author ylh
 * @version V1.0
 * @ClassName: AddSerialNumAssemblyVo
 * @Description: 增加序号列属性扩展类
 * Copyright: Copyright (c) 2017
 * Company:深圳市讯方技术股份有限公司
 * @date 2017/10/20
 */
public class AddSerialNumAssemblyVo extends AddSerialNumAssembly{


    private String[] columnName;
    private String[] columnType;
    private String[] addSerialNumAssemblyId;

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

    public void setAddSerialNumAssemblyId(String[] addSerialNumAssemblyId) {
        this.addSerialNumAssemblyId = addSerialNumAssemblyId.length == 0 ? new String[]{""} : addSerialNumAssemblyId;
    }

    public String[] getAddSerialNumAssemblyId() {
        return addSerialNumAssemblyId;
    }
}

