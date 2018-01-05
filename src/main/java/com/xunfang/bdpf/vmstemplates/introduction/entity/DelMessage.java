package com.xunfang.bdpf.vmstemplates.introduction.entity;

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
public class DelMessage {
	
	//总数
	private String success;

	//完成数
	private String code;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
