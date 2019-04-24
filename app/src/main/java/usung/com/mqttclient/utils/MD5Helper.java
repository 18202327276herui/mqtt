package usung.com.mqttclient.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 帮助类
 */
public class MD5Helper {

	/**
	 * 将 字符 进行MD5加密
	 * 
	 * @return String 16进制的字符串
	 */
	public static String Encrypt5(String str) {
		byte[] digest = null;
		try {
			// 将字符转换
			MessageDigest digester = MessageDigest.getInstance("MD5");
			byte[] bytes = str.getBytes("UTF-8");
			digester.update(bytes);
			// 得到的是10进制的byte数组
			digest = digester.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5出错", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("String 转换成 Byte 编码出错", e);
		}
		// 将10进制的数转换成16进制的数
		StringBuffer hex = new StringBuffer(digest.length * 2);
		for (byte b : digest) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}

	/**
	 * 将 字符 进行MD5加密
	 * 
//	 * @param String str
	 * @return byte[]
	 */
	public static byte[] Encrypt5ToByte(String str) {
		byte[] digest = null;
		try {
			// 将字符转换
			MessageDigest digester = MessageDigest.getInstance("MD5");
			byte[] bytes = str.getBytes("UTF-8");
			digester.update(bytes);
			// 得到的是10进制的byte数组
			digest = digester.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5出错", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("String 转换成 Byte 编码出错", e);
		}

		return digest;
	}

	public static byte[] Encrypt5ToByte(byte[] str) {
		byte[] digest = null;
		try {
			// 将字符转换
			MessageDigest digester = MessageDigest.getInstance("MD5");
			digester.update(str);
			// 得到的是10进制的byte数组
			digest = digester.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5出错", e);
		}

		return digest;
	}
}
