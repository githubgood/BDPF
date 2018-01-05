/**  
* @Title: NetworkEntity.java
* @Package com.xunfang.cloud.model
* @Description: NetWork Entity
* Copyright: Copyright (c) 2017 
* Company:深圳市讯方技术股份有限公司
* @author huangjf
* @date 2017年6月16日 上午10:53:44
* @version V1.0  
*/
package com.xunfang.bdpf.vmstemplates.virtual.entity;

import java.io.Serializable;

/**
* @ClassName: NetworkEntity
* @Description: network Entity 网络实体
* @author huangjf
* @date 2017年6月16日 上午10:53:44
*
*/
public class NetworkEntity implements Serializable {

	
	/**
	* @Fields serialVersionUID : Version UID
	*/
	private static final long serialVersionUID = -4056382116473230108L;
	
	
	/**
	* @Fields id : 唯一码
	*/
	private String id;
	
	
	/**
	* @Fields name : 网络名称
	*/
	private String name;

	
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
	
	
	

}
