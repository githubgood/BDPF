package com.xunfang.bdpf.vmstemplates.introduction.entity;

import java.util.List;

import com.xunfang.bdpf.experiment.task.entity.Task;

/**
 * 
 * @ClassName IntroData
 * @Description: 入门-添加云主机所需要的数据
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年7月17日 上午10:59:19
 * @version V1.0
 */
public class IntroData {
	//模板列表
	List<Images> imageList;
	//云主机类型数据集
	List<Flavors> flavorsList;
	//秘钥对数据集
	List<KeyPair> keyPairList;
	//	安全组数据集
	List<Secgroup> secgroupList;
	//可用域数据集
	List<Zone> zoneList;
	//网络列表
	List<Networks> networkList;
	
	//任务列表 
	List<Task> taskList;
	
	public List<Images> getImageList() {
		return imageList;
	}
	public void setImageList(List<Images> imageList) {
		this.imageList = imageList;
	}
	public List<Flavors> getFlavorsList() {
		return flavorsList;
	}
	public void setFlavorsList(List<Flavors> flavorsList) {
		this.flavorsList = flavorsList;
	}
	public List<KeyPair> getKeyPairList() {
		return keyPairList;
	}
	public void setKeyPairList(List<KeyPair> keyPairList) {
		this.keyPairList = keyPairList;
	}
	public List<Secgroup> getSecgroupList() {
		return secgroupList;
	}
	public void setSecgroupList(List<Secgroup> secgroupList) {
		this.secgroupList = secgroupList;
	}
	public List<Zone> getZoneList() {
		return zoneList;
	}
	public void setZoneList(List<Zone> zoneList) {
		this.zoneList = zoneList;
	}
	public List<Networks> getNetworkList() {
		return networkList;
	}
	public void setNetworkList(List<Networks> networkList) {
		this.networkList = networkList;
	}
	public List<Task> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
	
	
}
