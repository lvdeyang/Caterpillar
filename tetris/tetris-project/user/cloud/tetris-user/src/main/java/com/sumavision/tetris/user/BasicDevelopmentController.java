package com.sumavision.tetris.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumavision.tetris.commons.util.encoder.MessageEncoder.Sha256Encoder;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.user.exception.AppSecretCannotBeNullException;
import com.sumavision.tetris.user.exception.PasswordErrorException;

@Controller
@RequestMapping(value = "/basic/development")
public class BasicDevelopmentController {

	@Autowired
	private UserQuery userQuery;
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private BasicDevelopmentDAO basicDevelopmentDao;
	
	@Autowired
	private BasicDevelopmentService basicDevelopmentService;
	
	@Autowired
	private Sha256Encoder sha265Encoder;
	
	/**
	 * 查询用户基础配置<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年4月22日 上午11:20:38
	 * @return BasicDevelopmentVO 用户基础配置
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/query")
	public Object query(HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		BasicDevelopmentPO basicDevelopment = basicDevelopmentDao.findByUserId(Long.valueOf(user.getUuid()));
		
		if(basicDevelopment == null){
			basicDevelopment = basicDevelopmentService.add(user);
		}
		
		return new BasicDevelopmentVO().set(basicDevelopment);
	}
	
	/**
	 * 修改开发者密码<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年4月22日 上午11:36:54
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/reset/app/secret")
	public Object resetAppSecret(
			String appSecret,
			String password,
			HttpServletRequest request) throws Exception{
		
		if(appSecret==null || "".equals(appSecret)){
			throw new AppSecretCannotBeNullException();
		}
		
		UserVO user = userQuery.current();
		
		UserPO userEntity = userDao.findOne(Long.valueOf(user.getUuid()));
		
		if(!userEntity.getPassword().equals(sha265Encoder.encode(password))){
			throw new PasswordErrorException();
		}
		
		basicDevelopmentService.update(user, appSecret);
		
		return null;
	}
	 
	
}
