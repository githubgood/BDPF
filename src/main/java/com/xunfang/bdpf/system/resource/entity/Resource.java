package com.xunfang.bdpf.system.resource.entity;
/**
 * 
 * @ClassName Resource
 * @Description: 实验资源类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年7月20日 下午5:08:42
 * @version V1.0
 */
public class Resource {
	//id 主键
    private String id;
    //课程实验资源
    private String classExperimentProportion;
    //开放实验资源 
    private String openExperimentProportion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getClassExperimentProportion() {
        return classExperimentProportion;
    }

    public void setClassExperimentProportion(String classExperimentProportion) {
        this.classExperimentProportion = classExperimentProportion == null ? null : classExperimentProportion.trim();
    }

    public String getOpenExperimentProportion() {
        return openExperimentProportion;
    }

    public void setOpenExperimentProportion(String openExperimentProportion) {
        this.openExperimentProportion = openExperimentProportion == null ? null : openExperimentProportion.trim();
    }
}