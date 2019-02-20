package pub.caterpillar.app.carpool.weixin;

import pub.caterpillar.app.carpool.vo.WeixinConfigVO;

public class YuebaWxPayConstants {
    //第三方用户唯一ID
    public static String APPID = "";
    //第三方用户唯一凭证密码
    public static String APP_SECRET = "";
    //商户ID
    public static String MCH_ID = "";
    //微信商户平台-账户设置-安全设置-api安全,配置32位key
    public static String KEY  = "";
    //交易类型
    public static String TRADE_TYPE_JS = "JSAPI";
    //微信支付回调url
    public static String NOTIFY_URL = "";
    
    public static void set(WeixinConfigVO vo,String notifyUrl){
    	APPID=vo.getAppid();
    	APP_SECRET=vo.getAppsecret();
    	MCH_ID=vo.getMch_id();
    	KEY=vo.getKey();
    	NOTIFY_URL=notifyUrl;
    }
}
