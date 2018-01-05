/**  
* @Title: KeypairEntity.java
* @Package com.xunfang.cloud.model
* @Description: Compute Entity
* Copyright: Copyright (c) 2017 
* Company:深圳市讯方技术股份有限公司
* @author huangjf
* @date 2017年6月16日 下午3:18:24
* @version V1.0  
*/
package com.xunfang.bdpf.vmstemplates.virtual.entity;

import java.io.Serializable;

/**
* @ClassName: KeypairEntity
* @Description: Keypair Entity 密钥对实体
* @author huangjf
* @date 2017年6月16日 下午3:18:24
*
*/
public class KeypairEntity implements Serializable {

	
	/**
	* @Fields serialVersionUID : Version UID
	*/
	private static final long serialVersionUID = -9028462370610098124L;
	
	
	/**
	* @Fields id : 唯一码
	*/
	private String id;
	
	
	/**
	* @Fields name : 密钥对名称
	*/
	private String name;
	
	
	/**
	* @Fields publicKey : 公共秘钥
	*/
	private String publicKey;
	
	
	/**
	* @Fields privateKey : 私钥
	*/
	private String privateKey;
	
	
	/**
	* @Fields fingerprint : 指纹
	*/
	private String fingerprint;

	
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
	* @return publicKey
	*/
	public String getPublicKey() {
		return publicKey;
	}

	
	/**
	* @param publicKey the publicKey to set
	*/
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	
	/**
	* @return privateKey
	*/
	public String getPrivateKey() {
		return privateKey;
	}

	
	/**
	* @param privateKey the privateKey to set
	*/
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	
	/**
	* @return fingerprint
	*/
	public String getFingerprint() {
		return fingerprint;
	}

	
	/**
	* @param fingerprint the fingerprint to set
	*/
	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}
	
	

}
