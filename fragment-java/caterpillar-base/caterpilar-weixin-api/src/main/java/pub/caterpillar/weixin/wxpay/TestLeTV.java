package pub.caterpillar.weixin.wxpay;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.http.impl.client.HttpClients;

import pub.caterpillar.communication.http.client.HttpClient;
import pub.caterpillar.weixin.wxpay.WXPayConstants.SignType;

public class TestLeTV {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Map<String, String> reqData = new HashMap<String, String>();
		reqData.put("user_unique", "djvupr7cxk");
		reqData.put("api", "video.download");
		reqData.put("format", "json");
		reqData.put("ver", "2.0");
		reqData.put("timestamp", Long.toString(new Date().getTime()));
		reqData.put("video_id","49369038");
		Set<String> keySet = reqData.keySet();
		String[] keyArray = keySet.toArray(new String[keySet.size()]);
		Arrays.sort(keyArray);

		StringBuilder sb = new StringBuilder();
		for (String k : keyArray) {

			if (reqData.get(k).trim().length() > 0) // 参数值为空，则不参与签名
				sb.append(k).append(reqData.get(k));
		}

		sb.append("747fe87754122748e45b5ec035f06fe3");
		System.out.println("2" + sb.toString());

		String sign = MD5(sb.toString());
		String sign1=encrypt(sb.toString());
		System.out.println(sign+"---------"+sign1);
		reqData.put("sign", sign);
		
		String result=HttpClient.postHttps("http://api.letvcloud.com/open.php", reqData);
		System.out.println(result);
//		StringBuilder sb2 = new StringBuilder();
//		for (String k : keyArray) {
//
//			if (reqData.get(k).trim().length() > 0) // 参数值为空，则不参与签名
//				sb2.append(k).append("=").append(reqData.get(k).trim()).append("&");
//		}
//		String paramsString = sb2.toString();
//
//		try {
//			// reqData.put("", "");
//			// reqData.put("", "");
//			paramsString = paramsString + "sign=" + sign;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			String result = HttpClient.get("http://api.letvcloud.com/open.php?" + paramsString);
//			System.out.println("3" + result);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("4" + paramsString);
	}

	public static String MD5(String data) throws Exception {
        java.security.MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }
	
	
	private static MessageDigest digester;

	static {
		try {
			digester = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将任意的字符串进行md5加密，并返回加密后的十六进制字符串。
	 * 需要注意，MessageDigest是非线程安全的，所以需要使用synchronized同步。
	 * 
	 * @param str
	 *            待加密字符串
	 * @return 返回md5加密后的十六进制字符串
	 */
	public static String encrypt(String str) {
		if (digester == null || str == null || str.length() == 0) {
			return null;
		}

		synchronized (digester) {
			try {
				digester.update(str.getBytes("UTF-8"));
				String s1 = new BigInteger(1, digester.digest()).toString(16);
				// 补齐BigInteger省略的前置0
				return new String(new char[32 - s1.length()]).replace("\0", "0") + s1;
			} catch (Exception e) {
				// 一般不会有异常抛出， 该死的Java受检异常，导致丑陋的代码
			}
		}

		return null;
	}

}
