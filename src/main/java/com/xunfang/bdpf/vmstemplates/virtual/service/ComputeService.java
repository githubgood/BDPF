/**  
* @Title: ComputeService.java
* @Package com.xunfang.cloud.compute
* @Description: 计算服务
* Copyright: Copyright (c) 2017 
* Company:深圳市讯方技术股份有限公司
* @author huangjf
* @date 2017年6月26日 上午11:00:29
* @version V1.0  
*/
package com.xunfang.bdpf.vmstemplates.virtual.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.openstack4j.api.Builders;
import org.openstack4j.model.compute.ServerCreate;

import com.xunfang.bdpf.vmstemplates.virtual.entity.NetworkEntity;
import com.xunfang.bdpf.vmstemplates.virtual.entity.ServerEntity;
import com.xunfang.utils.OpenStackApi;
import com.xunfang.utils.ThreadUtil;

/**
* @ClassName: ComputeService
* @Description: 主机创建线程服务，利用线程池执行创建云主机任务并监控完成情况
* @author huangjf
* @date 2017年6月26日 上午11:00:29
*
*/
public class ComputeService {
	
	private static Logger log = Logger.getLogger(ComputeService.class);
	
	/**
	* @Fields serverEntity : 云主机实体
	*/
	private ServerEntity serverEntity;
	
	/**
	* @Fields token : 身份token
	*/
	private String token;

	/**
	* 创建一个新的实例 ComputeService.
	*
	* @param serverEntity 主机实体参数
	* @param token 身份token
	*/
	public ComputeService(ServerEntity serverEntity, String token) {
		this.serverEntity = serverEntity;
		this.token = token;
	}
	
	/**
	* @Title: excute
	* @Description: 主机创建服务执行，返回线程id
	* @return
	* String threadid
	*/
	public String excute(){
		String threadid = UUID.randomUUID().toString().replaceAll("-", "");
		service(threadid);
		return threadid;
	}
	
	/**
	* @Title: service
	* @Description: 主机服务启动，构造线程池并装载执行
	* @param threadid
	* void
	*/
	private void service(String threadid){
		ThreadUtil.Initialize(threadid);
		ThreadUtil.setTotalCount(serverEntity.getCount(), threadid);
		ExecutorService service = Executors.newFixedThreadPool(10);
		if(serverEntity.getCount()>1){
			for(int i = 0; i < serverEntity.getCount(); i++){
				service.execute(new ServerRunnable(threadid, serverEntity, token, (i+1)));
			}
		}else{
			service.execute(new ServerRunnable(threadid, serverEntity, token, 0));
		}
		service.shutdown();
	}
	
	/**
	* @ClassName: ServerRunnable
	* @Description: 主机创建服务线程实现类
	* @author huangjf
	* @date 2017年6月26日 上午11:00:29
	*
	*/  
	class ServerRunnable implements Runnable{

		
		/**
		* @Fields threadid : 线程唯一码
		*/
		private String threadid;

		
		/**
		* @Fields serverEntity : 主机实体
		*/
		private ServerEntity serverEntity;
		
		
		/**
		* @Fields serverNum : 主机号
		*/
		private int serverNum;
		
		
		/**
		* @Fields token : 身份token
		*/
		private String token;
		
		
		
		/**
		* 创建一个新的实例 ServerRunnable.
		*
		* @param threadid 线程唯一码
		* @param serverEntity 主机实体
		* @param token 身份token
		* @param serverNum 主机号
		*/
		public ServerRunnable(String threadid, ServerEntity serverEntity, String token, int serverNum) {
			this.threadid = threadid;
			this.serverEntity = serverEntity;
			this.token = token;
			this.serverNum = serverNum;
		}

		@Override
		public void run() {
			List<String> networkParams = new ArrayList<String>();
			if(serverEntity != null && serverEntity.getNetworkEntities() != null && serverEntity.getNetworkEntities().size() > 0){
				for(NetworkEntity network : serverEntity.getNetworkEntities())
					networkParams.add(network.getId());
			}
			try {
				String serverName = serverEntity.getName();
				if(serverNum > 0){
					serverName = serverEntity.getName() + "-" + serverNum;
				}
				ServerCreate sc = Builders.server().name(serverName)
						.flavor(serverEntity.getFlavorId())
						.image(serverEntity.getImageId())
						.networks(networkParams)
						.addSecurityGroup(serverEntity.getSecurityGroupsName())
						.availabilityZone(serverEntity.getAvailabilityZone())
						.addAdminPass(serverEntity.getAdminPass())
						.keypairName(serverEntity.getKeyName()).build();
				    OpenStackApi.cloudServerCreate(sc,token);
					ThreadUtil.refresh(threadid, 1);
			} catch (Exception e) {
				log.error("创建云主机错误：", e);
				ThreadUtil.setThreadCash(threadid, e.getMessage());
				ThreadUtil.setErrorCount(1, threadid);
			}
		}
	}
}