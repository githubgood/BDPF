package com.xunfang.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.cfg.DefaultConfig;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

/**
 * 
 * @ClassName Tools
 * @Description: 工具类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年5月26日 上午9:39:44
 * @version V1.0
 */
public class Tools {

	public static final String TRANSACTION_TOKEN_KEY ="org.apache.struts.action.TOKEN";
	
	/**
	 * 定义消息序号开始
	 */
	public static int number = 10001;

	private static long pkId; // 唯一Long型主码
	static Logger logger = Logger.getLogger(Tools.class);

	public static synchronized long getPkId() {
		long lTmp = System.currentTimeMillis();
		if (pkId < lTmp) {
			pkId = lTmp;
		} else {
			pkId++;
		}
		return pkId;
	}

	/**
	 * byte转化为byte数组
	 * 
	 * @param n
	 *            整数
	 * @return 字节数组
	 */
	public static byte[] byteToByteArray(int n) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		try {
			dos.writeByte(n);
			return out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * int转化为byte数组
	 * 
	 * @param n
	 *            整数
	 * @return 字节数组
	 */
	public static byte[] intToByteArray(int n) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		try {
			dos.writeInt(n);
			return out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * short转化为byte数组
	 * 
	 * @param n
	 *            整数
	 * @return 字节数组
	 */
	public static byte[] shortToByteArray(int n) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		try {
			dos.writeShort(n);
			return out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * byte数组转化为byte
	 * 
	 * @param byteArray
	 *            字节数组
	 * @return 整数
	 */
	public static int byteArrayToByte(byte[] byteArray) {
		ByteArrayInputStream input = new ByteArrayInputStream(byteArray);
		DataInputStream dis = new DataInputStream(input);
		try {
			return dis.readByte() & 0xFF;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * byte数组转化为Short
	 * 
	 * @param byteArray
	 *            字节数组
	 * @return 整数
	 */
	public static int byteArrayToShort(byte[] byteArray) {
		ByteArrayInputStream input = new ByteArrayInputStream(byteArray);
		DataInputStream dis = new DataInputStream(input);
		try {
			return dis.readShort() & 0xFFFF;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * byte数组转化为int
	 * 
	 * @param byteArray
	 *            字节数组
	 * @return 整数
	 */
	public static int byteArrayToInt(byte[] byteArray) {
		ByteArrayInputStream input = new ByteArrayInputStream(byteArray);
		DataInputStream dis = new DataInputStream(input);
		try {
			return dis.readInt() & 0xFFFFFFFF;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * @Title:bytes2HexString 
	 * @Description:字节数组转16进制字符串 
	 * @param b 字节数组 
	 * 
	 * @return 16进制字符串 
	 * @throws
	 */
	public static String bytes2HexString(byte[] b) {
		StringBuffer result = new StringBuffer();
		String hex;
		for (int i = 0; i < b.length; i++) {
			hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			result.append(hex.toUpperCase());
		}
		return result.toString();
	}

	/**
	 * @Title:hexString2Bytes 
	 * @Description:16进制字符串转字节数组 
	 * @param src 16进制字符串
	 *  
	 * @return 字节数组 
	 * @throws
	 */
	public static byte[] hexString2Bytes(String src) {
		int l = src.length() / 2;
		byte[] ret = new byte[l];
		for (int i = 0; i < l; i++) {
			ret[i] = (byte) Integer.valueOf(src.substring(i * 2, i * 2 + 2), 16).byteValue();
		}
		return ret;
	}

	/**
	 * @Title:hexString2String 
	 * @Description:16进制字符串转字符串 
	 * @param src 16进制字符串 
	 * 
	 * @return 字节数组 
	 * @throws
	 */
	public static String hexString2String(String src) {
		String temp = "";
		for (int i = 0; i < src.length() / 2; i++) {
			temp = temp + (char) Integer.valueOf(src.substring(i * 2, i * 2 + 2), 16).byteValue();
		}
		return temp;
	}

	/**
	 * @Title:char2Byte 
	 * @Description:字符转成字节数据char-->integer-->byte 
	 * @param  src
	 *  
	 * @return 
	 * @throws
	 */
	public static Byte char2Byte(Character src) {
		return Integer.valueOf((int) src).byteValue();
	}

	/**
	 * @Title:intToHexString 
	 * @Description:10进制数字转成16进制 
	 * @param a 转化数据 
	 * @param len 占用字节数
	 *  
	 * @return 
	 * @throws
	 */
	public static String intToHexString(int a, int len) {
		len <<= 1;
		String hexString = Integer.toHexString(a);
		int b = len - hexString.length();
		if (b > 0) {
			for (int i = 0; i < b; i++) {
				hexString = "0" + hexString;
			}
		}
		return hexString;
	}

	/**
	 * 字符串转16进制字符串 add jm 2015-12-10
	 * 
	 * @param strPart
	 *            字符串
	 * @return 16进制字符串
	 */
	public static String string2HexString(String strPart) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < strPart.length(); i++) {
			int ch = (int) strPart.charAt(i);
			String strHex = Integer.toHexString(ch);
			hexString.append(strHex);
		}
		return hexString.toString();
	}

	/**
	 * 16进制的字符串转为字节数组 add jm 2015-12-10
	 * 
	 * @param s
	 *            String 16进制字符串
	 * @return 数组
	 */
	public static byte[] toStringHex1(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
		}
		return baKeyword;
	}

	/**
	 * 
	 * @Title: asc_to_bcd
	 * @param asc
	 * @return byte
	 */
	public static byte asc_to_bcd(byte asc) {
		byte bcd;
		if ((asc >= '0') && (asc <= '9'))
			bcd = (byte) (asc - '0');
		else if ((asc >= 'A') && (asc <= 'F'))
			bcd = (byte) (asc - 'A' + 10);
		else if ((asc >= 'a') && (asc <= 'f'))
			bcd = (byte) (asc - 'a' + 10);
		else
			bcd = (byte) (asc - 48);
		return bcd;
	}

	public static byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {
		byte[] bcd = new byte[asc_len / 2];
		int j = 0;
		for (int i = 0; i < (asc_len + 1) / 2; i++) {
			bcd[i] = asc_to_bcd(ascii[j++]);
			bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));
		}
		return bcd;
	}

	public static String bcd2Str(byte[] bytes) {
		char temp[] = new char[bytes.length * 2], val;
		for (int i = 0; i < bytes.length; i++) {
			val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
			temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

			val = (char) (bytes[i] & 0x0f);
			temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
		}
		return new String(temp);
	}

	/** */
	/**
	 * @函数功能: BCD码转ASC码
	 * @输入参数: BCD串
	 * @输出结果: ASC码
	 */
	public final static char[] BToA = "0123456789ABCDEF".toCharArray();

	public static byte[] BCD2ASC(byte[] bytes) {
		StringBuffer temp = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			int h = ((bytes[i] & 0xf0) >>> 4);
			int l = (bytes[i] & 0x0f);
			temp.append(BToA[h]).append(BToA[l]);
		}
		return temp.toString().getBytes();
	}

	/**
	 * 
	 * @Title: getNowTime
	 * @Description: 获得当前时间到秒
	 * @return String 获得当前时间的字符串变量，如"2017-03-15 16:54:30"
	 */
	public static String getNowTime() {
		return new java.sql.Timestamp(System.currentTimeMillis()).toString().substring(0, 19);
	}

	/**
	 * @Title : getNow
	 * @Description: 获得当前时间
	 * @return String 获得当前时间的字符串变量，如"170315165430"
	 */
	public static String getNow() {
		Date d = new Date();
		String str = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
		str = formatter.format(d);
		return str;
	}

	/**
	 * 获得消息序列号
	 */
	public static String getMessageSequence() {
		if (number > 29999) {
			number = 1;
		}
		String time = (number++) + "";
		return time;
	}

	/**
	 * compareArrStrFindDiff
	 * 
	 * @Description: 比较两个数组去掉重复的数据并生成随机数
	 * @param source
	 *            第一个数组【数据较多且已经包含了s2中所有元素】
	 * @param target
	 *            第一个数组【数据较少且所有元素都在s1中】
	 * @return String
	 */
	public static String compareArrStrFindDiff(String[] source, String[] target) {
		String[] result = null;
		StringBuffer sb = new StringBuffer();
		Integer index = 0;
		for (int i = 0; i < source.length; i++) {
			boolean flag = true;
			for (int j = 0; j < target.length; j++) {
				if (source[i].equals(target[j])) {
					flag = false;
					break;
				}
			}
			if (flag) {
				sb.append(index == 0 ? source[i] : "," + source[i]);
				index++;
			}
		}
		if (sb.length() > 0) {
			result = sb.toString().split(",");
			// 生成随机数
			Random rad = new Random();
			int indexs = rad.nextInt(result.length);
			return result[indexs];
		}
		return null;
	}
	
	// @描述：是否是2003的excel，返回true是2003 
    public static boolean isExcel2003(String filePath)  {
         return filePath.matches("^.+\\.(?i)(xls)$");  
    }  
   
    //@描述：是否是2007的excel，返回true是2007 
    public static boolean isExcel2007(String filePath)  {  
         return filePath.matches("^.+\\.(?i)(xlsx)$");  
    }
   
    /**
     * 
      * @Title: stringToInt
      * @Description: 字符串转为int
      *  @param string
      *  @return  int 返回类型
      * @throws
     */
    public static int stringToInt(String string){
    	  if(string==null||"".equals(string)){
    		  return 0;
    	  }
    	  double ss = Double.parseDouble(string);
    	  return Integer.parseInt(Math.round(ss)+"");
    }
    
    /**
     * 
      * @Title: stringToDouble
      * @Description: 字符串转为double
      *  @param string
      *  @return  double 返回类型
      * @throws
     */
    public static double stringToDouble(String string){
    	  if(string==null||"".equals(string)){
    		  return 0;
    	  }
    	  return Double.parseDouble(string);
    }
	
    /**
     * 
      * @Title: normalization
      * @Description: 归一化处理
      *  @param value String 当前值
      *  @param max String  最大值
      *  @param min String  最小值
      *  @return  double 返回类型
      * @throws
     */
    public static String normalization(String value,String max,String min){
    	//如果默认为0或者1则不做归一化处理
    	if("0".equals(value)||"1".equals(value)){
    		return value;
    	}
    	//如果最大值等于最小值，则为0
    	if(max.equals(min)){
    		return "0";
    	}
    	//如果当前值等于最大值，则为0
    	if(value.equals(max)){
    		return "0";
    	}
    	//进行归一化公式计算：value-min/max-min
    	double result = Math.abs(Double.parseDouble(value)-Double.parseDouble(min))/Math.abs(Double.parseDouble(max)-Double.parseDouble(min));
    	//数据格式化处理
    	NumberFormat nFormat=NumberFormat.getNumberInstance();
    	//设置小数点后面位数为4
        nFormat.setMaximumFractionDigits(4);
        //返回当前格式化以后的数据
    	return nFormat.format(result);
    }
    
    /**
     * 
      * @Title: bigDecimal
      * @Description:求两个数值类型的字符串的差值
      *  @param value1
      *  @param value2
      *  @return  double 返回类型
      * @throws
     */
    public static double bigDecimal(String value1,String value2){
    	String  result = new BigDecimal(value1).subtract(new BigDecimal(value2)) .toString();
    	return Double.parseDouble(result);
    }
    
    /**
     * 
      * @Title: lablePointToList
      * @Description: 将List<LabeledPoint>转为List<List<Object>>
      *  @param list
      *  @return  List<List<Object>> 返回类型
      * @throws
     */
    public static List<List<Object>> lablePointToList(List<LabeledPoint> list){
    	List<List<Object>> outlist = new  ArrayList<List<Object>>();
    	for (LabeledPoint labeledPoint : list) {
    		List<Object> objectlist = new ArrayList<Object>();
			Vector vector = labeledPoint.features();
			double[] testdob = vector.toArray();
			for (int i = 0; i < testdob.length; i++) {
				objectlist.add(testdob[i]);
			}
			objectlist.add(labeledPoint.label());
			
			outlist.add(objectlist);
		}
    	return outlist;
    }
    
    /**
     * 
      * @Title: doubleNumberFormat
      * @Description: double类型数值格式化处理
      *  @param value double 当前数值
      *  @param length int  保留的小数位数
      *  @return  String 返回类型
      * @throws
     */
    public static String doubleNumberFormat(double value,int length){
    	//数据格式化处理
    	NumberFormat nFormat=NumberFormat.getNumberInstance();
    	//设置小数点后面位数为4
        nFormat.setMaximumFractionDigits(length);
        
        String result = "";
        
        if((value+"").length()>10){//只保留10位
        	value = Double.parseDouble((value+"").substring(0,10));
        }
        
        result = nFormat.format(value);
        
        return result;
    }
    
    /**
     * 
      * @Title: appendSymbol
      * @Description: 补全IK分词遗漏的符号
      *  @param line
      *  @param before
      *  @param cur
      *  @return  String 返回类型
      * @throws
     */
    public static String appendSymbol(String line, Lexeme before, Lexeme cur) {  
        String res = "";  
        if (before == null) {// 第一个词前边的符号  
            res = cur.getLexemeText() + " ";  
            int start = cur.getBegin();  
            if (start > 0) {  
                String left =appendWhiteSpace(line.substring(0, start));  
                res = left + res;  
            }  
        } else if (cur == null) {// 最后一个词后边的符号  
            int end = before.getEndPosition();  
            if (end < line.length()) {  
                String right =appendWhiteSpace(line.substring(before.getEndPosition()));  
                res = right;  
            }  
        } else { // 和前一个词之间的符号  
            res = cur.getLexemeText() + " ";  
            int beforeEnd = before.getEndPosition();  
            if (cur.getBegin() > beforeEnd) {  
                String mid = appendWhiteSpace(line.substring(beforeEnd, cur.getBegin()));  
                res = mid + res;  
            }  
        }  
        return res;  
    }  
    /**
     * 
      * @Title: appendWhiteSpace
      * @Description:增加空格
      *  @param src
      *  @return  String 返回类型
      * @throws
     */
    public static String appendWhiteSpace(String src){  
        String dst="";  
        for (char c : src.toCharArray()) {  
            dst += c + " ";  
        }  
        return dst;  
    }
    
    /**
     * 
      * @Title: splitWord
      * @Description: 分词
      * @param input String 输入的字符串
      * @param type  int 处理类型 0:不加符号  1:加符号
      * @return  String 返回类型
      * @throws
     */
    public static String splitWord(String input,int type){
    	String output = "";//输出字符串
//    	if(input==null||"".equals(input)){
//    		return output;
//    	}
//    	String input = "唐山市的10辆大货车多次违法未处理 遭曝光，河北新闻网讯（记者吴艳丽 通讯员张海健）在防事故“四大整治攻坚战”中，唐山市交警部门将整治大货车野蛮驾驶作为预防道路交通事故的“重中之重”。在日常勤务中严厉查处的同时，多次开展集中统一查处行动。然而，有的大货车虽有多次违法记录，但驾驶人却一直未去违法处理室接受处理。鉴于此，交警支队现对截至10月18日“多次违法未处理”的10辆重型货车予以公布。";  
    	Lexeme beforeWord = null;
        Lexeme currentWord = null;
        StringBuffer sb = new StringBuffer();
        IKSegmenter ik = new IKSegmenter(new StringReader(input), true);
        if(type == 0){
        	Lexeme lexeme = null;
        	try {
	            while((lexeme = ik.next())!=null) {
	                String word = lexeme.getLexemeText();
	                sb.append(word+" ");
	            }
    		} catch(IOException e){
    			e.printStackTrace();
    		}
//            System.out.println("分词："+sb.toString().replaceAll(" +", " ").trim());
            output = sb.toString().trim();
        }else{
        	try {
    			while((currentWord = ik.next()) != null){
    			    sb.append(appendSymbol(input, beforeWord, currentWord));
    			    beforeWord = currentWord;
    			}
    		} catch(IOException e){
    			e.printStackTrace();
    		}
            sb.append(appendSymbol(input, beforeWord, currentWord));
//            System.out.println("分词："+sb.toString().replaceAll(" +", " ").trim());
            output = sb.toString().replaceAll(" +", " ").trim();
        }
        
        
        return output;
    }
    
    /**
     * 
      * @Title: isNumeric
      * @Description: 判断当前值是否为数字类型
      *  @param str
      *  @return  boolean 返回类型
      * @throws
     */
    public static boolean isNumeric(String str){
      if("0".equals(str)||"1".equals(str)){
    	  return false;
      }
	  for (int i = 0; i < str.length(); i++){
	   if (!Character.isDigit(str.charAt(i))){
	    return false;
	   }
	  }
	  return true;
	 }
    
    /**
     * 
      * @Title: frequencyCount
      * @Description: 词频统计
      *  @param string  void 返回类型
      * @throws
     */
	public static Map<String,Integer> frequencyCount(String string,LinkedHashMap<String, Integer> wordfre){
		 StringReader reader = new StringReader(string);
		 Configuration configuration = DefaultConfig.getInstance();  
		 configuration.setUseSmart(true);
	     IKSegmenter ik = new IKSegmenter(reader,configuration);//后一个变量决定是否消歧
	     Lexeme lexeme = null;
	     try {
	            while((lexeme = ik.next())!=null) {
	                String word=lexeme.getLexemeText();
	                if (!wordfre.containsKey(word)) {
	                    wordfre.put(word,1);
	                }else {
	                    wordfre.put(word,wordfre.get(word)+1);
	                }
	            }
//	            Set<String> sets = wordfre.keySet();
//	            for (String string2 : sets) {
//					System.out.println("字段名称："+string2+"  对应的次数："+wordfre.get(string2));
//				}
	     }catch(Exception ex){
	    	 System.out.println(ex.getMessage());
	     }
	     return wordfre;
	}
	
//	public static void main(String[] args) {
//		System.out.println(splitWord("唐山市的10辆大货车多次违法未处理 遭曝光，河北新闻网讯（记者吴艳丽 通讯员张海健）在防事故“四大整治攻坚战”中，唐山市交警部门将整治大货车野蛮驾驶作为预防道路交通事故的“重中之重”。在日常勤务中严厉查处的同时，多次开展集中统一查处行动。然而，有的大货车虽有多次违法记录，但驾驶人却一直未去违法处理室接受处理。鉴于此，交警支队现对截至10月18日“多次违法未处理”的10辆重型货车予以公布。", 1));
//		System.out.println(splitWord("唐山市的10辆大货车多次违法未处理 遭曝光，河北新闻网讯（记者吴艳丽 通讯员张海健）在防事故“四大整治攻坚战”中，唐山市交警部门将整治大货车野蛮驾驶作为预防道路交通事故的“重中之重”。在日常勤务中严厉查处的同时，多次开展集中统一查处行动。然而，有的大货车虽有多次违法记录，但驾驶人却一直未去违法处理室接受处理。鉴于此，交警支队现对截至10月18日“多次违法未处理”的10辆重型货车予以公布。", 0));
//	}
	
}