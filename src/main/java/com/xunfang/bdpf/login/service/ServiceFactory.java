/**  
* @Title: ServiceFactory.java
* @Package com.xunfang.cloud
* @Description: keyStone identity service factory
* Copyright: Copyright (c) 2017 
* Company:深圳市讯方技术股份有限公司
* @author huangjf
* @date 2017年6月12日 下午3:12:04
* @version V1.0  
*/
package com.xunfang.bdpf.login.service;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.api.compute.ComputeService;
import org.openstack4j.api.identity.v3.IdentityService;
import org.openstack4j.api.image.v2.ImageService;
import org.openstack4j.api.networking.NetworkingService;
import org.openstack4j.api.storage.BlockStorageService;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.openstack.OSFactory;
import org.openstack4j.openstack.internal.OSClientSession.OSClientSessionV3;

import com.xunfang.utils.DataEncryptUtil;
import com.xunfang.utils.PropertiesUtil;


/**
* @ClassName: ServiceFactory
* @Description: KeyStone service factory 身份验证、获取服务实例类，例如云主机服务、镜像服务、网络等等一系列服务实例
* @author huangjf
* @date 2017年6月12日 下午3:12:04
*
*/
public class ServiceFactory {

	private final static Log log = LogFactory.getLog(ServiceFactory.class);
	
	/**
	* @Fields collection : Token实例集，{key：UUID加密后的token字符(并提供上册业务作为身份token)，value:Token实例}
	*/
	public static Map<String, OSClientV3> collection = Collections.synchronizedMap(new HashMap<String, OSClientV3>());

	/**
	* @Title: verification
	* @Description: verification KeyStone Service
	* @param userName
	* @param password
	* @return
	* String
	*/
	public static Map<String, Object> verification(String userName, String password){
		Map<String, Object> result = new HashMap<>();
		try {
			OSClientV3 os = OSFactory.builderV3()
	                .endpoint(PropertiesUtil.ENDPOINT_SERVICE_IDENTITY)
	                .credentials(userName, password,Identifier.byId(PropertiesUtil.IDENTITY_AUTH_DOMAIN))
	                .scopeToProject(Identifier.byName(userName), Identifier.byName("Default"))
	                .authenticate();
			String token = DataEncryptUtil.encryptSha256(UUID.randomUUID().toString());
			collection.put(token, os);
			result.put("result", true);
			result.put("token", token);
			return result;
		} catch (Exception e) {
			log.error("initialize identity error", e);
			result.put("result", false);
			return result;
		}
	}

	
	/**
	* @Title: currSesion
	* @Description: Get the OSClientSessionV3
	* @param token
	* @return
	* OSClientSessionV3
	*/
	public static OSClientSessionV3 currSesion(String token){
		return OSClientSessionV3.createSession(collection.get(token).getToken());
	}
	
	/**
	* @Title: Token
	* @Description: Get the Token
	* @param token
	* @return
	* Token
	*/
	public static Token token(String token){
		return currSesion(token).getToken();
	}
	
	
	/**
	* @Title: imageService
	* @Description: Get the ImageService
	* @param token
	* @return
	* ImageService
	*/
	public static ImageService imageService(String token){
		return currSesion(token).imagesV2();
	}
	
	
	/**
	* @Title: computeService
	* @Description: Get the ComputeService
	* @param token
	* @return
	* ComputeService
	*/
	public static ComputeService computeService(String token){
		return currSesion(token).compute();
	}
	
	
	/**
	* @Title: blockStorageService
	* @Description: Get the BlockStorageService
	* @param token
	* @return
	* BlockStorageService
	*/
	public static BlockStorageService blockStorageService(String token){
		return currSesion(token).blockStorage();
	}
	
	
	/**
	* @Title: networkingService
	* @Description: Get the NetworkingService
	* @param token
	* @return
	* NetworkingService
	*/
	public static NetworkingService networkingService(String token){
		return currSesion(token).networking();
	}
	
	
	/**
	* @Title: identityService
	* @Description: Get the IdentityService
	* @param token
	* @return
	* IdentityService
	*/
	public static IdentityService identityService(String token){
		return currSesion(token).identity();
	}
}
