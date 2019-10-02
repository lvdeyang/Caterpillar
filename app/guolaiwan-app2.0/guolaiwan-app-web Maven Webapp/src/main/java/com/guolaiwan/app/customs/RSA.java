package com.guolaiwan.app.customs;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

public class RSA {
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * 公钥加密
	 *
	 * @param publicKey
	 *            公钥
	 * @param src
	 *            明文
	 * @param encode
	 *            编码方式
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(PublicKey publicKey, String src, String encode) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] data = src.getBytes(encode);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return Base64.encodeBase64String(encryptedData);
	}

	/**
	 * 得到公钥对象
	 *
	 * @param key
	 *            密钥字符串（经过16进制编码）
	 * @throws Exception
	 */
	public static PublicKey getPublicKey(String key) {
		try {
			byte[] keyBytes = hexToByte(key.trim());
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
			String info = "getPublicKey failed: " + key + " | " + e.getMessage();
			return null;
		}
	}
	
	public static byte[] hexToByte(String hex){
        int m = 0, n = 0;
        int byteLen = hex.length() / 2; // 每两个字符描述一个字节
        byte[] ret = new byte[byteLen];
        for (int i = 0; i < byteLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            int intVal = Integer.decode("0x" + hex.substring(i * 2, m) + hex.substring(m, n));
            ret[i] = Byte.valueOf((byte)intVal);
        }
        return ret;
    }
	
}
