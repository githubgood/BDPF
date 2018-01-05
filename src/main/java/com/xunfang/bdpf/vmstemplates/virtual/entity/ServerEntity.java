/**  
* @Title: ServerEntity.java
* @Package com.xunfang.cloud.model
* @Description: Compute Entity
* Copyright: Copyright (c) 2017 
* Company:深圳市讯方技术股份有限公司
* @author huangjf
* @date 2017年6月16日 上午10:36:11
* @version V1.0  
*/
package com.xunfang.bdpf.vmstemplates.virtual.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* @ClassName: ServerEntity
* @Description: Server Entity 云主机实体
* @author huangjf
* @date 2017年6月16日 上午10:36:11
*
*/
public class ServerEntity implements Serializable{

	
	/**
	* @Fields serialVersionUID : Version UID
	*/
	private static final long serialVersionUID = 74167827302203355L;

	
	/**
	* @Fields id : 唯一码
	*/
	private String id;
	
	
	/**
	* @Fields name : 名称
	*/
	private String name;
	
	
	/**
	* @Fields imageId : 镜像唯一码
	*/
	private String imageId;
	
	
	/**
	* @Fields flavorId : 主机类型唯一码
	*/
	private String flavorId;
	
	
	/**
	* @Fields keyName : 密钥对
	*/
	private String keyName;
	
	
	/**
	* @Fields securityGroupsName : 安全组名称
	*/
	private String securityGroupsName;
	
	
	/**
	* @Fields adminPass : 云主机管理员密码
	*/
	private String adminPass;
	
	
	/**
	* @Fields availabilityZone : 可用域
	*/
	private String availabilityZone;
	
	
	/**
	* @Fields count : 云主机数量
	*/
	private Integer count;
	
	
	/**
	* @Fields networkEntities : 网络集合数据
	*/
	private List<NetworkEntity> networkEntities = new ArrayList<NetworkEntity>();

	
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
	* @return imageId
	*/
	public String getImageId() {
		return imageId;
	}

	
	/**
	* @param imageId the imageId to set
	*/
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	
	/**
	* @return flavorId
	*/
	public String getFlavorId() {
		return flavorId;
	}

	
	/**
	* @param flavorId the flavorId to set
	*/
	public void setFlavorId(String flavorId) {
		this.flavorId = flavorId;
	}

	
	/**
	* @return keyName
	*/
	public String getKeyName() {
		return keyName;
	}

	
	/**
	* @param keyName the keyName to set
	*/
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	
	/**
	* @return securityGroups
	*/
	public String getSecurityGroupsName() {
		return securityGroupsName;
	}

	
	/**
	* @param securityGroups the securityGroups to set
	*/
	public void setSecurityGroupsName(String securityGroupsName) {
		this.securityGroupsName = securityGroupsName;
	}

	
	/**
	* @return networkEntities
	*/
	public List<NetworkEntity> getNetworkEntities() {
		return networkEntities;
	}

	
	/**
	* @param networkEntities the networkEntities to set
	*/
	public void setNetworkEntities(List<NetworkEntity> networkEntities) {
		this.networkEntities = networkEntities;
	}


	
	/**
	* @return adminPass
	*/
	public String getAdminPass() {
		return adminPass;
	}


	
	/**
	* @param adminPass the adminPass to set
	*/
	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}


	
	/**
	* @return availabilityZone
	*/
	public String getAvailabilityZone() {
		return availabilityZone;
	}


	
	/**
	* @param availabilityZone the availabilityZone to set
	*/
	public void setAvailabilityZone(String availabilityZone) {
		this.availabilityZone = availabilityZone;
	}


	
	/**
	* @return count
	*/
	public Integer getCount() {
		return count;
	}


	
	/**
	* @param count the count to set
	*/
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	
}
