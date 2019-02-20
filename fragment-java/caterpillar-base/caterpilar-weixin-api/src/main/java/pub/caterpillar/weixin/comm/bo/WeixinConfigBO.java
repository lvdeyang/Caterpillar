package pub.caterpillar.weixin.comm.bo;
/**
 * @author ldy
 * 微信公众平台配置信息
 */
public class WeixinConfigBO {
	//微信公众号唯一标识
	private String appid;
	
	//公众号appsecret
	private String appsecret;
	
	//Token
	private String token;
	
	//微信支付分配的商户id
	private String mch_id;
	
	//商户名称
	private String send_name;
	
	//商户密钥
	private String key;

	public String getAppid() {
		return appid;
	}

	public WeixinConfigBO setAppid(String appid) {
		this.appid = appid;
		return this;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public WeixinConfigBO setAppsecret(String appsecret) {
		this.appsecret = appsecret;
		return this;
	}
	
	public String getToken() {
		return token;
	}

	public WeixinConfigBO setToken(String token) {
		this.token = token;
		return this;
	}

	public String getMch_id() {
		return mch_id;
	}

	public WeixinConfigBO setMch_id(String mch_id) {
		this.mch_id = mch_id;
		return this;
	}

	public String getSend_name() {
		return send_name;
	}

	public WeixinConfigBO setSend_name(String send_name) {
		this.send_name = send_name;
		return this;
	}

	public String getKey() {
		return key;
	}

	public WeixinConfigBO setKey(String key) {
		this.key = key;
		return this;
	}
	
}
