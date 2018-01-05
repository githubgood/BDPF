package com.xunfang.bdpf.vmstemplates.introduction.entity;

/**
 * @ClassName Secgroup
 * @Description: 安全组实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年7月7日 下午4:26:33
 * @version V1.0
 */
public class Secgroup {

	//安全组主键ID
	private String id;
	
	//安全组名称
	private String name;

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
	
}
