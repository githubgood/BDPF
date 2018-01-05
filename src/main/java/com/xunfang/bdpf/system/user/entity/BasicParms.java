package com.xunfang.bdpf.system.user.entity;

/**
 * 
 * @ClassName BasicParms
 * @Description: 班级、学生、教师信息实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年7月18日 下午3:52:48
 * @version V1.0
 */
public class BasicParms {

	//查询条件:班级名称、学生名称、教师名称
	private String keywords;
	
	//从第几条开始取数据
	private String pageNo;
	
	//每页显示多少条数据
	private String pageSize;
	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
}
