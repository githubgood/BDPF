package com.xunfang.bdpf.mllib.assembly.entity;

import java.util.Date;

/**
 * 
 * @ClassName Assembly
 * @Description: 组件实体类 Copyright: Copyright (c) 2017 Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月19日 上午10:48:04
 * @version V1.0
 */
public class Assembly {
	// 主键
	private String id;

	// 实验表主键
	private String bdpfMllibExperimentId;

	// 组件库主键
	private String bdpfMllibAssemblyLibraryId;

	// 父组件ID
	private String parentId;

	// 组件名称
	private String name;

	// 用户ID
	private String account;

	// 运行状态
	private Integer state;

	// 开始时间
	private Date startTime;

	// 序号
	private Integer xh;

	// 是否组件 0：否 1：是
	private Integer isAssembly;

	// 组件字符串
	private String mainarr;

	// 线条字符串
	private String connects;
	
	//目标组件主键ID
	private String tid;
	
	//目标组件库ID
	private String targetId;
	
	//目标组件名称
	private String targetName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getBdpfMllibExperimentId() {
		return bdpfMllibExperimentId;
	}

	public void setBdpfMllibExperimentId(String bdpfMllibExperimentId) {
		this.bdpfMllibExperimentId = bdpfMllibExperimentId == null ? null : bdpfMllibExperimentId.trim();
	}

	public String getBdpfMllibAssemblyLibraryId() {
		return bdpfMllibAssemblyLibraryId;
	}

	public void setBdpfMllibAssemblyLibraryId(String bdpfMllibAssemblyLibraryId) {
		this.bdpfMllibAssemblyLibraryId = bdpfMllibAssemblyLibraryId == null ? null : bdpfMllibAssemblyLibraryId.trim();
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId == null ? null : parentId.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	public Integer getIsAssembly() {
		return isAssembly;
	}

	public void setIsAssembly(Integer isAssembly) {
		this.isAssembly = isAssembly;
	}

	public String getMainarr() {
		return mainarr;
	}

	public void setMainarr(String mainarr) {
		this.mainarr = mainarr;
	}

	public String getConnects() {
		return connects;
	}

	public void setConnects(String connects) {
		this.connects = connects;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	
}