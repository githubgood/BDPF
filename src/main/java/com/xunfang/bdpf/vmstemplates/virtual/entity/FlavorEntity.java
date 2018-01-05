/**  
* @Title: FlavorEntity.java
* @Package com.xunfang.cloud.model
* @Description: Compute Entity
* Copyright: Copyright (c) 2017 
* Company:深圳市讯方技术股份有限公司
* @author huangjf
* @date 2017年6月16日 上午9:52:46
* @version V1.0  
*/
package com.xunfang.bdpf.vmstemplates.virtual.entity;

import java.io.Serializable;

/**
* @ClassName: FlavorEntity
* @Description: Flavor Entity 主机类型实体
* @author huangjf
* @date 2017年6月16日 上午9:52:46
*
*/
public class FlavorEntity implements Serializable {

	
	/**
	* @Fields serialVersionUID : version UID
	*/
	private static final long serialVersionUID = -719853135299149053L;
	
	
	/**
	* @Fields id : 主机类型唯一码
	*/
	private String id;
	
	
	/**
	* @Fields name : 名称
	*/
	private String name;
	
	
	/**
	* @Fields ram : 内存
	*/
	private Integer ram;
	
	
	/**
	* @Fields vcpus : 虚拟cpu数
	*/
	private Integer vcpus;
	
	
	/**
	* @Fields disk : 根磁盘
	*/
	private Integer disk;
	
	
	/**
	* @Fields swap : 交换磁盘
	*/
	private Integer swap;
	
	
	/**
	* @Fields rxtxFactor : RX/TX因子
	*/
	private Float rxtxFactor;
	
	
	/**
	* @Fields ephemeral : 临时磁盘
	*/
	private int ephemeral;
	
	
	/**
	* @Fields rxtxQuota : RX(出)/TX(进)配额
	*/
	private int rxtxQuota;
	
	
	/**
	* @Fields rxtxCap : RX(出)/TX(进)流量量
	*/
	private int rxtxCap;
	
	
	/**
	* @Fields isPublic : 是否公共
	*/
	private Boolean isPublic;
	
	
	/**
	* @Fields disabled : 是否禁用
	*/
	private Boolean disabled;

	
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
	* @return ram
	*/
	public Integer getRam() {
		return ram;
	}

	
	/**
	* @param ram the ram to set
	*/
	public void setRam(Integer ram) {
		this.ram = ram;
	}

	
	/**
	* @return vcpus
	*/
	public Integer getVcpus() {
		return vcpus;
	}

	
	/**
	* @param vcpus the vcpus to set
	*/
	public void setVcpus(Integer vcpus) {
		this.vcpus = vcpus;
	}

	
	/**
	* @return disk
	*/
	public Integer getDisk() {
		return disk;
	}

	
	/**
	* @param disk the disk to set
	*/
	public void setDisk(Integer disk) {
		this.disk = disk;
	}

	
	/**
	* @return swap
	*/
	public Integer getSwap() {
		return swap;
	}

	
	/**
	* @param swap the swap to set
	*/
	public void setSwap(Integer swap) {
		this.swap = swap;
	}

	
	/**
	* @return rxtxFactor
	*/
	public Float getRxtxFactor() {
		return rxtxFactor;
	}

	
	/**
	* @param rxtxFactor the rxtxFactor to set
	*/
	public void setRxtxFactor(Float rxtxFactor) {
		this.rxtxFactor = rxtxFactor;
	}

	
	/**
	* @return ephemeral
	*/
	public int getEphemeral() {
		return ephemeral;
	}

	
	/**
	* @param ephemeral the ephemeral to set
	*/
	public void setEphemeral(int ephemeral) {
		this.ephemeral = ephemeral;
	}

	
	/**
	* @return rxtxQuota
	*/
	public int getRxtxQuota() {
		return rxtxQuota;
	}

	
	/**
	* @param rxtxQuota the rxtxQuota to set
	*/
	public void setRxtxQuota(int rxtxQuota) {
		this.rxtxQuota = rxtxQuota;
	}

	
	/**
	* @return rxtxCap
	*/
	public int getRxtxCap() {
		return rxtxCap;
	}

	
	/**
	* @param rxtxCap the rxtxCap to set
	*/
	public void setRxtxCap(int rxtxCap) {
		this.rxtxCap = rxtxCap;
	}

	
	/**
	* @return isPublic
	*/
	public Boolean getIsPublic() {
		return isPublic;
	}

	
	/**
	* @param isPublic the isPublic to set
	*/
	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	
	/**
	* @return disabled
	*/
	public Boolean getDisabled() {
		return disabled;
	}

	
	/**
	* @param disabled the disabled to set
	*/
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	
	

}
