package com.xunfang.bdpf.vmstemplates.introduction.entity;

/**
 * @ClassName Network
 * @Description: 网络信息实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年7月7日 下午4:26:55
 * @version V1.0
 */
public class Networks {

	//网络ID
	private String id;
	
	//网路名称
	private String name;
	
	//共享的
	private String shared;
	
	//管理员状态
	private String adminStateUp;
	
	//状态
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShared() {
		return shared;
	}

	public void setShared(String shared) {
		this.shared = shared;
	}

	public String getAdminStateUp() {
		return adminStateUp;
	}

	public void setAdminStateUp(String adminStateUp) {
		this.adminStateUp = adminStateUp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
