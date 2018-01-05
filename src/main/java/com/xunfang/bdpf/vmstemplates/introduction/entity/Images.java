package com.xunfang.bdpf.vmstemplates.introduction.entity;


/**
 * @ClassName Image
 * @Description: 模板实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年7月7日 下午3:57:47
 * @version V1.0
 */
public class Images {
	//模板ID
	private String id;

	//模板名称
	private String name;
	
	//模板状态
	private String status;
	
	//模板大小
	private String size;
	
	//模板路径
	private String path;

	//模板描述
	private String description;
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
