package com.guolaiwan.app.interfac.alipay;

import java.util.HashMap;
import java.util.Map;

import com.guolaiwan.app.interfac.alipay.OrderInfoUtil2_0;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;

import pub.caterpillar.weixin.constants.WXContants;
import pub.caterpillar.weixin.wxpay.GuolaiwanWxPay;

public class AliAppOrderInfo {
	private AliAppOrderInfo() {
		super();
	}
	private static AliAppOrderInfo instance;

	public static synchronized AliAppOrderInfo getInstance() throws Exception {
		if (instance == null) {
			instance = new AliAppOrderInfo();
		}
		return instance;
	}

	/** 支付宝支付业务：入参app_id */
	public static final String APPID = "2016091401902378";

	/** 支付宝账户登录授权业务：入参pid值 */
	public static final String PID = "2088021513724979";
	/** 支付宝账户登录授权业务：入参target_id值 */
	public static final String TARGET_ID = "";

	/** 商户私钥，pkcs8格式 */
	/** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
	/** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
	/** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
	/** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
	/** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
	public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDKlz83xGz3hCAI"+
			"n71DpEdUaAQixfwCghNSnRVTUr6VK7zpHEDwABEBzNz8jaq57ZwAV17VoEPQf9R9"+
			"u47WEP6hmwRBi90ztva8xGqv85Za6cAmK3zx2JqBr9RMA9Syq0R6RY8Endj+We8r"+
			"5PVDzStqcLPgVvUw++RaVlS50eYcbZv43jwd9tTvMsNoJFLq6UadAhCOVjEAjr+i"+
			"ZfTghuzrMcoXaDmeMcx0/dJSNheuVAA+0GZfnQ10hTYmj9waPKP78wpbVNIbU41z"+
			"qMw+cdMpvchZjLtDVRRg+2cJi9sj/ALZfXF2GAObLT4poL2iG65K4QUQzxivozBS"+
			"f2U0QtNnAgMBAAECggEALdStfoY3SH9hZ9ILVQQTButJFDzJ2B9pE6Vpr6Hmb4Sn"+
			"hezYW/MH5epci93Yo5s3UXRyhc1tyftBLPiDRbYuhIy3F8T2U76ubuHYaNmoSATd"+
			"bUUlTOj9ex0138R2F71bDnYx9o9nCZN64ek3+xCtI7U/XB8P9PbnH/Cr1iW/8uoJ"+
			"0m69J+6cYny1si66MpKmOWbOeh0c88F+ZtMBOjAof5I2ZJP4B5AADvRyrVfyUTfk"+
			"jEh9i8P08nHkrk5X/Hv0q18oViWMjwk+NtKZopH2YgSC0JGjIPiae4bb3++8ZG0a"+
			"nnCTTOZ+QWgCN2mkdqhv2UYpgv9qRraXN1liJfjMgQKBgQDwA94lrURZF47mI3Bd"+
			"t0pZyWp/yZkFG0FuEvoRqYKAzerTFECfpL1/5AJ6xRT7szenAO2A96h8FSEuCz6E"+
			"00WXyZlH6D12Y/ntVoaIDhMxnryCb0WuDxLfyjw3qeffnbmExpKCOFxOxp/iMfse"+
			"Gt2cvyHo32n5h8MMPPr9gZ2M4QKBgQDYFVB9X9BrUPBak5sNYzGlX/08SFrVwMq+"+
			"O6cCPYUv/tct34JqSooYtuQtFAIRtEcsuXceYfR8iq6JVEfxUZo8LQkPDs6Dl8FG"+
			"Uw/RPWVZQ4nxqxKZih1VJu/1EQxNyA45f1VoOPiasJkJyV+6h1ScRg6PIujHDrhK"+
			"Pr2IaZ/hRwKBgQDTgRUJ7p4ERJZQh3pFm3eRl8SZx3Vo4pPOBDsJxW6TQuJkD7zC"+
			"8dFsGel1dqb/BvdL261vsM5wSc5rXM1DD0qMb8L7ctR2t7NOSqlSy9RuLYvBBX1J"+
			"3U6slvJpuvfyPnN1ZedFiZUOdkn+cjzwUoxnzKZ9KCOjGNfwuOwU6VuFAQKBgE8H"+
			"VJ3WCiMFwjJOY9n8mm6ycaq3h8k/rqbuqEEwgOKx4L8dTNRVGLa7RS6bb7pL5fAc"+
			"kM1i5Akcq4L8eSdwQemaSwLf1nXYbc6rmFBdlRCVnADwIWhYVCwNhptptcdI0LME"+
			"uBbbJ0lkJ0SmCsXssuxIzxBYoywSXSKCWc1nXBsHAoGAFtkFMkHfLg45sYigriuP"+
			"wCom8nWNJFHz4sHZbPgu7rk8D4u1AQVFvgAcNdCs+7+We6a7mwOvEtSsoS5mfeTi"+
			"fALtK2UmayfXKt9hc6d8GKohID9QsbkoRJuThpA+cIow3LsFOBXKVym11gpDp+LU"+
			"BfpnSzUOCzgwyIT+j5k/6ps=";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://"+WXContants.Website+"/website/alireport/reportN";


	public static final String RSA_PRIVATE = "";

	private static final int SDK_PAY_FLAG = 1;

	private static final int SDK_AUTH_FLAG = 2;


	public static Map<String, String> buildAliOrder(boolean rsa2 ,String num,Long allMoney,String productName,String orderNo){

		Map<String, String> keyValues = new HashMap<String, String>();
		double allMoneyd = ((double)allMoney)/100;

		keyValues.put("app_id", APPID);//allMoney

		keyValues.put("biz_content", "{\"timeout_express\":\"30m\",\"product_code\":\""+num+"\",\"total_amount\":\""+allMoneyd+"\",\"subject\":\""+productName+"*"+num+"\",\"body\":\""+productName+
				"\",\"out_trade_no\":\"" + orderNo +  "\",\"notify_url\":\"" + notify_url +  "\"}");

		keyValues.put("charset", "utf-8");

		keyValues.put("method", "alipay.trade.app.pay");

		keyValues.put("sign_type", rsa2 ? "RSA2" : "RSA");

		keyValues.put("timestamp", "2016-07-29 16:55:53");

		keyValues.put("version", "1.0");

		return keyValues;
	}
	public String  getSign(String num,Long allMoney,String productName,String orderNo){

		boolean rsa2 = (RSA2_PRIVATE.length() > 0);
		Map<String, String> params = buildAliOrder(rsa2,num,allMoney,productName,orderNo);
		String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
		String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
		String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
		final String orderInfo = orderParam + "&" + sign;
		return orderInfo;
	}
}
