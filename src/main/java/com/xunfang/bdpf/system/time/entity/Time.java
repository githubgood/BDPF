package com.xunfang.bdpf.system.time.entity;
/**
 * 
 * @ClassName Time
 * @Description: 实验时间设置类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年7月20日 下午4:12:30
 * @version V1.0
 */
public class Time {
	//id 主键
    private String id;
    //最小时间
    private String minTime;
    //最大时间
    private String maxTime;
    //每次增加的时间
    private String addTime;
    //本系统最大人数
    private String maxNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMinTime() {
        return minTime;
    }

    public void setMinTime(String minTime) {
        this.minTime = minTime == null ? null : minTime.trim();
    }

    public String getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(String maxTime) {
        this.maxTime = maxTime == null ? null : maxTime.trim();
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }
    
    public String getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(String maxNumber) {
        this.maxNumber = maxNumber == null ? null : maxNumber.trim();
    }
}