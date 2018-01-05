package com.xunfang.bdpf.login.entity;

/**
 * 
 * @ClassName Server
 * @Description: 虚拟机信息封装类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年7月5日 上午8:47:49
 * @version V1.0
 */
public class Servers {
	//虚拟机名字
	private String name;
	//虚拟机状态
	private String runstatus;
	//虚拟机ip
	private String ip;
	//id
	private String id;
	//运行总数
	private int runNum;
	//停止总数
	private int stopNum;
	//其他
	private int other;
	//VNC地址
	private String vnc;
	//镜像名称
	private String imageName;
	//VNC端口信息
	private String vncInfo;
	//虚拟机类型
	private String type;
	
	public String getRunstatus() {
		return runstatus;
	}

	public int getOther() {
		return other;
	}

	public void setOther(int other) {
		this.other = other;
	}

	public void setRunstatus(String runstatus) {
		this.runstatus = runstatus;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRunNum() {
		return runNum;
	}

	public void setRunNum(int runNum) {
		this.runNum = runNum;
	}

	public int getStopNum() {
		return stopNum;
	}

	public void setStopNum(int stopNum) {
		this.stopNum = stopNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVnc() {
		return vnc;
	}

	public void setVnc(String vnc) {
		this.vnc = vnc;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getVncInfo() {
		return vncInfo;
	}

	public void setVncInfo(String vncInfo) {
		this.vncInfo = vncInfo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
