package com.xunfang.utils;
/**
 * 
 * @ClassName Parms
 * @Description: http分页传参对象
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年7月3日 上午10:52:52
 * @version V1.0
 */
public class Parms {

	//关键字
	private String keywords;
	//从第几条开始
	private  int pageNo;
	//每页显示几条数据
	private int pageSize;
	
	private String account;
	
	private String password;
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
