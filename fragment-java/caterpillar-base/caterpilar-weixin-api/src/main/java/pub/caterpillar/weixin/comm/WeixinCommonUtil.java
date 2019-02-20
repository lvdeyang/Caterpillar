package pub.caterpillar.weixin.comm;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pub.caterpillar.commons.util.binary.Md5Utils;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.communication.http.client.HttpClient;
import pub.caterpillar.weixin.comm.bo.WeixinAuthorizeBO;
import pub.caterpillar.weixin.comm.bo.WeixinConfigBO;
import pub.caterpillar.weixin.constants.CommonConstant;

/**
 * @author ldy
 * 微信通用接口
 */
public class WeixinCommonUtil {

	private static WeixinCommonUtil instance;
	
	//单例模式
	private WeixinCommonUtil(){}
	
	public static WeixinCommonUtil getInstance(){
		if(instance == null){
			instance = new WeixinCommonUtil();
		}
		return instance;
	}
	
	/***************
	 *    微信接口
	 ***************/
	
	//获取普通access_token
	public WeixinAuthorizeBO getBaseToken(Long key) throws Exception{
		//先从缓存拿授权信息
		WeixinAuthorizeBO autho = AuthorizeCacheUtil.getInstance().findAuthorizeInfo(key);
		
		if(autho==null || (autho!=null && autho.getAccess_token_base()==null)){
			//请求新的access_token
			WeixinConfigBO config_weixin = ConfigUtil.getInstance().getWeixinConfigBean();
			
			//请求参数
			Map<String, String> params = new HashMap<String, String>();
			params.put("appid", config_weixin.getAppid());
			params.put("secret", config_weixin.getAppsecret());
			params.put("grant_type", CommonConstant.PARAM_GRANTTYPE_CREDENTIAL);
			
			String response = HttpClient.getHttps(CommonConstant.URL_WEIXIN_CGIBIN_TOKEN, params, CommonConstant.CHARSET);
			
			JSONObject responseJson = JSON.parseObject(response);
			
			//将返回值转换一下
			responseJson.put("access_token_base", responseJson.get("access_token"));
			responseJson.remove("access_token");
			responseJson.put("expires_in_base", responseJson.get("expires_in"));
			responseJson.remove("expires_in");
			responseJson.put("expires_in_base", getCacheTime());
			
			int errorcode = responseJson.getIntValue("errcode");
			if(errorcode != 0){
				//出错了抛出异常
			}
			
			//缓存授权信息
			AuthorizeCacheUtil.getInstance().cacheAuthorizeInfo(key, responseJson);
			autho = AuthorizeCacheUtil.getInstance().findAuthorizeInfo(key);
		}
		
		return autho;
	}
	
	//通过code换取网页授权access_token
	public WeixinAuthorizeBO getAccessToken(Long key, String code) throws Exception{
		//先从缓存拿授权信息
		WeixinAuthorizeBO autho = AuthorizeCacheUtil.getInstance().findAuthorizeInfo(key);
		
		if(autho==null || (autho!=null && autho.getAccess_token()==null)){
			//请求新的access_token
			WeixinConfigBO config_weixin = ConfigUtil.getInstance().getWeixinConfigBean();
			
			//请求参数
			Map<String, String> params = new HashMap<String, String>();
			params.put("appid", config_weixin.getAppid());
			params.put("secret", config_weixin.getAppsecret());
			params.put("code", code);
			params.put("grant_type", CommonConstant.PARAM_GRANTTYPE_CODE);
			
			String response = HttpClient.getHttps(CommonConstant.URL_WEIXIN_OAUTH2_ACCESSTOKEN, params, CommonConstant.CHARSET);
			
			JSONObject responseJson = JSON.parseObject(response);
			int errorcode = responseJson.getIntValue("errcode");
			if(errorcode != 0){
				//出错了抛出异常
			}
			
			//缓存授权信息
			AuthorizeCacheUtil.getInstance().cacheAuthorizeInfo(key, responseJson);
			autho = AuthorizeCacheUtil.getInstance().findAuthorizeInfo(key);
		}
		
		return autho;
	}
	
	//检验access_token是否有效
	public WeixinAuthorizeBO checkAccessToken(Long key) throws Exception{
		//先从缓存拿授权信息
		WeixinAuthorizeBO autho = AuthorizeCacheUtil.getInstance().findAuthorizeInfo(key);
		
		if(autho==null || (autho!=null && autho.getAccess_token()==null)){
			//没有access_token抛出异常
		}
		
		//请求参数
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", autho.getAccess_token());
		params.put("openid", autho.getOpenid());
		
		String response = HttpClient.postHttps(CommonConstant.URL_WEIXIN_AUTH, params, CommonConstant.CHARSET);
		
		JSONObject responseJson = JSON.parseObject(response);
		int errorcode = responseJson.getIntValue("errcode");
		if(errorcode != 0){
			//出错了抛出异常
		}
		
		return autho;
	}
	
