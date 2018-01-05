package com.xunfang.bdpf.vmstemplates.introduction.entity;

/**
 * @ClassName KeyPair
 * @Description: 秘钥对实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年7月7日 下午4:26:19
 * @version V1.0
 */
public class KeyPair {
	
	//密钥对主键ID
	private String id;
	
	//密钥对名称
	private String name;
	
	//公开密钥
	private String publicKey;
	
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
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	
}
