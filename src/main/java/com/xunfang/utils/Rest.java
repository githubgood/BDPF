package com.xunfang.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openstack4j.model.compute.Address;
import org.openstack4j.model.compute.Addresses;
import org.openstack4j.model.compute.CustomVNCConsole;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.Keypair;
import org.openstack4j.model.compute.SecGroupExtension;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.VNCConsole;
import org.openstack4j.model.compute.ext.AvailabilityZone;
import org.openstack4j.model.image.v2.Image;
import org.openstack4j.model.network.Network;

import com.xunfang.bdpf.experiment.exp.entity.ExpCloud;
import com.xunfang.bdpf.login.entity.Servers;
import com.xunfang.bdpf.vmstemplates.introduction.entity.Flavors;
import com.xunfang.bdpf.vmstemplates.introduction.entity.Images;
import com.xunfang.bdpf.vmstemplates.introduction.entity.KeyPair;
import com.xunfang.bdpf.vmstemplates.introduction.entity.Networks;
import com.xunfang.bdpf.vmstemplates.introduction.entity.Secgroup;
import com.xunfang.bdpf.vmstemplates.introduction.entity.Zone;

/**
 * 
 * @ClassName Rest
 * @Description: 远程接调用
 * Copyright: Copyright (c) 2017
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年7月5日 下午5:36:27
 * @version V1.0
 */
public class Rest {
	/**
	 * 
	  * @Title: getServerList
	  * @Description: 虚拟机的封装
	  *  @param array JSONArray
	  *  @return  Map<String,List<Server>> 返回类型
	  * @throws
	 */
	public static Map<String, List<Servers>> getServerList(List<? extends Server> servers,String token,Map<String, String> studentMap) {
		//返回值
		Map<String, List<Servers>> map = new HashMap<String, List<Servers>>();
		//存放虚拟机列表信息
		List<Servers> server = new ArrayList<Servers>();
		//存在虚拟机状态信息
		List<Servers> status = new ArrayList<Servers>();
		//定义虚拟机model
		Servers ser = null;
		//如果获取的虚拟机列表为空，则直接返回null
		if(servers==null||servers.size()==0){
			return map;
		}
		//如果学生虚拟机Map为空，则直接返回null
		if(studentMap == null){
			return map;
		}
		int runNum = 0;//虚拟机运行状态数量
		int stopNum = 0;//虚拟机停止状态数量
		int other = 0;//虚拟机其它状态数量
		
		//遍历循环虚拟机列表信息
		for (int i = 0; i < servers.size(); i++) {
			ser = new Servers();
			//遍历学生、教师虚拟机信息
			ser.setId(servers.get(i).getId());
			ser.setName(servers.get(i).getName());
			if((studentMap.get(ser.getId())!=null&&studentMap.get(ser.getId()).equals(ser.getId()))){//该学生或教师拥有的虚拟机,过滤掉其他虚拟机
				if(!servers.get(i).getStatus().toString().equals("ERROR")){
					VNCConsole vncUrl = OpenStackApi.CloudServerVnc(ser.getId(),token);
					if(vncUrl!=null && !"".equals(vncUrl)){
						String tokenVNC = vncUrl.getURL().split("=")[1];
						CustomVNCConsole customVNCConsole = OpenStackApi.getOSConsoleAuthTokens(token,tokenVNC);
						if(!customVNCConsole.getPort().equals("-1")){
							ser.setVncInfo(customVNCConsole.getHost()+":"+customVNCConsole.getPort());
							ser.setVnc(vncUrl.getURL());
						}else{
							ser.setVncInfo("");
						}
					}
				}else{
					ser.setVncInfo("");
				}
				Map<String, List<? extends Address>> addressMap = servers.get(i).getAddresses().getAddresses();
				if(addressMap==null || addressMap.size()==0){
					ser.setIp("");
				}else{
					if(addressMap.get("provider")!=null && addressMap.get("provider").size()>0){
						List<? extends Address> addresses = addressMap.get("provider");
						if(addresses.get(0).getAddr()!=null){
							ser.setIp(addresses.get(0).getAddr());
						}
					}else if(addressMap.get("selfservice")!=null && addressMap.get("selfservice").size()>0){
						List<? extends Address> addresses = addressMap.get("selfservice");
						if(addresses.get(0).getAddr()!=null){
							ser.setIp(addresses.get(0).getAddr());
						}
					}
				}
				if(servers.get(i).getImage() != null){
					if(PropertiesUtil.getValue("IMAGE_NAME_W").equals(servers.get(i).getImage().getName())){//安装集群环境
						ser.setImageName("安装集群环境");
						ser.setType("true");
					}else if(PropertiesUtil.getValue("IMAGE_NAME_K").equals(servers.get(i).getImage().getName())){//单点开发环境
						ser.setImageName("单点开发环境");
						ser.setType("true");
					}else if(PropertiesUtil.getValue("IMAGE_NAME_M").equals(servers.get(i).getImage().getName())){//集群开发控制点
						ser.setImageName("集群开发控制点");
					}else if(PropertiesUtil.getValue("IMAGE_NAME_S").equals(servers.get(i).getImage().getName())){//集群开发计算点
						ser.setImageName("集群开发计算点");
					}else{
						ser.setImageName(servers.get(i).getName());
					}
				}else{
					ser.setImageName(servers.get(i).getName());
				}
				if(!"".equals(studentMap.get("states"))){
					if("运行中".equals(studentMap.get("states"))){//运行中
						if(servers.get(i).getStatus().toString().equals("ACTIVE")){
							ser.setRunstatus("运行中");
							runNum++;
						}else{
							continue;
						}
					}else if("已停止".equals(studentMap.get("states"))){//停止中
						if(servers.get(i).getStatus().toString().equals("SHUTOFF")){
							ser.setRunstatus("已停止");
							stopNum++;
						}else{
							continue;
						}
					}else{
						continue;
					}
				}else{
					if (servers.get(i).getStatus().toString().equals("ACTIVE")) {
						ser.setRunstatus("运行中");
						runNum++;
					} else if (servers.get(i).getStatus().toString().equals("SHUTOFF")) {
						ser.setRunstatus("已停止");
						stopNum++;
					} else if (servers.get(i).getStatus().toString().equals("BUILD")) {
						ser.setRunstatus("创建中");
					} else if (servers.get(i).getStatus().toString().equals("ERROR")) {
						ser.setRunstatus("错误");
					}  else {
						ser.setRunstatus("其他");
						other++;
					}
				}
			}else{
				continue;
			}
			server.add(ser);//将Servers信息加到List<Servers>中
		}
		ser.setRunNum(runNum);//虚拟机运行状态数量
		ser.setStopNum(stopNum);//虚拟机停止状态数量
		ser.setOther(other);//虚拟机其它状态数量
		status.add(ser);//将Servers信息加到List<Servers>中
		map.put("server", server);//虚拟机列表信息
		map.put("status", status);//虚拟机状态信息
		return map;//返回
	}
	