	//刷新access_token
	public WeixinAuthorizeBO refreshAccessToken(Long key) throws Exception{
		//先从缓存拿授权信息
		WeixinAuthorizeBO autho = AuthorizeCacheUtil.getInstance().findAuthorizeInfo(key);
		
		if(autho==null || (autho!=null && autho.getRefresh_token()==null)){
			//没有refresh_token抛出异常
		}
		
		//请求新的access_token
		WeixinConfigBO config_weixin = ConfigUtil.getInstance().getWeixinConfigBean();
		//请求参数
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", config_weixin.getAppid());
		params.put("grant_type", CommonConstant.PARAM_GRANTTYPE_REFRESHTOKEN);
		params.put("refresh_token", autho.getRefresh_token());
		
		String response = HttpClient.postHttps(CommonConstant.URL_WEIXIN_OAUTH2_REFRESHTOKEN, params, CommonConstant.CHARSET);
		
		JSONObject responseJson = JSON.parseObject(response);
		int errorcode = responseJson.getIntValue("errcode");
		if(errorcode != 0){
			//出错了抛出异常
		}
		
		//缓存授权信息
		AuthorizeCacheUtil.getInstance().cacheAuthorizeInfo(key, responseJson);
		autho = AuthorizeCacheUtil.getInstance().findAuthorizeInfo(key);
		
		return autho;
	}
	
	//获取用户信息
	public WeixinAuthorizeBO getUserInfo(Long key) throws Exception{
		//先从缓存拿授权信息
		WeixinAuthorizeBO autho = AuthorizeCacheUtil.getInstance().findAuthorizeInfo(key);
		
		if(autho==null || (autho!=null && autho.getAccess_token()==null)){
			//没有access_token抛异常
		}else if(autho.getNickname() == null){
			if(autho.getScope().indexOf(CommonConstant.SCOPE_USERINFO) < 0){
				//没有权限 抛出异常
			}
			
			//获取用户信息 请求参数
			Map<String, String> params = new HashMap<String, String>();
			params.put("access_token", autho.getAccess_token());
			params.put("openid", autho.getOpenid());
			params.put("lang", CommonConstant.LANG_ZH_CN);
			
			//这个接口官网上说让GET请求，私下测试post也能请求到
			String response = HttpClient.postHttps(CommonConstant.URL_WEIXIN_USERINFO, params, CommonConstant.CHARSET);
			
			JSONObject responseJson = JSON.parseObject(response);
			int errorcode = responseJson.getIntValue("errcode");
			if(errorcode != 0){
				//出错了抛出异常
			}
			
			//缓存授权信息
			AuthorizeCacheUtil.getInstance().cacheAuthorizeInfo(key, responseJson);
			autho = AuthorizeCacheUtil.getInstance().findAuthorizeInfo(key);
		}
		
		return autho;
	}
	
	/***************
	 *   私有方法
	 ***************/
	
	//获取当前时间戳（单位：秒）
	private Long getCacheTime(){
		return new Date().getTime()/1000;
	}
	
	
	/***************
	 *   内部工具类
	 ***************/
	
	//随机字符串
	private Random random;
	
	private class Random{
		
		//随机字符串类型
		public static final String RANDOM_STRINGTYPE_NUM = "number";
		public static final String RANDOM_STRINGTYPE_LETTER = "letter";
		public static final String RANDOM_STRINGTYPE_LETTER_LOWERCASE = "letterl";
		public static final String RANDOM_STRINGTYPE_LETTER_UPPERCASE = "letteru";
		public static final String RANDOM_STRINGTYPE_NUMANDLETTER = "numberAndLetter";
		public static final String RANDOM_STRINGTYPE_NUMANDLETTER_LOWERCASE = "numberAndLetterl";
		public static final String RANDOM_STRINGTYPE_NUMANDLETTER_UPPERCASE = "numberAndLetterU";
		
		//大小写
		public static final String LETTER_LOWERCASE = "lowercase";
		public static final String LETTER_UPPERCASE = "uppercase";
		
		//生成一个变长的特定类型的字符串
		public String getRandomString(int maxlen, String type){
			int length = (int)(Math.random()*(maxlen-1) + 1);
			return getFixedlenRandomString(length, type);
		}
		
