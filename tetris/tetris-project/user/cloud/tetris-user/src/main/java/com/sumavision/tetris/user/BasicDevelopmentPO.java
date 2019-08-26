package com.sumavision.tetris.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.sumavision.tetris.commons.context.SpringContext;
import com.sumavision.tetris.commons.util.encoder.MessageEncoder.AES;
import com.sumavision.tetris.orm.po.AbstractBasePO;

@Entity
@Table(name = "TETRIS_BASIC_DEVELOPMENT")
public class BasicDevelopmentPO extends AbstractBasePO{

	//开发者密码加密密钥
	public static final String APPSECTET_ENCODE_RULE = "%@sumavisionrd@%==";
	
	private static final long serialVersionUID = 1L;

	/** 开发者id，存用户uuid */
	public String appId;
	
	/** 开发者密码 */
	public String appSecret;
	
	/** 关联用户 */
	public Long userId;
	
	/** 登录token */
	public String token;

	@Column(name = "APP_ID")
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Column(name = "APP_SECRET")
	public String getAppSecret() throws Exception{
		try{
			if(appSecret == null) return null;
			if("".equals(appSecret)) return "";
			AES aes = SpringContext.getBean(AES.class);
			return aes.decode(APPSECTET_ENCODE_RULE, appSecret);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	public void setAppSecret(String appSecret) throws Exception{
		try{
			if(appSecret == null) this.appSecret = null;
			else if("".equals(appSecret)) this.appSecret = "";
			else{
				AES aes = SpringContext.getBean(AES.class);
				this.appSecret = aes.encode(APPSECTET_ENCODE_RULE, appSecret);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		
	}

	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "TOKEN")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
