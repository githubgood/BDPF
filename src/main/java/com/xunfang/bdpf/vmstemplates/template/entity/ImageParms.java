package com.xunfang.bdpf.vmstemplates.template.entity;

/**
 * @ClassName DelMessage
 * @Description: 云主机删除返回消息实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年7月7日 下午4:14:04
 * @version V1.0
 */
public class ImageParms {
	
	//文件存放路径
	private String imageUrl;

	//模板名称
	private String name;
	
	private String id;
	
	private String description;
	
	private String textfield;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTextfield() {
		return textfield;
	}

	public void setTextfield(String textfield) {
		this.textfield = textfield;
	}
	
}
