package com.xunfang.bdpf.mllib.assembly.entity;

import java.io.Serializable;

public class RandomSamplingAssembly implements Serializable {
    private String id;

    private String bdpfMllibAssemblyId;

    private Integer sampleSize;

    private Double sampleRatio;

    private Integer sampleReplace;

    private Integer randomSeed;

    private Integer coreNum;

    private Integer memSizePercore;

    private static final long serialVersionUID = 1L;

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

    public Integer getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(Integer sampleSize) {
        this.sampleSize = sampleSize;
    }

    public Double getSampleRatio() {
        return sampleRatio;
    }

    public void setSampleRatio(Double sampleRatio) {
        this.sampleRatio = sampleRatio;
    }

    public Integer getSampleReplace() {
        return sampleReplace;
    }

    public void setSampleReplace(Integer sampleReplace) {
        this.sampleReplace = sampleReplace;
    }

    public Integer getRandomSeed() {
        return randomSeed;
    }

    public void setRandomSeed(Integer randomSeed) {
        this.randomSeed = randomSeed;
    }

    public Integer getCoreNum() {
        return coreNum;
    }

    public void setCoreNum(Integer coreNum) {
        this.coreNum = coreNum;
    }

    public Integer getMemSizePercore() {
        return memSizePercore;
    }

    public void setMemSizePercore(Integer memSizePercore) {
        this.memSizePercore = memSizePercore;
    }
}