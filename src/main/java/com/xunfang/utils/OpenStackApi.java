package com.xunfang.utils;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openstack4j.api.Builders;
import org.openstack4j.api.compute.ComputeSecurityGroupService;
import org.openstack4j.api.compute.FlavorService;
import org.openstack4j.api.compute.KeypairService;
import org.openstack4j.api.compute.ServerService;
import org.openstack4j.api.compute.ext.ZoneService;
import org.openstack4j.api.image.v2.ImageService;
import org.openstack4j.api.networking.NetworkingService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.common.Payloads;
import org.openstack4j.model.compute.Action;
import org.openstack4j.model.compute.CustomVNCConsole;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.Keypair;
import org.openstack4j.model.compute.RebootType;
import org.openstack4j.model.compute.SecGroupExtension;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;
import org.openstack4j.model.compute.VNCConsole;
import org.openstack4j.model.compute.VNCConsole.Type;
import org.openstack4j.model.compute.ext.AvailabilityZone;
import org.openstack4j.model.image.v2.ContainerFormat;
import org.openstack4j.model.image.v2.DiskFormat;
import org.openstack4j.model.image.v2.Image;
import org.openstack4j.model.network.Network;
import org.springframework.web.multipart.MultipartFile;

import com.xunfang.bdpf.login.entity.Servers;
import com.xunfang.bdpf.login.service.ServiceFactory;
import com.xunfang.bdpf.vmstemplates.introduction.entity.Flavors;
import com.xunfang.bdpf.vmstemplates.introduction.entity.Images;
import com.xunfang.bdpf.vmstemplates.introduction.entity.KeyPair;
import com.xunfang.bdpf.vmstemplates.introduction.entity.Networks;
import com.xunfang.bdpf.vmstemplates.introduction.entity.Secgroup;
import com.xunfang.bdpf.vmstemplates.introduction.entity.Zone;
import com.xunfang.bdpf.vmstemplates.virtual.entity.ImageEntity;
import com.xunfang.bdpf.vmstemplates.virtual.entity.ServerEntity;
import com.xunfang.bdpf.vmstemplates.virtual.service.ComputeService;

/**
 * @ClassName OpenStackApi
 * @Description:  调用OpenStack API接口
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年7月6日 上午9:24:30
 * @version V1.0
 */
public class OpenStackApi {

	/**
	 * 
	  * @Title: cloudServerList
	  * @Description: 通过云主机名称和授权码获取云主机信息
	  *  @param keyvalue String 云主机名称
	  *  @param token String 授权码token
	  *  @return  Map<String, List<Server>> 返回类型
	  * @throws
	 */
	public static  Map<String, List<Servers>> cloudServerList(String keyvalue,String token,Map<String, String> studentMap){
		Map<String, String> filteringParams = new HashMap<String, String>();
		filteringParams.put("name", keyvalue);
		ServerService serverService = ServiceFactory.computeService(token).servers();
		if(serverService == null){
			return null;
		}
		List<? extends Server> servers = serverService.list(filteringParams);
		if(servers == null){
			return null;
		}
		Map<String, List<Servers>> serverMap = Rest.getServerList(servers,token,studentMap);
		if(serverMap == null){
			return null;
		}
		//返回serverMap对象
		return serverMap;
	}
	
	/**
	 * 
	  * @Title: cloudServer
	  * @Description: 通过云主机主键和授权码获取云主机信息
	  *  @param keyvalue String 云主机名称
	  *  @param token String 授权码token
	  *  @return  Server 返回类型
	  * @throws
	 */
	public static Servers cloudServer(String id,String token){
		ServerService serverService = ServiceFactory.computeService(token).servers();
		if(serverService == null){
			return null;
		}
		Server  server = serverService.get(id);
		if(server == null){
			return null;
		}
		Servers  servers = Rest.getServerById(server);
		if(servers == null){
			return null;
		}
		//返回server对象
		return servers;
	}
	
