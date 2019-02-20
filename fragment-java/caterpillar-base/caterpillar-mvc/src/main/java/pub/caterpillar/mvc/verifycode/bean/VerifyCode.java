package pub.caterpillar.mvc.verifycode.bean;

import java.util.Date;

import pub.caterpillar.commons.img.VerifyCodeUtils;
import pub.caterpillar.commons.util.date.DateUtil;

public class VerifyCode{
		
	public static final String CATCHNAME = "verCode";
	
	//默认超时时间（5分钟）
	private static final int DEFAULT_TIMEOUT = 300000;
	
	//验证码
	private String token;
	
	//生成时间
	private Date generateTime;
	
	//超时时间（毫秒）
	private int timeout;

	public String getToken(){
		return this.token;
	}
	
	@Override
	public String toString() {
		return this.token;
	}
	
	public VerifyCode(int num){
		this(num, DEFAULT_TIMEOUT);
	}
	
	public VerifyCode(int num, int timeout){
		this(VerifyCodeUtils.generateVerifyCode(num), timeout);
	}
	
	public VerifyCode(String token){
		this(token, DEFAULT_TIMEOUT);
	}
	
	public VerifyCode(String token, int timeout){
		//默认转换成小写
		this.token = token.toLowerCase();
		this.generateTime = new Date();
		this.timeout = timeout;
	}
	
	//判断是否超时
	public boolean isTimeout(){
		//到期时间
		Date lastTime = DateUtil.addSecond(this.generateTime, this.timeout/1000);

		//当前时间
		Date currTime = new Date();
		
		if(lastTime.after(currTime)){
			return false;
		}else{
			return true;
		}
	}
	
	//验证码比较
	public boolean compare(String token){
		if(token == null){
			return false;
		}else if(token.toLowerCase().equals(this.token)){
			return true;
		}else{
			return false;
		}
	}
	
}