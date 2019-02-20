package com.guolaiwan.app.web.weixin;

import pub.caterpillar.weixin.constants.WXContants;

public class YuebaWxPayConstants {
    //第三方用户唯一ID
    public static String APPID = "";
    //第三方用户唯一凭证密码
    public static String APP_SECRET = "";
    //商户ID
    //public static String MCH_ID = "1266701001";
    
    public static String MCH_ID = WXContants.MchId;
    //微信商户平台-账户设置-安全设置-api安全,配置32位key
    //public static String KEY  = "21232f297a57a5a743894a0e4a801fc3";
    
    public static String KEY  = WXContants.AppKey;
    //交易类型
    public static String TRADE_TYPE_JS = "JSAPI";
    //微信支付回调url
    public static String NOTIFY_URL = "";
    
    public static void set(String notifyUrl,String appId,String appSecret){
    	APPID=appId;
    	APP_SECRET=appSecret;
    	NOTIFY_URL=notifyUrl;
    }
}
