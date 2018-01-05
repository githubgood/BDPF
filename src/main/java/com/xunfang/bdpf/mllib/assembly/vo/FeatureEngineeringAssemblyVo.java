package com.xunfang.bdpf.mllib.assembly.vo;

import com.xunfang.bdpf.mllib.assembly.entity.FeatureengineeringAssembly;

/**
 * @author ylh
 * @version V1.0
 * @ClassName:
 * @Description: 特征工程参数扩展类
 * Copyright: Copyright (c) 2017
 * Company:深圳市讯方技术股份有限公司
 * @date 2017/10/31
 */
public class FeatureEngineeringAssemblyVo extends FeatureengineeringAssembly{

    private String[] columnName;
    private String[] columnType;
    private String[] featureEngineeringChildAssemblyId;

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

    public void setFeatureEngineeringChildAssemblyId(String[] featureEngineeringChildAssemblyId) {
        this.featureEngineeringChildAssemblyId = featureEngineeringChildAssemblyId.length < 1 ? new String[]{""} : featureEngineeringChildAssemblyId;
    }

    public String[] getFeatureEngineeringChildAssemblyId() {
        return featureEngineeringChildAssemblyId;
    }
}
