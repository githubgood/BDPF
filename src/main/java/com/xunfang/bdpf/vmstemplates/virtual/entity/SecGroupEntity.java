/**  
* @Title: SecGroupEntity.java
* @Package com.xunfang.cloud.model
* @Description: Compute Entity
* Copyright: Copyright (c) 2017 
* Company:深圳市讯方技术股份有限公司
* @author huangjf
* @date 2017年6月16日 下午3:41:11
* @version V1.0  
*/
package com.xunfang.bdpf.vmstemplates.virtual.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* @ClassName: SecGroupEntity
* @Description: SecGroup Entity 安全组实体
* @author huangjf
* @date 2017年6月16日 下午3:41:11
*
*/
public class SecGroupEntity implements Serializable {

	
	/**
	* @Fields serialVersionUID : serial Version UID
	*/
	private static final long serialVersionUID = 4017471666318115927L;
	
	
	/**
	* @Fields id : 安全组唯一码
	*/
	private String id;
	
	
	/**
	* @Fields name : 安全组名称
	*/
	private String name;
	
	
	/**
	* @Fields description : 安全组描述
	*/
	private String description;
	
	
	/**
	* @Fields projectId : 项目唯一码
	*/
	private String projectId;
	
	
	/**
	* @Fields ruleEntities : 规则实体集
	*/
	private List<RuleEntity> ruleEntities = new ArrayList<RuleEntity>();

	
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
	* @return projectId
	*/
	public String getProjectId() {
		return projectId;
	}

	
	/**
	* @param projectId the projectId to set
	*/
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	
	/**
	* @return ruleEntities
	*/
	public List<RuleEntity> getRuleEntities() {
		return ruleEntities;
	}

	
	/**
	* @param ruleEntities the ruleEntities to set
	*/
	public void setRuleEntities(List<RuleEntity> ruleEntities) {
		this.ruleEntities = ruleEntities;
	}
	
	

}
