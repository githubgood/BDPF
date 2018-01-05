package com.xunfang.bdpf.vmstemplates.introduction.entity;

/**
 * @ClassName Flavors
 * @Description: 创建云主机类型实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年7月7日 下午4:14:04
 * @version V1.0
 */
public class CreateFlavors {
	
	//云主机类型名称
	private String name;
	
	//虚拟内核
	private String vcpus;
	
	//内存
	private String ram;
	
	//磁盘统计
	private String disk;
	
	//交换空间
	private String swap;
	
	//公有
	private String publics;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVcpus() {
		return vcpus;
	}

	public void setVcpus(String vcpus) {
		this.vcpus = vcpus;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getDisk() {
		return disk;
	}

	public void setDisk(String disk) {
		this.disk = disk;
	}

	public String getSwap() {
		return swap;
	}

	public void setSwap(String swap) {
		this.swap = swap;
	}

	public String getPublics() {
		return publics;
	}

	public void setPublics(String publics) {
		this.publics = publics;
	}
	
}