	/**
	 * 
	  * @Title: getServerList
	  * @Description: 虚拟机的封装
	  *  @param object JSONObject
	  *  @return  Server 返回类型
	  * @throws
	 */
	public static Servers getServerById(Server server) {
		Servers servers = new Servers();
		if(server == null){
			return servers;
		}
		servers.setId(server.getId());
		servers.setName(server.getName());
		
		Addresses  address = server.getAddresses();
		if(address == null){
			return servers;
		}
		
		Map<String, List<? extends Address>> addressMap = address.getAddresses();
		if(addressMap==null || addressMap.size()==0){
			return servers;
		}
		if(addressMap.get("provider").size()>0){
			List<? extends Address> addresses = addressMap.get("provider");
			if(addresses.get(0).getAddr()!=null){
				servers.setIp(addresses.get(0).getAddr());
			}
		}else{
			List<? extends Address> addresses = addressMap.get("selfservice");
			if(addresses.get(0).getAddr()!=null){
				servers.setIp(addresses.get(0).getAddr());
			}
		}
		//System.out.println(server.getStatus().toString());
		if (server.getStatus().toString().equals("ACTIVE")) {
			servers.setRunstatus("运行中");
		} else if (server.getStatus().toString().equals("SHUTOFF")) {
			servers.setRunstatus("已停止");
		} else if (server.getStatus().toString().equals("BUILD")) {
			servers.setRunstatus("创建中");
		} else {
			servers.setRunstatus("其他");
			}
		return servers;
   }
	