		//生成一个定长的特定类型字符串
		public String getFixedlenRandomString(int fixedlen, String type){
			StringBuilder randomBuilder = new StringBuilder();
			for(int i=1; i<=fixedlen; i++){
				if(RANDOM_STRINGTYPE_NUM.equals(type)){
					//全数字
					randomBuilder.append(getRandomNum());
				}else if(RANDOM_STRINGTYPE_LETTER_LOWERCASE.equals(type)){
					//小写字母
					randomBuilder.append(getRandomLetter(LETTER_LOWERCASE));
				}else if(RANDOM_STRINGTYPE_LETTER_UPPERCASE.equals(type)){
					//大写字母
					randomBuilder.append(getRandomLetter(LETTER_UPPERCASE));
				}else if(RANDOM_STRINGTYPE_LETTER.equals(type)){
					//大小写字母混合
					int random = (int)(Math.random()*9+1);
					if(random <= 5){
						randomBuilder.append(getRandomLetter(LETTER_LOWERCASE));
					}else{
						randomBuilder.append(getRandomLetter(LETTER_UPPERCASE));
					}
				}else if(RANDOM_STRINGTYPE_NUMANDLETTER_LOWERCASE.equals(type)){
					//数字和小写字母
					int random = (int)(Math.random()*9+1);
					if(random <= 5){
						randomBuilder.append(getRandomNum());
					}else{
						randomBuilder.append(getRandomLetter(LETTER_LOWERCASE));
					}
				}else if(RANDOM_STRINGTYPE_NUMANDLETTER_UPPERCASE.equals(type)){
					//数字和大写字母
					int random = (int)(Math.random()*9+1);
					if(random <= 5){
						randomBuilder.append(getRandomNum());
					}else{
						randomBuilder.append(getRandomLetter(LETTER_UPPERCASE));
					}
				}else if(RANDOM_STRINGTYPE_NUMANDLETTER.equals(type)){
					//数字和字母
					int random = (int)(Math.random()*8+1);
					if(random <= 3){
						randomBuilder.append(getRandomNum());
					}else if(random>3 && random<=6){
						randomBuilder.append(getRandomLetter(LETTER_LOWERCASE));
					}else if(random>6 && random<=7){
						randomBuilder.append(getRandomLetter(LETTER_UPPERCASE));
					}
				}
			}
			return randomBuilder.toString();
		}
		
		//获取一个随机的（0~9）数字
		public int getRandomNum(){
			return (int)(Math.random()*9);
		}
		
		//获取一个随机字母
		public String getRandomLetter(String type){
			
			char letter = (char)((int)(Math.random()*25 + 65));
			if(LETTER_LOWERCASE.equals(type)){
				return String.valueOf(letter).toLowerCase();
			}else{
				//默认返回大写字母
				return String.valueOf(letter);
			}
		}
	
	}
	
	public Random getRandom(){
		if(random == null){
			random = new Random();
		}
		return random;
	}
	
	//签名
	private Sign sign;
	
	private class Sign{
		
		//生成签名
		public String generate(JSONObject params, String exceptKey) throws Exception{
			//获取微信配置
			WeixinConfigBO config_weixin = ConfigUtil.getInstance().getWeixinConfigBean();
			
			SortedSet<String> paramSet = new TreeSet<String>(new Comparator<String>(){
				@Override
				public int compare(String param1, String param2) {
					String key1 = param1.split("=")[0];
					String key2 = param2.split("=")[0];
					return key1.compareTo(key2);
				}
			});
			
			Set<String> keySet = params.keySet();
			for(String key:keySet){
				if(!key.equals(exceptKey)){
					String tmpParam = key + "=" + params.getString(key);
					paramSet.add(tmpParam);
				}
			}
			
			//拼接StringA
			StringBuilder sBuilder = new StringBuilder();
			for(String param:paramSet){
				sBuilder.append(param);
				sBuilder.append("$");
			}
			sBuilder.append("key=");
			sBuilder.append(config_weixin.getKey());
			
			return Md5Utils.MD5(sBuilder.toString()).toUpperCase();
		}
		
	}
	
	public Sign getSign(){
		if(sign == null){
			sign = new Sign();
		}
		return sign;
	}
	
	//id生成器
	private IDGenerator generator;
	
	private class IDGenerator{
		
		//商户订单号
		public String getOrderId() throws Exception{
			//获取微信配置
			WeixinConfigBO config_weixin = ConfigUtil.getInstance().getWeixinConfigBean();
			
			//获取当前日期
			String currDate = DateUtil.format(new Date(), DateUtil.currentDatePattern);
			
			//一天内不重复的10位数字（时间戳后10位）
			long currTime = new Date().getTime()%10000000000l;
			
			StringBuilder idBuilder = new StringBuilder();
			idBuilder.append(config_weixin.getMch_id());
			idBuilder.append(currDate);
			idBuilder.append(currTime);
			
			return idBuilder.toString();
		}
		
	}
	
	public IDGenerator getIDGenerator(){
		if(generator == null){
			generator = new IDGenerator();
		}
		return generator;
	}
	
}
