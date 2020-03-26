package com.sumavision.tetris.auth.login;

import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sumavision.tetris.auth.login.exception.AppIdCannotBeNullException;
import com.sumavision.tetris.auth.login.exception.SignCannotBeNullException;
import com.sumavision.tetris.auth.login.exception.SignVerifyFailException;
import com.sumavision.tetris.auth.login.exception.TimestampCannotBeNullException;
import com.sumavision.tetris.auth.login.exception.UnknownAppIdException;
import com.sumavision.tetris.commons.util.encoder.MessageEncoder.Sha256Encoder;
import com.sumavision.tetris.user.BasicDevelopmentDAO;
import com.sumavision.tetris.user.BasicDevelopmentPO;
import com.sumavision.tetris.user.BasicDevelopmentQuery;
import com.sumavision.tetris.user.UserDAO;
import com.sumavision.tetris.user.UserPO;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserStatus;
import com.sumavision.tetris.user.UserVO;
import com.sumavision.tetris.user.exception.PasswordErrorException;
import com.sumavision.tetris.user.exception.TokenTimeoutException;
import com.sumavision.tetris.user.exception.UsernameNotExistException;

@Service
@Transactional(rollbackFor = Exception.class)
public class LoginService {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private UserQuery userQuery;
	
	@Autowired
	private Sha256Encoder sha256Encoder;
	
	@Autowired
	private BasicDevelopmentQuery basicDevelopmentQuery;
	
	@Autowired
	private BasicDevelopmentDAO basicDevelopmentDao;
	
	/**
	 * 强制用户id登录<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月5日 下午5:13:08
	 * @param Long userId 用户名id
	 * @return String token
	 */
	public String doUserIdLogin(Long userId) throws Exception{
		UserPO user = userDao.findOne(userId);
		if(userQuery.userTokenUseable(user)){
			return user.getToken();
		}else{
			String token = UUID.randomUUID().toString().replaceAll("-", "");
			user.setLastModifyTime(new Date());
			user.setToken(token);
			userDao.save(user);
			return token;
		}
	}
	
	/**
	 * 用户名密码登录<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月5日 下午5:13:08
	 * @param String username 用户名
	 * @param String password 密码
	 * @param String verifyCode 验证码
	 * @return String token
	 */
	public String doPasswordLogin(
			String username,
			String password,
			String verifyCode) throws Exception{
		
		UserPO user = userDao.findByUsername(username);
		
		if(user == null) throw new UsernameNotExistException(username);
		
		password = sha256Encoder.encode(password);
		
		if(!user.getPassword().equals(password)) throw new PasswordErrorException(username, password);
		
		String token = user.getToken();
		boolean tokenUseable = false;
		if(token != null){
			try{
				userQuery.checkToken(token);
				tokenUseable = true;
			}catch(Exception e){}
		}
		if(tokenUseable) return user.getToken();
		
		token = UUID.randomUUID().toString().replaceAll("-", "");
		user.setStatus(UserStatus.ONLINE);
		user.setLastModifyTime(new Date());
		user.setToken(token);
		userDao.save(user);
		
		return token;
	}
	
	/**
	 * 开发者登录<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年4月22日 下午4:30:59
	 * @param String appId 开发者id
	 * @param String timestamp 时间戳
	 * @param String sign 签名
	 * @return String token
	 */
	public String doAppSecretLogin(
			String appId, 
			String timestamp, 
			String sign) throws Exception{
		
		//参数校验
		if(appId == null) throw new AppIdCannotBeNullException();
		if(timestamp == null) throw new TimestampCannotBeNullException();
		if(sign == null) throw new SignCannotBeNullException();
		
		//签名校验
		BasicDevelopmentPO basicDevelopment = basicDevelopmentDao.findByAppId(appId);
		if(basicDevelopment==null) throw new UnknownAppIdException(appId);
		String serverSign = basicDevelopmentQuery.sign(appId, timestamp, basicDevelopment.getAppSecret());
		if(!serverSign.equals(sign)) throw new SignVerifyFailException(appId, timestamp, sign);
		
		//自动登录
		boolean needDoLogin = false;
		if(basicDevelopment.getToken() == null){
			needDoLogin = true;
		}else {
			try{
				boolean result = userQuery.checkToken(basicDevelopment.getToken());
				if(!result) needDoLogin = true;
			}catch(Exception e){
				needDoLogin = true;
			}
		}
		if(needDoLogin){
			UserPO user = userDao.findOne(basicDevelopment.getUserId());
			String token = UUID.randomUUID().toString().replaceAll("-", "");
			user.setLastModifyTime(new Date());
			user.setToken(token);
			userDao.save(user);
			basicDevelopment.setToken(token);
			basicDevelopmentDao.save(basicDevelopment);
			return token;
		}else{
			return basicDevelopment.getToken();
		}
	}
	
	/**
	 * 用户注销登录<br/>
	 * <p>这个接口暂时不清理缓存</p>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月8日 上午10:38:16
	 */
	public void doLogout() throws Exception{
		UserVO user = userQuery.current();
		UserPO userEntity = userDao.findOne(Long.valueOf(user.getUuid()));
		userEntity.setToken(null);
		userEntity.setLastModifyTime(null);
		userEntity.setStatus(UserStatus.OFFLINE);
		userDao.save(userEntity);
	}
	
}
