package com.xunfang.bdpf.material.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunfang.bdpf.experiment.course.entity.Course;
import com.xunfang.bdpf.experiment.course.entity.CourseExample;
import com.xunfang.bdpf.experiment.course.service.CourseService;
import com.xunfang.utils.Page;
import com.xunfang.utils.PropertiesUtil;

/**
 * 
 * @ClassName MaterialController
 * @Description: 资料库控制层
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年6月14日 下午3:23:16
 * @version V1.0
 */
@Controller
@RequestMapping(value = "material")
public class MaterialController {

	/**
	 * @Fields log : 输出日志
	 * 
	 */
	private static Logger log = Logger.getLogger(MaterialController.class);
	@Autowired
	//课程资源管理Service接口
	private CourseService courseService;
	
	 public final static boolean DEBUG = true; // 调试用  
	    private static int BUFFER_SIZE = 8096; // 缓冲区大小  
	    private Vector vDownLoad = new Vector(); // URL列表  
	    private Vector vFileList = new Vector(); // 下载后的保存文件名列表  
	
	
	/**
	 * 
	  * @Title: exp
	  * @Description: TODO      资料库管理跳转方法，进入首页
	  * @param @param request   课程资源model
	  * @param @param session   http缓存
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value = "")
	public String exp(HttpServletRequest request, HttpSession session) {
		return "material/index";
	}
	
	
	/**
	 * 
	  * @Title: toDetailDoc
	  * @Description: 文件预览
	  * @param @param id     资源id
	  * @param @param model
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value="to-detailDoc/{id}")
	public String toDetailDoc(@PathVariable String id, Model model){
//		根据id获取到预览的资料对象
		Course course = courseService.selectByPrimaryKey(id);
		model.addAttribute("course", course);
		return "material/pdf";
	}
	
	/**
	 * 
	  * @Title: download
	  * @Description: 文件下载
	  * @param @param fileName  文件名称
	  * @param @param request
	  * @param @param response
	  * @param @throws Exception    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping("/download/{fileName}")   
    public void download(@PathVariable("fileName")   
    String fileName, HttpServletRequest request, HttpServletResponse response)   
            throws Exception { 
		//设置编码格式
        response.setContentType("text/html;charset=utf-8");   
        request.setCharacterEncoding("UTF-8");   
        BufferedInputStream bis = null;   
        HttpURLConnection httpUrl = null;
        BufferedOutputStream bos = null;
        URL url = null; 
        fileName=fileName.replaceAll("\\|", "\\\\");//把|替换成\
        fileName=fileName.replaceAll("\\*", ".");//把*替换成.
        //获取文件名称
        String title = "";
		CourseExample example = new CourseExample();
		example.createCriteria().andPathEqualTo(fileName.replace('\\', '/'));
		List<Course> selectByExample = courseService.selectByExample(example);
		if(selectByExample.size()>0){
		title = selectByExample.get(0).getTitle();
		}
        String[] files = fileName.split("res");
        String downLoadPath = null ;
        //分隔字符串取，找到文件的本地路径
        String file = (PropertiesUtil.getValue("DIR_FILE")+files[1]);
        //如果 文件为doc或者xls或者ppt 或者 txt
        if(file.contains(".doc") || file.contains(".xls") || file.contains(".ppt")|| file.contains(".txt")){
        	//就把路径的。pdf替换为空字符
        	downLoadPath = file.replaceAll(".pdf", "");
        	fileName = fileName.replaceAll(".pdf", "");
        }else{
        	downLoadPath = file;
        }
        //文件名
        fileName=fileName.substring(fileName.lastIndexOf("\\")+1, fileName.length());
        File file1 = new File(downLoadPath);  
        String fileName1 = file1.getName();  
        String suffix = fileName1.substring(fileName1.lastIndexOf(".") + 1);  
        System.out.println(suffix);
        //saveToFile("http://127.0.0.1:8080/res/doc/"+fileName,"D:/material/"+fileName);
        
        url = new URL("http://xunfang.qicp.io:6083/res/doc/"+fileName);  
        httpUrl = (HttpURLConnection) url.openConnection();
        try {
            long fileLength = new File(downLoadPath).length();   
            response.setContentType("application/x-msdownload;");   
            response.setHeader("Content-disposition", "attachment; filename="  + new String((title+"."+suffix).getBytes("utf-8"), "ISO8859-1"));   
            response.setHeader("Content-Length", String.valueOf(fileLength));   
            bis = new BufferedInputStream(httpUrl.getInputStream());  
            bos = new BufferedOutputStream(response.getOutputStream());   
            byte[] buff = new byte[1024*1024];   
            int bytesRead;   
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {   
                bos.write(buff, 0, bytesRead);   
            }  
            bos.flush();
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            if (bis != null)   
                bis.close();   
            if (bos != null)   
                bos.close();   
        }   
    }   
	
	
//	@RequestMapping("/download/{fileName}")   
//    public void download(@PathVariable("fileName")   
//    String fileName, HttpServletRequest request, HttpServletResponse response)   
//            throws Exception { 
//		//璁剧疆缂栫爜鏍煎紡
//        response.setContentType("text/html;charset=utf-8");   
//        request.setCharacterEncoding("UTF-8");   
//        BufferedInputStream bis = null;   
//        BufferedOutputStream bos = null;
//        fileName=fileName.replaceAll("\\|", "\\\\");//鎶妡鏇挎崲鎴怽
//        fileName=fileName.replaceAll("\\*", ".");//鎶�*鏇挎崲鎴�.
//        //鑾峰彇鏂囦欢鍚嶇О
//        String title = "";
//		CourseExample example = new CourseExample();
//		example.createCriteria().andPathEqualTo(fileName.replace('\\', '/'));
//		List<Course> selectByExample = courseService.selectByExample(example);
//		if(selectByExample.size()>0){
//		title = selectByExample.get(0).getTitle();
//		}
//        String[] files = fileName.split("res");
//        String downLoadPath = null ;
//        //鍒嗛殧瀛楃涓插彇锛屾壘鍒版枃浠剁殑鏈湴璺緞
//        String file = (PropertiesUtil.getValue("DIR_FILE")+files[1]);
//        //濡傛灉 鏂囦欢涓篸oc鎴栬�厁ls鎴栬�卲pt 鎴栬�� txt
//        if(file.contains(".doc") || file.contains(".xls") || file.contains(".ppt")|| file.contains(".txt")){
//        	//灏辨妸璺緞鐨勩�俻df鏇挎崲涓虹┖瀛楃
//        	downLoadPath = file.replaceAll(".pdf", "");
//        	fileName = fileName.replaceAll(".pdf", "");
//        }else{
//        	downLoadPath = file;
//        }
//        //鏂囦欢鍚�
//        fileName=fileName.substring(fileName.lastIndexOf("\\")+1, fileName.length());
//        File file1 = new File(downLoadPath);  
//        String fileName1 = file1.getName();  
//        String suffix = fileName1.substring(fileName1.lastIndexOf(".") + 1);  
//        System.out.println(suffix);
//        try {
//            long fileLength = new File(downLoadPath).length();   
//            response.setContentType("application/x-msdownload;");   
//            response.setHeader("Content-disposition", "attachment; filename="  + new String((title+"."+suffix).getBytes("utf-8"), "ISO8859-1"));   
//            response.setHeader("Content-Length", String.valueOf(fileLength));   
//            bis = new BufferedInputStream(new FileInputStream(downLoadPath));   
//            bos = new BufferedOutputStream(response.getOutputStream());   
//            byte[] buff = new byte[1024*1024];   
//            int bytesRead;   
//            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {   
//                bos.write(buff, 0, bytesRead);   
//            }  
//            bos.flush();
//        } catch (Exception e) {   
//            e.printStackTrace();   
//        } finally {   
//            if (bis != null)   
//                bis.close();   
//            if (bos != null)   
//                bos.close();   
//        }   
//    }   
	
	
	
    
    /**
     * 
      * @Title: listCourseByTypePage
      * @Description: 预览页面数据分页查询
      * @param @param id       文件id
      * @param @param keywords 关键字    
      * @param @param type     文件类型
      * @param @param pageNo   
      * @param @param pageSize
      * @param @return    设定文件
      * @return Page<Course>    返回类型
      * @throws
     */
	@RequestMapping(value = "typeList")
	@ResponseBody
	public Page<Course> listCourseByTypePage(String id,String keywords,int type, String pageNo, String pageSize) {
		int skip = Integer.parseInt(PropertiesUtil.getValue("page.pageNo"));//从第几条开始取数据
		int max = Integer.parseInt(PropertiesUtil.getValue("page.pageSize"));//每页显示多少条数据
		Page<Course> page = new Page<Course>();//Page类
		List<Course> list = new ArrayList<Course>();//课程资源列表
		if (StringUtils.isNotBlank(pageSize)) {
			max = Integer.parseInt(pageSize);
		}
		if (StringUtils.isNotBlank(pageNo)) {
			skip = max * (Integer.parseInt(pageNo) - 1);// mysql中记录从0开始
			page.setPageNo(Integer.parseInt(pageNo));
		} else {
			page.setPageNo(1);
		}
		try {
			page.setTotalCount(courseService.getCourseTypeCount(id,keywords, type));//根据查询条件获取文件资源总条数
			list = courseService.queryCourseByTypePage(id,keywords, type,skip, max);//根据查询条件获取文件资源列表
			page.setResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return page;
	}
	
	
	/** 
     * 　* 清除下载列表 　 
     */  
    public void resetList() {  
        vDownLoad.clear();  
        vFileList.clear();  
    }  
  
    /** 
     * 　* 增加下载列表项 　* 　* @param url String 　* @param filename String 　 
     */  
    public void addItem(String url, String filename) {  
        vDownLoad.add(url);  
        vFileList.add(filename);  
    }  
  
    /** 
     * 　* 根据列表下载资源 　 
     */  
    public void downLoadByList() {  
        String url = null;  
        String filename = null;  
        // 按列表顺序保存资源  
        for (int i = 0; i < vDownLoad.size(); i++) {  
            url = (String) vDownLoad.get(i);  
            filename = (String) vFileList.get(i);  
            try {  
                saveToFile(url, filename);  
            } catch (IOException err) {  
                if (DEBUG) {  
                    System.out.println("资源[" + url + "]下载失败!!!");  
                }  
            }  
        }  
        if (DEBUG) {  
            System.out.println("下载完成!!!");  
        }  
    }  
  
    /** 
     * 将HTTP资源另存为文件 
     *  
     * @param destUrl 
     *            String 
     * @param fileName 
     *            String 
     * @throws Exception 
     */  
    public void saveToFile(String destUrl, String fileName) throws IOException {  
        FileOutputStream fos = null;  
        BufferedInputStream bis = null;  
        HttpURLConnection httpUrl = null;  
        URL url = null;  
        byte[] buf = new byte[BUFFER_SIZE];  
        int size = 0;  
        // 建立链接  
        url = new URL(destUrl);  
        httpUrl = (HttpURLConnection) url.openConnection();  
        // 连接指定的资源  
        httpUrl.connect();  
        // 获取网络输入流  
        bis = new BufferedInputStream(httpUrl.getInputStream());  
        // 建立文件  
        fos = new FileOutputStream(fileName);  
        if (this.DEBUG)  
            System.out.println("正在获取链接[" + destUrl + "]的内容.../n将其保存为文件["  
                    + fileName + "]");  
        // 保存文件  
        while ((size = bis.read(buf)) != -1)  
            fos.write(buf, 0, size);  
        fos.close();  
        bis.close();  
        httpUrl.disconnect();  
    }  
  
    /** 
     * 将HTTP资源另存为文件 
     *  
     * @param destUrl 
     *            String 
     * @param fileName 
     *            String 
     * @throws Exception 
     */  
    public void saveToFile2(String destUrl, String fileName) throws IOException {  
        FileOutputStream fos = null;  
        BufferedInputStream bis = null;  
        HttpURLConnection httpUrl = null;  
        URL url = null;  
        byte[] buf = new byte[BUFFER_SIZE];  
        int size = 0;  
        // 建立链接  
        url = new URL(destUrl);  
        httpUrl = (HttpURLConnection) url.openConnection();  
        // String authString = "username" + ":" + "password";  
        String authString = "50301" + ":" + "88888888";  
        String auth = "Basic "  
                + new sun.misc.BASE64Encoder().encode(authString.getBytes());  
        httpUrl.setRequestProperty("Proxy-Authorization", auth);  
        // 连接指定的资源  
        httpUrl.connect();  
        // 获取网络输入流  
        bis = new BufferedInputStream(httpUrl.getInputStream());  
        // 建立文件  
        fos = new FileOutputStream(fileName);  
        if (this.DEBUG)  
            System.out.println("正在获取链接[" + destUrl + "]的内容.../n将其保存为文件["  
                    + fileName + "]");  
        // 保存文件  
        while ((size = bis.read(buf)) != -1)  
            fos.write(buf, 0, size);  
        fos.close();  
        bis.close();  
        httpUrl.disconnect();  
    }  
  
    /** 
     * 设置代理服务器 
     *  
     * @param proxy 
     *            String 
     * @param proxyPort 
     *            String 
     */  
    public void setProxyServer(String proxy, String proxyPort) {  
        // 设置代理服务器  
        System.getProperties().put("proxySet", "true");  
        System.getProperties().put("proxyHost", proxy);  
        System.getProperties().put("proxyPort", proxyPort);  
    }  
	
	
}
