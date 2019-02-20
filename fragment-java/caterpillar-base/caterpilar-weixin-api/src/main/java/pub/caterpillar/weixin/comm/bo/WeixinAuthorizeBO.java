package pub.caterpillar.weixin.comm.bo;

import java.util.Date;
import java.util.List;


public class WeixinAuthorizeBO {
	
	/********************
	 *   接口访问token
	 ********************/
	//接口访问token
	private String access_token_base;
	
	//接口访问token超时时间
	private String expires_in_base;
	
	//接口访问token更新时间
	private Long cacheTime_access_token_base;

	/********************
	 * 以下为微信用户授权信息
	 ********************/
	
	//网页授权接口调用凭证
	private String access_token;
	
	//access_token接口调用凭证超时时间，单位（秒）
	private Long expires_in; 
	
	//用户刷新access_token
	private String refresh_token;
	
	//用户授权的作用域，使用逗号（,）分隔
	private String scope;
	
	//access_token更新时间
	private Long cacheTime_access_token;
	
	/********************
	 * 以下为微信用户基本信息
	 ********************/
	
	//用户唯一标识
	private String openid;
	
	//用户昵称
	private String nickname;
	
	//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private Integer sex;
	
	//用户个人资料填写的省份
	private String province;
	
	//普通用户个人资料填写的城市
	private String city;
	
	//国家，如中国为CN
	private String country;
	
	//用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	private String headimgurl;
	
	//用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
	private List<String> privilege;
	
	//只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
	private String unionid;
	
	/********************
	 *       其他字段
	 ********************/
	
	//缓存更新时间
	private Long cacheTime;

	public String getAccess_token_base() {
		return access_token_base;
	}

	public void setAccess_token_base(String access_token_base) {
		this.access_token_base = access_token_base;
	}

	public String getExpires_in_base() {
		return expires_in_base;
	}

	public void setExpires_in_base(String expires_in_base) {
		this.expires_in_base = expires_in_base;
	}

	public Long getCacheTime_access_token_base() {
		return cacheTime_access_token_base;
	}

	public void setCacheTime_access_token_base(Long cacheTime_access_token_base) {
		this.cacheTime_access_token_base = cacheTime_access_token_base;
	}

	public String getAccess_token() {
		return access_token;
	}

	public WeixinAuthorizeBO setAccess_token(String access_token) {
		this.access_token = access_token;
		return this;
	}

	public Long getExpires_in() {
		return expires_in;
	}

	public WeixinAuthorizeBO setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
		return this;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public WeixinAuthorizeBO setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
		return this;
	}

	public String getScope() {
		return scope;
	}

	public WeixinAuthorizeBO setScope(String scope) {
		this.scope = scope;
		return this;
	}
	
	public Long getCacheTime_access_token() {
		return cacheTime_access_token;
	}

	public void setCacheTime_access_token(Long cacheTime_access_token) {
		this.cacheTime_access_token = cacheTime_access_token;
	}

	public String getOpenid() {
		return openid;
	}

	public WeixinAuthorizeBO setOpenid(String openid) {
		this.openid = openid;
		return this;
	}

	public String getNickname() {
		return nickname;
	}

	public WeixinAuthorizeBO setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public Integer getSex() {
		return sex;
	}

	public WeixinAuthorizeBO setSex(Integer sex) {
		this.sex = sex;
		return this;
	}

	public String getProvince() {
		return province;
	}

	public WeixinAuthorizeBO setProvince(String province) {
		this.province = province;
		return this;
	}

	public String getCity() {
		return city;
	}

	public WeixinAuthorizeBO setCity(String city) {
		this.city = city;
		return this;
	}

	public String getCountry() {
		return country;
	}

	public WeixinAuthorizeBO setCountry(String country) {
		this.country = country;
		return this;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public WeixinAuthorizeBO setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
		return this;
	}

	public List<String> getPrivilege() {
		return privilege;
	}

	public WeixinAuthorizeBO setPrivilege(List<String> privilege) {
		this.privilege = privilege;
		return this;
	}

	public String getUnionid() {
		return unionid;
	}

	public WeixinAuthorizeBO setUnionid(String unionid) {
		this.unionid = unionid;
		return this;
	}

	public Long getCacheTime() {
		return cacheTime;
	}

	public WeixinAuthorizeBO setCacheTime(Long cacheTime) {
		this.cacheTime = cacheTime;
		return this;
	}
	
	//将其他对象属性合并到this
	public void merge(WeixinAuthorizeBO other){
		if(other == null) return;
		if(other.getAccess_token_base() != null) this.setAccess_token_base(other.getAccess_token_base());
		if(other.getExpires_in_base() != null) this.setExpires_in_base(other.getExpires_in_base());
		if(other.getCacheTime_access_token_base() != null) this.setCacheTime_access_token_base(other.getCacheTime_access_token_base());
		if(other.getAccess_token() != null) this.setAccess_token(other.getAccess_token());
		if(other.getCity() != null) this.setCity(other.getCity());
		if(other.getCountry() != null) this.setCountry(other.getCountry());
		if(other.getExpires_in() != null) this.setExpires_in(other.getExpires_in());
		if(other.getHeadimgurl() != null) this.setHeadimgurl(other.getHeadimgurl());
		if(other.getNickname() != null) this.setNickname(other.getNickname());
		if(other.getOpenid() != null) this.setOpenid(other.getOpenid());
		if(other.getPrivilege() != null) this.setPrivilege(other.getPrivilege());
		if(other.getProvince() != null) this.setProvince(other.getProvince());
		if(other.getRefresh_token() != null) this.setRefresh_token(other.getRefresh_token());
		if(other.getScope() != null) this.setScope(other.getScope());
		if(other.getCacheTime_access_token() != null) other.setCacheTime_access_token(this.getCacheTime_access_token());
		if(other.getSex() != null) this.setSex(other.getSex());
		if(other.getUnionid() != null) this.setUnionid(other.getUnionid());
	}
	
	//设置缓存时间
	public void setCacheTime(){
		this.setCacheTime(new Date().getTime()/1000);
	}
}
