package com.xunfang.bdpf.mllib.assembly.entity;

/**
 * 
 * @ClassName SplitAssembly
 * @Description: 拆分实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月19日 下午2:32:18
 * @version V1.0
 */
public class SplitAssembly {
	//主键
    private String id;

    //组件主键
    private String bdpfMllibAssemblyId;

    //拆分方式
    private Integer resolutionMode;

    //切分比例
    private Double segmentationRatio;

    //随机种子数
    private Integer random;

    //计算核心数
    private Integer coreNumber;

    //每个核心内存
    private Integer memory;

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

    public Integer getResolutionMode() {
        return resolutionMode;
    }

    public void setResolutionMode(Integer resolutionMode) {
        this.resolutionMode = resolutionMode;
    }

    public Double getSegmentationRatio() {
        return segmentationRatio;
    }

    public void setSegmentationRatio(Double segmentationRatio) {
        this.segmentationRatio = segmentationRatio;
    }

    public Integer getRandom() {
        return random;
    }

    public void setRandom(Integer random) {
        this.random = random;
    }

    public Integer getCoreNumber() {
        return coreNumber;
    }

    public void setCoreNumber(Integer coreNumber) {
        this.coreNumber = coreNumber;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }
}