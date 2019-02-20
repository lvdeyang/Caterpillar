package pub.caterpillar.weixin.constants;

public interface CommonConstant {

	/*****************
	 *  微信接口缓存相关
	 *****************/
	//微信用户授权缓存
	public static final String CACHENAME_WEIXIN_AUTHORIZE = "wx_authorize";
	
	/*****************
	 *  微信接口url地址
	 *****************/
	//获取code
	public static final String URL_WEIXIN_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize";
	//获取普通的access_token
	public static final String URL_WEIXIN_CGIBIN_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";
	//通过code获取access_token
	public static final String URL_WEIXIN_OAUTH2_ACCESSTOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
	//刷新access_tocken
	public static final String URL_WEIXIN_OAUTH2_REFRESHTOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
	//请求用户信息
	public static final String URL_WEIXIN_USERINFO = "https://api.weixin.qq.com/sns/userinfo";
	//检验access_token有效性
	public static final String URL_WEIXIN_AUTH = "https://api.weixin.qq.com/sns/auth";
	
	
	/*****************
	 *  微信接口常量参数
	 *****************/
	public static final String PARAM_GRANTTYPE_CREDENTIAL = "client_credential";
	public static final String PARAM_GRANTTYPE_CODE = "authorization_code";
	public static final String PARAM_GRANTTYPE_REFRESHTOKEN = "refresh_token";
	
	//授权作用域
	public static final String SCOPE_BASE = "snsapi_base";
	public static final String SCOPE_USERINFO = "snsapi_userinfo";
	
	//语言环境
	public static final String LANG_ZH_CN = "zh_CN";
	public static final String LANG_ZH_TW = "zh_TW";
	public static final String LANG_EN = "en";
	
	//字符编码
	public static final String CHARSET = "utf-8";
	
	//微信重定向后缀
	public static final String FLAG_REDIRECT = "#wechat_redirect";
	
	//响应类型
	public static final String RESPONSE_TYPE = "code";
}
