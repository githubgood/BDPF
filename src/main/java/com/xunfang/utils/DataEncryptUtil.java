package com.xunfang.utils;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @ClassName DataEncryptUtil
 * @Description: 数据加密，解密
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年5月26日 上午9:38:56
 * @version V1.0
 */
public class DataEncryptUtil {

	/** 密钥key */
	private static final String SECRET_KEY = "HTMD5KYE";
	/** 密钥key编码 */
	private static final String SECRET_KEY_SPEC = "DES";
	/** 加密密钥 */
	private static final String CIPHER_KEY = "DES/CBC/PKCS5Padding";

	/**
	 * 得到一个规定的密钥
	 * 
	 * @param plainKey
	 *            ,原始8位密钥字符串，如：test1234
	 * @return Key
	 */
	private static Key getKeyByPlainText(String plainKey) {
		try {
			return new SecretKeySpec(plainKey.getBytes(), SECRET_KEY_SPEC);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 字节转换为十六进制
	 * 
	 * @param abyte0
	 * @return
	 */
	private static String byte2Hex(byte abyte0[]) {
		StringBuffer stringbuffer = new StringBuffer();
		for (int i = 0; i < abyte0.length; i++) {
			String s = Integer.toHexString(abyte0[i] & 0xff);
			if (s.length() != 2)
				stringbuffer.append('0').append(s);
			else
				stringbuffer.append(s);
		}

		return new String(stringbuffer);
	}

	/**
	 * 十六进制转换为字节
	 * 
	 * @param s
	 * @return
	 */
	private static byte[] hex2Byte(String s) {
		int i = s.length() / 2;
		byte abyte0[] = new byte[i];
		for (int j = 0; j < i; j++) {
			String s1 = s.substring(j * 2, j * 2 + 2);
			abyte0[j] = (byte) Integer.parseInt(s1, 16);
		}

		return abyte0;
	}

	/**
	 * 将指定的数据根据提供的密钥进行加密
	 * 
	 * @param key
	 *            密钥
	 * @param data
	 *            需要加密的数据
	 * @return byte[] 加密后的数据
	 * @throws util.EncryptException
	 */
	private static byte[] doEncrypt(Key key, byte[] data) {
		try {
			// Get a cipher object
			Cipher cipher = Cipher.getInstance(CIPHER_KEY);
			IvParameterSpec iv = new IvParameterSpec(
					SECRET_KEY.getBytes("UTF-8"));
			// Encrypt
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			// byte[] stringBytes = amalgam.getBytes("UTF8");
			byte[] raw = cipher.doFinal(data);
			// BASE64Encoder encoder = new BASE64Encoder();
			// String base64 = encoder.encode(raw);
			return raw;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将给定的已加密的数据通过指定的密钥进行解密
	 * 
	 * @param key
	 *            密钥
	 * @param raw
	 *            待解密的数据
	 * @return byte[] 解密后的数据
	 * @throws util.EncryptException
	 */
	private static byte[] doDecrypt(Key key, byte[] raw) {
		try {
			// Get a cipher object
			Cipher cipher = Cipher.getInstance(CIPHER_KEY);
			IvParameterSpec iv = new IvParameterSpec(
					SECRET_KEY.getBytes("UTF-8"));
			// Decrypt
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
			// BASE64Decoder decoder = new BASE64Decoder();
			// byte[] raw = decoder.decodeBuffer(data);
			byte[] data = cipher.doFinal(raw);
			// String result = new String(stringBytes, "UTF8");
			// System.out.println("the decrypted data is: " + result);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 加密
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static synchronized String desEncrypt(String data) {

		Key key = getKeyByPlainText(SECRET_KEY);
		String thetext = "";
		try {
			byte[] orgData = data.getBytes("UTF8");
			byte[] raw = doEncrypt(key, orgData);
			thetext = byte2Hex(raw).toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return thetext;
	}

	/**
	 * 解密
	 * 
	 * @throws java.security.NoSuchAlgorithmException
	 */
	public static synchronized String desDecrypt(String hexData) {

		String encryptedHexString = hexData; // 16进制编码的加密数据
		String theText = "";
		try {
			Key key = getKeyByPlainText(SECRET_KEY);

			byte[] data = doDecrypt(key, hex2Byte(encryptedHexString));

			theText = new String(data, "UTF8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return theText;
	}

	/**
	 * 将字符串转换成Sha256算法加密
	 * 
	 * @param inputStr
	 * @return
	 */
	public static synchronized String encryptSha256(String inputStr) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			byte digest[] = md.digest(inputStr.getBytes("UTF-8"));

			return new String(Base64.encodeBase64(digest));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将字符串转换成MD5算法加密
	 * 
	 * @param inputStr
	 * @return
	 */
	public static synchronized String encryptMd5(String inputStr) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(inputStr.getBytes());
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(Integer.toHexString((int) (b & 0xff)));
			}

			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}

	// 测试
	public static void main(String[] args) throws Exception {
		String data = "520zhuangxiaohui";
		String a = DataEncryptUtil.desEncrypt(data);
		String b = DataEncryptUtil.desDecrypt(a);
		System.out.println(data + "加密后的数据：" + a);
		System.out.println(data + "解密后的数据：" + b);
	}
}
