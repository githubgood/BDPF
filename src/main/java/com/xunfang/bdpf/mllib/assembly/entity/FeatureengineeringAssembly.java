package com.xunfang.bdpf.mllib.assembly.entity;

/**
 * 
 * @ClassName FeatureengineeringAssembly
 * @Description: 特征工程表实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月31日 上午9:05:45
 * @version V1.0
 */
public class FeatureengineeringAssembly {
	//主键
    private String id;

    //组件表主键
    private String bdpfMllibAssemblyId;

    //选择列名称
    private String name;

    //比例系数
    private String proportionalityCoefficient;

    //等距离散
    private String equidistantDispersion;

    //等距离散区间
    private String equidistantDispersionInterval;

    //K值
    private String k;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBdpfMllibAssemblyId() {
        return bdpfMllibAssemblyId;
    }

    public void setBdpfMllibAssemblyId(String bdpfMllibAssemblyId) {
        this.bdpfMllibAssemblyId = bdpfMllibAssemblyId == null ? null : bdpfMllibAssemblyId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getProportionalityCoefficient() {
        return proportionalityCoefficient;
    }

    public void setProportionalityCoefficient(String proportionalityCoefficient) {
        this.proportionalityCoefficient = proportionalityCoefficient == null ? null : proportionalityCoefficient.trim();
    }

    public String getEquidistantDispersion() {
        return equidistantDispersion;
    }

    public void setEquidistantDispersion(String equidistantDispersion) {
        this.equidistantDispersion = equidistantDispersion == null ? null : equidistantDispersion.trim();
    }

    public String getEquidistantDispersionInterval() {
        return equidistantDispersionInterval;
    }

    public void setEquidistantDispersionInterval(String equidistantDispersionInterval) {
        this.equidistantDispersionInterval = equidistantDispersionInterval == null ? null : equidistantDispersionInterval.trim();
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k == null ? null : k.trim();
    }
}