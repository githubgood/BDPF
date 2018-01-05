package com.xunfang.bdpf.experiment.exp.entity;
/**
 * 
 * @ClassName ExpCloud
 * @Description: 进入实验所需要的实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年7月6日 上午11:35:46
 * @version V1.0
 */
public class ExpCloud {

	/**
	 * id 云主机唯一ID
	 */
	private String id;
	/**
	 * IP地址
	 */
	private String ip;
	/**
	 * vnc地址
	 */
	private String url;
	/**
	 * 标签
	 */
	private String node;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
