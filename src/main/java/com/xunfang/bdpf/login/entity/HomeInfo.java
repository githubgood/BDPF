package com.xunfang.bdpf.login.entity;
/**
 * 
 * @ClassName HomeInfo
 * @Description: TODO 首页网络数据与云主机信息实体
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年10月10日 下午3:15:54
 * @version V1.0
 */
public class HomeInfo {

	//网络总数
	private String allsize;
	//运行中数量
	private String start;
	//停止数量
	private String stop;
	//其它数量
	private String other;
	
	//内存最大容量 
	private String ram;
	//已使用
	private String ramUsed;
	
	//虚拟机创建实例数
	private String instances;
	//已创建
	private String instancesUsed;
	
	//cup最大核数
	private String cores;
	//已使用
	private String coresUsed;
	public String getAllsize() {
		return allsize;
	}
	public void setAllsize(String allsize) {
		this.allsize = allsize;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getStop() {
		return stop;
	}
	public void setStop(String stop) {
		this.stop = stop;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getRamUsed() {
		return ramUsed;
	}
	public void setRamUsed(String ramUsed) {
		this.ramUsed = ramUsed;
	}
	public String getInstances() {
		return instances;
	}
	public void setInstances(String instances) {
		this.instances = instances;
	}
	public String getInstancesUsed() {
		return instancesUsed;
	}
	public void setInstancesUsed(String instancesUsed) {
		this.instancesUsed = instancesUsed;
	}
	public String getCores() {
		return cores;
	}
	public void setCores(String cores) {
		this.cores = cores;
	}
	public String getCoresUsed() {
		return coresUsed;
	}
	public void setCoresUsed(String coresUsed) {
		this.coresUsed = coresUsed;
	}
	
	
	
	
}