	/**
	 * 
	  * @Title: cloudServerCreate
	  * @Description: 创建云主机
	  *  @param createServer CreateServer 条件(可进入该类查看字段说明)
	  *  @param token String 授权码token
	  *  @return  Server 返回类型
	  * @throws
	 */
	public static Servers cloudServerCreate(ServerCreate  serverCreate,String token){
		ServerService serverService = ServiceFactory.computeService(token).servers();
		Server server;
		if(serverService == null){
			return null;
		}
		try {
			server = serverService.boot(serverCreate);
			if(server == null){
				return null;
			}
			Servers  servers = Rest.getServerById(server);
			if(servers == null){
				return null;
			}
			//返回servers对象
			return servers;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 
	  * @Title: batchCloudServerCreate
	  * @Description: 批量创建云主机
	  *  @param threadServer ThreadServer 条件(可进入该类查看字段说明)
	  *  @param token String 授权码token
	  *  @return  String 返回类型
	  * @throws
	 */
	public static String batchCloudServerCreate(ServerEntity serverEntity,String token){
		String threadid = new ComputeService(serverEntity, token).excute();
		//调用的接口返回字段success，并返回
		return threadid;
	}
	
	/**
	 * 
	  * @Title: serverDel
	  * @Description:虚拟机单个删除
	  *  @param keyvalue  String 虚拟机名称
	  *  @param token  String 授权码token
	  *  @return  DelMessage 返回类型
	  * @throws
	 */
	public  static ActionResponse  serverDel(String id,String token){
		ServerService serverService = ServiceFactory.computeService(token).servers();
		if(serverService == null){
			return null;
		}
		ActionResponse actionResponse = serverService.delete(id);
		return  actionResponse;
	}
	
	/**
	 * 
	  * @Title: batchServerDel
	  * @Description:虚拟机批量删除
	  *  @param ids String 云主机主键ID
	  *  @param token String  授权码token
	  *  @return  DelMessage 返回类型
	  * @throws
	 */
	public  static ActionResponse  batchServerDel(String ids,String token){
		ActionResponse actionResponse = null;
		ServerService serverService = ServiceFactory.computeService(token).servers();
		if(serverService == null){
			return null;
		}
		if(StringUtils.isNotBlank(ids)){
			String[] idArr = ids.split(",");
			for(String id : idArr)
				actionResponse = serverService.delete(id);
		}
		//返回DelMessage对象
		return  actionResponse;
	}
	
	/**
	 * 
	  * @Title: cloudServerAction
	  * @Description: 云主机操作，例如关闭、启动
	  *  @param type String   STOP(关闭),START(启动)
	  *  @param id String 云主机唯一ID
	  *  @param token String 授权码token
	  *  @return  DelMessage 返回类型
	  * @throws
	 */
	public static ActionResponse cloudServerAction(String action,String serverId,String token){
		ActionResponse  actionResponse;
		ServerService serverService = ServiceFactory.computeService(token).servers();
		if(serverService == null){
			return null;
		}
		actionResponse = serverService.action(serverId, Action.valueOf(action));
		//返回DelMessage对象
		return  actionResponse;
	}
	
	/**
	 * 
	  * @Title: batchCloudServerAction
	  * @Description: 云主机批量操作，例如关闭、启动
	  *  @param type String   STOP(关闭),START(启动)
	  *  @param id String 云主机唯一ID
	  *  @param token String 授权码token
	  *  @return  DelMessage 返回类型
	  * @throws
	 */
	public static ActionResponse batchCloudServerAction(String action,String ids,String token){
		ActionResponse actionResponse = null;
		ServerService serverService = ServiceFactory.computeService(token).servers();
		if(serverService == null){
			return null;
		}
		if(StringUtils.isNotBlank(ids)){
			String[] idArr = ids.split(",");
			for(String id : idArr)
				actionResponse = serverService.action(id, Action.valueOf(action));
		}
		return actionResponse;
	}
	
	/**
	 * 
	  * @Title: cloudServerReboot
	  * @Description: 云主机重启，包含软、硬重启
	  *  @param type Sting SOFT(软重启),HARD(硬重启)
	  *  @param id  String 云主机唯一ID
	  *  @param token String 授权码token
	  *  @return  DelMessage 返回类型
	  * @throws
	 */
	public static ActionResponse cloudServerReboot(String rebootType,String id,String token){
		ActionResponse  actionResponse;
		ServerService serverService = ServiceFactory.computeService(token).servers();
		if(serverService == null){
			return null;
		}
		actionResponse = serverService.reboot(id, RebootType.valueOf(rebootType));
		//返回DelMessage对象
		return  actionResponse;
	}
	
	/**
	 * 
	  * @Title: batchCloudServerReboot
	  * @Description: 云主机批量重启，包含软、硬重启
	  *  @param type String SOFT(软重启),HARD(硬重启)
	  *  @param id  String 云主机唯一ID
	  *  @param token String 授权码token
	  *  @return  DelMessage 返回类型
	  * @throws
	 */
	public static ActionResponse batchCloudServerReboot(String rebootType,String ids,String token){
		ActionResponse actionResponse = null;
		ServerService serverService = ServiceFactory.computeService(token).servers();
		if(serverService == null){
			return null;
		}
		if(StringUtils.isNotBlank(ids)){
			String[] idArr = ids.split(",");
			for(String id : idArr)
				actionResponse = serverService.reboot(id, RebootType.valueOf(rebootType));
		}
		return actionResponse;
	}
	
	/**
	 * 
	  * @Title: CloudServerSnapshot
	  * @Description: 创建快照
	  * @param  id  String 云主机ID
	  * @param  name  String   快照名称
	  * @param  token  String   授权码token
	  * @param  设定文件
	  * @return  String   返回类型
	  * @throws
	 */
	public static String CloudServerSnapshot(String id,String names,String token){
		String actionResponse;
		ServerService serverService = ServiceFactory.computeService(token).servers();
		if(serverService == null){
			return null;
		}
		actionResponse = serverService.createSnapshot(id, names);
		//返回DelMessage对象
		return  actionResponse;
	}
	
	/**
	 * 
	  * @Title: CloudServerVnc
	  * @Description: 获取VNC地址
	  * @param  id  String 云主机ID
	  * @param  token  String   授权码token
	  * @param  设定文件
	  * @return  String   返回类型
	  * @throws
	 */
	public static VNCConsole CloudServerVnc(String id,String token){
		VNCConsole actionResponse;
		ServerService serverService = ServiceFactory.computeService(token).servers();
		if(serverService == null){
			return null;
		}
		actionResponse = serverService.getVNCConsole(id, Type.NOVNC);
		
		//返回DelMessage对象
		return  actionResponse;
	}
	
	/**
	 * 
	  * @Title: CloudServerVnc
	  * @Description: 获取VNC地址
	  * @param  id  String 云主机ID
	  * @param  token  String   授权码token
	  * @param  设定文件
	  * @return  String   返回类型
	  * @throws
	 */
	public static CustomVNCConsole getOSConsoleAuthTokens(String token,String tokenVnc){
		CustomVNCConsole customVNCConsole;
		ServerService serverService = ServiceFactory.computeService(token).servers();
		if(serverService == null){
			return null;
		}
		customVNCConsole = serverService.getOSConsoleAuthTokens(tokenVnc);
		
		//返回DelMessage对象
		return  customVNCConsole;
	}
	
	/**
	 * 
	  * @Title: cloudFlavors
	  * @Description: 获取云主机类型数据集
	  *  @param keyvalue  String  云主机类型名称
	  *  @param token String 授权码token
	  *  @return  List<Flavors> 返回类型
	  * @throws
	 */
	public static List<Flavors> cloudFlavors(String  keyvalue,String token){
		FlavorService flavorService = ServiceFactory.computeService(token).flavors();
		if(flavorService == null){
			return null;
		}
		Map<String, String> filteringParams = new HashMap<String, String>();
		filteringParams.put("name", keyvalue);
		List<? extends Flavor> flavors = flavorService.list(filteringParams);
		if(flavors == null){
			return null;
		}
		return Rest.getFlavorsList(flavors);
	}
	
	/**
	 * 
	  * @Title: cloudFlavorsById
	  * @Description: 根据唯一编码获取云主机类型数据
	  *  @param id  String  云主机类型ID
	  *  @param token String 授权码token
	  *  @return  Flavors 返回类型
	  * @throws
	 */
	public static Flavor cloudFlavorsById(String  id,String token){
		FlavorService flavorService = ServiceFactory.computeService(token).flavors();
		if(flavorService == null){
			return null;
		}
		//返回Flavors对象
		return flavorService.get(id);
	}
	
	/**
	 * 
	  * @Title: cloudKeypair
	  * @Description: 获取秘钥对数据集
	  *  @param token String 授权码token
	  *  @return  List<KeyPair> 返回类型
	  * @throws
	 */
	public static List<KeyPair> cloudKeypair(String token){
		KeypairService keypairService = ServiceFactory.computeService(token).keypairs();
		if(keypairService == null){
			return null;
		}
		List<? extends Keypair> keypairs = keypairService.list();
		if(keypairs == null){
			return null;
		}
		return Rest.getKeyPairList(keypairs);
	}
	
	/**
	 * 
	  * @Title: cloudSecgroup
	  * @Description: 获取安全组数据集
	  *  @param token String 授权码token
	  *  @return  List<Secgroup> 返回类型
	  * @throws
	 */
	public static List<Secgroup> cloudSecgroup(String token){
		ComputeSecurityGroupService computeSecurityGroupService = ServiceFactory.computeService(token).securityGroups();
		if(computeSecurityGroupService == null){
			return null;
		}
		List<? extends SecGroupExtension> secGroupExtensions = computeSecurityGroupService.list();
		if(secGroupExtensions == null){
			return null;
		}
		return Rest.getSecgroupList(secGroupExtensions);
	}
	
	/**
	 * 
	  * @Title: cloudZone
	  * @Description: 获取可用域数据集
	  *  @param token String 授权码token
	  *  @return  List<Zone> 返回类型
	  * @throws
	 */
	public static List<Zone> cloudZone(String token){
		ZoneService zoneService = ServiceFactory.computeService(token).zones();
		if(zoneService == null){
			return null;
		}
		List<? extends AvailabilityZone> availabilityZones = zoneService.list();
		if(availabilityZones == null){
			return null;
		}
		return Rest.getZoneList(availabilityZones);
	}
	
	/**
	 * 
	  * @Title: cloudNetwork
	  * @Description: 获取网络列表
	  *  @param token String 授权码token
	  *  @return  List<Network> 返回类型
	  * @throws
	 */
	public static List<Networks> cloudNetwork(String token){
		NetworkingService networkingService = ServiceFactory.networkingService(token);
		if(networkingService == null){
			return null;
		}
		List<? extends Network> networks = networkingService.network().list();
		if(networks == null){
			return null;
		}
		return Rest.getNetworkList(networks);
	}
	
	/**
	 * 
	  * @Title: cloudImage
	  * @Description: 获取镜像列表
	  *  @param keyvalue String 镜像名称
	  *  @param token String 授权码token
	  *  @return  List<Image> 返回类型
	  * @throws
	 */
	public static List<Images> cloudImage(String  name,String token){
		Map<String, String> filteringParams = new HashMap<String, String>();
		ImageService imageService = ServiceFactory.imageService(token);
		List<? extends Image> images;
		if(!"".equals(name)&&null != name){
			filteringParams.put("name", name);
			images = imageService.list(filteringParams);
		}else{
			images =imageService.list();
		}
		if(images == null){
			return null;
		}
		return Rest.getImageList(images);
	}
	
	/**
	 * 
	  * @Title: cloudImageSave
	  * @Description:  保存镜像
	  * @param imageUrl String 文件存放路径
	  * @param name String 镜像名称
	  * @param token String 授权码token
	  * @return  Image 返回类型
	  * @throws
	 */
	public static Images cloudImageSave(ImageEntity imageEntity,String token){
		Image image = null;
		 try {
			 if(null != imageEntity){
					String url = imageEntity.getImageUrl();
					Payload<URL> payload = Payloads.create(new URL(url));
					ImageService os = ServiceFactory.imageService(token);
					image = os.create(Builders.imageV2().name(imageEntity.getName())
							.containerFormat(ContainerFormat.BARE).diskFormat(DiskFormat.QCOW2).build());
					if(image == null){
						return null;
					}
					/*
					ActionResponse upload = ServiceFactory.imageService(token).upload(
						image.getId(),
						payload,
						image);
					*/
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回Image对象
		return Rest.getImageById(image);
	}
	
	/**
	 * 镜像文件上传
	 * @param file 文件对象
	 * @return String 镜像文件的下载地址
	 */
	public String uploadFile(MultipartFile file){
		 String fileName = file.getOriginalFilename();
		 String uploadFilePath=PropertiesUtil.getValue("uploadFilePath");
		 File targetFile = new File(uploadFilePath, fileName);
		 targetFile.mkdirs();
		 try {
			file.transferTo(targetFile);
		 }catch(Exception e){
			 e.printStackTrace();
			 return null;
		 }
		return PropertiesUtil.getValue("uploadFileUrl") + "/" + fileName;
	}
	
	/**
	 * 
	  * @Title: cloudImageUpdate
	  * @Description: 修改镜像信息
	  * @param name String 镜像名称
	  * @param token String 授权码token
	  * @return  Image 返回类型
	  * @throws
	 */
	public static Images cloudImageUpdate(ImageEntity imageEntity,String token){
		ImageService imageService = ServiceFactory.imageService(token);
		Image image = null; 
		if (null != imageEntity) {
			Image imageTemp = imageService.get(imageEntity.getId());
			if (null != imageTemp) {
				image =	imageService.update(imageTemp.toBuilder()
						.name(imageEntity.getName())
						.build());
			}
		}
		//返回Image对象
		return Rest.getImageById(image);
	}

	/**
	 * 
	  * @Title: cloudImageById
	  * @Description: 删除单个镜像
	  * @param id String 镜像ID
	  * @param token String 授权码token
	  * @return  Image 返回类型
	  * @throws
	 */
	public static Images cloudImageById(String id,String token){
		ImageService imageService = ServiceFactory.imageService(token);
		Image image = imageService.get(id);
		return Rest.getImageById(image);
	}
	/**
	 * 
	  * @Title: cloudImageDel
	  * @Description: 删除单个镜像
	  * @param id String 镜像ID
	  * @param token String 授权码token
	  * @return  Image 返回类型
	  * @throws
	 */
	public static ActionResponse cloudImageDel(String id,String token){
		ImageService imageService = ServiceFactory.imageService(token);
		ActionResponse actionResponse = imageService.delete(id);
		return actionResponse;
	}
	
	/**
	 * 
	  * @Title: batchCloudImageDel
	  * @Description: 批量删除镜像
	  * @param ids String 镜像ID
	  * @param token String 授权码token
	  * @return  String 返回类型
	  * @throws
	 */
	public static ActionResponse batchCloudImageDel(String ids,String token){
		ActionResponse actionResponse = null;
		if(StringUtils.isNotBlank(ids)){
			String[] idArr = ids.split(",");
			for(String id:idArr){
				 actionResponse = ServiceFactory.imageService(token).delete(id);
			}
		}
		return actionResponse;
	}
	
}
