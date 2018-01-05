package com.xunfang.utils;

/**
 * 
 * @ClassName Feedback
 * @Description: 通用反馈信息, 记录是否成功, 以及相应的信息提示
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年5月26日 上午9:30:50
 * @version V1.0
 */
public class Feedback {

	//是否返回成功
	private boolean successful;
	//返回消息
	private String message;
	//返回ID
	private String id;
	//返回路径
	private String path;
	//返回类型
	private int type;
	
	private Object data;
	
	/**
	 * 
	  * 创建一个新的实例 Feedback. 

	  * @param successful
	  * @param message
	 */
	public Feedback(boolean successful, String message) {
		this.successful = successful;
		this.message = message;
	}

	public Feedback(boolean successful,String message,Object data){
		this.successful = successful;
		this.message = message;
		this.data = data;
	}
	
	/**
	 * 
	  * 创建一个新的实例 Feedback. 
	 */
	public Feedback() {
		
	}
	
	public boolean isSuccessful() {
		return successful;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	public Object getData() {
		return data;
	}
}