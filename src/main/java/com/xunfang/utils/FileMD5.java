package com.xunfang.utils;

/**
 * @ClassName FILEMD5
 * @Description: 文件加密工具类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年6月12日 上午10:51:47
 * @version V1.0
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;  
  
public class FileMD5 {  
     /** 
     * 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符,apache校验下载的文件的正确性用的就是默认的这个组合 
     */  
    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6','7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };  
   
    /**
     * JAVA自带的数据加密类
     */
    protected static MessageDigest messagedigest = null;
    
    /**
     * 初始化数据加密类
     */
    static {  
        try {
            messagedigest = MessageDigest.getInstance("MD5");  
        } catch (NoSuchAlgorithmException nsaex) {  
            System.err.println(FileMD5.class.getName()+ "初始化失败，MessageDigest不支持MD5Util。");  
            nsaex.printStackTrace();  
        }
    }  
       
    /**
     * 
      * @Title: getMD5String
      * @Description: 生成字符串的md5校验值
      *  @param s String 要生成的字符串
      *  @return  String 返回类型
      * @throws
     */
    public static String getMD5String(String s) {  
        return getMD5String(s.getBytes());  
    }  
       
    /**
     * 
      * @Title: checkPassword
      * @Description: 判断字符串的md5校验码是否与一个已知的md5码相匹配 
      *  @param md5 String 要校验的字符串 
      *  @param md5PwdStr String 已知的md5校验码 
      *  @return  boolean 返回类型
      * @throws
     */
    public static boolean checkPassword(String md5, String md5PwdStr) {  
        return md5.equals(md5PwdStr);  
    }  
       
    /**
     * 
      * @Title: getFileMD5String
      * @Description: 生成文件的md5校验值
      *  @param file File  要校验的文件
      *  @return
      *  @throws IOException  String 返回类型
      * @throws
     */
    public static String getFileMD5String(File file) throws IOException {
    	String encrStr="";
    	// 读取文件  
        FileInputStream fis = new FileInputStream(file);  
        // 当文件<2G可以直接读取  
        if(file.length() <= Integer.MAX_VALUE) {  
            encrStr = getMD5Lt2G(file, fis);  
        } else { // 当文件>2G需要切割读取  
            encrStr = getMD5Gt2G(fis);  
        } 
        fis.close();  
        return encrStr;
    }  
    
    /** 
     * 
      * @Title: getMD5Lt2G
      * @Description: 小于2G文件  
      *  @param file File 文件
      *  @param fis FileInputStream 文件输入流
      *  @return
      *  @throws IOException  String 返回类型
      * @throws
     */
    public static String getMD5Lt2G(File file, FileInputStream fis) throws IOException {  
        // 加密码  
        String encrStr="";  
        FileChannel ch = fis.getChannel();    
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());    
        messagedigest.update(byteBuffer);   
        encrStr = bufferToHex(messagedigest.digest()); 
        return encrStr;   
    }
   
    /** 
     * 
      * @Title: getMD5Gt2G
      * @Description: 超过2G文件的md5算法  
      *  @param fis InputStream 写入流
      *  @return
      *  @throws IOException  String 返回类型
      * @throws
     */
    public static String getMD5Gt2G(InputStream fis)  throws IOException {     
        // 自定义文件块读写大小，一般为4M，对于小文件多的情况可以降低  
        byte[] buffer = new byte[1024*1024*4];      
        int numRead = 0;      
        while ((numRead = fis.read(buffer)) > 0) {      
            messagedigest.update(buffer, 0, numRead);      
        }      
        return bufferToHex(messagedigest.digest());      
    }      
    
    /**
     * 
      * @Title: getMD5String
      * @Description: 通过字节获取字符串
      *  @param bytes byte[] 要转换的字节数组
      *  @return  String 返回类型
      * @throws
     */
    public static String getMD5String(byte[] bytes) {  
        messagedigest.update(bytes);  
        return bufferToHex(messagedigest.digest());  
    }  
   
    /**
     * 
      * @Title: bufferToHex
      * @Description: bype数组转为16进制字符串
      *  @param bytes byte  要转换的byte数组
      *  @return  String 返回类型
      * @throws
     */
    private static String bufferToHex(byte bytes[]) {  
        return bufferToHex(bytes, 0, bytes.length);  
    }  
   
    /**
     * 
      * @Title: bufferToHex
      * @Description: byte数组转为字符串
      *  @param bytes byte
      *  @param m int
      *  @param n int
      *  @return  String 返回类型
      * @throws
     */
    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);  
        int k = m + n;  
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);  
        }  
        return stringbuffer.toString();  
    }  
   
    /**
     * 
      * @Title: appendHexPair
      * @Description: 字节处理
      *  @param bt byte 文件字节流 
      *  @param stringbuffer  StringBuffer 文件缓存
      *  void 返回类型
      * @throws
     */
    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {  
        char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同   
        char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换   
        stringbuffer.append(c0);  
        stringbuffer.append(c1);  
    }  
       
    /*public static void main(String[] args) throws IOException {
        long begin = System.currentTimeMillis();
   
        File file = new File("D:/test/12345.txt");
        String md5 = getFileMD5String(file);
          
        //文件名不同，内容相同；  
        File file2 = new File("D:/test/12345(1).txt");
        String md52= getFileMD5String(file2);
          
          
        //文件名不同，内容不同；  
        File file3 = new File("D:/test/12345(2).txt");
        String md53= getFileMD5String(file3);
          
        
        //测试压缩包  
        File fileZip = new File("D:/test/12345.zip");
        String md5Zip= getFileMD5String(fileZip);
          
        //测试压缩包  
        File fileZip2 = new File("D:/test/12345(2).zip");
        String md5Zip2= getFileMD5String(fileZip2);
             
        System.out.println("MD5:"+md5);
        System.out.println("MD5:"+md52);
        System.out.println("MD5:"+md53);
        System.out.println("MD5:"+md5Zip);
        System.out.println("MD5:"+md5Zip2);
        System.out.println("两个文件名不同，内同相同"+ checkPassword(md5, md52));
        System.out.println("文件名不同，内容不同"+ checkPassword(md5, md53));
        System.out.println("测试压缩包,内容不同"+ checkPassword(md5Zip, md5Zip2));
        
        long end = System.currentTimeMillis();  
        System.out.println("md5:" + md5 + " time:" + ((end - begin) / 1000) + "s");  
    }*/
}