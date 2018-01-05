package com.xunfang.bdpf.vmstemplates.introduction.entity;

/**
 * @ClassName CreateMessage
 * @Description: 云主机创建实时信息实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年7月7日 下午4:14:04
 * @version V1.0
 */
public class CreateMessage {
	
	//总数
	private String total;
	
	//消息内容
	private String message;
	
	//完成数
	private String finish;
	
	//成功数
	private String over;
	
	//错误数
	private String error;

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public String getOver() {
		return over;
	}

	public void setOver(String over) {
		this.over = over;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
