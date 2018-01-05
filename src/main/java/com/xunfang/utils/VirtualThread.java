package com.xunfang.utils;

import java.util.List;

import com.xunfang.bdpf.experiment.grade.entity.StudentVirtual;
import com.xunfang.bdpf.login.entity.Servers;

/**
 * 
 * @ClassName VirtualThread
 * @Description: 虚拟机关机线程
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年9月4日 上午11:39:23
 * @version V1.0
 */
public class VirtualThread extends Thread{

	private List<StudentVirtual> studentVirtualListnew;
	private boolean flag = true;
	
	private String  token;

	public VirtualThread(List<StudentVirtual> studentVirtualListnew,String token) {
		super();
		this.studentVirtualListnew = studentVirtualListnew;
		this.token = token;
	}

	@Override
	public void run() {
		super.run();
		while (flag) {
			try {
				Thread.sleep(5000);
				if(studentVirtualListnew != null){
					for (int i = 0; i < studentVirtualListnew.size();) {
						StudentVirtual array_element = studentVirtualListnew.get(i);
						Servers cloudServer = OpenStackApi.cloudServer(array_element.getVirtualId(), token);
						if(cloudServer==null){
							break;
						}
						if(cloudServer.getRunstatus() != null){
							if(cloudServer.getRunstatus().equals("运行中")){
								//cloudServerAction 关机
//								System.out.println(cloudServer.getRunstatus());
								OpenStackApi.cloudServerAction("STOP", array_element.getVirtualId(), token);
								studentVirtualListnew.remove(i);
								Thread.sleep(1000);
							}else if(cloudServer.getRunstatus().equals("已停止")){
								flag = false;
								break;
							}else if(cloudServer.getRunstatus().equals("创建中")){
//								System.out.println(cloudServer.getRunstatus());
								i++;
							}
						}else{
							i++;
						}
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
}
