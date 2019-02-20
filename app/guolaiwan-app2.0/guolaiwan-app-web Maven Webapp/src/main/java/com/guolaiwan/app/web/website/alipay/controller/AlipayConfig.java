package com.guolaiwan.app.web.website.alipay.controller;

import java.io.FileWriter;
import java.io.IOException;

import pub.caterpillar.weixin.constants.WXContants;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016091401902378";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDKlz83xGz3hCAI"+
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
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlL86g7bzcjH+HpYGkM211TFI1pSAyyUpB1zKwl/8l9UHmkU2Sf4oTab9UwUEA7xxmHGi6xJU8saJ2SCB7Fi5knxLEEgHthdhpplhjy6JoCKWQJ7V5p+YpSN2MtEW3AV/i1QhCg5VQdvAgiK/mUUzoQBEbrtLeXwP4Ds8cEht4t2ZgomtUPBBBYl0lIL11ScY3jgOZtoLOCqzghhdIEJs5ru7+l91jxqxZCr7YHJDH5LW5YEjzW4qXiU/dwVxff32oSD7eTkjXjHwx7ZqWqIwu8LfT/b7Bsbno70T3jmjFmoP93ryt7N77MYlOdfe/GMUEseVaFsvOCPIPjrs8PIwmwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://"+WXContants.Website+"/website/alireport/reportN";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://"+WXContants.Website+"/website/alireport/reportR";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

