/**  
* @Title: GroupEntity.java
* @Package com.xunfang.cloud.model
* @Description: Identity Endity
* Copyright: Copyright (c) 2017 
* Company:深圳市讯方技术股份有限公司
* @author huangjf
* @date 2017年6月15日 上午11:16:12
* @version V1.0  
*/
package com.xunfang.bdpf.vmstemplates.virtual.entity;

import java.io.Serializable;

/**
* @ClassName: GroupEntity
* @Description: Identity Endity Group 组实体
* @author huangjf
* @date 2017年6月15日 上午11:16:12
*
*/
public class GroupEntity implements Serializable{

	
	/**
	* @Fields serialVersionUID : Initialize UID
	*/
	private static final long serialVersionUID = -5098895083801262708L;

	
	/**
	* @Fields id : 唯一码
	*/
	private String id;
	
	
	/**
	* @Fields name : 组名称
	*/
	private String name;
	
	
	/**
	* @Fields description : 组描述
	*/
	private String description;
	
	
	/**
	* @Fields domainId : 域
	*/
	private String domainId;
	
	
	/**
	* @return id
	*/
	public String getId() {
		return id;
	}

	
	/**
	* @param id the id to set
	*/
	public void setId(String id) {
		this.id = id;
	}

	
	/**
	* @return name
	*/
	public String getName() {
		return name;
	}

	
	/**
	* @param name the name to set
	*/
	public void setName(String name) {
		this.name = name;
	}

	
	/**
	* @return description
	*/
	public String getDescription() {
		return description;
	}

	
	/**
	* @param description the description to set
	*/
	public void setDescription(String description) {
		this.description = description;
	}

	
	/**
	* @return domainId
	*/
	public String getDomainId() {
		return domainId;
	}

	
	/**
	* @param domainId the domainId to set
	*/
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	
	
}
