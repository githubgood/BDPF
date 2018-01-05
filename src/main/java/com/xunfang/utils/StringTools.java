package com.xunfang.utils;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 字符串扩展类
 * @author yaolianhua
 *
 */
public class StringTools {


		public static UUIDGenerator ug = new UUIDGenerator();

		public static String getUUID() {
			return ug.generate().toString();
		}

		/**
		 * 把中文转成Unicode码
		 * 
		 * @param str
		 * @return
		 */
		public static String chinaToUnicode(String str) {
			String result = "";
			for (int i = 0; i < str.length(); i++) {
				int chr1 = (char) str.charAt(i);
				if (chr1 >= 19968 && chr1 <= 171941) {// 汉字范围 \u4e00-\u9fa5 (中文)
					result += "\\u" + Integer.toHexString(chr1);
				} else {
					result += str.charAt(i);
				}
			}
			return result;
		}

		/**
		 * 
		 * Unicode编码字符串，转汉字
		 * 
		 * @return
		 */

		public static String convert(String utfString) {
			StringBuilder sb = new StringBuilder();
			int i = -1;
			int pos = 0;

			while ((i = utfString.indexOf("\\u", pos)) != -1) {
				sb.append(utfString.substring(pos, i));
				if (i + 5 < utfString.length()) {
					pos = i + 6;
					sb.append((char) Integer.parseInt(utfString.substring(i + 2, i + 6), 16));
				}
			}

			return sb.toString();
		}

		public static String toUnicode(String str) {
			char[] arChar = str.toCharArray();
			int iValue = 0;
			String uStr = "";
			for (int i = 0; i < arChar.length; i++) {
				iValue = (int) str.charAt(i);
				if (iValue <= 256) {
					// uStr+="& "+Integer.toHexString(iValue)+";";
					uStr += "\\" + Integer.toHexString(iValue);
				} else {
					// uStr+="&#x"+Integer.toHexString(iValue)+";";
					uStr += "\\u" + Integer.toHexString(iValue);
				}
			}
			return uStr;
		}

		/**
		 * 生成一个UUID
		 * 
		 * @return
		 */
		public static final String newUUID() {
			return UUID.randomUUID().toString().replace("-", "").toUpperCase();
		}

		/**
		 * 检查一个字符串是否为null或者为empty
		 * 
		 * @param s
		 * @return
		 */
		public static final boolean stringIsNullOrEmpty(String s) {
			return s == null || s.isEmpty();
		}

		/**
		 * 拼接字符串
		 * 
		 * @param array
		 * @param separator
		 * @return
		 */
		public final static <T> String stringJoin(List<T> array, char separator) {
			if (array == null) {
				return null;
			}
			int arraySize = array.size();
			int bufSize = (arraySize == 0 ? 0 : ((array.get(0) == null ? 16 : array.get(0).toString().length()) + 1) * arraySize);
			StringBuffer buf = new StringBuffer(bufSize);

			for (int i = 0; i < arraySize; i++) {
				if (i > 0) {
					buf.append(separator);
				}
				if (array.get(i) != null) {
					buf.append(array.get(i));
				}
			}
			return buf.toString();
		}

		public final static <T> String stringJoin(List<T> array, String separator) {
			if (array == null) {
				return null;
			}
			int arraySize = array.size();
			int bufSize = (arraySize == 0 ? 0 : ((array.get(0) == null ? 16 : array.get(0).toString().length()) + 1) * arraySize);
			StringBuffer buf = new StringBuffer(bufSize);

			for (int i = 0; i < arraySize; i++) {
				if (i > 0) {
					buf.append(separator);
				}
				if (array.get(i) != null) {
					buf.append(array.get(i));
				}
			}
			return buf.toString();
		}

		/**
		 * 把字符串转换成数组
		 * 
		 * @param s
		 * @return
		 */
		public static List<String> stringToList(String s) {
			List<String> list = new ArrayList<String>();
			String[] str = s.split(";");
			for (String string : str) {
				list.add(string);
			}
			return list;
		}

		public static List<String> stringtoList(String s, String separator) {
			List<String> list = new ArrayList<String>();
			String[] str = s.split(separator);
			for (String string : str) {
				list.add(string);
			}
			return list;
		}

		/**
		 * 判断多个字符串是否为空
		 * 
		 * @author Dick 2015年2月7日
		 * @Email 823882651@qq.com
		 * @Desc
		 * @param strs
		 * @return
		 */
		public static Boolean stringIsNullOrEmpty(String... strs) {
			for (String str : strs) {
				if (str == null || str.isEmpty()) {
					return true;
				}
			}
			return false;
		}
	
}