	/**
	  * @Title: transIntoChinese
	  * @Description: 解决不同机器编码的问题
	  * @param array JSONArray
	  * @return Map<String, String> 返回类型
	  * @throws
	 */
	public static String transIntoChinese(String origin){
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        if (p.matcher(origin).find()) {
            return origin;
        }
		String code[] = {"iso8859-1", "UTF-8", "GBK"};
		String newStr = origin;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j ++){
				if(i == j)
					continue;
				try {
					newStr = new String( origin.getBytes(code[i]) , code[j]);
					if(p.matcher(newStr).find()){
						return newStr;
					}
					if(newStr.equals(origin)){
						return origin;
					}
				} catch (UnsupportedEncodingException e) {
				}
			}
		}
		return origin;
	}
	
	/**
	  * @throws IOException 
	  * @Title: addimageDesc
	  * @Description: 镜像模板id与描述映射添加
	  * @param imageId 镜像id
	  * @param description 镜像描述
	  * @return Map<String, String> 返回类型
	  * @throws
	 */
	public static void addImageDesc(String imageId, String description) throws IOException{
		String path = PropertiesUtil.getValue("DIR_FILE")+"/"+"template"+"/";
		FileWriter fileWritter = new FileWriter(path + "imageDesc", true);
		fileWritter.write('\n' + imageId + ' ' + transIntoChinese(description));
		fileWritter.close();
	}
	
	/**
	 * 
	  * @Title: getimageDesc
	  * @Description: 镜像模板id与描述映射查询
	  *  @return Map<String, String> 返回类型
	  * @throws
	 */
	public static Map<String, String> getImageDesc(){
		String path = PropertiesUtil.getValue("DIR_FILE")+"/"+"template"+"/";
		Map<String,String> imageMap = new HashMap<String, String>();
		File toDesc = new File(path, "imageDesc");
		if(toDesc.exists()&&toDesc.isFile()){
			String temp = null;
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(toDesc.getAbsolutePath())));
				while((temp = br.readLine()) != null){
					if("".equals(temp)){
						continue;
					}
					String[] strList = temp.split(" ", 2);
					imageMap.put(strList[0], transIntoChinese(strList[1]));
				}
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return imageMap;
	}
	
	/**
	 * 
	  * @Title: getimageList
	  * @Description: 镜像模板封装
	  *  @param array JSONArray
	  *  @return  List<Image> 返回类型
	  * @throws
	 */
	public static List<Images> getImageList(List<? extends Image> image) {
		List<Images> imagelist = new ArrayList<Images>();
		Images images = null;
		Map<String,String> imageMap = getImageDesc();
		for (int i = 0; i < image.size(); i++) {
			images = new Images();// 循环json数组
			images.setId(image.get(i).getId());
			images.setName(image.get(i).getName());
			String desc = imageMap.get(images.getId());
			if(desc == null){
				desc = "";
			}
			images.setDescription(desc);
			images.setSize(image.get(i).getSize()+"");
			if (image.get(i).getStatus().toString().equals("ACTIVE")) {
				images.setStatus("运行中");
			} else if (image.get(i).getStatus().toString().equals("SHUTOFF")) {
				images.setStatus("已停止");
			} else {
				images.setStatus("其他");
			}
			imagelist.add(images);
		}
		return imagelist;
	}
	
	/**
	 * 
	  * @Title: expCloudList
	  * @Description: 进入实验，获取vnc url地址
	  * @param @param serverMap  虚拟机信息
	  * @param @param token      授权码token
	  * @param @return    设定文件
	  * @return List<ExpCloud>    返回类型
	  * @throws
	 */
	public static List<ExpCloud> expCloudList( Map<String, List<Servers>> serverMap,String vmId,String token){
		List<ExpCloud> list = new ArrayList<ExpCloud>() ;
		List<Servers>  servers = serverMap.get("server");
		int index = 1;//循环的次数
		boolean contains = vmId.contains(",");//判断字符串中是否包含‘,’号
		if(contains){//如果包含
			for(Servers server:servers){//循环云主机数据
					if(vmId.contains(server.getId())){//如果vmd包含云主机的id，则保存数据。
						ExpCloud expCloud = new ExpCloud();
						VNCConsole outs = OpenStackApi.CloudServerVnc(server.getId(),token);
						expCloud.setIp(server.getIp());
						expCloud.setId(server.getId());
						expCloud.setUrl(outs.getURL().toString());
						expCloud.setNode("Node"+index);
						list.add(expCloud);
						index++;
					}
					
			}
		}else{//如果只有个id，就在循环里面做判断
			for(Servers server:servers){//循环云主机数据
				if(vmId.equals(server.getId())){//如果vmid与云主机的id相同，则保存数据。
					ExpCloud expCloud = new ExpCloud();
					VNCConsole outs = OpenStackApi.CloudServerVnc(server.getId(),token);
					expCloud.setIp(server.getIp());
					expCloud.setId(server.getId());
					expCloud.setUrl(outs.getURL().toString());
					expCloud.setNode("Node"+index);
					list.add(expCloud);
					index++;
				}
			}
		}
		return list;
	}
	
	/**
	 * 
	  * @Title: getImageById
	  * @Description:  根据镜像ID查找镜像信息
	  *  @param ob JSONObject
	  *  @return  Image 返回类型
	  * @throws
	 */
	public static Images getImageById(Image image) {
		Images images = new Images();
		images.setId(image.getId());
		images.setName(image.getName());
		images.setSize(image.getSize()+"");
		if (image.getStatus().toString().equals("ACTIVE")) {
			images.setStatus("运行中");
		} else if (image.getStatus().toString().equals("SHUTOFF")) {
			images.setStatus("已停止");
		} else {
			images.setStatus("其他");
		}
		return images;
	}
	
	/**
	 * 
	  * @Title: getimageList
	  * @Description: 云主机类型封装
	  *  @param array JSONArray
	  *  @return  List<Flavors> 返回类型
	  * @throws
	 */
	public static List<Flavors> getFlavorsList(List<? extends Flavor> flavor) {
		List<Flavors> flavorslist = new ArrayList<Flavors>();
		Flavors flavors = null;
		for (int i = 0; i < flavor.size(); i++) {
			flavors = new Flavors();// 循环json数组
			flavors.setId(flavor.get(i).getId());
			flavors.setName(flavor.get(i).getName());
			flavors.setVcpus(flavor.get(i).getVcpus()+"");
			flavors.setRam(flavor.get(i).getRam()+"");
			flavors.setDisk(flavor.get(i).getDisk()+"");
			flavors.setSwap(flavor.get(i).getSwap()+"");
			flavors.setPublics(flavor.get(i).isPublic()?"是":"否" );
			flavorslist.add(flavors);
		}
		return flavorslist;
	}
	
	/**
	 * 
	  * @Title: getFlavorsById
	  * @Description: 根据唯一编码获取云主机类型数据
	  *  @param array JSONArray
	  *  @return  Flavors 返回类型
	  * @throws
	 */
	public static Flavors getFlavorsById(Flavor flavor) {
		Flavors flavors = new Flavors();
		flavors.setId(flavor.getId());
		flavors.setName(flavor.getName()+"");
		flavors.setVcpus(flavor.getVcpus()+"");
		flavors.setRam(flavor.getRam()+"");
		flavors.setDisk(flavor.getDisk()+"");
		flavors.setSwap(flavor.getSwap()+"");
		flavors.setPublics(flavor.isPublic()?"是":"否");
		return flavors;
	}
	
	/**
	 * 
	  * @Title: getKeyPairList
	  * @Description: 密钥对封装
	  *  @param array JSONArray
	  *  @return  List<KeyPair> 返回类型
	  * @throws
	 */
	public static List<KeyPair> getKeyPairList(List<? extends Keypair> keypairs) {
		List<KeyPair> keyPairlist = new ArrayList<KeyPair>();
		KeyPair keyPair = null;
		for (int i = 0; i < keypairs.size(); i++) {
			keyPair = new KeyPair();// 循环json数组
			keyPair.setId(keypairs.get(i).getId()+"");
			keyPair.setName(keypairs.get(i).getName());
			keyPair.setPublicKey(keypairs.get(i).getPrivateKey());
			keyPairlist.add(keyPair);
		}
		return keyPairlist;
	}
	
	/**
	 * 
	  * @Title: getSecgroupList
	  * @Description: 安全组封装
	  *  @param array JSONArray
	  *  @return  List<Flavors> 返回类型
	  * @throws
	 */
	public static List<Secgroup> getSecgroupList(List<? extends SecGroupExtension> secGroupExtensions) {
		List<Secgroup> secgrouplist = new ArrayList<Secgroup>();
		Secgroup secgroup = null;
		for (int i = 0; i < secGroupExtensions.size(); i++) {
			secgroup = new Secgroup();// 循环json数组
			secgroup.setId(secGroupExtensions.get(i).getId());
			secgroup.setName(secGroupExtensions.get(i).getName());
			secgrouplist.add(secgroup);
		}
		return secgrouplist;
	}
	
	/**
	 * 
	  * @Title: getZoneList
	  * @Description: 可用域封装
	  *  @param array JSONArray
	  *  @return  List<Zone> 返回类型
	  * @throws
	 */
	public static List<Zone> getZoneList(List<? extends AvailabilityZone> availabilityZones) {
		List<Zone> zonelist = new ArrayList<Zone>();
		Zone zone = null;
		for (int i = 0; i < availabilityZones.size(); i++) {
			zone = new Zone();// 循环json数组
			zone.setZoneName(availabilityZones.get(i).getZoneName());
			zonelist.add(zone);
		}
		return zonelist;
	}
	
	/**
	 * 
	  * @Title: getNetworkList
	  * @Description: 网络封装
	  *  @param array JSONArray
	  *  @return  List<Network> 返回类型
	  * @throws
	 */
	public static List<Networks> getNetworkList(List<? extends Network> networks) {
		List<Networks> networklist = new ArrayList<Networks>();
		Networks network = null;
		for (int i = 0; i < networks.size(); i++) {
			network = new Networks();// 循环json数组
			network.setId(networks.get(i).getId());
			network.setName(networks.get(i).getName());
			network.setShared(networks.get(i).isShared() ? "是" : "否");
			network.setAdminStateUp(networks.get(i).isAdminStateUp()? "正常" : "故障");
			network.setStatus(networks.get(i).getStatus()+"");
			networklist.add(network);
		}
		return networklist;
	}
	
	/**
	 * 
	  * @Title: getNetworkById
	  * @Description: 网络封装
	  *  @param ob JSONObject 
	  *  @return  Network  返回类型
	  * @throws
	 */
	public static Networks getNetworkById(Network network) {
		Networks networks = new Networks();
		networks.setId(network.getId());
		networks.setName(network.getName());
		networks.setShared(network.isShared() ? "是" : "否");
		networks.setAdminStateUp(network.isAdminStateUp()? "正常" : "故障");
		networks.setStatus(network.getStatus()+"");
		return networks;
	}
	
	/**
	 * 
	  * @Title: postJsonSMS
	  * @Description: post方式调用接口
	  *  @param postData String 请求的参数
	  *  @param postUrl String 请求的url
	  *  @param token String  授权码token
	  *  @return  String 返回类型
	  * @throws
	 */
	public static String postJsonSMS(String postData, String postUrl, String token) {
		try {
			// 发送POST请求
			URL url = new URL(postUrl);
			//
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Connection", "Keep-Alive");
			if (token != null && !"".equals(token)) {
				conn.setRequestProperty("Auth-Token", token);
			}
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(10000);
			conn.setRequestProperty("Content-Length", "" + postData.length());
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(postData);
			out.flush();
			out.close();
			// 获取响应状态
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("connect failed!");
				return "";
			}
			// 获取响应内容体
			String line, result = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			in.close();
			if(result.indexOf("DOCTYPE")>0){
				return "";
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return "";
	}
	
	/**
	 * 
	  * @Title: getJsonSMS
	  * @Description: get方式调用接口
	  *  @param postUrl String 请求的url
	  *  @param token String 授权码token
	  *  @return  String 返回类型
	  * @throws
	 */
	public static String getJsonSMS( String postUrl, String token) {
		try {
			// 发送get请求
			URL url = new URL(postUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(10000);
			if (token != null && !"".equals(token)) {
				conn.setRequestProperty("Auth-Token", token);
			}
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("connect failed!");
				return "";
			}
			// 获取响应内容体
			String line, result = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			in.close();
			return result;
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return "";
	}
}
